package com.ivser.subsctiptionfragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;

/**
 * SubscriptionFragment
 * <p>
 * Created by SIvanov on 06.03.2017.
 */

abstract public class BaseSubscriptionFragment<T> extends BaseProgressFragment implements ISubscription<T> {

    private static final String TAG = BaseSubscriptionFragment.class.getName();
    protected List<Subscription> subscriptions = new ArrayList<>();
    protected boolean dataLoaded;

    public BaseSubscriptionFragment(int layoutId) {
        super(layoutId);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isDataLoaded()) {
            showContent();
            return;
        }
        restoreData();
    }

    @Override
    public void onPause() {
        super.onPause();
        for(Subscription subscription: subscriptions) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        showProgress();
        loadData();
    }

    @Override
    public void onDataLoaded(T response) {
        dataLoaded = true;
        onDataRefreshed();
        showContent();
        processData(response);
        buildUI();
    }

    @Override
    public void onLoadingError(Throwable throwable) {
        onDataRefreshed();
        showError();
    }

    protected abstract Observable<T> getLoadDataObservable();
    protected abstract void processData(T response);
    protected abstract void buildUI();

    protected void loadData() {
        Observable<T> observable = getLoadDataObservable();
        if(observable == null) {
            onLoadingError(new Exception("LoadDataObservable does not provided"));
            return;
        }
        subscriptions.add(observable
                .subscribe(this::onDataLoaded, this::onLoadingError));
    }

    protected void restoreData() {
        showProgress();
        loadData();
    }

    protected boolean isDataLoaded() {
        return dataLoaded;
    }
}
