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
 * Created by lixinchun on 16/7/27.
 */
public class CardPreAuthorizationDeskFragment extends Fragment {
    private View rootView;
    private Button backBtn,preBtn,preOkBtn,cancelBtn,cancelOkBtn;
    private Fragment card_pre_author_pay_fragment,card_pre_author_ok_pay_fragment,card_pre_author_cancel_fragment,card_pre_author_ok_cancel_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.card_pre_authorization_desk_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backBtn = (Button) view.findViewById(R.id.backBtn);
        preBtn = (Button) view.findViewById(R.id.preBtn);
        preOkBtn = (Button) view.findViewById(R.id.preOkBtn);
        cancelBtn = (Button) view.findViewById(R.id.cancelBtn);
        cancelOkBtn = (Button) view.findViewById(R.id.cancelOkBtn);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        preBtn.setOnClickListener(buttonListener);
        preOkBtn.setOnClickListener(buttonListener);
        cancelBtn.setOnClickListener(buttonListener);
        cancelOkBtn.setOnClickListener(buttonListener);

    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.preBtn:
                    setCardAuthorPayFragment();
                    break;
                case R.id.preOkBtn:
                    setCardAuthorOkPayFragment();
                    break;
                case R.id.cancelBtn:
                    setCardPreAuthorCancelFragment();
                    break;
                case R.id.cancelOkBtn:
                    setCardPreAuthorOkCancelFragment();
                    break;
            }
        }
    }

    private void initFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    /**
     * 创建授权刷卡界面
     */
    private void setCardAuthorPayFragment() {
        initFragment();
        if (card_pre_author_pay_fragment ==null) card_pre_author_pay_fragment = new CardPreAuthorPayFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_pay_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 创建授权完成刷卡界面
     */
    private void setCardAuthorOkPayFragment() {
        initFragment();
        if (card_pre_author_ok_pay_fragment ==null) card_pre_author_ok_pay_fragment = new CardPreAuthorOkPayFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_ok_pay_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 创建授权撤销界面
     */
    private void setCardPreAuthorCancelFragment() {
        initFragment();
        if (card_pre_author_cancel_fragment ==null) card_pre_author_cancel_fragment = new CardPreAuthorCancelFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_cancel_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 创建授权撤销完成界面
     */
    private void setCardPreAuthorOkCancelFragment() {
        initFragment();
        if (card_pre_author_ok_cancel_fragment ==null) card_pre_author_ok_cancel_fragment = new CardPreAuthorOkCancelFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_ok_cancel_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", Functions.pre_author_ok_cancel_code);
        card_pre_author_ok_cancel_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
