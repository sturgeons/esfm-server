package com.esfm.modules.gage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.gage.dao.GageCalibrationFileDao;
import com.esfm.modules.gage.entity.GageCalibrationFile;
import com.esfm.modules.gage.service.GageCalibrationFileService;
import org.springframework.stereotype.Service;

/**
 * 检具校准附件(GageCalibrationFile)表服务实现类
 *
 * @author makejava
 * @since 2021-10-24 21:19:10
 */
@Service("gageCalibrationFileService")
public class GageCalibrationFileServiceImpl extends ServiceImpl<GageCalibrationFileDao, GageCalibrationFile> implements GageCalibrationFileService {

}
