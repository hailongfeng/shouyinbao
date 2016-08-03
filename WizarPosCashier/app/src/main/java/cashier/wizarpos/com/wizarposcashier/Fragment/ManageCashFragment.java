package cashier.wizarpos.com.wizarposcashier.Fragment;

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
import android.widget.TextView;

import cashier.wizarpos.com.wizarposcashier.Function.Functions;
import cashier.wizarpos.com.wizarposcashier.R;
import cashier.wizarpos.com.wizarposcashier.Util.CaculateUtil;


/**
 * 收银主管界面
 * Created by lixinchun on 16/7/27.
 */
public class ManageCashFragment extends Fragment {
    private int code = Functions.cash_manage_code;
    private TextView themeTitle;
    private View rootView;
    private EditText amountText;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn00,btn0,btnback,backBtn,crashCardBtn,scanCodeBtn,scanMainBtn;
    private Fragment scan_code_pay_fragment,crash_code_pay_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.manage_cash_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        amountText = (EditText) view.findViewById(R.id.amountText);
        themeTitle = (TextView) view.findViewById(R.id.themeTitle);
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
        btn00 = (Button)view.findViewById(R.id.btn00);
        btnback = (Button)view.findViewById(R.id.btnback);
        crashCardBtn = (Button)view.findViewById(R.id.crashCardBtn);
        scanCodeBtn = (Button)view.findViewById(R.id.scanCodeBtn);
        scanMainBtn = (Button)view.findViewById(R.id.scanMainBtn);
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
        btnback.setOnClickListener(buttonListener);
        btn00.setOnClickListener(buttonListener);
        crashCardBtn.setOnClickListener(buttonListener);
        scanCodeBtn.setOnClickListener(buttonListener);
        scanMainBtn.setOnClickListener(buttonListener);
        Bundle bundle = getArguments();
        if (bundle!=null){
            code = bundle.getInt("code");
            switch (code){
                case Functions.cash_manage_code:
                    themeTitle.setText("收银");
                    break;
                case Functions.iclound_manage_recharge_code:
                    themeTitle.setText("云账户充值");
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
                case R.id.crashCardBtn:
                    setCrashCardPayFragment(code);
                    break;
                case R.id.scanCodeBtn:
                    setManagerScanCodeFragment();
                    break;
                case R.id.scanMainBtn:
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
                case R.id.btnback:
                    if (amountText.getText().length()>0){
                        amountText.setText(amountText.getText().toString().substring(0,amountText.getText().length()-1));
                    }
                    break;
                case R.id.btn00:
                    amountText.setText(amountText.getText()+"00");
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
     * 扫码支付界面
     */
    private void setManagerScanCodeFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        scan_code_pay_fragment = new ScanCodePayFragment();
        fragmentTransaction.replace(R.id.mainFragment, scan_code_pay_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 刷卡支付界面
     */
    private void setCrashCardPayFragment(int code){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        crash_code_pay_fragment = new CrashCardPayFragment();
        fragmentTransaction.replace(R.id.mainFragment, crash_code_pay_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", code);
        crash_code_pay_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



}
