package cashier.wizarpos.com.wizarposcashier.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cashier.wizarpos.com.wizarposcashier.R;


/**
 * Created by lixinchun on 16/7/27.
 */
public class ManageFragment extends Fragment {
    private View rootView;
    private ListView manage_list;
    private Fragment manager_password_fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.manage_center,container,false);
        manage_list = (ListView) rootView.findViewById(R.id.manageList);
        manage_list.setAdapter(createListAdapter());
        manage_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        setManagerPasswordFragment();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
        return rootView;
    }

    /**
     * 创建ListView adapter
     * @return
     */
    private ListAdapter createListAdapter() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;
        int[] draws = {R.drawable.manage_cashier,R.drawable.manage_crash_card,R.drawable.manage_printer,R.drawable.manage_password,R.drawable.manage_settings};
        String[] arrays = {"收银员管理", "支付设置", "打印交易订单", "安全密码", "店铺参数"};
        for (int i=0;i<draws.length;i++) {
            map = new HashMap<>();
            map.put("picture", draws[i]);
            map.put("text", arrays[i]);
            map.put("button", R.drawable.manage_arrow);
            list.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(rootView.getContext(), list, R.layout.manage_center_list_item, new String[]{"picture", "text", "button"}, new int[]{R.id.mListImageInfo, R.id.mListTextInfo, R.id.mListImageIndex});
        return simpleAdapter;
    }

    private void setManagerPasswordFragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        manager_password_fragment = new ManagerPasswordFragment();
        fragmentTransaction.replace(R.id.mainFragment,manager_password_fragment);
        fragmentTransaction.commit();
    }
}
