package com.wizarpos.q1.cashier.Fragment;

import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.wizarpos.q1.cashier.R;
import com.wizarpos.q1.cashier.Model.TradeDetail;


/**
 * 最近一笔交易详情
 * Created by lixinchun on 16/7/27.
 */
public class LastTradeEnquiryDetailInfoFragment extends Fragment {
    private View rootView;
    private LinearLayout backHomeBtn;
    private Button backBtn,tradeSuccessStatus,tradeCancelStatus;
    private Fragment cashier_desk_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.last_trade_enquiry_detail_info_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backBtn = (Button) view.findViewById(R.id.backBtn);
        backHomeBtn = (LinearLayout) view.findViewById(R.id.backHomeBtn);
        tradeSuccessStatus = (Button) view.findViewById(R.id.tradeSuccessStatus);
        tradeCancelStatus = (Button) view.findViewById(R.id.tradeCancelStatus);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        backHomeBtn.setOnClickListener(buttonListener);
        Map<String,String> map = TradeDetail.getLastTradeDetail();
        if (map.get("tradeStatus").equals("交易成功")){
            tradeSuccessStatus.setVisibility(View.VISIBLE);
            tradeCancelStatus.setVisibility(View.GONE);

        }else if(map.get("tradeStatus").equals("交易失败")){
            tradeCancelStatus.setVisibility(View.VISIBLE);
            tradeSuccessStatus.setVisibility(View.GONE);
        }


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
     * 跳转应用主界面
     */
    private void setCashierDeskFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        cashier_desk_fragment = new CashierDeskFragment();
        fragmentTransaction.replace(R.id.mainFragment, cashier_desk_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 创建ListView adapter
     * @return
     */
    private ListAdapter createListAdapter(List<Map<String,String>> list) {
        String [] names = {"tradeNumber"};
        int[] ids = new int[]{R.id.tradeNumber};
        SimpleAdapter simpleAdapter = new SimpleAdapter(rootView.getContext(), list, R.layout.all_trade_enquiry_code_detail_list_item, names, ids);
        return simpleAdapter;
    }


}
