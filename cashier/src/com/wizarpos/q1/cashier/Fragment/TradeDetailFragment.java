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
 * 交易详情
 * Created by lixinchun on 16/7/27.
 */
public class TradeDetailFragment extends Fragment {
    private View rootView;
    private LinearLayout backHomeBtn;
    private Button operateBtn;
    private Fragment cashier_desk_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.trade_detail_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backHomeBtn = (LinearLayout) view.findViewById(R.id.backHomeBtn);
        operateBtn = (Button) view.findViewById(R.id.operateBtn);
        ButtonListener buttonListener = new ButtonListener();
        backHomeBtn.setOnClickListener(buttonListener);
        operateBtn.setOnClickListener(buttonListener);
    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.backHomeBtn:
                    setCashierDeskFragment();
                    break;
            }
        }
    }

    /**
     * 创建收银界面
     */
    private void setCashierDeskFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (cashier_desk_fragment ==null) cashier_desk_fragment = new CashierDeskFragment();
        fragmentTransaction.replace(R.id.mainFragment, cashier_desk_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
