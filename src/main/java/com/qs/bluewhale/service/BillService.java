package com.qs.bluewhale.service;

import com.github.pagehelper.Page;
import com.qs.bluewhale.entity.BillInfo;

public interface BillService {

    Page<BillInfo> findBillPage(Page<BillInfo> page);
}
