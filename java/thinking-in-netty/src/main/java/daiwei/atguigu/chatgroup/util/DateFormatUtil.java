package daiwei.atguigu.chatgroup.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Daiwei on 2021/2/21
 */
public class DateFormatUtil {

    private DateFormatUtil() {}

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String nowStr() {
        return format.format(new Date());
    }

}
