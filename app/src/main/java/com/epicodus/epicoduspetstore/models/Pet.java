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

    public Pet(JSONObject object) throws JSONException {
        try {
            mName = object.getString("name");
            Log.d("NAME TEST", mName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return mName;
    }
}
