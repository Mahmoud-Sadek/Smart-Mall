package com.sadek.orxstradev.smartmall.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sadek.orxstradev.smartmall.R;

public class EditEmailDialog extends Dialog {
    useCouponAction useCouponAction;
    EditText email_txt_input, password_txt_input;
    Button done,back;
    public EditEmailDialog(@NonNull final Context context, String value, final useCouponAction useCouponAction) {
        super(context);
        setContentView(R.layout.change_email_dialog);
        this.useCouponAction=useCouponAction;

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        email_txt_input=findViewById(R.id.email_txt_input);
        password_txt_input=findViewById(R.id.password_txt_input);
        done=findViewById(R.id.btnAddReview);
        back=findViewById(R.id.btnCancel);

        email_txt_input.setText(value);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email_txt_input.getText().toString().isEmpty()){
                    email_txt_input.setError(context.getString(R.string.error));
                }else if(password_txt_input.getText().toString().isEmpty()){
                    password_txt_input.setError(context.getString(R.string.error));
                }{
                    useCouponAction.onGetCouponCode(email_txt_input.getText().toString(),password_txt_input.getText().toString());
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
        void onGetCouponCode(String email, String password);
    }
}