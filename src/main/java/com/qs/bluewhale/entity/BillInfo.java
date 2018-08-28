package com.qs.bluewhale.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账单信息表
 */
@Entity
@Table(name = "t_bw_bill_info")
public class BillInfo implements Serializable {

    //主键
    @Id
    @GenericGenerator(name = "my-uuid", strategy = "uuid")
    @GeneratedValue(generator = "my-uuid")
    @Column(name = "bill_id")
    private String billId;

    //账单所属类型
    @Column(name = "category_id")
    private String categoryId;

    //账单金额
    @Column(name = "money")
    private BigDecimal money;

    //记账时间(时间点)
    @Column(name = "record_date")
    private Date recordDate;

    //记账时间段（开始时间）
    @Column(name = "record_start_date")
    private Date recordStartDate;

    //记账时间段（结束时间）
    @Column(name = "record_end_date")
    private Date recordEndDate;

    //账单类型（收入 0、支出 1）
    @Column(name = "bill_type")
    private String billType;

    //是否报销（0 否 1 是）
    @Column(name = "is_reimburse")
    private String isReimburse;

    //账单状态（UNFINISH 未完结，FINISHED 已完结）
    @Column(name = "status")
    private String status;

    //创建人
    @Column(name = "create_by")
    private String createBy;

    //最后修改人
    @Column(name = "last_modify_by")
    private String lastModifyBy;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //最后修改时间
    @Column(name = "last_modify_time")
    private Date lastModifyTime;


    public Date getRecordStartDate() {
        return recordStartDate;
    }

    public void setRecordStartDate(Date recordStartDate) {
        this.recordStartDate = recordStartDate;
    }

    public Date getRecordEndDate() {
        return recordEndDate;
    }

    public void setRecordEndDate(Date recordEndDate) {
        this.recordEndDate = recordEndDate;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
