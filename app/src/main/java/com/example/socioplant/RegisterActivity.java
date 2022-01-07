package com.example.socioplant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private FirebaseAuth mAuth;

    private TextView textView2;
    private ImageButton backButton;
    private EditText mEmail, mPassword, mConfirmPassword;
    private Button btnRegister, btnRegisterGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent moveIntent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(moveIntent);
        }

        textView2 = (TextView) findViewById(R.id.textView2);
        String text = "<font color=#000000>love your</font> <font color=#519259>plant</font> <font color=#000000>with us</font>";
        textView2.setText(Html.fromHtml(text));

        backButton = (ImageButton) findViewById(R.id.backButton);
        mEmail = (EditText) findViewById(R.id.editTextEmail);
        mPassword = (EditText) findViewById(R.id.editTextPassword);
        mConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        btnRegister = (Button) findViewById(R.id.buttonRegister);

        mAuth = FirebaseAuth.getInstance();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(RegisterActivity.this, MainActivity2.class);
                startActivity(moveIntent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String confirm = mConfirmPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Alamat surel wajib diisi");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Kata sandi wajib diisi");
                    return;
                }

                if (TextUtils.isEmpty(confirm)) {
                    mConfirmPassword.setError("Kata sandi wajib diisi");
                    return;
                }

                if (!password.equals(confirm)) {
                    mConfirmPassword.setError("Kata sandi tidak sama");
                    return;
                }
                createAccount(email, password);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                            Intent moveIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(moveIntent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }

    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }
}