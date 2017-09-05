/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataelementprocess.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dataelementprocess.entity.DataElementProcess;
import com.thinkgem.jeesite.modules.oa.entity.TestAudit;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.dataelementprocess.dao.DataElementProcessDao;

/**
 * 数据元审批Service
 * 
 * @author -
 * @version 2017-08-29
 */
@Service
@Transactional(readOnly = true)
public class DataElementProcessService extends CrudService<DataElementProcessDao, DataElementProcess> {

	@Autowired
	private ActTaskService actTaskService;

	public DataElementProcess get(String id) {
		return super.get(id);
	}

	public List<DataElementProcess> findList(DataElementProcess dataElementProcess) {
		return super.findList(dataElementProcess);
	}

	public Page<DataElementProcess> findPage(Page<DataElementProcess> page, DataElementProcess dataElementProcess) {
		return super.findPage(page, dataElementProcess);
	}

	@Transactional(readOnly = false)
	public void save(DataElementProcess dataElementProcess) {

		// 申请发起
		if (StringUtils.isBlank(dataElementProcess.getId())) {
			dataElementProcess.preInsert();
			super.save(dataElementProcess);

			// 启动流程
			actTaskService.startProcess(ActUtils.PD_DATAELEMENT_PROCESS[0], ActUtils.PD_DATAELEMENT_PROCESS[1],
					dataElementProcess.getId(), "数据元申报");

		}
		// 重新编辑申请
		else {
			dataElementProcess.preUpdate();
			dao.update(dataElementProcess);

			dataElementProcess.getAct()
					.setComment(("yes".equals(dataElementProcess.getAct().getFlag()) ? "[重申] " : "[销毁] ")
							+ dataElementProcess.getAct().getComment());

			// 完成流程任务
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(dataElementProcess.getAct().getFlag()) ? "1" : "0");
			actTaskService.complete(dataElementProcess.getAct().getTaskId(), dataElementProcess.getAct().getProcInsId(),
					dataElementProcess.getAct().getComment(), "数据元申报", vars);
		}
	}

	/**
	 * 审核审批保存
	 * 
	 * @param testAudit
	 */
	@Transactional(readOnly = false)
	public void auditSave(DataElementProcess dataElementProcess) {

		// 设置意见
		dataElementProcess.getAct().setComment(("yes".equals(dataElementProcess.getAct().getFlag()) ? "[同意] " : "[驳回] ")
				+ dataElementProcess.getAct().getComment());

		dataElementProcess.preUpdate();

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = dataElementProcess.getAct().getTaskDefKey();

		// 编写数据元
		if ("write".equals(taskDefKey)) {
			//view = "dataElementProcessForm";
			dao.update(dataElementProcess);
		}
		// 整理汇总
		else if ("zhenglihuizong".equals(taskDefKey)) {
			dao.update(dataElementProcess);
			//view = "dataElementProcessAudit";
			// String formKey = "/oa/testAudit";
			// return "redirect:" + ActUtils.getFormUrl(formKey, testAudit.getAct());
		}
		// 形式审查
		else if ("xingshishencha".equals(taskDefKey)) {
			//view = "dataElementProcessAudit";
			dao.update(dataElementProcess);
		}
		// 技术初审
		else if ("jishuchushen".equals(taskDefKey)) {
			//view = "dataElementProcessAudit";
			dao.update(dataElementProcess);
		}
		// 征求意见
		else if ("zhengquiyijian".equals(taskDefKey)) {
			//view = "dataElementProcessAudit";
			dao.update(dataElementProcess);
		}
		// 整理征求意见结果
		else if ("zhenglizhengqiuyijian".equals(taskDefKey)) {
			//view = "dataElementProcessAudit";
			dao.update(dataElementProcess);
		}
		// 技术终审
		else if ("jishuzhongshen".equals(taskDefKey)) {
			//view = "dataElementProcessAudit";
			dao.update(dataElementProcess);
		}
		// 审批
		else if ("shenpi".equals(taskDefKey)) {
			//view = "dataElementProcessAudit";
			dao.update(dataElementProcess);
		}
		// 公布标准数据元
		else if ("publish".equals(taskDefKey)) {
			//view = "dataElementProcessAudit";
			dao.update(dataElementProcess);
		}

		// 未知环节，直接返回
		else {
			return;
		}

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(dataElementProcess.getAct().getFlag()) ? "1" : "0");
		actTaskService.complete(dataElementProcess.getAct().getTaskId(), dataElementProcess.getAct().getProcInsId(),
				dataElementProcess.getAct().getComment(), vars);

		// vars.put("var_test", "yes_no_test2");
		// actTaskService.getProcessEngine().getTaskService().addComment(testAudit.getAct().getTaskId(),
		// testAudit.getAct().getProcInsId(), testAudit.getAct().getComment());
		// actTaskService.jumpTask(testAudit.getAct().getProcInsId(),
		// testAudit.getAct().getTaskId(), "audit2", vars);
	}

	@Transactional(readOnly = false)
	public void delete(DataElementProcess dataElementProcess) {
		super.delete(dataElementProcess);
	}

}