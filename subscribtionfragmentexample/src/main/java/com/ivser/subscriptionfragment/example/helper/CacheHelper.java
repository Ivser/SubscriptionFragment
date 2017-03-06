package com.ivser.subscriptionfragment.example.helper;

import com.ivser.subscriptionfragment.example.model.ExampleResponse;

import rx.Observable;

/**
 * SubscriptionFragment
 * <p>
 * Created by SIvanov on 06.03.2017.
 */

public class CacheHelper {

    private static ExampleResponse cached;

    private static CacheHelper instance;

    public static CacheHelper getInstance() {
        if (instance == null) {
            instance = new CacheHelper();
        }
        return instance;
    }

    public Observable<Boolean> cacheExampleData(ExampleResponse response) {
        cached = response;
        return Observable.just(true);
    }

    public Observable<ExampleResponse> getCachedExampleData() {
        return Observable.just(cached);
    }
}
