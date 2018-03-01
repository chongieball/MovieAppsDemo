package com.example.chongieball.daggerdemo.feature.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by chongieball on 19/02/18.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void toFragment(@IdRes int viewId, Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(viewId, fragment)
                .commit();
    }
}
