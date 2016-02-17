package com.epicodus.epicoduspetstore.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.epicoduspetstore.R;

import com.epicodus.epicoduspetstore.models.Pet;
import com.epicodus.epicoduspetstore.service.PetfinderService;


import java.io.IOException;
import java.util.ArrayList;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class PetsActivity extends AppCompatActivity {
    private ArrayList<Pet> mPets = new ArrayList<>();
    private String mZip = "97204";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);


        try {
            showPets();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        }
    }

    private void showPets() throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        final PetfinderService petFinder = new PetfinderService(this);

        petFinder.getFood(mZip, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mPets = petFinder.processResults(response);

                PetsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("HI", "HELLO");
                        for (Pet pet : mPets) {
                            Log.d("Food Name:", pet.getName() + pet.getName());
                        }
                    }
                });
            }
        });


    }
}