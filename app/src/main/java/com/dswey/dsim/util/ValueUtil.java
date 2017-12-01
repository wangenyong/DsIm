package com.dswey.dsim.util;

/**
 *
 * @author wangenyong
 * @date 2017/12/1
 */

public class ValueUtil {
    /**
     * 判断字符串是否为空，Null或空白字符串均返回true
     * Author: hyl
     * Time: 2015-8-13下午1:18:26
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        if(value == null) {
            return true;
        }
        if(value.trim().length() == 0) {
            return true;
        }
        return false;
    }
}
