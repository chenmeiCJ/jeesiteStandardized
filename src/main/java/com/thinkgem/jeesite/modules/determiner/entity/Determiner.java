/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.determiner.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 限定词信息表Entity
 * @author -
 * @version 2017-08-16
 */
public class Determiner extends DataEntity<Determiner> {
	
	private static final long serialVersionUID = 1L;
	private String internalIdentifier;		// 内部标识符
	private String identifier;		// 标识符
	private String ccname;		// 中文名称
	private String statue;		// 状态
	private Date submissionDate;		// 提交日期
	private String remark;		// 说明
	private String entryMan;		// 录入人
	private String entryUnit;		// 录入单位
	private Date entryDate;		// 录入时间
	private String contactInformation;		// 联系方式
	private String origin;  //来源
	
	public Determiner() {
		super();
	}

	public Determiner(String id){
		super(id);
	}

	@Length(min=0, max=36, message="内部标识符长度必须介于 0 和 36 之间")
	public String getInternalIdentifier() {
		return internalIdentifier;
	}

	public void setInternalIdentifier(String internalIdentifier) {
		this.internalIdentifier = internalIdentifier;
	}
	
	@Length(min=0, max=36, message="标识符长度必须介于 0 和 36 之间")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	@Length(min=0, max=64, message="中文名称长度必须介于 0 和 64 之间")
	public String getCcname() {
		return ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
	
	@Length(min=0, max=16, message="状态长度必须介于 0 和 16 之间")
	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	
	@Length(min=0, max=64, message="说明长度必须介于 0 和 64 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=32, message="录入人长度必须介于 0 和 32 之间")
	public String getEntryMan() {
		return entryMan;
	}

	public void setEntryMan(String entryMan) {
		this.entryMan = entryMan;
	}
	
	@Length(min=0, max=64, message="录入单位长度必须介于 0 和 64 之间")
	public String getEntryUnit() {
		return entryUnit;
	}

	public void setEntryUnit(String entryUnit) {
		this.entryUnit = entryUnit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	@Length(min=0, max=32, message="联系方式长度必须介于 0 和 32 之间")
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
}