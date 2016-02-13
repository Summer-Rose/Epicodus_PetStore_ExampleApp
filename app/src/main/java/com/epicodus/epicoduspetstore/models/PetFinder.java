package com.epicodus.epicoduspetstore.models;

import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by staff on 2/12/16.
 */
public class PetFinder {
    private Context context;

    public PetFinder(Context context) {
        this.context = context;
    }

    public void getPets(Callback callback) {
        String url = "http://api.petfinder.com/pet.find?format=json&key=3a62ece31719a64dcf6726980917d7ad&location=97217&count=15";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }


    public ArrayList<Pet> processResults(Response response) {
        ArrayList<Pet> mPets = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject petsJSON = new JSONObject(jsonData);

                JSONObject petfinderObject = petsJSON.getJSONObject("petfinder");
                JSONObject petsObject = petfinderObject.getJSONObject("pets");
                JSONArray petsJSONarray = petsObject.getJSONArray("pet");

                for (int i = 0; i < petsJSONarray.length(); i++) {
                    JSONObject singlePetObject = petsJSONarray.getJSONObject(i);
                    Pet newPet = new Pet(singlePetObject);
                    mPets.add(newPet);
                }
            } else {
                Log.e("ERROR", response.message());
            }
        } catch (IOException e) {
            Log.e("ERROR", "Exception caught: ", e);
            return mPets;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mPets;
    }
}



//one dog
//String url = "http://api.petfinder.com/pet.getRandom?format=json&key=543d99ef3df666cba9be3e29eb9b7b77&animal=dog&output=basic";

//just dogs
//String url = "http://api.petfinder.com/pet.find?format=json&key=3a62ece31719a64dcf6726980917d7ad&animal=dog&location=97217&count=10";

//10 of any animal by zip code