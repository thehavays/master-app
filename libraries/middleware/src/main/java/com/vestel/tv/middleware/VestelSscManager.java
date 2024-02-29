package com.vestel.tv.middleware;

public class VestelSscManager {

    public static boolean isSscEnabled(int miu) {
        return false;
    }

    public static int getSscModulation(int miu) {
        return -1;
    }

    public static int getSscPercentage(int miu) {
        return -1;
    }

    public static boolean setSscParameters(int index, boolean isEnabled, int modulation, int percentage) {
        return false;
    }

    public static int getSscMiuMinValue() {
        return -1;
    }

    public static int getSscMiuMaxValue() {
        return -1;
    }

    public static int getSscModulationMinValue() {
        return -1;
    }

    public static int getSscModulationMaxValue() {
        return -1;
    }

    public static int getSscPercentageMinValue() {
        return -1;
    }

    public static int getSscPercentageMaxValue() {
        return -1;
    }

    public static int getSwingLevel() {
        return -1;
    }

    public static int getSwingLevelMinValue() {
        return -1;
    }

    public static int getSwingLevelMaxValue() {
        return -1;
    }

    public static boolean setSwingLevel(int swingLevel) {
        return false;
    }

    public static boolean isLvdsEnabled() {
        return false;
    }

    public static boolean setLvdsEnabled(boolean isEnabled) {
        return false;
    }

    public static int getLvdsParametersMinValue() {
        return -1;
    }

    public static int getLvdsParametersMaxValue() {
        return -1;
    }

    public static int getLvdsModulation() {
        return -1;
    }

    public static boolean setLvdsModulation(int modulation) {
        return false;
    }

    public static int getLvdsPercentage() {
        return -1;
    }

    public static boolean setLvdsPercentage(int percentage) {
        return false;
    }

}
