package com.sadek.orxstradev.smartmall.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.sadek.orxstradev.smartmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FilterBottomDialogFragment extends BottomSheetDialogFragment {


    Unbinder unbinder;
    @BindView(R.id.price_lower_high)
    RadioButton price_lower_high;
    @BindView(R.id.price_high_low)
    RadioButton price_high_low;
    @BindView(R.id.most_popular)
    RadioButton most_pop;

    static FilterAction filterAction;

    public static FilterBottomDialogFragment newInstance(FilterAction _filterAction) {
        filterAction = _filterAction;
        return new FilterBottomDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_filter, container,
                false);
        unbinder = ButterKnife.bind(this, view);
        price_lower_high.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    filterAction.onGetFilterCode(0);
            }
        });
        price_high_low.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    filterAction.onGetFilterCode(1);
            }
        });
        most_pop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    filterAction.onGetFilterCode(2);
            }
        });
        return view;

    }

    public interface FilterAction {
        void onGetFilterCode(int code);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        unbinder.unbind();
    }

}