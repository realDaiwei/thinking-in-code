package io.daiwei;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

import java.text.DecimalFormat;

/**
 * 内存负载
 * Created by Daiwei on 2021/4/22
 */
public class TestMemLoadMain {

    public static void main(String[] args) throws InterruptedException {
        while(true) {
            SystemInfo systemInfo = new SystemInfo();
            GlobalMemory memory = systemInfo.getHardware().getMemory();
            long totalByte = memory.getTotal();
            long availableByte = memory.getAvailable();
            System.out.println(("内存大小 =  "+ formatByte(totalByte) +" 剩余内存" + formatByte(availableByte) +",内存使用率 = " +new DecimalFormat("#.##%").format((totalByte-availableByte)*1.0/totalByte)));
            Thread.sleep(1000);
        }
    }

    public static String formatByte(long byteNumber){
        double FORMAT = 1024.0;
        double kbNumber = byteNumber/FORMAT;
        if(kbNumber<FORMAT){
            return new DecimalFormat("#.##KB").format(kbNumber);
        }
        double mbNumber = kbNumber/FORMAT;
        if(mbNumber<FORMAT){
            return new DecimalFormat("#.##MB").format(mbNumber);
        }
        double gbNumber = mbNumber/FORMAT;
        if(gbNumber<FORMAT){
            return new DecimalFormat("#.##GB").format(gbNumber);
        }
        double tbNumber = gbNumber/FORMAT;
        return new DecimalFormat("#.##TB").format(tbNumber);
    }
}
