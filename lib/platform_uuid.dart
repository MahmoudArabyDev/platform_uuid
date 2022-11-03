import 'platform_uuid_platform_interface.dart';

class PlatformUuid {
  Future<String?> getPlatformVersion() {
    return PlatformUuidPlatform.instance.getPlatformVersion();
  }

  Future<String?> getUUid() {
    return PlatformUuidPlatform.instance.getUUid();
  }

  Future checkPermission() {
    return PlatformUuidPlatform.instance.checkPermission();
  }
}
