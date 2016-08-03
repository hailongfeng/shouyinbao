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
import android.widget.Toast;

import com.wizarpos.q1.cashier.R;


/**
 * Created by lixinchun on 16/7/27.
 */
public class ManagerPosSignFragment extends Fragment {
    private View rootView;
    private Button backBtn,signQuickBtn;
    private EditText accountText,passwordText;
    private Fragment admin_setting_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.manager_pos_sign,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backBtn = (Button)view.findViewById(R.id.backBtn);
        signQuickBtn = (Button)view.findViewById(R.id.signQuickBtn);
        accountText = (EditText)view.findViewById(R.id.accountText);
        passwordText = (EditText)view.findViewById(R.id.passwordText);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        signQuickBtn.setOnClickListener(buttonListener);
        accountText.addTextChangedListener(textWatcher);
        passwordText.addTextChangedListener(textWatcher);
    }

    /**
     * 创建单击事件
     */
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.signQuickBtn:
                    if (accountText.getText().toString().contains("admin")&&passwordText.getText().toString().contains("123456")){
                        setAdminSettingsFragment();
                    }else if (accountText.getText().toString().contains("0001")&&passwordText.getText().toString().contains("123456")){
                        setManagerCashierDeskInFragment();
                    }
                    else{
                        accountText.setText("");
                        passwordText.setText("");
                        Toast.makeText(rootView.getContext(),"账户，密码错误",Toast.LENGTH_SHORT);
                    }
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
            if (s.length()>0){
                signQuickBtn.setEnabled(true);
            }
        }
    };

    /**
     * POS机界面跳转
     */
    private void setAdminSettingsFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        admin_setting_fragment = new AdminSettingFragment();
        fragmentTransaction.replace(R.id.mainFragment,admin_setting_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * POS机界面跳转
     */
    private void setManagerCashierDeskInFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        admin_setting_fragment = new CashierDeskFragment();
        fragmentTransaction.replace(R.id.mainFragment,admin_setting_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
