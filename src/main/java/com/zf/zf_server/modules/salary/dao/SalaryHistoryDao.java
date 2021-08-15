package com.zf.zf_server.modules.salary.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.zf_server.modules.salary.entity.SalaryHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 人员历史薪资(SalaryHistory)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-01 19:40:39
 */
public interface SalaryHistoryDao extends BaseMapper<SalaryHistory> {


    List<SalaryHistory> selectAllHistory(@Param("name") String name, @Param("workCode") String workCode);
}