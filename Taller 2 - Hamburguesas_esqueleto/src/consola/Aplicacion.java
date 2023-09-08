package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import modelo.Combo;
import modelo.Ingrediente;
import modelo.ProductoMenu;
import modelo.Restaurante;

public class Aplicacion {
	
	public static void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("0. Mostrar el menu");
		System.out.println("1. Iniciar un nuevo pedido");
		System.out.println("2. Consultar la información de un pedido dado su id");
		System.out.println("3. Salir de la aplicación\n");
	}
	public void ejecutarOpcion() throws IOException
	{
		Restaurante.cargarInformacionRestaurante("data\\ingredientes.txt", "data\\menu.txt", "data\\combos.txt");
		System.out.println("Restaurante hamburguesas");
		boolean continuar = true;
		while (continuar)
		{
			//try
			//{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 0)
				ejecutarOpcionCargarMenu();
				if (opcion_seleccionada == 1)
					ejecutarOpcionIniciarPedido();
				else if (opcion_seleccionada == 2)
					ejecutarOpcionConsultarInfoId();
				else if (opcion_seleccionada == 3)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
					//continuar = false;
				}
			//}
//			catch (NumberFormatException e)
//			{
//				System.out.println("Debe seleccionar uno de los números de las opciones.");
//				//System.out.println("aiuda");
//				continuar = false;
//			}
		}
	}
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	private void ejecutarOpcionCargarMenu() throws IOException
	{	

		for (ProductoMenu producto: Restaurante.getMenuBase())
		{
			String nombreProducto = producto.getNombre();
			int precioProducto = producto.getPrecio();
			System.out.println(nombreProducto +" - "+ precioProducto);
		}
		for (Ingrediente ingrediente: Restaurante.getIngredientes())
		{
			String nombreIngrediente = ingrediente.getNombre();
			int precioIngrediente = ingrediente.getCostoAdicional();
			System.out.println(nombreIngrediente +" - "+ precioIngrediente);
		}
		for (Combo combo: Restaurante.getCombo())
		{
			String nombreCombo = combo.getNombre();
			int precioCombo = combo.getPrecio();
			System.out.println(nombreCombo +" - "+ precioCombo);
		}	
	}
	
	private void ejecutarOpcionIniciarPedido() throws IOException
	{
		String nombreCliente = input("Ingrese su nombre: ");
		String direccionCliente = input("Ingrese su dirección: ");
		Restaurante.iniciarPedido(nombreCliente, direccionCliente);
		//Restaurante.iniciarPedido("ALEJANDRO", "DIRECCION");
	}
	
	private void ejecutarOpcionConsultarInfoId()
	{
		Integer id = Integer.parseInt(input("\nIngrese el id del Pedido"));
		String resp = Restaurante.consultarPedido(id);
		System.out.println(resp);
	}	
	public static void main(String[] args) throws IOException
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarOpcion();
	}
}