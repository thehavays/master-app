package com.vestel.tv.middleware;

public class VestelOledPanelManager {

    private static final String TAG = "VestelOledPanelManager";

    private static final String TPC_ENABLE_KEY = "TconFunct:bTPCEnable";

    // TODO: move this method to VestelPanelProperties class
    public static boolean isTpcEnabled() {
        boolean result = false;

        String value = VestelFileSystem.getIniParameter(VestelConfig.getOledPanelConfigFile(), TPC_ENABLE_KEY);
        try {
            result  = (Integer.parseInt(value) > 0);
        } catch (Exception exception) {
            VestelLog.error(TAG, exception.toString());
        }

        return result;
    }

    // TODO: move this method to VestelPanelProperties class
    public static boolean setTpcEnabled(boolean isEnabled) {
        return VestelFileSystem.setIniParameter(
                VestelConfig.getOledPanelConfigFile(),
                TPC_ENABLE_KEY,
                isEnabled ? "1" : "0"
        );
    }

}
