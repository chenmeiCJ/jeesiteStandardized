/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataitem.web;

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
import com.thinkgem.jeesite.modules.dataitem.entity.DataItem;
import com.thinkgem.jeesite.modules.dataitem.service.DataItemService;

/**
 * 数据项信息表Controller
 * @author -
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/dataitem/dataItem")
public class DataItemController extends BaseController {

	@Autowired
	private DataItemService dataItemService;
	
	@ModelAttribute
	public DataItem get(@RequestParam(required=false) String id) {
		DataItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataItemService.get(id);
		}
		if (entity == null){
			entity = new DataItem();
		}
		return entity;
	}
	
	@RequiresPermissions("dataitem:dataItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataItem dataItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataItem> page = dataItemService.findPage(new Page<DataItem>(request, response), dataItem); 
		model.addAttribute("page", page);
		return "modules/dataitem/dataItemList";
	}

	@RequiresPermissions("dataitem:dataItem:view")
	@RequestMapping(value = "form")
	public String form(DataItem dataItem, Model model) {
		model.addAttribute("dataItem", dataItem);
		return "modules/dataitem/dataItemForm";
	}
	
    @RequiresPermissions("dataitem:dataItem:view")
	@RequestMapping(value = "view")
	public String view(DataItem dataItem, Model model) {
		model.addAttribute("dataItem", dataItem);
		return "modules/dataitem/dataItemView";
	}

	@RequiresPermissions("dataitem:dataItem:edit")
	@RequestMapping(value = "save")
	public String save(DataItem dataItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataItem)){
			return form(dataItem, model);
		}
		Integer sort = dataItemService.getMaxSort();
		dataItem.setSort(sort+1);
		dataItemService.save(dataItem);
		addMessage(redirectAttributes, "保存数据项信息表成功");
		return "redirect:"+Global.getAdminPath()+"/dataitem/dataItem/?repage";
	}
	
	@RequiresPermissions("dataitem:dataItem:edit")
	@RequestMapping(value = "delete")
	public String delete(DataItem dataItem, RedirectAttributes redirectAttributes) {
		dataItemService.delete(dataItem);
		addMessage(redirectAttributes, "删除数据项信息表成功");
		return "redirect:"+Global.getAdminPath()+"/dataitem/dataItem/?repage";
	}

}