package CIS3334.weightlossapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//Login page to allow users with accounts access, and those without to create an account with an email to
//access it. Using Firebase Auth Feature and allows user to click a textview to Register an account
//in another activity.
public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;

    //on start, check if user is signed in (non-null) and update UI accordingly.
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //when bundle is created, make UI and declare UI features and variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.registerNow);

        //when register now is clicked, take to Register.java page
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });
        //setup methods for UI
        setupEditText();
        setupButton();
        setupProgressBar();

        //when buttonLogin is pressed, check validity of Email and Password attempt
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hide progress bar
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                //if email is empty, display toast that prompts for an email
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Enter an email", Toast.LENGTH_SHORT).show();
                    return;
                }
                //if password is empty, display toast that prompts for a password
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //attempt to sign into the application with email and password parameters passed in
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                // Sign in success, update UI with the signed-in user's information
                                if (task.isSuccessful()) {
                                    Log.d("CIS3334", "createUserWithEmail:success");
                                    Toast.makeText(getApplicationContext(), "Login Success.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    // If sign in fails, display a message to the user.
                                } else {
                                    Log.w("CIS3334", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    //setup progress bar UI
    private void setupProgressBar() {
        progressBar = findViewById(R.id.progressBar);
    }
    //setup button UI
    private void setupButton() {
        buttonLogin = findViewById(R.id.btn_login);
    }
    //setup EditText fields for UI
    private void setupEditText() {
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
    }

}