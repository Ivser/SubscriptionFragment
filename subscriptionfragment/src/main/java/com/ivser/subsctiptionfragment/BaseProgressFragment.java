package com.ivser.subsctiptionfragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * SubscriptionFragment
 * <p>
 * Created by SIvanov on 06.03.2017.
 */

public abstract class BaseProgressFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected View contentView;
    protected View progressView;
    protected SwipeRefreshLayout errorView;
    protected TextView errorText;

    public BaseProgressFragment(int layoutId) {
        super(layoutId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.subscription_fragment, null);
            contentView = inflater.inflate(layoutId, null);
            contentView.setId(R.id.content);
            ((FrameLayout)view).addView(contentView);
        }
        progressView = view.findViewById(R.id.progress);
        errorView = (SwipeRefreshLayout)view.findViewById(R.id.error);
        errorText = (TextView)view.findViewById(R.id.error_text);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (errorView != null) {
            errorView.setOnRefreshListener(this);
            errorView.setColorSchemeColors(ContextCompat.getColor(getActivity(), android.R.color.black), ContextCompat.getColor(getActivity(), android.R.color.holo_blue_dark));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        contentView = null;
    }

    @Override
    public void onRefresh() {
        errorView.setRefreshing(true);
    }

    public void setEmptyText(int resId) {
        errorText.setText(resId);
    }

    protected void showContent() {
        contentView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
        progressView.setVisibility(View.GONE);
    }

    protected void showError() {
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorText.setVisibility(View.VISIBLE);
        progressView.setVisibility(View.GONE);
    }

    public void showProgress() {
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
        progressView.setVisibility(View.VISIBLE);
    }

    public void onDataRefreshed() {
        errorView.setRefreshing(false);
    }

}
