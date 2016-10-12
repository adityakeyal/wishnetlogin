package com.aditya.wishnetlogin.prefs;

import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditya.wishnetlogin.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PreferenceActivityFragment extends PreferenceFragment {

    public PreferenceActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        addPreferencesFromResource(R.xml.settings_preferences);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
