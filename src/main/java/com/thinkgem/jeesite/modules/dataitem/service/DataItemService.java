/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataitem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dataitem.entity.DataItem;
import com.thinkgem.jeesite.modules.dataitem.dao.DataItemDao;

/**
 * 数据项信息表Service
 * @author -
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class DataItemService extends CrudService<DataItemDao, DataItem> {

	public DataItem get(String id) {
		return super.get(id);
	}
	
	public List<DataItem> findList(DataItem dataItem) {
		return super.findList(dataItem);
	}
	
	public Page<DataItem> findPage(Page<DataItem> page, DataItem dataItem) {
		return super.findPage(page, dataItem);
	}
	
	@Transactional(readOnly = false)
	public void save(DataItem dataItem) {
		super.save(dataItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(DataItem dataItem) {
		super.delete(dataItem);
	}
	
	public Integer getMaxSort() {
		return super.getMaxSort();
	}
	
}