package com.epicodus.epicoduspetstore.service;

import android.content.Context;
import android.util.Log;

import com.epicodus.epicoduspetstore.R;
import com.epicodus.epicoduspetstore.models.Pet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;


/**
 * Created by staff on 2/12/16.
 */
public class PetfinderService {
    private Context context;

    public PetfinderService(Context context) {
        this.context = context;
    }

    public void getFood(String zip, Callback callback) throws OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        String CONSUMER_KEY = context.getString(R.string.consumer_key);
        String CONSUMER_SECRET = context.getString(R.string.consumer_secret);
        String TOKEN = context.getString(R.string.token);
        String TOKEN_SECRET = context.getString(R.string.token_secret);
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.yelp.com/v2/search?term=food").newBuilder();
        urlBuilder.addQueryParameter("location", zip);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Request signedRequest = (Request) consumer.sign(request).unwrap();

        Call call = client.newCall(signedRequest);
        call.enqueue(callback);
    }

    public ArrayList<Pet> processResults(Response response) {
        ArrayList<Pet> pets = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject yelpJSON = new JSONObject(jsonData);
                JSONArray businesses = yelpJSON.getJSONArray("businesses");

                for (int i = 0; i < businesses.length(); i++) {
                    JSONObject restaurant = businesses.getJSONObject(i);
                    Pet pet = new Pet(restaurant);
                    pets.add(pet);
                }
            }

            Log.d("JSON DATA ", jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pets;
    }
}


/*
JSONArray businesses = (JSONArray) response.get("businesses");
JSONObject firstBusiness = (JSONObject) businesses.get(0);
String firstBusinessID = firstBusiness.get("id").toString();
System.out.println(String.format(
        "%s businesses found, querying business info for the top result \"%s\" ...",
        businesses.size(), firstBusinessID));*/
