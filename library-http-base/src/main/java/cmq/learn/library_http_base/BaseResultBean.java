package cmq.learn.library_http_base;

/**
 * Created by cuimingqiang on 15/12/17.
 */
public class BaseResultBean<T> {
    public int status;
    public String msg;
    public T data;
}
