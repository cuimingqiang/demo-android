package cmq.learn.library_http_user;

import cmq.learn.library_http_base.BaseResultBean;
import cmq.learn.library_http_user.params.LoginParamBean;
import cmq.learn.library_http_user.params.RegisterParamBean;
import cmq.learn.library_http_user.results.LoginResultBean;
import cmq.learn.library_http_user.results.RegisterResultBean;
import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by cuimingqiang on 15/12/17.
 */
public interface API {
    @POST("/user/register")
    Observable<BaseResultBean<RegisterResultBean>> register(@Body RegisterParamBean param);

    @POST("/user/login")
    Observable<BaseResultBean<LoginResultBean>> login(@Body LoginParamBean param);

}
