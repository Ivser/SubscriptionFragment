package com.ivser.subscriptionfragment.example.fragment;

import android.widget.TextView;

import com.ivser.subscriptionfragment.example.R;
import com.ivser.subscriptionfragment.example.helper.ApiHelper;
import com.ivser.subscriptionfragment.example.helper.CacheHelper;
import com.ivser.subscriptionfragment.example.model.ExampleResponse;
import com.ivser.subsctiptionfragment.BaseCachedSubscriptionFragment;

import rx.Observable;

/**
 * SubscriptionFragment
 * <p>
 * Created by SIvanov on 06.03.2017.
 */

public class CachedSubscriptionFragment extends BaseCachedSubscriptionFragment<ExampleResponse> {

    private String responseString;

    public CachedSubscriptionFragment() {
        super(R.layout.subscription_fragment_example);
    }

    @Override
    protected Observable getLoadDataObservable() {
        return ApiHelper.getInstance().loadExampleData2();
    }

    @Override
    protected void processData(ExampleResponse response) {
        this.responseString = response.getResponseString();
    }

    @Override
    protected Observable<ExampleResponse> getLoadCachedDataObservable() {
        return CacheHelper.getInstance().getCachedExampleData();
    }

    @Override
    protected Observable<Boolean> getCacheDataObservable(ExampleResponse response) {
        return CacheHelper.getInstance().cacheExampleData(response);
    }

    @Override
    protected void buildUI() {
        ((TextView)getView().findViewById(R.id.text)).setText(responseString);
    }

}
