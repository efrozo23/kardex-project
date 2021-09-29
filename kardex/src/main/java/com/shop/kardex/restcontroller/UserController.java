package com.shop.kardex.restcontroller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shop.kardex.dao.UserDAO;
import com.shop.kardex.dto.ResponseDTO;
import com.shop.kardex.dto.UserDTO;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private UserDAO userDao;

	public UserController(UserDAO userDAO) {
		this.userDao = userDAO;
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO userDTO) {
		logger.info("User : {}", userDTO.getName());
		if(userDao.userExists(userDTO.getEmail())) {
			return new ResponseEntity<String>("USER ALREADY EXIST",HttpStatus.BAD_REQUEST);
		}
		this.userDao.save(userDTO);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseDTO handleValidationExceptions(MethodArgumentNotValidException ex){
		ResponseDTO r = new ResponseDTO();
		r.setMensaje("ERROR");
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
		r.setErrores(Arrays.asList(errors));
		return r;
		
	}

}
