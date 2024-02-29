package com.vestel.tv.middleware;

import android.content.Context;
import android.media.tv.TvInputInfo;
import android.media.tv.TvInputManager;

import java.util.List;

/**
 * @brief This class manages video input sources of Androoid device
 */
public class VestelInputManager
{
    private static final String TAG = "VestelInputManager";


    private static TvInputManager m_tv_input_manager = null;

    /**
     * @brief Initializes the class with the given Android application context
     * @param context Android application context
     */
    public static void init(Context context)
    {
        try
        {
            m_tv_input_manager = (TvInputManager) context.getSystemService(Context.TV_INPUT_SERVICE);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * @brief Returns the name of video input source currently selected
     * @return "PC" if VGA input source is currently selected,
     *         "TV" if analog tuner is currently selected but no channel is tuned to,
     *         "TV <channel number> - <channel_name> if an analog channel is tuned to,
     *         "DTV" if digital tuner is currently selected but no channel is tuned to,
     *         "DTV <channel number> - <channel_name> if a digital channel is tuned to,
     *         "BAV" if AV input source is currently selected,
     *         "YPBPR" if component input source is currently selected,
     *         "HDMI<hdmi number>" if HDMI input source is currently selected,
     */
    public static String getSourceName()
    {
        String source_name = "";
        VestelLog.info(TAG, "Input Source= " + source_name);
        return source_name;
    }

    /**
     * @brief Returns the type of video input source currently selected
     * @return The type of video input source currently selected
     */
    public static int getCurrentSource()
    {
        return -1;
    }

    /**
     * @brief Returns the name of video input source currently selected
     * @return The name of video input source currently selected
     */
    public static String getCurrentSourceName()
    {
        return "";
    }

    /**
     * @brief Checks if the video input source currently selected is HDMI input
     * @return True if the video input source currently selected is HDMI input, false otherwise
     */
    public static boolean isCurrentSourceHDMI()
    {
        return false;
    }

    /**
     * @brief Checks if the video input source currently selected is VGA input
     * @return True if the video input source currently selected is VGA input, false otherwise
     */
    public static boolean isCurrentSourceVGA()
    {
        return false;
    }

    /**
     * @brief Checks if the video input source currently selected is analog or digital tuner input
     * @return True if the video input source currently selected is analog or digital tuner input,
     *         false otherwise
     */
    public static boolean isCurrentSourceTV()
    {
        boolean ret = false;

        String source_name = getCurrentSourceName();

        if (source_name != null)
        {
            VestelProjectType project_type = VestelSystemProperties.getProjectType();

            switch (project_type)
            {
                case MEDUSA:
                case COMA:
                case MALIN:
                case HOCKEY:
                case SUNFLOWER:
                    ret = source_name.equalsIgnoreCase("ATV") || source_name.equalsIgnoreCase("DTV");
                    break;

                default:
                    ret = source_name.equalsIgnoreCase("TV");
                    break;
            }
        }

        return ret;
    }

    /**
     * @brief Checks if any of video input sources is currently selected
     * @return True if none of video input sources is currently selected, false otherwise
     */
    public static boolean isSourceDeselected()
    {
        String source_name = getCurrentSourceName();
        return (source_name == null || source_name.isEmpty());
    }

    /**
     * @brief Checks if VGA input source is available on the device
     * @return True if VGA input source is available on the device, false otherwise
     */
    public static boolean vgaInputAvailable()
    {
        boolean vga_input_available = false;

        if (m_tv_input_manager != null)
        {
            List<TvInputInfo> tv_input_list = m_tv_input_manager.getTvInputList();
            VestelLog.info(TAG, "Input(size)= " + tv_input_list.size());

            for (int i = 0; i < tv_input_list.size(); i++)
            {
                int type = tv_input_list.get(i).getType();
                VestelLog.info(TAG, "Input(" + i + ")= " + type);

                if (type == TvInputInfo.TYPE_VGA)
                {
                    vga_input_available = true;
                    break;
                }
            }
        }

        return vga_input_available;
    }
}

