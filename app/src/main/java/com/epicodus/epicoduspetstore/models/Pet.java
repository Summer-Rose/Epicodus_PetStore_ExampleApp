package com.epicodus.epicoduspetstore.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by staff on 2/12/16.
 */
public class Pet {
    private String mName;
    private String mAnimal;
    private String mAge;
    private String mSex;
    private String mDescription;
    private ArrayList<String> mImageURLs;
    private ArrayList<String> mBreeds;

    public Pet(JSONObject petObject) throws JSONException {
        try {
            mName = formatString(petObject.getString("name"));
            mAnimal = formatString(petObject.getString("animal"));
            mAge = formatString(petObject.getString("age"));
            mSex = formatString(petObject.getString("sex"));
            mDescription = formatString(petObject.getString("description"));

            JSONObject breedsJSON = petObject.getJSONObject("breeds");
            JSONArray breedArrayJSON = breedsJSON.getJSONArray("breed");
            for (int i = 0; i < breedArrayJSON.length(); i++) {
                String breed = breedArrayJSON.getJSONObject(i).getString("$t");
                mBreeds.add(breed);
            }

            JSONObject mediaObject = petObject.getJSONObject("media");
            JSONObject photosObject = mediaObject.getJSONObject("photos");
            JSONArray photoArrayJSON = photosObject.getJSONArray("photo");

            for (int i = 0; i < photoArrayJSON.length(); i++) {
                String url = photoArrayJSON.getJSONObject(i).getString("$t");
                mImageURLs.add(url);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return mName;
    }

    public String getAnimal() {
        return mAnimal;
    }

    public String getAge() {
        return mAge;
    }

    public String getSex() {
        return mSex;
    }

    public String getDescription() {
        return mDescription;
    }

    public ArrayList<String> getImageURLs() {
        return mImageURLs;
    }

    public ArrayList<String> getBreeds() {
        return mBreeds;
    }

    public String formatString(String phrase) {
        String newPhrase = phrase.substring(7, phrase.length() -2);
        return newPhrase;
    }
}
