package com.zf.zf_server.modules.deliveryCheck.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.deliveryCheck.entity.DeliveryCheck;
import com.zf.zf_server.modules.deliveryCheck.entity.vo.RequestImgVO;
import com.zf.zf_server.modules.deliveryCheck.service.DeliveryCheckService;
import com.zf.zf_server.utils.YaoSay;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.List;

/**
 * Vqe主积分表
 * (DeliveryCheck)表控制层
 *
 * @author makejava
 * @since 2021-09-01 14:55:44
 */
@RestController
@RequestMapping("deliveryCheck")
public class DeliveryCheckController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private DeliveryCheckService deliveryCheckService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<DeliveryCheck> page, DeliveryCheck deliveryCheck) {
        return success(this.deliveryCheckService.page(page, new QueryWrapper<>(deliveryCheck)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.deliveryCheckService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody DeliveryCheck deliveryCheck) {
        return success(this.deliveryCheckService.save(deliveryCheck));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody DeliveryCheck deliveryCheck) {
        return success(this.deliveryCheckService.updateById(deliveryCheck));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.deliveryCheckService.removeByIds(idList));
    }

    /**
     * 上传照片
     */
    @PostMapping(value = "uploadPicture")
    public R<?> uploadPicture(MultipartHttpServletRequest request) {
        MultipartFile files = request.getFile("file");
        return this.deliveryCheckService.uploadPicture(files);
    }

    /**
     * base64图片分析数据
     **/
    @PostMapping(value = "analysisImage")
    public R<?> analysisImage(@RequestBody RequestImgVO imgStr) {
//        YaoSay.p(imgStr.getImgStr().replace("data:image/jpeg;base64,", ""));
        return this.deliveryCheckService.analysisImage(imgStr.getImgStr().replace("data:image/jpeg;base64,", ""));
    }
}