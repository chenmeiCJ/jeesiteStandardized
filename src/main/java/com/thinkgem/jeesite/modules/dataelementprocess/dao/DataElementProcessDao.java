/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataelementprocess.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dataelementprocess.entity.DataElementProcess;

/**
 * 数据元审批DAO接口
 * @author -
 * @version 2017-08-29
 */
@MyBatisDao
public interface DataElementProcessDao extends CrudDao<DataElementProcess> {
	
}