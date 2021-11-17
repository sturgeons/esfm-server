package com.esfm.modules.lpa.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esfm.extension.api.ApiController;
import com.esfm.extension.api.R;
import com.esfm.modules.lpa.entity.LpaPicture;
import com.esfm.modules.lpa.service.LpaPictureService;
import com.esfm.utils.excel.ExportXls;
import com.esfm.utils.excel.ImportExcel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * 分层审核-图片(LpaPicture)表控制层
 *
 * @author yaoxin
 * @since 2020-07-30 11:29:03
 */
@RestController
@RequestMapping("lpaPicture/")
public class LpaPictureController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LpaPictureService lpaPictureService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param lpaPicture 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<LpaPicture> page, LpaPicture lpaPicture) {
        var res = this.lpaPictureService.page(page, new QueryWrapper<>(lpaPicture));
        res.getRecords().forEach(r -> r.setUrl(r.getUrl()));
        return success(res);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.lpaPictureService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param lpaPicture 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody LpaPicture lpaPicture) {
        return success(this.lpaPictureService.save(lpaPicture));
    }

    /**
     * 修改数据
     *
     * @param lpaPicture 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody LpaPicture lpaPicture) {
        return success(this.lpaPictureService.updateById(lpaPicture));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return success(this.lpaPictureService.removeByIds(idList));
    }

    /**
     * 导出excel
     *
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<LpaPicture> res = lpaPictureService.list();
        var exportXls = new ExportXls<LpaPicture>("lpaPicture" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, LpaPicture.class);
    }

    /**
     * 通过excel导入数据
     *
     * @param request  请求
     * @param response 反馈
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<LpaPicture> importExcel = new ImportExcel<>();
        return importExcel.importData(request, LpaPicture.class, lpaPictureService);
    }

    /**
     * 上传照片
     * //@RequestParam(value = "file") MultipartFile file,@RequestBody LpaPicture lpaPicture
     *
     * @return R 结果
     */
    @RequestMapping(value = "uploadPicture", method = RequestMethod.POST)
    public R<?> uploadPicture(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        LpaPicture lpaPicture = new LpaPicture();
        lpaPicture.setChecklistId(params.getParameter("checklistId"));
        lpaPicture.setScheduleId(params.getParameter("scheduleId"));
        return this.lpaPictureService.uploadPicture(file, lpaPicture);
    }
}
