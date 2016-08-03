package cashier.wizarpos.com.wizarposcashier.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class ManagerAddFragment extends Fragment {
    private View rootView;
    private ListView managerList;
    private Button addManagerBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.manager_add_ui,container,false);
        initView(rootView);
        return rootView;
    }

    //初始化
    private void initView(View view){
        managerList = (ListView) view.findViewById(R.id.managerList);
        addManagerBtn = (Button) view.findViewById(R.id.addManagerBtn);
        managerList.setAdapter(createListAdapter());
        addManagerBtn.setOnClickListener(new ButtonListener());
    }

    /**
     * 单击创建新柜员
     */
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.addManagerBtn:

                    break;

            }
        }
    }


    /**
     * 创建ListView adapter
     * @return
     */
    private ListAdapter createListAdapter() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;
        String[] arrays = {"001", "002", "003", "004", "005"};
        for (int i=0;i<5;i++) {
            map = new HashMap<>();
            map.put("picture", R.drawable.manager_list_man_icon);
            map.put("text", arrays[i]);
            map.put("button", R.drawable.manage_arrow);
            list.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(rootView.getContext(), list, R.layout.manager_add_list_item, new String[]{"picture", "text", "button"}, new int[]{R.id.mListImageInfo, R.id.mListTextInfo, R.id.mListImageIndex});
        return simpleAdapter;
    }



}
