package work.lilei.whatsapp.atlas;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import work.lilei.whatsapp.network.RemoteService;

/**
 * Atlas remote interface
 * Created by lei on 6/23/16.
 */

public interface AtlasRemoteService {
    /**
     * 获取app列表
     * @return
     */
    @GET("atlas/apps")
    Call<List<AppObject>> listApps();

    /**
     * 下载app
     */
    @GET("plugin/{fileName}")
    Call<ResponseBody> download(@Path("fileName") String fileName);
}
