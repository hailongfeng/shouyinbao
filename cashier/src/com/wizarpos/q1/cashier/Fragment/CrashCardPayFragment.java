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
 * 刷卡支付Fragment
 * Created by lixinchun on 16/7/27.
 */
public class CrashCardPayFragment extends Fragment {
    private int code;
    private View rootView;
    private TextView themeText;
    private Button insertCardBtn,crashCardBtn,backBtn;
    private Fragment insert_card_pay_fragment,pay_password_input_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.crash_card_pay_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    public void initView(View view){

        themeText = (TextView) view.findViewById(R.id.themeText);
        backBtn = (Button) view.findViewById(R.id.backBtn);
        insertCardBtn = (Button) view.findViewById(R.id.insertCardBtn);
        crashCardBtn = (Button) view.findViewById(R.id.crashCardBtn);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        insertCardBtn.setOnClickListener(buttonListener);
        crashCardBtn.setOnClickListener(buttonListener);
        Bundle bundle = getArguments();
        if (bundle != null) {
            code = bundle.getInt("code");
            switch (code){
                case Functions.pre_author_cancel_ok:
                    themeText.setText("预授权完成撤销");
                    break;
                case Functions.trade_cancel_code:
                    themeText.setText("交易撤销");
                    break;
                case Functions.balance_enquiry_code:
                    themeText.setText("余额查询");
                    insertCardBtn.setText("");
                    insertCardBtn.setEnabled(false);
                    break;
                case Functions.union_pay_wallet_coupon_code:
                    themeText.setText("银联优惠券");
                    insertCardBtn.setText("");
                    insertCardBtn.setEnabled(false);
                    break;
                case Functions.union_pay_wallet_cancel_code:
                    themeText.setText("请刷卡");
                    break;
                case Functions.union_pay_wallet_return_good_code:
                    themeText.setText("银联钱包退货");
                    break;
                case Functions.iclound_manage_recharge_code:
                    themeText.setText("云账户充值");
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
                case R.id.insertCardBtn:
                    setInsertCardPayFragment();
                    break;
                case R.id.crashCardBtn:
                    //获取银行卡信息后，跳转密码支付页面

                    switch (code){
                        case Functions.pre_author_cancel_ok:
                            setPayPasswordInputFragment(code);
                            break;
                        case Functions.trade_cancel_code:
                            setPayPasswordInputFragment(code);
                            break;
                        case Functions.balance_enquiry_code:
                            setBalanceEnquiryCardPasswordInputFragment();
                            break;
                        case Functions.union_pay_wallet_coupon_code:
                            setUnionPayCouponTradeDetailConfirmFragment();
                            break;
                        case Functions.union_pay_wallet_cancel_code:
                            setPayPasswordInputFragment(code);
                            break;
                        case Functions.union_pay_wallet_return_good_code:
                            //刷卡完成到退货详细界面
                            setUnionPayWalletReturnGoodCardConfirmFragment();
                            break;
                        case Functions.iclound_manage_recharge_code:
                            //云账户充值
                            setPayPasswordInputFragment(code);
                            break;
                        default:
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
     * 创建收银界面
     */
    private void setInsertCardPayFragment() {
        initFragment();
        if (insert_card_pay_fragment ==null) insert_card_pay_fragment = new InsertCardPayFragment();
        fragmentTransaction.replace(R.id.mainFragment, insert_card_pay_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 密码支付验证页面
     */
    private void setPayPasswordInputFragment(int code) {
        initFragment();
        if (pay_password_input_fragment ==null) pay_password_input_fragment = new PayPasswordInputFragment();
        fragmentTransaction.replace(R.id.mainFragment, pay_password_input_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", code);
        pay_password_input_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 卡号密码支付验证页面
     */
    private void setBalanceEnquiryCardPasswordInputFragment() {
        initFragment();
        if (pay_password_input_fragment ==null) pay_password_input_fragment = new BalanceEnquiryCardPasswordInputFragment();
        fragmentTransaction.replace(R.id.mainFragment, pay_password_input_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 卡号交易信息验证页面
     */
    private void setUnionPayCouponTradeDetailConfirmFragment() {
        initFragment();
        if (pay_password_input_fragment ==null) pay_password_input_fragment = new UnionPayCouponTradeDetailConfirmFragment();
        fragmentTransaction.replace(R.id.mainFragment, pay_password_input_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 银联钱包退货信息确认页面
     */
    private void setUnionPayWalletReturnGoodCardConfirmFragment() {
        initFragment();
        if (pay_password_input_fragment ==null) pay_password_input_fragment = new UnionPayWalletReturnGoodCardConfirmFragment();
        fragmentTransaction.replace(R.id.mainFragment, pay_password_input_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
