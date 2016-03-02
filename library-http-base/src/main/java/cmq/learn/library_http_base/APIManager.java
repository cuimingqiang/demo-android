package cmq.learn.library_http_base;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.ConcurrentHashMap;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by cuimingqiang on 15/12/17.
 */
public class APIManager {

    private static OkHttpClient client = new OkHttpClient();
    private static ConcurrentHashMap<String, Object> APICache = new ConcurrentHashMap<>();

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        client.interceptors().add(logging);
    }

    public static <T> T getAPI(Class<T> clazz) {
        T api = (T) APICache.get(clazz.getName());
        if (api == null) {
            synchronized (APIManager.class) {
                if (api == null) return api;
                api = new Retrofit.Builder().client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .baseUrl("http://112.124.25.25:8080")
                        .build().create(clazz);
                APICache.put(clazz.getName(),api);
            }
        }
        return api;
    }
}
