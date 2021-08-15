package com.zf.zf_server.utils.excel;


import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class ExportXls<T> {

    public ExportXls(String fileName, String fileTitle, String operator) {
        this.fileName = fileName;
        this.fileTitle = fileTitle;
        this.operator = operator;
    }

    private T clazz;
    private String fileName="Export Data";
    private String fileTitle="Export Data";
    private String operator="anonymity";



    public  ModelAndView export(List<T> data,Class clz){
        ModelAndView modelAndView=new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, fileName);
        modelAndView.addObject(NormalExcelConstants.CLASS,clz);
        modelAndView.addObject(NormalExcelConstants.PARAMS, new ExportParams(fileTitle, operator, "Data"));
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, data);
        return modelAndView;
    }


}
