package com.christianv07.dev.speedy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.christianv07.dev.speedy.aboutActivity.AboutActivity;
import com.christianv07.dev.speedy.contactActivities.ContactActivity;
import com.christianv07.dev.speedy.introActivity.MyIntro;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    // References to variables to use later on to verify is both TextFields are empty or not
    private TextView et1, et2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // show menu when menu button is pressed
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // display a message when a button was pressed

//        String message = "";

        switch (item.getItemId()) {
            case R.id.menuRate:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.christianv07.dev.speedy&hl=en")));
                break;
            case R.id.menuContact:
                startActivity(new Intent(getApplicationContext(), ContactActivity.class));
                break;
            case R.id.menuAbout:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Launch Screen theme to show while the app is loading
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        // OnCreate's layout to use
        setContentView(R.layout.activity_main);

        // Show intro once
        MyIntro();

        // References to all views and constant values to proceed with all the calculations
        final double KB = Math.pow(2, 10), MB = Math.pow(2, 20), GB = Math.pow(2, 30), TB = Math.pow(2, 40), Kbps = Math.pow(10, 3);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        final TextView txtFileSize = (TextView) findViewById(R.id.txtFile);
        final TextView txtSpeed = (TextView) findViewById(R.id.txtSpeed);
        ((SeekBar) findViewById(R.id.seekBar)).setOnSeekBarChangeListener(this);
        final SeekBar progressBar = (SeekBar) findViewById(R.id.seekBar);
        final Button btnCalc = (Button) findViewById(R.id.btnCalculate);
        final TextView txtFYI = (TextView) findViewById(R.id.txtFYI);

        // Method to load all the strings from the string-array inside res/values/strings.xml
        LoadSpin(spinner, spinner2);

        // Variables to verify if both TextFields are empty or not
        et1 = txtFileSize;
        et2 = txtSpeed;

        // Setting listeners
        et1.addTextChangedListener(mTextWatcher);
        et2.addTextChangedListener(mTextWatcher);

        // Run once to disable if empty
        checkFieldsForEmptyValues();

        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Variables needed to do all calculations
                double averageSpeed, fileSize, progress, seconds, finalFileSize, percentageExtracted;
                fileSize = Double.parseDouble(txtFileSize.getText().toString());
                averageSpeed = Double.parseDouble(txtSpeed.getText().toString());
                progress = progressBar.getProgress();

                // Verifying user input from the first spinner
                switch (spinner.getSelectedItem().toString()) {
                    case "KB":
                        fileSize = fileSize * KB;
                        break;
                    case "MB":
                        fileSize = fileSize * MB;
                        break;
                    case "GB":
                        fileSize = fileSize * GB;
                        break;
                    case "TB":
                        fileSize = fileSize * TB;
                        break;
                }
                // Verifying user input from the second spinner
                switch (spinner2.getSelectedItem().toString()) {
                    case "KB/s":
                        averageSpeed = averageSpeed * KB;
                        break;
                    case "MB/s":
                        averageSpeed = averageSpeed * MB;
                        break;
                    case "GB/s":
                        averageSpeed = averageSpeed * GB;
                        break;
                    case "TB/s":
                        averageSpeed = averageSpeed * TB;
                        break;
                    case "Kbps":
                        averageSpeed = (averageSpeed / 8) * Kbps;
                        break;
                    case "Mbps":
                        averageSpeed = (averageSpeed / 8) * MB;
                        break;
                    case "Gbps":
                        averageSpeed = (averageSpeed / 8) * GB;
                        break;
                    case "Tbps":
                        averageSpeed = (averageSpeed / 8) * TB;
                        break;
                }
                // Calculations based on the user input
                if (progress > 0) {
                    percentageExtracted = fileSize * (progress / 100);
                    finalFileSize = fileSize - percentageExtracted;
                    seconds = finalFileSize / averageSpeed;
                } else
                    seconds = fileSize / averageSpeed;

                new MaterialDialog.Builder(MainActivity.this)
//                        .title(R.string.menuAboutBtn)
                        .content(getString(R.string.sDownloadMessage) + convertSeconds(seconds))
                        .positiveText(R.string.sBtnAlert)
                        .show();
            }
        });
    }

    // Method to track SeekBar's progress
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        ((TextView) findViewById(R.id.lblPercent)).setText(progress + "%");
    }

    // Method to track when the SeekBar has started moving
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        ((TextView) findViewById(R.id.lblPercent)).setText(seekBar.getProgress() + "%");
    }

    // Method to track when the SeekBar has stopped
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        ((TextView) findViewById(R.id.lblPercent)).setText(seekBar.getProgress() + "%");
    }

    // Method used to assign all the string-array values to show for spinner1 and spinner2
    public void LoadSpin(Spinner spin, Spinner spin2) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinner2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter2);
    }

    // Method used to convert seconds to all other time's format
    public String convertSeconds(double seconds2) {
        double seconds = seconds2 % 60;
        double minutes = (seconds2 / 60) % 60;
        double hours = seconds2 / 3600;
        double days, weeks, months, years;
        String stringDays = getString(R.string.stringDays);
        String stringWeeks = getString(R.string.stringWeeks);
        String stringMonths = getString(R.string.stringMonths);
        String stringYears = getString(R.string.stringYears);
        String time = "";

        // Making sure Days are shown only show when Hours >= 24
        if (hours >= 24) {
            days = hours / 24;
            hours = hours % 24;
            time = String.format(Locale.US, "%2d %s, %02d:%02d:%02d", (int) days, stringDays, (int) hours, (int) minutes, (int) seconds);
            // Making sure Weeks are shown only when Days >= 7
            if (days >= 7) {
                weeks = days / 7;
                days = days % 7;
                time = String.format(Locale.US, " %2d %s, %2d %s, %02d:%02d:%02d", (int) weeks, stringWeeks, (int) days, stringDays, (int) hours, (int) minutes, (int) seconds);
                // Making sure Months are shown only when Weeks >= 4
                if (weeks >= 4) {
                    months = weeks / 4;
                    weeks = weeks % 4;
                    time = String.format(Locale.US, " %2d %s, %2d %s, %2d %s, %02d:%02d:%02d", (int) months, stringMonths, (int) weeks, stringWeeks, (int) days, stringDays, (int) hours, (int) minutes, (int) seconds);
                    // Making sure Years are shown only when Months >= 12
                    if (months >= 12) {
                        years = months / 12;
                        months = months % 12;
                        time = String.format(Locale.US, " %2d %s, %2d %s, %2d %s, %2d %s, %02d:%02d:%02d", (int) years, stringYears, (int) months, stringMonths, (int) weeks, stringWeeks, (int) days, stringDays, (int) hours, (int) minutes, (int) seconds);
                    }
                }
            }
        }
        // Showing the default values of HH:MM:SS
        else {
            time = String.format(Locale.US, "%02d:%02d:%02d", (int) hours, (int) minutes, (int) seconds);
        }
        return time;
    }

    // Method to check if TextFields are empty
    void checkFieldsForEmptyValues() {
        Button b = (Button) findViewById(R.id.btnCalculate);

        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();

        if (s1.equals("") || s2.equals("")) {
            b.setEnabled(false);
        } else {
            b.setEnabled(true);
        }
    }

    //  Creating a textWatcher
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };

    private void MyIntro() {
        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {

                    //  Launch app intro
                    Intent i = new Intent(MainActivity.this, MyIntro.class);
                    startActivity(i);

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();
    }
}


