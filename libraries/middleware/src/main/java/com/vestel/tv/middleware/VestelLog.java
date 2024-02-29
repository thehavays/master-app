package com.vestel.tv.middleware;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @brief This class manages Android application logs
 */
@SuppressWarnings("WeakerAccess")
public class VestelLog
{
    /**
     * @brief Defines log output levels,
     *        any logs with output levels above the default output level will not be outputted
     */
    enum VestelLogLevel
    {
        VESTEL_LOG_LEVEL_NONE,
        VESTEL_LOG_LEVEL_ERROR,
        VESTEL_LOG_LEVEL_WARNING,
        VESTEL_LOG_LEVEL_DEBUG,
        VESTEL_LOG_LEVEL_INFO,
        VESTEL_LOG_LEVEL_VERBOSE,
        VESTEL_LOG_LEVEL_TOAST
    }

    private static final VestelLogLevel m_default_log_level = isLogEnabled()
                                                            ? VestelLogLevel.VESTEL_LOG_LEVEL_VERBOSE
                                                            : VestelLogLevel.VESTEL_LOG_LEVEL_NONE;

    private static Application m_application = null;
    private static boolean m_file_output_enabled = false;

    /**
     * @brief Checks if log output is enabled for Android applications using VestelLog for output
     * @return True if log output is enabled, false otherwise
     */
    public static boolean isLogEnabled()
    {
        String prop_value = "true";

        boolean is_debug_build = VestelSystemProperties.isDebugBuild();
        boolean is_log_enabled;

        if (is_debug_build)
        {
            is_log_enabled = !(prop_value.equals("false") || prop_value.equals("0"));
        }
        else
        {
            is_log_enabled = (prop_value.equals("true") || prop_value.equals("1"));
        }

        return is_log_enabled;
    }

    /**
     * @brief Returns absolute path of the log file if application logs are written into file
     * @return Absolute path of the log file
     */
    private static String getFileName()
    {
        String usb_root_folder = VestelStorageManager.getUSBRootFolder();
        return (usb_root_folder == null) ? "" : (usb_root_folder + "/log.txt");
    }

    /**
     * @brief Enables or disables writing application logs into file
     * @param flag True to enable writing application logs into file, false to disable
     */
    public static void setFileOutputEnabled(boolean flag)
    {
        m_file_output_enabled = flag;
    }

    /**
     * @brief Sets the application in which trace logs will be displayed as popup messages
     * @param application Android application instance
     */
    public static void setApplication(Application application)
    {
        m_application = application;
    }

    /**
     * @brief Sets the application context
     * @param context Android application context
     */
    public static void setContext(Context context)
    {
        VestelStorageManager.init(context);
    }

    /**
     * @brief Writes Android application log into the log file specified by getFileName() function
     * @param tag Tag defined for Java class in which VestelLog function is called
     * @param text Android application log that will be written into the log file
     */
    private static void writeToFile(String tag, String text)
    {
        File log_file = new File(getFileName());
        boolean file_exists = log_file.exists();

        if (!file_exists)
        {
            try
            {
                file_exists = log_file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (file_exists)
        {
            try
            {
                BufferedWriter buf = new BufferedWriter(new FileWriter(log_file, true));
                buf.append(tag).append(":").append(text);
                buf.newLine();
                buf.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * @brief Checks if default log level prevents outputting logs with specified log level
     * @param level One of the values defined in VestelLogLevel enumeration
     * @return True if default log level prevents outputting logs with specified log level, false otherwise
     */
    private static boolean checkLogLevel(VestelLogLevel level)
    {
        return (m_default_log_level.ordinal() < level.ordinal());
    }

    /**
     * @brief Outputs Android application log as trace message
     * @param tag Tag defined for Java class in which VestelLog function is called
     * @param text Android application log
     */
    public static void trace(String tag, String text)
    {
        if (!checkLogLevel(VestelLogLevel.VESTEL_LOG_LEVEL_TOAST))
        {
            if (m_file_output_enabled)
            {
                writeToFile(tag, text);
            }
            else if (m_application != null)
            {
                Toast.makeText(m_application, tag + ":" + text, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @brief Outputs Android application log as verbose message
     * @param tag Tag defined for Java class in which VestelLog function is called
     * @param text Android application log
     */
    public static void verbose(String tag, String text)
    {
        if (!checkLogLevel(VestelLogLevel.VESTEL_LOG_LEVEL_VERBOSE))
        {
            if (m_file_output_enabled)
            {
                writeToFile(tag, text);
            }
            else
            {
                Log.v(tag, text);
            }
        }
    }

    /**
     * @brief Outputs Android application log as info message
     * @param tag Tag defined for Java class in which VestelLog function is called
     * @param text Android application log
     */
    public static void info(String tag, String text)
    {
        if (!checkLogLevel(VestelLogLevel.VESTEL_LOG_LEVEL_INFO))
        {
            if (m_file_output_enabled)
            {
                writeToFile(tag, text);
            }
            else
            {
                Log.i(tag, text);
            }
        }
    }

    /**
     * @brief Outputs Android application log as debug message
     * @param tag Tag defined for Java class in which VestelLog function is called
     * @param text Android application log
     */
    public static void debug(String tag, String text)
    {
        if (!checkLogLevel(VestelLogLevel.VESTEL_LOG_LEVEL_DEBUG))
        {
            if (m_file_output_enabled)
            {
                writeToFile(tag, text);
            }
            else
            {
                Log.d(tag, text);
            }
        }
    }

    /**
     * @brief Outputs Android application log as error message
     * @param tag Tag defined for Java class in which VestelLog function is called
     * @param text Android application log
     */
    public static void error(String tag, String text)
    {
        if (!checkLogLevel(VestelLogLevel.VESTEL_LOG_LEVEL_ERROR))
        {
            if (m_file_output_enabled)
            {
                writeToFile(tag, text);
            }
            else
            {
                Log.e(tag, text);
            }
        }
    }

    /**
     * @brief Outputs Android application log as warning message
     * @param tag Tag defined for Java class in which VestelLog function is called
     * @param text Android application log
     */
    public static void warning(String tag, String text)
    {
        if (!checkLogLevel(VestelLogLevel.VESTEL_LOG_LEVEL_WARNING))
        {
            if (m_file_output_enabled)
            {
                writeToFile(tag, text);
            }
            else
            {
                Log.e(tag, text);
            }
        }
    }
}
