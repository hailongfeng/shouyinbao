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

import cashier.wizarpos.com.wizarposcashier.R;
import cashier.wizarpos.com.wizarposcashier.View.PasswordInputView;


/**
 * Created by lixinchun on 16/7/27.
 */
public class ManagerPasswordFragment extends Fragment {
    private View rootView;
    private PasswordInputView pv;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnreset,btn0,btnback,backBtn;
    private Fragment manager_add_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.manager_password_enter,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
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
        btnback = (Button)view.findViewById(R.id.btnback);
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
        btnreset.setOnClickListener(buttonListener);
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
                case R.id.btnback:
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
                setManagerPasswordFragment();
            }
        }
    };

    private void setManagerPasswordFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        manager_add_fragment = new ManagerAddFragment();
        fragmentTransaction.replace(R.id.mainFragment,manager_add_fragment);
        fragmentTransaction.commit();
    }

}
