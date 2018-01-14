package whut.qingxie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.bean.OperationHistoryItem;

public class OperationHistoryItemAdapter extends ArrayAdapter {
    private int resourceId;

    public OperationHistoryItemAdapter(Context context, int textViewResourceId, List<OperationHistoryItem> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        OperationHistoryItem operationHistoryItem=(OperationHistoryItem)getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView textView1=(TextView)view.findViewById(R.id.operation_title);
        TextView textView2=(TextView)view.findViewById(R.id.operation_date);
        TextView textView3=(TextView)view.findViewById(R.id.operation_text);
        textView1.setText(operationHistoryItem.getTitle());
        textView2.setText(operationHistoryItem.getDate());
        textView3.setText(operationHistoryItem.getText());
        return view;
    }
}
