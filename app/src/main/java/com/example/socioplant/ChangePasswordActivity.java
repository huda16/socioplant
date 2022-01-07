package com.example.socioplant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final String TAG = "ChangePasswordActivity";
    private EditText mPassword, mConfirmPassword;
    private Button btnChangePassword;
    private ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_change_password);

        mPassword = (EditText) findViewById(R.id.editTextPassword);
        mConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        btnBack = (ImageButton) findViewById(R.id.backButton);
        btnChangePassword = (Button) findViewById(R.id.buttonChangePassword);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = mPassword.getText().toString();
                String confirm = mConfirmPassword.getText().toString();

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
                updatePassword(password);
            }
        });
    }

    public void updatePassword(String password) {
        // [START update_password]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User password updated.");
                        }
                    }
                });
        // [END update_password]
    }
}