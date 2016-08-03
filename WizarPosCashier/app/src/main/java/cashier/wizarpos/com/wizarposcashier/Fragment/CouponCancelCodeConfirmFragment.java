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
import android.widget.LinearLayout;

import cashier.wizarpos.com.wizarposcashier.Function.Functions;
import cashier.wizarpos.com.wizarposcashier.R;


/**
 * 优惠券码验证界面
 * Created by lixinchun on 16/7/27.
 */
public class CouponCancelCodeConfirmFragment extends Fragment {
    private View rootView;
    private EditText pv;
    private LinearLayout numberKeyBoard;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnreset,btn0,btnback,cancelBtn,btnConfirm,payBtn,scanCodeBtn;
    private Fragment pay_password_input_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.coupon_cancel_code_confirm_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        pv = (EditText) view.findViewById(R.id.payCode);
        numberKeyBoard = (LinearLayout)view.findViewById(R.id.numberLargekeyboard);
        scanCodeBtn = (Button)view.findViewById(R.id.scanCodeBtn);
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
        btnback = (Button)view.findViewById(R.id.removeBtn);
        btnConfirm = (Button)view.findViewById(R.id.btnconfirm);
        payBtn =  (Button)view.findViewById(R.id.payBtn);
        ButtonListener buttonListener = new ButtonListener();
        scanCodeBtn.setOnClickListener(buttonListener);
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
        btnback.setOnClickListener(buttonListener);
        btnreset.setOnClickListener(buttonListener);
        btnConfirm.setOnClickListener(buttonListener);
        payBtn.setOnClickListener(buttonListener);
        pv.setOnClickListener(buttonListener);

    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.scanCodeBtn:
                    //扫码按钮
                    setScanCodePayFragment();
                    break;
                case R.id.payCode:
                    numberKeyBoard.setVisibility(View.VISIBLE);
                    Log.w("单击成功！","");
                    break;
                case R.id.cancelBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.btnconfirm:
                    payBtn.setEnabled(true);
                    break;
                case R.id.payBtn:
                    //券编号正确－跳往优惠券核销详细界面
                    if (pv.getText().toString().equals("1234")){
                        setCouponCancelCodeDetailFragment();
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
            if (s.toString().matches("^[0-9]*$")&&s.toString().length()==6&&s.toString().equals("123456")) {

            }
        }
    };

    /**
     * 输入密码页面
     */
    private void setCouponCancelCodeDetailFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (pay_password_input_fragment==null)pay_password_input_fragment = new CouponCancelCodeDetailFragment();
        fragmentTransaction.replace(R.id.mainFragment,pay_password_input_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 跳往扫码页面
     */
    private void setScanCodePayFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        pay_password_input_fragment = new ScanCodePayFragment();
        fragmentTransaction.replace(R.id.mainFragment,pay_password_input_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", Functions.coupon_cancel_code);
        pay_password_input_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
