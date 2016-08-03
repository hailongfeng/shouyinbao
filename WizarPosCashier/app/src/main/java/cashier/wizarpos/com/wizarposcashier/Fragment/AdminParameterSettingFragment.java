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
 * 管理员参数管理界面
 * Created by lixinchun on 16/7/27.
 */
public class AdminParameterSettingFragment extends Fragment {
    private View rootView;
    private Button backBtn;
    private LinearLayout merchantNoSet,terminalNoSet,englishNoSet,appNameSet;
    private Fragment manager_password_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.admin_parameter_settings_ui,container,false);
        initView(rootView);
        return rootView;
    }

    /**
     * 初始化
     * @param view
     */
    private void initView(View view){
        backBtn = (Button)view.findViewById(R.id.backBtn);
        merchantNoSet = (LinearLayout)view.findViewById(R.id.merchantNoSet);
        terminalNoSet = (LinearLayout)view.findViewById(R.id.terminalNoSet);
        englishNoSet = (LinearLayout)view.findViewById(R.id.englishNoSet);
        appNameSet = (LinearLayout)view.findViewById(R.id.appNameSet);
        ButtonListener buttonListener = new ButtonListener();
        backBtn.setOnClickListener(buttonListener);
        merchantNoSet.setOnClickListener(buttonListener);
        terminalNoSet.setOnClickListener(buttonListener);
        englishNoSet.setOnClickListener(buttonListener);
        appNameSet.setOnClickListener(buttonListener);
    }

    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.backBtn:
                    getFragmentManager().popBackStack();
                    break;
                case R.id.merchantNoSet:
                    setAdminMerchantNumberSettingFragment();
                    break;
                case R.id.terminalNoSet:
                    break;
                case R.id.englishNoSet:
                    break;
                case R.id.appNameSet:
                    break;
                default:
                    break;

            }
        }
    }


    /**
     * 管理员商户号修改界面跳转
     */
    private void setAdminMerchantNumberSettingFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        manager_password_fragment = new AdminMerchantNumberSettingFragment();
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
