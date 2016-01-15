package com.hrskrs.gesturefun;

import android.os.Bundle;

import com.hrskrs.gesturefun.core.BaseActivity;

/**
 * Created by hrskrs
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            addFragment(MainFragment.newInstance());
        }
    }
}
