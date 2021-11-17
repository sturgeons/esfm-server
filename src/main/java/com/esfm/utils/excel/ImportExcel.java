package com.esfm.utils.excel;

import com.baomidou.mybatisplus.extension.service.IService;
import com.esfm.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class ImportExcel<T> {
    T clazz;

    public R<?> importData(HttpServletRequest request, Class<?> clazz, IService<T> iService) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<T> listJoaDemos = ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
                for (T joaDemoExcel : listJoaDemos) {
                    iService.save(joaDemoExcel);
                }
                return R.ok("文件导入成功！数据行数:" + listJoaDemos.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return R.failed("文件导入失败:" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return R.ok("文件导入失败！");
    }
}
