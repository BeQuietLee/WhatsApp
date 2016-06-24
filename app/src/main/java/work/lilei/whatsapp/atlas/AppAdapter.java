package work.lilei.whatsapp.atlas;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.morgoo.droidplugin.pm.PluginManager;
import com.morgoo.helper.compat.PackageManagerCompat;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import work.lilei.whatsapp.R;
import work.lilei.whatsapp.network.Network;
import work.lilei.whatsapp.util.ViewUtils;

/**
 * 单个应用
 * TODO 优化为 RecyclerView
 * Created by lei on 6/23/16.
 */

public class AppAdapter extends BaseAdapter {

    public static final String TAG = AppAdapter.class.getSimpleName();

    private Context context;
    private List<AppObject> appList;

    public AppAdapter(Context context, List<AppObject> appList) {
        this.context = context;
        this.appList = appList;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int i) {
        return appList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_atlas_app, null);
            vh = new ViewHolder();
            vh.icon = (ImageView) view.findViewById(R.id.icon);
            vh.name = (TextView) view.findViewById(R.id.name);
            vh.download = (Button) view.findViewById(R.id.download);
            vh.play = (Button) view.findViewById(R.id.play);
            vh.delete = (Button) view.findViewById(R.id.delete);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        final AppObject app = (AppObject) getItem(i);
        Picasso.with(context).load(app.getIconUrl()).into(vh.icon);
        vh.name.setText(app.getName());
        vh.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = (Network.retrofit().create(AtlasRemoteService.class)).download(app.getApkUrl());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        ViewUtils.toast(context, "Download finish!");
                        try {
                            InputStream is = response.body().byteStream();
                            BufferedInputStream bis = new BufferedInputStream(is);
                            File file = new File(context.getFilesDir() + "/" + app.getApkUrl());
                            FileOutputStream fileOS = new FileOutputStream(file);
                            byte[] bytes = new byte[2048];
                            int len;
                            while ((len = bis.read(bytes)) != -1) {
                                // write file
                                fileOS.write(bytes, 0, len);
                                fileOS.flush();
                            }
                            is.close();
                            bis.close();
                            fileOS.close();
                            // install
                            try {
                                int resultCode = PluginManager.getInstance().installPackage(context.getFilesDir() + "/" + app.getApkUrl(), 0);
                                if (resultCode == PackageManagerCompat.INSTALL_SUCCEEDED) {
                                    ViewUtils.toast(context, "Install Finish");
                                } else {
                                    ViewUtils.toast(context, "Install Fail, resultCode=" + resultCode);
                                }
                            } catch (RemoteException e) {
                                Log.e(TAG, "fail to run apk", e);
                                ViewUtils.toast(context, "Install Fail");
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        ViewUtils.toast(context, "Download Fail");
                        t.printStackTrace();
                    }
                });
            }
        });
        vh.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager pm = context.getPackageManager();
                Intent i = pm.getLaunchIntentForPackage(app.getPkgName());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        vh.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtils.toast(context, "delete!");
            }
        });
        return view;
    }

    static class ViewHolder {
        ImageView icon;
        TextView name;
        Button download, play, delete;
    }
}
