package com.vestel.tv.middleware;

import java.io.*;

/**
 * @brief This class provides information about SatcoDX installation
 */
public class VestelSatcoDX
{
    private static final String TAG = "VestelSatcoDX";

    /**
     * @brief Checks if SatcoDX installation is enabled on device
     * @return True if SatcoDX installation is enabled on device, false otherwise
     */
    public static boolean isEnabled()
    {
        final String key = "SATCODX:bSdxEnabled";
        String ret = VestelFileSystem.getIniParameter(VestelConfig.getCustomizationFuncFile(), key);

        return ("1".equals(ret));
    }

    /**
     * @brief Returns the name of SatcoDX channel file for the country selected during FTI
     * @return The name of SatcoDX channel file for the country selected during FTI or empty string if not available
     */
    public static String getFileName()
    {
        File folder = new File(VestelConfig.getSatcodxFolder());
        File[] file_list = folder.listFiles();

        String file_name = "";

        if (file_list != null)
        {
            String default_file_name = "default_preset_services";
            String file_extension = ".sdx";

            String country = "TR";
            String country_file_name = default_file_name + "_" + country;

            VestelLog.debug(TAG, "countryFileName:" + country_file_name);
            for (File file : file_list)
            {
                if (file.getName().equalsIgnoreCase(country_file_name + file_extension))
                {
                    file_name = file.getName();
                    break;
                }
                else if (file.getName().equalsIgnoreCase(default_file_name + file_extension))
                {
                    file_name = file.getName();
                }
            }
        }

        VestelLog.debug(TAG, "SDXfileName:" + file_name);
        return file_name;
    }
}
