package com.dragonites.practice.hard;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Dragonites on 14/11/2016.
 */
public class SettingsActivity extends Activity{

    private Spinner spinner;
    private SeekBar seekBar;
    private TextView seekBarValue;
    private CheckBox checkBox;

    int step = 4;
    int max = 2100;
    int min = 1800;

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        spinner = (Spinner) this.findViewById(R.id.spinner);
        seekBar = (SeekBar) this.findViewById(R.id.seekbar);
        seekBarValue = (TextView) this.findViewById(R.id.seekbar_value);
        checkBox = (CheckBox) this.findViewById(R.id.checkbox);

        seekBar.setMax( (max - min) / step );
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int value = min + (progress * step);
            seekBarValue.setText(value + "");
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt("spinner", spinner.getSelectedItemPosition());

        editor.putInt("seekbar", seekBar.getProgress());

        editor.putBoolean("checkbox", checkBox.isChecked());

        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        spinner.setSelection(settings.getInt("spinner", 0));

        seekBar.setProgress(settings.getInt("seekbar", 0));

        checkBox.setChecked(settings.getBoolean("checkbox", false));
    }
}
