package com.prueba2;

/**
 * Clase derivada de la clase Entrada.
 * Usa valor diferentes para calcular el precio.
 * @author Murilo
 *
 */
public class EntradaAbonado  extends Entrada {


	/**
	 * Contructor da classe EntradaAbonado.
	 * @param id Número de identificación.
	 * @param zona Nombre de la zona
	 * @param nombre Nombre del comprador.
	 */
	public EntradaAbonado(int id, String zona, String nombre) {
		super(id, zona, nombre);

	}

	/**
	 * Calcula el precio de la entrada de acuerdo com la zona.
	 * @return O valor final "precio" de la entrada.
	 */
	@Override
	protected double CalcularPrecio()
	{
		double d = 0;
		switch(getZona())
		{
		case "Principal": d = Precios.PRINCIPAL_ABONADO; break;
		case "Palco": d = Precios.PALCO_ABONADO; break;
		case "Central": d = Precios.CENTRAL_ABONADO; break;
		case "Lateral": d = Precios.LATERAL_ABONADO; break;
		}
		return d;
	}
}