package br.com.fiap.exception;

public class IdNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public IdNotFoundException() {
	}

	public IdNotFoundException(String msg) {
		super(msg);
	}

}
