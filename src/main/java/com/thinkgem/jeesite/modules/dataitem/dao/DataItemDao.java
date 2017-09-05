/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataitem.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dataitem.entity.DataItem;

/**
 * 数据项信息表DAO接口
 * @author -
 * @version 2017-08-16
 */
@MyBatisDao
public interface DataItemDao extends CrudDao<DataItem> {
	public Integer getMaxSort();
}