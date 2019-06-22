package com.bahrcode.ship.mainshipview;

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

        // this is uncommented when we actually port everything
        //final Intent intent = new Intent(this, shipScreenActivity.class);

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
                                network.getShips().subscribe(shipList->{

                                        for(ShipInfo ship:shipList)
                                            ship.display();
                                        Toast.makeText(this, "Successful Login", Toast.LENGTH_SHORT).show();

                                        }, throwable -> {

                                        Toast.makeText(this, "Error on ship fetch", Toast.LENGTH_LONG).show();
                                        throwable.printStackTrace();
                                    });

                                }, Throwable::printStackTrace);

                }
        );
    }

}
