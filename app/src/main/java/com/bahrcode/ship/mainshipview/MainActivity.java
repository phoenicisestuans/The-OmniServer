package com.bahrcode.ship.mainshipview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    protected String input_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public String getInput_username() {
        return input_username;
    }

    public void setInput_username(String input_username) {
        this.input_username = input_username;
    }
}
