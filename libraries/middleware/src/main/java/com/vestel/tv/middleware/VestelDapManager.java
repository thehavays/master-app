package com.vestel.tv.middleware;

public class VestelDapManager {

    private static final String TAG = "VestelDapManager";

    static {
        VestelConfig.init();
    }

    private static final String DAP_INI_FILE_PATH_TEMPLATE = VestelConfig.getAqDapFolder() + "%s.ini";

    private static final String DAP_PARAMETER_KEY = "DAP_PARAM:";
    private static final String SURROUND_DECODER_ENABLE_KEY = "intermediate_profile_surround-decoder-enable";
    private static final String SURROUND_BOOST_KEY = "surround-boost";
    private static final String VOLUME_LEVELER_AMOUNT_KEY = "volume-leveler-amount";
    private static final String VOLUME_LEVELER_IN_TARGET = "volume-leveler-in-target";
    private static final String VOLUME_LEVELER_OUT_TARGET = "volume-leveler-out-target";
    private static final String VOLUME_LEVELER_ENABLE_KEY = "volume-leveler-enable";
    private static final String IEQ_ENABLE_KEY = "ieq-enable";
    private static final String DIALOG_ENHANCER_ENABLE_KEY = "dialog-enhancer-enable";
    private static final String DIALOG_ENHANCER_AMOUNT_KEY = "dialog-enhancer-amount";
    private static final String AUDIO_OPTIMIZER_ENABLE_KEY = "intermediate_profile_audio-optimizer-enable";
    private static final String REGULATOR_ENABLE_KEY = "intermediate_profile_regulator-enable";
    private static final String VIRTUAL_BASS_ENABLE_KEY = "intermediate_profile_partial_virtual_bass_enable";


    public static boolean isSurroundDecoderEnabled(String iniFileName) {
        return getBooleanDapParameter(iniFileName, DAP_PARAMETER_KEY + SURROUND_DECODER_ENABLE_KEY);
    }

    public static boolean setSurroundDecoderEnabled(String iniFileName, boolean isEnabled) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + SURROUND_DECODER_ENABLE_KEY, isEnabled);
    }

    public static int getSurroundBoost(String iniFileName) {
        return getIntDapParameter(iniFileName, DAP_PARAMETER_KEY + SURROUND_BOOST_KEY);
    }

    public static boolean setSurroundBoost(String iniFileName, int value) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + SURROUND_BOOST_KEY, value);
    }

    public static int getVolumeLevelerAmount(String iniFileName) {
        return getIntDapParameter(iniFileName, DAP_PARAMETER_KEY + VOLUME_LEVELER_AMOUNT_KEY);
    }

    public static boolean setVolumeLevelerAmount(String iniFileName, int value) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + VOLUME_LEVELER_AMOUNT_KEY, value);
    }

    public static int getVolumeLevelerInTarget(String iniFileName) {
        return getIntDapParameter(iniFileName, DAP_PARAMETER_KEY + VOLUME_LEVELER_IN_TARGET);
    }

    public static boolean setVolumeLevelerInTarget(String iniFileName, int value) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + VOLUME_LEVELER_IN_TARGET, value);
    }

    public static int getVolumeLevelerOutTarget(String iniFileName) {
        return getIntDapParameter(iniFileName, DAP_PARAMETER_KEY + VOLUME_LEVELER_OUT_TARGET);
    }

    public static boolean setVolumeLevelerOutTarget(String iniFileName, int value) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + VOLUME_LEVELER_OUT_TARGET, value);
    }

    public static boolean isVolumeLevelerEnabled(String iniFileName) {
        return getBooleanDapParameter(iniFileName, DAP_PARAMETER_KEY + VOLUME_LEVELER_ENABLE_KEY);
    }

    public static boolean setVolumeLevelerEnabled(String iniFileName, boolean isEnabled) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + VOLUME_LEVELER_ENABLE_KEY, isEnabled);
    }

    public static boolean isIeqEnabled(String iniFileName) {
        return getBooleanDapParameter(iniFileName, DAP_PARAMETER_KEY + IEQ_ENABLE_KEY);
    }

    public static boolean setIeqEnabled(String iniFileName, boolean isEnabled) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + IEQ_ENABLE_KEY, isEnabled);
    }

    public static boolean isDialogEnhancerEnabled(String iniFileName) {
        return getBooleanDapParameter(iniFileName, DAP_PARAMETER_KEY + DIALOG_ENHANCER_ENABLE_KEY);
    }

    public static boolean setDialogEnhancerEnabled(String iniFileName, boolean isEnabled) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + DIALOG_ENHANCER_ENABLE_KEY, isEnabled);
    }

    public static int getDialogEnhancerAmount(String iniFileName) {
        return getIntDapParameter(iniFileName, DAP_PARAMETER_KEY + DIALOG_ENHANCER_AMOUNT_KEY);
    }

    public static boolean setDialogEnhancerAmount(String iniFileName, int value) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + DIALOG_ENHANCER_AMOUNT_KEY, value);
    }

    public static boolean isAudioOptimizerEnabled(String iniFileName) {
        return getBooleanDapParameter(iniFileName, DAP_PARAMETER_KEY + AUDIO_OPTIMIZER_ENABLE_KEY);
    }

    public static boolean setAudioOptimizerEnabled(String iniFileName, boolean isEnabled) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + AUDIO_OPTIMIZER_ENABLE_KEY, isEnabled);
    }

    public static boolean isRegulatorEnabled(String iniFileName) {
        return getBooleanDapParameter(iniFileName, DAP_PARAMETER_KEY + REGULATOR_ENABLE_KEY);
    }

    public static boolean setRegulatorEnabled(String iniFileName, boolean isEnabled) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + REGULATOR_ENABLE_KEY, isEnabled);
    }

    public static boolean isVirtualBassEnabled(String iniFileName) {
        return getBooleanDapParameter(iniFileName, DAP_PARAMETER_KEY + VIRTUAL_BASS_ENABLE_KEY);
    }

    public static boolean setVirtualBassEnabled(String iniFileName, boolean isEnabled) {
        return setDapParameter(iniFileName, DAP_PARAMETER_KEY + VIRTUAL_BASS_ENABLE_KEY, isEnabled);
    }

    private static boolean getBooleanDapParameter(String iniFileName, String key) {
        final String iniFilePath = String.format(DAP_INI_FILE_PATH_TEMPLATE, iniFileName);
        final String value = VestelFileSystem.getIniParameter(iniFilePath, key);

        boolean isEnabled = false;
        try {
            isEnabled = Integer.parseInt(value) == 1;
        } catch (Exception exception) {
            VestelLog.error(TAG, exception.toString());
        }

        return isEnabled;
    }

    private static int getIntDapParameter(String iniFileName, String key) {
        final String iniFilePath = String.format(DAP_INI_FILE_PATH_TEMPLATE, iniFileName);
        final String value = VestelFileSystem.getIniParameter(iniFilePath, key);

        int intValue = 0;
        try {
            intValue = Integer.parseInt(value);
        } catch (Exception exception) {
            VestelLog.error(TAG, exception.toString());
        }

        return intValue;
    }

    private static boolean setDapParameter(String iniFileName, String key, boolean value) {
        return setDapParameter(iniFileName, key, value ? "1" : "0");
    }

    private static boolean setDapParameter(String iniFileName, String key, int value) {
        return setDapParameter(iniFileName, key, Integer.toString(value));
    }

    private static boolean setDapParameter(String iniFileName, String key, String value) {
        final String iniFilePath = String.format(DAP_INI_FILE_PATH_TEMPLATE, iniFileName);
        boolean result = VestelFileSystem.setIniParameter(iniFilePath, key, value);
        return result && loadAqParamFromIni(iniFilePath);
    }

    private static boolean loadAqParamFromIni(String iniFilePath) {
        return false;
    }

}
