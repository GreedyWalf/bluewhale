package com.qs.bluewhale.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.bluewhale.entity.BillInfo;
import com.qs.bluewhale.service.BillService;
import com.qs.bluewhale.service.mapper.BillMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "billService")
public class BillServiceImpl implements BillService {

    @Resource
    private BillMapper billMapper;


    @Override
    public Page<BillInfo> findBillPage(Page<BillInfo> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return billMapper.findBillPage();
    }
}
