package com.prueba2;

/**
 * Clase base para las entradas que se van a vender.
 * Funciona como el boleto m�s simple y como modelo para sus variantes.
 * @author Murilo
 *
 */
public class Entrada {

	/**
	 * N�mero de identificaci�n.
	 */
	private int id;
	/**
	 * Nombre del comprador.
	 */
	private String nombre;
	/**
	 * Nombre de la zona
	 */
	private String zona;
	/**
	 * Valor de la entrada
	 */
	private double precio;
	
	/**
	 * Contructor da classe Entrada.
	 * @param id N�mero de identificaci�n.
	 * @param zona Nombre de la zona
	 * @param nombre Nombre del comprador.
	 */
	public Entrada(int id, String zona, String nombre)
	{
		this.id = id;
		this.zona = zona;
		this.nombre = nombre;
		
		precio = CalcularPrecio();
	}
	
	/**
	 * Calcula el precio de la entrada de acuerdo com la zona.
	 * @return O valor final "precio" de la entrada.
	 */
	protected double CalcularPrecio()
	{
		double d = 0;
		switch(getZona())
		{
		case "Principal": d = Precios.PRINCIPAL_NORMAL; break;
		case "Palco": d = Precios.PALCO_NORMAL; break;
		case "Central": d = Precios.CENTRAL_NORMAL; break;
		case "Lateral": d = Precios.LATERAL_NORMAL; break;
		}
		return d;
	}
	
	/**
	 * Descripci�n de las informaciones de la entrada.
	 * @return Texto de descripci�n.
	 */
	public String getInfo()
	{
		String text = "";
		
		text += "Entrada: " + id;
		text += " |Zona: " + zona;
		text += " |Espectador: " + nombre;
		text += " |Precio: " + precio + "�";
		
		return text;
	}

	/**
	 * Funci�n GET de ID.
	 * @return Valor del  ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Funci�n GET de Nombre.
	 * @return Valor del nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Funci�n GET de zona.
	 * @return Valor del zona.
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * Funci�n GET de precio.
	 * @return Valor del precio.
	 */
	public double getPrecio() {
		return precio;
	}
}
