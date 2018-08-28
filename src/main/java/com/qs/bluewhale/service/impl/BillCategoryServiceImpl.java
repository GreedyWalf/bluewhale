package com.qs.bluewhale.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.bluewhale.entity.BillCategory;
import com.qs.bluewhale.service.BillCategoryService;
import com.qs.bluewhale.service.mapper.BillCategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "billCategoryService")
public class BillCategoryServiceImpl implements BillCategoryService {

    @Resource
    private BillCategoryMapper billCategoryMapper;


    @Override
    public Page<BillCategory> getBillCategoryList(Page<BillCategory> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return billCategoryMapper.findBillCategoryPage();
    }

    @Override
    public List<BillCategory> getAllBillCategoryList() {
        return billCategoryMapper.findAllBillCategoryList();
    }
}
