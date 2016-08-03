package com.wizarpos.q1.cashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wizarpos.q1.cashier.R;
import com.wizarpos.q1.cashier.Function.Functions;


/**
 * 扫码页面UI
 * Created by lixinchun on 16/7/27.
 */
public class ScanCodePayFragment extends Fragment {
    private int code;
    private TextView themeTitle;
    private View rootView;
    private LinearLayout backBtn;
    private Button manualVerify;
    private Fragment manual_verify_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.scan_code_pay_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        themeTitle = (TextView)view.findViewById(R.id.themeTitle);
        backBtn = (LinearLayout) view.findViewById(R.id.backBtn);
        manualVerify = (Button) view.findViewById(R.id.manualVerify);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        manualVerify.setOnClickListener(buttonListener);
        Bundle bundle = getArguments();
        if (bundle!=null){
            code = bundle.getInt("code");
            switch (code){
                case Functions.coupon_cancel_code:
                    //优惠券撤销
                    themeTitle.setText("优惠券");
                    manualVerify.setText("测试详细");
                    break;
            }
        }
    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.manualVerify:
                    switch (code){
                        case Functions.coupon_cancel_code:
                            setCouponCancelCodeDetailFragment();
                            break;
                        default:
                            setManagerCashFragment();
                            break;
                    }

                    break;
            }
        }
    }

    private void initFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    /**
     * 扫描页面获取二维码数据 跳转核销结果界面
     */
    private void setCouponCancelCodeDetailFragment() {
        initFragment();
        if (manual_verify_fragment ==null) manual_verify_fragment = new CouponCancelCodeDetailFragment();
        fragmentTransaction.replace(R.id.mainFragment, manual_verify_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 创建收银界面
     */
    private void setManagerCashFragment() {
        initFragment();
        if (manual_verify_fragment ==null) manual_verify_fragment = new PayManualVerifyFragment();
        fragmentTransaction.replace(R.id.mainFragment, manual_verify_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
