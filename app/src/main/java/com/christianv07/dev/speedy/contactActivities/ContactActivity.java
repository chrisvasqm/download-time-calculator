package com.christianv07.dev.speedy.contactActivities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.christianv07.dev.speedy.MainActivity;
import com.christianv07.dev.speedy.R;


public class ContactActivity extends AppCompatActivity {

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
        // Makes sure the screen cant be in landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        String[] cardsName = {
                getString(R.string.nameChris),
                getString(R.string.nameDavid),
                getString(R.string.nameAle)
        };
        Integer[] cardsImg = {
                R.drawable.profpicforcontact2,
                R.drawable.profpicforcontact,
                R.drawable.profpicforcontact3
        };

        ListAdapter cardsAdapter = new CustomAdapter(this, cardsName, cardsImg);
        ListView cardsListView = (ListView) findViewById(R.id.listView);
        cardsListView.setAdapter(cardsAdapter);

        cardsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        String cards = String.valueOf(adapterView.getItemAtPosition(position));
//                        Toast.makeText(ContactActivity.this, cards, Toast.LENGTH_SHORT).show();

                        switch (cards) {
                            case "Christian Vásquez":
                                startActivity(new Intent(getApplicationContext(), ProfChrisActivity.class));
                                break;
                            case "David Peña":
                                startActivity(new Intent(getApplicationContext(), ProfDavidActivity.class));
                                break;
                            case "Alejandro Martinez":
                                startActivity(new Intent(getApplicationContext(), ProfAleActivity.class));
                                break;
                        }
                    }
                }
        );
    }

}
