package com.hrskrs.gesturefun;

import android.view.LayoutInflater;
import android.view.View;

import com.hrskrs.gesturefun.core.BaseFragment;

/**
 * Created by hrskrs
 */
public class MainFragment extends BaseFragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getContentLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void populateUI(LayoutInflater inflater, View rooView) {

    }
}
