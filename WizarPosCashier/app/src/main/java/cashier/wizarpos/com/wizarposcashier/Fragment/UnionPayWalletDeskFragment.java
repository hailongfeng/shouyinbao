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
 * 银联钱包主界面
 * Created by lixinchun on 16/7/27.
 */
public class UnionPayWalletDeskFragment extends Fragment {
    private View rootView;
    private Button backBtn, couponBtn, eticketBtn, scoreBtn, cancelBtn,returnGoodBtn;
    private Fragment card_pre_author_pay_fragment,card_pre_author_ok_pay_fragment,card_pre_author_cancel_fragment,card_pre_author_ok_cancel_fragment,fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.unionpay_wallet_desk_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backBtn = (Button) view.findViewById(R.id.backBtn);
        couponBtn = (Button) view.findViewById(R.id.couponBtn);
        eticketBtn = (Button) view.findViewById(R.id.eticketBtn);
        scoreBtn = (Button) view.findViewById(R.id.scoreBtn);
        cancelBtn = (Button) view.findViewById(R.id.cancelBtn);
        returnGoodBtn = (Button) view.findViewById(R.id.returnGoodBtn);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        couponBtn.setOnClickListener(buttonListener);
        eticketBtn.setOnClickListener(buttonListener);
        scoreBtn.setOnClickListener(buttonListener);
        cancelBtn.setOnClickListener(buttonListener);
        returnGoodBtn.setOnClickListener(buttonListener);
    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.couponBtn:
                    setUnionPayCouponInputPriceFragment();
                    break;
                case R.id.eticketBtn:
                    break;
                case R.id.scoreBtn:
                    setCardPreAuthorCancelFragment();
                    break;
                case R.id.cancelBtn:
                    //银联钱包撤销
                    setUnionPayWalletCancelFragment(Functions.union_pay_wallet_cancel_code);
                    break;
                case R.id.returnGoodBtn:
                    //银联钱包退货
                    setUnionPayWalletCancelFragment(Functions.union_pay_wallet_return_good_code);
                    break;
            }
        }
    }

    private void initFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    /**
     * 创建银联钱包撤销界面
     */
    private void setUnionPayCouponInputPriceFragment() {
        initFragment();
        if (card_pre_author_pay_fragment ==null) card_pre_author_pay_fragment = new UnionPayCouponInputPriceFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_pay_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 创建银联钱包撤销界面-输入管理员密码
     */
    private void setUnionPayWalletCancelFragment(int code) {
        initFragment();
        card_pre_author_cancel_fragment = new CardPreAuthorOkCancelFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_cancel_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", code);
        card_pre_author_cancel_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 创建授权撤销界面
     */
    private void setCardPreAuthorCancelFragment() {
        initFragment();
        if (card_pre_author_cancel_fragment ==null) card_pre_author_cancel_fragment = new CardPreAuthorCancelFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_cancel_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 创建授权撤销完成界面
     */
    private void setCardPreAuthorOkCancelFragment() {
        initFragment();
        if (card_pre_author_ok_cancel_fragment ==null) card_pre_author_ok_cancel_fragment = new CardPreAuthorOkCancelFragment();
        fragmentTransaction.replace(R.id.mainFragment, card_pre_author_ok_cancel_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("code", Functions.pre_author_ok_cancel_code);
        card_pre_author_ok_cancel_fragment.setArguments(bundle);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
