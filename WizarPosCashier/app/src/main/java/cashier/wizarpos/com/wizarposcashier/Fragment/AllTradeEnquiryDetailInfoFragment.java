package cashier.wizarpos.com.wizarposcashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

import cashier.wizarpos.com.wizarposcashier.Model.TradeDetail;
import cashier.wizarpos.com.wizarposcashier.R;


/**
 * 交易查询详情界面
 * Created by lixinchun on 16/7/27.
 */
public class AllTradeEnquiryDetailInfoFragment extends Fragment {
    private int id = 0;
    private View rootView;
    private LinearLayout backHomeBtn;
    private Button operateBtn,lastTradeBtn,nextTradeBtn;
    private TextView tradeType,tradeTime,tradeSucessStatus,tradeCancelStatus,tradePrice,tradeNumber,tradeOperator;
    private Fragment cashier_desk_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.all_trade_enquiry_detail_info_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){

        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        Log.w("有交易数据",id+"");
        backHomeBtn = (LinearLayout) view.findViewById(R.id.backHomeBtn);
        operateBtn = (Button) view.findViewById(R.id.operateBtn);
        lastTradeBtn = (Button) view.findViewById(R.id.lastTradeBtn);
        nextTradeBtn = (Button) view.findViewById(R.id.nextTradeBtn);
        tradeType = (TextView) view.findViewById(R.id.tradeType);
        tradeTime = (TextView) view.findViewById(R.id.tradeTime);
        tradeSucessStatus = (TextView) view.findViewById(R.id.tradeSucessStatus);
        tradeCancelStatus = (TextView) view.findViewById(R.id.tradeCancelStatus);
        tradePrice = (TextView) view.findViewById(R.id.tradePrice);
        tradeNumber = (TextView) view.findViewById(R.id.tradeNumber);
        tradeOperator = (TextView) view.findViewById(R.id.tradeOperator);
        setNewTradeDetailFragment(id);
        ButtonListener buttonListener = new ButtonListener();
        backHomeBtn.setOnClickListener(buttonListener);
        operateBtn.setOnClickListener(buttonListener);
        lastTradeBtn.setOnClickListener(buttonListener);
        nextTradeBtn.setOnClickListener(buttonListener);


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
                case R.id.operateBtn:
//                    setCashierDeskFragment();
                    break;
                case R.id.lastTradeBtn:
//                    setCashierDeskFragment();
                        id--;
                        if (!setNewTradeDetailFragment(id)){
                            lastTradeBtn.setEnabled(false);
                            nextTradeBtn.setEnabled(true);
                        }
                        nextTradeBtn.setEnabled(true);
                    break;
                case R.id.nextTradeBtn:
//                    setCashierDeskFragment();
                        id++;
                    if (!setNewTradeDetailFragment(id)){
                        lastTradeBtn.setEnabled(true);
                        nextTradeBtn.setEnabled(false);
                    }
                    lastTradeBtn.setEnabled(true);
                    break;
            }
        }
    }
    /**
     * 向上，向下查阅交易详细
     */
    private boolean setNewTradeDetailFragment(int id) {
        Log.w("有交易数据","");
        Map<String,String> tradeDetail = TradeDetail.getTradeDetailById(id);
        if (tradeDetail!=null){

            if (tradeDetail.get("tradeStatus").equals("交易成功")){
                tradeSucessStatus.setVisibility(View.VISIBLE);
                tradeCancelStatus.setVisibility(View.GONE);
            }else if (tradeDetail.get("tradeStatus").equals("交易失败")){
                tradeCancelStatus.setVisibility(View.VISIBLE);
                tradeSucessStatus.setVisibility(View.GONE);
            }
            tradeNumber.setText(tradeDetail.get("tradeNumber"));
            tradeType.setText(tradeDetail.get("tradeType"));
            tradeTime.setText(tradeDetail.get("tradeTime"));
            tradePrice.setText(tradeDetail.get("tradePrice"));
            tradeOperator.setText(tradeDetail.get("tradeOperator"));
            return true;
        }else{
            Log.w("无交易数据","");
            return false;
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
