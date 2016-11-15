package com.cxf.controller;

import java.io.UnsupportedEncodingException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cxf.service.BCertService;
import rpcservice.RemoteService;

@Controller
@RequestMapping("/rcp")
public class TestController {
	
	private final Logger log=LoggerFactory.getLogger(TestController.class);
	@Resource(name = "bCertService")
	private BCertService bCertService;
	/**
	 * testPage.jsp通过【报文】直接访问公安接口(身份证)
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/CZRK",method = RequestMethod.POST)
	public String CZRK(HttpServletRequest request,HttpServletResponse response,Model model){
		String paraStr = request.getParameter("paras");
		try {
			paraStr = new String(paraStr.getBytes("ISO-8859-1"),"GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		log.info("页面接收到的请求报文==="+paraStr);
		String result = "";
		
		String paraStr1 = "{\"paras\":{\"IDNUMBER\":\""+paraStr+"\"}}";
		result = bCertService.GetDetailBcertSfzInfo(paraStr1);
		
		//result =  RemoteService.sendMessage(paraStr);
		log.info("调用本地服务（内含调用公安接口）返回数据===");
		log.info(result);
		model.addAttribute("paras",paraStr);
		model.addAttribute("result",result);
		return "sfzTest";
	}
	
	/**
	 * 通过【身份证号】调用本地服务访问公安接口（常住人口）
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getHKBDetail",method = RequestMethod.POST)
	public String getHKBDetail(HttpServletRequest request,HttpServletResponse response,Model model){
		String paraStr = request.getParameter("paras");
		String data = "{\"paras\":{\"IDNUMBER\":\""+paraStr+"\"}}";
		log.info("通过身份证拼接json字符串==="+data);
		String result = "";
		result =  bCertService.GetDetailBcertHkbInfo(data);
		log.info("调用本地服务（内含调用公安接口）返回数据==="+result);
		model.addAttribute("result",result);
		model.addAttribute("paras",paraStr);
		return "hkbTest";
	}
	
	/**
	 * 通过报文，测试请求报文拼写是否正确
	 * @author 王磊
	 * @Date Jul 26, 2016 10:22:33 AM
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getInfoByReq",method = RequestMethod.POST)
	public String getInfoByReq(HttpServletRequest request,HttpServletResponse response,Model model){
		String paraStr = request.getParameter("paras");
		String result = RemoteService.sendMessage(paraStr);
		model.addAttribute("paras",paraStr);
		model.addAttribute("result",result);
		return "reqTest";
	}
	
	/**
	 * 通过身份证，调用公安接口
	 * @author 王磊
	 * @Date Jul 26, 2016 10:22:33 AM
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getImageByIdCard",method = RequestMethod.POST)
	public String getImageByIdCard(HttpServletRequest request,HttpServletResponse response,Model model){
		String paraStr = request.getParameter("paras");
		String result = RemoteService.remoteZpByCardId(paraStr);
		model.addAttribute("paras",paraStr);
		model.addAttribute("result",result);
		return "imageTest";
	}

}
