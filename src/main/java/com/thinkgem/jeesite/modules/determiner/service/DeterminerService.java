/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.determiner.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.determiner.entity.Determiner;
import com.thinkgem.jeesite.modules.determiner.dao.DeterminerDao;

/**
 * 限定词信息表Service
 * @author -
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class DeterminerService extends CrudService<DeterminerDao, Determiner> {

	public Determiner get(String id) {
		return super.get(id);
	}
	
	public List<Determiner> findList(Determiner determiner) {
		return super.findList(determiner);
	}
	
	public Page<Determiner> findPage(Page<Determiner> page, Determiner determiner) {
		return super.findPage(page, determiner);
	}
	
	@Transactional(readOnly = false)
	public void save(Determiner determiner) {
		super.save(determiner);
	}
	
	@Transactional(readOnly = false)
	public void delete(Determiner determiner) {
		super.delete(determiner);
	}
	
}