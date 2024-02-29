package com.vestel.tv.middleware;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings.Global;


import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.Date;
import java.util.HashMap;

/**
 * @brief This class manages picture settings of Android device
 */
@SuppressWarnings("WeakerAccess")
public class VestelPictureSettings {
    private static final String TAG = "VestelPictureSettings";


    private static ContentResolver m_content_resolver = null;
    private static final VestelVideoInformation m_video_information = VestelVideoInformation.getInstance();

    private static int m_period_pwm = 65535;
    private static boolean isDispout_panel = true;

    private static final int[][] m_default_backlight_slider_percentages = new int[VestelPictureMode.values().length][2];

    private static final HashMap<Integer, VestelPictureMode> m_int_to_picture_mode_map = new HashMap<Integer, VestelPictureMode>();
    private static final HashMap<VestelPictureMode, Integer> m_picture_mode_to_int_map = new HashMap<VestelPictureMode, Integer>();

    // returns 8192 when backlight slider set to 0
    // returns 65535 when backlight slider set to 100

    // UPDATE 1: returns 2304 when backlight slider set to 0
    // UPDATE 1: returns 40000 when backlight slider set to 100

    /**
     * @param context Android application context
     * @brief Initializes the class with the given Android application context
     */
    public static void init(Context context) {
        m_content_resolver = context.getContentResolver();

        String str = VestelFileSystem.getIniParameter(VestelConfig.getPanelFile(), "panel:u32PeriodPWM");

        try {
            m_period_pwm = Integer.decode(str);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }

        initDefaultBacklightSliderPercentages();
        initPictureModeMap();
    }

    /**
     * @param value Picture mode defined by SoC provider
     * @return One of the values defined in VestelPictureMode enumeration
     * @brief Converts picture modes defined by SoC provider to VestelPictureMode enumeration values
     */
    private static VestelPictureMode convertPictureMode(int value) {
        VestelPictureMode picture_mode = m_int_to_picture_mode_map.get(Integer.valueOf(value));

        if (picture_mode == null) {
            picture_mode = VestelPictureMode.PICTURE_MODE_INVALID;
        }

        return picture_mode;
    }

    /**
     * @param picture_mode One of the values defined in VestelPictureMode enumeration
     * @return Picture mode defined by SoC provider
     * @brief Converts VestelPictureMode enumeration values to picture modes defined by SoC provider
     */
    private static int convertPictureMode(VestelPictureMode picture_mode) {
        Integer value = m_picture_mode_to_int_map.get(picture_mode);

        if (picture_mode == null) {
            picture_mode = VestelPictureMode.PICTURE_MODE_INVALID;
        }

        return (value == null) ? -1 : value.intValue();
    }

    /**
     * @param mode One of the values defined in VestelPictureMode enumeration
     * @return String representation of VestelPictureMode enumeration value
     * @brief Returns string representation of VestelPictureMode enumeration values
     */
    public static String convertToString(VestelPictureMode mode) {
        String str;

        switch (mode) {
            // STANDARD PICTURE MODES

            case PICTURE_MODE_USER:
                str = "User";
                break;

            case PICTURE_MODE_STANDARD:
                str = "Standard";
                break;

            case PICTURE_MODE_VIVID:
                str = "Vivid";
                break;

            case PICTURE_MODE_SPORT:
                str = "Sports";
                break;

            case PICTURE_MODE_MOVIE:
                str = "Movie";
                break;

            case PICTURE_MODE_GAME:
                str = "Game";
                break;

            case PICTURE_MODE_ENERGY_SAVING:
                str = "Energy_saving";
                break;

            case PICTURE_MODE_HI_BRIGHT:
                str = "Hi_bright";
                break;

            case PICTURE_MODE_AI_PQ:
                str = "Ai_pq";
                break;

            case PICTURE_MODE_IMAX:
                str = "Imax";
                break;

            // HDR PICTURE MODES

            case PICTURE_MODE_USER_HDR:
                str = "User(HDR10)";
                break;

            case PICTURE_MODE_STANDARD_HDR:
                str = "Standard(HDR10)";
                break;

            case PICTURE_MODE_VIVID_HDR:
                str = "Vivid(HDR10)";
                break;

            case PICTURE_MODE_SPORT_HDR:
                str = "Sports(HDR10)";
                break;

            case PICTURE_MODE_MOVIE_HDR:
                str = "Movie(HDR10)";
                break;

            case PICTURE_MODE_GAME_HDR:
                str = "Game(HDR10)";
                break;

            case PICTURE_MODE_ENERGY_SAVING_HDR:
                str = "Energy_saving(HDR10)";
                break;

            // HDR PLUS PICTURE MODES

            case PICTURE_MODE_USER_HDR_PLUS:
                str = "User(HDR10+)";
                break;

            case PICTURE_MODE_STANDARD_HDR_PLUS:
                str = "Standard(HDR10+)";
                break;

            case PICTURE_MODE_VIVID_HDR_PLUS:
                str = "Vivid(HDR10+)";
                break;

            case PICTURE_MODE_SPORT_HDR_PLUS:
                str = "Sports(HDR10+)";
                break;

            case PICTURE_MODE_MOVIE_HDR_PLUS:
                str = "Movie(HDR10+)";
                break;

            case PICTURE_MODE_GAME_HDR_PLUS:
                str = "Game(HDR10+)";
                break;

            case PICTURE_MODE_ENERGY_SAVING_HDR_PLUS:
                str = "Energy_saving(HDR10+)";
                break;

            // HLG PICTURE MODES

            case PICTURE_MODE_USER_HLG:
                str = "User(HLG)";
                break;

            case PICTURE_MODE_STANDARD_HLG:
                str = "Standard(HLG)";
                break;

            case PICTURE_MODE_VIVID_HLG:
                str = "Vivid(HLG)";
                break;

            case PICTURE_MODE_SPORT_HLG:
                str = "Sports(HLG)";
                break;

            case PICTURE_MODE_MOVIE_HLG:
                str = "Movie(HLG)";
                break;

            case PICTURE_MODE_GAME_HLG:
                str = "Game(HLG)";
                break;

            case PICTURE_MODE_ENERGY_SAVING_HLG:
                str = "Energy_saving(HLG)";
                break;

            // DOLBY VISION PICTURE MODES

            case PICTURE_MODE_DOLBY_VISION_BRIGHT:
                str = "Dolby_Vision_Bright";
                break;

            case PICTURE_MODE_DOLBY_VISION_DARK:
                str = "Dolby_Vision_Dark";
                break;

            case PICTURE_MODE_DOLBY_VISION_VIVID:
                str = "Dolby_Vision_Vivid";
                break;

            case PICTURE_MODE_DOLBY_VISION_GAME:
                str = "Dolby_Vision_Game";
                break;

            case PICTURE_MODE_DOLBY_VISION_IQ:
                str = "Dolby_Vision_IQ";
                break;

            // FILMMAKER PICTURE MODES

            case PICTURE_MODE_FILMMAKER:
                str = "Filmmaker";
                break;

            case PICTURE_MODE_FILMMAKER_HDR:
                str = "Filmmaker(HDR)";
                break;

            case PICTURE_MODE_FILMMAKER_DOLBY_VISION:
                str = "Filmmaker(Dolby_Vision)";
                break;

            case PICTURE_MODE_FILMMAKER_DOLBY_VISION_IQ:
                str = "Filmmaker(Dolby_Vision_IQ)";
                break;

            default:
                str = "Invalid";
                break;
        }

        return str;
    }

    private static final String KEY_PICTURE_MODE = "picture_mode";
    private static final String KEY_PREVIOUS_PICTURE_MODE = "previous_picture_mode";

    /**
     * @return One of the values defined in VestelPictureMode enumeration
     * @brief Returns the picture mode currently selected from picture settings menu
     */
    public static VestelPictureMode getCurrentPictureMode() {
        return convertPictureMode(Global.getInt(m_content_resolver, KEY_PICTURE_MODE, 0));
    }

    /**
     * @return One of the values defined in VestelPictureMode enumeration
     * @brief Returns the picture mode proviously selected from picture settings menu
     */
    public static VestelPictureMode getPreviousPictureMode() {
        return convertPictureMode(Global.getInt(m_content_resolver, KEY_PREVIOUS_PICTURE_MODE, -1));
    }

    /**
     * @param previous_picture_mode One of the values defined in VestelPictureMode enumeration
     * @return True if the picture mode is saved successfully, false otherwise
     * @brief Saves the picture mode proviously selected from picture settings menu in non-volatile storage
     */
    public static boolean setPreviousPictureMode(VestelPictureMode previous_picture_mode) {
        boolean success = false;

        if (previous_picture_mode != VestelPictureMode.PICTURE_MODE_USER &&
                previous_picture_mode != VestelPictureMode.PICTURE_MODE_USER_HDR &&
                previous_picture_mode != VestelPictureMode.PICTURE_MODE_USER_HDR_PLUS &&
                previous_picture_mode != VestelPictureMode.PICTURE_MODE_USER_HLG &&
                previous_picture_mode != VestelPictureMode.PICTURE_MODE_INVALID) {
            int value = convertPictureMode(previous_picture_mode);
            success = Global.putInt(m_content_resolver, KEY_PREVIOUS_PICTURE_MODE, value);
        }

        return success;
    }

    /**
     * @param value Backlight mode defined by SoC provider
     * @return One of the values defined in VestelBacklightMode enumeration
     * @brief Converts backlight modes defined by SoC provider to VestelBacklightMode enumeration values
     */
    private static VestelBacklightMode convertBacklightMode(int value) {
        VestelBacklightMode mode;

        switch (value) {
            case 0:
                mode = VestelBacklightMode.BACKLIGHT_MODE_OFF;
                break;

            case 1:
                mode = VestelBacklightMode.BACKLIGHT_MODE_MIN;
                break;

            case 2:
                mode = VestelBacklightMode.BACKLIGHT_MODE_MED;
                break;

            case 3:
                mode = VestelBacklightMode.BACKLIGHT_MODE_ECO;
                break;

            default:
                mode = VestelBacklightMode.BACKLIGHT_MODE_INVALID;
                break;
        }

        return mode;
    }

    /**
     * @param mode One of the values defined in VestelBacklightMode enumeration
     * @return Backlight mode defined by SoC provider
     * @brief Converts VestelBacklightMode enumeration values to backlight modes defined by SoC provider
     */
    private static int convertBacklightMode(VestelBacklightMode mode) {
        int value;

        switch (mode) {
            case BACKLIGHT_MODE_OFF:
                value = 0;
                break;

            case BACKLIGHT_MODE_MIN:
                value = 1;
                break;

            case BACKLIGHT_MODE_MED:
                value = 2;
                break;

            case BACKLIGHT_MODE_ECO:
                value = 3;
                break;

            default:
                value = -1;
                break;
        }

        return value;
    }

    /**
     * @param mode One of the values defined in VestelBacklightMode enumeration
     * @return String representation of VestelBacklightMode enumeration value
     * @brief Returns string representation of VestelBacklightMode enumeration values
     */
    public static String convertToString(VestelBacklightMode mode) {
        String str;

        switch (mode) {
            case BACKLIGHT_MODE_OFF:
                str = "Off";
                break;

            case BACKLIGHT_MODE_MIN:
                str = "Low/Min";
                break;

            case BACKLIGHT_MODE_MED:
                str = "Middle/Med";
                break;

            case BACKLIGHT_MODE_ECO:
                str = "High/Eco";
                break;

            default:
                str = "Invalid";
                break;
        }

        return str;
    }

    public static final String KEY_PICTURE_LIGHT_SENSE = "tv_picture_video_light_sense";

    public static boolean isLightSensorEnabled() {
        if (m_content_resolver != null) {
            return (Global.getInt(m_content_resolver, KEY_PICTURE_LIGHT_SENSE, 0) != 0);
        }

        return false;
    }

    public static void setLightSensorEnabled(boolean enabled) {
        if (m_content_resolver != null) {
            Global.putInt(m_content_resolver, KEY_PICTURE_LIGHT_SENSE, enabled ? 1 : 0);
        }
    }

    public static final String KEY_PICTURE_ALS = "picture_als";

    public static boolean isAutoBrightnessEnabled() {
        if (m_content_resolver != null) {
            return (Global.getInt(m_content_resolver, KEY_PICTURE_ALS, 0) != 0);
        }

        return false;
    }

    public static void setAutoBrightnessEnabled(boolean enabled) {
        if (m_content_resolver != null) {
            Global.putInt(m_content_resolver, KEY_PICTURE_ALS, enabled ? 1 : 0);
        }
    }

    private static final String KEY_PICTURE_AUTO_BACKLIGHT = "picture_auto_backlight";
    private static final String KEY_PICTURE_AUTO_BACKLIGHT_USER = "key_picture_mode_user_auto_backlight";
    private static final String KEY_PICTURE_AUTO_BACKLIGHT_STANDARD = "key_picture_mode_standard_auto_backlight";
    private static final String KEY_PICTURE_AUTO_BACKLIGHT_VIVID = "key_picture_mode_vivid_auto_backlight";
    private static final String KEY_PICTURE_AUTO_BACKLIGHT_SPORT = "key_picture_mode_sport_auto_backlight";
    private static final String KEY_PICTURE_AUTO_BACKLIGHT_MOVIE = "key_picture_mode_movie_auto_backlight";
    private static final String KEY_PICTURE_AUTO_BACKLIGHT_GAME = "key_picture_mode_game_auto_backlight";
    private static final String KEY_PICTURE_AUTO_BACKLIGHT_ENERGY_SAVING = "key_picture_mode_energy_saving_auto_backlight";
    private static final String KEY_PICTURE_AUTO_BACKLIGHT_OTHERS = "key_picture_mode_others_auto_backlight";

    /**
     * @return One of the values defined in VestelBacklightMode enumeration
     * @brief Returns the backlight mode currently selected from picture settings menu
     */
    public static VestelBacklightMode getCurrentBacklightMode() {
        String key = KEY_PICTURE_AUTO_BACKLIGHT;
        int default_value = 0;

        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type) {
            case MEDUSA:
            case COMA:
            case NEEDLE:
            case MALIN:
            case HOCKEY:
            case SUNFLOWER: {
                VestelPictureMode picture_mode = getCurrentPictureMode();

                switch (picture_mode) {
                    case PICTURE_MODE_STANDARD:
                        key = KEY_PICTURE_AUTO_BACKLIGHT_STANDARD;
                        break;

                    case PICTURE_MODE_VIVID:
                        key = KEY_PICTURE_AUTO_BACKLIGHT_VIVID;
                        break;

                    case PICTURE_MODE_SPORT:
                        key = KEY_PICTURE_AUTO_BACKLIGHT_SPORT;
                        break;

                    case PICTURE_MODE_MOVIE:
                        key = KEY_PICTURE_AUTO_BACKLIGHT_MOVIE;
                        break;

                    case PICTURE_MODE_GAME:
                        key = KEY_PICTURE_AUTO_BACKLIGHT_GAME;
                        break;

                    case PICTURE_MODE_ENERGY_SAVING:
                        key = KEY_PICTURE_AUTO_BACKLIGHT_ENERGY_SAVING;
                        break;

                    case PICTURE_MODE_USER:
                        key = KEY_PICTURE_AUTO_BACKLIGHT_USER;
                        break;

                    default:
                        key = KEY_PICTURE_AUTO_BACKLIGHT_OTHERS;
                        break;
                }

                if (key.equals(KEY_PICTURE_AUTO_BACKLIGHT_STANDARD)) {
                    default_value = 3;
                }
            }
            break;

            default:
                break;
        }

        return convertBacklightMode(Global.getInt(m_content_resolver, key, default_value));
    }

    public static void setBacklightMode(VestelBacklightMode backlight_mode, boolean apply_to_all_picture_modes) {
        VestelProjectType project_type = VestelSystemProperties.getProjectType();
        int value = convertBacklightMode(backlight_mode);

        if (apply_to_all_picture_modes) {
            switch (project_type) {
                case MEDUSA:
                case COMA:
                case NEEDLE:
                case MALIN:
                case HOCKEY:
                case SUNFLOWER: {
                    Global.putInt(m_content_resolver, KEY_PICTURE_AUTO_BACKLIGHT_STANDARD, value);
                    Global.putInt(m_content_resolver, KEY_PICTURE_AUTO_BACKLIGHT_VIVID, value);
                    Global.putInt(m_content_resolver, KEY_PICTURE_AUTO_BACKLIGHT_SPORT, value);
                    Global.putInt(m_content_resolver, KEY_PICTURE_AUTO_BACKLIGHT_MOVIE, value);
                    Global.putInt(m_content_resolver, KEY_PICTURE_AUTO_BACKLIGHT_GAME, value);
                    Global.putInt(m_content_resolver, KEY_PICTURE_AUTO_BACKLIGHT_ENERGY_SAVING, value);
                    Global.putInt(m_content_resolver, KEY_PICTURE_AUTO_BACKLIGHT_USER, value);
                    Global.putInt(m_content_resolver, KEY_PICTURE_AUTO_BACKLIGHT_OTHERS, value);
                }
                break;

                default:
                    Global.putInt(m_content_resolver, KEY_PICTURE_AUTO_BACKLIGHT, value);
                    break;
            }

            VestelLog.verbose(TAG, "setBacklightModeForAll: " + backlight_mode);
        } else {
            String key = KEY_PICTURE_AUTO_BACKLIGHT;

            switch (project_type) {
                case MEDUSA:
                case COMA:
                case NEEDLE:
                case MALIN:
                case HOCKEY:
                case SUNFLOWER: {
                    VestelPictureMode picture_mode = getCurrentPictureMode();

                    switch (picture_mode) {
                        case PICTURE_MODE_STANDARD:
                            key = KEY_PICTURE_AUTO_BACKLIGHT_STANDARD;
                            break;

                        case PICTURE_MODE_VIVID:
                            key = KEY_PICTURE_AUTO_BACKLIGHT_VIVID;
                            break;

                        case PICTURE_MODE_SPORT:
                            key = KEY_PICTURE_AUTO_BACKLIGHT_SPORT;
                            break;

                        case PICTURE_MODE_MOVIE:
                            key = KEY_PICTURE_AUTO_BACKLIGHT_MOVIE;
                            break;

                        case PICTURE_MODE_GAME:
                            key = KEY_PICTURE_AUTO_BACKLIGHT_GAME;
                            break;

                        case PICTURE_MODE_ENERGY_SAVING:
                            key = KEY_PICTURE_AUTO_BACKLIGHT_ENERGY_SAVING;
                            break;

                        case PICTURE_MODE_USER:
                            key = KEY_PICTURE_AUTO_BACKLIGHT_USER;
                            break;

                        default:
                            key = KEY_PICTURE_AUTO_BACKLIGHT_OTHERS;
                            break;
                    }
                }
                break;

                default:
                    break;
            }

            Global.putInt(m_content_resolver, key, value);
            VestelLog.verbose(TAG, "setBacklightMode: " + backlight_mode);
        }
    }

    /**
     * @return Backlight dimming of the panel in [0-255] range
     * @brief Returns backlight dimming of the panel
     * @note Zero value denotes no backlight dimming, not the backlight dimming
     * when the backlight slider in picture settings is at the minimum
     */
    public static int getBacklightDimming() {
        int dimming;

        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type) {
            case MEDUSA:
            case COMA:
            case NEEDLE:
            case MALIN:
            case HOCKEY:
            case SUNFLOWER: {
                byte[] value = {0x00};
                int success = -1;
                VestelLog.verbose(TAG, "getBacklightDimming: " + "dimming_hex:" +
                        String.format("0x%02X", value[0]) + ", success:" + success);

                dimming = Byte.toUnsignedInt(value[0]);

                if (success == 0) {
                    VestelLog.verbose(TAG, "getBacklightDimming: " + "dimming:" + dimming + "(0-255)");
                } else {
                    VestelLog.verbose(TAG, "getBacklightDimming: failed");
                }
            }
            break;

            default: {
                VestelLog.verbose(TAG, "getBacklightDimming: " + "period_pwm:" + m_period_pwm);

                dimming = -1;
                VestelLog.verbose(TAG, "getBacklightDimming: " + "dimming:" + dimming + "(0-65535)");

                if (m_period_pwm > 0) {
                    dimming = 255 * dimming / m_period_pwm;
                }

                VestelLog.verbose(TAG, "getBacklightDimming: " + "dimming:" + dimming + "(0-255)");
            }
            break;
        }

        return dimming;
    }

    /**
     * @param percentage    Backlight dimming to be set in [0-100] range
     * @param update_slider True if the backlight slider in picture settings
     *                      should be updated after backlight adjustment, false otherwise
     * @brief Sets backlight dimming of the panel in percentage
     */
    public static void setBacklightDimming(int percentage, boolean update_slider) {
    }

    /**
     * @param dimming           Backlight dimming to be set in [0-255] range
     * @param update_slider     True if the backlight slider in picture settings
     *                          should be updated after backlight adjustment, false otherwise
     * @param slider_percentage New position of backlight slider in [0-100] range, negative values
     *                          demote that backlight slider should be updated based on the new backlight dimming
     * @brief Sets backlight dimming of the panel
     * @note Zero value denotes no backlight dimming, not the backlight dimming
     * when the backlight slider in picture settings is at the minimum
     */
    public static void setBacklightDimming(int dimming, boolean update_slider, int slider_percentage) {
    }

    /**
     * @brief Sets backlight dimming for store mode of Android device
     */
    public static void setBacklightDimmingForStoreMode() {
    }

    /**
     * @brief Sets picture mode for store mode of Android device
     */
    public static void adjustPictureModeForStoreMode() {
    }

    private static final String KEY_PICTURE_BACKLIGHT = "picture_backlight";

    /**
     * @return The position of backlight slider in picture settings menu in [0-100] range
     * @brief Returns the position of backlight slider in picture settings menu
     */
    public static int getBacklightSliderPercentage() {
        return Global.getInt(m_content_resolver, KEY_PICTURE_BACKLIGHT, -1);
    }

    /**
     * @param percentage The position to be set in [0-100] range
     * @return True if backlight slider position is successfully set, false otherwise
     * @brief Sets the position of backlight slider in picture settings menu
     */
    public static boolean setBacklightSliderPercentage(int percentage) {
        VestelLog.verbose(TAG, "setBacklightSliderPercentage: " + percentage);
        return Global.putInt(m_content_resolver, KEY_PICTURE_BACKLIGHT, percentage);
    }

    /**
     * @return Average pixel luminance of the panel in [0-255] range or zero if not available
     * @brief Returns average pixel luminance of the panel
     */
    public static int getAPL() {
        return -1;
    }

    /**
     * @return An integer array of length 32 that represents luma histogram
     * or zero length array if luma histogram is not available
     * Integer at index 0 represents pixels with minimum luma, ie. black pixels
     * Integer at index 31 represents pixels with maximum luma, ie. white pixels
     * @brief Returns luma histogram of video snapshot
     */
    @SuppressWarnings("MethodWithMultipleReturnPoints")
    public static int[] getLumaHistogram() {
        return new int[0];
    }

    private static final double default_histogram_similarity_percentage = 0.5;

    /**
     * @param histogram1 Luma histogram of the first video snapshot
     * @param histogram2 Luma histogram of the second video snapshot
     * @return True if luma histograms are similar, false otherwise
     * @brief Checks similarity of two luma histograms to estimate similarity of video snapshots
     */
    public static boolean getLumaHistogramSimilarity(int[] histogram1, int[] histogram2) {
        int sum1 = 0;
        int sum2 = 0;
        int difference = 0;

        double percentage;

        int size1 = histogram1.length;
        int size2 = histogram2.length;

        if (size1 == 0 && size2 == 0) {
            // comparison of two black pictures
        } else if (size1 == 0) {
            for (int j = 0; j < size2; j++) {
                sum2 += histogram2[j];
                difference += (j == 0) ? 0 : histogram2[j];
            }

            difference = Math.abs(sum2 - histogram2[0]);
        } else if (size2 == 0) {
            for (int j = 0; j < size1; j++) {
                sum1 += histogram1[j];
                difference += (j == 0) ? 0 : histogram1[j];
            }

            difference = Math.abs(sum1 - histogram1[0]);
        } else if (size1 == size2) {
            for (int j = 0; j < size1; j++) {
                sum1 += histogram1[j];
                sum2 += histogram2[j];
                difference += Math.abs(histogram1[j] - histogram2[j]);
            }
        }

        String prop_value = "persist.debug.vendor.vestel.histogram_similarity_percentage";

        try {
            percentage = Double.parseDouble(prop_value);
        } catch (NumberFormatException e) {
            percentage = default_histogram_similarity_percentage;
        }

        int threshold = (int) (((double) Math.max(sum1, sum2) * percentage) / (double) 100);
        boolean result = (difference <= threshold);

        VestelLog.debug(TAG, "getLumaHistogramSimilarity Percentage: " + percentage);
        VestelLog.debug(TAG, "getLumaHistogramSimilarity Histogram sum1: " + sum1);
        VestelLog.debug(TAG, "getLumaHistogramSimilarity Histogram sum2: " + sum2);
        VestelLog.debug(TAG, "getLumaHistogramSimilarity Difference: " + difference);
        VestelLog.debug(TAG, "getLumaHistogramSimilarity Threshold: " + threshold);
        VestelLog.debug(TAG, "getLumaHistogramSimilarity Similar: " + result);

        return result;
    }

    private static final String KEY_LAST_LIVETV_PICTURE_MODE = "last_live_tv_picture_mode";

    /**
     * @return One of the values defined in VestelPictureMode enumeration
     * @brief Returns the picture mode that was selected when Live TV application was at foreground
     */
    public static VestelPictureMode getLastLiveTvPictureMode() {
        return convertPictureMode(Global.getInt(m_content_resolver, KEY_LAST_LIVETV_PICTURE_MODE, -1));
    }

    /**
     * @param mode One of the values defined in VestelPictureMode enumeration
     * @return True if the picture mode is saved successfully, false otherwise
     * @brief Saves the picture mode that was selected when Live TV application was at foreground
     */
    public static boolean setLastLiveTvPictureMode(VestelPictureMode mode) {
        int value = convertPictureMode(mode);
        return Global.putInt(m_content_resolver, KEY_LAST_LIVETV_PICTURE_MODE, value);
    }

    /**
     * @return The number of times "reset to default" action is triggered from picture menu
     * @brief Returns the number of times "reset to default" action is triggered from picture menu
     */
    public static int getResetToDefaultCounter() {
        return Global.getInt(m_content_resolver, "picture_reset_to_default", 0);
    }

    /**
     * @return True if backlight dimming has been previously altered with test tool command, false otherwise
     * @brief Checks if backlight dimming has been previously altered with test tool command
     */
    public static boolean getBacklightTestFlag() {
        return (Global.getInt(m_content_resolver, "backlight_test_flag", 0) > 0);
    }

    /**
     * @param flag True if backlight dimming has been previously altered with test tool command, false otherwise
     * @brief Notifies if backlight dimming has been previously altered with test tool command
     */
    public static void setBacklightTestFlag(boolean flag) {
        Global.putInt(m_content_resolver, "backlight_test_flag", flag ? 1 : 0);
    }

    private static final String KEY_LAST_LIVETV_BACKLIGHT_MODE = "last_live_tv_backlight_mode";

    /**
     * @return One of the values defined in VestelBacklightMode enumeration
     * @brief Returns the backlight mode that was selected when Live TV application was at foreground
     */
    public static VestelBacklightMode getLastLiveTvBacklightMode() {
        return convertBacklightMode(Global.getInt(m_content_resolver, KEY_LAST_LIVETV_BACKLIGHT_MODE, -1));
    }

    /**
     * @param mode One of the values defined in VestelBacklightMode enumeration
     * @return True if the backlight mode is saved successfully, false otherwise
     * @brief Saves the backlight mode that was selected when Live TV application was at foreground
     */
    public static boolean setLastLiveTvBacklightMode(VestelBacklightMode mode) {
        int value = convertBacklightMode(mode);
        return Global.putInt(m_content_resolver, KEY_LAST_LIVETV_BACKLIGHT_MODE, value);
    }

    //**********************************************************************************************
    //    User values of backlight slider percentage for OFF mode
    //**********************************************************************************************

    private static final int INVALID_VALUE = -1;

    /**
     * @param source_id ID of input source currently selected in Live TV application or -1 if no input source is selected
     * @return Array index in [0-1] range
     * @brief Converts ID of input source to the index of array where backlight slider preferences of user are kept
     */
    private static int convertSourceIDToIndex(int source_id) {
        return (source_id < 0) ? 0 : 1;
    }

    private static final String KEY_BACKLIGHT_SLIDER_PREFIX = "backlight_slider_";

    /**
     * @param mode       One of the values defined in VestelPictureMode enumeration
     * @param source_id  ID of input source currently selected in Live TV application or -1 if no input source is selected
     * @param percentage Backlight slider position in [0-100] range
     * @return True if backlight slider position is successfully saved, false otherwise
     * @brief Saves backlight slider position that the user has set for specified picture mode when backlight mode is OFF
     */
    public static boolean saveBacklightSliderPreference(VestelPictureMode mode, int source_id, int percentage) {
        int source_index = convertSourceIDToIndex(source_id);

        String key = KEY_BACKLIGHT_SLIDER_PREFIX + mode + "_" + source_index;
        VestelLog.verbose(TAG, "saveBacklightSliderPercentage(" + key + "): " + percentage);

        return Global.putInt(m_content_resolver, key, percentage);
    }

    /**
     * @brief Resets backlight slider positions that the user has set for each picture mode when backlight mode is OFF
     */
    public static void resetBacklightSliderPreferences() {
        String key;

        int length = VestelPictureMode.values().length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2; j++) {
                key = KEY_BACKLIGHT_SLIDER_PREFIX + VestelPictureMode.values()[i] + "_" + j;
                VestelLog.verbose(TAG, "resetBacklightSliderPercentage(" + key + "): " + INVALID_VALUE);

                Global.putInt(m_content_resolver, key, INVALID_VALUE);
            }
        }

        initDefaultBacklightSliderPercentages();

        // Not so good fix for HOCKEY-3832: The following sleep has been added to make sure TVSettings application
        // has finished setting all picture preferences to default values after incrementing "reset counter"
        // Settings.Global value
        VestelStandbyManager.pauseApplicationThread(1, TimeUnit.SECONDS);
    }

    /**
     * @param mode      One of the values defined in VestelPictureMode enumeration
     * @param source_id ID of input source currently selected in Live TV application or -1 if no input source is selected
     * @return Backlight slider position in [0-100] range
     * @brief Returns backlight slider position that the user has set for specified picture mode when backlight mode is OFF
     */
    public static int getBacklightSliderPreference(VestelPictureMode mode, int source_id) {
        int source_index = convertSourceIDToIndex(source_id);

        String key = KEY_BACKLIGHT_SLIDER_PREFIX + mode + "_" + source_index;
        int percentage = Global.getInt(m_content_resolver, key, INVALID_VALUE);

        VestelLog.verbose(TAG, "getBacklightSliderPercentage(" + key + "): " + percentage);

        if (percentage == INVALID_VALUE) {
            percentage = getDefaultBacklightSliderPreference(mode, source_id);
        }

        return percentage;
    }

    //**********************************************************************************************
    //    Default values of backlight slider percentage for OFF mode
    //**********************************************************************************************

    /**
     * @brief Initializes default backlight slider positions for each picture mode when backlight mode is OFF
     */
    private static void initDefaultBacklightSliderPercentages() {
        int length = VestelPictureMode.values().length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2; j++) {
                m_default_backlight_slider_percentages[i][j] = INVALID_VALUE;
            }
        }
    }

    private static void addPictureModeMapEntry(int value, VestelPictureMode picture_mode) {
        addPictureModeMapEntry(value, picture_mode, true);
    }

    private static void addPictureModeMapEntry(int value, VestelPictureMode picture_mode, boolean two_way_mapping) {
        m_int_to_picture_mode_map.put(value, picture_mode);

        if (two_way_mapping) {
            m_picture_mode_to_int_map.put(picture_mode, value);
        }
    }

    private static void initPictureModeMap() {
    }

    /**
     * @param mode      One of the values defined in VestelPictureMode enumeration
     * @param source_id ID of input source currently selected in Live TV application or -1 if no input source is selected
     * @return Backlight slider position in [0-100] range
     * @brief Returns default backlight slider position for specified picture mode when backlight mode is OFF
     */
    private static int getDefaultBacklightSliderPreference(VestelPictureMode mode, int source_id) {
        return -1;
    }

    public static boolean isSupportDolbyIQ() {
        return false;
    }
}

