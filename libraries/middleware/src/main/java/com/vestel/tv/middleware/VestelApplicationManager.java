package com.vestel.tv.middleware;

import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;

/**
 * @brief This class queries the user interface application that is displayed at foreground
 */
public class VestelApplicationManager
{
    private static final String TAG = "VestelApplicationManager";

    private static ActivityManager m_activity_manager = null;

    private static final String LIVE_TV_PACKAGE_NAME = "com.mediatek.wwtv.tvcenter";
    private static final String TV_SETTINGS_PACKAGE_NAME = "com.android.tv.settings";
    private static final String ANDROID_TV_HOME_PACKAGE_NAME = "com.google.android.tvlauncher";
    private static final String GOOGLE_TV_HOME_PACKAGE_NAME = "com.google.android.apps.tv.launcherx";

    /**
     * @brief Initializes the class with the given Android application context
     * @param context Android application context
     */
    public static void init(Context context)
    {
        VestelLog.info(TAG, "init");
        m_activity_manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }

    /**
     * @brief Checks whether the user interface of live TV application is displayed at foreground
     * @return True if the user interface of live TV application is displayed at foreground, false otherwise
     */
    public static boolean isInLiveTV()
    {
        boolean is_in_live_tv = false;

        if (m_activity_manager != null)
        {
            List<ActivityManager.RunningTaskInfo> tasks = m_activity_manager.getRunningTasks(1);

            if (!tasks.isEmpty())
            {
                String componentName = tasks.get(0).topActivity.getPackageName();
                is_in_live_tv = (componentName.equals(LIVE_TV_PACKAGE_NAME));
            }
        }

        return is_in_live_tv;
    }

    /**
     * @brief Checks whether the user interface of TV settings application is displayed at foreground
     * @return True if the user interface of TV settings application is displayed at foreground, false otherwise
     */
    public static boolean isInTVSettings()
    {
        boolean is_in_tv_settings = false;

        if (m_activity_manager != null)
        {
            List<ActivityManager.RunningTaskInfo> tasks = m_activity_manager.getRunningTasks(1);

            if (!tasks.isEmpty())
            {
                String componentName = tasks.get(0).topActivity.getPackageName();
                is_in_tv_settings = (componentName.equals(TV_SETTINGS_PACKAGE_NAME));
            }
        }

        return is_in_tv_settings;
    }

    /**
     * @brief Return package name of the application that is displayed at foreground
     * @return Package name of the application that is displayed at foreground
     */
    public static String getApplicationNameOnTop()
    {
        String name = "";

        if (m_activity_manager != null)
        {
            List<ActivityManager.RunningTaskInfo> tasks = m_activity_manager.getRunningTasks(1);

            if (!tasks.isEmpty())
            {
                name = tasks.get(0).topActivity.getPackageName();
            }
        }

        return name;
    }

    /**
     * @brief Gets the type of the user interface application that is displayed at foreground
     * @return APPLICATION_LIVE_TV if live TV application is displayed at foreground,
     *         APPLICATION_TV_SETTINGS if TV settings application is displayed at foreground,
     *         APPLICATION_HOME_LAUNCHER if home launcher menu is displayed at foreground,
     *         APPLICATION_OTHER otherwise
     */
    public static VestelApplicationType getApplicationOnTop()
    {
        VestelApplicationType type = VestelApplicationType.APPLICATION_OTHER;

        if (m_activity_manager != null)
        {
            List<ActivityManager.RunningTaskInfo> tasks = m_activity_manager.getRunningTasks(1);

            if (!tasks.isEmpty())
            {
                String componentName = tasks.get(0).topActivity.getPackageName();

                switch (componentName) {

                    case LIVE_TV_PACKAGE_NAME:
                        type = VestelApplicationType.APPLICATION_LIVE_TV;
                        break;

                    case TV_SETTINGS_PACKAGE_NAME:
                        type = VestelApplicationType.APPLICATION_TV_SETTINGS;
                        break;

                    case ANDROID_TV_HOME_PACKAGE_NAME:
                    case GOOGLE_TV_HOME_PACKAGE_NAME:
                        type = VestelApplicationType.APPLICATION_HOME_LAUNCHER;
                        break;

                    default:
                        break;
                }
            }
        }

        return type;
    }

    public static float performSystemCleanUp()
    {
        float available_memory = 0.0f;

        if (m_activity_manager != null)
        {
            ActivityManager.MemoryInfo memory_info = new ActivityManager.MemoryInfo();

            m_activity_manager.getMemoryInfo(memory_info);
            float old_available_memory = (float) memory_info.availMem;

            for (ActivityManager.RunningAppProcessInfo next : m_activity_manager.getRunningAppProcesses())
            {
                String process_name = next.processName;

                VestelLog.info(TAG, "process = " + process_name + " - importance: " + next.importance);
                VestelLog.info(TAG, "Killing process...");

                m_activity_manager.killBackgroundProcesses(process_name);
            }

            m_activity_manager.getMemoryInfo(memory_info);
            float new_available_memory = (float) memory_info.availMem;

            if (new_available_memory > old_available_memory)
            {
                available_memory = (new_available_memory - old_available_memory) / 1048576.0f;
            }
        }

        return available_memory;
    }
}

