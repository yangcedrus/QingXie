package whut.qingxie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.Item.ManageWorkerAccountItem;

public class ManageWorkerAccountItemAdapter extends RecyclerView.Adapter<ManageWorkerAccountItemAdapter.ViewHolder> {
    private List<ManageWorkerAccountItem> manageWorkerAccountItemList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView ID,accountID,IsOrNo,Date;

        private ViewHolder(View view){
            super(view);
            ID=(TextView)view.findViewById(R.id.manage_id);
            accountID=(TextView)view.findViewById(R.id.manage_account_id);
            IsOrNo=(TextView)view.findViewById(R.id.manage_IsOrNo);
            Date=(TextView)view.findViewById(R.id.manage_date);
        }
    }

    public ManageWorkerAccountItemAdapter(List<ManageWorkerAccountItem> manageList){
        manageWorkerAccountItemList =manageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_worker_account,parent,false);
        ViewHolder holder;
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ManageWorkerAccountItem manageWorkerAccountItem = manageWorkerAccountItemList.get(position);
        holder.ID.setText(manageWorkerAccountItem.getS1());
        holder.accountID.setText(manageWorkerAccountItem.getS2());
        holder.IsOrNo.setText(manageWorkerAccountItem.getS3());
        holder.Date.setText(manageWorkerAccountItem.getS4());
    }

    @Override
    public int getItemCount() {
        return manageWorkerAccountItemList.size();
    }
}
