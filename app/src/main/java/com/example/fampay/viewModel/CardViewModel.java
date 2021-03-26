package com.example.fampay.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.load.HttpException;
import com.example.fampay.CardApplication;
import com.example.fampay.R;
import com.example.fampay.bean.CardGroup;
import com.example.fampay.data.Repository;
import com.example.fampay.utilities.PreferenceHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class CardViewModel extends ViewModel {
    /**
     * LiveData to keep track of the fetching process' status.
     * True if successfully fetched, false otherwise.
     */
    public MutableLiveData<Boolean> successfulFetch = new MutableLiveData();
    public List<CardGroup> cardGroups = new ArrayList<>();
    public String errorMessage;
    private Repository repository = new Repository();

    public void fetchConCards() {
        repository.getCardGroups()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<CardGroup>>() {

                    @Override
                    public void onNext(@NonNull List<CardGroup> cardGroups) {
                        cardGroups = getFilteredList(cardGroups);
                        successfulFetch.postValue(true);
                        Log.d("tag", "successfully fetched");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (new IOException().equals(e)) {
                            errorMessage = CardApplication
                                    .getContext().getString(R.string.check_connection);
                        } else if (new TimeoutException().equals(e)) {
                            errorMessage = CardApplication
                                    .getContext().getString(R.string.time_out);
                        } else if (new HttpException(e.getMessage()).equals(e.getMessage())) {
                            errorMessage = CardApplication
                                    .getContext().getString(R.string.cannot_connect_server);

                        } else {
                            {
                                Log.e("tag", e.getMessage());
                            }
                            errorMessage = CardApplication
                                    .getContext().getString(R.string.error_occurred);
                        }
                        successfulFetch.postValue(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * This method is used to exclude any group whose id is stored in local storage
     */
    // TODO: do this task in back-ground thread
    private List<CardGroup> getFilteredList(List<CardGroup> groups) {
        List<CardGroup> filteredList = new ArrayList<>();
        CardGroup group;

        for (int i = 0; i < groups.size(); i++) {
            group = groups.get(i);
            if (!PreferenceHelper.excludeGroup(group.getId().toString())) {
                filteredList.add(group);
            }
        }
        return filteredList;
    }


}
