package work.lilei.whatsapp.atlas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import work.lilei.whatsapp.R;

/**
 * Atlas: 动态加载第三方app的框架
 * Created by lei on 6/23/16.
 */

public class AtlasActivity extends Activity {

    private ListView lvApps;
    private List<AppObject> appList;
    private AppAdapter appAdapter;

    public AtlasActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atlas);
        initData();
        initViews();
    }

    private void initData() {
        appList = new ArrayList<>();
        AppObject appObject01 = new AppObject.Builder().id("0x0001").name("DianPing").iconUrl("http://10.4.232.53:9528/plugin/plugin_01.jpg")
                .pkgName("com.dianping.v1").apkUrl("plugin_01.apk").build();
        AppObject appObject02 = new AppObject.Builder().id("0x0001").name("NetEase News").iconUrl("http://10.4.232.53:9528/plugin/plugin_02.jpg")
                .pkgName("com.netease.newsreader.activity").apkUrl("plugin_02.apk").build();
        AppObject appObject03 = new AppObject.Builder().id("0x0001").name("Season 1").iconUrl("http://10.4.232.53:9528/plugin/plugin_03.png")
                .pkgName("com.leili.season1").apkUrl("plugin_03.apk").build();
        AppObject appObject04 = new AppObject.Builder().id("0x0001").name("WeChat6.20").iconUrl("http://10.4.232.53:9528/plugin/plugin_04.png")
                .pkgName("com.tencent.mm").apkUrl("plugin_04.apk").build();
        appList.add(appObject01);
        appList.add(appObject02);
        appList.add(appObject03);
        appList.add(appObject04);
        appAdapter = new AppAdapter(this, appList);
    }

    private void initViews() {
        lvApps = (ListView) findViewById(R.id.app_list);
        lvApps.setAdapter(appAdapter);
    }
}
