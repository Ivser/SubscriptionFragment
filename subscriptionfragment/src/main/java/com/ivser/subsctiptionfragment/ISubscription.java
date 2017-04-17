package com.ivser.subsctiptionfragment;

/**
 * Created by SIvanov on 16.10.2015.
 */
public interface ISubscription<T> {
    void onDataLoaded(T response);
    void onLoadingError(Throwable throwable);
}
