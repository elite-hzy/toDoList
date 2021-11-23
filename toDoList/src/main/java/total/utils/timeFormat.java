package total.utils;

import java.util.Calendar;
import java.util.Date;

public class timeFormat {
    //这里是用来转化时间的

    public static void main(String[] args) {
        formatTime();
        //这里的任务就是先提取到时间然后转化为毫秒或秒来对比
    }
    public static void formatTime(){
        Date date = new Date();
        //date.getTime可以轻松获取到毫秒值
        long time = date.getTime();
        System.out.println("date.getTime得到毫秒"+time);
        //calendar是日历对象,不能直接new一个,这个默认是今日的时间
        Calendar c = Calendar.getInstance();
        //转化为毫秒
        long timeInMillis = c.getTimeInMillis();
        System.out.println("Calendar.getInstance()"+c);
        System.out.println("毫秒"+timeInMillis);
    }
}
