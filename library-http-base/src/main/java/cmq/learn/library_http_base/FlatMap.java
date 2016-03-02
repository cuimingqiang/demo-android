package cmq.learn.library_http_base;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by cuimingqiang on 16/3/2.
 */
public class FlatMap<T> implements Func1<BaseResultBean<T>, Observable<T>> {
    @Override
    public Observable<T> call(final BaseResultBean<T> result) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (result.status == 1) {
                    subscriber.onNext(result.data);
                    subscriber.onCompleted();
                } else subscriber.onError(new HttpError(result.status, result.msg));
            }
        });
    }
}
