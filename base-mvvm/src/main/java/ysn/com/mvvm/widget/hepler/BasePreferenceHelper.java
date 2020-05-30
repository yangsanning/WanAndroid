package ysn.com.mvvm.widget.hepler;

import android.content.SharedPreferences;

/**
 * @Author yangsanning
 * @ClassName BasePreferenceHelper
 * @Description Preference 基类
 * @Date 2020/5/30
 */
public abstract class BasePreferenceHelper {

    protected abstract SharedPreferences getSharePreferences();

    protected void setValue(String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences.Editor editor = getSharePreferences().edit();
        switch (type) {
            case "String":
                editor.putString(key, object.toString());
                break;
            case "Integer":
                editor.putInt(key, (Integer) object);
                break;
            case "Boolean":
                editor.putBoolean(key, (Boolean) object);
                break;
            case "Float":
                editor.putFloat(key, (Float) object);
                break;
            case "Long":
                editor.putLong(key, (Long) object);
                break;
            default:
                break;
        }
        editor.apply();
    }

    protected Object getValue(String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        switch (type) {
            case "String":
                return getSharePreferences().getString(key, (String) defaultObject);
            case "Integer":
                return getSharePreferences().getInt(key, (Integer) defaultObject);
            case "Boolean":
                return getSharePreferences().getBoolean(key, (Boolean) defaultObject);
            case "Float":
                return getSharePreferences().getFloat(key, (Float) defaultObject);
            case "Long":
                return getSharePreferences().getLong(key, (Long) defaultObject);
            default:
                return null;
        }
    }
}
