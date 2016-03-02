package cmq.learn.demo.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;

import java.lang.ref.WeakReference;

import cmq.learn.demo.activities.MainActivity;
import cmq.learn.library_http_user.params.LoginParamBean;
import cmq.learn.library_http_user.results.LoginResultBean;
import cmq.learn.demo.presenters.LoginPresenter;
import cmq.learn.demo.view.LoginView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cuimingqiang on 15/12/29.
 */
public class LoginController extends BaseController {
    private LoginPresenter presenter;
    private WeakReference<LoginView> loginView;
    private CompositeSubscription subscription = new CompositeSubscription();
    public LoginController(LoginView view) {

        this.loginView = new WeakReference<>(view);

        archiveSubscription(initEdit());
        archiveSubscription(initLogin());
        Subscriber subscriber = new Subscriber<LoginResultBean>() {
            @Override
            public void onCompleted() {
                Log.i("---","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("---","onError");
            }

            @Override
            public void onNext(LoginResultBean loginResultBean) {
                Log.i("---","onNext");
                getLoginView().startActivity(new Intent((Context) getLoginView(), MainActivity.class));
                ((Activity)getLoginView()).finish();

            }
        };
        subscriber.add(subscription);
        presenter = new LoginPresenter(subscriber);

    }


    private Subscription initLogin(){
       return RxView.clicks(getLoginView().doLogin())
               .subscribe(new Action1<Void>() {
                   @Override
                   public void call(Void aVoid) {
                       LoginParamBean param = new LoginParamBean();
                       param.mobile = getLoginView().getMobile().getText().toString().trim();
                       param.password = getLoginView().getPassword().getText().toString().trim();
                       param.devicetoken = "----";
                       archiveSubscription(presenter.login(param));
                   }
               });
    }
    private Subscription initEdit(){
        return Observable.combineLatest(RxTextView.afterTextChangeEvents(getLoginView().getMobile()),
                RxTextView.afterTextChangeEvents(getLoginView().getPassword()),
                new Func2<TextViewAfterTextChangeEvent, TextViewAfterTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(TextViewAfterTextChangeEvent o1, TextViewAfterTextChangeEvent o2) {
                        boolean result = (!TextUtils.isEmpty(o1.editable())) && (!TextUtils.isEmpty(o2.editable()));
                        return result;
                    }
                }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean o) {
                getLoginView().doLogin().setEnabled(o);
            }
        });
    }

    public LoginView getLoginView(){
        return loginView.get();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }
}
