package com.weigo.visitor.service;

import java.util.Date;
import java.util.List;

import com.weigo.commons.pojo.VisitorPage;

public interface TbVisitorService {

	void insertVisitor(Integer managepage);
	List<VisitorPage> selectVisitorConunt(Date date);
}
