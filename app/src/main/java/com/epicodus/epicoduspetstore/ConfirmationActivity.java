package com.epicodus.epicoduspetstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConfirmationActivity extends AppCompatActivity {
    @Bind(R.id.nameTextView) TextView mNameTextView;
    @Bind(R.id.phoneTextView) TextView mPhoneTextView;
    @Bind(R.id.emailTextView) TextView mEmailTextView;
    @Bind(R.id.petNameTextView) TextView mPetNameTextView;
    @Bind(R.id.questionTextView) TextView mQuestionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        String petName = intent.getStringExtra("petName");
        String question = intent.getStringExtra("question");

        mNameTextView.setText(name);
        mPhoneTextView.setText(phone);
        mEmailTextView.setText(email);
        mPetNameTextView.setText("Thank you for your interest in " + petName);
        mQuestionTextView.setText(question);
    }
}


//
//private ValueEventListener mEventListener;
//private String mName;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_confirmation);
//
//        Intent intent = getIntent();
//        mName = intent.getStringExtra("name");
//
//        mConfirmTextView = (TextView) findViewById(R.id.petNameTextView);
//
//        getConfirmationMessage(mName);
//    }
//
//    public void getConfirmationMessage(String name) {
//
//        mEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    //if POJO
//                    //ExampleObject object = dataSnapshot.getValue(ExampleObject.class);
//                    String email = dataSnapshot.child("email").getValue(String.class);
//                    String phone = dataSnapshot.child("phone").getValue(String.class);
//                    String petName = dataSnapshot.child("petName").getValue(String.class);
//
//                    mConfirmTextView.setText(email + " " + phone + " " + petName);
//                    //mConfirmTextView.setText(object.getName());
//                }
//                //else Toast to say it didn't go through
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                //toast
//            }
//        };
//
//        EpicodusPetStoreApplication.getAppInstance()
//                .getFirebaseRef()
//                .child("adoptionRequests/" + name)
//                .addListenerForSingleValueEvent(mEventListener);
//
//    }
//
//    //if pause out of activity and enter back in, we need to recreate
//    @Override
//    public void onResume() {
//        super.onResume();
//        getConfirmationMessage(mName);
//    }
//
//    //stuff that need to be seen in real time as they update this will be needed
//    @Override
//    public void onPause() {
//        super.onPause();
//        EpicodusPetStoreApplication.getAppInstance().getFirebaseRef().child("adoptionRequests/" + mName).removeEventListener(mEventListener);
//    }