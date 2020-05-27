package ysn.com.mvvm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author yangsanning
 * @ClassName TimeUtils
 * @Description 一句话概括作用
 * @Date 2020/5/27
 */
public class TimeUtils {

    public static final String TODAY_FORMAT = "yyyy-MM-dd";

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String FIVE_MINUTE_AGO = "刚刚";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_YEAR_AGO = "年前";

    public static String formatLong2Today(long time) {
        long delta = System.currentTimeMillis() - time;

        if (delta < 5L * ONE_MINUTE) {
            return FIVE_MINUTE_AGO;
        }

        if (delta < 60L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }

        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }

        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }

        return long2Today(time);
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    public static String long2Today(long timestamp) {
        return new SimpleDateFormat(TODAY_FORMAT, Locale.getDefault()).format(new Date(timestamp));
    }
}
