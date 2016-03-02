package cmq.learn.library_http_base;

/**
 * Created by cuimingqiang on 15/12/17.
 */
public class HttpError extends Exception{
    public int code;

    public HttpError(int code,String msg) {
        super(msg);
        this.code = code;
    }

    @Override
    public String toString() {
        return "HttpError{" +
                "code=" + code +
                '}'+super.toString();
    }
}
