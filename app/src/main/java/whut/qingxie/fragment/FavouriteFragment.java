package whut.qingxie.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import whut.qingxie.R;
import whut.qingxie.Item.FavouriteItem;
import whut.qingxie.adapter.FavouriteItemAdapter;

public class FavouriteFragment extends Fragment {

    private List<FavouriteItem> favouriteItems=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(favouriteItems.size()==0)
            initItem();
        FavouriteItemAdapter adapter=new FavouriteItemAdapter(getActivity(),R.layout.favourite_item,favouriteItems);
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        ListView listView=(ListView)view.findViewById(R.id.favourite_listview);
        listView.setAdapter(adapter);

        return view;
    }

    public void initItem(){
        favouriteItems.add(new FavouriteItem("张三","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        favouriteItems.add(new FavouriteItem("张三","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        favouriteItems.add(new FavouriteItem("张三","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        favouriteItems.add(new FavouriteItem("张三","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        favouriteItems.add(new FavouriteItem("张三","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
        favouriteItems.add(new FavouriteItem("张三","详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"));
    }
}
