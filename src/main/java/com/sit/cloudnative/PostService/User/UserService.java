package com.sit.cloudnative.PostService.User;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User create(@Valid User user) {
		return userRepository.save(user);
	}

	public User getUserById(long id){
		return userRepository.getOne(id);
	}
}