package com.weigo.item.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PicService {

	Map<String, Object> upload(MultipartFile mf) throws IOException;

}
