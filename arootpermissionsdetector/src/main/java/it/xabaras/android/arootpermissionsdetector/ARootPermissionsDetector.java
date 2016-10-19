package it.xabaras.android.arootpermissionsdetector;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paolo Montalto on 19/10/16.
 */

/***
 * This is a simple utility class combining several methods for checking root permisions on android devices
 */
public final class ARootPermissionsDetector {

    /***
     * Check if device is rooted
     * @param context a valid Context
     * @return true if root permissions detected, false otherwise
     */
    public static boolean isRooted(Context context) {
        return hasSuperuserApk() || isTestKeyBuild() || isPackageInstalled("eu.chainfire.supersu", context) || canExecuteSuCommand();
    }

    /***
     *
     * @return true if su command detected, false otherwise
     */
    private static boolean canExecuteSuCommand() {
        try {
            Runtime.getRuntime().exec("su");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /***
     *
     * @return true if superuser apk is installed, false otherwise
     */
    private static boolean hasSuperuserApk() {
        try {
            return new File("/system/app/Superuser.apk").exists();
        } catch(Exception e) {
            return false;
        }
    }

    /***
     *
     * @return true if on a test build, false otherwise
     */
    private static boolean isTestKeyBuild() {
        String str = Build.TAGS;
        if (str != null && str.contains("test-keys"))
            return true;

        return false;

    }

    /***
     * Check if a specified package is installed on the device
     * @param packagename the package name to check
     * @param context a valid Context
     * @return true id packagename is installed, false otherwise
     */
    private static boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
