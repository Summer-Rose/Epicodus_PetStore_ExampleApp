package com.epicodus.epicoduspetstore.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.epicoduspetstore.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;

import com.epicodus.epicoduspetstore.models.Pet;
import com.epicodus.epicoduspetstore.models.PetFinder;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;

public class PetsActivity extends AppCompatActivity {
    private ArrayList<Pet> mPets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);

        getPets();
    }

    private void getPets() {
        final PetFinder petFinder = new PetFinder(this);

        petFinder.getPets(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                mPets = petFinder.processResults(response);

                PetsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Pet pet : mPets) {
                            Log.d("Pet Name: ", pet.getName());
                            Log.d("Pet Age: ", pet.getAge());
                            Log.d("Pet Type: ", pet.getAnimal());
                        }
                    }
                });
            }

        });
    }
}
