package com.sadek.orxstradev.smartmall.activites;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.dialogs.LanguageDialog;
import com.sadek.orxstradev.smartmall.interfaces.LoginInterface;
import com.sadek.orxstradev.smartmall.model.body.LoginBody;
import com.sadek.orxstradev.smartmall.model.response.LoginResponse;
import com.sadek.orxstradev.smartmall.presenters.LoginPresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.sadek.orxstradev.smartmall.utils.LocaleUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import io.paperdb.Paper;

public class LoginActivity extends BaseActitvty implements LoginInterface {


    private static final int RC_SIGN_IN = 1010;
    @BindView(R.id.username_input)
    EditText usernameEdt;
    @BindView(R.id.password_input)
    EditText passwordEdt;
    public static KProgressHUD dialog = null;
    LoginPresenter _LoginPresenter;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        Paper.init(this);
        dialog = new KProgressHUD(this);

        _LoginPresenter = new LoginPresenter(LoginActivity.this, this);
        Button btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        configureGoogleSignIn();
    }


    @OnClick(R.id.btnLogin)
    public void onButtonLoginClick(View view) {
        if (valid()) {
            _LoginPresenter.loginUser(new LoginBody(usernameEdt.getText().toString(), passwordEdt.getText().toString()));
        }
    }

    @OnClick(R.id.skip_btn)
    public void onButtonskip_btnClick(View view) {
        Intent ordersIntent = new Intent(this, HomeActivity.class);
        ordersIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(ordersIntent);
        finish();
    }
    private boolean valid() {
        usernameEdt.setError(null);
        passwordEdt.setError(null);

        if (usernameEdt.getText().toString().isEmpty()) {
            usernameEdt.setError(this.getResources().getString(R.string.Pages_Login_EmailRequired));
            usernameEdt.requestFocus();
            return false;
        } else if (passwordEdt.getText().toString().isEmpty()) {
            passwordEdt.setError(this.getResources().getString(R.string.Pages_Login_PasswordRequired));
            passwordEdt.requestFocus();
            return false;
        }
        return true;
    }

    private void configureGoogleSignIn() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()//request email id
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    @OnClick(R.id.default_google_sign_in_button)
    public void doGoogleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);//pass the declared request code here
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //method to handle google sign in result
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            getProfileInformation(account);

            //show toast
            Toast.makeText(this, "Google Sign In Successful.", Toast.LENGTH_SHORT).show();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("TAG", "signInResult:failed code=" + e.getStatusCode());

            //show toast
            Toast.makeText(this, "Failed to do Sign In : " + e.getStatusCode(), Toast.LENGTH_SHORT).show();

            //update Ui for this
            getProfileInformation(null);
        }
    }

    private void getProfileInformation(GoogleSignInAccount acct) {
        if (acct != null) {

            //user display name
            String personName = acct.getDisplayName();

            //user first name
            String personGivenName = acct.getGivenName();

            //user last name
            String personFamilyName = acct.getFamilyName();

            //user email id
            String personEmail = acct.getEmail();

            //user unique id
            String personId = acct.getId();

            //user profile pic
            Uri personPhoto = acct.getPhotoUrl();

            //show the user details
//            userDetailLabel.setText("ID : " + personId + "\nDisplay Name : " + personName + "\nFull Name : " + personGivenName + " " + personFamilyName + "\nEmail : " + personEmail);

            //show the user profile pic
//            Picasso.with(this).load(personPhoto).fit().placeholder(R.mipmap.ic_launcher_round).into(userProfileImageView);

            //change the text of Custom Sign in button to sign out
//            customSignInButton.setText(getResources().getString(R.string.sign_out));

            //show the label and image view
//            userDetailLabel.setVisibility(View.VISIBLE);
//            userProfileImageView.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {
        if (loginResponse.getStatus().equals("true")) {
            Toast.makeText(getBaseContext(), getString(R.string.welcome), Toast.LENGTH_LONG).show();
            Paper.book().write(Common.token, loginResponse.getData().get(0).getId() + "");
            Common.CURRENT_USSER = Paper.book().read(Common.token);
            Intent ordersIntent = new Intent(this, HomeActivity.class);
            ordersIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(ordersIntent);
            finish();
        } else {
            Toast.makeText(getBaseContext(), loginResponse.getMessage() + "", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onProgressDialog(boolean status) {
        if (dialog != null) {
            if (status)
                dialog.show();
            else dialog.dismiss();
        }
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(getBaseContext(), error + "", Toast.LENGTH_LONG).show();
    }
}
