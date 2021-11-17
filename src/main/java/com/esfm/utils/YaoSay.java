package com.esfm.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class YaoSay {
    private static final boolean DEUBG = true;
    public static void p(String str) {
        log.info(str);
    }
    public static void e(Object str) {
        log.error(String.valueOf(str));
    }
    public static void p(Object... str) {
        for (Object s : str
        ) {
            log.info(String.valueOf(s));
        }
    }

    public static void p(List<Object> arrayList) {
        for (Object o : arrayList
        ) {
            log.info(o.toString());
        }
    }

    public static void process(String... str) {
        for (String s : str
        ) {
            log.info(StrUtil.repeat("*", 30 + s.length()));
            log.info(StrUtil.repeat("*", 15) + s + StrUtil.repeat("*", 15));
            log.info(StrUtil.repeat("*", 30 + s.length()));
        }
    }
}
