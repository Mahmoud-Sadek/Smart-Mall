package com.sadek.orxstradev.smartmall.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sadek.orxstradev.smartmall.R;

public class EditProfileDialog extends Dialog {
    useCouponAction useCouponAction;
    EditText reviewInput;
    Button done,back;
    public EditProfileDialog(@NonNull final Context context,String value, final useCouponAction useCouponAction) {
        super(context);
        setContentView(R.layout.change_profile_dialog);
        this.useCouponAction=useCouponAction;

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        reviewInput=findViewById(R.id.review_txt_input);
        done=findViewById(R.id.btnAddReview);
        back=findViewById(R.id.btnCancel);

        reviewInput.setText(value);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reviewInput.getText().toString().isEmpty()){
                    reviewInput.setError(context.getString(R.string.reviewisrquerd));
                }else {
                    useCouponAction.onGetCouponCode(reviewInput.getText().toString());
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface useCouponAction{
        void onGetCouponCode(String code);
    }
}