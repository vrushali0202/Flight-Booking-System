package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.entity.UserData;
import com.user.exception.UserDataException;
import com.user.repository.UserDataRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImp implements UserDetailsService {
	@Autowired
	private UserDataRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserData> userData = repo.findByName(username);
		return userData.map(UserDataDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
	}

	public String addUser(UserData userInfo) {
//		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
//		repo.save(userInfo);
//		return "User added successfully";
		boolean isUserPresent=false;
		String result;
		List<UserData> allData=getAllUser();
		for(UserData data:allData) {
			if(data.getUserName().equals(userInfo.getUserName())) {
				 isUserPresent=true;
				 break;
				//throw new UserDataException("user already prsesnt");
			}
			else {
				isUserPresent=false;
			}
		}
		
		if(isUserPresent==false) {
			userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
			repo.save(userInfo);
			result= "User added successfully";
		}else {
			result="user already present";
		}
		
		return result;
	}

	public List<UserData> getAllUser() {
		return repo.findAll();
	}

	public Optional<UserData> getUser(String username) {
		return repo.findByName(username);
	}
}