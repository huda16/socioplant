package com.example.socioplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    private TextView textView;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent moveIntent = new Intent(MainActivity2.this, HomeActivity.class);
            startActivity(moveIntent);
        }

        textView = (TextView) findViewById(R.id.textView);
        btnRegister = (Button) findViewById(R.id.button_signup);
        btnLogin = (Button) findViewById(R.id.button_signin);
        String text = "<font color=#000000>Let's make the world</font> <font color=#519259>green</font>";
        textView.setText(Html.fromHtml(text));

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(MainActivity2.this, RegisterActivity.class);
                startActivity(moveIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(MainActivity2.this, LoginActivity.class);
                startActivity(moveIntent);
            }
        });
    }
}