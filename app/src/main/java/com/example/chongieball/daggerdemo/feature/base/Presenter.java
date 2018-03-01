package com.example.chongieball.daggerdemo.feature.base;

/**
 * Created by chongieball on 19/02/18.
 */

public interface Presenter<V extends BaseView> {

    void setView(V view);

    V getView();

    void destroyView();
}
