package whut.qingxie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.OperationHistoryItemAdapter;
import whut.qingxie.bean.OperationHistoryItem;

public class OperationHistoryFragment extends Fragment {
    private List<OperationHistoryItem> operationHistoryItemList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(operationHistoryItemList.size()==0)
            initItem();
        OperationHistoryItemAdapter operationHistoryItemAdapter=new OperationHistoryItemAdapter(getActivity(),
                R.layout.operation_history_item,operationHistoryItemList);
        View view = inflater.inflate(R.layout.activity_operation_history_fragment, container, false);
        ListView listView=(ListView)view.findViewById(R.id.operation_listview);
        listView.setAdapter(operationHistoryItemAdapter);

        return view;
    }

    public void initItem(){
        operationHistoryItemList.add(new OperationHistoryItem("张三","20180113/0258","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        operationHistoryItemList.add(new OperationHistoryItem("张三","20180113/0258","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        operationHistoryItemList.add(new OperationHistoryItem("张三","20180113/0258","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        operationHistoryItemList.add(new OperationHistoryItem("张三","20180113/0258","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        operationHistoryItemList.add(new OperationHistoryItem("张三","20180113/0258","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        operationHistoryItemList.add(new OperationHistoryItem("张三","20180113/0258","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
    }
}
