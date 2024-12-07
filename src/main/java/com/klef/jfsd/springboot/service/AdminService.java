package com.klef.jfsd.springboot.service;

import java.util.Map;

import com.klef.jfsd.springboot.model.Admin;

public interface AdminService {
	public Map<String, Object> verifyAdmin(Admin admin);
}
