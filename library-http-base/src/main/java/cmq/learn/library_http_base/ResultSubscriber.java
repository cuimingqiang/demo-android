package cmq.learn.library_http_base;

import org.apache.http.conn.ConnectTimeoutException;

import retrofit.HttpException;
import rx.Subscriber;

/**
 * Created by cuimingqiang on 15/12/17.
 */
public abstract class ResultSubscriber<T> extends Subscriber<BaseResultBean<T>> {

    @Override
    public void onError(Throwable e) {
        try {
            if(e instanceof HttpError) {
                onError((HttpError) e);
            }else if(e instanceof ConnectTimeoutException){
                onError(new HttpError(100,"网络未连接"));
            }else if (e instanceof HttpException){
                onError(new HttpError(((HttpException) e).code(),((HttpException) e).message()));
            } else {
                onError(new HttpError(101,e.getMessage()));
            }
        }catch (Exception e1){
            e.printStackTrace();
        }
    }

    public abstract void onSuccess(T result);

    public abstract void onError(HttpError error);

    @Override
    public void onNext(BaseResultBean<T> result) {
        if(result==null){
            onError(new NullPointerException("未返回参数"));
        }else if(result.status!=1){
            onError(new HttpError(result.status,result.msg));
        }else {
            onSuccess(result.data);
        }
    }
}
