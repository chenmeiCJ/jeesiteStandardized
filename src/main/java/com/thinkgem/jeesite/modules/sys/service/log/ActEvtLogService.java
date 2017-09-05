/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.log;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.log.ActEvtLog;
import com.thinkgem.jeesite.modules.sys.dao.log.ActEvtLogDao;

/**
 * -Service
 * @author -
 * @version 2017-08-15
 */
@Service
@Transactional(readOnly = true)
public class ActEvtLogService extends CrudService<ActEvtLogDao, ActEvtLog> {

	public ActEvtLog get(String id) {
		return super.get(id);
	}
	
	public List<ActEvtLog> findList(ActEvtLog actEvtLog) {
		return super.findList(actEvtLog);
	}
	
	public Page<ActEvtLog> findPage(Page<ActEvtLog> page, ActEvtLog actEvtLog) {
		return super.findPage(page, actEvtLog);
	}
	
	@Transactional(readOnly = false)
	public void save(ActEvtLog actEvtLog) {
		super.save(actEvtLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActEvtLog actEvtLog) {
		super.delete(actEvtLog);
	}
	
}