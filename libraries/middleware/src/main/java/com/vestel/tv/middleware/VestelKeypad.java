package com.vestel.tv.middleware;

/**
 * @brief This class provides information about keypad
 */
public class VestelKeypad
{
    private static final String TAG = "VestelKeypad";

    /**
     * @brief Returns the type of keypad
     * @return String representation of keypad type or "N/A" if not defined
     */
    public static String getType()
    {
        String type = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), "KEYPAD_CFG:KEYPAD_NAME");

        if (type.isEmpty())
        {
            type = "N/A";
        }

        VestelLog.info(TAG, "Keypad type:" + type);
        return type;
    }

    /**
     * @brief Returns the revision of keypad configuration file
     * @return File revision information if available or "Rev: N/A" otherwise
     */
    public static String getRevision()
    {
        return VestelFileSystem.getFileRevision(VestelConfig.getBoardFile());
    }
}

