package com.thinkgem.jeesite.modules.dataelement.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class DataElementVo extends DataEntity<DataElement>{
	
	private static final long serialVersionUID = -8380268588874006826L;

	
	private String nbbsf;		// 内部标识符
	private String zwmc;		// 中文名称
	private String zwqp;		// 中文全拼
	private String bsf;		// 标识符
	private String bb;		// 版本
	private String tycmc;		// 同义名称
	private String sm;		// 说明
	private String dxlx;		// 对象类词
	private String txc;		// 特性词
	private String gx;		// 关系
	private String bsc;		// 表示词
	private String sjlx;		// 数据类型
	private String bsgs;		// 表示格式
	private String jldw;		// 计量单位
	private String zy;		// 值域
	private String zt;		// 状态
	private String tjjg;		// 提交机构
	private String zyqcr;		// 主要起草人
	private Date pzsj;		// 批准时间
	private String bz;		// 备注
	private String sqlx;		// 申请类型
	private String lrr;		// 录入人
	private Date lrsj;		// 录入时间
	
	public DataElementVo() {
		super();
	}

	public DataElementVo(String id){
		super(id);
	}

	@Length(min=1, max=100, message="内部标识符长度必须介于 1 和 100 之间")
	@ExcelField(title = "ELEMENT_ID")
	public String getNbbsf() {
		return nbbsf;
	}

	public void setNbbsf(String nbbsf) {
		this.nbbsf = nbbsf;
	}
	
	@Length(min=1, max=100, message="中文名称长度必须介于 1 和 100 之间")
	@ExcelField(title = "CHINESE_NAME")
	public String getZwmc() {
		return zwmc;
	}

	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	
	
	@Length(min=1, max=100, message="中文全拼长度必须介于 1 和 100 之间")
	@ExcelField(title = "CHINESE_FULL_NAME")
	public String getZwqp() {
		return zwqp;
	}

	public void setZwqp(String zwqp) {
		this.zwqp = zwqp;
	}
	
	@Length(min=1, max=100, message="标识符长度必须介于 1 和 100 之间")
	@ExcelField(title = "ID_NO")
	public String getBsf() {
		return bsf;
	}

	public void setBsf(String bsf) {
		this.bsf = bsf;
	}
	
	@Length(min=1, max=100, message="版本长度必须介于 1 和 100 之间")
	@ExcelField(title = "VERSION")
	public String getBb() {
		return bb;
	}

	public void setBb(String bb) {
		this.bb = bb;
	}
	
	@Length(min=0, max=100, message="同义名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "SYNONYMS")
	public String getTycmc() {
		return tycmc;
	}

	public void setTycmc(String tycmc) {
		this.tycmc = tycmc;
	}
	
	@Length(min=1, max=100, message="说明长度必须介于 1 和 100 之间")
	@ExcelField(title = "MEMO")
	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}
	
	@Length(min=1, max=100, message="对象类词长度必须介于 1 和 100 之间")
	@ExcelField(title = "OBJECT_CLASS_TERM")
	public String getDxlx() {
		return dxlx;
	}

	public void setDxlx(String dxlx) {
		this.dxlx = dxlx;
	}
	
	@Length(min=1, max=100, message="特性词长度必须介于 1 和 100 之间")
	@ExcelField(title = "CHARACTERISTIC_WORDS")
	public String getTxc() {
		return txc;
	}

	public void setTxc(String txc) {
		this.txc = txc;
	}
	

	
	
	@Length(min=0, max=100, message="关系长度必须介于 0 和 100 之间")
	@ExcelField(title = "RELATION")
	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}
	
	@Length(min=1, max=100, message="表示词长度必须介于 1 和 100 之间")
	@ExcelField(title = "EXPRESS_WORDS")
	public String getBsc() {
		return bsc;
	}

	public void setBsc(String bsc) {
		this.bsc = bsc;
	}
	
	@Length(min=1, max=100, message="数据类型长度必须介于 1 和 100 之间")
	@ExcelField(title = "DATA_TYPE")
	public String getSjlx() {
		return sjlx;
	}

	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}
	
	@Length(min=1, max=100, message="表示格式长度必须介于 1 和 100 之间")
	@ExcelField(title = "DATA_FORMAT")
	public String getBsgs() {
		return bsgs;
	}

	public void setBsgs(String bsgs) {
		this.bsgs = bsgs;
	}
	
	@Length(min=0, max=100, message="计量单位长度必须介于 0 和 100 之间")
	@ExcelField(title = "MEASURE_UNIT")
	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	
	@ExcelField(title = "VALUE_RANGE")
	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}
	
	@Length(min=1, max=11, message="状态长度必须介于 1 和 11 之间")
	@ExcelField(title = "STATE")
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
	
	@Length(min=1, max=200, message="提交机构长度必须介于 1 和 200 之间")
	@ExcelField(title = "COMMIT_ORG")
	public String getTjjg() {
		return tjjg;
	}

	public void setTjjg(String tjjg) {
		this.tjjg = tjjg;
	}
	

	
	@ExcelField(title = "MAIN_DRAFTER")
	public String getZyqcr() {
		return zyqcr;
	}

	public void setZyqcr(String zyqcr) {
		this.zyqcr = zyqcr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "CONFIRM_DATE",fieldType=Date.class)
	public Date getPzsj() {
		return pzsj;
	}

	public void setPzsj(Date pzsj) {
		this.pzsj = pzsj;
	}
	
	@ExcelField(title = "REMARK")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Length(min=0, max=500, message="申请类型长度必须介于 0 和 500 之间")
	@ExcelField(title = "STANDARD_MODE")
	public String getSqlx() {
		return sqlx;
	}

	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}

	
	@Length(min=0, max=100, message="录入人长度必须介于 0 和 100 之间")
	@ExcelField(title = "CREATE_USER")
	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "CREATE_TIME",fieldType=Date.class)
	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}
	
}
