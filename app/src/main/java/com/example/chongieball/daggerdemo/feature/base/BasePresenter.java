package com.example.chongieball.daggerdemo.feature.base;

/**
 * Created by chongieball on 19/02/18.
 */

public class BasePresenter<V extends BaseView> implements Presenter<V> {

    V view;

    @Override
    public void setView(V view) {
        this.view = view;
    }

    @Override
    public V getView() {
        return view;
    }

    @Override
    public void destroyView() {
        view = null;
    }
}
