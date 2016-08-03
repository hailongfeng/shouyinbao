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
 * 交易详情
 * Created by lixinchun on 16/7/27.
 */
public class UnionPayCouponTradeDetailConfirmFragment extends Fragment {
    private View rootView;
    private Button backBtn,confirmBtn;
    private Fragment cashier_desk_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.unionpay_coupon_trade_detail_confirm_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        confirmBtn = (Button) view.findViewById(R.id.confirmBtn);
        backBtn = (Button) view.findViewById(R.id.backBtn);
        ButtonListener buttonListener = new ButtonListener();
        confirmBtn.setOnClickListener(buttonListener);
        backBtn.setOnClickListener(buttonListener);
    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.confirmBtn:
                    setPayPasswordInputFragment();
                    break;
            }
        }
    }

    /**
     * 输入支付密码界面
     */
    private void setPayPasswordInputFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (cashier_desk_fragment ==null) cashier_desk_fragment = new PayPasswordInputFragment();
        fragmentTransaction.replace(R.id.mainFragment, cashier_desk_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
