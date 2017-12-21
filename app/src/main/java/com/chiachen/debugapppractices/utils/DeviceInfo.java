package com.chiachen.debugapppractices.utils;

import android.os.Build;

import java.util.Locale;

/**
 * Created by jianjiacheng on 21/12/2017.
 */

public class DeviceInfo {
    /**
     * Returns the current locale (ex. "en_US").
     */
    public static String getLocale() {
        final Locale locale = Locale.getDefault();
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    /**
     * Returns the vendor
     */
    public static String getVendor() {
        return Build.MANUFACTURER;
    }

    /**
     * Returns the version of the os
     */
    public static String getOSVersion() {
        return String.format(Locale.US, "%s (SDK %d)", android.os.Build.VERSION.RELEASE, android.os.Build.VERSION.SDK_INT);
    }
}
