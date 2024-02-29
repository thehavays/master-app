package com.vestel.tv.middleware;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class VestelBatteryManager {

    private static final String TAG = "VestelBatteryManager";

    private static BatteryStatus mPreviousBatteryStatus = BatteryStatus.UNKNOWN;
    private static BatteryStatus mCurrentBatteryStatus = BatteryStatus.UNKNOWN;

    private static final int BATTERY_LEVEL_ARRAY_COUNT = 30;
    private static final int[] mBatteryLevelArray = new int[BATTERY_LEVEL_ARRAY_COUNT];
    private static int mBatteryLevelCounter = 0;

    private static final Map<Integer, Integer> adcToPercentageChargeMap;
    private static final Map<Integer, Integer> adcToPercentageDischargeMap;

    private static int mCurrentBatteryPercentage = 0;

    static {
        adcToPercentageChargeMap = new LinkedHashMap<>();
        adcToPercentageChargeMap.put(212, 100);
        adcToPercentageChargeMap.put(200, 87);
        adcToPercentageChargeMap.put(179, 50);
        adcToPercentageChargeMap.put(171, 40);

        adcToPercentageDischargeMap = new LinkedHashMap<>(adcToPercentageChargeMap);

        adcToPercentageChargeMap.put(117, 5);
        adcToPercentageChargeMap.put(97, 1);

        adcToPercentageDischargeMap.put(122, 5);
        adcToPercentageDischargeMap.put(102, 1);
    }

    public enum BatteryMode {
        NORMAL,
        ECO,
        ECO_PLUS,
        NONE
    }

    public enum BatteryStatus {
        CHARGING("Charging"),
        DISCHARGING("Discharging"),
        FULL("Full"),
        UNKNOWN("Unknown");

        private final String name;

        BatteryStatus(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

    }

    public static class BatteryInformation {
        private final BatteryStatus status;
        private final int percentage;

        public BatteryInformation(BatteryStatus status, int percentage) {
            this.status = status;
            this.percentage = percentage;
        }

        public BatteryStatus getStatus() {
            return status;
        }

        public int getPercentage() {
            return percentage;
        }
    }

    public static BatteryInformation getBatteryInformation() {
        return null;
    }

    public static void setBatteryMode(BatteryMode batteryMode) {
        int dimming = 0;
        final String configFileNamesFilePath = "/vendor/cusdata/bsp/common/customer/ConfigFileNames.ini"; // Can we use VestelConfig.getConfigFileNamesFile() here?

        if (batteryMode == BatteryMode.NORMAL) {
            dimming = 255;
        } else if (batteryMode == BatteryMode.ECO) {
            try {
                String str = VestelFileSystem.getIniParameter(configFileNamesFilePath, "BATTERY:BATTERY_ECO_MODE_DIMMING");
                dimming = Integer.parseInt(str);
            } catch (NumberFormatException exception) {
                VestelLog.error(TAG, exception.toString());
            }
        } else if (batteryMode == BatteryMode.ECO_PLUS) {
            try {
                String str = VestelFileSystem.getIniParameter(configFileNamesFilePath, "BATTERY:BATTERY_ECO_PLUS_MODE_DIMMING");
                dimming = Integer.parseInt(str);
            } catch (NumberFormatException exception) {
                VestelLog.error(TAG, exception.toString());
            }
        }

        VestelPictureSettings.setBacklightMode(VestelBacklightMode.BACKLIGHT_MODE_OFF, false);
        VestelPictureSettings.setBacklightDimming(dimming, true, -1);
    }

    private static void parseBatteryStatusData(String statusData) {
        char[] data0 = statusData.toCharArray();
        if (data0[4] == '1') {
            mCurrentBatteryStatus = BatteryStatus.DISCHARGING;
        } else if (data0[5] == '1') {
            mCurrentBatteryStatus = BatteryStatus.CHARGING;
        } else if (data0[4] == '0' && data0[5] == '0') {
            if (mCurrentBatteryPercentage > 98) {
                mCurrentBatteryStatus = BatteryStatus.FULL;
            } else {
                VestelLog.debug(TAG,"current_status was not handled, return to previous one");
                mCurrentBatteryStatus = mPreviousBatteryStatus;
            }
        } else {
            mCurrentBatteryStatus = BatteryStatus.UNKNOWN;
            VestelLog.debug(TAG, "i2c query for battery status is not meaningful");
        }
    }

    private static void parseBatteryLevelData(String levelData) {
        try {
            final int batteryLevel = Integer.parseInt(levelData, 2);
            if (batteryLevel > 75) {
                mCurrentBatteryPercentage = calculateBatteryPercentage(calculateBatteryLevelAverageValue(batteryLevel));
            }
        } catch (NumberFormatException exception) {
            VestelLog.error(TAG, exception.toString());
            mCurrentBatteryPercentage = 1;
        }
    }

    private static int calculateBatteryPercentage(int adcLevel) {
        final Map<Integer, Integer> adcToPercentageMap = new LinkedHashMap<>();
        int percentage = 1;

        if (mCurrentBatteryStatus == BatteryStatus.FULL || mCurrentBatteryStatus == BatteryStatus.CHARGING) {
            adcToPercentageMap.putAll(adcToPercentageChargeMap);
        } else {
            adcToPercentageMap.putAll(adcToPercentageDischargeMap);
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = adcToPercentageMap.entrySet().iterator();
        Map.Entry<Integer, Integer> previousEntry = null;
        if (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (adcLevel >= entry.getKey() || mCurrentBatteryStatus == BatteryStatus.FULL) {
                percentage = entry.getValue();
            }
            previousEntry = entry;
        }

        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (adcLevel >= entry.getKey()) {
                final int adcDiff = previousEntry.getKey() - entry.getKey();
                final int percentageDiff = previousEntry.getValue() - entry.getValue();
                percentage = entry.getValue() + ((adcLevel - entry.getKey()) * percentageDiff / adcDiff);
                break;
            }
            previousEntry = entry;
        }

        return percentage == 0 ? 1 : percentage;
    }

    private static int calculateBatteryLevelAverageValue(int batteryLevel) {
        mBatteryLevelArray[mBatteryLevelCounter] = batteryLevel;
        mBatteryLevelCounter++;

        int sum = 0;
        for (int i = 0; i < mBatteryLevelCounter; i++) {
            sum += mBatteryLevelArray[i];
        }

        final int averageValue = sum / mBatteryLevelCounter;

        if (mBatteryLevelCounter == BATTERY_LEVEL_ARRAY_COUNT - 1) {
            mBatteryLevelCounter = 0;
        }

        return averageValue;
    }

    public static String getFirmwareVersion()
    {
        String version = "";
        VestelLog.debug(TAG, "getFirmwareVersion():" + version);
        return version;
    }
}
