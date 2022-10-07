package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserMapper mapper;
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		return mapper.selectUserByLoginId(loginId);
	}

}
