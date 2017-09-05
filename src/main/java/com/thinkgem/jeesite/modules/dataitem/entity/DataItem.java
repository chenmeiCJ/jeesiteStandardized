/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataitem.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 数据项信息表Entity
 * @author -
 * @version 2017-08-16
 */
public class DataItem extends DataEntity<DataItem> {
	
	private static final long serialVersionUID = 1L;
	private String dataItemName;		// 数据项名称
	private String dataItemIdentifier;		// 数据项标识符
	private String dataElementInternalIdentifier;		// 数据元内部标识符
	private String dataElementCcname;		// 数据元中文名称
	private String qualifierInternalIdentifier;		// 限定词内部标识符
	private String qualifierCcname;		// 限定词中文名称
	private String entryMan;		// 录入人
	private Date entryDate;		// 录入时间
	private String entryUnit;		// 录入单位
	private String contactInformation;		// 联系方式
	private Integer sort;
	private String origin;  //来源
	
	public DataItem() {
		super();
	}

	public DataItem(String id){
		super(id);
	}

	@Length(min=0, max=64, message="数据项名称长度必须介于 0 和 64 之间")
	public String getDataItemName() {
		return dataItemName;
	}

	public void setDataItemName(String dataItemName) {
		this.dataItemName = dataItemName;
	}
	
	@Length(min=0, max=64, message="数据项标识符长度必须介于 0 和 64 之间")
	public String getDataItemIdentifier() {
		return dataItemIdentifier;
	}

	public void setDataItemIdentifier(String dataItemIdentifier) {
		this.dataItemIdentifier = dataItemIdentifier;
	}
	
	@Length(min=0, max=64, message="数据元内部标识符长度必须介于 0 和 64 之间")
	public String getDataElementInternalIdentifier() {
		return dataElementInternalIdentifier;
	}

	public void setDataElementInternalIdentifier(String dataElementInternalIdentifier) {
		this.dataElementInternalIdentifier = dataElementInternalIdentifier;
	}
	
	@Length(min=0, max=64, message="数据元中文名称长度必须介于 0 和 64 之间")
	public String getDataElementCcname() {
		return dataElementCcname;
	}

	public void setDataElementCcname(String dataElementCcname) {
		this.dataElementCcname = dataElementCcname;
	}
	
	@Length(min=0, max=64, message="限定词内部标识符长度必须介于 0 和 64 之间")
	public String getQualifierInternalIdentifier() {
		return qualifierInternalIdentifier;
	}

	public void setQualifierInternalIdentifier(String qualifierInternalIdentifier) {
		this.qualifierInternalIdentifier = qualifierInternalIdentifier;
	}
	
	@Length(min=0, max=64, message="限定词中文名称长度必须介于 0 和 64 之间")
	public String getQualifierCcname() {
		return qualifierCcname;
	}

	public void setQualifierCcname(String qualifierCcname) {
		this.qualifierCcname = qualifierCcname;
	}
	
	@Length(min=0, max=32, message="录入人长度必须介于 0 和 32 之间")
	public String getEntryMan() {
		return entryMan;
	}

	public void setEntryMan(String entryMan) {
		this.entryMan = entryMan;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	@Length(min=0, max=64, message="录入单位长度必须介于 0 和 64 之间")
	public String getEntryUnit() {
		return entryUnit;
	}

	public void setEntryUnit(String entryUnit) {
		this.entryUnit = entryUnit;
	}
	
	@Length(min=0, max=32, message="联系方式长度必须介于 0 和 32 之间")
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
	
}