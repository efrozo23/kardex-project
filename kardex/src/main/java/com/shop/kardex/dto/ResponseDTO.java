package com.shop.kardex.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseDTO {
	
	private String mensaje;
	private List<?> errores = new ArrayList<>();;
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<?> getErrores() {
		return errores;
	}
	public void setErrores(List<?> errores) {
		this.errores = errores;
	}

}
