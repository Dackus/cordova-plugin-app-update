package com.vaenow.appupdate.android;

import android.content.res.Resources;

import org.json.JSONObject;
import org.json.JSONException;

/**
 * Created by dackus.it on 28/10/2021.
 */
public final class PluginOptions {
    private static JSONObject options;

    private PluginOptions() { }

    public static void setOptions(JSONObject newOptions) {
        options = newOptions;
    }

    public static String getString(String name) throws JSONException {
        if (options == null) {
            throw new JSONException("options not set");
        }

        return options.getString(name);
    }

    public static String getMessage(String name) throws JSONException {
        if (options == null) {
            throw new JSONException("options not set");
        }

        return options
            .getJSONObject("messages")
            .getString(name);
    }

    public Boolean getBoolean(String name) throws JSONException {
        if (options == null) {
            throw new JSONException("options not set");
        }

        return options.getBoolean(name);
    }
}
