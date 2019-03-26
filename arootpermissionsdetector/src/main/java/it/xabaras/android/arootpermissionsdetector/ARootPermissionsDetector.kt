package it.xabaras.android.arootpermissionsdetector

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

import java.io.File
import java.io.IOException
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * Created by Paolo Montalto on 19/10/16.
 */

/***
 * This is a simple utility class combining several methods for checking root permisions on android devices
 */
class ARootPermissionsDetector {

    companion object {
        /***
         * Check if device is rooted
         * @param context a valid Context
         * @return true if root permissions detected, false otherwise
         */
        @JvmStatic
        fun isRooted(context: Context) : Boolean {
            return isTestKeyBuild() || hasSuperuserApk() || hasSuperSu(context) || hasSuCommand()
        }

        /***
         * Check if device is running on a test build
         *
         * @return true if on a test build, false otherwise
         */
        private fun isTestKeyBuild() : Boolean {
            val str = Build.TAGS
            return str != null && str.contains("test-keys")
        }

        /***
         * Check if su command is present
         *
         * @return true if su command detected, false otherwise
         */
        private fun hasSuCommand() : Boolean {
            val paths = listOf("/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su")
            var process: Process? = null

            return paths.any {
                try {
                    File(it).exists()
                } catch (e: Exception) {
                    false
                }
            } || try {
                process = Runtime.getRuntime().exec("su")
                process.let { it.destroy() }
                true
            } catch (e: IOException) {
                false
            } || try {
                process = Runtime.getRuntime().exec(arrayOf("/system/bin/which", "su"))
                val br = BufferedReader(InputStreamReader(process!!.inputStream))
                br.readLine() != null
            } catch (t: Throwable) {
                return false
            } finally {
                process?.let { it.destroy() }
            } || try {
                process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
                val br = BufferedReader(InputStreamReader(process!!.inputStream))
                br.readLine() != null
            } catch (t: Throwable) {
                return false
            } finally {
                process?.let { it.destroy() }
            }
        }

        /***
         * Check if superuser apk is installed
         *
         * @return true if superuser apk is installed, false otherwise
         */
        private fun hasSuperuserApk() : Boolean {
            return try {
                File("/system/app/Superuser.apk").exists()
            } catch (e: Exception) {
                false
            }

        }

        /**
         * Check if SuperSu app is installed
         *
         * @param context a valid Context
         * @return true if SuperSU is installed, false otherwise
         */
        private fun hasSuperSu(context: Context): Boolean {
            return isPackageInstalled("eu.chainfire.supersu", context)
        }

        /***
         * Check if a specified package is installed on the device
         * @param packageName the package name to check
         * @param context a valid Context
         * @return true id packageName is installed, false otherwise
         */
        private fun isPackageInstalled(packageName: String, context: Context): Boolean {
            val pm = context.packageManager
            return try {
                pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }

        }
    }
}
