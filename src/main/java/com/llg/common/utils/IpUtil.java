package com.llg.common.utils;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
    public static String client(HttpServletRequest request) {
        String xff = request.getHeader("x-forwarded-for");
        if (xff == null) {
            xff = request.getRemoteAddr();
        }
        return xff;
    }
}
