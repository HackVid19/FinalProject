package com.example.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpForm extends AppCompatActivity {

    public EditText txt_fullName,txt_username,txt_email,txt_password;
    Button btn_register;
    RadioButton radioGenderMale,radioGenderFemale;
    DatabaseReference databaseReference;
    String gender="";
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        txt_fullName=(EditText) findViewById(R.id.txt_full_name);
        txt_username=(EditText) findViewById(R.id.txt_username);
        txt_email=(EditText) findViewById(R.id.txt_email);
        txt_password=(EditText) findViewById(R.id.txt_password);
        btn_register=(Button) findViewById(R.id.btn_register);
        radioGenderMale=(RadioButton) findViewById(R.id.radio_male);
        radioGenderFemale=(RadioButton) findViewById(R.id.radio_female);

        databaseReference= FirebaseDatabase.getInstance().getReference("user");
        firebaseAuth=FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullName=txt_fullName.getText().toString();
                final String userName=txt_username.getText().toString();
                final String email=txt_email.getText().toString();
                String password=txt_password.getText().toString();

                if( radioGenderMale.isChecked()){
                    gender="Male";

                }
                if(radioGenderFemale.isChecked()){
                    gender="Female";
                }

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(SignUpForm.this,"Please Enter Email",Toast.LENGTH_SHORT);
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(SignUpForm.this,"Please Enter Password",Toast.LENGTH_SHORT);
                    return;
                }
                if(TextUtils.isEmpty(fullName))
                {
                    Toast.makeText(SignUpForm.this,"Please Enter Full Name",Toast.LENGTH_SHORT);
                    return;
                }
                if(TextUtils.isEmpty(userName))
                {
                    Toast.makeText(SignUpForm.this,"Please Enter User Name",Toast.LENGTH_SHORT);
                    return;
                }

                if(password.length()<6)
                {
                    Toast.makeText(SignUpForm.this,"Password too short",Toast.LENGTH_SHORT);
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpForm.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    User information = new User(
                                            fullName,
                                            userName,
                                            email,
                                            gender
                                    );


                                    FirebaseDatabase.getInstance().getReference("user")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(SignUpForm.this,"Registration Complete",Toast.LENGTH_SHORT);
                                            startActivity(new Intent(getApplicationContext(),LoginForm.class));


                                        }
                                    });

                                } else {

                                }

                                // ...
                            }
                        });
            }
        });

    }
}

