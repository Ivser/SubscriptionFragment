package com.ivser.subsctiptionfragment;

import rx.Observable;

/**
 * SubscriptionFragment
 * <p>
 * Created by SIvanov on 06.03.2017.
 */

public abstract class BaseCachedSubscriptionFragment<T> extends BaseSubscriptionFragment<T> implements ICachedSubscription<T> {

    private static final String TAG = BaseCachedSubscriptionFragment.class.getName();

    public BaseCachedSubscriptionFragment(int layoutId) {
        super(layoutId);
    }

    @Override
    public void onDataLoaded(T response) {
        dataLoaded = true;
        super.onDataRefreshed();
        cacheData(response);
        showContent();
        processData(response);
        buildUI();
    }

    @Override
    public void onCachedDataLoaded(T response) {
        dataLoaded = true;
        if(response == null) {
            loadData();
            return;
        }
        super.onDataRefreshed();
        showContent();
        processData(response);
        buildUI();
    }

    @Override
    public void onLoadingCachedDataError(Throwable t) {
        loadData();
    }

    protected void loadCachedData() {
        Observable<T> observable = getLoadCachedDataObservable();
        if(observable == null) {
            onLoadingCachedDataError(new Exception("getLoadCachedDataObservable does not provided"));
            return;
        }
        subscriptions.add(observable
                .subscribe(this::onCachedDataLoaded, this::onLoadingCachedDataError));
    }

    protected void cacheData(T response) {
        Observable<Boolean> observable = getCacheDataObservable(response);
        if(observable == null) {
            onCachingError(new Exception("getCacheDataObservable not set"));
            return;
        }
        subscriptions.add(observable
                .subscribe(this::onDataCached, this::onCachingError));
    }

    @Override
    public void onDataCached(Boolean success) {
        // stub
    }

    @Override
    public void onCachingError(Throwable throwable) {
        // stub
    }

    protected void restoreData() {
        showProgress();
        loadCachedData();
    }

    protected abstract Observable<T> getLoadCachedDataObservable();
    protected abstract Observable<Boolean> getCacheDataObservable(T response);
}
