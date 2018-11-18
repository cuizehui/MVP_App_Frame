package cn.nela.tools;

import android.os.Build;

public class SdkTool {

    public static boolean hasApi(int level) {
        return Build.VERSION.SDK_INT >= level;
    }

    public static boolean hasJellyBean() { // >= 16
        return hasApi(Build.VERSION_CODES.JELLY_BEAN);
    }

    public static boolean hasJellyBeanMr1() { // >= 17
        return hasApi(Build.VERSION_CODES.JELLY_BEAN_MR1);
    }

    public static boolean hasJellyBeanMr2() { // >= 18
        return hasApi(Build.VERSION_CODES.JELLY_BEAN_MR2);
    }

    public static boolean hasKitkat() { // >= 19
        return hasApi(Build.VERSION_CODES.KITKAT);
    }

    public static boolean hasKitkatWatch() { // >= 20
        return hasApi(Build.VERSION_CODES.KITKAT_WATCH);
    }

    public static boolean hasLollipop() { // >= 21
        return hasApi(Build.VERSION_CODES.LOLLIPOP);
    }

    public static boolean hasM() { // >= 23
        return hasApi(Build.VERSION_CODES.M);
    }

    public static boolean hasN() { // >= 24
        return hasApi(Build.VERSION_CODES.N);
    }

    public static boolean hasO() { // >= 26
        return hasApi(Build.VERSION_CODES.O);
    }

    public static boolean isApi(int level) {
        return Build.VERSION.SDK_INT == level;
    }

    public static boolean isJellyBean() { // 16
        return hasApi(Build.VERSION_CODES.JELLY_BEAN);
    }

    public static boolean isLollipop() { // 21
        return isApi(Build.VERSION_CODES.LOLLIPOP);
    }

}
