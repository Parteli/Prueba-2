package com.prueba2;
import java.util.ArrayList;

/**
 * Clase responsable por crear, guardar y buscar informaciones de Entradas.
 * @author Murilo
 *
 */
public class GestorDeEntradas {

	/**
	 * Lista de entradas vendidas.
	 */
	private ArrayList<Entrada> lista;

	/**
	 * Cantidad asientos ocupados en la zona Principal.
	 */
	private int capacidadPrincipal = 0;
	/**
	 * Cantidad asientos ocupados en la zona Palco.
	 */
	private int capacidadPalco = 0;
	/**
	 * Cantidad asientos ocupados en la zona Central.
	 */
	private int capacidadCentral = 0;
	/**
	 * Cantidad asientos ocupados en la zona Larteral.
	 */
	private int capacidadLateral = 0;
	
	/**
	 * Constructor de la clase GestorDeEntradas
	 */
	public GestorDeEntradas()
	{
		lista = new ArrayList<Entrada>();
	}	

	/**
	 * Crea una nueva Entrada, insere ella en la lista y 
	 * actualiza los valores de cantidad de asientos ocupados.
	 * @param zona Nombre de la zona
	 * @param nombre Nombre del comprador
	 * @param abono Tipo de abono
	 */
	public void vendeEntrada(String zona, String nombre, String abono)
	{
		Entrada e = null;
		switch(abono)
		{
		case "Reducido": e = new EntradaReducida(lista.size(), zona, nombre); break;
		case "Abonado": e = new EntradaAbonado(lista.size(), zona, nombre); break;
		default: e = new Entrada(lista.size(), zona, nombre); break;
		}
		
		lista.add(e);
		
		switch(zona)
		{
		case "Principal": capacidadPrincipal++; break;
		case "Palco": capacidadPalco++; break;
		case "Central": capacidadCentral++; break;
		case "Lateral": capacidadLateral++; break;
		}
		
		System.out.println( e.getInfo() );
	}
	
	/**
	 * Publica las informaciones de la zona seleccionada.
	 * @param zona Nombre de la zona.
	 */
	public void informeZona(String zona)
	{
		
		String text = "*** Zona " + zona + " ***\n";
		double p = 0;
		int count = 0;
		for (Entrada entrada : lista) {
			if(entrada.getZona() == zona)
			{
				//text += entrada.getInfo() + "\n";
				p += entrada.getPrecio();
				count ++;
			}
		}
		text += "Total de localidades vendidas: " + count + "\n";
		text += "Total de recaudación: " + p + "\n______________________";
		
		System.out.println(text);
	}
	
	/**
	 * Publica las informaciones de Entrada seleccionada.
	 * @param id Número de ID seleccionado
	 * @throws InputError Caso no ha Entrada con el ID seleccionado.
	 */
	public void consultaEntrada(int id) throws InputError
	{
		if(lista.size() > id) System.out.println( lista.get(id).getInfo() );
		else throw new InputError("ERROR: Boleto de Entrada no se ha encontrado.");
	}
	
	/**
	 * Publica las informaciones de todas las Entradas que contengan el texto inserido.
	 * @param nombre Texto usado para la búsqueda.
	 * @throws InputError Caso no ha Entrada que contenga el texto.
	 */
	public void consultaEntradaNombre(String nombre) throws InputError
	{
		boolean check = false;
		for (Entrada entrada : lista) {
			if(entrada.getNombre().contains(nombre))
			{
				System.out.println( entrada.getInfo() );
				check = true;
			}
		}

		if(!check) throw new InputError("ERROR: Boleto de Entrada no se ha encontrado.");
	}

	/**
	 * Publica las informaciones de todas las Entradas que están en la zona seleccionada.
	 * @param zona Nombre de la zona.
	 * @throws InputError Caso no ha Entrada que contenga el texto.
	 */
	public void consultaEntradaZona(String zona) throws InputError
	{
		boolean check = false;
		for (Entrada entrada : lista) {
			if(entrada.getZona() == zona)
			{
				System.out.println(entrada.getInfo() );
				check = true;
			}
		}
		
		if(!check) throw new InputError("ERROR: Boleto de Entrada no se ha encontrado.");
	}

	/**
	 * Publica las informaciones de todas las Entradas.
	 */
	public void consultaEntradaListado()
	{
		for (Entrada entrada : lista) {
			System.out.println(entrada.getInfo() );
		}
		if(lista.size() == 0 ) System.out.println("No vendido ninguna entrada.");
	}
	
	/**
	 * Comproba si a zona seleccionada está llena.
	 * @param zona Nombre de la zona
	 * @return Verdadero si tiene asientos disponibles aún.
	 * @throws InputError Caso el nombre de la zona escrito no sea encontrado. 
	 */
	public boolean comprobarCapacidad(String zona) throws InputError
	{
		switch(zona)
		{
		case "Principal": return capacidadPrincipal<Precios.PRINCIPAL_LOCALIDADES;
		case "Palco": return capacidadPalco<Precios.PALCO_LOCALIDADES;
		case "Central": return capacidadCentral<Precios.CENTRAL_LOCALIDADES;
		case "Lateral": return capacidadLateral<Precios.LATERAL_LOCALIDADES;
		default: throw new InputError("Zona no se ha encontrado!");
		}
	}
}
