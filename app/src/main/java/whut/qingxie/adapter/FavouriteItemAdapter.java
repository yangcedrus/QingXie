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
import whut.qingxie.entity.activity.VolActivityInfo;

public class FavouriteItemAdapter extends ArrayAdapter {
    private int resourceId;

    public FavouriteItemAdapter(Context context, int textViewResourceId, List<VolActivityInfo> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        VolActivityInfo favouriteItem= (VolActivityInfo) getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView title=(TextView)view.findViewById(R.id.favourite_item_title);
        TextView text=(TextView)view.findViewById(R.id.favourite_item_text);

        title.setText(favouriteItem.getName());
        text.setText(favouriteItem.getGeneral());
        return view;
    }
}
