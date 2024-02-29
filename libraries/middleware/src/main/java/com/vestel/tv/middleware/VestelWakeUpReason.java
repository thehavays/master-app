package com.vestel.tv.middleware;

import androidx.annotation.NonNull;

public enum VestelWakeUpReason {
    UNKNOWN("Unknown"),
    VGA("VGA"),
    RTC("RTC"),
    FP("FP"),
    IIRC("IIRC"),
    UART("UART"),
    AC_POWER("AC power"),
    HDMI("HDMI"),
    UART_NORMAL("UART normal"),
    RC_DIGIT_0("RC digit 0"),
    RC_DIGIT_1("RC digit 1"),
    RC_DIGIT_2("RC digit 2"),
    RC_DIGIT_3("RC digit 3"),
    RC_DIGIT_4("RC digit 4"),
    RC_DIGIT_5("RC digit 5"),
    RC_DIGIT_6("RC digit 6"),
    RC_DIGIT_7("RC digit 7"),
    RC_DIGIT_8("RC digit 8"),
    RC_DIGIT_9("RC digit 9"),
    RC_PROGRAM_UP("RC program up"),
    RC_PROGRAM_DOWN("RC program down"),
    RC_INPUT_SOURCE("RC input source"),
    RC_ANALOG("RC analog"),
    RC_DIGITAL("RC digital"),
    RC_DIGITAL_ANALOG("RC digital/analog"),
    FP_PROGRAM_UP("FP program up"),
    FP_PROGRAM_DOWN("FP program down"),
    FP_INPUT_SOURCE("FP input source"),
    DVD("DVD"),
    RTC_SPECIAL("RTC special"),
    WATCHDOG("WatchDog"),
    SCART("SCART"),
    ETHERNET("Ethernet"),
    RESUME_FROM_SUSPEND("Resume from Suspend"),
    WIFI("WiFi"),
    BLUETOOTH("Bluetooth");

    private final String name;

    VestelWakeUpReason(final String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

}
