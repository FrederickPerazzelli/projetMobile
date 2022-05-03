package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText date;
    private EditText password;
    private EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.editEmail);
        date = findViewById(R.id.editBirthdate);
        password = findViewById(R.id.editPassword);
        confirmPassword = findViewById(R.id.editPasswordConfirm);

        Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pass1 = password.getText().toString();
                String pass2 = confirmPassword.getText().toString();
                String emailText = email.getText().toString();
                String dateText = date.getText().toString();

                if (pass1.equals("") || pass2.equals("") || emailText.equals("") || dateText.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
                else if (!pass1.matches(pass2)) {
                    Toast.makeText(RegisterActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User();
                    user.setEmail(emailText);
                    user.setBirthdate(dateText);
                    user.setPassword(pass1);

                    /*Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);*/
                }
            }
        });

        TextView gotoLogin = findViewById(R.id.gotoLogin);
        /*gotoLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/
    }


}