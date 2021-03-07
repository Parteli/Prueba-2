package com.prueba2;

import java.util.Scanner;

/**
 * Clase responsable por controlar la interfaz del sistema.
 * @author Murilo
 *
 */
public class SistemaVentas {

	/**
	 * Referencia del GestorDeEntradas.
	 */
	private GestorDeEntradas gestor = new GestorDeEntradas();
	
	/**
	 * La persona responsable de operar la terminal.
	 */
	private String cajero;
	/**
	 * Función GET para el 'cajero'.
	 * @return el nombre del 'cajero'.
	 */
	public String getCajero() {return cajero;}


	/**
	 * Referencia del Scanner.
	 */
	private Scanner scanner;
	
	/**
	 * Constructor del SistemaVentas
	 * @param cajero Nombre del operador del terminal.
	 */
	public SistemaVentas(String cajero) {
		
		this.cajero = cajero;
		

		scanner = new Scanner(System.in);
		
		
		System.out.println("Bien venido " + cajero + "!"
				+ "\nEl sistema de ventas estás listo para uso."
				+ "\n************************\n");
		
		MenuPrincipal();
	}

	
	/**
	 * El Menú Principal permite al usuario verificar la información sobre 
	 * las Zonas y Entradas. También puede iniciar el proceso de venta.
	 */
	private void MenuPrincipal(){
		String[] messages = new String[5];
		
		messages[0] = "····························\nSeleccione la acción necesaria.";
		messages[1] = "Venta de Entrada.";
		messages[2] = "Consulta de Entrada.";
		messages[3] = "Informe de Zona.";
		messages[4] = "Cerrar sistema.";
		
		while(true)
		{
			int input = LoopMenu(messages, true);
			
			switch(input) {
			case 1: 
				if( MenuVentaEntrada() ) {
					System.out.println(" $ La entrada se vendió con éxito. $");
				} else {
					System.out.println("Venta de entradas cancelada.");
				}
				break;
			case 2: MenuConsultaEntrada(); break;
			case 3: MenuConsultaZona(); break;
			case 0: System.exit(0); return;
			}
		}
	}

	/**
	 * Menu responsable por guiar el operador pelo proceso de venta.
	 * @return Verdadero si la venta se sucedió.
	 */
	private boolean MenuVentaEntrada()
	{
		
		System.out.println("Escriba el nombre del comprador: ");
		
		String nombre = scanner.next();

		if(nombre.isBlank() || nombre == null) {
			System.out.println("No se escribió ningún nombre. Redirigiendo al menú principal.");
			return false;
		}
		
		String zona = "";

		String[] messages;
		
		while(zona == "")
		{
			zona = EligeZona();
			
			try {
				if( !gestor.comprobarCapacidad(zona) )
				{
					zona = "";
					
					messages = new String[3];
					
					messages[0] = "¡Alerta: Esta zona está llena!";
					messages[1] = "Elegir otra zona.";
					messages[2] = "Cancelar venta";
					
					if(LoopMenu(messages, true) == 0) return false;
				}
			} catch (InputError e) {
				e.printStackTrace();
			}
		}
		
		String entrada = "";
		
		while(entrada == "")
		{
			entrada = EligeAbono();
			
			if(entrada != "Normal")
			{
				System.out.println("¿La documentación del comprador esta lista?\n¿Si o No?");
				
				String r = scanner.next().trim();
				System.out.println(r);
				if(r.toLowerCase().equals("si") || r.toLowerCase().equals("s") )
				{
					System.out.println("------ IMPRIMIENDO LA ENTRADA ------");
					gestor.vendeEntrada(zona, nombre, entrada);
					return true;
				}
				else if(r.toLowerCase().equals("no") || r.toLowerCase().equals("n") )
				{
					messages = new String[3];
					
					messages[0] = "¡Alerta: Documentación inválida!";
					messages[1] = "Elegir otro tipo de entrada";
					messages[2] = "Cancelar venta";
					
					if(LoopMenu(messages, true) == 0) return false;
				}
				else {
					System.out.println("ERROR: Respuesta inválida.");
				}
				entrada = "";
			}
			else {
				System.out.println("------ IMPRIMIENDO LA ENTRADA ------");
				gestor.vendeEntrada(zona, nombre, entrada);
				return true;
			}
		}
		
		return false;
	}
	/**
	 * Menu responsable por guiar el operador pelo proceso de consultas de Entradas.
	 */
	private void MenuConsultaEntrada() {
		
		String[] messages = new String[6];
		
		messages[0] = "Seleccione el tipo de búsqueda de entradas.";
		messages[1] = "Por número ID de venta";
		messages[2] = "Por nombre de usuario";
		messages[3] = "Por zona";
		messages[4] = "Listado completo";
		messages[5] = "Regresar al menú principal";
		
		
		while(true) {

			int input = LoopMenu(messages, true);
			
			switch(input) {
			case 1: // ID
				System.out.println("Ingrese el número ID de la venta deseada: ");
				try {
					gestor.consultaEntrada(scanner.nextInt());
					return;
				} catch (InputError e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:// Nombre
				System.out.println("Ingrese el nombre de usuario: ");
				try {
					gestor.consultaEntradaNombre(scanner.next());
					return;
				} catch (InputError e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3: // Zona
				try {
					gestor.consultaEntradaZona(EligeZona());
					return;
				} catch (InputError e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4: // completo
				System.out.println("*** Listado completo de entradas ***");
				gestor.consultaEntradaListado();
				return;
			case 0: return;
			}
		}
	}
	
	/**
	 * Menu responsable por guiar el operador pelo proceso de consultas de Zonas.
	 */
	private void MenuConsultaZona() {
	
		String[] messages = new String[4];
	
		messages[0] = "¿Consultar solo 1 zona o todas?";
		messages[1] = "Una zona";
		messages[2] = "Todas las zona";
		messages[3] = "Regresar al menú principal";
		
		int input = LoopMenu(messages, true);
			
		switch(input) {
		case 1: // una
			gestor.informeZona( EligeZona() );
			return;
			
		case 2:// Todas
			
			gestor.informeZona( "Principal" );
			gestor.informeZona( "Palco");
			gestor.informeZona( "Central");
			gestor.informeZona( "Lateral");
			return;
		}
	}
	

	/**
	 * Clase auxiliar para la selección de zonas.
	 * @return El nombre de la zona seleccionada.
	 */
	private String EligeZona()
	{
		String[] messages = new String[5];

		messages[0] = "Elige la zona deseada:";
		messages[1] = "Principal";
		messages[2] = "Palco";
		messages[3] = "Central";
		messages[4] = "Lateral";
		
		int input = LoopMenu(messages);
		
		return messages[input];
	}
	
	/**
	 * Clase auxiliar para la selección de tipos de abono.
	 * @return El tipo de abono.
	 */
	private String EligeAbono()
	{
		String[] messages = new String[4];

		messages[0] = "Elige el tipo de entrada";
		messages[1] = "Normal";
		messages[2] = "Abononada";
		messages[3] = "Reducida";
		
		int input = LoopMenu(messages);
		
		return messages[input];
	}

	/*és la misma función que crie en el prueba 1*/
	/**
	 * Auxiliary function that creates a "select menu" where the user has to choose between "1"
	 *  and "Nº" where "Nº" is the length of the String array given minus 1.<br/> 
	 * @param messages List of messages to be displayed. The first message (Array[0]) 
	 * is the menu title and the rest are the choices. The choices are displayed in 
	 * the format 'Nº - array[Nº]'. The array must have at least 3 Strings inside
	 * (the title and 2 choices).
	 * @return The number (int) of the choice made by the user or -1 if the conditions
	 *  of the array are not met.  
	 */
	private int LoopMenu(String[] messages, boolean hasBack) {
		if(messages == null || messages.length < 3) return -1;

		boolean check = false;
		int value = 0;
		
		do
		{
			String text = messages[0];
			for(int i = 1; i < messages.length; i++)
				text += "\n" + (hasBack && i == messages.length-1?"0":i) + " - " + messages[i];
				
			System.out.println(text);
			value = scanner.nextInt();
			
			if(hasBack) check = (value>=0 && value < messages.length-1);
			else check = ( value>0 && value < messages.length );
			
			if(!check) System.out.println("Seleccion no válida ... ¡Vuelve a intentarlo!");
		}
		while (!check);

		return value;
	}
	private int LoopMenu(String[] messages) {
		return LoopMenu(messages, false);
	}
}
