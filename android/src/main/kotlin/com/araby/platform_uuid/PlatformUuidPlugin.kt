package com.araby.platform_uuid

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context.TELEPHONY_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.util.*

/** PlatformUuidPlugin */
class PlatformUuidPlugin: FlutterPlugin, MethodCallHandler , ActivityAware {

  private lateinit var channel : MethodChannel
  private lateinit var activity: Activity
  private var checkedPermission = PackageManager.PERMISSION_DENIED
  private val requestState = 100
  lateinit var manager: TelephonyManager


  override fun onAttachedToEngine( flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "platform_uuid")
    channel.setMethodCallHandler(this)
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onMethodCall(call: MethodCall, result: Result) {
    when (call.method) {
        "getPlatformVersion" -> {
          result.success("Android ${Build.VERSION.RELEASE}")
        }
        "checkPermission" -> {
            requestPermission()
        }
        "getUUid" -> {
            getAndroidId(result);
        }
        else -> {
          result.notImplemented()
        }
    }
  }


private fun requestPermission() {
  checkedPermission = ContextCompat.checkSelfPermission(this.activity,
          Manifest.permission.READ_PHONE_STATE)
  if (Build.VERSION.SDK_INT >= 23 && checkedPermission !=
          PackageManager.PERMISSION_GRANTED) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      this.activity.requestPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE), requestState)
     }
  } else {
    checkedPermission = PackageManager.PERMISSION_GRANTED
}

}



  @SuppressLint("HardwareIds")
  @RequiresApi(Build.VERSION_CODES.O)
  private fun  getUUid(result: Result){
      Build.ID
      val deviceUuid : String  = "35"  +
              Build.BOARD +"-" + Build.BRAND + "-" +Build.SUPPORTED_ABIS.joinToString("-") + "-" +Build.DEVICE+ "-" +Build.DISPLAY+ "-" +Build.HOST+ "-" +Build.ID +"-" + Build.MANUFACTURER + "-" +Build.MODEL + "-" +Build.PRODUCT + "-" +Build.TAGS + "-" +Build.TYPE

      result.success(deviceUuid)

  }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun  getPseudoCode(result: Result){
        Build.ID
        val deviceUuid : String  = "35"  +
                Build.BOARD +"-" + Build.BRAND + "-" + Build.SUPPORTED_ABIS.joinToString("-") + "-" +Build.DEVICE+ "-" +Build.DISPLAY+ "-" +Build.HOST+ "-" +Build.ID +"-" + Build.MANUFACTURER + "-" +Build.MODEL + "-" +Build.PRODUCT + "-" +Build.TAGS + "-" +Build.TYPE

        result.success(deviceUuid)

    }


    @SuppressLint("HardwareIds")
    private fun  getAndroidId(result: Result){
   val androidId : String? = Settings.Secure.getString(this.activity.contentResolver, Settings.Secure.ANDROID_ID)
    result.success(androidId)

  }

  override fun onDetachedFromEngine(  binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivityForConfigChanges() {
   }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
   }

  override fun onDetachedFromActivity() {
   }
}
