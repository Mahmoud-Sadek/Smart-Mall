package com.sadek.orxstradev.smartmall.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.activites.FavoriteListActivity;
import com.sadek.orxstradev.smartmall.activites.LoginActivity;
import com.sadek.orxstradev.smartmall.activites.MessageActivity;
import com.sadek.orxstradev.smartmall.activites.ProfileSettingActivity;
import com.sadek.orxstradev.smartmall.activites.WebViewActivity;
import com.sadek.orxstradev.smartmall.interfaces.ProfileInterface;
import com.sadek.orxstradev.smartmall.model.body.ProfileBody;
import com.sadek.orxstradev.smartmall.model.response.ProfileResponse;
import com.sadek.orxstradev.smartmall.presenters.ProfilePresenter;
import com.sadek.orxstradev.smartmall.utils.Common;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.paperdb.Paper;

public class ProfileFragment extends Fragment implements ProfileInterface {


    Unbinder unbinder;
    @BindView(R.id.profile_name_txt)
    TextView profile_name_txt;
    @BindView(R.id.profile_setting_btn)
    ImageView profile_setting_btn;
    @BindView(R.id.profile_message_btn)
    ImageView profile_message_btn;


    /*
    @BindView(R.id.profile_name_txt)
    TextView profile_name_txt;
    @BindView(R.id.profile_email_txt)
    TextView profile_email_txt;
    @BindView(R.id.profile_phone_txt)
    TextView profile_phone_txt;*/
//    @BindView(R.id.profile_name_txt)
//    TextView profile_name_txt;

    ProfilePresenter profilePresenter;

    public static ProfileResponse profileResponse;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        return view;
    }

    @OnClick(R.id.profile_name_txt)
    public void onprofile_name_txtClick(View view) {
        if (Paper.book().read(Common.token) == null) {
            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }
    @OnClick(R.id.profile_FAQ_btn)
    public void onprofile_FAQ_btnClick(View view) {
        Intent urlIntent = new Intent(getActivity(),WebViewActivity.class);
        urlIntent.putExtra("url","http://smartmall-sa.com/faq");
        startActivity(urlIntent);
    }
    @OnClick(R.id.profile_contact_btn)
    public void onprofile_contact_btnClick(View view) {
        Intent urlIntent = new Intent(getActivity(),WebViewActivity.class);
        urlIntent.putExtra("url","http://smartmall-sa.com/contactus");
        startActivity(urlIntent);
    }
    @OnClick(R.id.profile_support_btn)
    public void onprofile_support_btnClick(View view) {
        Intent urlIntent = new Intent(getActivity(),WebViewActivity.class);
        urlIntent.putExtra("url","http://smartmall-sa.com/aboutsmart");
        startActivity(urlIntent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        Paper.init(getContext());

        profilePresenter = new ProfilePresenter(getContext(), this);


        getData();

        profile_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingIntent = new Intent(getContext(), ProfileSettingActivity.class);
                startActivity(settingIntent);
            }
        });
        profile_message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingIntent = new Intent(getContext(), MessageActivity.class);
                startActivity(settingIntent);
            }
        });
    }


    @OnClick(R.id.layout_wishlist_product)
    void layout_wishlist_product() {
        if (Paper.book().read(Common.token) == null) {
            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
        } else
            startActivity(new Intent(getActivity(), FavoriteListActivity.class));

    }

    private void getData() {
        String UID = Paper.book().read(Common.token) + "";
        profilePresenter.getProfile(new ProfileBody(UID));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        unbinder.unbind();
    }


    @Override
    public void onSuccess(ProfileResponse profileResponse) {
        this.profileResponse = profileResponse;
        try {
            if (profileResponse.getData().get(0) != null) {
                profile_name_txt.setText(profileResponse.getData().get(0).getName() + "");
//                profile_email_txt.setText(profileResponse.getData().get(0).getEmail() + "");
//                profile_phone_txt.setText(profileResponse.getData().get(0).getPhone() + "");
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onProgressDialog(boolean status) {

    }

    @Override
    public void onFailure(String error) {
        try {
            Toast.makeText(getContext(), error + "", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }
}
