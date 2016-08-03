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
 * Created by lixinchun on 16/7/27.
 */
public class CardPreAuthorConfirmFragment extends Fragment {
    private View rootView;
    private Button backBtn, confirmBtn;
    private EditText tradeNumber,amountText;
    private Fragment pay_password_input_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.card_pre_author_confirm_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backBtn = (Button)view.findViewById(R.id.backBtn);
        confirmBtn = (Button)view.findViewById(R.id.confirmBtn);
        tradeNumber = (EditText)view.findViewById(R.id.tradeNumber);
        amountText = (EditText)view.findViewById(R.id.amountText);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        confirmBtn.setOnClickListener(buttonListener);
        tradeNumber.addTextChangedListener(textWatcher);
        amountText.addTextChangedListener(textWatcher);
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
                case R.id.confirmBtn:
                    //授权金额确认
                    if (!tradeNumber.getText().toString().equals("")&&!amountText.getText().toString().equals("")){
                        setPayPasswordInputFragment();
                    } else{
                        amountText.setText("");
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
            if (!s.toString().matches("^[0-9]*$")) {
                amountText.setText("");
            }
        }
    };

    /**
     * 密码输入界面跳转
     */
    private void setPayPasswordInputFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        pay_password_input_fragment = new PayPasswordInputFragment();
        fragmentTransaction.replace(R.id.mainFragment,pay_password_input_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
