package com.vestel.tv.middleware;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum VestelFactoryPowerMode {
    SECONDARY("Secondary"),
    MEMORY("Memory"),
    DIRECT("Direct"),
    INVALID("Invalid");

    private final String name;
    private static final Map<String, VestelFactoryPowerMode> factoryPowerModeMap = new HashMap<>();

    static {
        for (VestelFactoryPowerMode powerMode : VestelFactoryPowerMode.values()) {
            factoryPowerModeMap.put(powerMode.name, powerMode);
        }
    }

    VestelFactoryPowerMode(final String name) {
        this.name = name;
    }

    public static String[] names() {
        return Stream.of(VestelFactoryPowerMode.values()).map(VestelFactoryPowerMode::toString).toArray(String[]::new);
    }

    public static VestelFactoryPowerMode get(String name) {
        return factoryPowerModeMap.get(name);
    }

    @Override
    public String toString() {
        return name;
    }

}
