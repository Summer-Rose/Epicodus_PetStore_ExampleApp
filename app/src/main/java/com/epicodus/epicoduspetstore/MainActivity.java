package com.epicodus.epicoduspetstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mViewPetsButton;
    private Button mAdoptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPetsButton = (Button) findViewById(R.id.viewPetsButton);
        mAdoptButton = (Button) findViewById(R.id.adoptButton);

        mViewPetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "mViewPetsButton has been clicked");
                Intent intent = new Intent(MainActivity.this, PetsActivity.class);
                startActivity(intent);
            }
        });

        mAdoptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdoptActivity.class);
                startActivity(intent);
            }
        });
    }
}