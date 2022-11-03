#include "include/platform_uuid/platform_uuid_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "platform_uuid_plugin.h"

void PlatformUuidPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  platform_uuid::PlatformUuidPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
