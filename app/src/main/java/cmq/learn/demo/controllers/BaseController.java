package cmq.learn.demo.controllers;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cuimingqiang on 15/12/29.
 */
public class BaseController implements Controller {
    private CompositeSubscription subscriptions = new CompositeSubscription();
    public BaseController(){

    }

    protected void archiveSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public void onDestroy() {
        subscriptions.unsubscribe();
    }
}
