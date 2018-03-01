package com.example.chongieball.daggerdemo;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chongieball on 01/03/18.
 *
 * JUnit Test Rule with RxJava and Android schedulers for unit test
 */

public class RxSchedulerRule implements TestRule {

    private Scheduler trampoline = Schedulers.trampoline();

    private Function<Scheduler, Scheduler> schedulerFunction = scheduler ->
            trampoline;

    private Function<Callable<Scheduler>, Scheduler> schedulerFunctionLazy =
            schedulerCallable -> trampoline;

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxAndroidPlugins.reset();
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerFunctionLazy);

                RxJavaPlugins.reset();
                RxJavaPlugins.setIoSchedulerHandler(schedulerFunction);
                RxJavaPlugins.setNewThreadSchedulerHandler(schedulerFunction);
                RxJavaPlugins.setComputationSchedulerHandler(schedulerFunction);

                base.evaluate();

                RxAndroidPlugins.reset();
                RxJavaPlugins.reset();
            }
        };
    }
}
