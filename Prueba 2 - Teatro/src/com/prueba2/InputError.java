package com.prueba2;

/**
 * Clase de excepci�n personalizada.
 * @author Murilo
 *
 */
public class InputError extends Exception {


	private static final long serialVersionUID = 1L;
	
	/**
	 * Excepci�n personalizada.
	 * @param param
	 */
	public InputError(String param)
	{
		super(param);
	}
}
