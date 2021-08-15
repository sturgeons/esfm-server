package com.zf.zf_server.modules.lpa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.modules.lpa.dao.LpaPictureDao;
import com.zf.zf_server.modules.lpa.entity.LpaPicture;
import com.zf.zf_server.modules.lpa.service.LpaPictureService;
import com.zf.zf_server.modules.wizard.entity.bo.ObsBo;
import com.zf.zf_server.modules.wizard.service.ObsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 分层审核-图片(LpaPicture)表服务实现类
 * @author yaoxin
 * @since 2020-07-30 11:29:03
 */
@Service("lpaPictureService")
public class LpaPictureServiceImpl extends ServiceImpl<LpaPictureDao, LpaPicture> implements LpaPictureService {

    @Resource
    private ObsService obsService;

    @Override
    public R<?> uploadPicture(MultipartFile file, LpaPicture lpaPicture) {

        R<?>res=obsService.upload(file);
        if (res.ok()){
            ObsBo data= (ObsBo) res.getData();

            lpaPicture.setUrl(data.getUrl());
            int resCount=this.baseMapper.insert(lpaPicture);
            if (resCount>=1){
                return R.ok(lpaPicture);
            }else{
                return R.failed(ResponseInfoConstant.OPERATE_FAIL);
            }
        }
        return  res;



//
//        String fileName = file.getOriginalFilename();
//        String suffixName = fileName != null ? fileName.substring(fileName.lastIndexOf(".")) : null;
//        String filePath = "/users/yaoxin/esfm/";
//        fileName = UUID.randomUUID() + suffixName;
//        File dest = new File(filePath + fileName);
//        if (!dest.getParentFile().exists()) {
//            if (!dest.getParentFile().mkdirs()) {
//                return R.failed("图片上传错误-权限错误，无法创建文件存储路径");
//            }
//        }
//        try {
//            file.transferTo(dest);
//            //文件存储成功
//            lpaPicture.setUrl("/esfm/" + fileName);
//            this.baseMapper.insert(lpaPicture);
//            lpaPicture.setUrl(lpaPicture.getUrl());
//            return R.ok(lpaPicture);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return R.failed("图片上传失败！");
//        }
    }
}
