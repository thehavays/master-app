package com.vestel.tv.middleware;

import android.content.Context;
import android.media.AudioManager;

/**
 * @brief This class manages sound settings of Android device
 */
public class VestelSoundSettings
{
    private static final String TAG = "VestelSoundSettings";

    private static AudioManager m_audio_manager = null;

    /**
     * @brief Initializes the class with the given Android application context
     * @param context Android application context
     */
    public static void init(Context context)
    {
        try
        {
            m_audio_manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * @brief Returns the current volume level of speaker
     * @return The current volume level of speaker which is in [0-100] range
     */
    public static int getVolume()
    {
        return -1;
    }

    /**
     * @brief Sets volume level of speaker
     * @param volume Volume level to be set for speaker which is in [0-100] range
     */
    public static void setVolume(int volume)
    {
    }

    /**
     * @brief Maximizes volume level of speaker
     */
    public static void setVolumeToMax()
    {
    }

    /**
     * @brief Returns the current volume level of headphone
     * @return The current volume level of headphone which is in [0-100] range
     */
    public static int getHeadphoneVolume()
    {
        return -1;
    }

    /**
     * @brief Sets volume level of headphone
     * @param volume Volume level to be set for headphone which is in [0-100] range
     */
    public static void setHeadphoneVolume(int volume)
    {
    }
}
