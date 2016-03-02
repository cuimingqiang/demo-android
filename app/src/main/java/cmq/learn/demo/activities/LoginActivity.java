package cmq.learn.demo.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import cmq.learn.demo.R;
import cmq.learn.demo.controllers.LoginController;
import cmq.learn.demo.view.LoginView;

/**
 * Created by cuimingqiang on 15/12/24.
 */
public class LoginActivity extends BaseActivity implements LoginView{

    @Bind(R.id.login)
    Button login;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.password)
    EditText password;
    public static LoginController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new LoginController(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.onDestroy();
    }

    @Override
    void contentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public EditText getMobile() {
        return phone;
    }

    @Override
    public EditText getPassword() {
        return password;
    }

    @Override
    public Button doLogin() {
        return login;
    }
}
