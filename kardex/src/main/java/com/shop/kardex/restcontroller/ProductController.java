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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shop.kardex.dao.ProductDAO;
import com.shop.kardex.dto.ProductDTO;
import com.shop.kardex.dto.ResponseDTO;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private ProductDAO productDAO;

	public ProductController(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
		try {
			logger.info("START SAVE PRODUCT {}",productDTO.getName());
			this.productDAO.save(productDTO);
		} catch (Exception e) {
			return new ResponseEntity<String>("ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllItems(){
		try {
			List<ProductDTO> listItems = this.productDAO.getAllItems();
			return new ResponseEntity<>(listItems,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
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
