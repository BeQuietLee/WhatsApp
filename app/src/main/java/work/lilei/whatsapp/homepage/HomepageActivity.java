package work.lilei.whatsapp.homepage;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.morgoo.droidplugin.pm.PluginManager;

import work.lilei.whatsapp.R;
import work.lilei.whatsapp.util.ViewUtils;

/**
 * 主页
 * Created by lei on 6/21/16.
 */

public class HomepageActivity extends Activity {

    private static final String TAG = HomepageActivity.class.getSimpleName();
    private Button btnInstall, btnRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initViews();
    }

    private void initViews() {
        btnInstall = (Button) findViewById(R.id.install);
        btnInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileFolderPath = getFilesDir().getPath();
                String pluginFilePath = fileFolderPath + "/plugin_01.apk";
                try {
                    PluginManager.getInstance().installPackage(pluginFilePath, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        btnRun = (Button) findViewById(R.id.run);
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager pm = getPackageManager();
                Intent i = pm.getLaunchIntentForPackage("com.leili.season1");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }
}
