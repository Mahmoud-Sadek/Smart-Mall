package com.sadek.orxstradev.smartmall.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sadek.orxstradev.smartmall.R;

public class ReviewDialog extends Dialog {
    useCouponAction useCouponAction;
    EditText reviewInput;
    Button done,back;
    public ReviewDialog(@NonNull final Context context, final useCouponAction useCouponAction) {
        super(context);
        setContentView(R.layout.review_dialog);
        this.useCouponAction=useCouponAction;
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        reviewInput=findViewById(R.id.review_txt_input);
        done=findViewById(R.id.btnAddReview);
        back=findViewById(R.id.btnCancel);
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