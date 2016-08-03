package cashier.wizarpos.com.wizarposcashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cashier.wizarpos.com.wizarposcashier.R;


/**
 * 银行卡退货刷卡
 * Created by lixinchun on 16/7/27.
 */
public class TradeReturnGoodCrashCardFragment extends Fragment {
    private View rootView;
    private Button scanCodeBtn,crashCardBtn,backBtn;
    private Fragment trade_return_good_card_confirm_fragment,trade_return_good_code_confirm_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.trade_return_good_crash_card_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    public void initView(View view){
        backBtn = (Button) view.findViewById(R.id.backBtn);
        scanCodeBtn = (Button) view.findViewById(R.id.scanCodeBtn);
        crashCardBtn = (Button) view.findViewById(R.id.crashCardBtn);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        scanCodeBtn.setOnClickListener(buttonListener);
        crashCardBtn.setOnClickListener(buttonListener);

    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.scanCodeBtn:
                    setTradeReturnGoodCodeConfirmFragment();
                    break;
                case R.id.crashCardBtn:
                    //获取银行卡信息后，跳转银行卡退货页面
                    setTradeReturnGoodCardConfirmFragment();
                    break;
            }
        }
    }

    private void initFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    /**
     * 退货银行刷卡验证页面
     */
    private void setTradeReturnGoodCardConfirmFragment() {
        initFragment();
        if (trade_return_good_card_confirm_fragment ==null) trade_return_good_card_confirm_fragment = new TradeReturnGoodCardConfirmFragment();
        fragmentTransaction.replace(R.id.mainFragment, trade_return_good_card_confirm_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 退货扫码验证页面
     */
    private void setTradeReturnGoodCodeConfirmFragment() {
        initFragment();
        if (trade_return_good_code_confirm_fragment ==null) trade_return_good_code_confirm_fragment = new TradeReturnGoodCodeConfirmFragment();
        fragmentTransaction.replace(R.id.mainFragment, trade_return_good_code_confirm_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
