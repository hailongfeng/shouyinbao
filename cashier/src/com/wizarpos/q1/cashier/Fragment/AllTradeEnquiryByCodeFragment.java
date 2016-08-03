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


/**
 * 通过凭证号／交易单号查询
 * Created by lixinchun on 16/7/27.
 */
public class AllTradeEnquiryByCodeFragment extends Fragment {
    private View rootView;
    private EditText pv;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnreset,btn0,removeBtn,cancelBtn,btnConfirm;
    private Fragment trade_cancel_confirm_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.all_trade_enquiry_by_code_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        pv = (EditText) view.findViewById(R.id.payCode);
        cancelBtn = (Button)view.findViewById(R.id.cancelBtn);
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
        btnConfirm = (Button)view.findViewById(R.id.btnconfirm);
        ButtonListener buttonListener = new ButtonListener();
        cancelBtn.setOnClickListener(buttonListener);
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
        btnConfirm.setOnClickListener(buttonListener);

    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.cancelBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.btnconfirm:
                    if (!pv.getText().toString().replaceAll(" ","").equals("")){
                        setAllTradeEnquiryCodeDetailFragment(pv.getText().toString());
                    }else{
                        pv.setText("");
                    }
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
            //验证密码是否不为数字
            if (!s.toString().matches("^[0-9]*$")) {
                pv.setText("");
            }
        }
    };

    /**
     * 跳往交易单号／凭证号查询结果页面
     */
    private void setAllTradeEnquiryCodeDetailFragment(String code){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        trade_cancel_confirm_fragment = new AllTradeEnquiryCodeDetailFragment();
        fragmentTransaction.replace(R.id.mainFragment,trade_cancel_confirm_fragment);
        Bundle bundle = new Bundle();
        bundle.putString("tradeNumber", code);
        trade_cancel_confirm_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
