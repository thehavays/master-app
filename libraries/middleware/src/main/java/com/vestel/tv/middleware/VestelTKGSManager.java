package com.vestel.tv.middleware;

import java.util.stream.Stream;

public class VestelTKGSManager {

    private static final int DEFAULT_UPDATE_TYPE_VALUE = 0;
    private static final int CERTIFICATION_UPDATE_TYPE_VALUE = 1;

    public enum TKGSUpdateType {
        DEFAULT,
        CERTIFICATION;

        public static String[] names() {
            return Stream.of(TKGSUpdateType.values()).map(TKGSUpdateType::name).toArray(String[]::new);
        }
    }

    public static TKGSUpdateType getTKGSUpdateType() {
        int value = DEFAULT_UPDATE_TYPE_VALUE;
        if (value == CERTIFICATION_UPDATE_TYPE_VALUE) {
            return TKGSUpdateType.CERTIFICATION;
        } else {
            return TKGSUpdateType.DEFAULT;
        }
    }

    public static boolean setTKGSUpdateType(TKGSUpdateType updateType) {
        int updateTypeValue = DEFAULT_UPDATE_TYPE_VALUE;
        if (updateType == TKGSUpdateType.CERTIFICATION) {
            updateTypeValue = CERTIFICATION_UPDATE_TYPE_VALUE;
        }
        return true;
    }

}
