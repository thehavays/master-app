package com.vestel.tv.middleware;

import android.content.Context;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class manages USB operations
 */
public class VestelUSBOperations
{
    private static final String TAG = "VestelUSBOperations";

    private static List<VestelUSBOperationResult> m_message_list = new ArrayList<>();

    private static final int file_mode = 0644;    // This file mode is used for octal format
    private static final int folder_mode = 0755;  // This folder mode is used for octal format

    private static final int esnkpekph_size = 272;
    private static final int new_esnkpekph_size = 304;
    private static final int esn_size = 64;
    private static final int kpekph_size = 208;
    private static final int new_kpekph_size = 240;

    private static final String mCiPlusKeyUsbPath = "/spi/ciplus.key";
    private static final String mCiPlusEcpKeyUsbPath = "/spi/ciplusecp.key";
    private static final String mHdcp14KeyUsbPath = "/spi/hdcp_key.bin";
    private static final String mHdcp20KeyUsbPath = "/spi/hdcp2_key.bin";
    private static final String mHashKeyUsbPath = "/spi/hashkey.sign";
    private static final String mWidevineKeyUsbPath = "/spi/KeyBox.bin";
    private static final String mWidevineV9KeyUsbPath = "/spi/KeyBoxV9.bin";
    private static final String mNagraKeyUsbPath = "/spi/NagraKey.bin";
    private static final String mKeyMasterKeyUsbPath = "/spi/attestkey.bin";

    private static final String mPlayReady25KeyPrivUsbPath = "/spi/PR25/zgpriv_protected.dat";
    private static final String mPlayReady25KeyCertUsbPath = "/spi/PR25/bgroupcert.dat";
    private static final String mPlayReady30KeyPrivUsbPath = "/spi/PR30/zgpriv_protected.dat";
    private static final String mPlayReady30KeyCertUsbPath = "/spi/PR30/bgroupcert.dat";
    private static final String mPlayReady40KeyPrivUsbPath = "/spi/PLAYREADYPK/zgpriv_protected.dat";
    private static final String mPlayReady40KeyCertUsbPath = "/spi/PLAYREADYPK/bgroupcert.dat";

    private static final String mMacAddressUsbPath = "/spi/MACADDR";

    private static final String mNetflixEsnKeyUsbPath = "/spi/ESNID";
    private static final String mKpeKphKeyUsbPath = "/spi/KpeKph";
    private static final String mNetflixKpeKphKeyUsbPath = "/spi/ESNIDKpeKph";

    private static final String mMfcFirmwareUsbPath = "/ursa/MultiAP.binc";
    private static final String mUrsaOrbitCfgUsbPath = "/ursa/ursa_orbit_cfg.ini";

    private static final String mBoardUsbDir = "/spi/board/";
    private static final String mBoardExtension = ".ini";

    private static final String mPanelFileUsbDir = "/panel/";
    private static final String mPanelFileExtension = ".ini";

    private static final String mIRConfigUsbDir = "/spi/irconfig/";
    private static final String mIRConfigExtension = ".ini";

    private static final String mBootLogoUsbDir = "/spi/";
    private static final String[] mBootLogoExtensions = {".jpg", ".jpeg"};
    private static final int MAX_BOOT_LOGO_FILE_SIZE = 100;
    private static final String FILE_EXTENSION_BIN = ".bin";

    private static final String mBootMusicUsbDir = "/spi/";
    private static final String mBootMusicExtension = ".mp3";
    private static final int MAX_BOOT_MUSIC_FILE_SIZE = 64;

    private static final String mCustomizationUsbPath = "/spi/CustomizationFunc.ini";

    private static final String mMBootEnvDBName = "db_table";

    private static final String mHdmiEdid14UsbPath = "/spi/edid_hdmi_14_";
    private static final String mHdmiEdid20UsbPath = "/spi/edid_hdmi_20_";
    private static final String mHdmiEdid21UsbPath = "/spi/edid_hdmi_21_";
    private static final String mHdmiEdidExtension =".bin";
    private static final String mHDMIEdidSection = "HDMI_EDID_";
    private static final String mHDMIEdidKey = ":HDMI_EDID_File";
    private static final String mHDMI14EdidKey = ":HDMI_EDID_File_1_4";
    private static final String mHDMI20EdidKey = ":HDMI_EDID_File_2_0";
    private static final String mHDMI21EdidKey = ":HDMI_EDID_File_2_1";

    private static final String mVgaEdidUsbPath = "/spi/edid_vga.bin";

    private static final String mTconFileUsbPath = "/panel/TCON20.bin";
    private static final String mTconGammaUsbPath = "/panel/panel_gamma.bin";
    private static final String mTconIniUsbPath = "/panel/tcon.ini";
    private static final String mTconIniSystemPath = "/vendor/tvconfig/config/tcon/tcon.ini";
    private static final String mTconFileSystemPath = "/vendor/tvconfig/config/tcon/TCON20.bin";
    private static final String mTconGammaSystemPath = "/vendor/tvconfig/config/gamma/panel_gamma.bin";

    private static String mPqFilesUsbDir = "/pq/";
    private static String[] mPqFileNames = {};

    private static String mPqGeneralFilesUsbDir = "/PQ_general/";
    private static String[] mPqGeneralFileNames = {};

    private static String mGammaTableFileSystemDir = "/vendor/tvconfig/config/gamma";
    private static String[] mGammaTableFileNames = {};

    private static String mOSDRangeFileSystemDir = "/vendor/tvconfig/config/OSDRange";
    private static String[] mOsdMappingFileNames = {};

    private static String mColorFileSystemDir = "/vendor/tvconfig/config/HSY";
    private static String[] mColorFileNames = {};

    private static String mConfigDefaultValueFileUsbDir = "/pq/";
    private static String mConfigDefaultValueFileName = "ConfigDefaultValue.ini";

    private static String mHdrBinFilesUsbDir = "/HDR_BIN/";
    private static String[] mHdrBinFileNames = {};
    private static String[] mPanelHdrBin1611FileNames = {};

    private static String mAudioConfigUsbDir = "/spi/";
    private static String[] mAqAmpFileNames = {};

    private static final String mTvWizardCountryUsbDir = "/spi/tv_wizard_country_config.xml";

    private static final String mPanelGammaFileName = "panel_gamma.bin";

    private static String mPanelTconFileName = null;
    private static String mPanelTconBinFileKey = "TCON_BIN:TCON_FILE";
    private static String mPanelTconPmicFileKey = "TCON_BIN:PMIC_FILE";
    private static String mPanelTconPGammaFileKey = "TCON_BIN:Pgamma_FILE";
    private static String mPanelTconLevelShiftFileKey = "TCON_BIN:LEVELSHIFT_FILE";

    private static String mAqFilesUsbDir = "/aq/";

    private static String[] mAqFileNames = {};
    private static String[] mAqInputSrcFileNames = {};
    private static String[] mAqSoundStyleFileNames = {};
    private static String[] mAqDapFileNames = {};
    private static String[] mAqDtsVxFileNames = {};
    private static String file_name_thermal_mapping = null;

    private static final long SLEEP_PRECISION = TimeUnit.MILLISECONDS.toNanos(2);
    private static final String mOslbTempMapFileUsbDir = "/oled/";
    private static int mOslbRetryCount = 0;
    private static byte mOslbFwCmd = 0;
    private static int mOslbFwStatus = 0;
    private static int mOslbAddress = 0;
    private static int mOslbSize = 0;
    private static boolean mOslbIsUpdateFinish = false;

    private static final String mLocalDimmingUsbDir = "/ldm/";

    private static final long OSLB_TCON_DELAY_MAX = 200000000;
    private static final long OSLB_TCON_DELAY_MIN = 10000000;
    private static final int OSLB_NEXT_DATA_READY = 1;
    private static final int OSLB_IDLE= 2;
    private static final int OSLB_BUSY = 3;
    private static final int OSLB_ERROR = 4;
    private static final int OSLB_RETRY = 5;
    private static final int OSLB_RESERVED = 0;
    private static final String OSLB_SET_I2C_HEADER_0 = "0x4E";
    private static final String OSLB_SET_I2C_HEADER_1 = "0x30";
    private static final String OSLB_READ_STATUS = "0x00";
    private static final String OSLB_VALUE_00 = "0x00";
    private static final String OSLB_VALUE_82 = "0x82";
    private static final int DEFAULT_SEND_SIZE = 32;
    private static final int SET_I2C_HEADER_SIZE = 6;
    private static final int OSLB_RETRY_COUNT_MAX = 4;

    private static VestelProjectType m_project_type = VestelSystemProperties.getProjectType();

    private static void initFileNames()
    {
        switch (m_project_type)
        {
            case COSMOS:
            case COMET:
                {
                    mPanelTconFileName = "tcon.ini";

                    mPqFileNames = new String[]
                    {
                        "Bandwidth_RegTable.bin",
                        "HSY.bin",
                        "Main.bin",
                        "Main_Ex.bin",
                        "Main_Ex_Text.bin",
                        "Main_Text.bin",
                        "Main_TMO.bin",
                        "Main_TMO_Text.bin",
                        "Sub.bin",
                        "Sub_Ex.bin",
                        "Sub_Text.bin",
                        "TMO.bin",
                        "TMO_Text.bin",
                        "UFSC.bin",
                        "UFSC_Text.bin"
                    };
                    mPqGeneralFileNames = new String[]
                    {
                        "Backlight.ini",
                        "hdr.ini",
                        "NLA.ini",
                        "PQConfig.ini",
                        "WhiteBalanceCorrection.ini",
                        "OsdMapping.ini"
                    };
                    mGammaTableFileNames = new String[]
                    {
                        "gammatable_1.ini"
                    };
                    mOsdMappingFileNames = new String[]
                    {
                        "OSDRange.ini"
                    };
                    mColorFileNames = new String[]
                    {
                        "Color.ini"
                    };
                    mHdrBinFileNames = new String[]
                    {
                        "dolby_brightness.bin",
                        "dolby_dark.bin",
                        "dolby_vivid.bin"
                    };
                    mAqAmpFileNames = new String[]
                    {
                        "audio_amp_config.ini"
                    };
                    mAqFileNames = new String[]
                    {
                        "basic_default.ini",
                        "basic_dtv.ini",
                        "dbx.ini",
                        "Dbx_Default.ini",
                        "Dbx_Standard.ini",
                        "dts_srs.ini",
                        "dts_studio_sound_ii.ini",
                        "dts_virtual_x.ini",
                        "DtsSs2_Default.ini",
                        "DtsSs2_Movie.ini",
                        "DtsSs2_Music.ini",
                        "DtsSs2_News.ini",
                        "DtsSs2_Sports.ini",
                        "DtsSs2_Standard.ini",
                        "DtsSs2_User.ini",
                        "DtsSs2_Vivid.ini",
                        "sonic_emotion.ini",
                        "SonicEmotion_Default.ini",
                        "SonicEmotion_Movie.ini",
                        "SonicEmotion_Music.ini",
                        "SonicEmotion_News.ini",
                        "SonicEmotion_Sports.ini",
                        "SonicEmotion_Standard.ini",
                        "SonicEmotion_User.ini",
                        "SonicEmotion_Vivid.ini"
                    };
                    mAqInputSrcFileNames = new String[]
                    {
                        "BasicSound_ATV.ini",
                        "BasicSound_CVBS.ini",
                        "BasicSound_Default.ini",
                        "BasicSound_DTV.ini",
                        "BasicSound_HDMI.ini",
                        "BasicSound_MM.ini"
                    };
                    mAqSoundStyleFileNames = new String[]
                    {
                        "BasicSound_Movie.ini",
                        "BasicSound_Music.ini",
                        "BasicSound_News.ini",
                        "BasicSound_Sports.ini",
                        "BasicSound_Standard.ini",
                        "BasicSound_User.ini",
                        "BasicSound_Vivid.ini"
                    };
                    mAqDapFileNames = new String[]
                    {
                        "Dap_Default.ini",
                        "Dap_Game.ini",
                        "Dap_Movie.ini",
                        "Dap_Music.ini",
                        "Dap_News.ini",
                        "Dap_Stadium.ini",
                        "Dap_Standard.ini",
                        "Dap_User.ini",
                    };
                    mAqDtsVxFileNames = new String[]
                    {
                        "DtsVx_Default.ini",
                        "DtsVx_Movie.ini",
                        "DtsVx_Music.ini",
                        "DtsVx_News.ini",
                        "DtsVx_Sports.ini",
                        "DtsVx_Standard.ini",
                        "DtsVx_User.ini",
                        "DtsVx_Vivid.ini"
                    };
                }
                break;

            case COMA:
            case NEEDLE:
                {
                    mPanelTconFileName = "tcon_cfg.ini";

                    mPqFileNames = new String[]
                    {
                        "HSY.bin",
                        "Main.bin",
                        "Main_Text.bin",
                        "Main_TMO.bin",
                        "Main_TMO_Text.bin"
                    };
                    mPqGeneralFileNames = new String[]
                    {
                        "Backlight.ini",
                        "ColorRemapping.ini",
                        "ColorRemappingRange.ini",
                        "gammatable_1.ini",
                        "NLA.ini"
                    };
                    mHdrBinFileNames = new String[]
                    {
                        "dolby.bin"
                    };
                    mPanelHdrBin1611FileNames = new String[]
                    {
                        "dolby.bin"
                    };
                    mAqAmpFileNames = new String[]
                    {
                        "audio_amp_config.ini",
                        "audio_amp_device_0.ini",
                        "audio_amp_device_1.ini"
                    };
                    mAqInputSrcFileNames = new String[]
                    {
                        "BasicSound_ATV.ini",
                        "BasicSound_DTV.ini",
                        "BasicSound_HDMI.ini",
                        "BasicSound_MM.ini",
                        "BasicSound_CVBS.ini"
                    };
                    mAqSoundStyleFileNames = new String[]
                    {
                        "BasicSound_Movie.ini",
                        "BasicSound_Music.ini",
                        "BasicSound_News.ini",
                        "BasicSound_Sports.ini",
                        "BasicSound_Standard.ini",
                        "BasicSound_User.ini",
                        "BasicSound_Vivid.ini",
                        "BasicSound_DefaultStyle_DTS.ini"
                    };
                    mAqDapFileNames = new String[]
                    {
                        "Dap_Game.ini",
                        "Dap_Movie.ini",
                        "Dap_Music.ini",
                        "Dap_News.ini",
                        "Dap_Stadium.ini",
                        "Dap_Standard.ini",
                        "Dap_User.ini",
                        "dolby_dap_tuning_config.dat"
                    };
                    mAqDtsVxFileNames = new String[]
                    {
                        "DtsVx_Movie.ini",
                        "DtsVx_Music.ini",
                        "DtsVx_News.ini",
                        "DtsVx_Sports.ini",
                        "DtsVx_Standard.ini",
                        "DtsVx_User.ini",
                        "DtsVx_Vivid.ini"
                    };
                }
                break;

            default:
                {
                    mPanelTconFileName = "tcon_cfg.ini";

                    mPqFileNames = new String[]
                    {
                        "HSY.bin",
                        "Main.bin",
                        "Main_Text.bin",
                        "Main_TMO.bin",
                        "Main_TMO_Text.bin"
                    };
                    mPqGeneralFileNames = new String[]
                    {
                        "Backlight.ini",
                        "ColorRemapping.ini",
                        "ColorRemappingRange.ini",
                        "gammatable_1.ini",
                        "NLA.ini"
                    };
                    mHdrBinFileNames = new String[]
                    {
                        "dolby.bin"
                    };
                    mPanelHdrBin1611FileNames = new String[]
                    {
                        "dolby.bin"
                    };
                    mAqAmpFileNames = new String[]
                    {
                        "audio_amp_config.ini",
                        "audio_amp_device_0.ini",
                        "audio_amp_device_1.ini"
                    };
                    mAqInputSrcFileNames = new String[]
                    {
                        "BasicSound_ATV.ini",
                        "BasicSound_DTV.ini",
                        "BasicSound_HDMI.ini",
                        "BasicSound_MM.ini"
                    };
                    mAqSoundStyleFileNames = new String[]
                    {
                        "BasicSound_Movie.ini",
                        "BasicSound_Music.ini",
                        "BasicSound_News.ini",
                        "BasicSound_Sports.ini",
                        "BasicSound_Standard.ini",
                        "BasicSound_User.ini",
                        "BasicSound_Vivid.ini",
                        "BasicSound_DefaultStyle_DTS.ini"
                    };
                    mAqDapFileNames = new String[]
                    {
                        "Dap_Game.ini",
                        "Dap_Movie.ini",
                        "Dap_Music.ini",
                        "Dap_News.ini",
                        "Dap_Stadium.ini",
                        "Dap_Standard.ini",
                        "Dap_User.ini",
                        "Dap_SpeakerConfig200.dat",
                        "dolby_dap_tuning_config.dat"
                    };
                    mAqDtsVxFileNames = new String[]
                    {
                        "DtsVx_Movie.ini",
                        "DtsVx_Music.ini",
                        "DtsVx_News.ini",
                        "DtsVx_Sports.ini",
                        "DtsVx_Standard.ini",
                        "DtsVx_User.ini",
                        "DtsVx_Vivid.ini"
                    };
                }
                break;
        }
    }

    private static void addMessage(VestelUSBOperationResult code)
    {
        m_message_list.add(code);
    }

    public static List<VestelUSBOperationResult> getMessages()
    {
        return m_message_list;
    }

    public static void updateFiles(Context context, String root_path)
    {
        m_message_list.clear();

        VestelConfig.init();
        VestelInputManager.init(context);

        initFileNames();

        if (m_project_type != VestelProjectType.NEEDLE)
        {
            updateCIPlusKey(root_path);
            updateCIPlusECPKey(root_path);
        }
        updateHashKey(root_path);
        updateHDCP14Key(root_path);
        updateHDCP20Key(root_path);
        updatePlayreadyKeys(root_path);
        updateWidevineKey(root_path);
        updateWidevineV9Key(root_path);
        updateNagraKey(root_path);
        updateAttestationKey(root_path);
        updateNetflixKeys(root_path);
        updateFreeviewPlayKey(root_path);
        updateOpAppKey(root_path);

        updateBootLogo(root_path);
        updateBootMusic(root_path);
        updateEdid(root_path);
        updateTconFiles(root_path);
        updatePanelFile(root_path);
        updatePqFiles(root_path);
        updateAqFiles(root_path);
        updateTvWizardCountryXml(root_path);
        updateMacAddr(root_path);
        updateBoard(root_path);
        updateIRConfig(root_path);
        updateCustomization(root_path);

        if (VestelPanelProperties.isPanelOLED())
        {
            updateMFCFirmware(root_path);
            updateUrsaOrbitConfiguration(root_path);
            updateThermalMapping(root_path);
        }

        if (VestelPanelProperties.isPanelMiniLED())
        {
            updateLocalDimmingFile(root_path);
        }

        VestelFileSystem.syncFs();
    }

    public static void backupFiles(Context context, String root_path)
    {
        m_message_list.clear();

        VestelConfig.init();
        VestelInputManager.init(context);

        initFileNames();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();

        root_path = root_path + "/Backup-" + dtf.format(now);
        VestelLog.debug(TAG, "backupFiles() Creating panel folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            if (m_project_type != VestelProjectType.NEEDLE)
            {
                backupCIPlusKey(root_path);
                backupCIPlusECPKey(root_path);
            }
            backupHashKey(root_path);
            backupHDCP14Key(root_path);
            backupHDCP20Key(root_path);
            backupPlayreadyKeys(root_path);
            backupWidevineKey(root_path);
            backupWidevineV9Key(root_path);
            backupNagraKey(root_path);
            backupAttestationKey(root_path);
            backupNetflixKeys(root_path);
            backupFreeviewPlayKey(root_path);
            backupOpAppKey(root_path);

            backupBootLogo(root_path);
            backupBootMusic(root_path);
            backupEdid(root_path);
            backupTconFiles(root_path);
            backupPanelFile(root_path);
            backupPqFiles(root_path);
            backupAqFiles(root_path);
            backupTvWizardCountryXml(root_path);
            backupMacAddr(root_path);
            backupBoard(root_path);
            backupIRConfig(root_path);
            backupCustomization(root_path);

            if (VestelPanelProperties.isPanelOLED())
            {
                backupMFCFirmware(root_path);
                backupUrsaOrbitConfiguration(root_path);
                backupThermalMapping(root_path);
            }

            if (VestelPanelProperties.isPanelMiniLED())
            {
                backupLocalDimmingFile(root_path);
            }

            VestelFileSystem.syncFs();
        }
        else
        {
            VestelLog.debug(TAG, "backupFiles() Create folder failed");
        }
    }

    private static void updateCIPlusKey(String root_path)
    {
        String usb_input_path = root_path + mCiPlusKeyUsbPath;
        VestelLog.debug(TAG, "updateCIPlusKey() CI+ Key Path = " + usb_input_path);

        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            VestelKeyManager.eraseCIKey();
            VestelLog.debug(TAG, "updateCIPlusKey() CI+ Key System Path = " + VestelConfig.g_ci_plus_key);

            if (VestelFileSystem.copyFile(usb_input_path, VestelConfig.g_ci_plus_key, file_mode))
            {
                if (VestelKeyManager.updateCIKeyWithPath(VestelConfig.g_ci_plus_key))
                {
                    VestelLog.debug(TAG, "updateCIPlusKey() CI+ Key Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateCIPlusKey() CI+ Key Update With Path Failed!");
                    addMessage(VestelUSBOperationResult.ci_plus_update_with_path_fail);
                }
            }
            else
            {
                VestelLog.debug(TAG, "updateCIPlusKey() CI+ Key Copy Failed!");
                addMessage(VestelUSBOperationResult.ci_plus_key_copy_fail);
            }
        }
    }

    private static void backupCIPlusKey(String root_path)
    {
        root_path = Paths.get(root_path + mCiPlusKeyUsbPath).getParent().toString();
        VestelLog.debug(TAG, "backupCIPlusKey() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.g_ci_plus_key;
            VestelLog.debug(TAG, "backupCIPlusKey() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupCIPlusKey() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupCIPlusKey() Copy file failed!");
                addMessage(VestelUSBOperationResult.ci_plus_key_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupCIPlusKey() Create folder failed");
            addMessage(VestelUSBOperationResult.ci_plus_key_copy_fail);
        }
    }

    private static void updateCIPlusECPKey(String root_path)
    {
        String usb_input_path = root_path + mCiPlusEcpKeyUsbPath;
        VestelLog.debug(TAG, "updateCIPlusECPKey() CI+ ECP Key Path = " + usb_input_path);

        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            VestelLog.debug(TAG, "updateCIPlusECPKey() CI+ ECP Key System Path = " + VestelConfig.g_ci_plus_ecp_key);

            if (VestelFileSystem.copyFile(usb_input_path, VestelConfig.g_ci_plus_ecp_key, file_mode))
            {
                if (VestelKeyManager.updateCIEcpKeyWithPath(VestelConfig.g_ci_plus_ecp_key))
                {
                    VestelLog.debug(TAG, "updateCIPlusECPKey() CI+ ECP Key Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateCIPlusECPKey() CI+ ECP Key Update With Path Failed!");
                    addMessage(VestelUSBOperationResult.ci_plus_ecp_update_with_path_fail);
                }
            }
            else
            {
                VestelLog.debug(TAG, "updateCIPlusECPKey() CI+ ECP Key Copy Failed!");
                addMessage(VestelUSBOperationResult.ci_plus_ecp_key_copy_fail);
            }
        }
    }

    private static void backupCIPlusECPKey(String root_path)
    {
        root_path = Paths.get(root_path + mCiPlusEcpKeyUsbPath).getParent().toString();
        VestelLog.debug(TAG, "backupCIPlusECPKey() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.g_ci_plus_ecp_key;
            VestelLog.debug(TAG, "backupCIPlusECPKey() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupCIPlusECPKey() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupCIPlusECPKey() Copy file failed!");
                addMessage(VestelUSBOperationResult.ci_plus_ecp_key_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupCIPlusECPKey() Create folder failed");
            addMessage(VestelUSBOperationResult.ci_plus_ecp_key_copy_fail);
        }
    }

    private static void updateHashKey(String root_path)
    {
        String usb_input_path = root_path + mHashKeyUsbPath;
        VestelLog.debug(TAG, "updateHashKey() Hash Key Path = " + usb_input_path);

        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            VestelLog.debug(TAG, "updateHashKey() Hash Key System Path = " + VestelConfig.g_hash_key);

            if (VestelFileSystem.copyFile(usb_input_path, VestelConfig.g_hash_key, file_mode))
            {
                VestelLog.debug(TAG, "updateHashKey() Hash Key Copied");
            }
            else
            {
                VestelLog.debug(TAG, "updateHashKey() Hash Key Copy Failed!");
                addMessage(VestelUSBOperationResult.hash_key_copy_fail);
            }

            boolean check_result = VestelKeyManager.isHashKeyValid();
            VestelLog.debug(TAG, "updateHashKey() Hash Key Check Result = " + check_result);
        }
    }

    private static void backupHashKey(String root_path)
    {
        root_path = Paths.get(root_path + mHashKeyUsbPath).getParent().toString();
        VestelLog.debug(TAG, "backupHashKey() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.g_hash_key;
            VestelLog.debug(TAG, "backupHashKey() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupHashKey() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupHashKey() Copy file failed!");
                addMessage(VestelUSBOperationResult.hash_key_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupHashKey() Create folder failed");
            addMessage(VestelUSBOperationResult.hash_key_copy_fail);
        }
    }

    private static void updateHDCP14Key(String root_path)
    {
        String usb_input_path = root_path + mHdcp14KeyUsbPath;
        VestelLog.debug(TAG, "updateHDCP14Key() HDCP 1.4 Path = " + usb_input_path);

        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            VestelLog.debug(TAG, "updateHDCP14Key() HDCP 1.4 System Path = " + VestelConfig.g_hdcp14_key);

            if (VestelFileSystem.copyFile(usb_input_path, VestelConfig.g_hdcp14_key, file_mode))
            {
                VestelKeyManager.reloadHDCPKeys();
                VestelLog.debug(TAG, "updateHDCP14Key() HDCP 1.4 Key Copied");
            }
            else
            {
                VestelLog.debug(TAG, "updateHDCP14Key() HDCP 1.4 Key Copy Failed!");
                addMessage(VestelUSBOperationResult.hdcp_14_key_copy_fail);
            }

            boolean check_result = VestelKeyManager.isHDCP1xKeyValid();
            VestelLog.debug(TAG, "updateHDCP14Key() HDCP 1.4 Key Check Result = " + check_result);
        }
    }

    private static void backupHDCP14Key(String root_path)
    {
        root_path = Paths.get(root_path + mHdcp14KeyUsbPath).getParent().toString();
        VestelLog.debug(TAG, "backupHDCP14Key() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.g_hdcp14_key;
            VestelLog.debug(TAG, "backupHDCP14Key() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupHDCP14Key() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupHDCP14Key() Copy file failed!");
                addMessage(VestelUSBOperationResult.hdcp_14_key_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupHDCP14Key() Create folder failed");
            addMessage(VestelUSBOperationResult.hdcp_14_key_copy_fail);
        }
    }

    private static void updateHDCP20Key(String root_path)
    {
        String usb_input_path = root_path + mHdcp20KeyUsbPath;
        VestelLog.debug(TAG, "updateHDCP20Key() HDCP 2.0 Key Path = " + usb_input_path);

        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            VestelLog.debug(TAG, "updateHDCP20Key() HDCP 2.0 Key System Path = " + VestelConfig.g_hdcp20_key);

            if (VestelFileSystem.copyFile(usb_input_path, VestelConfig.g_hdcp20_key, file_mode))
            {
                VestelKeyManager.reloadHDCPKeys();
                if (VestelKeyManager.encryptHDCP2xKey())
                {
                    VestelLog.debug(TAG, "updateHDCP20Key() HDCP 2.0 Key Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateHDCP20Key() HDCP 2.0 Key Encryption Failed!");
                    addMessage(VestelUSBOperationResult.hdcp_20_key_encrypt_fail);
                }
            }
            else
            {
                VestelLog.debug(TAG, "updateHDCP20Key() HDCP 2.0 Key Copy Failed!");
                addMessage(VestelUSBOperationResult.hdcp_20_key_copy_fail);
            }

            boolean check_result = VestelKeyManager.isHDCP2xKeyValid();
            VestelLog.debug(TAG, "updateHDCP20Key() HDCP 2.0 Key Check Result = " + check_result);
        }
    }

    private static void backupHDCP20Key(String root_path)
    {
        root_path = Paths.get(root_path + mHdcp20KeyUsbPath).getParent().toString();
        VestelLog.debug(TAG, "backupHDCP20Key() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.g_hdcp20_key;
            VestelLog.debug(TAG, "backupHDCP20Key() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupHDCP20Key() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupHDCP20Key() Copy file failed!");
                addMessage(VestelUSBOperationResult.hdcp_20_key_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupHDCP20Key() Create folder failed");
            addMessage(VestelUSBOperationResult.hdcp_20_key_copy_fail);
        }
    }

    private static void updatePlayreadyKeys(String root_path)
    {
        switch (m_project_type)
        {
            case COSMOS:
            case COMET:
                {
                    ////////////////////////////////PLAYREADY 2.5 KEY UPDATE///////////////////////////////////////////

                    String usb_input_path_1 = root_path + mPlayReady25KeyPrivUsbPath;
                    String usb_input_path_2 = root_path + mPlayReady25KeyCertUsbPath;
                    VestelLog.debug(TAG, "updatePlayreadyKeys() PR 2.5 Key Priv Path = " + usb_input_path_1);
                    VestelLog.debug(TAG, "updatePlayreadyKeys() PR 2.5 Key Cert Path = " + usb_input_path_2);

                    if (VestelFileSystem.isFileExist(usb_input_path_1) && VestelFileSystem.isFileExist(usb_input_path_2))
                    {
                        boolean pr_zgpriv = false;
                        boolean pr_bgroup = false;

                        if (!VestelFileSystem.isFolderExist(VestelConfig.g_playready25_folder))
                        {
                            VestelFileSystem.createFolder(VestelConfig.g_playready25_folder, folder_mode);
                        }

                        if (VestelFileSystem.copyFile(usb_input_path_1, VestelConfig.g_playready25_key_priv, file_mode))
                        {
                            pr_zgpriv = true;
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updatePlayreadyKeys() PR 2.5 Key (zgpriv_protected.dat) Copy Failed!");
                            addMessage(VestelUSBOperationResult.playready_25_key_priv_copy_fail);
                        }

                        if (VestelFileSystem.copyFile(usb_input_path_2, VestelConfig.g_playready25_key_cert, file_mode))
                        {
                            pr_bgroup = true;
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updatePlayreadyKeys() PR 2.5 Key (bgroupcert.dat) Copy Failed!");
                            addMessage(VestelUSBOperationResult.playready_25_key_cert_copy_fail);
                        }

                        if (pr_zgpriv && pr_bgroup)
                        {
                            if (VestelKeyManager.encryptPlayready25Key())
                            {
                                VestelLog.debug(TAG, "updatePlayreadyKeys() PR 2.5 Keys Copied");
                            }
                            else
                            {
                                VestelLog.debug(TAG, "updatePlayreadyKeys() PR 2.5 Keys Encryption Failed!");
                                addMessage(VestelUSBOperationResult.playready_25_key_encrypt_fail);
                            }
                        }
                    }

                    ////////////////////////////////PLAYREADY 3.0 KEY UPDATE///////////////////////////////////////////

                    usb_input_path_1 = root_path + mPlayReady30KeyPrivUsbPath;
                    usb_input_path_2 = root_path + mPlayReady30KeyCertUsbPath;
                    VestelLog.debug(TAG, "updatePlayreadyKeys() PR 3.0 Key Priv Path = " + usb_input_path_1);
                    VestelLog.debug(TAG, "updatePlayreadyKeys() PR 3.0 Key Cert Path = " + usb_input_path_2);

                    if (VestelFileSystem.isFileExist(usb_input_path_1) && VestelFileSystem.isFileExist(usb_input_path_2))
                    {
                        boolean pr_zgpriv = false;
                        boolean pr_bgroup = false;

                        if (!VestelFileSystem.isFolderExist(VestelConfig.g_playready30_folder))
                        {
                            VestelFileSystem.createFolder(VestelConfig.g_playready30_folder, folder_mode);
                        }

                        if (VestelFileSystem.copyFile(usb_input_path_1, VestelConfig.g_playready30_key_priv, file_mode))
                        {
                            pr_zgpriv = true;
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updatePlayreadyKeys() PR 3.0 Key (zgpriv_protected.dat) Copy Failed!");
                            addMessage(VestelUSBOperationResult.playready_30_key_priv_copy_fail);
                        }

                        if (VestelFileSystem.copyFile(usb_input_path_2, VestelConfig.g_playready40_key_cert, file_mode))
                        {
                            pr_bgroup = true;
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updatePlayreadyKeys() PR 3.0 Key (bgroupcert.dat) Copy Failed!");
                            addMessage(VestelUSBOperationResult.playready_30_key_cert_copy_fail);
                        }

                        if (pr_zgpriv && pr_bgroup)
                        {
                            if (VestelKeyManager.encryptPlayready30Key())
                            {
                                VestelLog.debug(TAG, "updatePlayreadyKeys() PR 3.0 Keys Copied");
                            }
                            else
                            {
                                VestelLog.debug(TAG, "updatePlayreadyKeys() PR 3.0 Keys Encryption Failed!");
                                addMessage(VestelUSBOperationResult.playready_30_key_encrypt_fail);
                            }
                        }
                    }
                }
                break;

            default:
                {
                    ////////////////////////////////PLAYREADY 4.0KEY UPDATE///////////////////////////////////////////

                    String usb_input_path_1 = root_path + mPlayReady40KeyPrivUsbPath;
                    String usb_input_path_2 = root_path + mPlayReady40KeyCertUsbPath;
                    VestelLog.debug(TAG, "updatePlayreadyKeys() PR 4.0 Key Priv Path = " + usb_input_path_1);
                    VestelLog.debug(TAG, "updatePlayreadyKeys() PR 4.0 Key Cert Path = " + usb_input_path_2);

                    if (VestelFileSystem.isFileExist(usb_input_path_1) && VestelFileSystem.isFileExist(usb_input_path_2))
                    {
                        boolean pr_zgpriv = false;
                        boolean pr_bgroup = false;

                        if (!VestelFileSystem.isFolderExist(VestelConfig.g_playready40_folder))
                        {
                            VestelFileSystem.createFolder(VestelConfig.g_playready40_folder, folder_mode);
                        }

                        if (VestelFileSystem.copyFile(usb_input_path_1, VestelConfig.g_playready40_key_priv, file_mode))
                        {
                            pr_zgpriv = true;
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updatePlayreadyKeys() PR 40 Key (zgpriv_protected.dat) Copy Failed!");
                            addMessage(VestelUSBOperationResult.playready_40_key_priv_copy_fail);
                        }

                        if (VestelFileSystem.copyFile(usb_input_path_2, VestelConfig.g_playready40_key_cert, file_mode))
                        {
                            pr_bgroup = true;
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updatePlayreadyKeys() PR 4.0 Key (bgroupcert.dat) Copy Failed!");
                            addMessage(VestelUSBOperationResult.playready_40_key_cert_copy_fail);
                        }

                        if (pr_zgpriv && pr_bgroup)
                        {
                            if (VestelKeyManager.encryptPlayready40Key())
                            {
                                VestelLog.debug(TAG, "updatePlayreadyKeys() PR 4.0 Keys Copied");
                            }
                            else
                            {
                                VestelLog.debug(TAG, "updatePlayreadyKeys() PR 4.0 Keys Encryption Failed!");
                                addMessage(VestelUSBOperationResult.playready_40_key_encrypt_fail);
                            }
                        }

                        boolean check_result = VestelKeyManager.isPlayready40KeyValid();
                        VestelLog.debug(TAG, "updatePlayreadyKeys() PLAYREADY 4.0 Key Check Result = " + check_result);
                    }
                }
                break;
        }
    }

    private static void backupPlayreadyKeys(String root_path)
    {
        VestelLog.debug(TAG, "backupPlayreadyKeys() Not implemented");
    }

    private static void updateWidevineKey(String root_path)
    {
        String usb_input_path = root_path + mWidevineKeyUsbPath;
        VestelLog.debug(TAG, "updateWidevineKey() WV Key Path = " + usb_input_path);
        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            if (!VestelFileSystem.isFolderExist(VestelConfig.getWidevineFolder()))
            {
                VestelFileSystem.createFolder(VestelConfig.getWidevineFolder(), folder_mode);
            }

            boolean wv_cenc = false;

            if (VestelFileSystem.copyFile(usb_input_path, VestelConfig.g_widevine_key, file_mode))
            {
                wv_cenc = true;
            }
            else
            {
                VestelLog.debug(TAG, "updateWidevineKey() WV Key Copy Failed!");
                addMessage(VestelUSBOperationResult.widevine_key_copy_fail);
            }

            if (wv_cenc)
            {
                if (VestelKeyManager.encryptWidevineKey())
                {
                    VestelLog.debug(TAG, "updateWidevineKey() WV Key Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateWidevineKey() WV Key Encryption Failed!");
                    addMessage(VestelUSBOperationResult.widevine_key_encrypt_fail);
                }
            }

            boolean check_result = VestelKeyManager.isWidevineKeyValid();
            VestelLog.debug(TAG,"updateWidevineKey() Widevine Key Check Result = " + check_result);
        }
    }

    private static void backupWidevineKey(String root_path)
    {
        VestelLog.debug(TAG, "backupWidevineKey() Not implemented");
    }

    private static void updateWidevineV9Key(String root_path)
    {
        String usb_input_path = root_path + mWidevineV9KeyUsbPath;
        VestelLog.debug(TAG, "updateWidevineV9Key() WV9 Key Path = " + usb_input_path);
        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            if (!VestelFileSystem.isFolderExist(VestelConfig.getWidevineV9Folder()))
            {
                VestelFileSystem.createFolder(VestelConfig.getWidevineV9Folder(), folder_mode);
            }

            boolean wvv9_cenc = false;

            if (VestelFileSystem.copyFile(usb_input_path, VestelConfig.g_widevinev9_key, file_mode))
            {
                wvv9_cenc = true;
            }
            else
            {
                VestelLog.debug(TAG, "updateWidevineV9Key() WV9 Key Copy Failed!");
                addMessage(VestelUSBOperationResult.widevine_v9_key_copy_fail);
            }

            if (wvv9_cenc)
            {
                if (VestelKeyManager.encryptWidevineV9Key())
                {
                    VestelLog.debug(TAG, "updateWidevineV9Key() WV9 Key Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateWidevineV9Key() WV9 Key Encryption Failed!");
                    addMessage(VestelUSBOperationResult.widevine_v9_key_encrypt_fail);
                }
            }

            boolean check_result = VestelKeyManager.isWidevineV9KeyValid();
            VestelLog.debug(TAG,"updateWidevineV9Key() Widevine9 Key Check Result = " + check_result);
        }
    }

    private static void backupWidevineV9Key(String root_path)
    {
        VestelLog.debug(TAG, "backupWidevineV9Key() Not implemented");
    }

    private static void updateNagraKey(String root_path)
    {
        String usb_input_path = root_path + mNagraKeyUsbPath;
        VestelLog.debug(TAG, "updateNagraKey() WV Key Path = " + usb_input_path);
        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            if (!VestelFileSystem.isFolderExist(VestelConfig.getNagraFolder()))
            {
                VestelFileSystem.createFolder(VestelConfig.getNagraFolder(), folder_mode);
            }

            boolean nagra_cenc = false;

            if (VestelFileSystem.copyFile(usb_input_path, VestelConfig.g_nagra_key, file_mode))
            {
                nagra_cenc = true;
            }
            else
            {
                VestelLog.debug(TAG, "updateNagraKey() Nagra Key Copy Failed!");
                addMessage(VestelUSBOperationResult.nagra_key_copy_fail);
            }

            if (nagra_cenc)
            {
                if (VestelKeyManager.encryptNagraKey())
                {
                    VestelLog.debug(TAG, "updateNagraKey() Nagra Key Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateNagraKey() Nagra Key Encryption Failed!");
                    addMessage(VestelUSBOperationResult.nagra_key_encrypt_fail);
                }
            }

            boolean check_result = VestelKeyManager.isNagraKeyValid();
            VestelLog.debug(TAG,"updateNagraKey() Nagra Key Check Result = " + check_result);
        }
    }

    private static void backupNagraKey(String root_path)
    {
        VestelLog.debug(TAG, "backupNagraKey() Not implemented");
    }

    private static void updateAttestationKey(String root_path)
    {
        String usb_input_path = root_path + mKeyMasterKeyUsbPath;
        VestelLog.debug(TAG, "updateAttestationKey() KeyMaster Key Path = " + usb_input_path);
        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            if (!VestelFileSystem.isFolderExist(VestelConfig.g_keymaster_folder))
            {
                VestelFileSystem.createFolder(VestelConfig.g_keymaster_folder, folder_mode);
            }

            boolean keymaster = false;

            if (VestelFileSystem.copyFile(usb_input_path, VestelConfig.g_keymaster_key, file_mode))
            {
                keymaster = true;
            }
            else {
                VestelLog.debug(TAG, "updateAttestationKey() KeyMastar Key Copy Failed!");
                addMessage(VestelUSBOperationResult.attestation_key_copy_fail);
            }

            if (keymaster)
            {
                boolean encrypt_result = VestelKeyManager.encryptKeymaster();

                if (encrypt_result)
                {
                    VestelLog.debug(TAG, "updateAttestationKey() KeyMaster Key Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateAttestationKey() KeyMaster Key Encryption Failed!");
                    addMessage(VestelUSBOperationResult.attestation_key_encrypt_fail);
                }
            }

            boolean check_result = VestelKeyManager.checkKeymaster();
            VestelLog.debug(TAG,"updateAttestationKey() KeyMaster Key Check Result = " + check_result);
        }
    }

    private static void backupAttestationKey(String root_path)
    {
        VestelLog.debug(TAG, "backupAttestationKey() Not implemented");
    }

    private static void updateNetflixKeys(String root_path)
    {
        String usb_input_path = root_path + mNetflixKpeKphKeyUsbPath;

        String usb_input_path_alt_1 = root_path + mNetflixEsnKeyUsbPath;
        String usb_input_path_alt_2 = root_path + mKpeKphKeyUsbPath;

        if (VestelFileSystem.isFileExist(usb_input_path) ||
           (VestelFileSystem.isFileExist(usb_input_path_alt_1) && VestelFileSystem.isFileExist(usb_input_path_alt_2)))
        {
            if (!VestelFileSystem.isFolderExist(VestelConfig.g_netflix_key_folder))
            {
                VestelFileSystem.createFolder(VestelConfig.g_netflix_key_folder, folder_mode);
            }

            ArrayList<Byte> arrayList;
            boolean ret = false;

            try
            {
                if (VestelFileSystem.isFileExist(usb_input_path))
                {
                    try {
                        File esn_file_obj = new File(usb_input_path);
                        int esn_file_size = (int) esn_file_obj.length();

                        VestelLog.debug(TAG, "updateNetflixKeys() Netflix EsnKpeKph Key File Size = " + esn_file_size);
                        if ((esn_file_size == esnkpekph_size) || (esn_file_size == new_esnkpekph_size)) {
                            ret = VestelFileSystem.copyFile(usb_input_path, VestelConfig.g_netflix_esnid_kpekph, file_mode);
                            VestelLog.debug(TAG, "updateNetflixKeys() ret = " + ret);

                            arrayList = VestelFileSystem.readFileByte(VestelConfig.g_netflix_esnid_kpekph, esn_size, 0);
                            VestelFileSystem.createFile(VestelConfig.g_netflix_esnid);
                            VestelFileSystem.writeFileByte(VestelConfig.g_netflix_esnid, arrayList, "w");
                            VestelFileSystem.changeFileMode(VestelConfig.g_netflix_esnid, file_mode);

                            int remaining_size = esn_file_size - esn_size;
                            arrayList.clear();
                            arrayList = VestelFileSystem.readFileByte(VestelConfig.g_netflix_esnid_kpekph, remaining_size, esn_size);
                            VestelFileSystem.createFile(VestelConfig.g_netflix_kpekph);
                            VestelFileSystem.writeFileByte(VestelConfig.g_netflix_kpekph, arrayList, "w");
                            VestelFileSystem.changeFileMode(VestelConfig.g_netflix_kpekph, file_mode);
                        }
                    } catch (Exception e) {
                        VestelLog.debug(TAG, "updateNetflixKeys() Get Netflix ESN Key File Size Error!");
                        ret = false;
                    }
                }
                else
                {
                    ret = VestelFileSystem.copyFile(usb_input_path_alt_1, VestelConfig.g_netflix_esnid, file_mode)
                       && VestelFileSystem.copyFile(usb_input_path_alt_2, VestelConfig.g_netflix_kpekph, file_mode);
                }

                if (!ret)
                {
                    VestelLog.debug(TAG, "updateNetflixKeys() Netflix ESN Key Copy Failed!");
                    addMessage(VestelUSBOperationResult.netflix_esn_key_copy_fail);
                }
                else
                {
                    VestelKeyManager.encryptNetflixKey();

                    if (VestelFileSystem.isFileExist(VestelConfig.g_netflix_esnid) &&
                        VestelFileSystem.isFileExist(VestelConfig.g_netflix_kpekph))
                    {
                        VestelLog.debug(TAG, "updateNetflixKeys() Netflix ESN Key Copied!");
                    }
                    else
                    {
                        VestelLog.debug(TAG, "updateNetflixKeys() Netflix ESN Key Copy Failed!");
                        addMessage(VestelUSBOperationResult.netflix_esn_key_copy_fail);
                    }
                }

                VestelFileSystem.removeFile(VestelConfig.g_netflix_esnid_kpekph);

                boolean check_result = VestelKeyManager.isNetflixKeyValid();
                VestelLog.debug(TAG,"updateNetflixKeys() Netflix ESN Key Check Result = " + check_result);
            }
            catch (Exception e)
            {
                VestelLog.debug(TAG, "updateNetflixKeys() Netflix ESN Key Copy Failed!");
                addMessage(VestelUSBOperationResult.netflix_esn_key_copy_fail);
            }
        }
    }

    private static void backupNetflixKeys(String root_path)
    {
        VestelLog.debug(TAG, "backupNetflixKeys() Not implemented");
    }

    private static void updateFreeviewPlayKey(String root_path)
    {
        switch (m_project_type)
        {
            case COSMOS:
            case COMET:
                {
                    String usb_input_path_1 = root_path + "/spi/" + VestelConfig.getFVPOEMFileName();
                    String usb_input_path_2 = root_path + "/spi/" + VestelConfig.getFVPVestelFileName();
                    String usb_input_path_3 = root_path + "/spi/" + VestelConfig.getFVPVestelIntermediateFileName();

                    if (VestelFileSystem.isFileExist(usb_input_path_1) &&
                        VestelFileSystem.isFileExist(usb_input_path_2) &&
                        VestelFileSystem.isFileExist(usb_input_path_3))
                    {
                        String fvp_folder = VestelConfig.getFVPFolder();
                        if (!VestelFileSystem.isFolderExist(fvp_folder))
                        {
                            VestelFileSystem.createFolder(fvp_folder, folder_mode);
                        }

                        try
                        {
                            boolean ret1 = VestelFileSystem.copyFile(usb_input_path_1, VestelConfig.getFVPOEMFile(), file_mode);
                            boolean ret2 = VestelFileSystem.copyFile(usb_input_path_2, VestelConfig.getFVPVestelFile(), file_mode);
                            boolean ret3 = VestelFileSystem.copyFile(usb_input_path_3, VestelConfig.getFVPVestelIntermediateFile(), file_mode);

                            if (ret3)
                            {
                                ret3 = VestelKeyManager.encryptFVPIntermediateKey();
                            }

                            if (ret1 && ret2 && ret3)
                            {
                                VestelLog.debug(TAG, "updateFreeviewPlayKey() FVP Key Copied");
                            }
                            else
                            {
                                VestelLog.debug(TAG, "updateFreeviewPlayKey() FVP Key Copy Failed!");
                                addMessage(VestelUSBOperationResult.freeviewplay_key_copy_fail);
                            }
                        }
                        catch (Exception e)
                        {
                            VestelLog.debug(TAG, "updateFreeviewPlayKey() FVP Key Copy Failed!");
                            addMessage(VestelUSBOperationResult.freeviewplay_key_copy_fail);
                        }
                    }
                }
                break;

            default:
                {
                    String usb_input_path = root_path + "/spi/" + VestelConfig.getFVPVestelIntermediateFileName();
                    if (VestelFileSystem.isFileExist(usb_input_path))
                    {
                        String fvp_folder = VestelConfig.getFVPFolder();
                        if (!VestelFileSystem.isFolderExist(fvp_folder))
                        {
                            VestelFileSystem.createFolder(fvp_folder, folder_mode);
                        }

                        try
                        {
                            String file = VestelConfig.getFVPVestelIntermediateFile();

                            if (VestelFileSystem.copyFile(usb_input_path, file, file_mode))
                            {
                                if (!VestelKeyManager.encryptFVPKey())
                                {
                                    VestelLog.debug(TAG, "updateFreeviewPlayKey() FVP Key Copy Failed!");
                                    addMessage(VestelUSBOperationResult.freeviewplay_key_copy_fail);
                                }
                                else
                                {
                                    VestelLog.debug(TAG, "updateFreeviewPlayKey() FVP Key Copied");
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            VestelLog.debug(TAG, "updateFreeviewPlayKey() FVP Key Copy Failed!");
                            addMessage(VestelUSBOperationResult.freeviewplay_key_copy_fail);
                        }
                    }
                }
                break;
        }
    }

    private static void backupFreeviewPlayKey(String root_path)
    {
        VestelLog.debug(TAG, "backupFreeviewPlayKey() Not implemented");
    }

    private static void updateOpAppKey(String root_path)
    {
        if (m_project_type == VestelProjectType.HOCKEY && VestelFileSystem.isFolderExist(root_path + "/opapp_key/"))
        {
            String oppAppDir = VestelConfig.getOppAppFolder();
            if (!VestelFileSystem.isFolderExist(oppAppDir))
            {
                VestelFileSystem.createFolder(oppAppDir);
            }

            boolean success = VestelKeyManager.encryptOpAppKey(root_path);
            VestelLog.debug(TAG, "updateOpAppKey() Opapp encryptOpappKey ret = " + success);

            if (success) {
                VestelLog.debug(TAG, "updateOpAppKey() Opapp Key Copied");
            } else {
                VestelLog.debug(TAG, "updateOpAppKey() Opapp Key Failed!");
            }
        }
    }

    private static void backupOpAppKey(String root_path)
    {
        VestelLog.debug(TAG, "backupOpAppKey() Not implemented");
    }

    private static void updateBootLogo(String root_path)
    {
        root_path = root_path + mBootLogoUsbDir;
        VestelLog.debug(TAG, "updateBootLogo() Checking for boot logo file at = " + root_path);

        File folder = new File(root_path);
        File[] list_of_files = folder.listFiles();

        if (list_of_files == null)
        {
            VestelLog.debug(TAG, "updateBootLogo() Directory not found at = " + root_path);
            return;
        }

        String boot_logo_file_name = "";
        for (int i = 0; i < list_of_files.length; i++)
        {
            String file_name = list_of_files[i].getName();
            if (file_name.endsWith(mBootLogoExtensions[0]) ||
                file_name.endsWith(mBootLogoExtensions[1]))
            {
                boot_logo_file_name = file_name;
                break;
            }
        }

        if (boot_logo_file_name.isEmpty())
        {
            VestelLog.debug(TAG, "updateBootLogo() No boot logo file found");
            return;
        }

        String usb_input_path = root_path + boot_logo_file_name;
        VestelLog.debug(TAG, "updateBootLogo() Boot logo file usb path = " + usb_input_path);

        File file = new File(usb_input_path);
        int file_size = Integer.parseInt(String.valueOf(file.length()/1024));
        VestelLog.debug(TAG, "updateBootLogo() Boot logo size = " + String.valueOf(file_size));

        if (file_size > MAX_BOOT_LOGO_FILE_SIZE)
        {
            addMessage(VestelUSBOperationResult.boot_logo_too_large);
            return;
        }

        String system_path = VestelConfig.getBootLogoFile();
        VestelLog.debug(TAG, "updateBootLogo() Boot logo file system path = " + system_path);

        if (!VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
        {
            VestelLog.debug(TAG, "updateBootLogo() Copy boot logo file failed!");
            addMessage(VestelUSBOperationResult.boot_logo_copy_fail);
            return;
        }

        if (!VestelSystemProperties.setEmmcEnvironmentVariable(mMBootEnvDBName, "0"))
        {
            VestelLog.debug(TAG, "updateBootLogo() Update MBoot DB Failed!");
            addMessage(VestelUSBOperationResult.boot_logo_mboot_env_db_update_fail);
            return;
        }

        String config_file_names = VestelConfig.getConfigFileNamesFile();
        if (!VestelFileSystem.isFileExist(config_file_names))
        {
            VestelFileSystem.createFile(config_file_names, file_mode);
        }

        if (!VestelFileSystem.setIniParameter(config_file_names, "LOGO_CFG:LOGO_NAME", boot_logo_file_name))
        {
            VestelLog.debug(TAG, "updateBootLogo() Update boot logo file name failed!");
            addMessage(VestelUSBOperationResult.boot_logo_name_update_fail);
            return;
        }

        VestelLog.debug(TAG, "updateBootLogo() Boot logo file updated successfully");
    }

    private static void backupBootLogo(String root_path)
    {
        root_path = root_path + mBootLogoUsbDir;
        VestelLog.debug(TAG, "backupBootLogo() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.getBootLogoFile();
            VestelLog.debug(TAG, "backupBootLogo() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupBootLogo() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupBootLogo() Copy file failed!");
                addMessage(VestelUSBOperationResult.boot_logo_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupBootLogo() Create folder failed");
            addMessage(VestelUSBOperationResult.boot_logo_copy_fail);
        }
    }

    private static void updateBootMusic(String root_path)
    {
        root_path = root_path + mBootMusicUsbDir;
        VestelLog.debug(TAG, "updateBootMusic() Checking for boot music file at = " + root_path);

        File folder = new File(root_path);
        File[] list_of_files = folder.listFiles();

        if (list_of_files == null)
        {
            VestelLog.debug(TAG, "updateBootMusic() Directory not found at = " + root_path);
            return;
        }

        String boot_music_file_name = "";
        for (int i = 0; i < list_of_files.length; i++)
        {
            String file_name = list_of_files[i].getName();
            if (file_name.endsWith(mBootMusicExtension))
            {
                boot_music_file_name = file_name;
                break;
            }
        }

        if (boot_music_file_name.isEmpty())
        {
            VestelLog.debug(TAG, "updateBootMusic() No boot music file found");
            return;
        }

        String usb_input_path = root_path + boot_music_file_name;
        VestelLog.debug(TAG, "updateBootMusic() Boot music file usb path = " + usb_input_path);

        File file = new File(usb_input_path);
        int file_size = Integer.parseInt(String.valueOf(file.length()/1024));
        VestelLog.debug(TAG, "updateBootMusic() Boot music size = " + String.valueOf(file_size));

        if (file_size > MAX_BOOT_MUSIC_FILE_SIZE)
        {
            addMessage(VestelUSBOperationResult.boot_music_too_large);
            return;
        }

        String system_path = VestelConfig.getBootMusicFile();
        if (!VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
        {
            VestelLog.debug(TAG, "updateBootMusic() Copy boot music file failed!");
            addMessage(VestelUSBOperationResult.boot_music_copy_fail);
            return;
        }

        boolean success;

        switch (m_project_type)
        {
            case COSMOS:
            case COMET:
                {
                    success = VestelSystemProperties.setEmmcEnvironmentVariable(mMBootEnvDBName, "0")
                           && VestelSystemProperties.setEmmcEnvironmentVariable("music_vol", "0x23");
                }
                break;

            default:
                {
                    success = VestelSystemProperties.setEmmcEnvironmentVariable(mMBootEnvDBName, "0");
                }
                break;
        }

        if (!success)
        {
            VestelLog.debug(TAG, "updateBootMusic() Update MBoot DB failed!");
            addMessage(VestelUSBOperationResult.boot_music_mboot_env_db_update_fail);
            return;
        }

        String config_file_names = VestelConfig.getConfigFileNamesFile();
        if (!VestelFileSystem.isFileExist(config_file_names))
        {
            VestelFileSystem.createFile(config_file_names, file_mode);
        }

        if (!VestelFileSystem.setIniParameter(config_file_names, "MUSIC_CFG:MUSIC_NAME", boot_music_file_name))
        {
            VestelLog.debug(TAG, "updateBootMusic() Update boot music file name failed!");
            addMessage(VestelUSBOperationResult.boot_music_name_update_fail);
            return;
        }
        VestelLog.debug(TAG, "updateBootMusic() Boot music file updated successfully");
    }

    private static void backupBootMusic(String root_path)
    {
        root_path = root_path + mBootMusicUsbDir;
        VestelLog.debug(TAG, "backupBootMusic() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.getBootMusicFile();
            VestelLog.debug(TAG, "backupBootMusic() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupBootMusic() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupBootMusic() Copy file failed!");
                addMessage(VestelUSBOperationResult.boot_music_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupBootMusic() Create folder failed");
            addMessage(VestelUSBOperationResult.boot_music_copy_fail);
        }
    }

    private static void updateEdid(String root_path)
    {
        String edid_system_dir = VestelConfig.getHDMIEdidBinFolder();
        String edid_cfg_ini_file = VestelConfig.getEdidConfigFile();

        VestelBoardType board_type = VestelSystemProperties.getBoardType();

        String edid_14_value;
        String edid_20_value;
        String edid_21_value;

        String destination_14;
        String destination_20;
        String destination_21;

        String usb_input_path;

        String mHdmiEdid14Prefix;
        String mHdmiEdid20Prefix;
        String mHdmiEdid21Prefix;

        int[] hdmi_order = new int[] {1, 2, 3, 4};

        if (m_project_type == VestelProjectType.COMA || m_project_type == VestelProjectType.NEEDLE)
        {
            hdmi_order[0] = 1;
            hdmi_order[1] = 3;
            hdmi_order[2] = 2;
            hdmi_order[3] = 4;
        }
        else if (m_project_type == VestelProjectType.MEDUSA)
        {
            if (board_type == VestelBoardType.MB185Y)
            {
                hdmi_order[0] = 2;
                hdmi_order[1] = 3;
                hdmi_order[2] = 1;
                hdmi_order[3] = 4;
            } else
            {
                hdmi_order[0] = 1;
                hdmi_order[1] = 3;
                hdmi_order[2] = 2;
                hdmi_order[3] = 4;
            }
        }
        else
        {
            hdmi_order[0] = 1;
            hdmi_order[1] = 2;
            hdmi_order[2] = 3;
            hdmi_order[3] = 4;
        }

        for (int i = 1; i < 5; i++)
        {
            if (m_project_type == VestelProjectType.COSMOS ||
                m_project_type == VestelProjectType.COMET)
            {
                int IC = 1;

                /////physical address match
                if (board_type == VestelBoardType.MB170)
                {
                    mHdmiEdid14Prefix = "17MB170_edid_hdmi_14_";
                    mHdmiEdid20Prefix = "17MB170_edid_hdmi_20_";
                    mHdmiEdid21Prefix = "17MB170_edid_hdmi_21_";

                    if (i == 2)
                    {
                        IC = 4;
                    }
                    else if (i == 3)
                    {
                        IC = 2;
                    }
                    else if (i == 4)
                    {
                        IC = 3;
                    }
                }
                else if (board_type == VestelBoardType.MB171)
                {
                    mHdmiEdid14Prefix = "17MB171_edid_hdmi_14_";
                    mHdmiEdid20Prefix = "17MB171_edid_hdmi_20_";
                    mHdmiEdid21Prefix = "17MB171_edid_hdmi_21_";

                    if (i == 2)
                    {
                        IC = 3;
                    }
                    else if (i == 3)
                    {
                        IC = 2;
                    }
                    else if (i == 4)
                    {
                        IC = 4;
                    }
                }
                else if (board_type == VestelBoardType.MB170_E)
                {
                    mHdmiEdid14Prefix = "17MB173_edid_hdmi_14_";
                    mHdmiEdid20Prefix = "17MB173_edid_hdmi_20_";
                    mHdmiEdid21Prefix = "17MB173_edid_hdmi_21_";

                    if (i == 2)
                    {
                        IC = 2;
                    }
                    else if (i == 3)
                    {
                        IC = 4;
                    }
                    else if (i == 4)
                    {
                        IC = 3;
                    }
                }
                else
                {
                    mHdmiEdid14Prefix = "17MB170_edid_hdmi_14_";
                    mHdmiEdid20Prefix = "17MB170_edid_hdmi_20_";
                    mHdmiEdid21Prefix = "17MB170_edid_hdmi_21_";
                }

                edid_14_value = "\"" + edid_system_dir + mHdmiEdid14Prefix + IC + mHdmiEdidExtension + "\"";
                edid_20_value = "\"" + edid_system_dir + mHdmiEdid20Prefix + IC + mHdmiEdidExtension + "\"";
                edid_21_value = "\"" + edid_system_dir + mHdmiEdid21Prefix + IC + mHdmiEdidExtension + "\"";

                destination_14 = edid_system_dir + mHdmiEdid14Prefix + i + mHdmiEdidExtension;
                destination_20 = edid_system_dir + mHdmiEdid20Prefix + i + mHdmiEdidExtension;
                destination_21 = edid_system_dir + mHdmiEdid21Prefix + i + mHdmiEdidExtension;
            }
            else
            {
                mHdmiEdid14Prefix = "edid_hdmi_14_";
                mHdmiEdid20Prefix = "edid_hdmi_20_";
                mHdmiEdid21Prefix = "edid_hdmi_21_";

                edid_14_value = mHdmiEdid14Prefix + i + mHdmiEdidExtension;
                edid_20_value = mHdmiEdid20Prefix + i + mHdmiEdidExtension;
                edid_21_value = mHdmiEdid21Prefix + i + mHdmiEdidExtension;

                destination_14 = edid_system_dir + edid_14_value;
                destination_20 = edid_system_dir + edid_20_value;
                destination_21 = edid_system_dir + edid_21_value;
            }

            ////////////////////////////////HDMI 1.4 EDID UPDATE///////////////////////////////////////////
            usb_input_path = root_path + mHdmiEdid14UsbPath + i + mHdmiEdidExtension;
            VestelLog.debug(TAG, "updateEdid() HDMI 1.4 Edid Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                VestelLog.debug(TAG, "updateEdid() HDMI 1.4 Edid System Path = " + destination_14);

                if (VestelFileSystem.copyFile(usb_input_path, destination_14, file_mode))
                {
                    VestelLog.debug(TAG, "updateEdid() HDMI 1.4 Edid Copied");

                    String edid_key = mHDMIEdidSection + hdmi_order[i - 1] + mHDMI14EdidKey;
                    boolean ret = VestelFileSystem.setIniParameter(edid_cfg_ini_file, edid_key, edid_14_value);
                    VestelLog.debug(TAG, "updateEdid() edid_key = " + edid_key + " ret = " + ret);
                }
                else
                {
                    VestelLog.debug(TAG, "updateEdid() HDMI 1.4 Edid Copy Failed!");
                    addMessage(VestelUSBOperationResult.hdmi_14_edid_copy_fail);
                }
            }

            if ((m_project_type == VestelProjectType.HOCKEY) || (VestelPanelProperties.isPanelVRR()))
            {
                ////////////////////////////////HDMI 2.0 EDID UPDATE///////////////////////////////////////////
                usb_input_path = root_path + mHdmiEdid20UsbPath + i + mHdmiEdidExtension;
                VestelLog.debug(TAG, "updateEdid() HDMI 2.0 Edid Path = " + usb_input_path);

                if (VestelFileSystem.isFileExist(usb_input_path))
                {
                    VestelLog.debug(TAG, "updateEdid() HDMI 2.0 Edid System Path = " + destination_20);

                    if (VestelFileSystem.copyFile(usb_input_path, destination_20, file_mode))
                    {
                        VestelLog.debug(TAG, "updateEdid() HDMI 2.0 Edid Copied");

                        String edid_key = mHDMIEdidSection + hdmi_order[i - 1] + mHDMI20EdidKey;
                        boolean ret = VestelFileSystem.setIniParameter(edid_cfg_ini_file, edid_key, edid_20_value);
                        VestelLog.debug(TAG, "updateEdid() edid_key = " + edid_key + " ret = " + ret);
                    }
                    else
                    {
                        VestelLog.debug(TAG, "updateEdid() HDMI 2.0 Edid Copy Failed!");
                        addMessage(VestelUSBOperationResult.hdmi_20_edid_copy_fail);
                    }
                }

                ////////////////////////////////HDMI 2.1 EDID UPDATE///////////////////////////////////////////
                usb_input_path = root_path + mHdmiEdid21UsbPath + i + mHdmiEdidExtension;
                VestelLog.debug(TAG, "updateEdid() HDMI 2.1 Edid Path = " + usb_input_path);

                if (VestelFileSystem.isFileExist(usb_input_path))
                {
                    VestelLog.debug(TAG, "updateEdid() HDMI 2.1 Edid System Path = " + destination_21);

                    if (VestelFileSystem.copyFile(usb_input_path, destination_21, file_mode))
                    {
                        VestelLog.debug(TAG, "updateEdid() HDMI 2.1 Edid Copied");

                        String edid_key = mHDMIEdidSection + hdmi_order[i - 1] + mHDMI21EdidKey;
                        boolean ret = VestelFileSystem.setIniParameter(edid_cfg_ini_file, edid_key, edid_21_value);
                        VestelLog.debug(TAG, "updateEdid() edid_key = " + edid_key + " ret = " + ret);

                        edid_key = mHDMIEdidSection + hdmi_order[i - 1] + mHDMIEdidKey;
                        ret = VestelFileSystem.setIniParameter(edid_cfg_ini_file, edid_key, edid_21_value);
                        VestelLog.debug(TAG, "updateEdid() edid_key = " + edid_key + " ret = " + ret);
                    }
                    else
                    {
                        VestelLog.debug(TAG, "updateEdid() HDMI 2.1 Edid Copy Failed!");
                        addMessage(VestelUSBOperationResult.hdmi_21_edid_copy_fail);
                    }
                }
            }
            else
            {
                ////////////////////////////////HDMI 2.1 EDID UPDATE///////////////////////////////////////////
                usb_input_path = root_path + mHdmiEdid21UsbPath + i + mHdmiEdidExtension;
                VestelLog.debug(TAG, "updateEdid() HDMI 2.1 Edid Path = " + usb_input_path);

                if (VestelFileSystem.isFileExist(usb_input_path))
                {
                    VestelLog.debug(TAG, "updateEdid() HDMI 2.1 Edid System Path = " + destination_21);

                    if (VestelFileSystem.copyFile(usb_input_path, destination_21, file_mode))
                    {
                        String edid_key = mHDMIEdidSection + hdmi_order[i - 1] + mHDMI20EdidKey;
                        boolean ret = VestelFileSystem.setIniParameter(edid_cfg_ini_file, edid_key, edid_21_value);
                        VestelLog.debug(TAG, "updateEdid() edid_key = " + edid_key + " ret = " + ret);

                        if (!(m_project_type == VestelProjectType.COSMOS || m_project_type == VestelProjectType.COMET))
                        {
                            edid_key = mHDMIEdidSection + hdmi_order[i - 1] + mHDMI21EdidKey;
                            ret = VestelFileSystem.setIniParameter(edid_cfg_ini_file, edid_key, edid_21_value);
                            VestelLog.debug(TAG, "updateEdid() edid_key = " + edid_key + " ret = " + ret);

                            edid_key = mHDMIEdidSection + hdmi_order[i - 1] + mHDMIEdidKey;
                            ret = VestelFileSystem.setIniParameter(edid_cfg_ini_file, edid_key, edid_21_value);
                            VestelLog.debug(TAG, "updateEdid() edid_key = " + edid_key + " ret = " + ret);
                        }

                        VestelLog.debug(TAG, "updateEdid() HDMI 2.1 Edid Copied");
                    }
                    else
                    {
                        VestelLog.debug(TAG, "updateEdid() HDMI 2.1 Edid Copy Failed!");
                        addMessage(VestelUSBOperationResult.hdmi_21_edid_copy_fail);
                    }
                }
            }
        }

        if (VestelInputManager.vgaInputAvailable())
        {
            ////////////////////////////////VGA EDID UPDATE///////////////////////////////////////////
            usb_input_path = root_path + mVgaEdidUsbPath ;
            VestelLog.debug(TAG, "updateEdid() VGA Edid Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                String destination =  VestelConfig.getVGAEdidBinFolder() + VestelConfig.g_vga_edid_file_name;
                VestelLog.debug(TAG, "updateEdid() VGA Edid System Path = " + destination);

                if (VestelFileSystem.copyFile(usb_input_path, destination, file_mode))
                {
                    String vga_edid_key = "VGA_EDID:VGA_EDID_File";
                    String vga_edid_value;

                    switch (m_project_type)
                    {
                        case COSMOS:
                        case COMET:
                            vga_edid_value = destination;
                            break;

                        default:
                            vga_edid_value = VestelConfig.g_vga_edid_file_name;
                            break;
                    }

                    VestelLog.debug(TAG, "updateEdid() edid_key = " + vga_edid_key);
                    if (VestelFileSystem.setIniParameter(edid_cfg_ini_file, vga_edid_key, vga_edid_value))
                    {
                        VestelLog.debug(TAG, "updateEdid() VGA Edid Copied");
                    }
                    else
                    {
                        VestelLog.debug(TAG, "updateEdid() Set Ini Parameter Failed!");
                        addMessage(VestelUSBOperationResult.vga_edid_copy_fail);
                    }
                }
                else
                {
                    VestelLog.debug(TAG, "updateEdid() VGA Edid Copy Failed!");
                    addMessage(VestelUSBOperationResult.vga_edid_copy_fail);
                }
            }
        }
    }

    private static void backupEdid(String root_path)
    {
        VestelLog.debug(TAG, "backupEdid() Not implemented");
    }

    private static void updateTconFiles(String root_path)
    {
        switch (m_project_type)
        {
            case COSMOS:
            case COMET:
                {
                    String usb_input_path = root_path + mTconGammaUsbPath;

                    if (VestelFileSystem.isFileExist(usb_input_path))
                    {
                        if (VestelFileSystem.copyFile(usb_input_path, mTconGammaSystemPath, file_mode))
                        {
                            VestelLog.debug(TAG, "updateTconFGamma() Done");
                            if (!VestelSystemProperties.setEmmcEnvironmentVariable(mMBootEnvDBName, "0"))
                            {
                                addMessage(VestelUSBOperationResult.tcon_mboot_env_db_update_fail);
                            }
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updateTconGamma() Failed!");
                            addMessage(VestelUSBOperationResult.tcon_gamma_file_copy_fail);
                        }
                    }

                    usb_input_path = root_path + mTconFileUsbPath;

                    if (VestelFileSystem.isFileExist(usb_input_path))
                    {
                        if (VestelFileSystem.copyFile(usb_input_path, mTconFileSystemPath, file_mode))
                        {
                            VestelLog.debug(TAG, "updateTconFile() Done");
                            if (!VestelSystemProperties.setEmmcEnvironmentVariable(mMBootEnvDBName, "0"))
                            {
                                addMessage(VestelUSBOperationResult.tcon_mboot_env_db_update_fail);
                            }
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updateTconFile() Failed!");
                            addMessage(VestelUSBOperationResult.tcon_binary_file_copy_fail);
                        }
                    }

                    usb_input_path = root_path + mTconIniUsbPath;

                    if (VestelFileSystem.isFileExist(usb_input_path))
                    {
                        if (VestelFileSystem.copyFile(usb_input_path, mTconIniSystemPath, file_mode))
                        {
                            VestelLog.debug(TAG, "updateTconIni() Done");
                            if (!VestelSystemProperties.setEmmcEnvironmentVariable(mMBootEnvDBName, "0"))
                            {
                                addMessage(VestelUSBOperationResult.tcon_mboot_env_db_update_fail);
                            }
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updateTconFile() Failed!");
                            addMessage(VestelUSBOperationResult.tcon_ini_file_copy_fail);
                        }
                    }
                }
                break;

            default:
                {
                    if (!VestelPanelProperties.isPanelTCONLESS())
                    {
                        VestelLog.debug(TAG, "updateTconFiles() Not TCONLESS board");
                        return;
                    }

                    String file_name = mPanelGammaFileName;
                    String usb_input_path = root_path + mPanelFileUsbDir + "/" + file_name;
                    VestelLog.debug(TAG, "updateTconFiles() Gamma USB File Path = " + usb_input_path);

                    String system_path = VestelConfig.getGammaBinFolder() + mPanelGammaFileName;
                    VestelLog.debug(TAG, "updateTconFiles() Gamma System Path = " + system_path);

                    if (VestelFileSystem.isFileExist(usb_input_path))
                    {
                        if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                        {
                            VestelLog.debug(TAG, "updateTconFiles() Gamma File Copied");
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updateTconFiles() Gamma File Copy Failed!");
                            addMessage(VestelUSBOperationResult.tcon_gamma_file_copy_fail);
                        }
                    }

                    file_name = mPanelTconFileName;
                    usb_input_path = root_path + mPanelFileUsbDir + "/" + file_name;
                    VestelLog.debug(TAG, "updateTconFiles() Tcon ini USB File Path = " + usb_input_path);

                    boolean tcon_ini_copied = false;

                    String tcon_ini_cfg_file = VestelConfig.getTconConfigFile();
                    VestelLog.debug(TAG, "updateTconFiles() Tcon ini File System Path = " + tcon_ini_cfg_file);

                    if (VestelFileSystem.isFileExist(usb_input_path))
                    {
                        if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                        {
                            VestelLog.debug(TAG, "updateTconFiles() Tcon ini File Copied");
                            tcon_ini_copied = true;
                        }
                        else
                        {
                            VestelLog.debug(TAG, "updateTconFiles() Tcon ini File Copy Failed!");
                            addMessage(VestelUSBOperationResult.tcon_ini_file_copy_fail);
                        }
                    }

                    if (tcon_ini_copied)
                    {
                        String tcon_bin_dir = VestelConfig.getTconBinFolder();

                        String tcon_file_name = VestelFileSystem.getIniParameter(tcon_ini_cfg_file , mPanelTconBinFileKey);
                        if (!tcon_file_name.isEmpty())
                        {
                            system_path = tcon_bin_dir + tcon_file_name;
                            VestelLog.debug(TAG, "updateTconFiles() Tcon Bin File System Path = " + system_path);

                            usb_input_path = root_path + mPanelFileUsbDir + tcon_file_name;
                            if (VestelFileSystem.isFileExist(usb_input_path))
                            {
                                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                                {
                                    VestelLog.debug(TAG, "updateTconFiles() Tcon Bin File Copied");
                                }
                                else
                                {
                                    VestelLog.debug(TAG, "updateTconFiles() Tcon Bin File Copy Failed!");
                                    addMessage(VestelUSBOperationResult.tcon_binary_file_copy_fail);
                                }
                            }
                        }

                        tcon_file_name = VestelFileSystem.getIniParameter(tcon_ini_cfg_file , mPanelTconPmicFileKey);
                        if (!tcon_file_name.isEmpty())
                        {
                            system_path = tcon_bin_dir + tcon_file_name;
                            VestelLog.debug(TAG, "updateTconFiles() Tcon PMIC File System Path = " + system_path);

                            usb_input_path = root_path + mPanelFileUsbDir + tcon_file_name;
                            if (VestelFileSystem.isFileExist(usb_input_path))
                            {
                                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                                {
                                    VestelLog.debug(TAG, "updateTconFiles() Tcon PMIC File Copied");
                                }
                                else
                                {
                                    VestelLog.debug(TAG, "updateTconFiles() Tcon PMIC File Copy Failed!");
                                    addMessage(VestelUSBOperationResult.tcon_pmic_file_copy_fail);
                                }
                            }
                        }

                        tcon_file_name = VestelFileSystem.getIniParameter(tcon_ini_cfg_file , mPanelTconPGammaFileKey);
                        if (!tcon_file_name.isEmpty())
                        {
                            system_path = tcon_bin_dir + tcon_file_name;
                            VestelLog.debug(TAG, "updateTconFiles() Tcon PGamma File System Path = " + system_path);

                            usb_input_path = root_path + mPanelFileUsbDir + tcon_file_name;
                            if (VestelFileSystem.isFileExist(usb_input_path))
                            {
                                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                                {
                                    VestelLog.debug(TAG, "updateTconFiles() Tcon PGamma File Copied");
                                }
                                else
                                {
                                    VestelLog.debug(TAG, "updateTconFiles() Tcon PGamma File Copy Failed!");
                                    addMessage(VestelUSBOperationResult.tcon_gamma_file_copy_fail);
                                }
                            }
                        }

                        tcon_file_name = VestelFileSystem.getIniParameter(tcon_ini_cfg_file , mPanelTconLevelShiftFileKey);
                        if (!tcon_file_name.isEmpty())
                        {
                            system_path = tcon_bin_dir + tcon_file_name;
                            VestelLog.debug(TAG, "updateTconFiles() Tcon Level Shift File System Path = " + system_path);

                            usb_input_path = root_path + mPanelFileUsbDir + tcon_file_name;
                            if (VestelFileSystem.isFileExist(usb_input_path))
                            {
                                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                                {
                                    VestelLog.debug(TAG, "updateTconFiles() Tcon Level Shift File Copied");
                                }
                                else
                                {
                                    VestelLog.debug(TAG, "updateTconFiles() Tcon Level Shift File Copy Failed!");
                                    addMessage(VestelUSBOperationResult.tcon_level_shift_file_copy_fail);
                                }
                            }
                        }
                    }
                }
                break;
        }
    }

    private static void backupTconFiles(String root_path)
    {
        VestelLog.debug(TAG, "backupTconFiles() Not implemented");
    }

    private static void updatePanelFile(String root_path)
    {
        root_path = root_path + mPanelFileUsbDir;
        VestelLog.debug(TAG, "updatePanelFile() Checking for panel file at = " + root_path);

        File folder = new File(root_path);
        File[] list_of_files = folder.listFiles();

        if (list_of_files == null)
        {
            VestelLog.debug(TAG, "updatePanelFile() Directory not found at = " + root_path);
            return;
        }

        String panel_file_name = "";
        for (int i = 0; i < list_of_files.length; i++)
        {
            String file_name = list_of_files[i].getName();
            if (file_name.endsWith(mPanelFileExtension) && !file_name.equals(mPanelTconFileName))
            {
                panel_file_name = file_name;
                break;
            }
        }

        if (panel_file_name.isEmpty())
        {
            VestelLog.debug(TAG, "updatePanelFile() No panel file Found");
            return;
        }

        String usb_input_path = root_path + panel_file_name;
        VestelLog.debug(TAG, "updatePanelFile() Panel file usb path = " + usb_input_path);

        String system_path = VestelConfig.getPanelFolder() + panel_file_name;
        VestelLog.debug(TAG, "updatePanelFile() Panel file system path = " + system_path);

        if (!VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
        {
            VestelLog.debug(TAG, "updatePanelFile() Copy panel file failed!");
            addMessage(VestelUSBOperationResult.panel_file_copy_fail);
            return;
        }

        if (VestelConfig.setPanelFile(system_path) != 0)
        {
            VestelLog.debug(TAG, "updatePanelFile() Update Panel File Name Failed!");
            addMessage(VestelUSBOperationResult.panel_file_name_update_fail);
            return;
        }

        String key;

        switch (m_project_type)
        {
            case COSMOS:
            case COMET:
                {
                    key = "panel:m_pProductionPanelName";
                }
                break;

            default:
                {
                    key = "panel:m_p4K540_240PPanelName";
                }
        }

        String customer_ini_file = VestelConfig.getCustomerFile();

        if (!(m_project_type == VestelProjectType.COSMOS || m_project_type == VestelProjectType.COMET))
        {
            VestelFileSystem.setIniParameter(customer_ini_file, "panel:m_pPanelName", system_path);
            VestelSystemProperties.setEmmcEnvironmentVariable(mMBootEnvDBName, "0");
        }

        String panel = VestelFileSystem.getIniParameter(customer_ini_file, "panel:m_pPanelName");
        VestelLog.debug(TAG, "Panel get name : " + panel);

        if (!VestelFileSystem.setIniParameter(customer_ini_file, key, panel))
        {
            VestelLog.debug(TAG, "updatePanelFile() Update " + key + " Panel File Name Failed!");
            addMessage(VestelUSBOperationResult.panel_file_name_update_fail);
            return;
        }

        String mirror_cfg_ini = VestelConfig.getMirrorConfigFile();
        String mirror_osd = VestelFileSystem.getIniParameter(system_path, "panel:MIRROR_OSD");
        String mirror_video = VestelFileSystem.getIniParameter(system_path, "panel:MIRROR_VIDEO");
        String mirror_osd_type = VestelFileSystem.getIniParameter(system_path, "panel:MIRROR_OSD_TYPE");
        String mirror_video_type = VestelFileSystem.getIniParameter(system_path, "panel:MIRROR_VIDEO_TYPE");

        if ((mirror_osd != null) && !mirror_osd.equals("") &&
            (mirror_video != null) && !mirror_video.equals("") &&
            (mirror_osd_type != null) && !mirror_osd_type.equals("") &&
            (mirror_video_type != null) && !mirror_video_type.equals(""))
        {
            boolean success = VestelFileSystem.setIniParameter(mirror_cfg_ini, "MISC_MIRROR_CFG:MIRROR_OSD", mirror_osd)
                           && VestelFileSystem.setIniParameter(mirror_cfg_ini, "MISC_MIRROR_CFG:MIRROR_VIDEO", mirror_video)
                           && VestelFileSystem.setIniParameter(mirror_cfg_ini, "MISC_MIRROR_CFG:MIRROR_OSD_TYPE", mirror_osd_type)
                           && VestelFileSystem.setIniParameter(mirror_cfg_ini, "MISC_MIRROR_CFG:MIRROR_VIDEO_TYPE", mirror_video_type);
            if (!success)
            {
                VestelLog.debug(TAG, "Mirror Panel params cannot be updated");
            }
        }
        else
        {
            VestelLog.debug(TAG, "There is no mirror panel params on "+ panel_file_name);
        }

        VestelLog.debug(TAG, "updatePanelFile() Panel file updated successfully");
    }

    private static void backupPanelFile(String root_path)
    {
        root_path = root_path + mPanelFileUsbDir;
        VestelLog.debug(TAG, "backupPanelFile() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.getPanelFile();
            VestelLog.debug(TAG, "backupPanelFile() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupPanelFile() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupPanelFile() Copy file failed!");
                addMessage(VestelUSBOperationResult.panel_file_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupPanelFile() Create folder failed");
            addMessage(VestelUSBOperationResult.panel_file_copy_fail);
        }
    }

    private static void updatePqFiles(String root_path)
    {
        String usb_input_path;
        String system_path;
        String file_name;

        String pq_sys_dir = VestelConfig.getPqFolder();
        for (int i = 0; i < mPqFileNames.length; i++)
        {
            file_name = mPqFileNames[i];
            usb_input_path = root_path + mPqFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updatePqFiles() PQ File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                system_path = pq_sys_dir + file_name;
                VestelLog.debug(TAG, "updatePqFiles() PQ File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updatePqFiles() PQ File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updatePqFiles() PQ File Copy Failed!");
                    addMessage(VestelUSBOperationResult.pq_file_copy_fail);
                }
            }
        }

        String pq_general_sys_dir = VestelConfig.getPqGeneralFolder();
        for (int i = 0; i < mPqGeneralFileNames.length; i++)
        {
            file_name = mPqGeneralFileNames[i];
            usb_input_path = root_path + mPqGeneralFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updatePqFiles() PQ General File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                system_path = pq_general_sys_dir + file_name;
                VestelLog.debug(TAG, "updatePqFiles() PQ General File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updatePqFiles() PQ General File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updatePqFiles() PQ General File Copy Failed!");
                    addMessage(VestelUSBOperationResult.pq_general_file_copy_fail);
                }
            }
        }

        String hdr_bin_sys_dir = VestelConfig.getHdrBinFolder();
        for (int i = 0; i < mHdrBinFileNames.length; i++)
        {
            file_name = mHdrBinFileNames[i];
            usb_input_path = root_path + mHdrBinFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updatePqFiles() HDR BIN File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                system_path = hdr_bin_sys_dir + file_name;
                VestelLog.debug(TAG, "updatePqFiles() HDR BIN File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updatePqFiles() HDR BIN File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updatePqFiles() HDR BIN File Copy Failed!");
                    addMessage(VestelUSBOperationResult.pq_hdr_bin_file_copy_fail);
                }
            }
        }

        String hdr_1611_bin_sys_dir = VestelConfig.getHdr1611BinFolder();
        for (int i = 0; i < mPanelHdrBin1611FileNames.length; i++)
        {
            file_name = mPanelHdrBin1611FileNames[i];
            usb_input_path = root_path + mHdrBinFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updatePqFiles() HDR 1.6.1.1 BIN File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                system_path = hdr_1611_bin_sys_dir + file_name;
                VestelLog.debug(TAG, "updatePqFiles() HDR 1.6.1.1 BIN File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updatePqFiles() HDR 1.6.1.1 BIN File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updatePqFiles() HDR 1.6.1.1 BIN File Copy Failed!");
                    addMessage(VestelUSBOperationResult.pq_hdr_1611_bin_file_copy_fail);
                }
            }
        }

        usb_input_path = root_path + mConfigDefaultValueFileUsbDir + mConfigDefaultValueFileName;
        VestelLog.debug(TAG, "updatePqFiles() ConfigDefaultValue File Path = " + usb_input_path);

        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            system_path = VestelConfig.getConfigDefaultValueFile();
            VestelLog.debug(TAG, "updatePqFiles() ConfigDefaultValue File System Path = " + system_path);

            if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
            {
                VestelLog.debug(TAG, "updatePqFiles() ConfigDefaultValue File Copied");
            }
            else
            {
                VestelLog.debug(TAG, "updatePqFiles() ConfigDefaultValue File Copy Failed!");
                addMessage(VestelUSBOperationResult.config_default_value_file_copy_fail);
            }
        }

        for (int i = 0; i < mGammaTableFileNames.length; i++)
        {
            file_name = mGammaTableFileNames[i];
            usb_input_path = root_path + mPqGeneralFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updatePqFiles() GammaTable File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                if (!VestelFileSystem.isFolderExist(mGammaTableFileSystemDir))
                {
                    VestelFileSystem.createFolder(mGammaTableFileSystemDir);
                }

                system_path = mGammaTableFileSystemDir + "/" + file_name;
                VestelLog.debug(TAG, "updatePqFiles() GammaTable File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updatePqFiles() GammaTable File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updatePqFiles() GammaTable File Copy Failed!");
                    addMessage(VestelUSBOperationResult.pq_gamma_file_copy_fail);
                }
            }
        }

        for (int i = 0; i < mOsdMappingFileNames.length; i++)
        {
            file_name = mOsdMappingFileNames[i];
            usb_input_path = root_path + mPqGeneralFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updatePqFiles() OsdMapping File Path = " + usb_input_path);

           if (VestelFileSystem.isFileExist(usb_input_path))
            {
                if (!VestelFileSystem.isFolderExist(mOSDRangeFileSystemDir))
                {
                    VestelFileSystem.createFolder(mOSDRangeFileSystemDir);
                }

                system_path = mOSDRangeFileSystemDir + "/" + file_name;
                VestelLog.debug(TAG, "updatePqFiles() OsdMapping File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updatePqFiles() OsdMapping File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updatePqFiles() OsdMapping File Copy Failed!");
                    addMessage(VestelUSBOperationResult.pq_osd_mapping_file_copy_fail);
                }
            }
        }

        for (int i = 0; i < mColorFileNames.length; i++)
        {
            file_name = mColorFileNames[i];
            usb_input_path = root_path + mPqGeneralFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updatePqFiles() Color File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                if (!VestelFileSystem.isFolderExist(mColorFileSystemDir))
                {
                    VestelFileSystem.createFolder(mColorFileSystemDir);
                }

                system_path = mColorFileSystemDir + "/" + file_name;
                VestelLog.debug(TAG, "updatePqFiles() Color File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updatePqFiles() Color File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updatePqFiles() Color File Copy Failed!");
                    addMessage(VestelUSBOperationResult.pq_color_file_copy_fail);
                }
            }
        }
    }

    private static void backupPqFiles(String root_path)
    {
        String output_path = root_path + mPqFilesUsbDir;
        VestelLog.debug(TAG, "backupPqFiles() Creating folder at = " + output_path);

        if (VestelFileSystem.createFolder(output_path, folder_mode))
        {
            String folder = VestelConfig.getPqFolder();
            VestelLog.debug(TAG, "backupPqFiles() PQ Folder path = " + folder);

            for (int i = 0; i < mPqFileNames.length; i++)
            {
                String system_path = folder + mPqFileNames[i];
                VestelLog.debug(TAG, "updatePqFiles() PQ File Path = " + system_path);

                if (VestelFileSystem.copyFileToFolder(system_path, output_path))
                {
                    VestelLog.debug(TAG, "backupPqFiles() File copied");
                }
                else
                {
                    VestelLog.debug(TAG, "backupPqFiles() Copy file failed!");
                    addMessage(VestelUSBOperationResult.pq_file_copy_fail);
                }
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupPqFiles() Create folder failed!");
            addMessage(VestelUSBOperationResult.pq_file_copy_fail);
        }

        output_path = root_path + mPqGeneralFilesUsbDir;
        VestelLog.debug(TAG, "backupPqFiles() Creating folder at = " + output_path);

        if (VestelFileSystem.createFolder(output_path, folder_mode))
        {
            String folder = VestelConfig.getPqGeneralFolder();
            VestelLog.debug(TAG, "backupPqFiles() PQ General Folder path = " + folder);

            for (int i = 0; i < mPqGeneralFileNames.length; i++)
            {
                String system_path = folder + mPqGeneralFileNames[i];
                VestelLog.debug(TAG, "updatePqFiles() PQ General File Path = " + system_path);

                if (VestelFileSystem.copyFileToFolder(system_path, output_path))
                {
                    VestelLog.debug(TAG, "backupPqFiles() File copied");
                }
                else
                {
                    VestelLog.debug(TAG, "backupPqFiles() Copy file failed!");
                    addMessage(VestelUSBOperationResult.pq_file_copy_fail);
                }
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupPqFiles() Create folder failed!");
            addMessage(VestelUSBOperationResult.pq_file_copy_fail);
        }

        output_path = root_path + mConfigDefaultValueFileUsbDir;
        VestelLog.debug(TAG, "backupPqFiles() Creating folder at = " + output_path);

        if (VestelFileSystem.createFolder(output_path, folder_mode))
        {
            String system_path = VestelConfig.getConfigDefaultValueFile();
            VestelLog.debug(TAG, "backupPqFiles() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, output_path))
            {
                VestelLog.debug(TAG, "backupPqFiles() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupPqFiles() Copy file failed!");
                addMessage(VestelUSBOperationResult.pq_file_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupPqFiles() Create folder failed!");
            addMessage(VestelUSBOperationResult.pq_file_copy_fail);
        }
    }

    private static void updateAqFiles(String root_path)
    {
        String usb_input_path;
        String system_path;
        String file_name;

        String aq_dir = VestelConfig.getAqFolder();
        for (int i = 0; i < mAqFileNames.length; i++)
        {
            file_name = mAqFileNames[i];
            usb_input_path = root_path + mAqFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updateAqFiles() AQ File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                system_path = aq_dir + file_name;
                VestelLog.debug(TAG, "updateAqFiles() AQ File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ File Copy Failed!");
                    addMessage(VestelUSBOperationResult.aq_file_copy_fail);
                }
            }
        }

        String aq_amp_dir = VestelConfig.getAqAmpFolder();
        for (int i = 0; i < mAqAmpFileNames.length; i++)
        {
            file_name = mAqAmpFileNames[i];
            usb_input_path = root_path + mAudioConfigUsbDir + file_name;
            VestelLog.debug(TAG, "updateAqFiles() AQ Amp File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                system_path = aq_amp_dir + file_name;
                VestelLog.debug(TAG, "updateAqFiles() AQ Amp File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ Amp File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ Amp File Copy Failed!");
                    addMessage(VestelUSBOperationResult.aq_amp_file_copy_fail);
                }
            }
        }

        String aq_input_source_dir = VestelConfig.getAqInputSourceFolder();
        for (int i = 0; i < mAqInputSrcFileNames.length; i++)
        {
            file_name = mAqInputSrcFileNames[i];
            usb_input_path = root_path + mAqFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updateAqFiles() AQ InputSrc File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                system_path = aq_input_source_dir + file_name;
                VestelLog.debug(TAG, "updateAqFiles() AQ InputSrc File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ InputSrc File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ InputSrc File Copy Failed!");
                    addMessage(VestelUSBOperationResult.aq_input_source_file_copy_fail);
                }
            }
        }

        String aq_sound_style_dir = VestelConfig.getAqSoundStyleFolder();
        for (int i = 0; i < mAqSoundStyleFileNames.length; i++)
        {
            file_name = mAqSoundStyleFileNames[i];
            usb_input_path = root_path + mAqFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updateAqFiles() AQ SoundStyle File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                system_path = aq_sound_style_dir + file_name;
                VestelLog.debug(TAG, "updateAqFiles() AQ SoundStyle File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ SoundStyle File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ SoundStyle File Copy Failed!");
                    addMessage(VestelUSBOperationResult.aq_sound_style_file_copy_fail);
                }
            }
        }

        String aq_dap_dir = VestelConfig.getAqDapFolder();
        for (int i = 0; i < mAqDapFileNames.length; i++)
        {
            file_name = mAqDapFileNames[i];
            usb_input_path = root_path + mAqFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updateAqFiles() AQ DAP File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                system_path = aq_dap_dir + file_name;
                VestelLog.debug(TAG, "updateAqFiles() AQ DAP File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ DAP File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ DAP File Copy Failed!");
                    addMessage(VestelUSBOperationResult.aq_dap_file_copy_fail);
                }
            }
        }

        String aq_dtsvx_dir = VestelConfig.getAqDtsVxFolder();
        for (int i = 0; i < mAqDtsVxFileNames.length; i++)
        {
            file_name = mAqDtsVxFileNames[i];
            usb_input_path = root_path + mAqFilesUsbDir + file_name;
            VestelLog.debug(TAG, "updateAqFiles() AQ DTSVX File Path = " + usb_input_path);

            if (VestelFileSystem.isFileExist(usb_input_path))
            {
                system_path = aq_dtsvx_dir + file_name;
                VestelLog.debug(TAG, "updateAqFiles() AQ DTSVX File System Path = " + system_path);

                if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ DTSVX File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateAqFiles() AQ DTSVX File Copy Failed!");
                    addMessage(VestelUSBOperationResult.aq_dts_vx_file_copy_fail);
                }
            }
        }
    }

    private static void backupAqFiles(String root_path)
    {
        VestelLog.debug(TAG, "backupAqFiles() Not implemented");
    }

    private static void updateTvWizardCountryXml(String root_path)
    {
        String usb_input_path = root_path + mTvWizardCountryUsbDir;
        VestelLog.debug(TAG, "updateTvWizardCountryXml() TV Wizard Country File Path = " + usb_input_path);

        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            String system_path = VestelConfig.getTvWizardCountryFile();
            VestelLog.debug(TAG, "updateTvWizardCountryXml() File System Path = " + system_path);

            if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
            {
                VestelLog.debug(TAG, "updateTvWizardCountryXml() File Copied");
            }
            else
            {
                VestelLog.debug(TAG, "updateTvWizardCountryXml() File Copy Failed!");
                addMessage(VestelUSBOperationResult.tv_wizard_country_file_copy_fail);
            }
        }
    }

    private static void backupTvWizardCountryXml(String root_path)
    {
        root_path = root_path + mTvWizardCountryUsbDir;
        VestelLog.debug(TAG, "backupTvWizardCountryXml() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.getTvWizardCountryFile();
            VestelLog.debug(TAG, "backupTvWizardCountryXml() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupTvWizardCountryXml() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupTvWizardCountryXml() Copy file failed!");
                addMessage(VestelUSBOperationResult.tv_wizard_country_file_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupTvWizardCountryXml() Create folder failed");
            addMessage(VestelUSBOperationResult.tv_wizard_country_file_copy_fail);
        }
    }

    private static void updateMacAddr(String root_path)
    {
        String usb_input_path = root_path + mMacAddressUsbPath;
        VestelLog.debug(TAG, "updateMacAddr() Mac Address Path = " + usb_input_path);

        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            try
            {
                FileInputStream stream = new FileInputStream(usb_input_path);
                StringBuilder builder = new StringBuilder(512);
                Reader reader = new InputStreamReader(stream, "UTF-8");

                int c = 0;
                while ((c = reader.read()) != -1)
                {
                    builder.append((char) c);
                }

                VestelLog.debug(TAG, "New Mac Address = " + builder.toString());
                VestelNetworkManager.setEthernetMacAddress(builder.toString());
            }
            catch (IOException e)
            {
                VestelLog.error(TAG, e.toString());
            }
        }
    }

    private static void backupMacAddr(String root_path)
    {
        VestelLog.debug(TAG, "backupMacAddr() Not implemented");
    }

    private static void updateBoard(String root_path)
    {
        root_path = root_path + mBoardUsbDir;
        VestelLog.debug(TAG, "updateBoard() Checking for board file at = " + root_path);

        File folder = new File(root_path);
        File[] list_of_files = folder.listFiles();

        if (list_of_files == null)
        {
            VestelLog.debug(TAG, "updateBoard() Directory not found at = " + root_path);
            return;
        }

        String board_file_name = "";
        for (int i = 0; i < list_of_files.length; i++)
        {
            String file_name = list_of_files[i].getName();
            if (file_name.endsWith(mBoardExtension))
            {
                board_file_name = file_name;
                break;
            }
        }

        if (board_file_name.isEmpty())
        {
            VestelLog.debug(TAG, "updateBoard() No board file found");
            return;
        }

        String usb_input_path = root_path + board_file_name;
        VestelLog.debug(TAG, "updateBoard() Board file usb path = " + usb_input_path);

        String system_path = VestelConfig.getBoardFile();
        if (!VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
        {
            VestelLog.debug(TAG, "updateBoard() Copy board file failed!");
            addMessage(VestelUSBOperationResult.board_file_copy_fail);
            return;
        }

        if (!VestelSystemProperties.setEmmcEnvironmentVariable(mMBootEnvDBName, "0"))
        {
            VestelLog.debug(TAG, "updateBoard() Update MBoot DB Failed!");
            addMessage(VestelUSBOperationResult.board_file_mboot_env_db_update_fail);
            return;
        }

        String config_file_names = VestelConfig.getConfigFileNamesFile();
        if (!VestelFileSystem.isFileExist(config_file_names))
        {
            VestelFileSystem.createFile(config_file_names, file_mode);
        }

        String value;

        switch (m_project_type)
        {
            case COSMOS:
            case COMET:
                {
                    value = (board_file_name.split("\\."))[0];
                }
                break;

            default:
                {
                    value = VestelFileSystem.getIniParameter(system_path, "BOARD_KEYPAD:BOARD_KEYPAD_TYPE");

                    if (value == null || value.equals(""))
                    {
                        value = board_file_name.substring(0, board_file_name.lastIndexOf(".ini"));
                    }
                }
                break;
        }

        if (!VestelFileSystem.setIniParameter(config_file_names, "KEYPAD_CFG:KEYPAD_NAME",value))
        {
            VestelLog.debug(TAG, "updateBoard() Update board file name failed!");
            addMessage(VestelUSBOperationResult.board_file_name_update_fail);
            return;
        }

        VestelLog.debug(TAG, "updateBoard() Board file updated successfully");
    }

    private static void backupBoard(String root_path)
    {
        root_path = root_path + mBoardUsbDir;
        VestelLog.debug(TAG, "backupBoard() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.getBoardFile();
            VestelLog.debug(TAG, "backupBoard() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupBoard() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupBoard() Copy file failed!");
                addMessage(VestelUSBOperationResult.board_file_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupBoard() Create folder failed");
            addMessage(VestelUSBOperationResult.board_file_copy_fail);
        }
    }

    private static void updateIRConfig(String root_path)
    {
        root_path = root_path + mIRConfigUsbDir;
        VestelLog.debug(TAG, "updateIRConfig() Checking for ir_config.ini at = " + root_path);

        File folder = new File(root_path);

        File[] list_of_files = folder.listFiles();
        if (list_of_files == null)
        {
            VestelLog.debug(TAG, "updateIRConfig() Directory not Found at = " + root_path);
            return;
        }

        String ir_file_name = "";
        for (int i = 0; i < list_of_files.length; i++)
        {
            String file_name = list_of_files[i].getName();
            if (file_name.endsWith(mIRConfigExtension))
            {
                ir_file_name = file_name;
                break;
            }
        }

        if (ir_file_name.isEmpty())
        {
            VestelLog.debug(TAG, "updateIRConfig() No ir_config.ini found");
            return;
        }

        String usb_input_path = root_path + ir_file_name;
        VestelLog.debug(TAG, "updateIRConfig() ir_config.ini Path = " + usb_input_path);

        String system_path = VestelConfig.getIrConfigFile();
        if (!VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
        {
            VestelLog.debug(TAG, "updateIRConfig() Copy ir_config.ini Failed!");
            addMessage(VestelUSBOperationResult.irconfig_file_copy_fail);
            return;
        }

        if (!VestelSystemProperties.setEmmcEnvironmentVariable(mMBootEnvDBName, "0"))
        {
            VestelLog.debug(TAG, "updateIRConfig() Update MBoot DB Failed!");
            addMessage(VestelUSBOperationResult.irconfig_file_mboot_env_db_update_fail);
            return;
        }

        String config_file_name = VestelConfig.getConfigFileNamesFile();
        if (!VestelFileSystem.isFileExist(config_file_name))
        {
            VestelFileSystem.createFile(config_file_name, file_mode);
        }

        String[] splittedFileName = ir_file_name.split("\\.");
        if (!VestelFileSystem.setIniParameter(config_file_name,"IR_CFG:RC_TYPE",splittedFileName[0]))
        {
            VestelLog.debug(TAG, "updateIRConfig() Update ir_config.ini File Name Failed!");
            addMessage(VestelUSBOperationResult.irconfig_file_name_update_fail);
            return;
        }

        VestelLog.debug(TAG, "updateIRConfig() ir_config.ini updated successfully");
    }

    private static void backupIRConfig(String root_path)
    {
        root_path = root_path + mIRConfigUsbDir;
        VestelLog.debug(TAG, "backupIRConfig() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.getIrConfigFile();
            VestelLog.debug(TAG, "backupIRConfig() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupIRConfig() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupIRConfig() Copy file failed!");
                addMessage(VestelUSBOperationResult.irconfig_file_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupIRConfig() Create folder failed");
            addMessage(VestelUSBOperationResult.irconfig_file_copy_fail);
        }
    }

    private static void updateCustomization(String root_path)
    {
        String usb_input_path = root_path + mCustomizationUsbPath;
        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            try
            {
                String system_path = VestelConfig.getCustomizationFuncFile();

                if (!VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
                {
                    VestelLog.debug(TAG, "updateCustomization() CustomizationFunc.ini copy failed!");
                    addMessage(VestelUSBOperationResult.customization_file_copy_fail);
                    return;
                }
                else
                {
                    VestelLog.debug(TAG, "updateCustomization() CustomizationFunc.ini copied");
                }

                if (!VestelSystemProperties.setEmmcEnvironmentVariable(mMBootEnvDBName, "0"))
                {
                    VestelLog.debug(TAG, "updateCustomization() Update MBoot DB Failed!");
                    addMessage(VestelUSBOperationResult.customization_file_mboot_env_db_update_fail);
                    return;
                }
            }
            catch (Exception e)
            {
                VestelLog.debug(TAG, "updateCustomization() CustomizationFunc.ini copy failed!");
                addMessage(VestelUSBOperationResult.customization_file_copy_fail);
            }
        }
    }

    private static void backupCustomization(String root_path)
    {
        root_path = Paths.get(root_path + mCustomizationUsbPath).getParent().toString();
        VestelLog.debug(TAG, "backupCustomization() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.getCustomizationFuncFile();
            VestelLog.debug(TAG, "backupCustomization() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupCustomization() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupCustomization() Copy file failed!");
                addMessage(VestelUSBOperationResult.customization_file_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupCustomization() Create folder failed");
            addMessage(VestelUSBOperationResult.customization_file_copy_fail);
        }
    }

    private static void updateMFCFirmware(String root_path)
    {
        root_path = root_path + mMfcFirmwareUsbPath;
        VestelLog.debug(TAG, "updateMFCFirmware() MFC Firmware Path = " + root_path);

        if (VestelFileSystem.isFileExist(root_path))
        {
            String str = VestelSystemProperties.getMFCFirmwareVersion();

            int current_version = str.isEmpty() ? 0 : Integer.parseInt(str);
            int file_version = VestelSystemProperties.getMFCVersion(root_path);

            VestelLog.debug(TAG, "updateMFCFirmware() MFC Firmware Current Version: " + current_version);
            VestelLog.debug(TAG, "updateMFCFirmware() MFC Firmware File Version: " + file_version);

            if (current_version == file_version)
            {
                addMessage(VestelUSBOperationResult.mfc_firmware_same_version);
            }
            else
            {
                VestelLog.debug(TAG, "updateMFCFirmware() MFC Firmware Update Started!");

                if (VestelSystemProperties.upgradeMFCFirmware(root_path))
                {
                    VestelLog.debug(TAG, "updateMFCFirmware() MFC Firmware Updated!");
                }
                else
                {
                    VestelLog.debug(TAG, "updateMFCFirmware() MFC Firmware Update Failed!");
                    addMessage(VestelUSBOperationResult.mfc_firmware_update_fail);
                }
            }
        }
    }

    private static void backupMFCFirmware(String root_path)
    {
        VestelLog.debug(TAG, "backupMFCFirmware() Not implemented");
    }

    private static void updateUrsaOrbitConfiguration(String root_path)
    {
        String usb_input_path = root_path + mUrsaOrbitCfgUsbPath;
        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            try
            {
                if (VestelFileSystem.copyFile(usb_input_path, VestelConfig.getUrsaOrbitConfigFile(), file_mode))
                {
                    VestelLog.debug(TAG, "updateUrsaOrbitConfiguration() File Copied");
                }
                else
                {
                    VestelLog.debug(TAG, "updateUrsaOrbitConfiguration() File Copy Failed!");
                    addMessage(VestelUSBOperationResult.ursa_orbit_config_file_copy_fail);
                }
            }
            catch (Exception e)
            {
                VestelLog.debug(TAG, "updateUrsaOrbitConfiguration() File Copy Failed!");
                addMessage(VestelUSBOperationResult.ursa_orbit_config_file_copy_fail);
            }
        }
    }

    private static void backupUrsaOrbitConfiguration(String root_path)
    {
        root_path = Paths.get(root_path + mUrsaOrbitCfgUsbPath).getParent().toString();
        VestelLog.debug(TAG, "backupUrsaOrbitConfiguration() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.getUrsaOrbitConfigFile();
            VestelLog.debug(TAG, "backupUrsaOrbitConfiguration() File system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupUrsaOrbitConfiguration() File copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupUrsaOrbitConfiguration() Copy file failed!");
                addMessage(VestelUSBOperationResult.ursa_orbit_config_file_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupUrsaOrbitConfiguration() Create folder failed");
            addMessage(VestelUSBOperationResult.ursa_orbit_config_file_copy_fail);
        }
    }

    private static void updateThermalMapping(String root_path)
    {
        root_path = root_path + mOslbTempMapFileUsbDir;

        File folder = new File(root_path);
        VestelLog.debug(TAG, "updateThermalMapping() Checking for Thermal Mapping at = " + root_path);

        File[] list_of_files = folder.listFiles();
        if (list_of_files == null)
        {
            VestelLog.debug(TAG, "updateThermalMapping() Directory not found at = " + root_path);
            return;
        }

        file_name_thermal_mapping = "";
        for (int i = 0; i < list_of_files.length; i++)
        {
            String file_name = list_of_files[i].getName();

            if (file_name.endsWith(FILE_EXTENSION_BIN))
            {
                file_name_thermal_mapping = file_name;
                break;
            }
        }

        if (file_name_thermal_mapping.isEmpty())
        {
            VestelLog.debug(TAG, "updateThermalMapping() No Thermal Mapping File Found");
            return;
        }

        String usb_input_path = root_path + file_name_thermal_mapping;
        VestelLog.debug(TAG, "updateThermalMapping() OSLB Temp Map Path = " + usb_input_path);

        mOslbRetryCount = 0;
        mOslbFwCmd = 0;
        mOslbFwStatus = 0;
        mOslbAddress = 0;
        mOslbSize = 0;
        mOslbIsUpdateFinish = false;

        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            try
            {
                byte[] file_data = Files.readAllBytes(Paths.get(usb_input_path));

                if (isFwStatusIdle())   //Check t-con status before data transmission
                {
                    sendFirst(file_data);   //Transmission of the first 32 bytes of the loaded binary file

                    while ((mOslbRetryCount < OSLB_RETRY_COUNT_MAX) && (!mOslbIsUpdateFinish))
                    {
                        try
                        {
                            sleepNanos((mOslbRetryCount > 0) ? OSLB_TCON_DELAY_MAX : OSLB_TCON_DELAY_MIN);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            VestelLog.debug(TAG, "updateThermalMapping() sleepNanos Failed!");
                        }

                        readReturn8Bytes();
                        switch (mOslbFwStatus)
                        {
                            case OSLB_NEXT_DATA_READY:
                                VestelLog.debug(TAG, "updateThermalMapping() LGD FW(T-Con) status : Ready");
                                mOslbRetryCount = 0;
                                sendData(mOslbAddress, mOslbSize, file_data, mOslbFwCmd);
                                break;

                            case OSLB_IDLE:
                                VestelLog.debug(TAG, "updateThermalMapping() I2C update Task normal finish");
                                mOslbIsUpdateFinish = true;

                                if (m_project_type != VestelProjectType.HOCKEY)
                                {
                                    if (file_name_thermal_mapping != null && !file_name_thermal_mapping.isEmpty()) {
                                        String config_file_names = VestelConfig.getConfigFileNamesFile();

                                        if (!VestelFileSystem.setIniParameter(config_file_names,"THERMAL_MAPPING:THERMAL_MAPPING_DATA", file_name_thermal_mapping)) {
                                            VestelLog.debug(TAG, "updateThermalMapping() I2C update Task NOK");
                                            addMessage(VestelUSBOperationResult.thermal_mapping_file_update_fail);
                                            return;
                                        }
                                    }
                                }
                                break;

                            case OSLB_BUSY:
                                VestelLog.debug(TAG, "updateThermalMapping() LGD FW(T-Con) status : Busy");
                                mOslbRetryCount++;
                                break;

                            case OSLB_ERROR:
                                VestelLog.debug(TAG, "updateThermalMapping() LGD FW(T-Con) status : Error");
                                VestelLog.debug(TAG, "updateThermalMapping() Update Fail!");
                                mOslbRetryCount = OSLB_RETRY_COUNT_MAX;
                                break;

                            case OSLB_RETRY:
                                VestelLog.debug(TAG, "updateThermalMapping() LGD FW(T-Con) status : Retry");
                                mOslbRetryCount = OSLB_RETRY_COUNT_MAX;
                                break;

                            default:
                                VestelLog.debug(TAG, "updateThermalMapping() fw_status error, retry");
                                mOslbRetryCount++;
                                break;
                        }
                    }
                    if (mOslbRetryCount == OSLB_RETRY_COUNT_MAX)
                    {
                        VestelLog.debug(TAG, "updateThermalMapping() I2C update Task abnormal finish");
                    }

                    if (!mOslbIsUpdateFinish)
                    {
                        addMessage(VestelUSBOperationResult.thermal_mapping_file_update_fail);
                    }
                }
            }
            catch (Exception e)
            {
                VestelLog.error(TAG, "updateThermalMapping() Error = " + e.toString());
            }
        }
    }

    private static void backupThermalMapping(String root_path)
    {
        VestelLog.debug(TAG, "backupThermalMapping() Not implemented");
    }

    public static VestelUSBOperationResult updateLocalDimmingFile(String root_path)
    {
        VestelUSBOperationResult result = VestelUSBOperationResult.ldm_file_update_skipped;

        String usb_input_path = root_path + mLocalDimmingUsbDir + "ldm.ini";
        VestelLog.debug(TAG, "updateLocalDimmingFile() LDM file path = " + usb_input_path);

        if (VestelFileSystem.isFileExist(usb_input_path))
        {
            String system_path = VestelConfig.getLdminiFile();
            VestelLog.debug(TAG, "updateLocalDimmingFile() LDM file system path = " + system_path);

            if (VestelFileSystem.copyFile(usb_input_path, system_path, file_mode))
            {
                VestelLog.debug(TAG, "updateLocalDimmingFile() LDM file copied");
                result = VestelUSBOperationResult.ldm_file_update_success;
            }
            else
            {
                VestelLog.debug(TAG, "updateLocalDimmingFile() LDM file copy failed!");
                result = VestelUSBOperationResult.ldm_file_copy_fail;
                addMessage(result);
            }
        }

        return result;
    }

    private static void backupLocalDimmingFile(String root_path)
    {
        root_path = root_path + mLocalDimmingUsbDir;
        VestelLog.debug(TAG, "backupLocalDimmingFile() Creating folder at = " + root_path);

        if (VestelFileSystem.createFolder(root_path, folder_mode))
        {
            String system_path = VestelConfig.getLdminiFile();
            VestelLog.debug(TAG, "backupLocalDimmingFile() LDM file system path = " + system_path);

            if (VestelFileSystem.copyFileToFolder(system_path, root_path))
            {
                VestelLog.debug(TAG, "backupLocalDimmingFile() LDM file copied");
            }
            else
            {
                VestelLog.debug(TAG, "backupLocalDimmingFile() LDM file copy failed!");
                addMessage(VestelUSBOperationResult.ldm_file_copy_fail);
            }
        }
        else
        {
            VestelLog.debug(TAG, "backupLocalDimmingFile() Create folder failed");
            addMessage(VestelUSBOperationResult.ldm_file_copy_fail);
        }
    }

    private static boolean sendReadStatusCMD()
    {
        boolean success = true;

        ArrayList<Byte> iic_addr = new ArrayList<Byte>();
        ArrayList<Byte> set_data = new ArrayList<Byte>();
        short size = 6;

        set_data.add(Integer.decode(OSLB_SET_I2C_HEADER_0).byteValue());
        set_data.add(Integer.decode(OSLB_SET_I2C_HEADER_1).byteValue());
        set_data.add(Integer.decode(OSLB_READ_STATUS).byteValue());
        set_data.add(Integer.decode(OSLB_VALUE_00).byteValue());
        set_data.add(Integer.decode(OSLB_VALUE_00).byteValue());
        set_data.add(Integer.decode(OSLB_VALUE_82).byteValue());

        if (VestelDiagnostics.writeToIic(13, iic_addr, size, set_data))
        {
            success = true;
        }
        else
        {
            success = false;
            VestelLog.debug(TAG, "sendReadStatusCMD() - I2C Write Failed!");
        }

        return success;
    }

    private static boolean readReturn8Bytes()
    {
        boolean success = false;

        ArrayList<Byte> iic_addr = new ArrayList<Byte>();
        ArrayList<Byte> get_data = new ArrayList<Byte>();
        short size = 8;
        int temp_int1 = 0;
        int temp_int2 = 0;
        int temp_int3 = 0;
        int temp_int4 = 0;

        if (VestelDiagnostics.readFromIic(13, iic_addr, size, get_data))
        {
            success = true;
        }
        else
        {
            success = false;
            VestelLog.debug(TAG, "readReturn8Bytes() - I2C Read Failed!");
        }

        if (success)
        {
            mOslbFwCmd = get_data.get(0);
            mOslbFwStatus = get_data.get(1);
            temp_int1 = get_data.get(2);
            temp_int2 = get_data.get(3);
            temp_int3 = get_data.get(4);
            temp_int4 = get_data.get(5);
            mOslbAddress = (temp_int1 << 24) | (temp_int2 << 16) | (temp_int3 << 8) | (temp_int4);
            temp_int1 = get_data.get(6);
            temp_int2 = get_data.get(7);
            mOslbSize = (temp_int1 << 8) | (temp_int2);

            VestelLog.debug(TAG, "readReturn8Bytes() - Bytes = " + get_data.get(0) + " " + get_data.get(1) + " " + get_data.get(2) + " " + get_data.get(3) + " " + get_data.get(4) + " " + get_data.get(5) + " " + get_data.get(6) + " " + get_data.get(7));
            VestelLog.debug(TAG, "readReturn8Bytes() - mOslbFwCmd = " + mOslbFwCmd);
            VestelLog.debug(TAG, "readReturn8Bytes() - mOslbFwStatus = " + mOslbFwStatus);
            VestelLog.debug(TAG, "readReturn8Bytes() - mOslbAddress = " + mOslbAddress);
            VestelLog.debug(TAG, "readReturn8Bytes() - mOslbSize = " + mOslbSize);
        }

        return success;
    }
    private static boolean isFwStatusIdle()
    {
        while (mOslbRetryCount < OSLB_RETRY_COUNT_MAX)
        {
            sendReadStatusCMD();

            try
            {
                sleepNanos(OSLB_TCON_DELAY_MAX);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                VestelLog.debug(TAG, "isFwStatusIdle() - sleepNanos Failed!");
            }

            readReturn8Bytes();

            if (mOslbFwStatus == OSLB_IDLE)
            {
                mOslbRetryCount = 0;
                VestelLog.debug(TAG, "isFwStatusIdle() - Status IDLE - Return true");
                return true;
            }
            else
            {
                mOslbRetryCount++;

                try
                {
                    sleepNanos(OSLB_TCON_DELAY_MIN);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    VestelLog.debug(TAG, "isFwStatusIdle() - sleepNanos Failed!");
                }
            }
        }

        if (mOslbRetryCount >= OSLB_RETRY_COUNT_MAX)
        {
            VestelLog.debug(TAG, "isFwStatusIdle() - Try Count Overflow");
            return false;
        }

        VestelLog.debug(TAG, "isFwStatusIdle() - Return true");
        return true;
    }

    private static boolean sendFirst(byte[] file_data)
    {
        int address = 0;
        int size = DEFAULT_SEND_SIZE;
        byte[] Data = new byte[DEFAULT_SEND_SIZE];

        for (int i = 0; i < DEFAULT_SEND_SIZE; i++)
        {
            Data[i] = file_data[i];
        }

        byte fw_cmd = Data[0];

        return sendData(address, DEFAULT_SEND_SIZE, Data, fw_cmd);
    }

    private static boolean sendData(int _address, int _size, byte[] binaryData, byte _fw_cmd)
    {
        boolean success = true;
        int SETMaker_HeaderSize = SET_I2C_HEADER_SIZE;
        byte[] pickedData = new byte[_size];

        pickDataFromBinary(binaryData, pickedData, _address, _size);

        byte[] sendDataPacket = new byte[SETMaker_HeaderSize + _size];

        addLGDHeaderCheckSumSize(pickedData, sendDataPacket, _fw_cmd,  _size);

        ArrayList<Byte> iic_addr = new ArrayList<Byte>();
        ArrayList<Byte> set_data = new ArrayList<Byte>();
        short size = (short) (SETMaker_HeaderSize + _size);

        for (int i = 0; i < (SETMaker_HeaderSize + _size); i++)
        {
            set_data.add(sendDataPacket[i]);
        }

        if (VestelDiagnostics.writeToIic(13, iic_addr, size, set_data))
        {
            success = true;
        }
        else
        {
            success = false;
            VestelLog.debug(TAG, "sendData() - I2C Write Failed!");
        }

        return success;
    }

    private static boolean addLGDHeaderCheckSumSize(byte[] inData, byte[] outData, byte _fw_cmd, int sendSize)
    {
        int checksum_cal = 0;
        outData[0] = Integer.decode(OSLB_SET_I2C_HEADER_0).byteValue();
        outData[1] = Integer.decode(OSLB_SET_I2C_HEADER_1).byteValue();
        outData[2] = _fw_cmd;
        outData[3] = (byte)(sendSize & 0xFF);
        outData[4] = (byte)((sendSize & 0xFF00) >> 8);
        outData[5] = 0;//Checksum

        for (int i = 0; i < 5; i++)
        {
            checksum_cal += outData[i];
        }

        for (int i = 0; i < sendSize; i++)
        {
            checksum_cal += inData[i];
        }

        checksum_cal = (0x100 - (checksum_cal & 0xFF)) & 0xFF;
        outData[5] = (byte)checksum_cal;

        for (int i = 0; i < sendSize; i++)
        {
            outData[i + SET_I2C_HEADER_SIZE] = inData[i];
        }

        return true;
    }

    private static void pickDataFromBinary(byte[] binaryData, byte[] pickedData, int _address, int _size)
    {
        for (int i = 0; i < _size; i++)
        {
            pickedData[i] = binaryData[_address + i];
        }
    }

    private static void sleepNanos(long nanoDuration) throws InterruptedException
    {
        final long end = System.nanoTime() + nanoDuration;

        long timeLeft = nanoDuration;
        do
        {
            if (timeLeft > SLEEP_PRECISION)
                Thread.sleep (1);
            else
                Thread.sleep (0); // Thread.yield();
            timeLeft = end - System.nanoTime();

            if (Thread.interrupted())
                throw new InterruptedException ();

        } while (timeLeft > 0);
    }
}
