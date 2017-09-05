/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataelement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dataelement.entity.DataElement;
import com.thinkgem.jeesite.modules.dataelement.dao.DataElementDao;

/**
 * 数据元信息录入Service
 * @author -
 * @version 2017-08-15
 */
@Service
@Transactional(readOnly = true)
public class DataElementService extends CrudService<DataElementDao, DataElement> {

	public DataElement get(String id) {
		return super.get(id);
	}
	
	public List<DataElement> findList(DataElement dataElement) {
		return super.findList(dataElement);
	}
	
	public Page<DataElement> findPage(Page<DataElement> page, DataElement dataElement) {
		return super.findPage(page, dataElement);
	}
	
	@Transactional(readOnly = false)
	public void save(DataElement dataElement) {
		super.save(dataElement);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataElement dataElement) {
		super.delete(dataElement);
	}
	
}