package whut.qingxie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.Item.MyHoursItem;
import whut.qingxie.R;

/**
 * Created by 22278 on 2018/3/12.
 * 我的志愿工时
 */

public class MyHoursItemAdapter extends RecyclerView.Adapter<MyHoursItemAdapter.ViewHolder> {
    private List<MyHoursItem> myHoursItems;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView time,place,people,hour,status;

        private ViewHolder(View view){
            super(view);
            time=(TextView)view.findViewById(R.id.my_hours_time);
            place=(TextView)view.findViewById(R.id.my_hours_place);
            people=(TextView)view.findViewById(R.id.my_hours_people);
            hour=(TextView)view.findViewById(R.id.my_hours_hours);
            status=(TextView)view.findViewById(R.id.my_hours_status);
        }
    }

    public MyHoursItemAdapter(List<MyHoursItem> hoursItemList){
        myHoursItems =hoursItemList;
    }

    @Override
    public MyHoursItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_hours_item,parent,false);
        MyHoursItemAdapter.ViewHolder holder;
        holder = new MyHoursItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHoursItemAdapter.ViewHolder holder, int position) {
        MyHoursItem myHoursItem = myHoursItems.get(position);
        holder.time.setText(myHoursItem.getServiceTime());
        holder.place.setText(myHoursItem.getServicePlace());
        holder.people.setText(myHoursItem.getServicePeople());
        holder.hour.setText(Double.toString(myHoursItem.getHours()));
        holder.status.setText(myHoursItem.getStatus()?"已认证":"未认证");
    }

    @Override
    public int getItemCount() {
        return myHoursItems.size();
    }
}
