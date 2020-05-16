package com.tsp.learn.android.core.mvp;

import androidx.appcompat.app.AppCompatActivity;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import florent37.github.com.rxlifecycle.RxLifecycle;
import io.reactivex.Observable;

public abstract class RxBaseMvpPresenter<V extends MvpView>
        extends MvpBasePresenter<V> implements MvpPresenter<V> {

   public  <T> Observable<T> bind(Observable<T> observable) {
       if (getView() == null) {
           return observable;
       }
       return observable.compose(RxLifecycle.<T>disposeOnDestroy((AppCompatActivity)getView()));
   }
}
