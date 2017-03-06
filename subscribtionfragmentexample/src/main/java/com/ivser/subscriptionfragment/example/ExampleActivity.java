package com.ivser.subscriptionfragment.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ivser.subscriptionfragment.example.fragment.CachedSubscriptionFragment;
import com.ivser.subscriptionfragment.example.fragment.SubscriptionFragment;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.button1) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, new SubscriptionFragment())
                    .commit();
        }

        if (v.getId() == R.id.button2) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, new CachedSubscriptionFragment())
                    .commit();
        }
    }
}
