package com.softmiracle.githubmvp.presentation.modules.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.presentation.modules.home.HomeActivity;

public class SettingsActivity extends AppCompatPreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String KEY_PREF_THEME = "theme";
    @SuppressWarnings("unused")
    private static final String THEME_LIGHT = "light";
    private static final String THEME_DARK = "dark";

    public static final int THEME_TYPE_GLOBAL = 0;
    public static final int THEME_TYPE_LOGIN = 1;
    public static final int THEME_TYPE_SETTINGS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        setupActionBar();
        bindPreferenceSummaryToValue(findPreference(KEY_PREF_THEME));
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    private static void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(KEY_PREF_THEME)) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public static boolean isDark(Context context) {
        SharedPreferences preference = PreferenceManager
                .getDefaultSharedPreferences(context);
        return preference.getString(KEY_PREF_THEME, THEME_LIGHT).equals(THEME_DARK);
    }

    public static int getTheme(Context context, int themeType) {
        switch (themeType) {
            case THEME_TYPE_GLOBAL:
                if (isDark(context)) {
                    return R.style.SempraTheme_dark;
                } else {
                    return R.style.SempraTheme_light;
                }
            case THEME_TYPE_LOGIN:
                if (isDark(context)) {
                    return R.style.SempraTheme_dark_Login;
                } else {
                    return R.style.SempraTheme_light_Login;
                }
            default:
            case THEME_TYPE_SETTINGS:
                if (isDark(context)) {
                    return R.style.SempraTheme_dark_Settings;
                } else {
                    return R.style.SempraTheme_light_Settings;
                }
        }
    }
}
