package com.example.demo.contollers;

//import java.util.Arrays;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	//@GetMapping
    //public String getUsers() {
	// return "Hello api";
  //}
	
	@GetMapping
	  public List<UserEntity> getUsers() {
		 //return Arrays.asList(new User(1l,"john","john@gmail.com"),new User(2l,"joe","joe@gmail.com"),new User(3l,"sa","sa@gmail.com"));
		return userRepository.findAll();
	  }
	
	    @PostMapping
	    public UserEntity createUser(@RequestBody UserEntity user) {
		//System.out.println("User Data:"+user.getName()+","+user.getEmail());
		return userRepository.save(user);
	}
		@GetMapping("/{id}")
		public UserEntity getUserById(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found this id:"+id));
		}

		@PutMapping("/{id}")
		public UserEntity updateUser(@PathVariable Long id,@RequestBody UserEntity user) {
			UserEntity userData= userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found this id:"+id));
		    userData.setEmail(user.getEmail());
		    userData.setName(user.getName());
		    return userRepository.save(userData);
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deleteUser(@PathVariable Long id) {
			UserEntity userData=userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found this id:"+id));
	        userRepository.delete(userData);
	        return ResponseEntity.ok().build();
		}
	}

