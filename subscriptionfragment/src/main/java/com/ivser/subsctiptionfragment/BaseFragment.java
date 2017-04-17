package com.ivser.subsctiptionfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * SubscriptionFragment
 * <p>
 * Created by SIvanov on 06.03.2017.
 */

public abstract class BaseFragment extends Fragment {

    final protected int layoutId;
    protected View view;

    public BaseFragment(int layoutId) {
        super();
        this.layoutId = layoutId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(layoutId, null);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}

