package com.shop.kardex.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.shop.kardex.dto.UserDTO;
import com.shop.kardex.entity.User;
import com.shop.kardex.repository.UserRepository;

@Repository
public class UserDAOI implements UserDAO {

	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDAOI(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void save(UserDTO dto) {
		User u = new User();
		u.setName(dto.getName());
		u.setLastName(dto.getLastName());
		u.setEmail(dto.getEmail());
		u.setPassword(passwordEncoder.encode(dto.getPassword()));
		u.setPhone(dto.getPhone());
		u.setAddress(dto.getAddress());
		userRepository.save(u);
	}

	@Override
	public boolean userExists(String email) {
		User u = this.userRepository.findByEmail(email);
		return u == null ? false : true;
	}

}
