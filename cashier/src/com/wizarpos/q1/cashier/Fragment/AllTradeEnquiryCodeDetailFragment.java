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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wizarpos.q1.cashier.R;
import com.wizarpos.q1.cashier.Model.TradeDetail;


/**
 * 交易详情
 * Created by lixinchun on 16/7/27.
 */
public class AllTradeEnquiryCodeDetailFragment extends Fragment {
    private View rootView;
    private ListView codeNumberList;
    private Button backBtn,noCodeNumberBtn;
    private Fragment cashier_desk_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private List<Map<String,String>> list = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.all_trade_enquiry_code_detail_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backBtn = (Button) view.findViewById(R.id.backBtn);

        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        Bundle bundle = getArguments();
        String code = bundle.getString("tradeNumber");
        if (!code.equals("")){
            list = TradeDetail.getTradeDetailByTradeNumber(code);
            if (list!=null&&list.size()>0){
                codeNumberList = (ListView) view.findViewById(R.id.codeNumberList);
                codeNumberList.setVisibility(View.VISIBLE);
                codeNumberList.setAdapter(createListAdapter(list));
                //单击查询出的凭证号／交易单号查询详细
                codeNumberList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        setAllTradeEnquiryDetailInfoFragment(Integer.parseInt(list.get(i).get("id")));
                    }
                });
            }else{
                noCodeNumberBtn = (Button) view.findViewById(R.id.noCodeNumberBtn);
                noCodeNumberBtn.setVisibility(View.VISIBLE);
                noCodeNumberBtn.setOnClickListener(buttonListener);
            }
        }

    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.noCodeNumberBtn:
                    getFragmentManager().popBackStack();
                    break;

            }
        }
    }

    /**
     * 跳转交易详情界面
     */
    private void setAllTradeEnquiryDetailInfoFragment(int id) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        cashier_desk_fragment = new AllTradeEnquiryDetailInfoFragment();
        fragmentTransaction.replace(R.id.mainFragment, cashier_desk_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        cashier_desk_fragment.setArguments(bundle);
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
