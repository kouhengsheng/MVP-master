package com.kou.mvp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import me.yaowei.mvp.R;

public class TestActivity extends AppCompatActivity {

	private static final String TAG = "TestActivity";
	@BindView(R.id.myText)
	TextView myText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		ButterKnife.bind(this);
		//observableCreate();

		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				emitter.onNext(1);
				emitter.onNext(2);
				emitter.onNext(3);
			}
		}).map(new Function<Integer, String>() {
			@Override
			public String apply(Integer integer) throws Exception {

				return "this is result" + integer;
			}
		}).subscribe(new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {

			}

			@Override
			public void onNext(String s) {
				myText.append("accept : " + s + "\n");
				Log.e(TAG, "accept : " + s + "\n");
			}

			@Override
			public void onError(Throwable e) {
				Log.e(TAG, "eee" + e.toString());
			}

			@Override
			public void onComplete() {
				Log.e(TAG, "ee");
			}
		});
	}

	private void observableCreate() {
		Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
				myText.append("Observable emit 1" + "\n");
				Log.e(TAG, "Observable emit 1" + "\n");
				emitter.onNext(1);
				myText.append("Observable emit 2" + "\n");
				Log.e(TAG, "Observable emit 2" + "\n");
				emitter.onNext(2);
				myText.append("Observable emit 3" + "\n");
				Log.e(TAG, "Observable emit 3" + "\n");
				emitter.onNext(3);
				emitter.onComplete();
				myText.append("Observable emit 4" + "\n");
				Log.e(TAG, "Observable emit 4" + "\n");
				emitter.onNext(4);
			}
		}).subscribe(new Observer<Integer>() {

			private Disposable mDisposable;
			private int i;

			@Override
			public void onSubscribe(Disposable d) {
				myText.append("onSubscribe : " + d.isDisposed() + "\n");
				Log.e(TAG, "onSubscribe : " + d.isDisposed() + "\n");
				mDisposable = d;
			}

			@Override
			public void onNext(Integer integer) {
				myText.append("onNext : value : " + integer + "\n");
				Log.e(TAG, "onNext : value : " + integer + "\n");
				i++;
				if (i == 2) {
					mDisposable.dispose();
					myText.append("onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
					Log.e(TAG, "onNext : isDisposable : " + mDisposable.isDisposed() + "\n");

				}
			}

			@Override
			public void onError(Throwable e) {
				myText.append("onError : value : " + e.getMessage() + "\n");
				Log.e(TAG, "onError : value : " + e.getMessage() + "\n");
			}

			@Override
			public void onComplete() {
				myText.append("onComplete" + "\n");
				Log.e(TAG, "onComplete" + "\n");

			}
		});
	}
}
