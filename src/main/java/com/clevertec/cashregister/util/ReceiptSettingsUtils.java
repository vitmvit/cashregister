package com.clevertec.cashregister.util;

import com.clevertec.cashregister.pattern.singleton.SettingsSingleton;

public class ReceiptSettingsUtils {

    public static String getTitle() {
        return SettingsSingleton.getInstance(null).getSettings().getOrDefault("title", "Please add receipt title");
    }

    public static String getStore() {
        return SettingsSingleton.getInstance(null).getSettings().getOrDefault("store", "Please add store");
    }

    public static String getAddress() {
        return SettingsSingleton.getInstance(null).getSettings().getOrDefault("address", "Please add address");
    }

    public static String getPhone() {
        return SettingsSingleton.getInstance(null).getSettings().getOrDefault("phone", "Please add phone");
    }
}
