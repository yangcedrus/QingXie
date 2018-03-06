package whut.qingxie.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import whut.qingxie.R;
import whut.qingxie.Item.volServiceItem;

public class ServiceItemAdapter extends ArrayAdapter {

    private int resourceId;

    public ServiceItemAdapter(Context context, int textViewResourceId, List<volServiceItem> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        volServiceItem volServiceItem = (volServiceItem) getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView name=(TextView)view.findViewById(R.id.service_name);
        TextView time=(TextView)view.findViewById(R.id.service_time);
        TextView num=(TextView)view.findViewById(R.id.service_num);
        TextView info=(TextView)view.findViewById(R.id.service_info);
        TextView status=(TextView)view.findViewById(R.id.service_status);

        name.setText(volServiceItem.getName());
        time.setText(volServiceItem.getTime());
        int Num= volServiceItem.getNum();
        num.setText(Integer.toString(Num));
        info.setText(volServiceItem.getInfo());
        Drawable drawable;
        // TODO: 2017/11/11 根据状态不同导入不同图片源文件
        switch (volServiceItem.getStatus()){
            case 1:
                status.setText("未开始");
                drawable=view.getResources().getDrawable(R.drawable.ic_person_black_24dp);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                status.setCompoundDrawables(drawable,null,null,null);
                break;
            case 2:
                drawable=view.getResources().getDrawable(R.drawable.ic_home_black_24dp);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                status.setCompoundDrawables(drawable,null,null,null);
                status.setText("面试中");
                break;
            case 3:
                status.setText("进行中");
                break;
            case 4:
                status.setText("已完成");
            default:
                // TODO: 2017/11/11 异常处理
                break;
        }

        return view;
    }
}
