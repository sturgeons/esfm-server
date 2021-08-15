package com.zf.zf_server.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public  class YaoSay {
    private static boolean DEUBG=true;
    public static void p(String str){
        log.info(str);
    }
    public static void p(String... str){
        for (String s:str
             ) {
            log.info(s);
        }
    }
    public static void p(ArrayList arrayList){
        for (Object o:arrayList
             ) {
            log.info(o.toString());
        }
    }

    public static void process(String... str){
        for (String s:str
        ) {
            log.info(StrUtil.repeat("*",30+s.length()));
            log.info(StrUtil.repeat("*",15)+s+StrUtil.repeat("*",15));
            log.info(StrUtil.repeat("*",30+s.length()));
        }
    }
}
