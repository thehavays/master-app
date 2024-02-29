package com.vestel.tv.middleware;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.storage.StorageManager;

import java.util.Iterator;
import java.util.Map;

/**
 * @brief This class manages storage units connected to USB ports of Android device
 */
public class VestelStorageManager {
    private static final String TAG = "VestelStorageManager";

    private static UsbManager m_usb_manager = null;
    private static StorageManager m_storage_manager = null;

    /**
     * @param context Android application context
     * @brief Initializes the class with the given Android application context
     */
    public static void init(Context context) {
        try {
            m_usb_manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
            m_storage_manager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * @return The number of connected USB devices
     * @brief Returns the number of connected USB devices
     */
    public static int getConnectedUsbCount() {
        int usb_count = 0;

        if (m_usb_manager != null) {
            try {
                Map<String, UsbDevice> usb_device_list = m_usb_manager.getDeviceList();

                Iterator<String> iterator = usb_device_list.keySet().iterator();
                int count = 0;

                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String value = usb_device_list.get(key).toString();

                    if (value.contains("mProductName=Wireless_Device")) {
                        count++;
                    }
                }

                usb_count = usb_device_list.size() - count;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        VestelLog.info(TAG, "USB count: " + usb_count);
        return usb_count;
    }

    /**
     * @return The absolute path of root folder of USB storage device or null if no USB stroage device is connected
     * @brief Returns the absolute path of root folder of USB storage device
     */
    public static String getUSBRootFolder() {
        return null;
    }
}
