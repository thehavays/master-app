package com.vestel.tv.middleware;

/**
 * @brief This class defines platform specific file and folder paths
 */
public class VestelConfig
{
    private static final String TAG = "VestelConfig";

    public static final String g_vga_edid_file_name = "edid_vga.bin";

    public static final String g_ci_plus_key = "/vendor/tvcertificate/ciplus.key";
    public static final String g_ci_plus_ecp_key = "/vendor/tvcertificate/ciplusecp.key";

    public static final String g_netflix_key_folder = "/vendor/tvcertificate/netflix50/";
    public static final String g_netflix_esnid = g_netflix_key_folder + "ESNID";
    public static final String g_netflix_kpekph = g_netflix_key_folder + "KpeKph";
    public static final String g_netflix_esnid_kpekph = g_netflix_key_folder + "ESNIDKpeKph";

    public static final String g_hdcp14_key = "/vendor/tvcertificate/hdcp_key.bin";
    public static final String g_hdcp20_key = "/vendor/tvcertificate/hdcp2_key.bin";

    public static final String g_playready25_folder = "/vendor/tvcertificate/PLAYREADY25/";
    public static final String g_playready25_key_priv = g_playready25_folder + "zgpriv.dat";
    public static final String g_playready25_key_cert = g_playready25_folder + "bgroupcert.dat";

    public static final String g_playready30_folder = "/vendor/tvcertificate/PLAYREADY30/";
    public static final String g_playready30_key_priv = g_playready30_folder + "zgpriv_protected.dat";
    public static final String g_playready30_key_cert = g_playready30_folder + "bgroupcert.dat";

    public static final String g_playready40_folder = g_playready30_folder;
    public static final String g_playready40_key_priv = g_playready30_key_priv;
    public static final String g_playready40_key_cert = g_playready30_key_cert;

    public static final String g_hash_key = "/vendor/tvcertificate/hashkey.sign";
    public static final String g_tv_serial_numbers = "/vendor/tvcertificate/TVSerialNumbers.ini";
    public static final String g_data_index_smart_logger_project_id = "SMART_LOGGER:LOGGER_PROJECT_ID";

    public static final String g_keymaster_folder = "/vendor/tvcertificate/keymaster/";
    public static final String g_keymaster_key = g_keymaster_folder + "attestkey.bin";

    public static final String g_opapp_key_folder = "/vendor/tvcertificate/webplatform/opapp/";
    public static final String g_opapp_key_hdplus_folder = "/vendor/tvcertificate/webplatform/opapp/HD+/";
    public static final String g_opapp_key_m7_folder = "/vendor/tvcertificate/webplatform/opapp/M7/";
    public static final String g_opapp_key_opapp_certificate = "Operator-Certificate.crt";
    public static final String g_opapp_key_term_certificate = "manufacturer-terminal-packaging-cert.crt";
    public static final String g_opapp_key_term_key = "manufacturer-terminal-packaging.key";
    public static final String g_opapp_key_term_pass = "manufacturer-terminal-packaging-key.password";

    private static final String g_project_id_file = "/vendor/project_id/project_id.ini";

    private static final String g_data_index_folder = "/vendor/cusdata/config/dataIndex/";
    private static final String g_default_data_index_file = "/vendor/cusdata/config/dataIndex/dataIndex_1.ini";

    private static final String g_als_ini_file = "/vendor/tvconfig/config/ALS.ini";

    private static final String g_data_index_boot_logo_file_key = "BootLogoMusic:m_pBootLogoMusicCfg_File";
    private static final String g_data_index_boot_music_file_key = "BootLogoMusic:m_pBootLogoMusic_Path";
    private static final String g_data_index_edid_bin_folder_key = "EDID:m_pEdidBinPath";
    private static final String g_data_index_edid_config_file_key = "EDID:m_pIniEdidCfgFile";
    private static final String g_data_index_panel_file_key = "panel:m_pPanelName";
    private static final String g_data_index_ldm_path_key = "LDM:m_pLDMPath";
    private static final String g_data_index_customer_file_key = "model:m_customerIniFile";
    private static final String g_data_index_pq_folder_key = "panel:PQBinPathName";
    private static final String g_data_index_color_file_key = "PQ_general:m_pIniColorTuner_File";
    private static final String g_data_index_color_remapping_file_key = "PQ_general:m_pIniColorRemapping";
    private static final String g_data_index_gamma_file_key = "PQ_general:m_pIniGammaTable_File";
    private static final String g_data_index_nla_file_key = "PQ_general:m_pIniNLA_File";
    private static final String g_data_index_config_default_value_file_key = "acfg:m_pConfigDefaultValue_File";
    private static final String g_data_index_gamma_bin_folder_key = "GAMMA_BIN:m_pGammaBinPath";
    private static final String g_data_index_board_file_key = "board:m_pBoardName";
    private static final String g_data_index_tcon_bin_folder_key = "TCON_BIN:m_pIniTconBinPath";
    private static final String g_data_index_tcon_config_file_key = "TCON_BIN:m_pIniTconCfgFile";
    private static final String g_data_index_aq_input_source_folder_key = "AQ:AqInputSrcPathName";
    private static final String g_data_index_aq_amp_folder_key = "AMP:m_pAMPCfgPathName";
    private static final String g_data_index_aq_sound_style_folder_key = "AQ:AqSoundStylePathName";
    private static final String g_data_index_aq_dap_folder_key = "AQ:AqDapPathName";
    private static final String g_data_index_aq_dtsvx_folder_key = "AQ:AqDtsVxPathName";
    private static final String g_data_index_tv_wizard_country_file_key = "apk:m_pWizardCountry_File";
    private static final String g_data_index_customization_func_file_key = "Misc:m_pCustomizationFunc_File";
    private static final String g_data_index_ir_config_file_key = "ir:m_pIrConfig_File";

    private enum MedusaProjectID
    {
        // Project ID values are defined in the following page:
        // https://rdwiki.vestel.com.tr/display/MEDUSA/How+To+Set+Medusa+ProjectID+Manually

        /*  0 */ PID_INVALID,
        /*  1 */ PID_LCD_COMMON,
        /*  2 */ PID_LCD_TCONLESS_COMMON,
        /*  3 */ PID_OLED_COMMON,
        /*  4 */ PID_LCD_PANASONIC,
        /*  5 */ PID_LCD_TUNERLESS_COMMON,
        /*  6 */ PID_LCD_VRR_COMMON,
        /*  7 */ PID_OLED_PANASONIC,
        /*  8 */ PID_LCD_VRR_PANASONIC,
        /*  9 */ PID_LCD_TCONLESS_PANASONIC,
        /* 10 */ PID_V22_OLED_COMMON,
        /* 11 */ PID_V22_OLED_PANASONIC
    }
    private static final MedusaProjectID g_medusa_project_ids[] = MedusaProjectID.values();

    private enum ComaProjectID
    {
        // Project ID values are defined in the following page:
        // https://rdwiki.vestel.com.tr/display/COMA/How+To+Set+Coma+ProjectID+Manually

        /*  0 */ PID_INVALID,
        /*  1 */ PID_LCD_COMMON,
        /*  2 */ PID_LCD_TCONLESS_COMMON,
        /*  3 */ PID_LCD_PANASONIC,
        /*  4 */ PID_LCD_TUNERLESS_COMMON,
        /*  5 */ PID_LCD_TCONLESS_PANASONIC,
        /*  6 */ PID_LCD_HD_COMMON,
        /*  7 */ PID_LCD_TCONLESS_HD_COMMON,
        /*  8 */ PID_LCD_HD_PANASONIC
    }
    private static final ComaProjectID g_coma_project_ids[] = ComaProjectID.values();

    private enum NeedleProjectID
    {
        /*  0 */ PID_INVALID,
        /*  1 */ PID_LCD_COMMON,
    }
    private static final NeedleProjectID g_needle_project_ids[] = NeedleProjectID.values();

    private enum MalinProjectID
    {
        // Project ID values are defined in the following page:
        // https://rdwiki.vestel.com.tr/display/MALIN/How+To+Set+Malin+ProjectID+Manually

        /*  0 */ PID_INVALID,
        /*  1 */ PID_LCD_COMMON,
        /*  2 */ PID_LCD_TCONLESS_COMMON,
        /*  3 */ PID_LCD_TUNERLESS_COMMON,
        /*  4 */ PID_LCD_PANASONIC,
        /*  5 */ PID_LCD_TCONLESS_PANASONIC
    }
    private static final MalinProjectID g_malin_project_ids[] = MalinProjectID.values();

    private enum HockeyProjectID
    {
        // Project ID values are defined in the following page:
        // https://rdwiki.vestel.com.tr/display/HOCKEY/VRR+Support

        /*  0 */ PID_INVALID,
        /*  1 */ PID_LCD_COMMON,
        /*  2 */ PID_LCD_TCONLESS_COMMON,
        /*  3 */ PID_LCD_TCONLESS_PANASONIC,
        /*  4 */ PID_LCD_PANASONIC,
        /*  5 */ PID_LCD_TUNERLESS_COMMON,
        /*  6 */ PID_LCD_VRR_COMMON,
        /*  7 */ PID_OLED_PANASONIC,
        /*  8 */ PID_LCD_DOLBY_VISION_IQ_COMMON,
        /*  9 */ PID_OLED_COMMON,
        /* 10 */ PID_MINILED_COMMON,
        /* 11 */ PID_FFGA_COMMON
    }
    private static final HockeyProjectID g_hockey_project_ids[] = HockeyProjectID.values();

    private enum SunflowerProjectID
    {
        /*  0 */ PID_INVALID,
        /*  1 */ PID_LCD_COMMON,
    }
    private static final SunflowerProjectID g_sunflower_project_ids[] = SunflowerProjectID.values();

    private static int m_project_id = 1;
    private static String m_data_index_file_path = "";

    /**
     * @brief Initializes project ID and data index file path
     * @note This function should be called before getting any file or folder path
     */
    public static void init()
    {
        setProjectID();
        setDataIndexFile();
    }

    /**
     * @brief Returns the default board folder name of the platform
     * @return The default board folder name of the platform
     */
    private static String getDefaultBoardFolder()
    {
        String board;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case MEDUSA:
            case HOCKEY:
            case SUNFLOWER:
                board = "BD_MT164B_10AT";
                break;

            case COMA:
            case NEEDLE:
                board = "BD_MT5867-H1V1-B2-S";
                break;

            case MALIN:
            default:
                board = "BD_MT168B-10AT-19133";
                break;
        }

        return "/vendor/cusdata/bsp/board/" + board + "/";
    }

    /**
     * @brief Reads the project ID from "/vendor/project_id/project_id.ini" file
     */
    public static void setProjectID()
    {
        try
        {
            String project_id_str = VestelFileSystem.getIniParameter(g_project_id_file, "model:gProjectID");
            int project_id = Integer.parseInt(project_id_str);

            VestelProjectType project_type = VestelSystemProperties.getProjectType();
            int project_id_count;

            switch (project_type)
            {
                case MEDUSA:
                    project_id_count = g_medusa_project_ids.length;
                    break;

                case COMA:
                    project_id_count = g_coma_project_ids.length;
                    break;

                case NEEDLE:
                    project_id_count = g_needle_project_ids.length;
                    break;

                case MALIN:
                    project_id_count = g_malin_project_ids.length;
                    break;

                case HOCKEY:
                    project_id_count = g_hockey_project_ids.length;
                    break;

                case SUNFLOWER:
                    project_id_count = g_sunflower_project_ids.length;
                    break;

                default:
                    project_id_count = 1;
                    break;
            }

            if ((project_id > 0) && (project_id < project_id_count))
            {
                m_project_id = project_id;
                VestelLog.debug(TAG, "setProjectID() Valid project_id = " + project_id);
            }
            else
            {
                m_project_id = 1;
                VestelLog.error(TAG, "setProjectID() Invalid project_id = " + project_id);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @brief Returns the project ID read from "/vendor/project_id/project_id.ini" file
     * @return The project ID read from "/vendor/project_id/project_id.ini" file
     *         or 1 if project ID has not been read from the file
     */
    public static int getProjectID()
    {
        return m_project_id;
    }

    /**
     * @brief Returns string representation of the project ID read from "/vendor/project_id/project_id.ini" file
     * @return String representation in PID_[LCD|OLED|MINILED]_[VRR|TUNERLESS|TCONLESS]_[COMMON|PANASONIC] format
     */
    public static String getProjectIDName()
    {
        String project_id_name = "";

        try
        {
            VestelProjectType project_type = VestelSystemProperties.getProjectType();

            switch (project_type)
            {
                case MEDUSA:
                    project_id_name = g_medusa_project_ids[m_project_id].name();
                    break;

                case COMA:
                    project_id_name = g_coma_project_ids[m_project_id].name();
                    break;

                case NEEDLE:
                    project_id_name = g_needle_project_ids[m_project_id].name();
                    break;

                case MALIN:
                    project_id_name = g_malin_project_ids[m_project_id].name();
                    break;

                case HOCKEY:
                    project_id_name = g_hockey_project_ids[m_project_id].name();
                    break;

                case SUNFLOWER:
                    project_id_name = g_sunflower_project_ids[m_project_id].name();
                    break;

                default:
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return project_id_name;
    }

    /**
     * @brief Sets absolute path of data index file that contains project specific file paths
     */
    public static void setDataIndexFile()
    {
        String data_index_file_prefix = "dataIndex_";
        String data_index_file_extension = ".ini";
        String data_index_file_name = data_index_file_prefix + m_project_id + data_index_file_extension;
        String data_index_file_path = g_data_index_folder + data_index_file_name;

        VestelLog.debug(TAG, "setDataIndexFile() data_index_file_path = " + data_index_file_path);

        boolean file_exist = false;

        if (file_exist)
        {
            VestelLog.info(TAG, "setDataIndexFile() Valid data_index_file_path = " + data_index_file_path);
        }
        else
        {
            VestelLog.error(TAG, "setDataIndexFile() Invalid data_index_file_path = " + data_index_file_path);
            data_index_file_path = g_default_data_index_file;
        }

        m_data_index_file_path = data_index_file_path;
    }

    /**
     * @brief Gets absolute path of data index file that contains project specific file paths
     * @return Absolute path of data index file based on the current project ID
     */
    public static String getDataIndexFile()
    {
        return m_data_index_file_path;
    }

    /**
     * @brief Reads absolute path of the specified file from data index file of the project
     * @param item_key File type specified in "<section>:<field>" format
     * @return Absolute path of the specified file from data index file of the project
     */
    public static String getPathFromDataIndexFile(String item_key)
    {
        String path;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                path = null;
                break;

            default:
                {
                    VestelLog.debug(TAG, "getPathFromDataIndexFile() item_key = " + item_key);
                    path = VestelFileSystem.getIniParameter(m_data_index_file_path, item_key);
                    VestelLog.debug(TAG, "getPathFromDataIndexFile() path_str = " + path);
                }
                break;
        }

        return path;
    }

    /**
     * @brief Sets absolute path of panel file and saves into data index file of the project
     * @param path Absolute path of panel file
     * @return RET_OK if absolute path of panel file is successfully set and saved
     */
    public static int setPanelFile(String path)
    {
        return -1;
    }

    /**
     * @brief Returns absolute path of panel file
     * @return Absolute path of panel file
     */
    public static String getPanelFile()
    {
        return "";
    }

    /**
     * @brief Returns absolute path of chip folder
     * @return Absolute path of chip folder
     */
    public static String getChipFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/chip/";
                break;
        }

        return folder;
    }

    /**
     * @brief Returns absolute path of boot folder that contains boot logo and music files
     * @return Absolute path of boot folder that contains boot logo and music files
     */
    private static String getBootFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/common/misc/";
                break;
        }

        return folder;
    }

    /**
     * @brief Returns absolute path of boot logo file
     * @return Absolute path of boot logo file
     */
    public static String getBootLogoFile()
    {
        String file = getBootFolder() + getBootLogoFileName();
        String path = getPathFromDataIndexFile(g_data_index_boot_logo_file_key);

        if (path != null)
        {
            int last_file_sep_pos = path.lastIndexOf('/');
            int path_size = path.length();

            VestelLog.debug(TAG, "getBootLogoFile() path = " + path);
            VestelLog.debug(TAG, "getBootLogoFile() last_file_sep_pos = " + last_file_sep_pos);
            VestelLog.debug(TAG, "getBootLogoFile() path_size = " + path_size);

            if ((last_file_sep_pos >= 0) && (last_file_sep_pos < path_size))
            {
                path = path.substring(0, last_file_sep_pos + 1);
                boolean folder_exist = VestelFileSystem.isFolderExist(path);
                if (folder_exist)
                {
                    file = path + getBootLogoFileName();
                }
                else
                {
                    VestelLog.error(TAG, "getBootLogoFile() Folder not exists!! = " + path);
                }
            }
            else
            {
                VestelLog.error(TAG, "getBootLogoFile() Invalid path = " + path);
            }
        }

        VestelLog.debug(TAG, "getBootLogoFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of boot music file
     * @return Absolute path of boot music file
     */
    public static String getBootMusicFile()
    {
        String file = getBootFolder() + getBootMusicFileName();
        String path = getPathFromDataIndexFile(g_data_index_boot_music_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getBootMusicFile() path = " + path);
            VestelLog.debug(TAG, "getBootMusicFile() path_size = " + path_size);

            if (!path.substring(path_size - 1).equals("/"))
            {
                path = path + "/";
            }

            boolean folder_exist = VestelFileSystem.isFolderExist(path);
            if (folder_exist)
            {
                file = path + getBootMusicFileName();
            }
            else
            {
                VestelLog.error(TAG, "getBootMusicFile() Folder not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getBootMusicFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of HDMI EDID BIN folder
     * @return Absolute path of HDMI EDID BIN folder
     */
    public static String getHDMIEdidBinFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/EDID_BIN/";
                break;

            default:
                folder = getEdidBinFolder();
                break;
        }

        return folder;
    }

    /**
     * @brief Returns absolute path of VGA EDID BIN folder
     * @return Absolute path of VGA EDID BIN folder
     */
    public static String getVGAEdidBinFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/EDID_BIN/";
                break;

            default:
                folder = getEdidBinFolder();
                break;
        }

        return folder;
    }

    /**
     * @brief Returns absolute path of EDID BIN folder
     * @return Absolute path of EDID BIN folder
     */
    private static String getEdidBinFolder()
    {
        String folder = "/vendor/cusdata/bsp/common/EDID_BIN/";
        String path = getPathFromDataIndexFile(g_data_index_edid_bin_folder_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getEdidBinFolder() path = " + path);
            VestelLog.debug(TAG, "getEdidBinFolder() path_size = " + path_size);

            if (!path.substring(path_size - 1).equals("/"))
            {
                path = path + "/";
            }

            boolean folder_exist = VestelFileSystem.isFolderExist(path);
            if (folder_exist)
            {
                folder = path;
            }
            else
            {
                VestelLog.error(TAG, "getEdidBinFolder() Folder not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getEdidBinFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of EDID configuration file
     * @return Absolute path of EDID configuration file
     */
    public static String getEdidConfigFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = getCustomerFile();
                break;

            default:
                file = getDefaultBoardFolder() + "edid_cfg.ini";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_edid_config_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getEdidConfigFile() path = " + path);
            VestelLog.debug(TAG, "getEdidConfigFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getEdidConfigFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getEdidConfigFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of mirror configuration file
     * @return Absolute path of mirror configuration file
     */
    public static String getMirrorConfigFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COMA:
            case NEEDLE:
                file = getBoardFolder() + "mirror_cfg.ini";
                break;

            default:
                file = getCustomerFile();
                break;
        }

        return file;
    }

    /**
     * @brief Returns absolute path of panel folder
     * @return Absolute path of panel folder
     */
    public static String getPanelFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/panel/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/common/panel/";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_panel_file_key);

        if (path != null)
        {
            int last_file_sep_pos = path.lastIndexOf('/');
            int path_size = path.length();

            VestelLog.debug(TAG, "getPanelFolder() path = " + path);
            VestelLog.debug(TAG, "getPanelFolder() last_file_sep_pos = " + last_file_sep_pos);
            VestelLog.debug(TAG, "getPanelFolder() path_size = " + path_size);

            if ((last_file_sep_pos >= 0) && (last_file_sep_pos < path_size))
            {
                path = path.substring(0, last_file_sep_pos + 1);
                boolean folder_exist = VestelFileSystem.isFolderExist(path);
                if (folder_exist)
                {
                    folder = path;
                }
                else
                {
                    VestelLog.error(TAG, "getPanelFolder() Folder not exists!! = " + path);
                }
            }
            else
            {
                VestelLog.error(TAG, "getPanelFolder() Invalid path = " + path);
            }
        }

        VestelLog.debug(TAG, "getPanelFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of panel folder
     * @return Absolute path of panel folder
     */
    public static String getLdmFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/ldm/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/chip/ldm/";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_ldm_path_key);

        if (path != null)
        {
            int last_file_sep_pos = path.lastIndexOf('/');
            int path_size = path.length();

            VestelLog.debug(TAG, "getLdmFolder() path = " + path);
            VestelLog.debug(TAG, "getLdmFolder() last_file_sep_pos = " + last_file_sep_pos);
            VestelLog.debug(TAG, "getLdmFolder() path_size = " + path_size);

            if ((last_file_sep_pos >= 0) && (last_file_sep_pos < path_size))
            {
                path = path.substring(0, last_file_sep_pos + 1);
                boolean folder_exist = VestelFileSystem.isFolderExist(path);
                if (folder_exist)
                {
                    folder = path;
                }
                else
                {
                    VestelLog.error(TAG, "getLdmFolder() Folder not exists!! = " + path);
                }
            }
            else
            {
                VestelLog.error(TAG, "getLdmFolder() Invalid path = " + path);
            }
        }

        VestelLog.debug(TAG, "getLdmFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of PQ folder
     * @return Absolute path of PQ folder
     */
    public static String getPqFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/pq/";
                break;
            case MEDUSA:
            case HOCKEY:
            case SUNFLOWER:
                if(VestelPanelProperties.isPanelOLED())
                {
                    folder = "/vendor/cusdata/bsp/chip/pq/pq_oled/";
                }
                else
                {
                    folder = "/vendor/cusdata/bsp/chip/pq/";
                }
                break;
            default:
                folder = "/vendor/cusdata/bsp/chip/pq/";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_pq_folder_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getPqFolder() path = " + path);
            VestelLog.debug(TAG, "getPqFolder() path_size = " + path_size);

            if (!path.substring(path_size - 1).equals("/"))
            {
                path = path + "/";
            }

            boolean folder_exist = VestelFileSystem.isFolderExist(path);
            if (folder_exist)
            {
                folder = path;
            }
            else
            {
                VestelLog.error(TAG, "getPqFolder() Folder not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getPqFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of PQ general folder
     * @return Absolute path of PQ general folder
     */
    public static String getPqGeneralFolder()
    {
        String folder = "/vendor/tvconfig/config/PQ_general/";
        String path = getPathFromDataIndexFile(g_data_index_gamma_file_key);

        if (path != null)
        {
            int last_file_sep_pos = path.lastIndexOf('/');
            int path_size = path.length();

            VestelLog.debug(TAG, "getPqGeneralFolder() path = " + path);
            VestelLog.debug(TAG, "getPqGeneralFolder() last_file_sep_pos = " + last_file_sep_pos);
            VestelLog.debug(TAG, "getPqGeneralFolder() path_size = " + path_size);

            if ((last_file_sep_pos >= 0) && (last_file_sep_pos < path_size))
            {
                path = path.substring(0, last_file_sep_pos + 1);
                boolean folder_exist = VestelFileSystem.isFolderExist(path);
                if (folder_exist)
                {
                    folder = path;
                }
                else
                {
                    VestelLog.error(TAG, "getPqGeneralFolder() Folder not exists!! = " + path);
                }
            }
            else
            {
                VestelLog.error(TAG, "getPqGeneralFolder() Invalid path = " + path);
            }
        }

        VestelLog.debug(TAG, "getPqGeneralFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of backlight file
     * @return Absolute path of backlight file
     */
    public static String getBacklightFile()
    {
        return getPqGeneralFolder() + "Backlight.ini";
    }

    /**
     * @brief Returns absolute path of color file
     * @return Absolute path of color file
     */
    public static String getColorFile()
    {
        String file = getPqGeneralFolder() + "Color.ini";
        String path = getPathFromDataIndexFile(g_data_index_color_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getColorFile() path = " + path);
            VestelLog.debug(TAG, "getColorFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getColorFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getColorFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of color remapping file
     * @return Absolute path of color remapping file
     */
    public static String getColorRemappingFile()
    {
        String file = getPqGeneralFolder() + "ColorRemapping.ini";
        String path = getPathFromDataIndexFile(g_data_index_color_remapping_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getColorRemappingFile() path = " + path);
            VestelLog.debug(TAG, "getColorRemappingFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getColorRemappingFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getColorRemappingFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of gamma file
     * @return Absolute path of gamma file
     */
    public static String getGammaFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/tvconfig/config/gamma/gammatable_1.ini";
                break;

            default:
                file = getPqGeneralFolder() + "gammatable_1.ini";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_gamma_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getGammaFile() path = " + path);
            VestelLog.debug(TAG, "getGammaFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getGammaFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getGammaFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of NLA file
     * @return Absolute path of NLA file
     */
    public static String getNlaFile()
    {
        String file = getPqGeneralFolder() + "NLA.ini";
        String path = getPathFromDataIndexFile(g_data_index_nla_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getNlaFile() path = " + path);
            VestelLog.debug(TAG, "getNlaFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getNlaFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getNlaFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of HDR BIN folder
     * @return Absolute path of HDR BIN folder
     */
    public static String getHdrBinFolder()
    {
        String folder = "/vendor/tvconfig/config/HDR_BIN/";

        VestelLog.debug(TAG, "getHdrBinFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of HDR BIN folder for version 1.6.1.1
     * @return Absolute path of HDR BIN folder for version 1.6.1.1
     */
    public static String getHdr1611BinFolder()
    {
        String folder = "/vendor/tvconfig/config/HDR_BIN/1.6.1.1/";

        VestelLog.debug(TAG, "getHdr1611BinFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of Dolby binary file
     * @return Absolute path of Dolby binary file
     */
    public static String getDolbyBinFile()
    {
        return getHdrBinFolder() +  "dolby.bin";
    }

    /**
     * @brief Returns absolute path of dolby factory cfg file
     * @return Absolute path of olby factory cfg file
     */
    public static String getDolbyFactoryCfgFile()
    {
        return getHdrBinFolder() +  "dolby_factory.cfg";
    }

    /**
     * @brief Returns absolute path of als ini file
     * @return Absolute path of als ini file
     */
    public static String getAlsiniFile()
    {
        return g_als_ini_file;
    }

    /**
     * @brief Returns absolute path of ldm ini file
     * @return Absolute path of ldm ini file
     */
    public static String getLdminiFile()
    {
        String g_ldm_ini_file = getLdmFolder() + "ldm.ini";
        return g_ldm_ini_file;
    }

    /**
     * @brief Returns absolute path of customer configuration file
     * @return Absolute path of customer configuration file
     */
    public static String getCustomerFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/config/model/Customer_1.ini";
                break;

            default:
                file = getDefaultBoardFolder() + "model/Customer_1.ini";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_customer_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getCustomerFile() path = " + path);
            VestelLog.debug(TAG, "getCustomerFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getCustomerFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getCustomerFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of default value configuration file
     * @return Absolute path of default value configuration file
     */
    public static String getConfigDefaultValueFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/tvconfig/apollo/ConfigDefaultValue.ini";
                break;

            default:
                file = "/vendor/cusdata/apollo/common/misc/ConfigDefaultValue.ini";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_config_default_value_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getConfigDefaultValueFile() path = " + path);
            VestelLog.debug(TAG, "getConfigDefaultValueFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getConfigDefaultValueFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getConfigDefaultValueFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of default value configuration file
     * @return Absolute path of default value configuration file
     */
    public static String getConfigFileNamesFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/tvconfig/config/customer/ConfigFileNames.ini";
                break;

            default:
                file = "/vendor/cusdata/bsp/common/customer/ConfigFileNames.ini";
                break;
        }

        return file;
    }

    /**
     * @brief Returns absolute path of gamma BIN folder
     * @return Absolute path of gamma BIN folder
     */
    public static String getGammaBinFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = null;
                break;

            default:
                folder = "/vendor/cusdata/bsp/common/GAMMA_BIN/";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_gamma_bin_folder_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getGammaBinFolder() path = " + path);
            VestelLog.debug(TAG, "getGammaBinFolder() path_size = " + path_size);

            if (!path.substring(path_size - 1).equals("/"))
            {
                path = path + "/";
            }

            boolean folder_exist = VestelFileSystem.isFolderExist(path);
            if (folder_exist)
            {
                folder = path;
            }
            else
            {
                VestelLog.error(TAG, "getGammaBinFolder() Folder not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getGammaBinFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of board folder
     * @return Absolute path of board folder
     */
    public static String getBoardFolder()
    {
        String folder = getDefaultBoardFolder();
        String path = getPathFromDataIndexFile(g_data_index_board_file_key);

        if (path != null)
        {
            int last_file_sep_pos = path.lastIndexOf('/');
            int path_size = path.length();

            VestelLog.debug(TAG, "getBoardFolder() path = " + path);
            VestelLog.debug(TAG, "getBoardFolder() last_file_sep_pos = " + last_file_sep_pos);
            VestelLog.debug(TAG, "getBoardFolder() path_size = " + path_size);

            if ((last_file_sep_pos >= 0) && (last_file_sep_pos < path_size))
            {
                path = path.substring(0, last_file_sep_pos + 1);
                boolean folder_exist = VestelFileSystem.isFolderExist(path);
                if (folder_exist)
                {
                    folder = path;
                }
                else
                {
                    VestelLog.error(TAG, "getBoardFolder() Folder not exists!! = " + path);
                }
            }
            else
            {
                VestelLog.error(TAG, "getBoardFolder() Invalid path = " + path);
            }
        }

        VestelLog.debug(TAG, "getBoardFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of board file
     * @return Absolute path of board file
     */
    public static String getBoardFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/tvconfig/config/board.ini";
                break;
            case HOCKEY:
            case SUNFLOWER:
                if(VestelPanelProperties.isPanelOLED())
                {
                    file = getDefaultBoardFolder() + "board_oled.ini";
                }
                else
                {
                    file = getDefaultBoardFolder() + "board.ini";
                }
                break;
            default:
                file = getDefaultBoardFolder() + "board.ini";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_board_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getBoardFile() path = " + path);
            VestelLog.debug(TAG, "getBoardFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getBoardFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getBoardFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of ursa orbit configuration file
     * @return Absolute path of ursa orbit configuration file
     */
    public static String getUrsaOrbitConfigFile()
    {
        return getBoardFolder() + "ursa_orbit_cfg.ini";
    }

    /**
     * @brief Returns absolute path of IR remote controller configuration file
     * @return Absolute path of IR remote controller configuration file
     */
    public static String getIrConfigFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/tvconfig/config/ir_config.ini";
                break;

            default:
                file = getDefaultBoardFolder() + "ir_config.ini";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_ir_config_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getIrConfigFile() path = " + path);
            VestelLog.debug(TAG, "getIrConfigFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getIrConfigFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getIrConfigFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of TCON configuration file
     * @return Absolute path of TCON configuration file
     */
    public static String getTconConfigFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/tvconfig/config/tcon/tcon.ini";
                break;

            default:
                file = getDefaultBoardFolder() + "tcon_cfg.ini";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_tcon_config_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getTconConfigFile() path = " + path);
            VestelLog.debug(TAG, "getTconConfigFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getTconConfigFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getTconConfigFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of TCON BIN folder
     * @return Absolute path of TCON BIN folder
     */
    public static String getTconBinFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = null;
                break;

            default:
                folder = "/vendor/cusdata/bsp/common/TCON_BIN/";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_tcon_bin_folder_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getTconBinFolder() path = " + path);
            VestelLog.debug(TAG, "getTconBinFolder() path_size = " + path_size);

            if (!path.substring(path_size - 1).equals("/"))
            {
                path = path + "/";
            }

            boolean folder_exist = VestelFileSystem.isFolderExist(path);
            if (folder_exist)
            {
                folder = path;
            }
            else
            {
                VestelLog.error(TAG, "getTconBinFolder() Folder not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getTconBinFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of AQ folder
     * @return Absolute path of AQ folder
     */
    public static String getAqFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/aq/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/chip/aq/";
                break;
        }
        return folder;
    }

    public static String getAqInputSourceFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/aq/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/chip/aq/InputSrc/";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_aq_input_source_folder_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getAqInputSrcFolder() path = " + path);
            VestelLog.debug(TAG, "getAqInputSrcFolder() path_size = " + path_size);

            if (!path.substring(path_size - 1).equals("/"))
            {
                path = path + "/";
            }

            boolean folder_exist = VestelFileSystem.isFolderExist(path);
            if (folder_exist)
            {
                folder = path;
            }
            else
            {
                VestelLog.error(TAG, "getAqInputSrcFolder() Folder not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getAqInputSrcFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of AQ AMP folder
     * @return Absolute path of AQ AMP folder
     */
    public static String getAqAmpFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/chip/amp/";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_aq_amp_folder_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getAqAmpFolder() path = " + path);
            VestelLog.debug(TAG, "getAqAmpFolder() path_size = " + path_size);

            if (!path.substring(path_size - 1).equals("/"))
            {
                path = path + "/";
            }

            boolean folder_exist = false;
            if (folder_exist)
            {
                folder = path;
            }
            else
            {
                VestelLog.error(TAG, "getAqAmpFolder() Folder not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getAqAmpFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of AQ sound style folder
     * @return Absolute path of AQ sound style folder
     */
    public static String getAqSoundStyleFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/aq/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/chip/aq/SoundStyle/";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_aq_sound_style_folder_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getAqSoundStyleFolder() path = " + path);
            VestelLog.debug(TAG, "getAqSoundStyleFolder() path_size = " + path_size);

            if (!path.substring(path_size - 1).equals("/"))
            {
                path = path + "/";
            }

            boolean folder_exist = VestelFileSystem.isFolderExist(path);
            if (folder_exist)
            {
                folder = path;
            }
            else
            {
                VestelLog.error(TAG, "getAqSoundStyleFolder() Folder not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getAqSoundStyleFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of AQ DAP folder
     * @return Absolute path of AQ DAP folder
     */
    public static String getAqDapFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder =  "/vendor/tvconfig/config/aq/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/chip/aq/Dap/";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_aq_dap_folder_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getAqDapFolder() path = " + path);
            VestelLog.debug(TAG, "getAqDapFolder() path_size = " + path_size);

            if (!path.substring(path_size - 1).equals("/"))
            {
                path = path + "/";
            }

            boolean folder_exist = VestelFileSystem.isFolderExist(path);
            if (folder_exist)
            {
                folder = path;
            }
            else
            {
                VestelLog.error(TAG, "getAqDapFolder() Folder not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getAqDapFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of AQ DTS VX folder
     * @return Absolute path of AQ DTS VX folder
     */
    public static String getAqDtsVxFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/aq/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/chip/aq/DtsVx/";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_aq_dtsvx_folder_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getAqDtsVxFolder() path = " + path);
            VestelLog.debug(TAG, "getAqDtsVxFolder() path_size = " + path_size);

            if (!path.substring(path_size - 1).equals("/"))
            {
                path = path + "/";
            }

            boolean folder_exist = VestelFileSystem.isFolderExist(path);
            if (folder_exist)
            {
                folder = path;
            }
            else
            {
                VestelLog.error(TAG, "getAqDtsVxFolder() Folder not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getAqDtsVxFolder() folder_path = " + folder);
        return folder;
    }

    /**
     * @brief Returns absolute path of TV wizard country file
     * @return Absolute path of TV wizard country file
     */
    public static String getTvWizardCountryFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/tvconfig/apollo/tv_wizard_country_config.xml";
                break;

            default:
                file = "/vendor/cusdata/app/Wizard/tv_wizard_country_config.xml";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_tv_wizard_country_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getTvWizardCountryFilePath() path = " + path);
            VestelLog.debug(TAG, "getTvWizardCountryFilePath() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getTvWizardCountryFilePath() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getTvWizardCountryFilePath() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of customization func file
     * @return Absolute path of customization func file
     */
    public static String getCustomizationFuncFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/tvconfig/apollo/CustomizationFunc.ini";
                break;
            case NEEDLE:
                file = "/vendor/cusdata/apollo/common/customizationfunc/us/CustomizationFunc.ini";
                break;
            case SUNFLOWER:
                file = "/vendor/cusdata/apollo/common/customizationfunc/CustomizationFuncUS.ini";
                break;
            default:
                file = "/vendor/cusdata/apollo/common/customizationfunc/CustomizationFunc.ini";
                break;
        }

        String path = getPathFromDataIndexFile(g_data_index_customization_func_file_key);

        if (path != null)
        {
            int path_size = path.length();

            VestelLog.debug(TAG, "getCustomizationFuncFile() path = " + path);
            VestelLog.debug(TAG, "getCustomizationFuncFile() path_size = " + path_size);

            boolean file_exist = VestelFileSystem.isFileExist(path);
            if (file_exist)
            {
                file = path;
            }
            else
            {
                VestelLog.error(TAG, "getCustomizationFuncFile() File not exists!! = " + path);
            }
        }

        VestelLog.debug(TAG, "getCustomizationFuncFile() file_path = " + file);
        return file;
    }

    /**
     * @brief Returns absolute path of channel list folder
     * @return Absolute path of channel list folder
     */
    public static String getChannelListFolder()
    {
        String channel_list_folder;

        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
                channel_list_folder = "/vendor/tvconfig/Cosmos/config/Channel_list/channel_list";
                break;

            case COMET:
                channel_list_folder = "/vendor/tvconfig/Android_TV/config/Channel_list/channel_list";
                break;

            case HOCKEY:
                channel_list_folder = "/vendor/tvconfig/apollo/";
                break;

            case SUNFLOWER:
                channel_list_folder = "/vendor/tvconfig/apollo/us/";
                break;

            case NEEDLE:
                channel_list_folder = "/vendor/tvconfig/apollo/misc/us/";
                break;

             default:
                channel_list_folder = "/vendor/tvconfig/apollo/misc/";
                break;
        }

        return channel_list_folder;
    }

    /**
     * @brief Returns absolute path of Satco DX folder
     * @return Absolute path of Satco DX folder
     */
    public static String getSatcodxFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/satcodx/";
                break;

            default:
                folder = "/vendor/cusdata/bsp/common/satcodx/";
                break;
        }
        return folder;
    }

    /**
     * @brief Returns absolute path of OLED panel configuration file
     * @return Absolute path of OLED panel configuration file
     */
    public static String getOledPanelConfigFile()
    {
        return getBoardFolder() + "oled_panel_misc_cfg.ini";
    }

    /**
     * @brief Returns absolute path of factory flag file that keeps the device in factory mode
     * @return Absolute path of factory flag file that keeps the device in factory mode
     */
    public static String getFactoryFlagFile()
    {
        return  "/vendor/tvconfig/factory_flag";
    }

    /**
     * @brief Returns absolute path of factory ini file
     * @return Absolute path of factory ini file
     */
    public static String getFactoryIniFile()
    {
        return "/vendor/cusdata/factory/AutoEnterFactory.ini";
    }

    /**
     * @brief Returns factory mode GPIO
     * @return Factory mode GPIO
     */
    public static int getFactoryModeGpio()
    {
        int gpio;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                gpio = 0x32B;
                break;

            case MEDUSA:
            case MALIN:
            case HOCKEY:
            case SUNFLOWER:
                gpio = 0x32A;
                break;

            case COMA:
            case NEEDLE:
                gpio = 0x327;
                break;

            default:
                gpio = 0x000;
                break;
        }

        return gpio;
    }

    /**
     * @brief Returns absolute path of FVP folder
     * @return Absolute path of FVP folder
     */
    public static String getFVPFolder()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/tvcertificate/WEBPLATFORM_CA/";
                break;

            default:
                file = "/vendor/tvcertificate/webplatform/";
                break;
        }
        return file;
    }

    /**
     * @brief Returns FVP key file name for the project
     * @return FVP key file name for the project
     */
    public static String getFVPVestelFileName()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
                file = "vestel_mb170_fvp_key.p12";
                break;

            case COMET:
                file = "vestel_mb171_fvp_key.p12";
                break;

            default:
                file = "vestel_mb185_fvp_key.p12";
                break;
        }
        return file;
    }

    /**
     * @brief Returns absolute path of FVP key file
     * @return Absolute path of FVP key file
     */
    public static String getFVPVestelFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
                file = "/vendor/tvcertificate/WEBPLATFORM_CA/vestel_mb170_fvp_key.p12";
                break;

            case COMET:
                file = "/vendor/tvcertificate/WEBPLATFORM_CA/vestel_mb171_fvp_key.p12";
                break;

            default:
                file = "/vendor/tvcertificate/webplatform/vestel_mb185_fvp_key.p12";
                break;
        }
        return file;
    }

    /**
     * @brief Returns FVP indermediate key file name for the project
     * @return FVP indermediate key file name for the project
     */
    public static String getFVPVestelIntermediateFileName()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
                file = "vestel_mb170_fvp_intermediate_noencypt.key";
                break;

            case COMET:
                file = "vestel_mb171_fvp_intermediate_noencypt.key";
                break;

            default:
                file = "vestel_mb185_fvp_intermediate.key";
                break;
        }
        return file;
    }

    /**
     * @brief Returns absolute path of FVP intermediate key file
     * @return Absolute path of FVP intermediate key file
     */
    public static String getFVPVestelIntermediateFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
                file = "/vendor/tvcertificate/vestel_mb170_fvp_intermediate_noencypt.key";
                break;

            case COMET:
                file = "/vendor/tvcertificate/vestel_mb171_fvp_intermediate_noencypt.key";
                break;

            default:
                file = "/vendor/tvcertificate/webplatform/fvp_intermediate_key.bin";
                break;
        }
        return file;
    }

    /**
     * @brief Returns absolute path of FVP encrypted intermediate key file
     * @return Absolute path of FVP encrypted intermediate key file
     */
    public static String getFVPVestelEncIntermediateFile()
    {
        return "/vendor/tvcertificate/enc_intermediate_key";
    }

    /**
     * @brief Returns FVP OEM key file name for the project
     * @return FVP OEM key file name for the project
     */
    public static String getFVPOEMFileName()
    {
        return "oem.p12";
    }

    /**
     * @brief Returns absolute path of FVP OEM key file
     * @return Absolute path of FVP OEM key file
     */
    public static String getFVPOEMFile()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "/vendor/tvcertificate/WEBPLATFORM_CA/oem.p12";
                break;

            default:
                file = "/vendor/tvcertificate/webplatform/oem.p12";
                break;
        }
        return file;
    }

    /**
     * @brief Returns absolute path of FVP checksum file
     * @return Absolute path of FVP checksum file
     */
    public static String getFVPChecksumFile()
    {
        return "/vendor/tvcertificate/fvpmd5.txt";
    }

    /**
     * @brief Returns absolute path of Widevine folder
     * @return Absolute path of Widevine folder
     */
    public static String getWidevineFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/widevine/";
                break;

            default:
                folder = "/vendor/tvcertificate/WVCENC/";
                break;
        }
        return folder;
    }

    /**
     * @brief Returns absolute path of Widevine V9 folder
     * @return Absolute path of Widevine V9 folder
     */
    public static String getWidevineV9Folder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/widevinev9/";
                break;

            default:
                folder = "/vendor/tvcertificate/WVCENCV9/";
                break;
        }
        return folder;
    }

    /**
     * @brief Returns absolute path of Nagra folder
     * @return Absolute path of Nagra folder
     */
    public static String getNagraFolder()
    {
        String folder;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                folder = "/vendor/tvconfig/config/NAGRADRM/";
                break;

            default:
                folder = "/vendor/tvcertificate/NAGRADRM/";
                break;
        }
        return folder;
    }

    public static final String g_widevine_key = getWidevineFolder() + "KeyBox.bin";
    public static final String g_widevinev9_key = getWidevineV9Folder() + "KeyBox.bin";
    public static final String g_nagra_key = getNagraFolder() + "NagraKey.bin";

    /**
     * @brief Returns file name of default boot logo
     * @return File name of default boot logo
     */
    public static String getBootLogoFileName()
    {
        String file;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                file = "bootlogo.jpg";
                break;

            default:
                file = "boot0.jpg";
                break;
        }
        return file;
    }

    /**
     * @brief Returns file name of default boot music
     * @return File name of default boot music
     */
    public static String getBootMusicFileName()
    {
        return "boot0.mp3";
    }

    /**
     * @brief Returns absolute path of OppApp folder
     * @return Absolute path of OppApp folder
     */
    public static String getOppAppFolder()
    {
        return "/vendor/tvcertificate/webplatform/";
    }

    /**
     * @brief Returns the list of audio ini files installed on Android device
     * @return The list of audio ini files installed on Android device
     */
    public static String[][] getAudioFiles()
    {
        String[][] audio_files;

        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type)
        {
            case COSMOS:
            case COMET:
                audio_files = new String[][]{
                                {".", "audio_amp_config"},
                                {"aq", "BasicSound_ATV"},
                                {"aq", "BasicSound_CVBS"},
                                {"aq", "BasicSound_DTV"},
                                {"aq", "BasicSound_HDMI"},
                                {"aq", "BasicSound_MM"},
                                {"aq", "BasicSound_Movie"},
                                {"aq", "BasicSound_Music"},
                                {"aq", "BasicSound_News"},
                                {"aq", "BasicSound_Sports"},
                                {"aq", "BasicSound_Standard"},
                                {"aq", "BasicSound_User"},
                                {"aq", "BasicSound_Vivid"},
                                {"aq", "Dap_Movie"},
                                {"aq", "Dap_Music"},
                                {"aq", "Dap_News"},
                                {"aq", "Dap_Standard"},
                                {"aq", "DtsVx_Default"},
                                {"aq", "DtsVx_Movie"},
                                {"aq", "DtsVx_Music"},
                                {"aq", "DtsVx_News"},
                                {"aq", "DtsVx_Sports"},
                                {"aq", "DtsVx_Standard"},
                                {"aq", "DtsVx_User"},
                                {"aq", "DtsVx_Vivid"}
                              };
                break;

            case COMA:
                audio_files = new String[][]{
                                {"amp", "audio_amp_config"},
                                {"amp", "audio_amp_device_0"},
                                {"amp", "audio_amp_device_1"},
                                {"InputSrc", "BasicSound_ATV"},
                                {"InputSrc", "BasicSound_CVBS"},
                                {"InputSrc", "BasicSound_DTV"},
                                {"InputSrc", "BasicSound_HDMI"},
                                {"InputSrc", "BasicSound_MM"},
                                {"SoundStyle", "BasicSound_Movie"},
                                {"SoundStyle", "BasicSound_Music"},
                                {"SoundStyle", "BasicSound_News"},
                                {"SoundStyle", "BasicSound_Sports"},
                                {"SoundStyle", "BasicSound_Standard"},
                                {"SoundStyle", "BasicSound_User"},
                                {"SoundStyle", "BasicSound_Vivid"},
                                {"SoundStyle", "BasicSound_DefaultStyle_DTS"},
                                {"Dap", "Dap_Game"},
                                {"Dap", "Dap_Movie"},
                                {"Dap", "Dap_Music"},
                                {"Dap", "Dap_News"},
                                {"Dap", "Dap_Stadium"},
                                {"Dap", "Dap_Standard"},
                                {"Dap", "Dap_User"},
                                {"DtsVx", "DtsVx_Movie"},
                                {"DtsVx", "DtsVx_Music"},
                                {"DtsVx", "DtsVx_News"},
                                {"DtsVx", "DtsVx_Sports"},
                                {"DtsVx", "DtsVx_Standard"},
                                {"DtsVx", "DtsVx_User"},
                                {"DtsVx", "DtsVx_Vivid"}
                              };
                break;

            default:
                audio_files = new String[][]{
                                {"amp", "audio_amp_config"},
                                {"amp", "audio_amp_device_0"},
                                {"amp", "audio_amp_device_1"},
                                {"InputSrc", "BasicSound_ATV"},
                                {"InputSrc", "BasicSound_DTV"},
                                {"InputSrc", "BasicSound_HDMI"},
                                {"InputSrc", "BasicSound_MM"},
                                {"SoundStyle", "BasicSound_Movie"},
                                {"SoundStyle", "BasicSound_Music"},
                                {"SoundStyle", "BasicSound_News"},
                                {"SoundStyle", "BasicSound_Sports"},
                                {"SoundStyle", "BasicSound_Standard"},
                                {"SoundStyle", "BasicSound_User"},
                                {"SoundStyle", "BasicSound_Vivid"},
                                {"SoundStyle", "BasicSound_DefaultStyle_DTS"},
                                {"Dap", "Dap_Game"},
                                {"Dap", "Dap_Movie"},
                                {"Dap", "Dap_Music"},
                                {"Dap", "Dap_News"},
                                {"Dap", "Dap_Stadium"},
                                {"Dap", "Dap_Standard"},
                                {"Dap", "Dap_User"},
                                {"DtsVx", "DtsVx_Movie"},
                                {"DtsVx", "DtsVx_Music"},
                                {"DtsVx", "DtsVx_News"},
                                {"DtsVx", "DtsVx_Sports"},
                                {"DtsVx", "DtsVx_Standard"},
                                {"DtsVx", "DtsVx_User"},
                                {"DtsVx", "DtsVx_Vivid"}
                              };
                break;
        }

        return audio_files;
    }

    public static String getLoggerProjectID()
    {
        VestelLog.debug(TAG, "SmartLogger project id variable = "
                                + g_data_index_smart_logger_project_id);

        return g_data_index_smart_logger_project_id;
    }
}
