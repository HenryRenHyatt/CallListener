package com.bearapp.calllistener;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;

/**
 * Created by Henry.Ren on 16/7/7.
 */
public class ListenContactsPreference extends Preference {

    private static final String TAG = "ListenContactsPreference";


    public ListenContactsPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public ListenContactsPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListenContactsPreference(Context context) {
        this(context, null);
    }

}
