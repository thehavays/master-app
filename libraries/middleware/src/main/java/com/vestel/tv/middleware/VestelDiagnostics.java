package com.vestel.tv.middleware;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;

public class VestelDiagnostics
{
    private static final String TAG = "VestelDiagnostics";

    private static Context m_context = null;

    /**
     * @brief Initializes the class with the given Android application context
     * @param context Android application context
     */
    public static void init(Context context)
    {
        m_context = context;
    }

    public static void displayMessage(int resource_id)
    {
        if (m_context != null)
        {
            Toast.makeText(m_context, resource_id, Toast.LENGTH_SHORT).show();
        }
    }

    public static void displayMessage(String text)
    {
        if (m_context != null)
        {
            Toast.makeText(m_context, text, Toast.LENGTH_SHORT).show();
        }
    }

    public static int getUartState()
    {
        if (m_context != null)
        {
            SharedPreferences pref = m_context.getSharedPreferences("shared", Context.MODE_PRIVATE);
            return pref.getInt("testTool", 0);
        }
        return 0;
    }

    public static void setUartState(int uart_state)
    {
        if (m_context != null)
        {
            boolean bOnOff;
            boolean lOnOff;

            if (uart_state == 0)
            {
                bOnOff = true;
                lOnOff = true;
            }
            else if (uart_state == 1)
            {
                bOnOff = false;
                lOnOff = false;
            }
            else
            {
                bOnOff = true;
                lOnOff = false;
            }

            SharedPreferences pref = m_context.getSharedPreferences("shared", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("testTool", uart_state);
            editor.commit();

            Intent intent = new Intent();
            intent.setAction("android.mtk.intent.action.UART_SETTINGS_CHANGED");
            intent.putExtra("com.mediatek.tv.factory.EXTRA_SWITCH_ON_OFF", bOnOff);
            intent.putExtra("com.vestel.factory.LISTENER_ON_OFF", lOnOff);
            m_context.sendBroadcast(intent);
        }
    }

    public static boolean readFromIic(int iic_device_id, ArrayList<Byte> iic_addr, short size, ArrayList<Byte> get_data)
    {
        return false;
    }

    public static boolean writeToIic(int iic_device_id, ArrayList<Byte> iic_addr, short size, ArrayList<Byte> set_data)
    {
        return false;
    }
}

