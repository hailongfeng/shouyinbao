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
 * 刷卡支付
 * Created by lixinchun on 16/7/27.
 */
public class CardPreAuthorPayFragment extends Fragment {
    private View rootView;
    private Button crashCardBtn,backBtn;
    private Fragment pre_author_confirm_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.card_pre_author_pay_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        backBtn = (Button) view.findViewById(R.id.backBtn);
        crashCardBtn = (Button) view.findViewById(R.id.crashCardBtn);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        crashCardBtn.setOnClickListener(buttonListener);
    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.crashCardBtn:
                    //获取银行卡信息后，跳转预授权确认页面
                    setCardPreAuthorConfirmFragment();
                    break;
            }
        }
    }

    private void initFragment() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    /**
     * 创建预授权确认界面
     */
    private void setCardPreAuthorConfirmFragment() {
        initFragment();
        if (pre_author_confirm_fragment ==null) pre_author_confirm_fragment = new CardPreAuthorConfirmFragment();
        fragmentTransaction.replace(R.id.mainFragment, pre_author_confirm_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



}
