package com.qs.bluewhale.service;

import com.github.pagehelper.Page;
import com.qs.bluewhale.entity.BillCategory;

import java.util.List;

public interface BillCategoryService {

    Page<BillCategory> getBillCategoryList(Page<BillCategory> page);

    List<BillCategory> getAllBillCategoryList();

}
