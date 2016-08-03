package cashier.wizarpos.com.wizarposcashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import cashier.wizarpos.com.wizarposcashier.R;


/**
 * 管理员管理界面
 * Created by lixinchun on 16/7/27.
 */
public class AdminTradeSettingFragment extends Fragment {
    private View rootView;
    private Button backBtn;
    private LinearLayout tradeSwitchSet,tradePasswordSet,tradeCrashCardSet,settleAccountSet,otherTradeSet;
    private Fragment manager_password_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.admin_trade_settings_ui,container,false);
        initView(rootView);
        return rootView;
    }

    /**
     * 初始化
     * @param view
     */
    private void initView(View view){
        backBtn = (Button)view.findViewById(R.id.backBtn);
        tradeSwitchSet = (LinearLayout)view.findViewById(R.id.tradeSwitchSet);
        tradePasswordSet = (LinearLayout)view.findViewById(R.id.tradePasswordSet);
        tradeCrashCardSet = (LinearLayout)view.findViewById(R.id.tradeCrashCardSet);
        settleAccountSet = (LinearLayout)view.findViewById(R.id.settleAccountSet);
        otherTradeSet = (LinearLayout)view.findViewById(R.id.otherTradeSet);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        tradeSwitchSet.setOnClickListener(buttonListener);
        tradePasswordSet.setOnClickListener(buttonListener);
        tradeCrashCardSet.setOnClickListener(buttonListener);
        settleAccountSet.setOnClickListener(buttonListener);
        otherTradeSet.setOnClickListener(buttonListener);

    }

    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.tradeSwitchSet:
                    setAdminParameterSettingFragment();
                    break;
                case R.id.tradePasswordSet:
                    break;
                case R.id.tradeCrashCardSet:
                    break;
                case R.id.settleAccountSet:
                    break;
                case R.id.otherTradeSet:
                    break;
                default:
                    break;

            }
        }
    }


    /**
     * 商户参数设置界面跳转
     */
    private void setAdminParameterSettingFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        manager_password_fragment = new AdminParameterSettingFragment();
        fragmentTransaction.replace(R.id.mainFragment,manager_password_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * POS机界面跳转
     */
    private void setManagerPosSignInFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        manager_password_fragment = new ManagerPosSignFragment();
        fragmentTransaction.replace(R.id.mainFragment,manager_password_fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
