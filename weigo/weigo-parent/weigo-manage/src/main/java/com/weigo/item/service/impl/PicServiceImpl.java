package com.weigo.item.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.weigo.commons.utils.FtpUtil;
import com.weigo.item.service.PicService;

@Service
public class PicServiceImpl implements PicService {
    
	@Value("${ftpclient.host}")
	private String host;
	@Value("${ftpclient.port}")
	private int port;
	@Value("${ftpclient.username}")
	private String username;
	@Value("${ftpclient.password}")
	private String password;
	@Value("${ftpclient.basepath}")
	private String basePath;
	@Value("${ftpclient.filepath}")
	private String filePath;
	
	@Override
	public Map<String, Object> upload(MultipartFile mf) throws IOException {
		
		
		String fileName = UUID.randomUUID().toString()+mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
		boolean ok = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, fileName, mf.getInputStream());
		System.out.println("url=http://"+ host+"/"+fileName);
		Map<String, Object> map = new HashMap<String, Object>();
		if(ok) {
			map.put("error", 0);
			map.put("url","http://"+ host+"/"+fileName);
		}else {
			map.put("error", 1);
			map.put("message", "ͼƬ�ϴ�ʧ��");
		}
		return map;
	}

}
