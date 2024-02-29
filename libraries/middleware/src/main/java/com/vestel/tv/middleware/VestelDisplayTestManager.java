package com.vestel.tv.middleware;

public class VestelDisplayTestManager {

    private static final int DEFAULT_DISPLAY_TEST_MODE = -1;

    public static boolean setVideoTestPattern(VestelDisplayTestColor color) {
        return false;
    }

    public static boolean generateTestPattern(VestelDisplayTestMode mode, VestelDisplayTestPattern pattern) {
        return false;
    }

    private static int vestelToMediatekDisplayTestColor(VestelDisplayTestColor color) {
        return -1;
    }

    private static int mapVestelToMediatekDisplayTestMode(VestelDisplayTestMode mode) {
        return -1;
    }

}
