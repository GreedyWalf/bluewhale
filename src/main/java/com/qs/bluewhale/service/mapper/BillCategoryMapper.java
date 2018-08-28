package com.qs.bluewhale.service.mapper;

import com.github.pagehelper.Page;
import com.qs.bluewhale.entity.BillCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillCategoryMapper {

    Page<BillCategory> findBillCategoryPage();

    List<BillCategory> findAllBillCategoryList();
}
