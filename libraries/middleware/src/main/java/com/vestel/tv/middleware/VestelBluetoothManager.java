package com.vestel.tv.middleware;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * @brief This class manages bluetooth interface of Android device
 */
public class VestelBluetoothManager
{
    private static final String TAG = "VestelBluetoothManager";

    private static Context m_context = null;
    private static BluetoothManager m_bluetooth_manager = null;

    private static int temp_rssi;
    private static String m_bluetooth_device_info = "";

    /**
     * @brief Initializes the class with the given Android application context
     * @param context Android application context
     */
    public static void init(Context context)
    {
        try
        {
            m_context = context;
            m_bluetooth_manager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * @brief Returns MAC address of bluetooth interface
     * @return MAC address of bluetooth interface in "xx:xx:xx:xx:xx:xx" format or
     *         empty string if bluetooth interface is not available
     */
    public static String getBluetoothMacAddress()
    {
        String mac_address = null;

        if (m_bluetooth_manager != null)
        {
            BluetoothAdapter adapter = m_bluetooth_manager.getAdapter();

            if (adapter != null)
            {
                mac_address = adapter.getAddress().toUpperCase();
                VestelLog.info(TAG, "bluetooth: " + mac_address);
            }
        }

        if (mac_address == null)
        {
            mac_address = "";
        }

        return mac_address;
    }

    /**
     * @brief Sets MAC address of bluetooth interface permanently
     * @param mac_address MAC address of bluetooth interface in "xx:xx:xx:xx:xx:xx" format
     * @return True if MAC address is successfully written to the device, false otherwise
     */
    public static boolean setBluetoothMacAddress(String mac_address)
    {
        return false;
    }

    public static void checkBluetoothDevice()
    {
    }

    public static String getBluetoothDevice()
    {
        return m_bluetooth_device_info;
    }
}

