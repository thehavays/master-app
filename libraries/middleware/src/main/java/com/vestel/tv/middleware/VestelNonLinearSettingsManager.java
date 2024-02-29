package com.vestel.tv.middleware;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class VestelNonLinearSettingsManager {


    public enum NonLinearPqType {
        BRIGHTNESS("Brightness"),
        CONTRAST("Contrast"),
        SATURATION("Saturation"),
        SHARPNESS("Sharpness"),
        HUE("Hue"),
        BACKLIGHT("BackLight"),
        MAX("Max");

        private final String name;
        private static final Map<String, NonLinearPqType>  nonLinearPqTypeMap = new HashMap<>();

        static {
            for (NonLinearPqType nonLinearPqType : NonLinearPqType.values()) {
                nonLinearPqTypeMap.put(nonLinearPqType.name, nonLinearPqType);
            }
        }

        NonLinearPqType(final String name) {
            this.name = name;
        }

        public static String[] names() {
            return Stream.of(VestelNonLinearSettingsManager.NonLinearPqType.values()).map(
                    VestelNonLinearSettingsManager.NonLinearPqType::toString
            ).toArray(String[]::new);
        }

        public static NonLinearPqType get(String name) {
            return nonLinearPqTypeMap.get(name);
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public static VestelNonLinearPqCurve getNonLinearPqCurve(NonLinearPqType type) {
        return null;
    }

    public static boolean setNonLinearPqCurve(NonLinearPqType type, VestelNonLinearPqCurve curve, int value) {
        return false;
    }

    // TODO: move this method to VestelSoundSettings class
    public static int getMainSpeakerVolume() {
        return -1;
    }

    // TODO: move this method to VestelSoundSettings class
    public static boolean setMainSpeakerVolume(int volume) {
        return false;
    }
}
