package com.thinkgem.jeesite.modules.dictionary.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dictionary.dao.DictionaryCodeDao;
import com.thinkgem.jeesite.modules.dictionary.entity.DictionaryCode;

@Service
@Transactional(readOnly = true)
public class DictionaryCodeService extends CrudService<DictionaryCodeDao, DictionaryCode>{
	
	public Page<DictionaryCode> findPage(Page<DictionaryCode> page, DictionaryCode dictionaryCode) {
		return super.findPage(page, dictionaryCode);
	}
	
}
