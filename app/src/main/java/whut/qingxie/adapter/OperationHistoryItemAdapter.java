package whut.qingxie.adapter;

import android.content.Context;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.Item.OperationHistoryItem;

public class OperationHistoryItemAdapter extends RecyclerView.Adapter<OperationHistoryItemAdapter.ViewHolder> {
    public List<OperationHistoryItem> operationHistoryItems;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,date,text;

        private ViewHolder(View view){
            super(view);
            title=(TextView)view.findViewById(R.id.operation_title);
            date=(TextView)view.findViewById(R.id.operation_date);
            text=(TextView)view.findViewById(R.id.operation_text);
        }
    }

    public OperationHistoryItemAdapter(List<OperationHistoryItem> operationHistoryItemList){
        operationHistoryItems=operationHistoryItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_operation_history,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OperationHistoryItem operationHistory=operationHistoryItems.get(position);
        holder.title.setText(operationHistory.getTitle());
        holder.date.setText(operationHistory.getDate());
        holder.text.setText(operationHistory.getText());
    }

    @Override
    public int getItemCount() {
        return operationHistoryItems.size();
    }
}
