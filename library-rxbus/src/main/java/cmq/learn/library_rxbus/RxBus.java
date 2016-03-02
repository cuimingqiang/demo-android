package cmq.learn.library_rxbus;

import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by cuimingqiang on 16/1/4.
 */
public final class RxBus {
    private static Subject subject = new SerializedSubject(PublishSubject.create());
    private static ConcurrentHashMap<String, Subject> stickySubject = new ConcurrentHashMap<>();

    public static <T> Observable<T> obtainEvent(Class<T> clazz) {
        return subject.ofType(clazz);
    }

    public static void post(Object object) {
        subject.onNext(object);
    }

    public static <T> Observable<T> obtainStickyEvent(Class<T> clazz){
        return getOrCreateStickySubject(clazz);
    }

    public static void postSticky(Object object){
        Subject s = getOrCreateStickySubject(object.getClass());
        s.onNext(object);
    }

    private synchronized static <T>  Subject getOrCreateStickySubject(Class<T> clazz) {
        Subject s = stickySubject.get(clazz.getName());
        if (s == null) {
            s = BehaviorSubject.create();
            stickySubject.put(clazz.getName(),s);
        }
        return s;
    }

    public static void recycleStickyEvent(Class<?> clazz) {
        stickySubject.remove(clazz.getName());
    }
}
