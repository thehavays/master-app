package com.vestel.tv.middleware;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

import java.util.concurrent.TimeUnit;

/**
 * This class manages standby and wakeup operations
 */
public class VestelStandbyManager {
    private static final String TAG = "VestelStandbyManager";

    private static final int DEFAULT_TIME_MS = (int) TimeUnit.MILLISECONDS.convert(20, TimeUnit.MINUTES);

    private static ContentResolver m_content_resolver = null;

    /**
     * @param context Android application context
     * @brief Initializes the class with the given Android application context
     */
    public static void init(Context context) {
        m_content_resolver = context.getContentResolver();
    }

    /**
     * @return Numeric value that defines the reason of most recent wakeup
     * @brief Returns the reason of most recent wakeup of the device
     */
    public static int getWakeupReason() {
        return 0;
    }

    /**
     * @return Auto turn off time in minutes or 0 if auto turn off is disabled
     * @brief Returns auto turn off time preference
     */
    public static int getAutoTurnOffTime() {
        return 0;
    }

    /**
     * @param value Auto turn off time in minutes or 0 if auto turn off is disabled
     * @brief Sets auto turn off time preference
     */
    public static void setAutoTurnOffTime(int value) {
    }

    /**
     * @return Auto sleep time in hours or 0 if auto sleep is disabled
     * @brief Returns auto sleep time preference
     */
    public static int getAutoSleepTime() {
        return 0;
    }

    /**
     * @param value Auto sleep time in hours or 0 if auto sleep is disabled
     * @brief Sets auto sleep time preference
     */
    public static void setAutoSleepTime(int value) {
    }

    private static final String NO_SIGNAL_AUTO_POWER_OFF = "no_signal_auto_power_off";

    /**
     * @return No signal power off time in minutes or 0 if no signal power off is disabled
     * @brief Returns no signal power off time preference
     */
    public static int getNoSignalPowerOffTime() {
        // settings get global no_signal_auto_power_off

        if (m_content_resolver != null) {
            int index = Settings.Global.getInt(m_content_resolver, NO_SIGNAL_AUTO_POWER_OFF, 4);

            // convert index to minutes
            int value;

            switch (index) {
                case 1:
                    value = 5;
                    break;
                case 2:
                    value = 10;
                    break;
                case 3:
                    value = 15;
                    break;
                case 4:
                    value = 30;
                    break;
                case 5:
                    value = 60;
                    break;
                default:
                    value = 0;
                    break;
            }

            return value;
        }

        return 0;
    }

    /**
     * @param value No signal power off time in minutes or 0 if no signal power off is disabled
     * @brief Sets no signal power off time preference
     */
    public static void setNoSignalPowerOffTime(int value) {
        if (m_content_resolver != null) {
            // convert minutes to index
            int index;

            switch (value) {
                case 5:
                    index = 1;
                    break;
                case 10:
                    index = 2;
                    break;
                case 15:
                    index = 3;
                    break;
                case 30:
                    index = 4;
                    break;
                case 60:
                    index = 5;
                    break;
                default:
                    index = 0;
                    break;
            }

            Settings.Global.putInt(m_content_resolver, NO_SIGNAL_AUTO_POWER_OFF, index);
        }
    }

    /**
     * @param time Pause duration
     * @param unit Time unit of pause duration
     * @brief Pauses the application thread where the function is called from
     */
    public static void pauseApplicationThread(int time, TimeUnit unit) {
        try {
            int msecs = (int) TimeUnit.MILLISECONDS.convert(time, unit);
            Thread.sleep(msecs);
        } catch (Exception e) {
        }
    }
}
