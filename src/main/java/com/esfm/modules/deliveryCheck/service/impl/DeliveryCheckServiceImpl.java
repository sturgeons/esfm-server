package com.esfm.modules.deliveryCheck.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.extension.api.R;
import com.esfm.extension.constant.ResponseInfoConstant;
import com.esfm.modules.deliveryCheck.dao.DeliveryCheckDao;
import com.esfm.modules.deliveryCheck.service.DeliveryCheckService;
import com.esfm.modules.wizard.entity.bo.ObsBo;
import com.esfm.modules.wizard.service.HuaweiOcrService;
import com.esfm.modules.wizard.service.ObsService;
import com.esfm.modules.deliveryCheck.entity.DeliveryCheck;
import com.esfm.modules.deliveryCheck.entity.DeliveryCheckPicture;
import com.esfm.modules.deliveryCheck.service.DeliveryCheckPictureService;

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
    @Autowired
    private DeliveryCheckPictureService deliveryCheckPictureService;

    @Override
    public R<?> uploadPicture(MultipartFile files) {
        var obsRes = obsService.upload(files);
        if (obsRes.ok()) {
            ObsBo ObsData = (ObsBo) obsRes.getData();
            DeliveryCheckPicture deliveryCheckPicture = new DeliveryCheckPicture();
            deliveryCheckPicture.setUrl(ObsData.getUrl());
            deliveryCheckPictureService.save(deliveryCheckPicture);
            R<?> ocrRes = huaweiOcrService.getOcr((ObsData).getUrl());
            if (ocrRes.ok()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("urlId", deliveryCheckPicture.getId());
                jsonObject.put("data", ocrRes.getData());
                return R.ok(jsonObject);
            } else {
                return ocrRes;
            }
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