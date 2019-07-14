package com.bahrcode.ship.mainshipview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.threetenabp.AndroidThreeTen;

import io.reactivex.disposables.Disposable;
import networking.LoginRequest;
import networking.Network;
import networking.NetworkManager;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class MainActivity extends AppCompatActivity {
    protected String input_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // correct login credentials:
        // email : shipstatus@bahrcode.com
        // pass : BA435B36A4E0
        final TextView emailText = findViewById(R.id.emailText);
        final TextView passwordText = findViewById(R.id.passwordText);

        Button loginButton = findViewById(R.id.loginButton);

        Button debugLoginButton = findViewById(R.id.autoLogin);


        debugLoginButton.setOnClickListener(
                view -> {
                    emailText.setText("shipstatus@bahrcode.com");
                    passwordText.setText("BA435B36A4E0");
                }
        );


        loginButton.setOnClickListener(
                view -> {

                    // initializing so the LocalDateTime class knows our local time.
                    AndroidThreeTen.init(this);

                    // nice and peasy
                    final Network network = NetworkManager.singleton.getNetwork();

                    Disposable d = network
                            .login(new LoginRequest(emailText.getText().toString(),passwordText.getText().toString()))
                            .observeOn(mainThread())
                            .subscribe(()->{

                                // go to our main ship view page
                                Intent intentToMainShipView = new Intent(view.getContext(), MainShipView.class);
                                MainActivity.this.startActivity(intentToMainShipView);
                                // This second lambda gets thrown if we didn't login right.
                                }, throwable -> {

                                Toast.makeText(this, "Invalid user credentials", Toast.LENGTH_LONG).show();
                                throwable.printStackTrace();
                            });


                }
        );
    }

    public String getInput_username() {
        return input_username;
    }

    public void setInput_username(String input_username) {
        this.input_username = input_username;
    }

}
