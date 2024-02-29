package com.vestel.tv.middleware;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class VestelPeqManager {

    public enum SoundMode {
        USER("User"),
        STANDARD("Standard"),
        VIVID("Vivid"),
        SPORT("Sport"),
        MOVIE("Movie"),
        MUSIC("Music"),
        NEWS("News");

        private final String name;
        private static final Map<String, VestelPeqManager.SoundMode> soundModeMap = new HashMap<>();

        static {
            for (VestelPeqManager.SoundMode soundMode : VestelPeqManager.SoundMode.values()) {
                soundModeMap.put(soundMode.name, soundMode);
            }
        }

        SoundMode(String name) {
            this.name = name;
        }

        public static String[] names() {
            return Stream.of(VestelPeqManager.SoundMode.values()).map(
                    VestelPeqManager.SoundMode::toString
            ).toArray(String[]::new);
        }

        public static VestelPeqManager.SoundMode get(String name) {
            return soundModeMap.get(name);
        }

        @Override
        public String toString() {
            return this.name;
        }

    }

    public enum PeqParameter { IS_ENABLE, FO_COARSE, FO_FINE, GAIN, Q }

    public static int getPeqParameter(SoundMode mode, PeqParameter parameter, int index) {
        return -1;
    }

    public static boolean setPeqParameter(SoundMode mode, PeqParameter parameter, int index, int value) {
        return false;
    }

    public static int getBandMaxNumber() {
        return -1;
    }

    public static int getCoarseMinValue() {
        return -1;
    }

    public static int getCoarseMaxValue() {
        return -1;
    }

    public static int getFineMinValue() {
        return -1;
    }

    public static int getFineMaxValue() {
        return -1;
    }

    public static int getGainMinValue() {
        return -1;
    }

    public static int getGainMaxValue() {
        return -1;
    }

    public static int getQMinValue() {
        return -1;
    }

    public static int getQMaxValue() {
        return -1;
    }

    private static boolean saveAudioIni() {
        return false;
    }

}
