package cmq.learn.demo.utils;

import cmq.learn.demo.app.AppManager;

/**
 * Created by cuimingqiang on 15/12/24.
 */
public final class ScreenUtils {

    public static float density(){
       return AppManager.getAppContext().getResources().getDisplayMetrics().density;
    }

    public static int screenWidth(){
        return AppManager.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }
    public static int screenHeight(){
        return AppManager.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int dp2px(int dip){
        return dip * (int)(density()+0.5);
    }

}
