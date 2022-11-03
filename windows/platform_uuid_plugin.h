#ifndef FLUTTER_PLUGIN_PLATFORM_UUID_PLUGIN_H_
#define FLUTTER_PLUGIN_PLATFORM_UUID_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace platform_uuid {

class PlatformUuidPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  PlatformUuidPlugin();

  virtual ~PlatformUuidPlugin();

  // Disallow copy and assign.
  PlatformUuidPlugin(const PlatformUuidPlugin&) = delete;
  PlatformUuidPlugin& operator=(const PlatformUuidPlugin&) = delete;

 private:
  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace platform_uuid

#endif  // FLUTTER_PLUGIN_PLATFORM_UUID_PLUGIN_H_
