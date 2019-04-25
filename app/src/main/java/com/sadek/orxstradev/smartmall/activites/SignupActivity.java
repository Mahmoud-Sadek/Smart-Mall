package com.sadek.orxstradev.smartmall.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.SignupInterface;
import com.sadek.orxstradev.smartmall.model.body.LoginBody;
import com.sadek.orxstradev.smartmall.model.body.SignupBody;
import com.sadek.orxstradev.smartmall.model.response.LoginResponse;
import com.sadek.orxstradev.smartmall.model.response.SignupResponse;
import com.sadek.orxstradev.smartmall.presenters.LoginPresenter;
import com.sadek.orxstradev.smartmall.presenters.SignupPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import io.paperdb.Paper;

public class SignupActivity extends BaseActitvty implements SignupInterface {

    @BindView(R.id.username_input)
    EditText usernameEdt;
    @BindView(R.id.password_input)
    EditText passwordEdt;
    @BindView(R.id.email_input)
    EditText emailEdt;
    public static KProgressHUD dialog = null;
    SignupPresenter _SignupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);
        Paper.init(this);
        dialog = new KProgressHUD(this);

        _SignupPresenter = new SignupPresenter(SignupActivity.this, this);
    }


    @OnClick(R.id.btnSignup)
    public void onButtonLoginClick(View view) {
        if (valid()) {
            _SignupPresenter.registerUser(new SignupBody(usernameEdt.getText().toString(), passwordEdt.getText().toString(), emailEdt.getText().toString()));
        }
    }

    @OnClick(R.id.btnBack)
    public void onbtnBackClick(View view) {
        onBackPressed();
    }
    private boolean valid() {
        usernameEdt.setError(null);
        passwordEdt.setError(null);
        emailEdt.setError(null);
        if (emailEdt.getText().toString().isEmpty()) {
            emailEdt.setError(this.getResources().getString(R.string.Pages_Login_EmailRequired));
            emailEdt.requestFocus();
            return false;
        } else if (usernameEdt.getText().toString().isEmpty()) {
            usernameEdt.setError(this.getResources().getString(R.string.Pages_Login_UserNameRequired));
            usernameEdt.requestFocus();
            return false;
        } else if (passwordEdt.getText().toString().isEmpty()) {
            passwordEdt.setError(this.getResources().getString(R.string.Pages_Login_PasswordRequired));
            passwordEdt.requestFocus();
            return false;
        }
        return true;
    }


    @Override
    public void onLoginSuccess(SignupResponse signupResponse) {
        if (signupResponse.getStatus().equals("true")) {
            Toast.makeText(getBaseContext(), R.string.welcome, Toast.LENGTH_LONG).show();
            Paper.book().write(Common.token, signupResponse.getUser_id() + "");
            Common.CURRENT_USSER = Paper.book().read(Common.token);
            Intent ordersIntent = new Intent(this, HomeActivity.class);
            ordersIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(ordersIntent);
            finish();
        } else {
            Toast.makeText(getBaseContext(), signupResponse.getMessage() + "", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(getBaseContext(), error + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProgressDialog(boolean status) {
        if (dialog != null) {
            if (status)
                dialog.show();
            else dialog.dismiss();
        }
    }
}
