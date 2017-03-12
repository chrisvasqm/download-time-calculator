package com.christianv07.dev.speedy.aboutActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.christianv07.dev.speedy.MainActivity;
import com.christianv07.dev.speedy.R;

public class AboutActivity extends AppCompatActivity {

    // Shows back button and it's behaviour
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        String versionName = " ";
        TextView appVersion = (TextView) findViewById(R.id.txtAppVersion);

        try {
            versionName = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        appVersion.setText(getString(R.string.menuAboutVersion) + " " + versionName);

        Button btnChangelog = (Button) findViewById(R.id.btnAbout);

        btnChangelog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnPress();
            }
        });

    }

    public void onBtnPress() {
        new MaterialDialog.Builder(this)
                .title(R.string.menuAboutBtn)
                .content(R.string.appchangelogs)
                .positiveText(R.string.sBtnAlert)
                .show();
    }
}
