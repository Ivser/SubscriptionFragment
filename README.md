# SubscriptionFragment

SubscriptionFragment provides easy way to load and display data via observables

## How does it works

There are two base objects 
- SubscriptionFragment loads data and display it;
- CachedSubscriptionFragment loads and cache data the first time, then it can take data from cache.

## How to use

You should extend SubscriptionFragment or CachedSubscriptionFragment and provide requested Observables:

```java
public class CachedSubscriptionFragment extends BaseCachedSubscriptionFragment<T> {

    public CachedSubscriptionFragment() {
		// set up your layout
        super(R.layout.your_fragment_layout);
    }

    @Override
    protected Observable getLoadDataObservable() {
		// Retrofit can be used here
        return ApiHelper.getInstance().loadData();
    }

    @Override
    protected Observable<ExampleResponse> getLoadCachedDataObservable() {
		// write to cache or sqlite can be used here
        return CacheHelper.getInstance().getCachedData();
    }

    @Override
    protected Observable<Boolean> getCacheDataObservable(ExampleResponse response) {
		// read from cache or sqlite can be used here
        return CacheHelper.getInstance().cacheExampleData(response);
    }

	@Override
    protected void processData(T response) {
        // process data
    }
	
    @Override
    protected void buildUI() {
        // init your ui with data
    }
}
```
