package com.wizarpos.q1.cashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wizarpos.q1.cashier.R;
import com.wizarpos.q1.cashier.Function.Functions;
import com.wizarpos.q1.cashier.View.PasswordInputView;


/**
 * 管理员密码输入界面
 * Created by lixinchun on 16/7/27.
 */
public class CardPreAuthorOkCancelFragment extends Fragment {
    private int code = Functions.pre_author_ok_cancel_code;
    private View rootView;
    private PasswordInputView pv;
    private TextView themeTitle;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnreset,btn0,removeBtn,backBtn;
    private Fragment card_pre_author_tradeno_fragment,trade_cancel_manual_verify_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.card_pre_author_ok_cancel_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        themeTitle = (TextView)view.findViewById(R.id.themeTitle);
        pv = (PasswordInputView)view.findViewById(R.id.passwordInputView);
        pv.addTextChangedListener(textWatcher);
        backBtn = (Button)view.findViewById(R.id.backBtn);
        btn0 = (Button)view.findViewById(R.id.btn0);
        btn1 = (Button)view.findViewById(R.id.btn1);
        btn2 = (Button)view.findViewById(R.id.btn2);
        btn3 = (Button)view.findViewById(R.id.btn3);
        btn4 = (Button)view.findViewById(R.id.btn4);
        btn5 = (Button)view.findViewById(R.id.btn5);
        btn6 = (Button)view.findViewById(R.id.btn6);
        btn7 = (Button)view.findViewById(R.id.btn7);
        btn8 = (Button)view.findViewById(R.id.btn8);
        btn9 = (Button)view.findViewById(R.id.btn9);
        btnreset = (Button)view.findViewById(R.id.btnreset);
        removeBtn = (Button)view.findViewById(R.id.removeBtn);
        ButtonListener buttonListener = new ButtonListener();
        removeBtn.setOnClickListener(buttonListener);
        backBtn.setOnClickListener(buttonListener);
        btn0.setOnClickListener(buttonListener);
        btn1.setOnClickListener(buttonListener);
        btn2.setOnClickListener(buttonListener);
        btn3.setOnClickListener(buttonListener);
        btn4.setOnClickListener(buttonListener);
        btn5.setOnClickListener(buttonListener);
        btn6.setOnClickListener(buttonListener);
        btn7.setOnClickListener(buttonListener);
        btn8.setOnClickListener(buttonListener);
        btn9.setOnClickListener(buttonListener);
        btnreset.setOnClickListener(buttonListener);
        Bundle bundle = getArguments();
        if (bundle!=null){
            code = bundle.getInt("code");
            switch (code){
                case Functions.trade_cancel_code:
                    themeTitle.setText("交易撤销");
                    break;
                case Functions.pre_author_ok_cancel_code:
                    themeTitle.setText("预授权完成撤销");
                    break;
                case Functions.trade_return_goods_code:
                    themeTitle.setText("交易退货");
                    break;
                case Functions.union_pay_wallet_cancel_code:
                    themeTitle.setText("银联钱包撤销");
                    break;
                case Functions.union_pay_wallet_return_good_code:
                    themeTitle.setText("银联钱包退货");
                    break;
                case Functions.iclound_manage_code:
                    themeTitle.setText("云账户");
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
                case R.id.btn0:
                    pv.setText(pv.getText()+"0");
                    break;
                case R.id.btn1:
                    pv.setText(pv.getText()+"1");
                    break;
                case R.id.btn2:
                    pv.setText(pv.getText()+"2");
                    break;
                case R.id.btn3:
                    pv.setText(pv.getText()+"3");
                    break;
                case R.id.btn4:
                    pv.setText(pv.getText()+"4");
                    break;
                case R.id.btn5:
                    pv.setText(pv.getText()+"5");
                    break;
                case R.id.btn6:
                    pv.setText(pv.getText()+"6");
                    break;
                case R.id.btn7:
                    pv.setText(pv.getText()+"7");
                    break;
                case R.id.btn8:
                    pv.setText(pv.getText()+"8");
                    break;
                case R.id.btn9:
                    pv.setText(pv.getText()+"9");
                    break;
                case R.id.removeBtn:
                    if (pv.getText().length()>0){
                        pv.setText(pv.getText().toString().substring(0,pv.getText().length()-1));
                    }
                    break;
                case R.id.btnreset:
                    pv.setText("");
                    break;
            }
        }
    }

    //监听事件
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.w("输入",s.toString());
            if (s.toString().matches("^[0-9]*$")&&s.toString().equals("123456")) {
                switch (code){
                    case Functions.trade_cancel_code:
                        setTradeCancelManualVerifyFragment();
                        break;
                    case Functions.pre_author_ok_cancel_code:
                        setCardPreAuthorTradeNoFragment();
                        break;
                    case Functions.trade_return_goods_code:
                        setTradeReturnGoodScanCardFragment();
                        break;
                    case Functions.union_pay_wallet_cancel_code:
                        setTradeCancelManualVerifyFragment();
                        break;
                    case Functions.union_pay_wallet_return_good_code:
                        //银联钱包退货刷卡界面
                        setCrashCardPayFragment(code);
                        break;
                    case Functions.iclound_manage_code:
                        //云账户余额详细界面
                        setICloudBalanceEnquiryDetailFragment();
                        break;
                    default:
                        break;
                }


            }
        }
    };

    /**
     * 预授权撤销手动输入凭证号页面
     */
    private void setCardPreAuthorTradeNoFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (card_pre_author_tradeno_fragment==null)card_pre_author_tradeno_fragment = new CardPreAuthorTradeNoFragment();
        fragmentTransaction.replace(R.id.mainFragment,card_pre_author_tradeno_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 交易取消手动输入交易单号页面
     */
    private void setTradeCancelManualVerifyFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (trade_cancel_manual_verify_fragment==null)trade_cancel_manual_verify_fragment = new TradeCancelManualVerifyFragment();
        fragmentTransaction.replace(R.id.mainFragment,trade_cancel_manual_verify_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", code);
        trade_cancel_manual_verify_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        if(fragmentManager.getBackStackEntryCount()>0){
            fragmentManager.popBackStack();
            fragmentTransaction.commit();
        }
    }

    /**
     * 刷卡界面跳转
     */
    private void setCrashCardPayFragment(int code){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (trade_cancel_manual_verify_fragment==null)trade_cancel_manual_verify_fragment = new CrashCardPayFragment();
        fragmentTransaction.replace(R.id.mainFragment,trade_cancel_manual_verify_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", code);
        trade_cancel_manual_verify_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 交易取消手动输入交易单号页面
     */
    private void setTradeReturnGoodScanCardFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (trade_cancel_manual_verify_fragment==null)trade_cancel_manual_verify_fragment = new TradeReturnGoodCrashCardFragment();
        fragmentTransaction.replace(R.id.mainFragment,trade_cancel_manual_verify_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.addToBackStack(null);
        if(fragmentManager.getBackStackEntryCount()>0){
            fragmentManager.popBackStack();
            fragmentTransaction.commit();
        }
    }

    /**
     * 云账户交易详细页面
     */
    private void setICloudBalanceEnquiryDetailFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (trade_cancel_manual_verify_fragment==null)trade_cancel_manual_verify_fragment = new ICloudBalanceEnquiryDetailFragment();
        fragmentTransaction.replace(R.id.mainFragment,trade_cancel_manual_verify_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.addToBackStack(null);
        if(fragmentManager.getBackStackEntryCount()>0){
            fragmentManager.popBackStack();
            fragmentTransaction.commit();
        }
    }


}
