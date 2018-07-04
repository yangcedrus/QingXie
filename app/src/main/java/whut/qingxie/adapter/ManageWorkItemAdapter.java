package whut.qingxie.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.Call;
import whut.qingxie.R;
import whut.qingxie.common.Content;
import whut.qingxie.dto.Msg;
import whut.qingxie.entity.activity.Activity4User;
import whut.qingxie.entity.activity.VolActivityInfo;
import whut.qingxie.network.CallBackUtil;
import whut.qingxie.network.OkhttpUtil;

import static android.content.ContentValues.TAG;

public class ManageWorkItemAdapter extends RecyclerView.Adapter<ManageWorkItemAdapter.ViewHolder> {
    private List<Activity4User> activityInfoList;

    public ManageWorkItemAdapter(List<Activity4User> activityInfoList) {
        this.activityInfoList = activityInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_work, parent, false);
        final ViewHolder holder;
        holder = new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                final Activity4User item = activityInfoList.get(pos);

                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("活动推进");
                builder.setMessage("是否推进" + item.getName() + "状态？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(parent.getContext(), "取消推进", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MoveStatus(item.getActivityId(), parent.getContext());
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Activity4User activityInfo = activityInfoList.get(position);

        holder.title.setText(activityInfo.getName());
        holder.manager.setText(activityInfo.getSponsor());
    }

    @Override
    public int getItemCount() {
        return activityInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, manager;
        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            title = (TextView) view.findViewById(R.id.manage_work_title);
            manager = (TextView) view.findViewById(R.id.manage_work_manager);
        }
    }

    private void MoveStatus(Integer activityId, final Context context) {
        OkhttpUtil.okHttpPost("/activity/" + activityId + "/boostActivity", new CallBackUtil.CallBackMsg() {
            @Override
            public void onFailure(Call call, Exception e) {
                Toast.makeText(context, "状态推进失败", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Msg response) {
                Toast.makeText(context, response.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}