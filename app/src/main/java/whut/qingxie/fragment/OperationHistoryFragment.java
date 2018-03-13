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
import java.util.zip.Inflater;

import whut.qingxie.R;
import whut.qingxie.adapter.OperationHistoryItemAdapter;
import whut.qingxie.Item.OperationHistoryItem;

public class OperationHistoryFragment extends Fragment {
    private List<OperationHistoryItem> operationHistoryItemList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_operation_history,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(operationHistoryItemList.size()==0)
            initItem();
        OperationHistoryItemAdapter operationHistoryItemAdapter=new OperationHistoryItemAdapter(getActivity(),
                R.layout.operation_history_item,operationHistoryItemList);
        ListView listView=(ListView)getActivity().findViewById(R.id.operation_listview);
        listView.setAdapter(operationHistoryItemAdapter);
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
