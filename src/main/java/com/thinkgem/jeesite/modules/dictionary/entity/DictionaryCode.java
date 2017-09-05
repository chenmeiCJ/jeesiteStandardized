/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dictionary.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 代码集表Entity
 * @author -
 * @version 2017-08-16
 */
public class DictionaryCode extends DataEntity<DictionaryCode> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String code;		// 代码
	private String pid;		// 父级代码
	private String dicId;		// 所属字典表 父类
	private String type;		// 类型，分类0，字典表1，代码2
	private String classic;		// 所属分类
	private String remark;		// 说明
	
	public DictionaryCode() {
		super();
	}

	public DictionaryCode(String id){
		super(id);
	}


	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="代码长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=36, message="父级代码长度必须介于 0 和 36 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
	@Length(min=0, max=11, message="类型，分类0，字典表1，代码2长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}
	@Length(min=0, max=36, message="所属字典表长度必须介于 0 和 36 之间")
	public String getDicId() {
		return dicId;
	}

	public void setDicId(String dicId) {
		this.dicId = dicId;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=36, message="所属分类长度必须介于 0 和 36 之间")
	public String getClassic() {
		return classic;
	}

	public void setClassic(String classic) {
		this.classic = classic;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}