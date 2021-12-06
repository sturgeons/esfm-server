package com.esfm.modules.system.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.modules.system.entity.SysFile;
import com.esfm.modules.system.service.SysFileService;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 文件存储(SysFile)表控制层
 *
 * @author yaoxin
 * @since 2021-11-30 14:38:20
 */
@RestController
@RequestMapping("sysFile")
public class SysFileController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private SysFileService sysFileService;

    /**
     * 分页查询所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<SysFile> page, SysFile sysFile) {
        return success(this.sysFileService.page(page, new QueryWrapper<>(sysFile)));
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.sysFileService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody SysFile sysFile) {
        return success(this.sysFileService.save(sysFile));
    }

    /**
     * 修改数据
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody SysFile sysFile) {
        return success(this.sysFileService.updateById(sysFile));
    }

    /**
     * 删除数据
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.sysFileService.removeByIds(idList));
    }
    //提交附件
    @PostMapping(value = "updatePicture")
    public R<?> updatePicture(HttpServletRequest request){
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        return  this.sysFileService.updatePicture(file);
    }
}