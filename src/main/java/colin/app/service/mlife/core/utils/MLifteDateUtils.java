package colin.app.service.mlife.core.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/8/26.
 */
public class MLifteDateUtils {
    /**
     * g获取当前的日期
     * @return
     */
    public static String getCurrentDate(){
        return DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss");
    }
}
