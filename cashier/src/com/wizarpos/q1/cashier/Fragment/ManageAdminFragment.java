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
 * Created by lixinchun on 16/7/27.
 */
public class ManageAdminFragment extends Fragment {
    private View rootView;
    private Button backBtn,signInBtn;
    private LinearLayout cashierManage,passwordUpdate,printer,signOut;
    private Fragment manager_password_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.manage_admin_ui,container,false);
        initView(rootView);
        return rootView;
    }

    /**
     * 初始化
     * @param view
     */
    private void initView(View view){
        backBtn = (Button)view.findViewById(R.id.backBtn);
        signInBtn = (Button)view.findViewById(R.id.signInBtn);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        signInBtn.setOnClickListener(buttonListener);
        cashierManage = (LinearLayout)view.findViewById(R.id.cashierManage);
        passwordUpdate = (LinearLayout)view.findViewById(R.id.passwordUpdate);
        printer = (LinearLayout)view.findViewById(R.id.printer);
        signOut = (LinearLayout)view.findViewById(R.id.signOut);
        cashierManage.setOnClickListener(buttonListener);
        passwordUpdate.setOnClickListener(buttonListener);
        printer.setOnClickListener(buttonListener);
        signOut.setOnClickListener(buttonListener);
    }

    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.signInBtn:
                    setManagerPosSignInFragment();
                    break;
                case R.id.cashierManage:
                    break;
                case R.id.passwordUpdate:
                    setManagerPasswordFragment();
                    break;
                case R.id.printer:
                    break;
                case R.id.signOut:
                    break;

            }
        }
    }


    /**
     * 密码输入界面跳转
     */
    private void setManagerPasswordFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        manager_password_fragment = new ManagerPasswordFragment();
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
