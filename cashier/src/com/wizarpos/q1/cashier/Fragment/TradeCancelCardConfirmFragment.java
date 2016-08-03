package com.wizarpos.q1.cashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wizarpos.q1.cashier.R;
import com.wizarpos.q1.cashier.Function.Functions;


/**
 * 界面重用
 * 消费撤销确认UI
 * Created by lixinchun on 16/7/27.
 */
public class TradeCancelCardConfirmFragment extends Fragment {
    private int code;
    private TextView themeTitle;
    private View rootView;
    private Button backBtn,crashCardConfirm;
    private Fragment crash_card_pay_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.trade_cancel_card_confirm_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        themeTitle = (TextView)view.findViewById(R.id.themeTitle);
        backBtn = (Button)view.findViewById(R.id.backHomeBtn);
        crashCardConfirm = (Button)view.findViewById(R.id.crashCardConfirm);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        crashCardConfirm.setOnClickListener(buttonListener);
        Bundle bundle = getArguments();
        if (bundle!=null){
            code = bundle.getInt("code");
            switch (code){
                case Functions.trade_cancel_code:
                    themeTitle.setText("交易撤销");
                    break;
                case Functions.union_pay_wallet_cancel_code:
                    themeTitle.setText("交易信息确认");
                    break;
            }
        }
    }

    /**
     * 创建单击事件
     */
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backHomeBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.crashCardConfirm:
                    //授权金额确认
                    switch (code){
                        case Functions.trade_cancel_code:
                            setCrashCardPayFragment();
                            break;
                        case Functions.union_pay_wallet_cancel_code:
                            setCrashCardPayFragment();
                            break;
                    }

                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 刷卡界面跳转
     */
    private void setCrashCardPayFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (crash_card_pay_fragment==null)crash_card_pay_fragment = new CrashCardPayFragment();
        fragmentTransaction.replace(R.id.mainFragment,crash_card_pay_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", code);
        crash_card_pay_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



}
