package whut.qingxie.network;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by evans on 2018/3/7.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = { 25 })
public class OkhttpUtilTest {
    @Test
    public void accessData() throws Exception {
        Map<String,String> params=new HashMap<>();
        Map<String,String> headers=new HashMap<>();
        OkhttpUtil.accessData("GET", "/activity/getAllActivities", params, headers, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.e("请求失败",e.getMessage());
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                Log.i("response",response);
            }
        });
    }

}