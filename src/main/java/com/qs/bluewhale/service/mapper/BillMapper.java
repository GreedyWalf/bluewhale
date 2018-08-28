package com.qs.bluewhale.service.mapper;

import com.github.pagehelper.Page;
import com.qs.bluewhale.entity.BillInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillMapper {

    Page<BillInfo> findBillPage();
}
