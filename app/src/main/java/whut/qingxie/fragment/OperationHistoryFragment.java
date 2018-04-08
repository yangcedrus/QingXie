package whut.qingxie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.adapter.OperationHistoryItemAdapter;
import whut.qingxie.Item.OperationHistoryItem;

/**
 * 管理员操作历史页面
 * todo 功能待完善
 */
public class OperationHistoryFragment extends Fragment {
    private List<OperationHistoryItem> operationHistoryItemList = new ArrayList<>();

    private static OperationHistoryItemAdapter adapter;
    private static SmartRefreshLayout smartRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_operation_history, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.operation_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new OperationHistoryItemAdapter(operationHistoryItemList);
        recyclerView.setAdapter(adapter);

        smartRefreshLayout = (SmartRefreshLayout) getActivity().findViewById(R.id.operation_refresh);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                init();
                //结束刷新
                smartRefreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for (int i = 0; i < 5; i++) {
                    operationHistoryItemList.add(new OperationHistoryItem("张三", "20180113/0258",
                            "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
                }
                //结束加载更多
                if(operationHistoryItemList.size()<10)
                    smartRefreshLayout.finishLoadmore();
                else
                    smartRefreshLayout.finishLoadmoreWithNoMoreData();
                reFresh();
            }
        });

        if (operationHistoryItemList.size() == 0)
            init();
    }

    private static void reFresh() {
        adapter.notifyDataSetChanged();
    }

    public void init() {
        operationHistoryItemList.clear();
        for (int i = 0; i < 5; i++) {
            operationHistoryItemList.add(new OperationHistoryItem("张三", "20180113/0258",
                    "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        }
        reFresh();
        smartRefreshLayout.resetNoMoreData();
    }
}
