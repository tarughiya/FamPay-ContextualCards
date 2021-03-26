package com.example.fampay.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.fampay.CardApplication;

/**
 * Helper class to manage storing and fetching data using Shared Preferences
 */
public class PreferenceHelper {
    private static String PREF_KEY = "contextual_card_pref";
    private SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private Context context;
    private int privateMode = 0;

    public PreferenceHelper(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_KEY, privateMode);
        editor = pref.edit();

    }

    /**
     * Helper method to save groupId locally using a string of Id(s) separated by ','
     *
     * @param groupId a unique identifier of group to be excluded.
     */
    public static void addGroupId(String groupId) {
        String storedString;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CardApplication.getContext());
        if (preferences != null) {
            storedString = preferences.getString(PREF_KEY, "").toString();
            storedString = storedString.concat(",").concat(groupId);
            editor = preferences.edit();
            editor.putString(PREF_KEY, storedString);
            editor.apply();
        }
    }

    /**
     * Helper method to check if specific group has to be excluded or not
     *
     * @param groupId a unique identifier of the group which may/maynot be excluded
     * @return true if the group has to be excluded, false otherwise
     */

    public static boolean excludeGroup(String groupId) {
        String id = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CardApplication.getContext());
        if (preferences != null) {
            String storedString = preferences.getString(PREF_KEY, "");
            String[] stringList = storedString.split(",");
            if (stringList != null) {
                for (int i = 0; i < stringList.length; i++) {
                    if (groupId.equals(stringList[i])) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }
}
