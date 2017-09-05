/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataelementprocess.web;

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
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.dataelementprocess.entity.DataElementProcess;
import com.thinkgem.jeesite.modules.dataelementprocess.service.DataElementProcessService;
import com.thinkgem.jeesite.modules.oa.entity.TestAudit;

/**
 * 数据元审批Controller
 * @author -
 * @version 2017-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/dataelementprocess/dataElementProcess")
public class DataElementProcessController extends BaseController {

	@Autowired
	private DataElementProcessService dataElementProcessService;
	
	@ModelAttribute
	public DataElementProcess get(@RequestParam(required=false) String id) {
		DataElementProcess entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataElementProcessService.get(id);
		}
		if (entity == null){
			entity = new DataElementProcess();
		}
		return entity;
	}
	
	@RequiresPermissions("dataelementprocess:dataElementProcess:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataElementProcess dataElementProcess, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DataElementProcess> page = dataElementProcessService.findPage(new Page<DataElementProcess>(request, response), dataElementProcess); 
		model.addAttribute("page", page);
		return "modules/dataelementprocess/dataElementProcessList";
	}

	
	/**
	 * 申请单填写
	 * @param dataElementProcess
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("dataelementprocess:dataElementProcess:view")
	@RequestMapping(value = "form")
	public String form(DataElementProcess dataElementProcess, Model model) {
		String view = "dataElementProcessForm";
		// 查看审批申请单
		if (StringUtils.isNotBlank(dataElementProcess.getId())){//.getAct().getProcInsId())){

			if(StringUtils.isNoneBlank(dataElementProcess.getNbbsf())) {
				Act act = dataElementProcess.getAct();
				dataElementProcess = dataElementProcessService.get(dataElementProcess);
				dataElementProcess.setAct(act);	
			}
			
			// 环节编号
			String taskDefKey = dataElementProcess.getAct().getTaskDefKey();
			
			// 查看工单
			if(dataElementProcess.getAct().isFinishTask()){
				view = "dataElementProcessView";
			}
			// 编写数据元
			else if ("write".equals(taskDefKey)){
				view = "dataElementProcessForm";
			}
			// 整理汇总
			else if ("zhenglihuizong".equals(taskDefKey)){
				view = "dataElementProcessAudit";
//				String formKey = "/oa/testAudit";
//				return "redirect:" + ActUtils.getFormUrl(formKey, testAudit.getAct());
			}
			// 形式审查
			else if ("xingshishencha".equals(taskDefKey)){
				view = "dataElementProcessAudit";
			}
			// 技术初审
			else if ("jishuchushen".equals(taskDefKey)){
				view = "dataElementProcessAudit";
			}
			// 征求意见
			else if ("zhengquiyijian".equals(taskDefKey)){
				view = "dataElementProcessAudit";
			}
			// 整理征求意见结果
			else if ("zhenglizhengqiuyijian".equals(taskDefKey)){
				view = "dataElementProcessAudit";
			}
			// 技术终审
			else if ("jishuzhongshen".equals(taskDefKey)){
				view = "dataElementProcessAudit";
			}
			// 审批
			else if ("shenpi".equals(taskDefKey)){
				view = "dataElementProcessAudit";
			}
			// 公布标准数据元
			else if ("publish".equals(taskDefKey)){
				view = "dataElementProcessAudit";
			}
		}
		model.addAttribute("dataElementProcess", dataElementProcess);
		return "modules/dataelementprocess/"+view;
	}

	/**
	 * 申请单保存/修改
	 * @param dataElementProcess
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("dataelementprocess:dataElementProcess:edit")
	@RequestMapping(value = "save")
	public String save(DataElementProcess dataElementProcess, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataElementProcess)){
			return form(dataElementProcess, model);
		}
		dataElementProcessService.save(dataElementProcess);
		addMessage(redirectAttributes, "提交审批成功");
		//return "redirect:"+Global.getAdminPath()+"/dataelementprocess/dataElementProcess/?repage";
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	
	/**
	 * 工单执行（完成任务）
	 * @param testAudit
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:testAudit:edit")
	@RequestMapping(value = "saveAudit")
	public String saveAudit(DataElementProcess dataElementProcess, Model model) {
		if (!beanValidator(model, dataElementProcess)){
			return form(dataElementProcess, model);
		}
		dataElementProcessService.auditSave(dataElementProcess);
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	
	
	@RequiresPermissions("dataelementprocess:dataElementProcess:edit")
	@RequestMapping(value = "delete")
	public String delete(DataElementProcess dataElementProcess, RedirectAttributes redirectAttributes) {
		dataElementProcessService.delete(dataElementProcess);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/dataelementprocess/dataElementProcess/?repage";
	}

}