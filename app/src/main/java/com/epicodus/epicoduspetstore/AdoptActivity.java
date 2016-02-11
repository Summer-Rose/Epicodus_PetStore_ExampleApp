package com.epicodus.epicoduspetstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdoptActivity extends AppCompatActivity {
    public static final String TAG = AdoptActivity.class.getSimpleName();
    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPhoneEditText;
    private EditText mPetNameEditText;
    private EditText mQuestionsEditText;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt);

        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mPhoneEditText = (EditText) findViewById(R.id.phoneEditText);
        mPetNameEditText = (EditText) findViewById(R.id.petNameEditText);
        mQuestionsEditText = (EditText) findViewById(R.id.questionsEditText);
        mSubmitButton = (Button) findViewById(R.id.submitButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();
                String email = mEmailEditText.getText().toString();
                String phone = mPhoneEditText.getText().toString();
                String petName = mPetNameEditText.getText().toString();
                String question = mQuestionsEditText.getText().toString();
                Log.d(TAG, name + " " + email + " " + phone + " " + petName + " " + question);
                Intent intent = new Intent(AdoptActivity.this, ConfirmationActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("phone", email);
                intent.putExtra("petName", petName);
                intent.putExtra("question", question);
                startActivity(intent);
            }
        });
    }
}
