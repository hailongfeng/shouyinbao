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
import com.wizarpos.q1.cashier.Function.Functions;


/**
 * 余额交易详情
 * Created by lixinchun on 16/7/27.
 */
public class ICloudBalanceEnquiryDetailFragment extends Fragment {
    private View rootView;
    private LinearLayout rechargeBtn;
    private Button backBtn;
    private Fragment cashier_desk_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.icloud_balance_enquiry_detail_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backBtn = (Button) view.findViewById(R.id.backBtn);
        rechargeBtn = (LinearLayout) view.findViewById(R.id.rechargeBtn);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        rechargeBtn.setOnClickListener(buttonListener);
    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.rechargeBtn:
                    setManageCashFragment();
                    break;
            }
        }
    }

    /**
     * 跳转云账户收银充值界面
     */
    private void setManageCashFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (cashier_desk_fragment ==null) cashier_desk_fragment = new ManageCashFragment();
        fragmentTransaction.replace(R.id.mainFragment, cashier_desk_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", Functions.iclound_manage_recharge_code);
        cashier_desk_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
