/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dataelement.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.ResultDTO;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dataelement.ImportExcelUtil;
import com.thinkgem.jeesite.modules.dataelement.entity.DataElement;
import com.thinkgem.jeesite.modules.dataelement.service.DataElementService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 数据元信息录入Controller
 * @author -
 * @version 2017-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/dataelement/dataElement")
public class DataElementController extends BaseController {
	
	protected Logger logger = LoggerFactory.getLogger(DataElementController.class);
	@Autowired
	private DataElementService dataElementService;
	
	@ModelAttribute
	public DataElement get(@RequestParam(required=false) String id) {
		DataElement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dataElementService.get(id);
		}
		if (entity == null){
			entity = new DataElement();
		}
		return entity;
	}
	
	@RequiresPermissions("dataelement:dataElement:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataElement dataElement, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(dataElement.getZt()!=null&&!dataElement.getZt().equals("")&&dataElement.getZt().equals("全部")) {
			dataElement.setZt("");
		}
		Page<DataElement> page = dataElementService.findPage(new Page<DataElement>(request, response), dataElement); 
		model.addAttribute("page", page);
		return "modules/dataelement/dataElementList";
	}

	@RequiresPermissions("dataelement:dataElement:view")
	@RequestMapping(value = "form")
	public String form(DataElement dataElement, Model model) {
		model.addAttribute("dataElement", dataElement);
		return "modules/dataelement/dataElementForm";
	}
	
	@RequiresPermissions("dataelement:dataElement:view")
	@RequestMapping(value = "view")
	public String view(DataElement dataElement, Model model) {
		model.addAttribute("dataElement", dataElement);
		return "modules/dataelement/dataElementView";
	}

	@RequiresPermissions("dataelement:dataElement:edit")
	@RequestMapping(value = "save")
	public String save(DataElement dataElement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dataElement)){
			return form(dataElement, model);
		}
		dataElementService.save(dataElement);
		addMessage(redirectAttributes, "保存数据元信息录入成功");
		return "redirect:"+Global.getAdminPath()+"/dataelement/dataElement/?repage";
	}
	
	@RequiresPermissions("dataelement:dataElement:view")
	@RequestMapping(value = "edit")
	public String edit(DataElement dataElement, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("dataElement", dataElement);
		User user =  UserUtils.getUser();
		if(!user.isAdmin()&&!user.getName().equals(dataElement.getLrr())) {
			addMessage(redirectAttributes, "没有修改权限");
			return "redirect:"+Global.getAdminPath()+"/dataelement/dataElement/?repage"; 
		}
		return "modules/dataelement/dataElementForm";
	}
	
	@RequiresPermissions("dataelement:dataElement:edit")
	@RequestMapping(value = "delete")
	public String delete(DataElement dataElement, RedirectAttributes redirectAttributes) {
		User user =  UserUtils.getUser();
		if(!user.isAdmin()&&!user.getName().equals(dataElement.getLrr())) {
			addMessage(redirectAttributes, "没有删除权限");
			return "redirect:"+Global.getAdminPath()+"/dataelement/dataElement/?repage"; 
		}
		dataElementService.delete(dataElement);
		addMessage(redirectAttributes, "删除数据元信息录入成功");
		return "redirect:"+Global.getAdminPath()+"/dataelement/dataElement/?repage";
	}
	
	
	@ResponseBody
	@RequestMapping(value="IsExist",produces="application/json")
	public ResultDTO IsExist(Model model,String value,String type) {
		ResultDTO result = new ResultDTO();
		DataElement dataElement = new DataElement();
		if(type.equals("bsf")) {
			dataElement.setBsf(value);
		}else if(type.equals("nbbsf")) {
			dataElement.setNbbsf(value);
		}
		List<DataElement> list = dataElementService.findList(dataElement);
		result.setSuccess(list.size()>0?false:true);
		if(type.equals("bsf")) {
			result.setMessage(list.size()>0?"<font color='red'>标识符已存在</font>":"");
		}else if(type.equals("nbbsf")) {
			result.setMessage(list.size()>0?"<font color='red'>内部标识符已存在</font>":"");
		}
		return result;
	}
	
	@RequiresPermissions("dataelement:dataElement:edit")
	@RequestMapping(value = "download")
	public void downLoadModle(HttpServletRequest request,
            HttpServletResponse response) {
	       try {
	    	   
	    	     String fileName = "DataElementImportTemplate.xlsx".toString();
	    	   	  //获取文件名
	             String filePath = System.getProperty("user.dir" )+"/"+fileName;
	             response.setCharacterEncoding("utf-8");
	             response.setContentType("multipart/form-data");
                 //处理下载弹出框名字的编码问题
	             response.setHeader("Content-Disposition", "attachment;fileName="
	                     + new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
                 //利用输入输出流对文件进行下载
	             InputStream inputStream = new FileInputStream(new File(filePath));
	             OutputStream os = response.getOutputStream();
	             byte[] b = new byte[2048];
	             int length;
	             while ((length = inputStream.read(b)) > 0) {
	                 os.write(b, 0, length);
	             }
	             // 这里主要关闭。
	             os.close();

	             inputStream.close();
	         } catch (FileNotFoundException e) {
	             e.printStackTrace();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	}
	
	@RequiresPermissions("dataelement:dataElement:edit")
	@RequestMapping(value = "import")
	public String importFile(MultipartFile file,RedirectAttributes redirectAttributes) throws Exception {  
		StringBuilder failureMsg = new StringBuilder(); 
        int successNum = 0;  
        int failureNum = 0;  
	    try {  
			List<List<Object>> listob = null;  
			InputStream in = null;  
			if (file.isEmpty()) {  
				 failureMsg.append("文件不存在！");  
		    }  
		    in = file.getInputStream();  
		    listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());  
		    in.close(); 
	        // 遍历数据，保存数据  
	        for (List<Object> obj : listob){  
	        	DataElement element = new DataElement();
	        	element.setNbbsf(obj.get(0).toString());
	            try{ 
	            	List<DataElement> listTemp = dataElementService.findList(element);
	                if (listTemp.size()==0){  
	                	element = this.buildElement(element, obj,failureMsg);
	                	String pzsj = obj.get(18).toString();
	        			if(!pzsj.equals("")&&pzsj!=null) {
	        				if(DataElementController.isRightDateStr(pzsj,"yyyyMMddHHmmss")) {
		        				Date date = DateUtils.parseDate(pzsj, new String[]{"yyyyMMddHHmmss"});  
		        				pzsj = DateFormatUtils.format(date, "yyyy-MM-dd");
	        				}else if(DataElementController.isRightDateStr(pzsj,"yyyy-MM-dd HH:mm:ss")) {
		        				Date date = DateUtils.parseDate(pzsj, new String[]{"yyyy-MM-dd HH:mm:ss"});  
		        				pzsj = DateFormatUtils.format(date, "yyyy-MM-dd");
	        				}else if(DataElementController.isRightDateStr(pzsj,"yyyyMMdd")) {
		        				Date date = DateUtils.parseDate(pzsj, new String[]{"yyyyMMdd"});  
		        				pzsj = DateFormatUtils.format(date, "yyyy-MM-dd");
	        				}else if(DataElementController.isRightDateStr(pzsj,"yyyy-MM-dd")) {
	        					Date date = DateUtils.parseDate(pzsj, new String[]{"yyyy-MM-dd"});  
		        				pzsj = DateFormatUtils.format(date, "yyyy-MM-dd");
	        				}
	        				Date date2 = DateUtils.parseDate(pzsj, new String[]{"yyyy-MM-dd"});
	        				element.setPzsj(date2);
	        			}			
	        			String lrsj = obj.get(27).toString();
	        			if(!lrsj.equals("")&&lrsj!=null) {
	        				if(DataElementController.isRightDateStr(lrsj,"yyyyMMddHHmmss")) {
		        				Date date = DateUtils.parseDate(lrsj, new String[]{"yyyyMMddHHmmss"});  
		        				lrsj = DateFormatUtils.format(date, "yyyy-MM-dd");
	        				}else if(DataElementController.isRightDateStr(lrsj,"yyyy-MM-dd HH:mm:ss")) {
		        				Date date = DateUtils.parseDate(lrsj, new String[]{"yyyy-MM-dd HH:mm:ss"});  
		        				lrsj = DateFormatUtils.format(date, "yyyy-MM-dd");
	        				}else if(DataElementController.isRightDateStr(lrsj,"yyyyMMdd")) {
		        				Date date = DateUtils.parseDate(lrsj, new String[]{"yyyyMMdd"});  
		        				lrsj = DateFormatUtils.format(date, "yyyy-MM-dd");
	        				}else if(DataElementController.isRightDateStr(lrsj,"yyyy-MM-dd")) {
	        					Date date = DateUtils.parseDate(lrsj, new String[]{"yyyy-MM-dd"});  
	        					lrsj = DateFormatUtils.format(date, "yyyy-MM-dd");
	        				}
	        				Date date2 = DateUtils.parseDate(lrsj, new String[]{"yyyy-MM-dd"});
	        				element.setLrsj(date2);
	        			}
	                	dataElementService.save(element);
	                    successNum++;  
	                }else{  
	                    failureMsg.append("<br/>数据元 "+element.getNbbsf()+" "+element.getZwmc()+" 已经存在！");  
	                    logger.error("<br/>数据元 "+element.getNbbsf()+" "+element.getZwmc()+" 已经存在！");
	                    failureNum++;  
	                }  
	            }catch(ConstraintViolationException ex){  
	                failureMsg.append("<br/>数据元 "+element.getNbbsf()+" "+element.getZwmc()+" 导入失败：");  
	                logger.error("<br/>数据元 "+element.getNbbsf()+" "+element.getZwmc()+" 导入失败：");
	            }catch (Exception ex) {  
	                failureMsg.append("<br/>数据元 "+element.getNbbsf()+" "+element.getZwmc()+" 导入失败："+ex.getMessage());  
	                logger.error("<br/>数据元 "+element.getNbbsf()+" "+element.getZwmc()+" 导入失败："+ex.getMessage());
	            }  
	        }  
	        if (failureNum>0){  
	            failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");  
	        }  
	    } catch (Exception e) {  
	    	addMessage(redirectAttributes,"导入数据元失败！失败信息："+e.getMessage());  
	    	logger.error("导入数据元失败！失败信息："+e.getMessage());
	    } 
	    addMessage(redirectAttributes,"已成功导入 "+successNum+" 条用户"+failureMsg);  
	    logger.info("已成功导入 "+successNum+" 条用户"+failureMsg);
	    return "redirect:"+Global.getAdminPath()+"/dataelement/dataElement/?repage";  
	} 
	
	public DataElement buildElement(DataElement el,List<Object> obj,StringBuilder failureMsg) {		
		el.setNbbsf(obj.get(0).toString());
		el.setZwmc(obj.get(1).toString());
		el.setZwqp(obj.get(2).toString());
		el.setBsf(obj.get(3).toString());
		el.setBb(obj.get(4).toString());
		el.setTycmc(obj.get(5).toString());
		el.setSm(obj.get(6).toString());
		el.setDxlx(obj.get(7).toString());
		el.setTxc(obj.get(8).toString());
		el.setBsc(obj.get(9).toString());
		el.setSjlx(obj.get(10).toString());
		el.setBsgs(obj.get(11).toString());
		el.setZy(obj.get(12).toString());
		el.setJldw(obj.get(13).toString());
		el.setGx(obj.get(14).toString());
		String state = obj.get(15).toString();
		el.setZt(state);
		el.setTjjg(obj.get(16).toString());
		el.setZyqcr(obj.get(17).toString());
		el.setBz(obj.get(19).toString());
		el.setYwmc(obj.get(20).toString());
		el.setYj(obj.get(21).toString());
		el.setYyys(obj.get(22).toString());
		el.setFlfa(obj.get(23).toString());
		el.setFlfaz(obj.get(24).toString());
		el.setZcjg(obj.get(25).toString());
		el.setOrigin(obj.get(26).toString());
		el.setLrr(obj.get(28).toString());
		el.setLrdw(obj.get(29).toString());
		el.setLxfs(obj.get(30).toString());
	    el.setSqlx(obj.get(31).toString());
	    el.setSqsm(obj.get(32).toString());
		return el;
	}
	
	public static void main(String[] args) {
	}
	
	
    public static boolean isRightDateStr(String dateStr,String datePattern){
        DateFormat dateFormat  = new SimpleDateFormat(datePattern);
        try {
            //采用严格的解析方式，防止类似 “2017-05-35” 类型的字符串通过
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
            Date date = (Date)dateFormat.parse(dateStr);
            //重复比对一下，防止类似 “2017-5-15” 类型的字符串通过
            String newDateStr = dateFormat.format(date);
            if(dateStr.equals(newDateStr)){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
	
}