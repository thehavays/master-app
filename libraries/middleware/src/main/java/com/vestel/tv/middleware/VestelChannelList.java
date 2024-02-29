package com.vestel.tv.middleware;

import android.content.Context;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @brief This class queries channel list and current channel information
 */
public class VestelChannelList
{
    private static final String TAG = "VestelChannelList";

    private static Context m_context = null;
    private static Timer m_channel_timer = null;
    private static TimerTask m_channel_timer_task = null;

    private static boolean m_channel_switching = false;

    /**
     * @brief Initializes the class with the given Android application context
     * @param context Android application context
     */
    public static void init(Context context)
    {
        m_context = context;
    }

    /**
     * @brief Returns the number of channels in channel list which contains
     *        channels installed from all networks (cable, satellite etc.)
     * @return The number of channels in channel list
     */
    public static int getChannelCount()
    {
        return -1;
    }

    /**
     * @brief Returns the unique ID of the channel that is tuned to in Live TV application
     * @return The unique ID of the channel that is tuned to in Live TV application
     *         or -1 if no channel is tuned to in Live TV application
     */
    public static int getCurrentChannelNumber()
    {
        return -1;
    }

    /**
     * @brief Returns the name of the channel that is tuned to in Live TV application
     * @return The name of the channel that is tuned to in Live TV application
     *         or null if no channel is tuned to in Live TV application
     */
    public static String getCurrentChannelName()
    {
        return "";
    }

    /**
     * @brief Checks if the channel that is tuned to in Live TV application is a radio channel
     * @return True if the channel that is tuned to in Live TV application is a radio channel,
     *         false otherwise
     */
    public static boolean isRadioChannelPlaying()
    {
        return false;
    }

    /**
     * @brief Checks if Live TV application is performing channel scan
     * @return True if Live TV application is performing channel scan, false otherwise
     */
    public static boolean isScanningChannels()
    {
        return false;
    }

    /**
     * @brief Sets Live TV application to tuning state for 5 seconds
     */
    public static void setChannelSwitching()
    {
        m_channel_switching = true;

        if (m_channel_timer != null)
        {
            m_channel_timer.cancel();
            m_channel_timer = null;
        }

        if (m_channel_timer_task != null)
        {
            m_channel_timer_task.cancel();
            m_channel_timer_task = null;
        }

        m_channel_timer = new Timer();
        m_channel_timer_task = new TimerTask()
        {
            @Override
            public void run()
            {
                m_channel_switching = false;
            }
        };

        m_channel_timer.schedule(m_channel_timer_task,
                                 TimeUnit.MILLISECONDS.convert(5L, TimeUnit.SECONDS));
    }

    /**
     * @brief Checks if Live TV application is tuning to a channel
     * @return True if Live TV application is tuning to a channel, false otherwise
     */
    public static boolean isChannelSwitching()
    {
        return m_channel_switching;
    }

    private static final int ENABLE_CREATE_XML = 1;

    /**
     * @brief Creates backup of installed channels to USB storage device in XML format
     * @return True if installed channels are successfully backed up to USB storage device, false otherwise
     */
    public static boolean backupChannelsToUSB()
    {
        return false;
    }

    /*
     * @brief Restores channels from USB storage device to the channel list of Live TV application
     * @return True if channels are successfully restored from USB storage device, false otherwise
     */
    public static boolean restoreChannelsFromUSB()
    {
        return false;
    }

    /*
     * @brief Restores preinstalled channels from internal storage to the channel list of Live TV application
     * @return True if preinstalled channels are successfully restored from internal storage, false otherwise
     */
    public static boolean restorePreinstalledChannels()
    {
        return false;
    }
}

