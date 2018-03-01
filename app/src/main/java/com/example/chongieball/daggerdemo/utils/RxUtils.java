package com.example.chongieball.daggerdemo.utils;

import io.reactivex.disposables.Disposable;

/**
 * Created by chongieball on 19/02/18.
 */

public class RxUtils {

    public static void unsubscribe(Disposable subcription) {
        if (subcription != null && !subcription.isDisposed()) subcription.dispose();
    }

    public static void unsubscribe(Disposable... subscriptions) {
        for (Disposable subscription : subscriptions) {
            unsubscribe(subscription);
        }
    }
}
