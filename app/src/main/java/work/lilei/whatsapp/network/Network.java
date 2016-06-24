package work.lilei.whatsapp.network;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * 网络请求
 * Created by lei on 6/23/16.
 */

public class Network {
    private static Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.4.232.53:9528").build();
    public static Retrofit retrofit() {
        return retrofit;
    }
//
//    private static Map<String, RemoteService> serviceMap = new HashMap<>();
//
//    public static RemoteService service(Class<? extends  RemoteService> clz) {
//
//        if (serviceMap.containsKey(clz.getName())) {
//            return serviceMap.get(clz.getName());
//        }
//        RemoteService result = retrofit.create(clz);
//        serviceMap.put(clz.getName(), result);
//        return result;
//    }
}
