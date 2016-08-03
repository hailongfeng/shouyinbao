package cashier.wizarpos.com.wizarposcashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cashier.wizarpos.com.wizarposcashier.Function.Functions;
import cashier.wizarpos.com.wizarposcashier.R;


/**
 * 消费撤销确认UI
 * Created by lixinchun on 16/7/27.
 */
public class TradeCancelCodeConfirmFragment extends Fragment {

    private View rootView;
    private Button backBtn, cancelConfirm;
    private Fragment pay_password_input_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.trade_cancel_code_confirm_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backBtn = (Button)view.findViewById(R.id.backHomeBtn);
        cancelConfirm = (Button)view.findViewById(R.id.cancelConfirm);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        cancelConfirm.setOnClickListener(buttonListener);
    }

    /**
     * 创建单击事件
     */
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backHomeBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.cancelConfirm:
                    //授权金额确认
                    setPayPasswordInputFragment();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 输入密码界面跳转
     */
    private void setPayPasswordInputFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (pay_password_input_fragment==null)pay_password_input_fragment = new PayPasswordInputFragment();
        fragmentTransaction.replace(R.id.mainFragment,pay_password_input_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", Functions.trade_cancel_code);
        pay_password_input_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



}
