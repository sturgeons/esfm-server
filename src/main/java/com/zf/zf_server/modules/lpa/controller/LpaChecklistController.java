package com.zf.zf_server.modules.lpa.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.modules.lpa.entity.LpaChecklist;
import com.zf.zf_server.modules.lpa.entity.vo.ChecklistVo;
import com.zf.zf_server.modules.lpa.service.LpaChecklistService;
import com.zf.zf_server.utils.excel.ExportXls;
import com.zf.zf_server.utils.excel.ImportExcel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * 分层审核-审核表(LpaChecklist)表控制层
 * @author yaoxin
 * @since 2020-07-30 11:28:31
 */
@RestController
@RequestMapping("lpaChecklist/")
public class LpaChecklistController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LpaChecklistService lpaChecklistService;

    /**
     * 分页查询所有数据
     * @param page         分页对象
     * @param lpaChecklist 查询实体
     * @return 所有数据
     */
    @GetMapping(value = "list")
    public R<?> selectAll(Page<LpaChecklist> page, LpaChecklist lpaChecklist) {
        return success(this.lpaChecklistService.page(page, new QueryWrapper<>(lpaChecklist)));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.lpaChecklistService.getById(id));
    }

    /**
     * 新增数据
     * @param lpaChecklist 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "insert")
    public R<?> insert(@RequestBody LpaChecklist lpaChecklist) {
        return success(this.lpaChecklistService.save(lpaChecklist));
    }

    /**
     * 新增审核单和项目
     * @param checklistVo 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "andCheckItems")
    public R<?> insert(@RequestBody ChecklistVo checklistVo) {
        return this.lpaChecklistService.andCheckItems(checklistVo);
    }

    /**
     * 修改数据
     * @param lpaChecklist 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "update")
    public R<?> update(@RequestBody LpaChecklist lpaChecklist) {
        return success(this.lpaChecklistService.updateById(lpaChecklist));
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping(value = "delete")
    public R<?> delete(@RequestParam("idList") List<String> idList) {
        return this.lpaChecklistService.deleteByIds(idList);
    }

    /**
     * 导出excel
     * @param request  请求
     * @param response 反馈
     */
    @GetMapping(value = "exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        List<LpaChecklist> res = lpaChecklistService.list();
        var exportXls = new ExportXls<LpaChecklist>("lpaChecklist" + DateTime.now().toString("yyMMddHHmmss"), "demo", "yaoxin");
        return exportXls.export(res, LpaChecklist.class);
    }

    /**
     * 通过excel导入数据
     * @param request  请求
     * @param response 反馈
     * @return R 结果
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public R<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        ImportExcel<LpaChecklist> importExcel = new ImportExcel<>();
        return importExcel.importData(request, LpaChecklist.class, lpaChecklistService);
    }

    /**
     * 根据审核区域获取审核单
     * @return 所有数据
     */
    @GetMapping(value = "listByArea")
    public R<?> listByArea(@RequestParam String area) {
        return this.lpaChecklistService.listByArea(area);
    }

}
