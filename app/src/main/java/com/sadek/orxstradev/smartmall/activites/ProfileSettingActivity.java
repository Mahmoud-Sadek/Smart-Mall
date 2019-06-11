package com.sadek.orxstradev.smartmall.activites;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.dialogs.EditEmailDialog;
import com.sadek.orxstradev.smartmall.dialogs.EditProfileDialog;
import com.sadek.orxstradev.smartmall.dialogs.LanguageDialog;
import com.sadek.orxstradev.smartmall.fragments.ProfileFragment;
import com.sadek.orxstradev.smartmall.interfaces.AddFavoriteInterface;
import com.sadek.orxstradev.smartmall.interfaces.SuccessInterface;
import com.sadek.orxstradev.smartmall.model.body.ChangeNameBody;
import com.sadek.orxstradev.smartmall.model.body.LoginBody;
import com.sadek.orxstradev.smartmall.model.response.DateResponse;
import com.sadek.orxstradev.smartmall.model.response.SuccessResponse;
import com.sadek.orxstradev.smartmall.presenters.ChangeEmailPresenter;
import com.sadek.orxstradev.smartmall.presenters.ChangeNamePresenter;
import com.sadek.orxstradev.smartmall.utils.Common;
import com.sadek.orxstradev.smartmall.utils.LocaleUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

public class ProfileSettingActivity extends BaseActitvty implements AddFavoriteInterface, SuccessInterface {

    ChangeNamePresenter changeNamePresenter;
    ChangeEmailPresenter changeEmailPresenter;
    public static KProgressHUD dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_seting);

        ButterKnife.bind(this);
        Paper.init(this);
        dialog = new KProgressHUD(this);

        changeNamePresenter = new ChangeNamePresenter(this, this);
        changeEmailPresenter = new ChangeEmailPresenter(this, this);

    }

    @OnClick(R.id.setting_language_btn)
    public void onsetting_language_btnClick(View view) {
        channgeLanguage();
    }

    EditProfileDialog changeNameDialog;
    EditEmailDialog changeEmailDialog;

    @OnClick(R.id.change_name)
    public void change_nameClick(View view) {
        if (Paper.book().read(Common.token) != null) {
            changeNameDialog = new EditProfileDialog(this, ProfileFragment.profileResponse.getData().get(0).getFullname(), new EditProfileDialog.useCouponAction() {

                @Override
                public void onGetCouponCode(String code) {
                    changeNameDialog.dismiss();
                    changeNamePresenter.changeName(new ChangeNameBody(Paper.book().read(Common.token).toString(), code, ProfileFragment.profileResponse.getData().get(0).getEmail()));

                }
            });
            changeNameDialog.show();
            changeNameDialog.setCancelable(false);
        } else {
            Toast.makeText(this, getString(R.string.you_are_not_login), Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.change_email)
    public void change_emailClick(View view) {
        if (Paper.book().read(Common.token) != null) {

            changeEmailDialog = new EditEmailDialog(this, ProfileFragment.profileResponse.getData().get(0).getEmail(), new EditEmailDialog.useCouponAction() {


                @Override
                public void onGetCouponCode(String email, String password) {

                    changeNameDialog.dismiss();
                    changeEmailPresenter.changeEmail(new LoginBody(email,password ));
                }
            });
            changeNameDialog.show();
            changeNameDialog.setCancelable(false);
        } else {
            Toast.makeText(this, getString(R.string.you_are_not_login), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.setting_rate_btn)
    public void onsetting_rate_btnClick(View view) {
        Uri uri = Uri.parse("market://details?id=" + getBaseContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getBaseContext().getPackageName())));
        }

    }

    @OnClick(R.id.setting_logout_btn)
    public void onsetting_logout_btnClick(View view) {
        if (Paper.book().read(Common.token) != null) {
            Paper.book().delete(Common.token);
            Paper.book().delete(Common.FIRIST_TIME);
            restartApp(ProfileSettingActivity.this, ProfileSettingActivity.this);
        } else {
            Toast.makeText(this, getString(R.string.you_are_not_login), Toast.LENGTH_SHORT).show();
        }
    }

    private void channgeLanguage() {

        LanguageDialog languageDialog = new LanguageDialog(ProfileSettingActivity.this, new LanguageDialog.languageDialogAction() {
            @Override
            public void onGetCode(boolean langStatus) {
                if (langStatus) {

                    Paper.book().write(Common.language, LocaleUtils.ARABIC);
                    restartApp(ProfileSettingActivity.this, ProfileSettingActivity.this);
                } else {

                    Paper.book().write(Common.language, LocaleUtils.ENGLISH);
                    restartApp(ProfileSettingActivity.this, ProfileSettingActivity.this);
                }
            }
        });
        languageDialog.show();
    }

    @Override
    public void onSuccess(DateResponse dateResponse) {
        Toast.makeText(this, getString(R.string.done), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(SuccessResponse successResponse) {

    }

    @Override
    public void onProgressDialog(boolean status) {
        if (status)
            dialog.show();
        else dialog.dismiss();

    }

    @Override
    public void onFailure(String error) {

    }
}
