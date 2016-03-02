package cmq.learn.demo.app;

import android.app.Application;

/**
 * Created by cuimingqiang on 15/12/24.
 */
public class CustomApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        AppManager.init(this);
    }
}
