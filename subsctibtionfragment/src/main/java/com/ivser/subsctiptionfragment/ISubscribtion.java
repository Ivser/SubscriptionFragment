package com.ivser.subsctiptionfragment;

/**
 * Created by SIvanov on 16.10.2015.
 */
public interface ISubscribtion<T> {
    void onDataLoaded(T response);
    void onLoadingError(Throwable throwable);
}
