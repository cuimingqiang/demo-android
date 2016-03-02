package cmq.learn.demo.app;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by cuimingqiang on 15/12/24.
 */
public final class AppManager {
    private static AppManager instance;
    private static Context appContext;

    private AppManager(Context context) {
        appContext = context.getApplicationContext();
    }

    public static void init(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo info : activityManager.getRunningAppProcesses()) {
            if (info.pid == android.os.Process.myPid()) {
                if (context.getPackageName().equals(info.processName)) {
                    instance = new AppManager(context);
                }
            }
        }
    }

    public static Context getAppContext() {
        if (appContext == null)
            throw new NullPointerException("AppManager context must be not null!");
        return appContext;
    }

    public static AppManager getInstance() {
        if (appContext == null)
            throw new NullPointerException("AppManager instance must be not null!");
        return instance;
    }
}
