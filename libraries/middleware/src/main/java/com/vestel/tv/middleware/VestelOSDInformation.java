package com.vestel.tv.middleware;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @brief This class queries the content of OSD plane
 */

public class VestelOSDInformation {
    private static boolean m_volume_osd = false;
    private static Timer m_refresh_timer = null;
    private static TimerTask m_refresh_timer_task = null;


    /**
     * @return The minimum number of pixels which is limited by the resolution of OSD plane,
     * not to be confused with the resolution video plane
     * @brief Returns the minimum number of pixels occupied by UI elements on OSD plane
     * to accept the existence of OSD on screen
     */
    public static int getPixelThreshold() {
        int threshold = 0;
        VestelProjectType project_type = VestelSystemProperties.getProjectType();

        switch (project_type) {
            case COSMOS, MEDUSA, MALIN, HOCKEY, SUNFLOWER ->
                    threshold = (int) (1920 * 1080 * 0.001);
            case COMET, COMA, NEEDLE -> threshold = 5;
        }

        return threshold;
    }

    /**
     * @param pixel_threshold The minimum number of pixels which is limited by the resolution
     *                        of OSD plane, not to be confused with the resolution video plane
     * @brief Sets the minimum number of pixels occupied by UI elements on OSD plane
     * to accept the existence of OSD on screen
     */
    private static void init(int pixel_threshold) {
    }

    /**
     * @return True if there exists OSD on screen, false otherwise
     * @brief Checks the existence of OSD on screen based on the current pixel threshold
     */
    public static boolean isOsdOnScreen() {
        return false;
    }

    /**
     * @brief Notifies the existence of volume bar OSD on screen
     */
    public static void notifyVolumeOSD() {
        m_volume_osd = true;

        if (m_refresh_timer != null) {
            m_refresh_timer.cancel();
            m_refresh_timer = null;
        }

        if (m_refresh_timer_task != null) {
            m_refresh_timer_task.cancel();
            m_refresh_timer_task = null;
        }

        m_refresh_timer = new Timer();
        m_refresh_timer_task = new TimerTask() {
            @Override
            public void run() {
                m_volume_osd = false;
            }
        };

        m_refresh_timer.schedule(m_refresh_timer_task,
                TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS));
    }

    /**
     * @return True if a web browser is being displayed in full screen mode, false otherwise
     * @brief Checks if a web browser is being displayed in full screen mode
     */
    public static boolean isBrowserFullScreen() {
        return false;
    }

    /**
     * @return True if a web browser is being displayed in full screen or windowed mode, false otherwise
     * @brief Checks if a web browser is being displayed in full screen or windowed mode
     */
    public static boolean isBrowserOnScreen() {
        return false;
    }

    /**
     * @return True if MHEG browser is being displayed in full screen or windowed mode, false otherwise
     * @brief Checks if MHEG browser is being displayed in full screen or windowed mode
     */
    public static boolean isMhegOnScreen() {
        return false;
    }
}
