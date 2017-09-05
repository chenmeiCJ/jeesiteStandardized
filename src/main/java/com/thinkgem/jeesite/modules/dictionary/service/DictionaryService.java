/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dictionary.entity.Dictionary;
import com.thinkgem.jeesite.modules.dictionary.dao.DictionaryDao;
import com.thinkgem.jeesite.modules.dictionary.entity.DictionaryCode;
import com.thinkgem.jeesite.modules.dictionary.dao.DictionaryCodeDao;

/**
 * 代码集表Service
 * @author -
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class DictionaryService extends CrudService<DictionaryDao, Dictionary> {

	@Autowired
	private DictionaryCodeDao dictionaryCodeDao;
	
	public Dictionary get(String id) {
		Dictionary dictionary = super.get(id);
		DictionaryCode code = new DictionaryCode();
		code.setPid(dictionary.getId());
		dictionary.setDictionaryCodeList(dictionaryCodeDao.findList(code));
		return dictionary;
	}
	
	public List<Dictionary> findList(Dictionary dictionary) {
		return super.findList(dictionary);
	}
	
	public Page<Dictionary> findPage(Page<Dictionary> page, Dictionary dictionary) {
		return super.findPage(page, dictionary);
	}
	
	@Transactional(readOnly = false)
	public void save(Dictionary dictionary) {
		super.save(dictionary);
		for (DictionaryCode dictionaryCode : dictionary.getDictionaryCodeList()){
			if (dictionaryCode.getId() == null){
				continue;
			}
			if (DictionaryCode.DEL_FLAG_NORMAL.equals(dictionaryCode.getDelFlag())){
				if (StringUtils.isBlank(dictionaryCode.getId())){
					dictionaryCode.setDicId(dictionary.getId());
					dictionaryCode.preInsert();
					dictionaryCodeDao.insert(dictionaryCode);
				}else{
					dictionaryCode.preUpdate();
					dictionaryCodeDao.update(dictionaryCode);
				}
			}else{
				dictionaryCodeDao.delete(dictionaryCode);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Dictionary dictionary) {
		super.delete(dictionary);
		DictionaryCode code = new DictionaryCode();
		code.setPid(dictionary.getId());
		dictionaryCodeDao.delete(code);
	}
	
}