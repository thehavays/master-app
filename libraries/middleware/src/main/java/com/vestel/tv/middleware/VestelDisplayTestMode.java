package com.vestel.tv.middleware;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum VestelDisplayTestMode {
    MVOP_PATTERN_MODE("MVOP Pattern Mode"),
    XC_ADC_PATTERN_MODE("XC ADC Pattern Mode"),
    XC_IP_PATTERN_MODE("XC IP Pattern Mode"),
    XC_IP_MUX_PATTERN_MODE("XC IP MUX Pattern Mode"),
    XC_OP_LINEBUFF_PATTERN_MODE("XC OP LINEBUFF Pattern Mode"),
    XC_OP_HVSP_PATTERN_MODE("XC OP HVSP Pattern Mode"),
    XC_VOP_PATTERN_MODE("XC VOP Pattern Mode"),
    XC_VOP2_PATTERN_MODE("XC VOP2 Pattern Mode"),
    XC_MOD_PATTERN_MODE("XC MOD Pattern Mode"),
    XC_WHITE_BALANCE_PATTERN_MODE("XC White Balance Pattern Mode");

    private final String name;
    private static final Map<String, VestelDisplayTestMode> displayTestModeMap = new HashMap<>();

    static {
        for (VestelDisplayTestMode displayTestMode : VestelDisplayTestMode.values()) {
            displayTestModeMap.put(displayTestMode.name, displayTestMode);
        }
    }

    VestelDisplayTestMode(String name) {
        this.name = name;
    }

    public static String[] names() {
        return Stream.of(VestelDisplayTestMode.values()).map(VestelDisplayTestMode::toString).toArray(String[]::new);
    }

    public static VestelDisplayTestMode get(String name) {
        return displayTestModeMap.get(name);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
