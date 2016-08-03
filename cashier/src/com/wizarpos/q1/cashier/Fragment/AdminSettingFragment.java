package com.wizarpos.q1.cashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wizarpos.q1.cashier.R;


/**
 * 管理员管理界面
 * Created by lixinchun on 16/7/27.
 */
public class AdminSettingFragment extends Fragment {
    private View rootView;
    private Button backBtn;
    private LinearLayout merchantSet,tradeParamSet,systemSet,communicationSet,passwordSet,elseSet,initSet,versionSet;
    private Fragment manager_password_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.admin_settings_ui,container,false);
        initView(rootView);
        return rootView;
    }

    /**
     * 初始化
     * @param view
     */
    private void initView(View view){
        backBtn = (Button)view.findViewById(R.id.backBtn);
        merchantSet = (LinearLayout)view.findViewById(R.id.merchantSet);
        tradeParamSet = (LinearLayout)view.findViewById(R.id.tradeParamSet);
        systemSet = (LinearLayout)view.findViewById(R.id.systemSet);
        communicationSet = (LinearLayout)view.findViewById(R.id.communicationSet);
        passwordSet = (LinearLayout)view.findViewById(R.id.passwordSet);
        elseSet = (LinearLayout)view.findViewById(R.id.elseSet);
        initSet = (LinearLayout)view.findViewById(R.id.initSet);
        versionSet = (LinearLayout)view.findViewById(R.id.versionSet);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        merchantSet.setOnClickListener(buttonListener);
        tradeParamSet.setOnClickListener(buttonListener);
        systemSet.setOnClickListener(buttonListener);
        communicationSet.setOnClickListener(buttonListener);
        passwordSet.setOnClickListener(buttonListener);
        elseSet.setOnClickListener(buttonListener);
        initSet.setOnClickListener(buttonListener);
        versionSet.setOnClickListener(buttonListener);
    }

    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.merchantSet:
                    setAdminParameterSettingFragment();
                    break;
                case R.id.tradeParamSet:
                    break;
                case R.id.systemSet:
                    break;
                case R.id.communicationSet:
                    break;
                case R.id.passwordSet:
                    break;
                case R.id.elseSet:
                    break;
                case R.id.initSet:
                    break;
                case R.id.versionSet:
                    break;

            }
        }
    }


    /**
     * 商户参数设置界面跳转
     */
    private void setAdminParameterSettingFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        manager_password_fragment = new AdminParameterSettingFragment();
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
