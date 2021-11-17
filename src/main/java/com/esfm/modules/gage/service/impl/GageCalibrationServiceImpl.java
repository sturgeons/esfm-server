package com.esfm.modules.gage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esfm.modules.gage.dao.GageCalibrationDao;
import com.esfm.modules.gage.entity.GageCalibration;
import com.esfm.modules.gage.service.GageCalibrationService;
import org.springframework.stereotype.Service;

/**
 * 检具校准记录(GageCalibration)表服务实现类
 *
 * @author makejava
 * @since 2021-10-24 21:18:50
 */
@Service("gageCalibrationService")
public class GageCalibrationServiceImpl extends ServiceImpl<GageCalibrationDao, GageCalibration> implements GageCalibrationService {

}
