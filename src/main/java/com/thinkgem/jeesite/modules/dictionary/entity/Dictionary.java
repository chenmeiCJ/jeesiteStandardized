/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dictionary.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 代码集表Entity
 * @author -
 * @version 2017-08-16
 */
public class Dictionary extends DataEntity<Dictionary> {
	
	private static final long serialVersionUID = 1L;
	private String ccname;		// 中文名称
	private String remark;		// 备注
	private String internalIdentifier;		// 内部标识符
	private String instruction;		// 说明
	private String version;		// 版本
	private String state;		// 状态
	private String normativeReferenceFile;		// 规范性引用文件
	private String termsAndDefinitions;		// 术语和定义
	private String classificationPrinciple;		// 分类原则和方法
	private String codingMethod;		// 编码方法
	private String draftman;		// 主要起草人
	private String standardNumber;		// 标准号
	private String submittingBody;		// 提交机构
	private Date releaseDate;		// 发布日期
	private String entryMan;		// 录入人
	private String entryUnit;		// 录入单位
	private String contactInformation;		// 联系方式
	private Date entryTime;		// 录入时间
	private String origin;  //来源
	private List<DictionaryCode> dictionaryCodeList = Lists.newArrayList();		// 子表列表
	
	public Dictionary() {
		super();
	}

	public Dictionary(String id){
		super(id);
	}

	@Length(min=0, max=64, message="中文名称长度必须介于 0 和 64 之间")
	public String getCcname() {
		return ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=36, message="内部标识符长度必须介于 0 和 36 之间")
	public String getInternalIdentifier() {
		return internalIdentifier;
	}

	public void setInternalIdentifier(String internalIdentifier) {
		this.internalIdentifier = internalIdentifier;
	}
	

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	@Length(min=0, max=16, message="版本长度必须介于 0 和 16 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Length(min=0, max=16, message="状态长度必须介于 0 和 16 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getNormativeReferenceFile() {
		return normativeReferenceFile;
	}

	public void setNormativeReferenceFile(String normativeReferenceFile) {
		this.normativeReferenceFile = normativeReferenceFile;
	}
	
	public String getTermsAndDefinitions() {
		return termsAndDefinitions;
	}

	public void setTermsAndDefinitions(String termsAndDefinitions) {
		this.termsAndDefinitions = termsAndDefinitions;
	}
	
	public String getClassificationPrinciple() {
		return classificationPrinciple;
	}

	public void setClassificationPrinciple(String classificationPrinciple) {
		this.classificationPrinciple = classificationPrinciple;
	}
	
	public String getCodingMethod() {
		return codingMethod;
	}

	public void setCodingMethod(String codingMethod) {
		this.codingMethod = codingMethod;
	}
	
	public String getDraftman() {
		return draftman;
	}

	public void setDraftman(String draftman) {
		this.draftman = draftman;
	}
	
	@Length(min=0, max=32, message="标准号长度必须介于 0 和 32 之间")
	public String getStandardNumber() {
		return standardNumber;
	}

	public void setStandardNumber(String standardNumber) {
		this.standardNumber = standardNumber;
	}
	
	@Length(min=0, max=32, message="提交机构长度必须介于 0 和 32 之间")
	public String getSubmittingBody() {
		return submittingBody;
	}

	public void setSubmittingBody(String submittingBody) {
		this.submittingBody = submittingBody;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	@Length(min=0, max=16, message="录入人长度必须介于 0 和 16 之间")
	public String getEntryMan() {
		return entryMan;
	}

	public void setEntryMan(String entryMan) {
		this.entryMan = entryMan;
	}
	
	@Length(min=0, max=32, message="录入单位长度必须介于 0 和 32 之间")
	public String getEntryUnit() {
		return entryUnit;
	}

	public void setEntryUnit(String entryUnit) {
		this.entryUnit = entryUnit;
	}
	
	@Length(min=0, max=32, message="contact_information长度必须介于 0 和 32 之间")
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	
	public List<DictionaryCode> getDictionaryCodeList() {
		return dictionaryCodeList;
	}

	public void setDictionaryCodeList(List<DictionaryCode> dictionaryCodeList) {
		this.dictionaryCodeList = dictionaryCodeList;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	
}