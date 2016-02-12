package com.epicodus.epicoduspetstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mViewPetsButton;
    private Button mAdoptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPetsButton = (Button) findViewById(R.id.viewPetsButton);
        mAdoptButton = (Button) findViewById(R.id.adoptButton);

        mViewPetsButton.setOnClickListener(this);
        mAdoptButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mViewPetsButton) {
            Intent intent = new Intent(MainActivity.this, PetsActivity.class);
            startActivity(intent);
        }

        if (v == mAdoptButton) {
            Intent intent = new Intent(MainActivity.this, AdoptActivity.class);
            startActivity(intent);
        }
    }
}

//    @Override
//    public void onClick(View v) {
//        if (v == mViewPetsButton) {
//            Log.d(TAG, "mViewPetsButton has been clicked");
//            Intent intent = new Intent(MainActivity.this, PetsActivity.class);
//            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //if already exsists will use that one instead
//            //intent.addFlags(Intent.)
//            //NavUtils.navigateUpFromSameTask(this);
//
//            startActivity(intent);
//        }
//
//
//        if (v == mAdoptButton) {
//            Intent intent = new Intent(MainActivity.this, AdoptActivity.class);
//            startActivity(intent);
//        }
//    }