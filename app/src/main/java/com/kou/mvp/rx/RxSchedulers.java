package com.kou.mvp.rx;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Nicholas on 2016/11/2.
 */

public class RxSchedulers {

    public static <T> Observable.Transformer<T, T> switchThread() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(new Scheduler() {
							@Override
							public Worker createWorker() {
								return null;
							}
						});
            }
        };
    }
}
