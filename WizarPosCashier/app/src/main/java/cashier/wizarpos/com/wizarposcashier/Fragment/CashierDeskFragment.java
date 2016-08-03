package cashier.wizarpos.com.wizarposcashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cashier.wizarpos.com.wizarposcashier.Function.Functions;
import cashier.wizarpos.com.wizarposcashier.R;


/**
 * Created by lixinchun on 16/7/27.
 */
public class CashierDeskFragment extends Fragment {
    private View rootView;
    private Button manageBtn,grantBtn,cancelBtn,refundBtn,balanceBtn,settleBtn,allTradeSelectBtn,lastTradeSelectBtn,cashImBtn,unionPayImBtn,couponPayImBtn,orderPayImBtn,cloudPayImBtn;
    private Fragment manage_admin_fragment,manage_cash_fragment,card_pre_author_desk_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.cashier_desk_main,container,false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view){
        manageBtn = (Button)view.findViewById(R.id.manageBtn);
        grantBtn = (Button)view.findViewById(R.id.grantBtn);
        cancelBtn = (Button)view.findViewById(R.id.cancelBtn);
        refundBtn = (Button)view.findViewById(R.id.refundBtn);
        balanceBtn = (Button)view.findViewById(R.id.balanceBtn);
        settleBtn = (Button)view.findViewById(R.id.settleBtn);
        allTradeSelectBtn = (Button)view.findViewById(R.id.allTradeSelectBtn);
        lastTradeSelectBtn = (Button)view.findViewById(R.id.lastTradeSelectBtn);
        cashImBtn = (Button) view.findViewById(R.id.cashImBtn);
        unionPayImBtn = (Button) view.findViewById(R.id.unionPayImBtn);
        couponPayImBtn = (Button) view.findViewById(R.id.couponPayImBtn);
        orderPayImBtn = (Button) view.findViewById(R.id.orderPayImBtn);
        cloudPayImBtn = (Button) view.findViewById(R.id.cloudPayImBtn);
        ButtonListener buttonListener = new ButtonListener();
        manageBtn.setOnClickListener(buttonListener);
        grantBtn.setOnClickListener(buttonListener);
        cancelBtn.setOnClickListener(buttonListener);
        refundBtn.setOnClickListener(buttonListener);
        balanceBtn.setOnClickListener(buttonListener);
        settleBtn.setOnClickListener(buttonListener);
        allTradeSelectBtn.setOnClickListener(buttonListener);
        lastTradeSelectBtn.setOnClickListener(buttonListener);
        cashImBtn.setOnClickListener(buttonListener);
        unionPayImBtn.setOnClickListener(buttonListener);
        couponPayImBtn.setOnClickListener(buttonListener);
        orderPayImBtn.setOnClickListener(buttonListener);
        cloudPayImBtn.setOnClickListener(buttonListener);
    }

    /**
     * 单击事件
     */
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.manageBtn:
                    setManagerAdminFragment();
                    break;
                case R.id.grantBtn:
                    setCardPreAuthorizationDeskFragment();
                    break;
                case R.id.cancelBtn:
                    setInputAdminPasswordFragment(Functions.trade_cancel_code);
                    break;
                case R.id.refundBtn:
                    setInputAdminPasswordFragment(Functions.trade_return_goods_code);
                    break;
                case R.id.balanceBtn:
                    setCrashCardPayFragment(Functions.balance_enquiry_code);
                    break;
                case R.id.settleBtn:
                    break;
                case R.id.allTradeSelectBtn:
                    setAllTradeEnquiryFragment();
                    break;
                case R.id.lastTradeSelectBtn:
                    setLastTradeEnquiryDetailInfoFragment();
                    break;
                case R.id.cashImBtn:
                    setManagerCashFragment();
                    break;
                case R.id.unionPayImBtn:
                    setUnionPayWalletFragment();
                    break;
                case R.id.couponPayImBtn:
                    //优惠券撤销
                    setCouponCancelCodeConfirmFragment();
                    break;
                case R.id.orderPayImBtn:
                    break;
                case R.id.cloudPayImBtn:
                    //云账户充值
                    setInputAdminPasswordFragment(Functions.iclound_manage_code);
                    break;
                default:
                    break;

            }
        }
    }

    private void initFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    /**
     * 创建管理员界面
     */
    private void setManagerAdminFragment() {
        initFragment();
        if (manage_admin_fragment ==null) manage_admin_fragment = new ManageAdminFragment();
        fragmentTransaction.replace(R.id.mainFragment, manage_admin_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 创建收银界面
     */
    private void setManagerCashFragment() {
        initFragment();
        if (manage_cash_fragment ==null) manage_cash_fragment = new ManageCashFragment();
        fragmentTransaction.replace(R.id.mainFragment, manage_cash_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 预授权界面
     */
    private void setCardPreAuthorizationDeskFragment(){
        initFragment();
        card_pre_author_desk_fragment = new CardPreAuthorizationDeskFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_desk_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    /**
     * 交易撤销－管理员密码界面
     */
    private void setInputAdminPasswordFragment(int code){
        initFragment();
        card_pre_author_desk_fragment = new CardPreAuthorOkCancelFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("code", code);
        card_pre_author_desk_fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_desk_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 刷卡支付接口(余额查询功能)
     */
    private void setCrashCardPayFragment(int code){
        initFragment();
        card_pre_author_desk_fragment = new CrashCardPayFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("code", code);
        card_pre_author_desk_fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_desk_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 银联钱包UI(余额查询功能)
     */
    private void setUnionPayWalletFragment(){
        initFragment();
        card_pre_author_desk_fragment = new UnionPayWalletDeskFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_desk_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 优惠券UI(余额查询功能)
     */
    private void setCouponCancelCodeConfirmFragment(){
        initFragment();
        card_pre_author_desk_fragment = new CouponCancelCodeConfirmFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_desk_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 所有交易查询接口UI
     */
    private void setAllTradeEnquiryFragment(){
        initFragment();
        card_pre_author_desk_fragment = new AllTradeEnquiryFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_desk_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 末笔交易查询接口UI
     */
    private void setLastTradeEnquiryDetailInfoFragment(){
        initFragment();
        card_pre_author_desk_fragment = new LastTradeEnquiryDetailInfoFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_desk_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
