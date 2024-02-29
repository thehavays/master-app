package com.vestel.tv.middleware;

/**
 * @brief This class provides information about remote controller unit
 */
public class VestelRemoteController
{
    private static final String TAG = "VestelRemoteController";

    /**
     * @brief Returns the type of remote controller unit
     * @return String representation of remote controller unit type or "N/A" if not defined
     */
    public static String getType()
    {
        String rc_type = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), "IR_CFG:RC_TYPE");

        if (rc_type.isEmpty())
        {
            rc_type = "N/A";
        }

        VestelLog.info(TAG, "RC type:" + rc_type);
        return rc_type;
    }

    /**
     * @brief Returns the revision of remote controller configuration file
     * @return File revision information if available or "Rev: N/A" otherwise
     */
    public static String getRevision()
    {
        return VestelFileSystem.getFileRevision(VestelConfig.getIrConfigFile());
    }

    /**
     * @brief Returns the version of remote controller unit
     * @return String representation of remote controller unit version or "N/A" if not defined
     */
    public static String getVersion()
    {
        String rc_version;
        String rc_type = getType();

        if (rc_type.equals("RC43157") ||
            rc_type.equals("RC43159") ||
            rc_type.equals("RC45157") ||
            rc_type.equals("RC45159"))
        {
            rc_version = "N/A";
        }
        else
        {
            String key = String.format("IR_CFG:%s_VERSION", rc_type);
            rc_version = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), key);
        }

        VestelLog.info(TAG, "RC version:" + rc_version);
        return rc_version;
    }
}
