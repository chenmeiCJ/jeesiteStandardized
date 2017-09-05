/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataelementprocess.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.ActEntity;

/**
 * 数据元审批Entity
 * @author -
 * @version 2017-08-29
 */
public class DataElementProcess extends ActEntity<DataElementProcess> {
	
	private static final long serialVersionUID = 1L;
	private String nbbsf;		// 内部标识符
	private String zwmc;		// 中文名称
	private String ywmc;		// 英文名称
	private String zwqp;		// 中文全拼
	private String bsf;		// 标识符
	private String yj;		// 语境
	private String bb;		// 版本
	private String tycmc;		// 同义名称
	private String sm;		// 说明
	private String dxlx;		// 对象类词
	private String txc;		// 特性词
	private String yyys;		// 应用约束
	private String flfa;		// 分类方案
	private String flfaz;		// 分类方案值
	private String gx;		// 关系
	private String bsc;		// 表示词
	private String sjlx;		// 数据类型
	private String bsgs;		// 表示格式
	private String jldw;		// 计量单位
	private String zy;		// 值域
	private String zt;		// 状态
	private String tjjg;		// 提交机构
	private String zcjg;		// 注册机构
	private String zyqcr;		// 主要起草人
	private Date pzsj;		// 批准时间
	private String bz;		// 备注
	private String sqlx;		// 申请类型
	private String sqsm;		// 申请说明
	private String lrr;		// 录入人
	private String lxfs;		// 联系方式
	private Date lrsj;		// 录入时间
	private String origin;		// 数据来源
	private String lrdw;		// 录入单位
	private String classify1;		// classify1
	private String classify2;		// classify2
	private String classify3;		// classify3
	private String classify4;		// classify4
	private String classify5;		// classify5
	private String procInsId;		// 流程实例ID
	
	public DataElementProcess() {
		super();
	}

	public DataElementProcess(String id){
		super(id);
	}

	@Length(min=1, max=100, message="内部标识符长度必须介于 1 和 100 之间")
	public String getNbbsf() {
		return nbbsf;
	}

	public void setNbbsf(String nbbsf) {
		this.nbbsf = nbbsf;
	}
	
	@Length(min=1, max=100, message="中文名称长度必须介于 1 和 100 之间")
	public String getZwmc() {
		return zwmc;
	}

	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	
	@Length(min=0, max=100, message="英文名称长度必须介于 0 和 100 之间")
	public String getYwmc() {
		return ywmc;
	}

	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}
	
	@Length(min=1, max=500, message="中文全拼长度必须介于 1 和 500 之间")
	public String getZwqp() {
		return zwqp;
	}

	public void setZwqp(String zwqp) {
		this.zwqp = zwqp;
	}
	
	@Length(min=1, max=100, message="标识符长度必须介于 1 和 100 之间")
	public String getBsf() {
		return bsf;
	}

	public void setBsf(String bsf) {
		this.bsf = bsf;
	}
	
	@Length(min=0, max=100, message="语境长度必须介于 0 和 100 之间")
	public String getYj() {
		return yj;
	}

	public void setYj(String yj) {
		this.yj = yj;
	}
	
	@Length(min=1, max=100, message="版本长度必须介于 1 和 100 之间")
	public String getBb() {
		return bb;
	}

	public void setBb(String bb) {
		this.bb = bb;
	}
	
	@Length(min=0, max=100, message="同义名称长度必须介于 0 和 100 之间")
	public String getTycmc() {
		return tycmc;
	}

	public void setTycmc(String tycmc) {
		this.tycmc = tycmc;
	}
	
	@Length(min=1, max=500, message="说明长度必须介于 1 和 500 之间")
	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}
	
	@Length(min=1, max=100, message="对象类词长度必须介于 1 和 100 之间")
	public String getDxlx() {
		return dxlx;
	}

	public void setDxlx(String dxlx) {
		this.dxlx = dxlx;
	}
	
	@Length(min=1, max=100, message="特性词长度必须介于 1 和 100 之间")
	public String getTxc() {
		return txc;
	}

	public void setTxc(String txc) {
		this.txc = txc;
	}
	
	@Length(min=0, max=100, message="应用约束长度必须介于 0 和 100 之间")
	public String getYyys() {
		return yyys;
	}

	public void setYyys(String yyys) {
		this.yyys = yyys;
	}
	
	@Length(min=0, max=100, message="分类方案长度必须介于 0 和 100 之间")
	public String getFlfa() {
		return flfa;
	}

	public void setFlfa(String flfa) {
		this.flfa = flfa;
	}
	
	@Length(min=0, max=100, message="分类方案值长度必须介于 0 和 100 之间")
	public String getFlfaz() {
		return flfaz;
	}

	public void setFlfaz(String flfaz) {
		this.flfaz = flfaz;
	}
	
	@Length(min=0, max=500, message="关系长度必须介于 0 和 500 之间")
	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}
	
	@Length(min=1, max=100, message="表示词长度必须介于 1 和 100 之间")
	public String getBsc() {
		return bsc;
	}

	public void setBsc(String bsc) {
		this.bsc = bsc;
	}
	
	@Length(min=1, max=100, message="数据类型长度必须介于 1 和 100 之间")
	public String getSjlx() {
		return sjlx;
	}

	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}
	
	@Length(min=1, max=100, message="表示格式长度必须介于 1 和 100 之间")
	public String getBsgs() {
		return bsgs;
	}

	public void setBsgs(String bsgs) {
		this.bsgs = bsgs;
	}
	
	@Length(min=0, max=100, message="计量单位长度必须介于 0 和 100 之间")
	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	
	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}
	
	@Length(min=1, max=64, message="状态长度必须介于 1 和 64 之间")
	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
	
	@Length(min=1, max=200, message="提交机构长度必须介于 1 和 200 之间")
	public String getTjjg() {
		return tjjg;
	}

	public void setTjjg(String tjjg) {
		this.tjjg = tjjg;
	}
	
	@Length(min=0, max=200, message="注册机构长度必须介于 0 和 200 之间")
	public String getZcjg() {
		return zcjg;
	}

	public void setZcjg(String zcjg) {
		this.zcjg = zcjg;
	}
	
	public String getZyqcr() {
		return zyqcr;
	}

	public void setZyqcr(String zyqcr) {
		this.zyqcr = zyqcr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPzsj() {
		return pzsj;
	}

	public void setPzsj(Date pzsj) {
		this.pzsj = pzsj;
	}
	
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Length(min=0, max=500, message="申请类型长度必须介于 0 和 500 之间")
	public String getSqlx() {
		return sqlx;
	}

	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}
	
	@Length(min=0, max=500, message="申请说明长度必须介于 0 和 500 之间")
	public String getSqsm() {
		return sqsm;
	}

	public void setSqsm(String sqsm) {
		this.sqsm = sqsm;
	}
	
	@Length(min=0, max=100, message="录入人长度必须介于 0 和 100 之间")
	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	
	@Length(min=0, max=100, message="联系方式长度必须介于 0 和 100 之间")
	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}
	
	@Length(min=0, max=200, message="数据来源长度必须介于 0 和 200 之间")
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	@Length(min=0, max=300, message="录入单位长度必须介于 0 和 300 之间")
	public String getLrdw() {
		return lrdw;
	}

	public void setLrdw(String lrdw) {
		this.lrdw = lrdw;
	}
	
	@Length(min=0, max=300, message="classify1长度必须介于 0 和 300 之间")
	public String getClassify1() {
		return classify1;
	}

	public void setClassify1(String classify1) {
		this.classify1 = classify1;
	}
	
	@Length(min=0, max=300, message="classify2长度必须介于 0 和 300 之间")
	public String getClassify2() {
		return classify2;
	}

	public void setClassify2(String classify2) {
		this.classify2 = classify2;
	}
	
	@Length(min=0, max=300, message="classify3长度必须介于 0 和 300 之间")
	public String getClassify3() {
		return classify3;
	}

	public void setClassify3(String classify3) {
		this.classify3 = classify3;
	}
	
	@Length(min=0, max=300, message="classify4长度必须介于 0 和 300 之间")
	public String getClassify4() {
		return classify4;
	}

	public void setClassify4(String classify4) {
		this.classify4 = classify4;
	}
	
	@Length(min=0, max=300, message="classify5长度必须介于 0 和 300 之间")
	public String getClassify5() {
		return classify5;
	}

	public void setClassify5(String classify5) {
		this.classify5 = classify5;
	}
	
	@Length(min=0, max=64, message="流程实例ID长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
}