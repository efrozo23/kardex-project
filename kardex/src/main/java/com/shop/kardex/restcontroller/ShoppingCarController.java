package com.shop.kardex.restcontroller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shop.kardex.dao.ProductDAO;
import com.shop.kardex.dao.ShoppingCarDAO;
import com.shop.kardex.dao.UserDAO;
import com.shop.kardex.dto.ResponseDTO;
import com.shop.kardex.dto.ShoppingCarDTO;

@RestController
@RequestMapping("/shoppingcar")
public class ShoppingCarController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ProductDAO productDAO;
	private UserDAO userDAO;
	private ShoppingCarDAO shoppingCarDAO;
	public ShoppingCarController(ProductDAO productDAO, UserDAO userDAO, ShoppingCarDAO shoppingCarDAO) {
		this.productDAO = productDAO;
		this.userDAO = userDAO;
		this.shoppingCarDAO = shoppingCarDAO;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ShoppingCarDTO dto){
		try {
			this.logger.info("START SAVE IN SHOPPING CAR {}",dto.getUserDTO().getEmail());
			if(!this.userDAO.userExists(dto.getUserDTO().getEmail())) {
				return new ResponseEntity<String>("USER NOT FOUND",HttpStatus.BAD_REQUEST);
			}
			if(!this.productDAO.containsStock(dto.getProductDTO().getId(), dto.getQuantity())) {
				return new ResponseEntity<String>("INSUFFICIENT ITEMS",HttpStatus.BAD_REQUEST);
			}
			this.shoppingCarDAO.save(dto);
		} catch (Exception e) {
			return new ResponseEntity<String>("ERROR" + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping("/get_items")
	public ResponseEntity<?> getItemsByUser(@RequestParam("email") String email){
		try {
			this.logger.info("START GET ITEMS: {}", email);
			List<ShoppingCarDTO> listItems = this.shoppingCarDAO.getItemsByEmail(email);
			return new ResponseEntity<List<ShoppingCarDTO>>(listItems,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("ERROR" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
