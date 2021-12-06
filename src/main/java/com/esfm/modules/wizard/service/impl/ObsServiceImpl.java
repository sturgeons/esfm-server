package com.esfm.modules.wizard.service.impl;

import com.esfm.extension.api.R;
import com.esfm.modules.wizard.entity.bo.HuaweiAuthProperties;
import com.esfm.modules.wizard.entity.bo.ObsBo;
import com.esfm.modules.wizard.service.ObsService;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


@Service("obsService")
public class ObsServiceImpl implements ObsService {

    @Resource
    private HuaweiAuthProperties huaweiAuthProperties;

    @Override
    public R<ObsBo> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return R.failed("获取文件名错误");
        }
        String sub = originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                return R.failed("文件内容不合法" + originalFilename);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return R.failed("获取文件流失败");
        }
        //上传图片
        InputStream in = null;
        ObsClient obsClient = null;
        try {
            //获取图片名称，作为上传文件名参数
            String objectKey = file.getOriginalFilename();
            //获取流对象
            in = file.getInputStream();
            String md5Filename = DigestUtils.md5Hex(in) + sub;
            in = file.getInputStream();
            // 创建ObsClient实例
            obsClient = new ObsClient(huaweiAuthProperties.getAk(), huaweiAuthProperties.getSk(), huaweiAuthProperties.getEndpoint());
            // 使用访问OBS
            PutObjectResult putObjectResult = obsClient.putObject("esfm", md5Filename, in);
            //将图片信息封装起来，方便前端回显调用
            String url = putObjectResult.getObjectUrl();
            ObsBo obsBo = new ObsBo();
            obsBo.setFilename(originalFilename);
            obsBo.setMd5Filename(putObjectResult.getObjectKey());
            obsBo.setUrl(url.replace("https://esfm.obs.cn-south-1.myhuaweicloud.com:443","http://obs.zf-ap.com"));
            return R.ok(obsBo);

        } catch (IOException e) {
            e.printStackTrace();
            return R.failed("上传图片失败");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("输出流关闭失败！");
            }
            try {
                if (obsClient != null) {
                    // 关闭obsClient
                    obsClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("obs客户端流对象关闭失败！");
            }

        }
    }

}
