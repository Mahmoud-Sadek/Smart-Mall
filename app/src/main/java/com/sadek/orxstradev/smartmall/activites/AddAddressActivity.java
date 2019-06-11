package com.sadek.orxstradev.smartmall.activites;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sadek.orxstradev.smartmall.BaseActitvty;
import com.sadek.orxstradev.smartmall.R;
import com.sadek.orxstradev.smartmall.interfaces.AddressInterface;
import com.sadek.orxstradev.smartmall.model.response.AddressModel;
import com.sadek.orxstradev.smartmall.presenters.AddressPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddAddressActivity extends BaseActitvty implements AddressInterface {


    Unbinder unbinder;
    @BindView(R.id.country)
    EditText country;
    @BindView(R.id.state)
    EditText state;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.street)
    EditText street;
    @BindView(R.id.address_detail)
    EditText address_detail;
    @BindView(R.id.nearst_landman)
    EditText nearst_landman;
    @BindView(R.id.mobile_number1)
    EditText mobile_number1;
    @BindView(R.id.mobile_number2)
    EditText mobile_number2;
    @BindView(R.id.shopping_note)
    EditText shopping_note;
    @BindView(R.id.first_name)
    EditText first_name;
    @BindView(R.id.last_name)
    EditText last_name;
    @BindView(R.id.district)
    EditText district;
    @BindView(R.id.postal_code)
    EditText postal_code;
    @BindView(R.id.location_type_RB)
    RadioGroup location_type_RB;


    public static AddressPresenter addressPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        unbinder = ButterKnife.bind(this);
        addressPresenter = new AddressPresenter(getBaseContext(), this);
    }

    @OnClick(R.id.btn_add_address)
    public void btn_product(View view) {
        if (validData()) {
            AddressModel.DataEntity addressModel = new AddressModel.DataEntity();
            addressModel.setCountry(country.getText().toString());
            addressModel.setCity(city.getText().toString());
            addressModel.setState(state.getText().toString());
            addressModel.setStreet(street.getText().toString());
            addressModel.setAddress(address_detail.getText().toString());
            addressModel.setLandmark(nearst_landman.getText().toString());
            addressModel.setPhone(mobile_number1.getText().toString());
            addressModel.setAltPhone(mobile_number2.getText().toString());
            addressModel.setShippingNote(shopping_note.getText().toString());
            addressModel.setFirstName(first_name.getText().toString());
            addressModel.setLastName(last_name.getText().toString());
            addressModel.setDistrict(district.getText().toString());
            addressModel.setPostalCode(postal_code.getText().toString());
            if (location_type_RB.getCheckedRadioButtonId() == R.id.homeRB) {
                addressModel.setLocationType("home");
            } else addressModel.setLocationType("work");
//            addressModel.setUserId(Paper.book().read(Common.token)+"");
            addressPresenter.addAddress(addressModel);
        }

    }

    private boolean validData() {
        country.setError(null);
        city.setError(null);
        state.setError(null);
        street.setError(null);
        address_detail.setError(null);
        nearst_landman.setError(null);
        mobile_number1.setError(null);
        shopping_note.setError(null);
        first_name.setError(null);
        last_name.setError(null);

        if (country.getText().toString().isEmpty()) {
            country.setError(this.getResources().getString(R.string.error));
            country.requestFocus();
            return false;
        } else if (city.getText().toString().isEmpty()) {
            city.setError(this.getResources().getString(R.string.error));
            city.requestFocus();
            return false;
        } else if (state.getText().toString().isEmpty()) {
            state.setError(this.getResources().getString(R.string.error));
            state.requestFocus();
            return false;
        } else if (street.getText().toString().isEmpty()) {
            street.setError(this.getResources().getString(R.string.error));
            street.requestFocus();
            return false;
        } else if (address_detail.getText().toString().isEmpty()) {
            address_detail.setError(this.getResources().getString(R.string.error));
            address_detail.requestFocus();
            return false;
        } else if (nearst_landman.getText().toString().isEmpty()) {
            nearst_landman.setError(this.getResources().getString(R.string.error));
            nearst_landman.requestFocus();
            return false;
        } else if (mobile_number1.getText().toString().isEmpty()) {
            mobile_number1.setError(this.getResources().getString(R.string.error));
            mobile_number1.requestFocus();
            return false;
        } else if (shopping_note.getText().toString().isEmpty()) {
            shopping_note.setError(this.getResources().getString(R.string.error));
            shopping_note.requestFocus();
            return false;
        } else if (first_name.getText().toString().isEmpty()) {
            first_name.setError(this.getResources().getString(R.string.error));
            first_name.requestFocus();
            return false;
        } else if (last_name.getText().toString().isEmpty()) {
            last_name.setError(this.getResources().getString(R.string.error));
            last_name.requestFocus();
            return false;
        } else if (district.getText().toString().isEmpty()) {
            district.setError(this.getResources().getString(R.string.error));
            district.requestFocus();
            return false;
        } else if (postal_code.getText().toString().isEmpty()) {
            postal_code.setError(this.getResources().getString(R.string.error));
            postal_code.requestFocus();
            return false;
        } else if (!location_type_RB.isSelected()) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    public void onSuccess(AddressModel addressModel) {
        finish();
    }

    @Override
    public void onProgressDialog(boolean status) {

    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }
}
