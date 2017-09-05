/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.determiner.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dataelement.entity.DataElement;
import com.thinkgem.jeesite.modules.determiner.entity.Determiner;
import com.thinkgem.jeesite.modules.determiner.service.DeterminerService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 限定词信息表Controller
 * @author -
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/determiner/determiner")
public class DeterminerController extends BaseController {

	@Autowired
	private DeterminerService determinerService;
	
	@ModelAttribute
	public Determiner get(@RequestParam(required=false) String id) {
		Determiner entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = determinerService.get(id);
		}
		if (entity == null){
			entity = new Determiner();
		}
		return entity;
	}
	
	@RequiresPermissions("determiner:determiner:view")
	@RequestMapping(value = {"list", ""})
	public String list(Determiner determiner, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Determiner> page = determinerService.findPage(new Page<Determiner>(request, response), determiner); 
		model.addAttribute("page", page);
		return "modules/determiner/determinerList";
	}

	@RequiresPermissions("determiner:determiner:view")
	@RequestMapping(value = "form")
	public String form(Determiner determiner, Model model) {
		model.addAttribute("determiner", determiner);
		return "modules/determiner/determinerForm";
	}
	
	@RequiresPermissions("determiner:determiner:view")
	@RequestMapping(value = "view")
	public String view(Determiner determiner, Model model) {
		model.addAttribute("determiner", determiner);
		return "modules/determiner/determinerView";
	}

	@RequiresPermissions("determiner:determiner:edit")
	@RequestMapping(value = "save")
	public String save(Determiner determiner, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, determiner)){
			return form(determiner, model);
		}
		determinerService.save(determiner);
		addMessage(redirectAttributes, "保存限定词信息表成功");
		return "redirect:"+Global.getAdminPath()+"/determiner/determiner/?repage";
	}
	
	@RequiresPermissions("determiner:determiner:view")
	@RequestMapping(value = "edit")
	public String edit(Determiner determiner, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("determiner", determiner);
		User user =  UserUtils.getUser();
		if(!user.isAdmin()&&!user.getName().equals(determiner.getEntryMan())) {
			addMessage(redirectAttributes, "没有修改权限");
			return "redirect:"+Global.getAdminPath()+"/determiner/determiner/?repage"; 
		}
		return "modules/determiner/determinerForm";
	}
	
	@RequiresPermissions("determiner:determiner:edit")
	@RequestMapping(value = "delete")
	public String delete(Determiner determiner, RedirectAttributes redirectAttributes) {
		User user =  UserUtils.getUser();
		if(!user.isAdmin()&&!user.getName().equals(determiner.getEntryMan())) {
			addMessage(redirectAttributes, "没有删除权限");
			return "redirect:"+Global.getAdminPath()+"/determiner/determiner/?repage"; 
		}
		determinerService.delete(determiner);
		addMessage(redirectAttributes, "删除限定词信息表成功");
		return "redirect:"+Global.getAdminPath()+"/determiner/determiner/?repage";
	}

}