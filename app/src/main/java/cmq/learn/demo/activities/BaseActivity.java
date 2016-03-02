package cmq.learn.demo.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

import butterknife.ButterKnife;
import cmq.learn.demo.controllers.callback.ActivityLifecycle;
import cmq.learn.demo.controllers.callback.OnDestroy;
import cmq.learn.demo.widgets.CustomActionBar;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;

/**
 * Created by cuimingqiang on 15/12/21.
 */
public abstract class BaseActivity extends FragmentActivity {
    protected RequestManager glide;
    protected BitmapPool bitmapPool;
    protected CustomActionBar actionBar;
    protected Subject<ActivityLifecycle,ActivityLifecycle> lifecycle = BehaviorSubject.create();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView();
        glide = Glide.with(this);
        bitmapPool = Glide.get(this).getBitmapPool();
        ButterKnife.bind(this);
        ActionBar bar = getActionBar();
        if (bar != null) {
            bar.setDisplayUseLogoEnabled(false);
            bar.setDisplayShowHomeEnabled(false);
            bar.setDisplayShowTitleEnabled(false);
            bar.setDisplayShowCustomEnabled(true);
            actionBar = new CustomActionBar(this);
            bar.setCustomView(actionBar, new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    abstract void contentView();

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        glide.onTrimMemory(level);
    }

    @Override
    protected void onDestroy() {
        lifecycle.onNext(new OnDestroy());
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    public void finish() {
        lifecycle.onCompleted();
        super.finish();
    }

    public CustomActionBar getCustomeActionBar() {
        return actionBar;
    }
}
