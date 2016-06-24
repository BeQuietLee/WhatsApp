package work.lilei.whatsapp.atlas;

/**
 * 单个App对象
 * Created by lei on 6/23/16.
 */

public class AppObject {
    public static final int NEW = 0x00;
    public static final int DOWNLOADED = 0x01;

    private String id;
    private String iconUrl; // 图标url
    private String name; // 名称
    private int status = 0x00; // 状态,默认未安装
    private String apkUrl; // apk地址
    private String pkgName; // package name

    public static class Builder {
        AppObject appObject = new AppObject();
        public Builder id (String id) {
            appObject.id = id;
            return this;
        }
        public Builder iconUrl (String iconUrl) {
            appObject.iconUrl = iconUrl;
            return this;
        }
        public Builder name (String name) {
            appObject.name = name;
            return this;
        }
        public Builder status (int status) {
            appObject.status = status;
            return this;
        }
        public Builder apkUrl (String apkUrl) {
            appObject.apkUrl = apkUrl;
            return this;
        }
        public Builder pkgName(String pkgName) {
            appObject.pkgName = pkgName;
            return this;
        }

        public AppObject build () {
            return appObject;
        }
    }

    public static int getNEW() {
        return NEW;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
