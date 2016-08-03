package cashier.wizarpos.com.wizarposcashier.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;

import cashier.wizarpos.com.wizarposcashier.Fragment.CashierDeskFragment;
import cashier.wizarpos.com.wizarposcashier.R;

/**
 * 收银主页面
 */
public class CashierMainActivity extends AppCompatActivity {
    private Fragment cashier_desk_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashier_main);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        setCashierManageFragment();
    }


    /**
     * 设置店铺管理页面
     */
    private void setCashierManageFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (cashier_desk_fragment ==null) cashier_desk_fragment = new CashierDeskFragment();
        fragmentTransaction.replace(R.id.mainFragment, cashier_desk_fragment);
        fragmentTransaction.commit();
    }



}
