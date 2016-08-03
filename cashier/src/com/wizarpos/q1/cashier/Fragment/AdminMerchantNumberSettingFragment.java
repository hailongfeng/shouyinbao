package com.wizarpos.q1.cashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.wizarpos.q1.cashier.R;


/**
 * 商户号设置参数管理界面
 * Created by lixinchun on 16/7/27.
 */
public class AdminMerchantNumberSettingFragment extends Fragment {
    private View rootView;
    private LinearLayout numberKeyBoard;
    private EditText merchantNo;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnreset,btn0,removeBtn,backBtn,saveBtn;
    private Fragment manager_password_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.admin_merchantno_settings_ui,container,false);
        initView(rootView);
        return rootView;
    }

    /**
     * 初始化
     * @param view
     */
    private void initView(View view){
        backBtn = (Button)view.findViewById(R.id.backBtn);
        saveBtn = (Button)view.findViewById(R.id.saveBtn);
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
        merchantNo = (EditText)view.findViewById(R.id.merchantNo);
        numberKeyBoard = (LinearLayout)view.findViewById(R.id.numberKeyBoard);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        saveBtn.setOnClickListener(buttonListener);
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
        removeBtn.setOnClickListener(buttonListener);
        merchantNo.setOnClickListener(buttonListener);
        merchantNo.addTextChangedListener(textWatcher);
    }

    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.merchantNo:
                    numberKeyBoard.setVisibility(View.VISIBLE);
                    break;
                case R.id.saveBtn:

                    break;
                case R.id.btn0:
                    merchantNo.setText(merchantNo.getText()+"0");
                    break;
                case R.id.btn1:
                    merchantNo.setText(merchantNo.getText()+"1");
                    break;
                case R.id.btn2:
                    merchantNo.setText(merchantNo.getText()+"2");
                    break;
                case R.id.btn3:
                    merchantNo.setText(merchantNo.getText()+"3");
                    break;
                case R.id.btn4:
                    merchantNo.setText(merchantNo.getText()+"4");
                    break;
                case R.id.btn5:
                    merchantNo.setText(merchantNo.getText()+"5");
                    break;
                case R.id.btn6:
                    merchantNo.setText(merchantNo.getText()+"6");
                    break;
                case R.id.btn7:
                    merchantNo.setText(merchantNo.getText()+"7");
                    break;
                case R.id.btn8:
                    merchantNo.setText(merchantNo.getText()+"8");
                    break;
                case R.id.btn9:
                    merchantNo.setText(merchantNo.getText()+"9");
                    break;
                case R.id.removeBtn:
                    if (merchantNo.getText().length()>0){
                        merchantNo.setText(merchantNo.getText().toString().substring(0,merchantNo.getText().length()-1));
                    }
                    break;
                case R.id.btnreset:
                    merchantNo.setText("");
                    break;
                default:
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
            if (s.toString().matches("^[0-9]*$")) {
                if (!saveBtn.isEnabled())saveBtn.setEnabled(true);

            }
        }
    };

    /**
     * 密码输入界面跳转
     */
    private void setManagerPasswordFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        manager_password_fragment = new ManagerPasswordFragment();
        fragmentTransaction.replace(R.id.mainFragment,manager_password_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * POS机界面跳转
     */
    private void setManagerPosSignInFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        manager_password_fragment = new ManagerPosSignFragment();
        fragmentTransaction.replace(R.id.mainFragment,manager_password_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
