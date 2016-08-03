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
import android.widget.EditText;

import com.wizarpos.q1.cashier.R;
import com.wizarpos.q1.cashier.Function.Functions;
import com.wizarpos.q1.cashier.Util.CaculateUtil;


/**
 * Created by lixinchun on 16/7/27.
 */
public class UnionPayCouponInputPriceFragment extends Fragment {
    private View rootView;
    private EditText amountText;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,removeBtn,backBtn,crashCardBtn,btnreset;
    private Fragment scan_code_pay_fragment,crash_code_pay_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.unionpay_coupon_input_price_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        amountText = (EditText) view.findViewById(R.id.amountText);
        amountText.addTextChangedListener(textWatcher);
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
        crashCardBtn = (Button)view.findViewById(R.id.crashCardBtn);
        ButtonListener buttonListener = new ButtonListener();
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
        removeBtn.setOnClickListener(buttonListener);
        btnreset.setOnClickListener(buttonListener);
        crashCardBtn.setOnClickListener(buttonListener);
        btnreset.setText(".");
    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.crashCardBtn:
                    setCrashCardPayFragment();
                    break;
                case R.id.btn0:
                    amountText.setText(CaculateUtil.inputMoney(amountText.getText().toString(),"0"));
                    break;
                case R.id.btn1:
                    amountText.setText(CaculateUtil.inputMoney(amountText.getText().toString(),"1"));
                    break;
                case R.id.btn2:
                    amountText.setText(CaculateUtil.inputMoney(amountText.getText().toString(),"2"));
                    break;
                case R.id.btn3:
                    amountText.setText(CaculateUtil.inputMoney(amountText.getText().toString(),"3"));
                    break;
                case R.id.btn4:
                    amountText.setText(CaculateUtil.inputMoney(amountText.getText().toString(),"4"));
                    break;
                case R.id.btn5:
                    amountText.setText(CaculateUtil.inputMoney(amountText.getText().toString(),"5"));
                    break;
                case R.id.btn6:
                    amountText.setText(CaculateUtil.inputMoney(amountText.getText().toString(),"6"));
                    break;
                case R.id.btn7:
                    amountText.setText(CaculateUtil.inputMoney(amountText.getText().toString(),"7"));
                    break;
                case R.id.btn8:
                    amountText.setText(CaculateUtil.inputMoney(amountText.getText().toString(),"8"));
                    break;
                case R.id.btn9:
                    amountText.setText(CaculateUtil.inputMoney(amountText.getText().toString(),"9"));
                    break;
                case R.id.removeBtn:
                    if (amountText.getText().length()>0){
                        amountText.setText(amountText.getText().toString().substring(0,amountText.getText().length()-1));
                    }
                    break;
                case R.id.btnreset:
                    amountText.setText("");
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

        }
    };

    /**
     * 刷卡支付界面
     */
    private void setCrashCardPayFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        crash_code_pay_fragment = new CrashCardPayFragment();
        fragmentTransaction.replace(R.id.mainFragment, crash_code_pay_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", Functions.union_pay_wallet_coupon_code);
        crash_code_pay_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



}
