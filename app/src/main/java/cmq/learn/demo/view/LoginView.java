package cmq.learn.demo.view;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by cuimingqiang on 16/2/17.
 */
public interface LoginView {
    EditText getMobile();
    EditText getPassword();
    Button doLogin();
    void startActivity(Intent intent);
}
