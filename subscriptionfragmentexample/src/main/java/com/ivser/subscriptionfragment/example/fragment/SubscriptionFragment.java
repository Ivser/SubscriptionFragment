package com.ivser.subscriptionfragment.example.fragment;

import android.widget.TextView;

import com.ivser.subscriptionfragment.example.R;
import com.ivser.subscriptionfragment.example.helper.ApiHelper;
import com.ivser.subscriptionfragment.example.model.ExampleResponse;
import com.ivser.subsctiptionfragment.BaseSubscriptionFragment;

import rx.Observable;

/**
 * SubscriptionFragment
 * <p>
 * Created by SIvanov on 06.03.2017.
 */

public class SubscriptionFragment extends BaseSubscriptionFragment<ExampleResponse> {

    private String responseString;

    public SubscriptionFragment() {
        super(R.layout.subscription_fragment_example);
    }

    @Override
    protected Observable getLoadDataObservable() {
        return ApiHelper.getInstance().loadExampleData();
    }

    @Override
    protected void processData(ExampleResponse response) {
        this.responseString = response.getResponseString();
    }

    @Override
    protected void buildUI() {
        ((TextView)getView().findViewById(R.id.text)).setText(responseString);
    }
}
