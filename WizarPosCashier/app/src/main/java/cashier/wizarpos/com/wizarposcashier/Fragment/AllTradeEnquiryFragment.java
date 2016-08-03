package cashier.wizarpos.com.wizarposcashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cashier.wizarpos.com.wizarposcashier.Model.TradeDetail;
import cashier.wizarpos.com.wizarposcashier.R;


/**
 * 余额交易详情
 * Created by lixinchun on 16/7/27.
 */
public class AllTradeEnquiryFragment extends Fragment {
    private List<Map<String,String>> list;
    private ListView tradeDetailList;
    private TextView tradeAmount,tradeCount;
    private View rootView;
    private Button searchBtn;
    private LinearLayout backHomeBtn;
    private Fragment cashier_desk_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.all_trade_enquiry_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        list = TradeDetail.getTradeDetails();
        searchBtn = (Button) view.findViewById(R.id.searchBtn);
        backHomeBtn = (LinearLayout) view.findViewById(R.id.backHomeBtn);
        tradeDetailList = (ListView) view.findViewById(R.id.tradeDetailList);
        tradeAmount = (TextView) view.findViewById(R.id.tradeAmount);
        tradeCount = (TextView) view.findViewById(R.id.tradeCount);
        tradeCount.setText(list.size()+"");
        tradeAmount.setText(String.valueOf(TradeDetail.getTradeAmount()));
        ButtonListener buttonListener = new ButtonListener();
        backHomeBtn.setOnClickListener(buttonListener);
        searchBtn.setOnClickListener(buttonListener);
        tradeDetailList.setAdapter(createListAdapter());
        tradeDetailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.w("获取ID",list.get(i).get("id"));
                setAllTradeEnquiryDetailInfoFragment(Integer.parseInt(list.get(i).get("id")));
            }
        });
    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backHomeBtn:
                    setCashierDeskFragment();
                    break;
                case R.id.searchBtn:
                    setAllTradeEnquiryByCodeFragment();
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
        cashier_desk_fragment = new CashierDeskFragment();
        fragmentTransaction.replace(R.id.mainFragment, cashier_desk_fragment);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 跳转按凭证号／交易单号搜索界面
     */
    private void setAllTradeEnquiryByCodeFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        cashier_desk_fragment = new AllTradeEnquiryByCodeFragment();
        fragmentTransaction.replace(R.id.mainFragment, cashier_desk_fragment);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 创建ListView adapter
     * @return
     */
    private ListAdapter createListAdapter() {

        String [] names = {"tradeNumber","tradeTime","tradeStatus","tradePrice"};
        int[] ids = new int[]{R.id.tradeNumber, R.id.tradeTime, R.id.tradeStatus, R.id.tradePrice};

        SimpleAdapter simpleAdapter = new SimpleAdapter(rootView.getContext(), list, R.layout.all_trade_enquiry_list_item, names, ids);
        return simpleAdapter;
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

}
