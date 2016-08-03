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
import android.widget.DatePicker;
import android.widget.EditText;

import com.wizarpos.q1.cashier.R;


/**
 * 交易退货刷卡支付界面
 * Created by lixinchun on 16/7/27.
 */
public class TradeReturnGoodCardConfirmFragment extends Fragment {
    private View rootView;
    private Button backBtn, confirmBtn;
    private DatePicker tradeDatePick;
    private EditText tradeNumber,amountText,authorCode;
    private Fragment trade_detail_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.trade_return_good_card_confirm_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        tradeDatePick = (DatePicker)view.findViewById(R.id.tradeDatePick);
        backBtn = (Button)view.findViewById(R.id.backBtn);
        confirmBtn = (Button)view.findViewById(R.id.confirmBtn);
        authorCode = (EditText) view.findViewById(R.id.authorCode);
        tradeNumber = (EditText)view.findViewById(R.id.tradeNumber);
        amountText = (EditText)view.findViewById(R.id.amountText);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        confirmBtn.setOnClickListener(buttonListener);
        authorCode.addTextChangedListener(textWatcher);
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
                    //确认退货按钮，跳转支付详细页面
                    if (!tradeNumber.getText().toString().equals("")&&!amountText.getText().toString().equals("")
                            &&authorCode.getText().toString().equals("123456")){
                        setTradeDetailFragment();
                    } else{
                        amountText.setText("");
                    }
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
            Log.w("输入",s.toString());
            if (!s.toString().matches("^[0-9]*$")) {
                amountText.setText("");
            }
        }
    };

    /**
     * 交易付款详细界面跳转
     */
    private void setTradeDetailFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        trade_detail_fragment = new TradeDetailFragment();
        fragmentTransaction.replace(R.id.mainFragment, trade_detail_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
