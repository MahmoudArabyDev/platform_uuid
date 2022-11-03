import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'platform_uuid_method_channel.dart';

abstract class PlatformUuidPlatform extends PlatformInterface {
  /// Constructs a PlatformUuidPlatform.
  PlatformUuidPlatform() : super(token: _token);

  static final Object _token = Object();

  static PlatformUuidPlatform _instance = MethodChannelPlatformUuid();

  /// The default instance of [PlatformUuidPlatform] to use.
  ///
  /// Defaults to [MethodChannelPlatformUuid].
  static PlatformUuidPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [PlatformUuidPlatform] when
  /// they register themselves.
  static set instance(PlatformUuidPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<String?> getUUid();
  Future checkPermission();
}
