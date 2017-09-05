/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.log;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.log.ActEvtLog;

/**
 * -DAO接口
 * @author -
 * @version 2017-08-15
 */
@MyBatisDao
public interface ActEvtLogDao extends CrudDao<ActEvtLog> {
	
}