package com.piu130.reactnativelocalnotification;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;

public class NotificationStorage {
    private static final String PREFERENCES_KEY = "react_native_local_notification";

    private final SharedPreferences sharedPreferences;

    public static NotificationStorage fromContext(Context context) {
        return new NotificationStorage(context.getSharedPreferences(NotificationStorage.PREFERENCES_KEY, Context.MODE_PRIVATE));
    }

    private NotificationStorage(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void store(int id, Bundle bundle) {
        Parcel p = Parcel.obtain();
        bundle.writeToParcel(p, 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(String.valueOf(id), Base64.getEncoder().encodeToString(p.marshall()));
        editor.commit();
    }

    public Map<Integer, Bundle> getAll() {
        Map<Integer, Bundle> map = new HashMap<>();
        for(Map.Entry<String, String> entry : ((Map<String, String>)sharedPreferences.getAll()).entrySet()) {
            map.put(
                Integer.parseInt(entry.getKey()),
                stringToBundle(entry.getValue().toString())
            );
        }
        return map;
        //Map<String, String> map = (Map<String, String>)sharedPreferences.getAll();
        // Function<Map.Entry<String, String>, Integer> keyToString = x -> Integer.parseInt(x.getKey());
        // Function<Map.Entry<String, String>, Bundle> valueToBundle = x -> stringToBundle(x.getValue().toString());
        // return sharedPreferences.getAll().entrySet().stream().collect(Collectors.toMap(keyToString, valueToBundle));
    }

    public Bundle getById(int id) {
        return stringToBundle(sharedPreferences.getString(String.valueOf(id), ""));
    }

    public void remove(int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(String.valueOf(id));
        editor.commit();
    }

    private Bundle stringToBundle(String string) {
        byte[] bytes = Base64.getDecoder().decode(string);

        Parcel p = Parcel.obtain();
        p.unmarshall(bytes, 0, bytes.length);
        p.setDataPosition(0);

        Bundle bundle = new Bundle();
        bundle.readFromParcel(p);
        return bundle;
    }
}
