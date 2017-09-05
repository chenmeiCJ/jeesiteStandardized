/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dictionary.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.ResultDTO;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dataelement.entity.DataElement;
import com.thinkgem.jeesite.modules.dictionary.entity.Dictionary;
import com.thinkgem.jeesite.modules.dictionary.entity.DictionaryCode;
import com.thinkgem.jeesite.modules.dictionary.service.DictionaryCodeService;
import com.thinkgem.jeesite.modules.dictionary.service.DictionaryService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 代码集表Controller
 * @author -
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/dictionary/dictionary")
public class DictionaryController extends BaseController {

	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private DictionaryCodeService dictionaryCodeService;
	
	@ModelAttribute
	public Dictionary get(@RequestParam(required=false) String id) {
		Dictionary entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dictionaryService.get(id);
		}
		if (entity == null){
			entity = new Dictionary();
		}
		return entity;
	}
	
	@RequiresPermissions("dictionary:dictionary:view")
	@RequestMapping(value = {"list", ""})
	public String list(Dictionary dictionary, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Dictionary> page = dictionaryService.findPage(new Page<Dictionary>(request, response), dictionary); 
		model.addAttribute("page", page);
		return "modules/dictionary/dictionaryList";
	}

	@RequiresPermissions("dictionary:dictionary:view")
	@RequestMapping(value = "form")
	public String form(Dictionary dictionary, Model model,HttpServletRequest request, HttpServletResponse response) {
		DictionaryCode code = new DictionaryCode();
		code.setDicId(dictionary.getId());
		Page<DictionaryCode> page = dictionaryCodeService.findPage(new Page<DictionaryCode>(request, response), code);
		dictionary.setDictionaryCodeList(page.getList());
		model.addAttribute("dictionary", dictionary);
		return "modules/dictionary/dictionaryForm";
	}
	
	@RequiresPermissions("dictionary:dictionary:view")
	@RequestMapping(value = "edit")
	public String edit(Dictionary dictionary, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		DictionaryCode code = new DictionaryCode();
		code.setDicId(dictionary.getId());
		Page<DictionaryCode> page = dictionaryCodeService.findPage(new Page<DictionaryCode>(request, response), code);
		dictionary.setDictionaryCodeList(page.getList());
		model.addAttribute("dictionary", dictionary);
		User user =  UserUtils.getUser();
		if(!user.isAdmin()&&!user.getName().equals(dictionary.getEntryMan())) {
			addMessage(redirectAttributes, "没有修改权限");
			return "redirect:"+Global.getAdminPath()+"/dictionary/dictionary/?repage"; 
		}
		return "modules/dictionary/dictionaryForm";
	}
	
	@RequiresPermissions("dictionary:dictionary:view")
	@RequestMapping(value = "view")
	public String view(Dictionary dictionary, Model model,HttpServletRequest request, HttpServletResponse response ) {
		DictionaryCode code = new DictionaryCode();
		code.setDicId(dictionary.getId());
		Page<DictionaryCode> page = dictionaryCodeService.findPage(new Page<DictionaryCode>(request, response), code); 
		model.addAttribute("dictionary", dictionary);
		model.addAttribute("page", page);
		return "modules/dictionary/dictionaryView";
	}

	@RequiresPermissions("dictionary:dictionary:edit")
	@RequestMapping(value = "save")
	public String save(Dictionary dictionary, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, dictionary)){
			return form(dictionary, model,request,response);
		}
		dictionaryService.save(dictionary);
		addMessage(redirectAttributes, "保存代码集表成功");
		return "redirect:"+Global.getAdminPath()+"/dictionary/dictionary/?repage";
	}
	
	@RequiresPermissions("dictionary:dictionary:edit")
	@RequestMapping(value = "delete")
	public String delete(Dictionary dictionary, RedirectAttributes redirectAttributes) {
		User user =  UserUtils.getUser();
		if(!user.isAdmin()&&!user.getName().equals(dictionary.getEntryMan())) {
			addMessage(redirectAttributes, "没有删除权限");
			return "redirect:"+Global.getAdminPath()+"/dictionary/dictionary/?repage"; 
		}
		dictionaryService.delete(dictionary);
		addMessage(redirectAttributes, "删除代码集表成功");
		return "redirect:"+Global.getAdminPath()+"/dictionary/dictionary/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value="IsExist",produces="application/json")
	public ResultDTO IsExist(Model model,String value,String type) {
		ResultDTO result = new ResultDTO();
		Dictionary dictionary = new Dictionary();
		if(type.equals("ccname")) {
			dictionary.setCcname(value);
		}else if(type.equals("internalIdentifier")) {
			dictionary.setInternalIdentifier(value);
		}
		List<Dictionary> list = dictionaryService.findList(dictionary);
		result.setSuccess(list.size()>0?false:true);
		if(type.equals("ccname")) {
			result.setMessage(list.size()>0?"<font color='red'>中文名称已存在</font>":"");
		}else if(type.equals("internalIdentifier")) {
			result.setMessage(list.size()>0?"<font color='red'>内部标识符已存在</font>":"");
		}
		return result;
	}
	

}