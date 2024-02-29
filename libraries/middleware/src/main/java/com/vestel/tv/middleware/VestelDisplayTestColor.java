package com.vestel.tv.middleware;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum VestelDisplayTestColor {
    WHITE("White"),
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    BLACK("Black"),
    NONE("None");

    private final String name;
    private static final Map<String, VestelDisplayTestColor> displayTestColorMap = new HashMap<>();

    static {
        for (VestelDisplayTestColor displayTestColor : VestelDisplayTestColor.values()) {
            displayTestColorMap.put(displayTestColor.name, displayTestColor);
        }
    }

    VestelDisplayTestColor(String name) {
        this.name = name;
    }

    public static String[] names() {
        return Stream.of(VestelDisplayTestColor.values()).map(VestelDisplayTestColor::toString).toArray(String[]::new);
    }

    public static VestelDisplayTestColor get(String name) {
        return displayTestColorMap.get(name);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
