package com.prueba2;

/**
 * Clase de excepción personalizada.
 * @author Murilo
 *
 */
public class InputError extends Exception {


	private static final long serialVersionUID = 1L;
	
	/**
	 * Excepción personalizada.
	 * @param param
	 */
	public InputError(String param)
	{
		super(param);
	}
}
