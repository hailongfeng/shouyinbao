package com.wizarpos.q1.cashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wizarpos.q1.cashier.R;
import com.wizarpos.q1.cashier.Function.Functions;


/**
 * 预授权完成撤销确认UI
 * Created by lixinchun on 16/7/27.
 */
public class CardPreAuthorOkCancelConfirmFragment extends Fragment {

    private View rootView;
    private Button backBtn,crashCardConfirm,inputConfirm;
    private Fragment crash_card_pay_fragment,pay_manual_verify_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.card_pre_author_ok_cancel_confirm_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backBtn = (Button)view.findViewById(R.id.backHomeBtn);
        crashCardConfirm = (Button)view.findViewById(R.id.crashCardConfirm);
        inputConfirm = (Button)view.findViewById(R.id.inputConfirm);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        crashCardConfirm.setOnClickListener(buttonListener);
        inputConfirm.setOnClickListener(buttonListener);
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
                case R.id.crashCardConfirm:
                    //授权金额确认
                    setCrashCardPayFragment();
                    break;
                case R.id.inputConfirm:
                    //授权金额确认
                    setPayManualVerifyFragment();
                    break;
            }
        }
    }

    /**
     * 刷卡界面跳转
     */
    private void setCrashCardPayFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (crash_card_pay_fragment==null)crash_card_pay_fragment = new CrashCardPayFragment();
        fragmentTransaction.replace(R.id.mainFragment,crash_card_pay_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", Functions.pre_author_cancel_ok);
        crash_card_pay_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 刷卡界面跳转
     */
    private void setPayManualVerifyFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (pay_manual_verify_fragment==null)pay_manual_verify_fragment = new PayManualVerifyFragment();
        fragmentTransaction.replace(R.id.mainFragment,pay_manual_verify_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", Functions.pre_author_cancel_ok);
        pay_manual_verify_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
