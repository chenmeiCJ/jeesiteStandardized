/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataelement.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dataelement.entity.DataElement;

/**
 * 数据元信息录入DAO接口
 * @author -
 * @version 2017-08-15
 */
@MyBatisDao
public interface DataElementDao extends CrudDao<DataElement> {
	
}