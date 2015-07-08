package com.futureinapps.ledawateradmin.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.futureinapps.ledawateradmin.R;
import com.futureinapps.ledawateradmin.activities.MainActivity;
import com.futureinapps.ledawateradmin.pojos.Order;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fappsilya on 07.07.15.
 */
public class DetailsOrderFragment extends BaseFragment {

    @Bind(R.id.order_image)
    ImageView mImage;
    @Bind(R.id.order_date)
    TextView mDate;
    @Bind(R.id.order_customer_name)
    TextView mCustomerName;
    @Bind(R.id.order_customer_phone)
    TextView mCustomerPhone;
    @Bind(R.id.order_name)
    TextView mName;
    @Bind(R.id.order_status)
    TextView mStatus;
    @Bind(R.id.order_height)
    TextView mHeight;
//    @Bind(R.id.order_width)
//    TextView mWidth;
//    @Bind(R.id.order_fat)
//    TextView mFat;
    @Bind(R.id.order_count)
    TextView mCount;
    @Bind(R.id.accept_order_btn)
    Button mAcceptBtn;

    private Order order;
    private ProgressDialog pd;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, v);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Подождите");
        pd.show();
        if(getArguments()!= null){
            order = getArguments().getParcelable("order");
            SimpleDateFormat dateParser = new SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.US);
            mDate.setText(dateParser.format(order.getCreatedAt()));
            mName.setText(order.getName());
            if(order.getStatus().equals("ПОДТВЕРЖДЕН")){
                mStatus.setTextColor(getResources().getColor(R.color.orange));
                mAcceptBtn.setVisibility(View.GONE);
            }
            mStatus.setText(order.getStatus());
            mCount.setText("Количество: " + order.getCount());
            MainActivity.imageSelector(order, mImage, getActivity());
            mHeight.setText("Параметры(см) ВхШхГ: " + order.getParams());
            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.whereEqualTo("objectId", order.getCustomerId());
            query.getFirstInBackground(new GetCallback<ParseUser>() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    mCustomerName.setText("Заказчик: " + parseUser.getUsername());
                    mCustomerPhone.setText("Телефон: " + parseUser.getString("phoneNumber"));
                    pd.dismiss();
                }
            });
        } else {
            order = new Order();
        }

        return v;
    }

    @OnClick(R.id.accept_order_btn)
    void onAcceptBtnClick(){
        order.setStatus("ПОДТВЕРЖДЕН");
        order.saveInBackground();
        ParseQuery query = ParseInstallation.getQuery();
        query.whereEqualTo("channels", "global");
        ParsePush push = new ParsePush();
        query.whereEqualTo("deviceId", order.getCustomerId());
        push.setQuery(query);
        push.setMessage("Ваш заказ подтвержден!");
        push.sendInBackground();
        returnFromFragment();
    }


    @OnClick(R.id.delete_order_btn)
    void onDeletetBtnClick(){
        order.deleteInBackground();
        returnFromFragment();
    }

    private void returnFromFragment() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container2, new OrdersFragment())
                .commit();
    }
}
