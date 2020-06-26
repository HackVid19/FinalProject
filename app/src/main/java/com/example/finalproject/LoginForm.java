package com.example.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginForm extends AppCompatActivity {

    EditText txt_email,txt_password;
    Button btnLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login");

        txt_email=(EditText)findViewById(R.id.txtEmail);
        txt_password=(EditText)findViewById(R.id.txtPassword);
        btnLogin=(Button)findViewById(R.id.btn_login);

        firebaseAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txt_email.getText().toString().trim();
                String password=txt_password.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(LoginForm.this,"Please Enter Email",Toast.LENGTH_SHORT);
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginForm.this,"Please Enter Password",Toast.LENGTH_SHORT);
                    return;
                }

                if(password.length()<6)
                {
                    Toast.makeText(LoginForm.this,"Password too short",Toast.LENGTH_SHORT);
                }



                firebaseAuth.signInWithEmailAndPassword(email, password)

                        .addOnCompleteListener(LoginForm.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent( getApplicationContext(),MainActivity.class));

                                } else {
                                    Toast.makeText(LoginForm.this,"Login Failed",Toast.LENGTH_SHORT);

                                }

                                // ...
                            }
                        });



            }
        });





    }
    public void btn_signup(View view){
        startActivity(new Intent(getApplicationContext(),SignUpForm.class));

    }
}
