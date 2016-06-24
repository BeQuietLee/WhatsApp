package work.lilei.whatsapp.util;

import android.content.Context;
import android.widget.Toast;

/**
 * View 工具类
 * Created by lei on 6/23/16.
 */

public class ViewUtils {
    private ViewUtils() {}

    /**
     * 展示toast
     * @param context
     * @param text
     */
    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
