import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'platform_uuid_platform_interface.dart';

/// An implementation of [PlatformUuidPlatform] that uses method channels.
class MethodChannelPlatformUuid extends PlatformUuidPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('platform_uuid');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<String?> getUUid() async {
    final uuid = await methodChannel.invokeMethod<String?>('getUUid');
    return uuid;
  }

  @override
  Future<void> checkPermission() async {
    await methodChannel.invokeMethod('checkPermission');
  }
}
