package com.rmh.rhoffman.cbtvelocity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Very simple preference activity to let the user make a few personal preferences. All this does is inflate the
 * preference layout and fragment. The data the user selects is stored automatically and can be retrieved throughout
 * the app.
 */
public class SettingsActivity extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		// Set the preference fragment.
		getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
	}

	public static class SettingsFragment extends PreferenceFragment{

		@Override
		public void onCreate(final Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pref_notification);
		}

	}

}
