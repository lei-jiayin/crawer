package com.xw.crawer.commons.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 截取特殊字符中的特殊字段
 * @author xw
 * @date 2019/6/24 12:01
 */
public class SubString {
    /**
     * str 中每个字符的间隔形如"长沙-天心区    10年以上经验    大专    招1人    06-24发布"
     * @param str 地址要求
     * @return list
     */
    public static List<String> subString(String str){
        String replace = str.trim().replaceAll("    ", ",");
        String[] ts = replace.split(",");
        List<String> tss = Arrays.asList(ts);
        return tss;
    }

    /**
     * 获取发布时间
     * @param str 要求字符
     * @return 时间字符
     */
    public static String getPubTime(String str){
        List<String> strs = subString(str);
        String s = "未设置发布时间";
        for (String a:strs) {
            if (a.contains("发布")) {
                s = a.substring(0, a.length() - 2);
            }
        }
        return s;
    }

    // 目标  长沙-天心区    10年以上经验    大专    招1人    06-24发布
    public static void main(String[] args) {
        List<String> s = subString("长沙-天心区    10年以上经验    大专    招1人    06-24发布");
        for (String a:s) {
            System.out.println(a);
        }

        String pubTime = getPubTime("长沙-天心区    10年以上经验    大专    招1人    06-24发布");
        System.out.println(pubTime);
    }

}
