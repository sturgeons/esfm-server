package com.zf.zf_server.modules.deliveryCheck.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.modules.deliveryCheck.dao.DeliveryCheckDao;
import com.zf.zf_server.modules.deliveryCheck.entity.DeliveryCheck;
import com.zf.zf_server.modules.deliveryCheck.service.DeliveryCheckService;
import com.zf.zf_server.modules.wizard.entity.bo.ObsBo;
import com.zf.zf_server.modules.wizard.service.HuaweiOcrService;
import com.zf.zf_server.modules.wizard.service.ObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Vqe主积分表
 * (DeliveryCheck)表服务实现类
 *
 * @author makejava
 * @since 2021-09-01 14:55:44
 */
@Service("deliveryCheckService")
public class DeliveryCheckServiceImpl extends ServiceImpl<DeliveryCheckDao, DeliveryCheck> implements DeliveryCheckService {

    @Autowired
    private ObsService obsService;
    @Autowired
    private HuaweiOcrService huaweiOcrService;

    @Override
    public R<?> uploadPicture(MultipartFile files) {
        var obsRes = obsService.upload(files);
        if (obsRes.ok()) {
            return huaweiOcrService.getOcr(((ObsBo) obsRes.getData()).getUrl());
        }
//        try {
//            File file = new File("/Users/yaoxin/1.jpg");
//            FileUtils.copyInputStreamToFile(files.getInputStream(), file);
//
//            ITesseract it = new Tesseract();
//            it.setLanguage("eng");
//            it.setDatapath("/Users/yaoxin/tessdata/");
////            iTesseract.setDatapath("./tessdata");
//
//            it.setOcrEngineMode(2);
//
//            BufferedImage image = ImageIO.read(files.getInputStream());
//            System.out.println(it.doOCR(image));
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }

        return R.failed(ResponseInfoConstant.OPERATE_FAIL);
    }

    @Override
    public R<?> analysisImage(String imgStr) {
        return huaweiOcrService.getOcrBase64(imgStr);
    }
}