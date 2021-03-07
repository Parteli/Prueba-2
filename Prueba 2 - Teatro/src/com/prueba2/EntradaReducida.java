package com.prueba2;

/**
 * Clase derivada de la clase Entrada.
 * Usa valor diferentes para calcular el precio.
 * @author Murilo
 *
 */
public class EntradaReducida extends Entrada {

	/**
	 * Contructor da classe EntradaReducida.
	 * @param id Número de identificación.
	 * @param zona Nombre de la zona
	 * @param nombre Nombre del comprador.
	 */
	public EntradaReducida(int id, String zona, String nombre) {
		super(id, zona, nombre);
	}

	/**
	 * Calcula el precio de la entrada de acuerdo com la zona.
	 * @return O valor final "precio" de la entrada.
	 */
	@Override
	protected double CalcularPrecio()
	{
		return super.CalcularPrecio() * 0.85;
	}
}
