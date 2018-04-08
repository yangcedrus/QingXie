package whut.qingxie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.Item.ReleaseWorkItem;

// TODO: 2018/4/8 待删除 
public class ReleaseWorkItemAdapter extends ArrayAdapter {
    private int resourceId;

    public ReleaseWorkItemAdapter(Context context, int textViewResourceId, List<ReleaseWorkItem> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ReleaseWorkItem releaseWorkItem=(ReleaseWorkItem)getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView textView=(TextView)view.findViewById(R.id.release_text);
        EditText editText=(EditText)view.findViewById(R.id.release_edittext);
        textView.setText(releaseWorkItem.getS1());
        editText.setText(releaseWorkItem.getS2());

        return view;
    }
}
