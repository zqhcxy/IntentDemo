package com.github.zqhcxy.intentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button intent_implicit;
    private Button intent_explicit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent_implicit = (Button) findViewById(R.id.intent_implicit);
        intent_explicit = (Button) findViewById(R.id.intent_explicit);

        intent_implicit.setOnClickListener(this);
        intent_explicit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.intent_implicit:
                Intent intent = new Intent(MainActivity.this, ImplicitIntentActivity.class);
                startActivity(intent);
                break;
            case R.id.intent_explicit:

                break;

        }
    }
}
