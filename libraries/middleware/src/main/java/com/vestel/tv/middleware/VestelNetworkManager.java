package com.vestel.tv.middleware;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;

/**
 * @brief This class manages network interfaces of Android device
 */
public class VestelNetworkManager
{
    private static final String TAG = "VestelNetworkManager";

    private static ConnectivityManager m_connectivity_manager = null;
    private static WifiManager m_wifi_manager = null;

    /**
     * @brief Initializes the class with the given Android application context
     * @param context Android application context
     */
    public static void init(Context context)
    {
        try
        {
            m_connectivity_manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            m_wifi_manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * @brief Returns MAC address of ethernet interface
     * @return MAC address of ethernet interface in "xx:xx:xx:xx:xx:xx" format or
     *         empty string if ethernet interface is not available
     */
    public static String getEthernetMacAddress()
    {
        String mac_address = null;

        try
        {
            NetworkInterface network_interface = NetworkInterface.getByName("eth0");
            if (network_interface != null)
            {
                StringBuilder buffer = new StringBuilder();
                byte[] address = network_interface.getHardwareAddress();

                for (byte b : address)
                {
                    buffer.append(String.format("%02X:", b));
                }

                if (buffer.length() > 0)
                {
                    buffer.deleteCharAt(buffer.length() - 1);
                }

                VestelLog.info(TAG, "eth0: " + buffer);
                mac_address = buffer.toString().toUpperCase(Locale.ROOT);
            }
        }
        catch (SocketException exception)
        {
            VestelLog.debug(TAG, exception.toString());
        }

        if (mac_address == null)
        {
            try
            {
                mac_address = "";
            }
            catch (Exception exception)
            {
                VestelLog.debug(TAG, exception.toString());
            }
        }

        if (mac_address == null)
        {
            mac_address = "";
        }

        return mac_address;
    }

    /**
     * @brief Sets MAC address of ethernet interface permanently
     * @param mac_address MAC address of ethernet interface in "xx:xx:xx:xx:xx:xx" format
     * @return True if MAC address is successfully written to the device, false otherwise
     */
    public static boolean setEthernetMacAddress(String mac_address)
    {
        return false;
    }

    /**
     * @brief Checks if network cable is plugged into ethernet port
     * @return True if network cable is plugged into ethernet port, false otherwise
     */
    public static boolean isEthernetCablePlugged()
    {
        return false;
    }

    /**
     * @brief Returns the board name of wi-fi interface
     * @return The board name of wi-fi interface if available or empty string otherwise
     */
    public static String getWifiBoardName()
    {
        return "";
    }

    /**
     * @brief Returns MAC address of wi-fi interface
     * @return MAC address of wi-fi interface in "xx:xx:xx:xx:xx:xx" format or
     *         empty string if wi-fi interface is not available
     */
    public static String getWifiMacAddress()
    {
        return "";
    }

    /**
     * @brief Returns MAC address of wi-fi interface
     * @return MAC address of wi-fi interface in "xx:xx:xx:xx:xx:xx" format or
     *         empty string if wi-fi interface is not available
     */
    public static String getWifiMacAddress2()
    {
        return "";
    }

    /**
     * @brief Sets MAC address of wi-fi interface permanently
     * @param mac_address MAC address of wi-fi interface in "xx:xx:xx:xx:xx:xx" format
     * @return True if MAC address is successfully written to the device, false otherwise
     */
    public static boolean setWifiMacAddress(String mac_address)
    {
        return false;
    }

    /**
     * @brief Scans for wireless networks and gets SSID of wireles network with highest signal strength
     * @return SSID of wireles network in "<SSID> "<signal strength> dB" format if available
     */
    public static String getWifiDongle()
    {
        return "";
    }

    /**
     * @brief Returns IPV4 address of the network interface currently connected
     * @return IPV4 address in "x.x.x.x" format if the device has connected to
     *         a network interface and obtained IP address, "0.0.0.0" otherwise
     */
    @SuppressWarnings("MethodWithMultipleReturnPoints")
    public static String getIPAddress()
    {
        return "";
    }

    public static boolean isWakeOnLanEnabled()
    {
        return false;
    }

    public static void setWakeOnLanEnabled(boolean enabled)
    {
    }

    public static boolean isWakeOnWlanEnabled()
    {
        return false;
    }

    public static void setWakeOnWlanEnabled(boolean enabled)
    {
    }

    public static boolean isWakeOnChromecastEnabled()
    {
        return false;
    }

    public static void setWakeOnChromecastEnabled(boolean enabled)
    {
    }
}

