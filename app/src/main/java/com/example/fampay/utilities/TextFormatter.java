package com.example.fampay.utilities;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.fampay.bean.Entity;
import com.example.fampay.bean.FormattedTitle;

public class TextFormatter {

    public static void applyFormattedText(FormattedTitle formattedTitle, AppCompatTextView textView, String callBackText) {

        if (formattedTitle == null) {
            textView.setText(callBackText);
        } else {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i = 0; i < formattedTitle.entityList.size(); i++) {
                Entity entity = formattedTitle.entityList.get(i);
                int colorCode = Color.parseColor(formattedTitle.entityList.get(i).getColor());
                SpannableString spannableString = new SpannableString(entity.getText());
                spannableString.setSpan(
                        new ForegroundColorSpan(colorCode),
                        0,
                        entity.getText().length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                spannableStringBuilder.append(spannableString);
                if (spannableStringBuilder.toString().isEmpty()) {
                    textView.setText(callBackText);
                } else {
                    textView.setText(spannableString);
                }


            }

        }
    }
}
