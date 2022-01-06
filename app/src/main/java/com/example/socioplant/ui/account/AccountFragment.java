package com.example.socioplant.ui.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.socioplant.HomeActivity;
import com.example.socioplant.LoginActivity;
import com.example.socioplant.MainActivity2;
import com.example.socioplant.R;
import com.example.socioplant.RegisterActivity;
import com.example.socioplant.databinding.FragmentAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {

    private static final String TAG = "AccountFragment";

    private AccountViewModel accountViewModel;
    private FragmentAccountBinding binding;
    private CircleImageView profile;
    private TextView textName, textEmail;
    private Button btnChangePassword, btnLogout;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        profile = (CircleImageView) view.findViewById(R.id.profileImage);
        textName = (TextView) view.findViewById(R.id.textViewName);
        textEmail = (TextView) view.findViewById(R.id.textViewEmail);
        btnLogout = (Button) view.findViewById(R.id.buttonLogout);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

//            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                    .setDisplayName("Mochamad Nurul Huda")
//                    .setPhotoUri(Uri.parse("https://images.pexels.com/photos/3911368/pexels-photo-3911368.jpeg"))
//                    .build();
//
//            user.updateProfile(profileUpdates)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Log.d(TAG, "User profile updated.");
//                            }
//                        }
//                    });

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();
            profile.setImageURI(photoUrl);
            textName.setText(name);
            textEmail.setText(email);

        } else {
            // No user is signed in
            Intent moveIntent = new Intent(getActivity(), LoginActivity.class);
            startActivity(moveIntent);
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent moveIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(moveIntent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}