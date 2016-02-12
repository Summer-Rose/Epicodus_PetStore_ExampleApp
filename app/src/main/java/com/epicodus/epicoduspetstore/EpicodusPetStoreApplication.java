package com.epicodus.epicoduspetstore;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by staff on 2/11/16.
 */
public class EpicodusPetStoreApplication extends Application {
    private static EpicodusPetStoreApplication app; //can easily get context because it is static (lives throughout the lifecycle of the app. Never garbage collected)
    private Firebase mFirebaseRef;

    public static EpicodusPetStoreApplication getAppInstance() {
        return app;
    }

    public Firebase getFirebaseRef() {
        return mFirebaseRef;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://epicodus-pet-store.firebaseio.com/");

        /*anything that needs to exist throughout the life of the app
        or a library that needs to be initialized can go here.

        Also great place to put ValueEvent or ChildEvent Listeners
        that should not get garbage collected
        */
    }
}
