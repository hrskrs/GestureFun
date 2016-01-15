package com.hrskrs.gesturefun.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by hrskrs
 */
public abstract class BaseFragment extends Fragment{

    @LayoutRes
    protected abstract int getContentLayoutResId();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(getContentLayoutResId(), container, false);
        ButterKnife.bind(this, rootView);
        populateUI(inflater, rootView);
        return rootView;
    }

    protected abstract void populateUI(LayoutInflater inflater, View rooView);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
