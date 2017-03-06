package com.ivser.subsctiptionfragment;

/**
 * Created by SIvanov on 16.10.2015.
 */
public interface ICachedSubscription<T> {
    void onCachedDataLoaded(T response);
    void onLoadingCachedDataError(Throwable throwable);
    void onDataCached(Boolean success);
    void onCachingError(Throwable throwable);
}
