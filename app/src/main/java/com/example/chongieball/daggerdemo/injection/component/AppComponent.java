package com.example.chongieball.daggerdemo.injection.component;

import android.app.Application;

import com.example.chongieball.daggerdemo.App;
import com.example.chongieball.daggerdemo.feature.favorites.FavoriteModule;
import com.example.chongieball.daggerdemo.injection.module.ActivityBuilder;
import com.example.chongieball.daggerdemo.injection.module.AppModule;
import com.example.chongieball.daggerdemo.injection.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by chongieball on 15/02/18.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class,
        NetworkModule.class, ActivityBuilder.class, FavoriteModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder app(Application application);
        AppComponent build();
    }

    void inject(App app);
}
