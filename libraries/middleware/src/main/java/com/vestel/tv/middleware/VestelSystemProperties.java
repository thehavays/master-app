package com.vestel.tv.middleware;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @apiNote This class provides system information about Android device
 */
public class VestelSystemProperties
{
    private static final String TAG = "VestelSystemProperties";

//    private static final MtkTvConfig mTvConfig = MtkTvConfig.getInstance();
//    private static final MtkTvFApiDisplay m_tv_display = MtkTvFApiDisplay.getInstance();
//    private static final MtkTvFApiPeripheral m_tv_peripheral = MtkTvFApiPeripheral.getInstance();
//    private static final MtkTvFApiSystem m_tv_system = MtkTvFApiSystem.getInstance();

    private static Context m_context = null;

    private static final Map<String,String> m_app_versions = new HashMap<>();
    static {
        m_app_versions.put("finch,AUT","V0.27.1.0");
        m_app_versions.put("finch,DEU","V0.20.0.0");
        m_app_versions.put("finch,TUR","V0.25.0.0");
        m_app_versions.put("finch,FRA","V0.26.0.0");
        m_app_versions.put("finch,GBR","V0.25.1.0");

        m_app_versions.put("kowloon,AUT","V0.27.1.0");
        m_app_versions.put("kowloon,DEU","V0.20.0.0");
        m_app_versions.put("kowloon,TUR","V0.25.0.0");
        m_app_versions.put("kowloon,FRA","V0.26.0.0");
        m_app_versions.put("kowloon,GBR","V0.25.1.0");

        m_app_versions.put("menlo,AUT","V0.27.1.0");
        m_app_versions.put("menlo,DEU","V0.20.0.0");
        m_app_versions.put("menlo,TUR","V0.25.0.0");
        m_app_versions.put("menlo,FRA","V0.26.0.0");
        m_app_versions.put("menlo,GBR","V0.25.1.0");

        m_app_versions.put("shandao,AUT","V0.27.1.0");
        m_app_versions.put("shandao,DEU","V0.20.0.0");
        m_app_versions.put("shandao,TUR","V0.25.0.0");
        m_app_versions.put("shandao,FRA","V0.26.0.0");
        m_app_versions.put("shandao,GBR","V0.25.1.0");

        m_app_versions.put("yeoksam,AUT","V0.27.1.0");
        m_app_versions.put("yeoksam,DEU","V0.20.0.0");
        m_app_versions.put("yeoksam,TUR","V0.25.0.0");
        m_app_versions.put("yeoksam,FRA","V0.26.0.0");
        m_app_versions.put("yeoksam,GBR","V0.25.1.0");

        m_app_versions.put("tokyo,AUS","");
        m_app_versions.put("tokyo,DEU","");
        m_app_versions.put("tokyo,TUR","");
    }

    /**
     * @brief Initializes the class with the given Android application context
     * @param context Android application context
     */
    public static void init(Context context)
    {
        m_context = context;
        VestelConfig.init();
    }

    /**
     * @brief Returns application version or firmware version if application version is not defined
     * @return The application version or firmware version if application version is not defined
     */
    public static String getAppVersion()
    {
        String country = "TR";
        String key = "APP_VERSION:" + country;
        VestelProjectType project_type = getProjectType();
        VestelLog.debug(TAG, "getAppVersion appVersionParameter: " + key);

        VestelConfig.init();
        String app_version = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), key);

        if (project_type.equals(VestelProjectType.SUNFLOWER))
        {
            app_version = "N/A";
        }
        else
        {
            if (app_version == null || app_version.isEmpty())
            {
                key = VestelSystemProperties.getDeviceName() + "," + country;
                app_version = m_app_versions.get(key);

                if (app_version == null || app_version.isEmpty())
                {
                    app_version = getFirmwareVersion();
                }
            }
        }
        VestelLog.debug(TAG, "appVersion: " + app_version);
        return app_version;
    }

    /**
     * @brief Returns the board name of device
     * @return The board name of device
     */
    public static String getBoardName()
    {
        return VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), "BOARD_CFG:BOARD_NAME");
    }

    /**
     * @brief Returns the name of boot logo file
     * @return The name of boot logo file of empty string if boot logo file is not available
     */
    public static String getBootLogoName()
    {
        String boot_logo_name = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(),
                                                            "LOGO_CFG:LOGO_NAME");
        if (boot_logo_name == null)
        {
            boot_logo_name = "";
        }

        return boot_logo_name;
    }

    /**
     * @brief Returns the name of boot music file
     * @return The name of boot music file of empty string if boot music file is not available
     */
    public static String getBootMusicName()
    {
        String boot_music_name = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(),
                                                            "MUSIC_CFG:MUSIC_NAME");
        if (boot_music_name == null)
        {
            boot_music_name = "";
        }

        return boot_music_name;
    }

    /**
     * @brief Returns the build date of system software
     * @return The build date of system software
     */
    public static String getBuildDate()
    {
        return "ro.build.date";
    }

    /**
     * @brief Returns the build version of system software
     * @return The build version of system software
     */
    public static String getBuildVersion()
    {
        return "ro.build.display.id";
    }

    /**
     * @brief Returns the client ID of device
     * @return The client ID of device
     */
    public static String getClientId()
    {
        return "ro.com.google.clientidbase";
    }

    /**
     * @brief Returns the 3-letter name of country selected during FTI
     * @return The 3-letter name of country selected during FTI
     */
    public static String getCountryName()
    {
        String country_name;

        if (isInFactoryMode())
        {
            country_name = "Vestel Factory";
        }
        else if (m_context != null)
        {
            VestelLog.debug(TAG, " wizard_other_country:true");
            country_name = "Other";
        }
        else
        {
            country_name = "Vestel Factory";
        }

        return country_name;
    }

    /**
     * @brief Sets country name of device
     * @param country_name The country name to be set
     * @return True if country name is successfully set, false otherwise
     */
    public static boolean setCountryName(String country_name)
    {
        return false;
    }

    /**
     * @brief Returns the brand name of device
     * @return The brand name of device
     */
    public static String getBrandName()
    {
        return "ro.product.brand";
    }

    /**
     * @brief Returns the brand type of device
     * @return One of the values defined in VestelBrandType enumeration
     */
    public static VestelBrandType getBrandType()
    {
        return (getBrandName().equals("Panasonic") || VestelConfig.getProjectIDName().contains("PANASONIC"))
               ? VestelBrandType.PANASONIC : VestelBrandType.VESTEL;
    }

    /**
     * @brief Returns the customer name of device
     * @return The customer name of device which is "Vestel" if it is not explicitly set
     */
    public static String getCustomerName()
    {
        String key = "CUSTOMER_CFG:CUSTOMER_NAME";

        String customer_name = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), key);
        if (customer_name == null || customer_name.isEmpty())
        {
            customer_name = "Vestel";
        }

        VestelLog.debug(TAG, "customerName: " + customer_name);
        return customer_name;
    }

    /**
     * @brief Returns the customer hash of device
     * @return The customer hash of device
     */
    public static String getCustomerHash()
    {
        String customer_hash = null;

        List<Byte> hash_bytes = null;

        if (hash_bytes != null)
        {
            StringBuilder buffer = new StringBuilder(255);

            for (int i = 0; i < hash_bytes.size(); i++)
            {
                buffer.append("0x");
                buffer.append(String.format("%02X ", hash_bytes.get(i)));
            }

            customer_hash = buffer.toString();

            VestelLog.debug(TAG, "Customer Hash: " + customer_hash);
        }

        return customer_hash;
    }

    /**
     * @brief Returns the name of device
     * @return The name of device
     */
    public static String getDeviceName()
    {
        return "ro.product.device";
    }

    /**
     * @brief Returns Netflix ESNID of device
     * @return Netflix ESNID of device or empty string if not available
     */
    public static String getESNID()
    {
        String esn_id = "";

        try
        {
            if (VestelFileSystem.isFileExist(VestelConfig.g_netflix_esnid))
            {
                final int esn_size = 64;

                String str = null;
                esn_id = str.substring(26, 29) + str.substring(50, 64);

                // OZER FactoryCommandSerivce str.substring(0,64) ?
            }
        }
        catch (Exception exception)
        {
            VestelLog.debug(TAG, exception.toString());
        }

        return esn_id;
    }

    /**
     * @brief Returns the fingerprint of device
     * @return The fingerprint of device
     */
    public static String getFingerprint()
    {
        return "ro.build.fingerprint";
    }

    /**
     * @brief Returns the firmware version of device
     * @return The firmware version of device
     */
    public static String getFirmwareVersion()
    {
        return "ro.vendor.oemname";
    }

    /**
     * @brief Returns the display language selected during FTI or from TV settings application
     * @return The display language selected during FTI or from TV settings application
     */
    public static String getLanguage()
    {
        return "lang";
    }

    /**
     * @brief Returns the manufacturer name of device
     * @return The manufacturer name of device
     */
    public static String getManufacturerName()
    {
        return "ro.product.vendor.manufacturer";
    }

    /**
     * @brief Returns MFC firmware version of device
     * @return MFC firmware version of device or empty string if not available
     */
    public static String getMFCFirmwareVersion()
    {
        return "mfc_fw_version";
    }

    public static boolean upgradeMFCFirmware(String firmware_file_path)
    {
       return false;
    }

    public static int getMFCVersion(String path)
    {
        int version = 0;

        try
        {
            byte[] data = Files.readAllBytes(Paths.get(path));

            byte[] pattern = new byte[] {0x24, 0x43, 0x68, 0x61,
                                         0x6E, 0x67, 0x65, 0x3A};

            int index = indexOf(data, pattern);

            if (index >= 2)
            {
                version = data[index-2];
            }
        }
        catch (Exception e)
        {
            VestelLog.error(TAG, e.toString());
        }

        return version;
    }

    /**
     * Finds the first occurrence of the pattern in the text.
     */
    private static int indexOf(byte[] data, byte[] pattern) {
        if (data.length == 0) return -1;

        int[] failure = computeFailure(pattern);
        int j = 0;

        for (int i = 0; i < data.length; i++) {
            while (j > 0 && pattern[j] != data[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == data[i]) { j++; }
            if (j == pattern.length) {
                return i - pattern.length + 1;
            }
        }
        return -1;
    }

    /**
     * Computes the failure function using a boot-strapping process,
     * where the pattern is matched against itself.
     */
    private static int[] computeFailure(byte[] pattern) {
        int[] failure = new int[pattern.length];

        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[j] != pattern[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == pattern[i]) {
                j++;
            }
            failure[i] = j;
        }

        return failure;
    }

    /**
     * @brief Returns the model name of device
     * @return The model name of device
     */
    public static String getModelGroup()
    {
        return "ro.vendor.nrdp.modelgroup";
    }

    /**
     * @brief Returns the model name of device
     * @return The model name of device
     */
    public static String getModelName()
    {
        return "ro.product.model";
    }

    /**
     * @brief Returns the model number of device
     * @return The model number of device if available, "N/A" otherwise
     */
    public static String getModelNumber()
    {
        String key = "BOARD_CFG:MODEL_NO";

        String model_number = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), key);
        if (model_number == null || model_number.isEmpty())
        {
            model_number = "N/A";
        }
        VestelLog.debug(TAG, "modelNumber: " + model_number);
        return model_number;
    }

    /**
     * @brief Set the model number of device
     * @param model_number The model number to be set
     * @return True if it is successfully set, false otherwise
     */
    public static boolean setModelNumber(String model_number)
    {
        String key = "BOARD_CFG:MODEL_NO";

        return VestelFileSystem.setIniParameter(VestelConfig.getConfigFileNamesFile(), key, model_number);
    }

    /**
     * @brief Returns the chip name of device
     * @return The chip name of device
     */
    public static String getChipName()
    {
        return "ro.board.platform";
    }

    /**
     * @brief Returns the product name of device
     * @return The product name of device
     */
    public static String getProductName()
    {
        return "ro.product.name";
    }

    /**
     * @brief Returns the profile name of device
     * @return The profile name of device
     */
    public static String getProfileName()
    {
        final String key = "PROFILE_CFG:PROFILE_NAME";
        return VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), key);
    }

    /**
     * @brief Returns the project name
     * @return String representation of the value defined in VestelProjectType enumeration
     */
    public static String getProjectName()
    {
        String project_name = "";
        VestelProjectType project_type = getProjectType();

        switch (project_type)
        {
            case COSMOS:
                project_name = "Cosmos";
                break;

            case COMET:
                project_name = "Comet";
                break;

            case MEDUSA:
                project_name = "Medusa";
                break;

            case COMA:
                project_name = "Coma";
                break;

            case NEEDLE:
                project_name = "Needle";
                break;

            case MALIN:
                project_name = "Malin";
                break;

            case HOCKEY:
                project_name = "Hockey";
                break;

            case SUNFLOWER:
                project_name = "Sunflower";
                break;

            default:
                break;
        }

        return project_name;
    }

    /**
     * @brief Returns the project type
     * @return One of the values defined in VestelProjectType enumeration
     */
    public static VestelProjectType getProjectType()
    {
        VestelProjectType type;
        String product_name = getProductName().toUpperCase();
        String marketregion = "ro.vendor.mtk.system.marketregion";
        VestelLog.debug(TAG, "marketregion>>>" + marketregion);

        if (product_name.equals("COSMOS170") || product_name.equals("ALP"))
        {
            type = VestelProjectType.COSMOS;
        }
        else if (product_name.startsWith("EMIR"))
        {
            type = VestelProjectType.MEDUSA;
        }
        else if (product_name.startsWith("BARTU"))
        {
            if (marketregion != null && marketregion.equals("us"))
            {
                type = VestelProjectType.NEEDLE;
            } else {
                type = VestelProjectType.COMA;
            }
        }
        else if (product_name.startsWith("BODRUM"))
        {
            type = VestelProjectType.MALIN;
        }
        else if (product_name.startsWith("OZUM"))
        {
            if (product_name.startsWith("OZUM_CONTINENTAL"))
            {
                type = VestelProjectType.SUNFLOWER;
            } else {
                type = VestelProjectType.HOCKEY;
            }
        }
        else
        {
            type = VestelProjectType.COMET;
        }

        return type;
    }

    /**
     * @brief Returns the board type
     * @return One of the values defined in VestelBoardType enumeration
     */
    public static VestelBoardType getBoardType()
    {
        VestelBoardType type = VestelBoardType.OTHER;

        String board_name = getBoardName();
        if (board_name.isEmpty())
        {
            String product_name = getProductName().toUpperCase();
            if (product_name.equals("COSMOS170") || product_name.equals("ALP"))
            {
                type = VestelBoardType.MB170;
            }
            else if (product_name.startsWith("EMIR") ||
                     product_name.startsWith("BODRUM") ||
                     product_name.startsWith("OZUM"))
            {
                type = VestelBoardType.MB185;
            }
            else if (product_name.startsWith("BARTU"))
            {
                type = VestelBoardType.MB186;
            }
        }
        else
        {
            switch (board_name)
            {
                case "MB170":
                    type = VestelBoardType.MB170;
                    break;

                case "MB171":
                    type = VestelBoardType.MB171;
                    break;

                case "MB170_TC":
                    type = VestelBoardType.MB170_TC;
                    break;

                case "MB170_E":
                    type = VestelBoardType.MB170_E;
                    break;

                case "MB185":
                    type = VestelBoardType.MB185;
                    break;

                case "MB185Y":
                case "MB185Y_G31":
                case "MB185Y_G32":
                    type = VestelBoardType.MB185Y;
                    break;

                case "MB186":
                case "MB186TC":
                    type = VestelBoardType.MB186;
                    break;

                case "MB185_G31":
                    type = VestelBoardType.MB185_G31;
                    break;

                case "MB185_G32":
                    type = VestelBoardType.MB185_G32;
                    break;

                default:
                    break;
            }
        }

        return type;
    }

    /**
     * @brief Returns the serial number of device
     * @return The serial number of device if available, empty string otherwise
     */
    public static String getSerialNumber()
    {
        String serial_number = "";

        try
        {
            serial_number = "";
        }
        catch (Exception exception)
        {
            VestelLog.debug(TAG, exception.toString());
        }

        return serial_number;
    }

    /**
     * @brief Returns EAN number (european article number) of device
     * @return EAN number of device if available, empty string otherwise
     */
    public static String getEANNumber()
    {
        String ean_number = "";

        try
        {
            final String key = "BOARD_CFG:TV_EAN_NO";
            ean_number = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), key);
        }
        catch (Exception exception)
        {
            VestelLog.error(TAG, exception.toString());
        }

        VestelLog.debug(TAG, "getEANNumber: " + ean_number);
        return ean_number;
    }

    /**
     * @brief Sets EAN number (european article number) of device
     * @param ean_number EAN number (european article number) to be set
     * @return True if it is successfully set, false otherwise
     */
    public static boolean setEANNumber(String ean_number)
    {
        final String key = "BOARD_CFG:TV_EAN_NO";

        return VestelFileSystem.setIniParameter(VestelConfig.getConfigFileNamesFile(), key, ean_number);
    }

    /**
     * @brief Returns VPAZ number (Vestel marketing number) of device
     * @return VPAZ number of device if available, empty string otherwise
     */
    public static String getVPAZNumber()
    {
        String vpaz_number = "";

        try
        {
            final String key = "BOARD_CFG:TV_VPAZ_NO";
            vpaz_number = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), key);
        }
        catch (Exception exception)
        {
            VestelLog.error(TAG, exception.toString());
        }

        VestelLog.debug(TAG, "getVPAZNumber: " + vpaz_number);
        return vpaz_number;
    }

    /**
     * @brief Sets VPAZ number (Vestel marketing number) of device
     * @param vpaz_number VPAZ number (Vestel marketing number) to be set
     * @return True if it is successfully set, false otherwise
     */
    public static boolean setVPAZNumber(String vpaz_number)
    {
        final String key = "BOARD_CFG:TV_VPAZ_NO";

        return VestelFileSystem.setIniParameter(VestelConfig.getConfigFileNamesFile(), key, vpaz_number);
    }

    /**
     * @brief Checks if the device is in factory mode
     * @return True if the device is in factory mode, false otherwise
     */
    public static boolean isInFactoryMode()
    {
        return false;
    }

    /**
     * @brief Returns the factory flag defined in factory ini file
     * @return True if factory flag is enabled in factory ini file, false otherwise
     */
    public static boolean getFactoryFlag()
    {
        String key = "FACTORY_FLAG:FACTORY_MODE";
        String factory_flag = VestelFileSystem.getIniParameter(VestelConfig.getFactoryIniFile(), key);

        return ("1".equals(factory_flag));
    }

    /**
     * @brief Sets the factory flag defined in factory ini file
     * @param factory_flag True to enable factory flag, false to disable
     * @return True if factory flag is successfully set, false otherwise
     */
    public static boolean setFactoryFlag(boolean factory_flag)
    {
        String key = "FACTORY_FLAG:FACTORY_MODE";
        String flag = (factory_flag) ? "1" : "0";

        return VestelFileSystem.setIniParameter(VestelConfig.getFactoryIniFile(), key, flag);
    }

    private static final String RETAIL_MODE_DB_FIELD = "RetailModeEnable";

    /**
     * @brief Checks if the device in store mode
     * @return True if the device in store mode, false otherwise
     */
    public static int isInStoreMode()
    {
        return Settings.Global.getInt(m_context.getContentResolver(), RETAIL_MODE_DB_FIELD, -1);
    }

    /**
     * @brief Checks if the mainboard of device is MB170E
     * @return True if the mainboard of device is MB170E, false otherwise
     */
    public static boolean isMB170E()
    {
        boolean flag = false;

        String board_name = getBoardName();
        if (board_name.equals("MB170_E") || board_name.equals("MB170_E_TC"))
        {
            flag = true;
        }
        return flag;
    }

     /**
     * @brief Checks if the mainboard of device is MB185Y
     * @return True if the mainboard of device is MB185Y, false otherwise
     */
    public static boolean isMB185Y()
    {
        boolean flag = false;

        String board_name = getBoardName();
        if (board_name.equals("MB185Y"))
        {
            flag = true;
        }
        return flag;
    }

    /**
     * @brief Returns clean OFF-RS time for OLED panels
     * @return Clean OFF-RS time in minutes
     */
    public static int getCleanOFFRSTimeInMin()
    {
        int clean_time = -1;
        if (VestelPanelProperties.isPanelOLED())
        {
            clean_time = -1;
        }
        return clean_time;
    }

    /**
     * @brief Checks if watchdog is enabled on Android device
     * @return True if watchdog is enabled on Android device, false otherwise
     */
    public static boolean getWatchDog()
    {
        return false;
    }

    /**
     * @brief Enables or disables watchdog on Android device
     * @param flag True to enable watchdog, false to disable watchdog
     */
    public static void setWatchDog(boolean flag)
    {
    }

    /**
     * @brief Returns version of the specified binary PQ file
     * @param str Type of binary PQ file, either "QMAP", "TMO", "HSY" or empty string
     * @return Version of the specified binary PQ file or empty string if type of binary PQ file is undefined
     */
    public static String getPQVersion(String str)
    {
        String pq_version = "";

        return pq_version;
    }

    /**
     * @brief Returns the size of RAM installed on device
     * @return The size of RAM in GB
     */
    public static double getInstalledRAM()
    {
        double ram_in_gb = 0;

        if (m_context != null)
        {
            MemoryInfo memory_info = new MemoryInfo();
            ActivityManager activity_manager = (ActivityManager) m_context.getSystemService(Context.ACTIVITY_SERVICE);
            activity_manager.getMemoryInfo(memory_info);

            // memory_info.totalMem return total memory accessible by the kernel.
            // This is basically the RAM size of the device,
            // not including below-kernel fixed allocations like DMA buffers,
            // RAM for the baseband CPU, etc.

            // memory_info.totalMem returns 1787 MB on device with 2 GB physical memory.
            // Under the assumption that below-kernel fixed allocations should take less than 512 MB,
            // we round memory_info.totalMem to the upper multiple of 512 MB for physical RAM size.

            long total_ram_bytes = memory_info.totalMem;
            long half_gb_bytes = 512*1024*1024;

            ram_in_gb = Math.ceil((double) total_ram_bytes / half_gb_bytes) * 0.5;
        }

        return ram_in_gb;
    }

    /**
     * @brief Checks if there is demo video in the file system of device and ready for playback
     * @return True if there is demo video in the file system of device and ready for playback, false otherwise
     */
    public static boolean isDemoVideoEnabled()
    {
        String key = "RETAILDEMO:bDemoVideoEnabled";
        String str = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), key);

        boolean demo_file_exists = VestelFileSystem.isFileExist("/vendor/cusdata/storevideo/storevideo.mp4");

        return str.equals("1") || demo_file_exists;
    }

    /**
     * @brief Checks if Android TV has tuner source
     * @return True if Android TV has no tuner source, false otherwise
     */
    public static boolean isTunerless()
    {
        VestelConfig.init();
        boolean flag = VestelConfig.getProjectIDName().contains("_TUNERLESS_");

        VestelLog.info(TAG, "Tunerless:" + flag);
        return flag;
    }

    /**
     * @brief Checks if Android TV has battery
     * @return True if Android TV is battery TV, false otherwise
     */
    public static boolean isBatteryAvailable()
    {
        boolean isBatteryConnected = false;
        String battery_enabled = VestelFileSystem.getIniParameter("/vendor/cusdata/bsp/common/customer/ConfigFileNames.ini", "BATTERY:BATTERY_ENABLED");
        if ((battery_enabled.trim().equals("1"))) {
            isBatteryConnected = true;
        }
        return isBatteryConnected;
    }

    /**
     * @brief Returns lifetime of Android device in minutes
     * @return Lifetime of Android device in minutes
     */
    public static int getDeviceLifetime()
    {
        return -1;
    }

    /**
     * @brief Returns merge tool version
     * @return Merge tool version or empty string if not available
     */
    public static String getMergeToolVersion()
    {
        String version = VestelFileSystem.getIniParameter(VestelConfig.getConfigFileNamesFile(), "MERGE_TOOL_CFG:TOOL_VER");

        VestelLog.debug(TAG, "getMergeToolVersion:" + version);

        if (version.equalsIgnoreCase("NA") || version.equalsIgnoreCase("N/A"))
        {
            version = "";
        }

        return version;
    }

    /**
     * @brief Returns the name of thermal mapping data file
     * @return The name of thermal mapping data file or empty string if thermal mapping data file is not available
     */
    public static String getThermalMappingData()
    {
        String path = VestelConfig.getConfigFileNamesFile();
        String thermal_mapping_data = VestelFileSystem.getIniParameter(path,"THERMAL_MAPPING:THERMAL_MAPPING_DATA");
        VestelLog.debug(TAG, "thermal_mapping_data=" + thermal_mapping_data);

        if (thermal_mapping_data == null)
        {
            thermal_mapping_data = "";
        }

        return thermal_mapping_data;
    }

    public static boolean setEmmcEnvironmentVariable(String key, String value)
    {
        return false;
    }

    /**
     * @brief Returns whether the software is userdebug variant
     * @return True if the software is userdebug variant, false otherwise
     */
    public static boolean isDebugBuild()
    {
        return false;
    }

    /**
     * @brief Returns whether the device supports Dolby Vision IQ
     * @return True if the device supports Dolby Vision IQ, false otherwise
     */
    public static boolean isDolbyVisionIQEnabled()
    {
        return VestelConfig.getProjectIDName().contains("DOLBY_VISION_IQ");
    }

    /**
     * @brief Returns whether the device has Google TV user interface running on top of Android TV
     * @return True if the device has Google TV user interface running on top of Android TV, false otherwise
     */
    public static boolean isGoogleTV()
    {
        VestelProjectType project_type = getProjectType();

        return (project_type == VestelProjectType.HOCKEY ||
                project_type == VestelProjectType.SUNFLOWER);
    }

    /**
     * @brief Returns whether the device is targeting for US market
     * @return True if the device is targeting for US market
     */
    public static boolean isUSProject()
    {
        VestelProjectType project_type = getProjectType();

        return (project_type == VestelProjectType.NEEDLE ||
                project_type == VestelProjectType.SUNFLOWER);
    }
}
