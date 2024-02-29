package com.vestel.tv.middleware;

public class VestelSystemManager {

    private static final String TAG = "VestelSystemManager";

    // Min and Max values for sound speaker delay defined in MtkTvFApiAudioBase.setSoundSpeakerDelay() method
    private static final int SOUND_SPEAKER_DELAY_MIN_VALUE = 0;
    private static final int SOUND_SPEAKER_DELAY_MAX_VALUE = 250;

    public enum VestelTimeSyncSource {NOT_SYNC, TIME_SYNC_FROM_TS}

    public static void setTimeSyncSource(VestelTimeSyncSource timeSyncSource) {

    }

    public static VestelFactoryPowerMode getFactoryPowerMode() {
        return VestelFactoryPowerMode.INVALID;
    }

    public static boolean setFactoryPowerMode(VestelFactoryPowerMode factoryPowerMode) {
        return false;
    }

    public static boolean isWifiWakeOnLanEnabled() {
        return false;
    }

    public static boolean setWifiWakeOnLanEnabled(boolean isEnabled) {
        return false;
    }

    public static boolean isEthernetWakeOnLanEnabled() {
        return false;
    }

    public static boolean setEthernetWakeOnLanEnabled(boolean isEnabled) {
        return false;
    }

    // TODO: move this method to VestelSoundSettings class
    public static int getSoundSpeakerDelay() {
        return -1;
    }

    // TODO: move this method to VestelSoundSettings class
    public static int getSoundSpeakerDelayMinValue() {
        return SOUND_SPEAKER_DELAY_MIN_VALUE;
    }

    // TODO: move this method to VestelSoundSettings class
    public static int getSoundSpeakerDelayMaxValue() {
        return SOUND_SPEAKER_DELAY_MAX_VALUE;
    }

    // TODO: move this method to VestelSoundSettings class
    public static boolean setSoundSpeakerDelay(int delay) {
        return false;
    }

    // TODO: replace VestelStandbyManager.getWakeupReason() with this method
    public static VestelWakeUpReason getWakeUpReason() {
        return VestelWakeUpReason.AC_POWER;
    }

    private static int vestelToMediatekTimeSyncSource(VestelTimeSyncSource timeSyncSource) {
        return 0;
    }

    private static VestelFactoryPowerMode mediatekToVestelFactoryPowerMode() {

        return VestelFactoryPowerMode.INVALID;

    }

    private static VestelWakeUpReason mediatekToVestelWakeUpReason(int reason) {
        switch (reason) {
            case 2:
                return VestelWakeUpReason.VGA;
            case 3:
                return VestelWakeUpReason.RTC;
            case 4:
                return VestelWakeUpReason.FP;
            case 5:
                return VestelWakeUpReason.IIRC;
            case 6:
                return VestelWakeUpReason.UART;
            case 7:
                return VestelWakeUpReason.AC_POWER;
            case 8:
                return VestelWakeUpReason.HDMI;
            case 9:
                return VestelWakeUpReason.UART_NORMAL;
            case 10:
                return VestelWakeUpReason.RC_DIGIT_0;
            case 11:
                return VestelWakeUpReason.RC_DIGIT_1;
            case 12:
                return VestelWakeUpReason.RC_DIGIT_2;
            case 13:
                return VestelWakeUpReason.RC_DIGIT_3;
            case 14:
                return VestelWakeUpReason.RC_DIGIT_4;
            case 15:
                return VestelWakeUpReason.RC_DIGIT_5;
            case 16:
                return VestelWakeUpReason.RC_DIGIT_6;
            case 17:
                return VestelWakeUpReason.RC_DIGIT_7;
            case 18:
                return VestelWakeUpReason.RC_DIGIT_8;
            case 19:
                return VestelWakeUpReason.RC_DIGIT_9;
            case 20:
                return VestelWakeUpReason.RC_PROGRAM_UP;
            case 21:
                return VestelWakeUpReason.RC_PROGRAM_DOWN;
            case 22:
                return VestelWakeUpReason.RC_INPUT_SOURCE;
            case 23:
                return VestelWakeUpReason.RC_ANALOG;
            case 24:
                return VestelWakeUpReason.RC_DIGITAL;
            case 25:
                return VestelWakeUpReason.RC_DIGITAL_ANALOG;
            case 26:
                return VestelWakeUpReason.FP_PROGRAM_UP;
            case 27:
                return VestelWakeUpReason.FP_PROGRAM_DOWN;
            case 28:
                return VestelWakeUpReason.FP_INPUT_SOURCE;
            case 29:
                return VestelWakeUpReason.DVD;
            case 30:
                return VestelWakeUpReason.RTC_SPECIAL;
            case 31:
                return VestelWakeUpReason.WATCHDOG;
            case 32:
                return VestelWakeUpReason.SCART;
            case 33:
                return VestelWakeUpReason.ETHERNET;
            case 34:
                return VestelWakeUpReason.RESUME_FROM_SUSPEND;
            case 35:
                return VestelWakeUpReason.WIFI;
            case 36:
                return VestelWakeUpReason.BLUETOOTH;
            default:
                return VestelWakeUpReason.UNKNOWN;
        }
    }

}
