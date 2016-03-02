package cmq.learn.library_presenter_user;

import cmq.learn.library_http_base.APIManager;
import cmq.learn.library_http_base.FlatMap;
import cmq.learn.library_http_user.API;
import cmq.learn.library_http_user.params.LoginParamBean;
import cmq.learn.library_http_user.results.LoginResultBean;
import rx.Observable;

/**
 * Created by cuimingqiang on 15/12/31.
 */
public class LoginPresenter {


    public Observable<LoginResultBean> login(LoginParamBean param) {
        return APIManager.getAPI(API.class).login(param)
                .flatMap(new FlatMap<LoginResultBean>());
    }



}
