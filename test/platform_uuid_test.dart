import 'package:flutter_test/flutter_test.dart';
import 'package:platform_uuid/platform_uuid.dart';
import 'package:platform_uuid/platform_uuid_method_channel.dart';
import 'package:platform_uuid/platform_uuid_platform_interface.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockPlatformUuidPlatform
    with MockPlatformInterfaceMixin
    implements PlatformUuidPlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<String?> getUUid() {
    // TODO: implement getUUid
    throw UnimplementedError();
  }
}

void main() {
  final PlatformUuidPlatform initialPlatform = PlatformUuidPlatform.instance;

  test('$MethodChannelPlatformUuid is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelPlatformUuid>());
  });

  test('getPlatformVersion', () async {
    PlatformUuid platformUuidPlugin = PlatformUuid();
    MockPlatformUuidPlatform fakePlatform = MockPlatformUuidPlatform();
    PlatformUuidPlatform.instance = fakePlatform;

    expect(await platformUuidPlugin.getPlatformVersion(), '42');
  });
}
