package com.weigo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbUserAddress;
import com.weigo.user.service.TbAddressService;

@Controller
public class TbAddressController {
	@Autowired
	private TbAddressService tbAddressService;
	@RequestMapping("/address/insert")
	@ResponseBody
	public MessageObject insertAddress(TbUserAddress tbUserAddress) {
		
		return tbAddressService.insertAddressByTbUserAddress(tbUserAddress);
	}
	
    @RequestMapping("/manage/showAddress")
    public String showAddress(Model model,Long id) {
    	 TbUserAddress tbUserAddress= tbAddressService.getAddress(id);
    	 model.addAttribute("address", tbUserAddress);
    	 return "address";
    }
    @RequestMapping("/manage/address/update")
    @ResponseBody
    public MessageObject updateAddressByTbUserAddress(TbUserAddress tbUserAddress) {
    	MessageObject mo = new MessageObject();
    	mo.setMsg("修改失败");
    	int ok = this.tbAddressService.updateAddressByTbUserAddress(tbUserAddress);
    	mo.setCode(6);
    	if(ok==1) {
    		mo.setMsg("修改成功");
    	}
    	return mo;
    }
    @RequestMapping("/manage/address/delete")
    @ResponseBody
    public Object deleteAddressById(Long id,String callback) {
    	MessageObject mo = this.tbAddressService.deleteAddressById(id);
    	//如果不是jsonp方式
         if(callback==null||"".equals(callback)) {
    	return mo;	
    	}
    	MappingJacksonValue mjv = new MappingJacksonValue(mo);
    	mjv.setJsonpFunction(callback);
    	return mjv;
    }
    @RequestMapping("/sellerOrder/selectAddress/list")
    public String sellerShowAddress(Model model,Long orderItemId) {
    	List<TbUserAddress> lsit=tbAddressService.selectAddressByUid();
    	model.addAttribute("addressList", lsit);
    	model.addAttribute("orderItemId", orderItemId);
    	 return "selectAddress";
    }
}
