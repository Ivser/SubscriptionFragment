package com.ivser.subscriptionfragment.example.helper;

import com.ivser.subscriptionfragment.example.model.ExampleResponse;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * SubscriptionFragment
 * <p>
 * Created by SIvanov on 06.03.2017.
 */

public class ApiHelper {

    private static ApiHelper instance;

    public static ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }

    public Observable<ExampleResponse> loadExampleData() {
        return Observable.just(new ExampleResponse("This is data for the first fragment"))
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ExampleResponse> loadExampleData2() {
        return Observable.just(new ExampleResponse("This is data for the second fragment"))
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
