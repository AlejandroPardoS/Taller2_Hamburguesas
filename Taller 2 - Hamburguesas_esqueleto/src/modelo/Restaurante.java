package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Restaurante 
{

	public Restaurante()
	{
		
	}
	public static void iniciarPedido(String nombreCliente, String direccionCliente) throws IOException
	{
		Pedido nuevopedido = new Pedido(nombreCliente, direccionCliente);
		boolean pedidobucle = true;
		while (pedidobucle)
		{
			System.out.println("Que tipo de producto desea ordenar?");
			System.out.println("\n 1. Menu Base ");
			System.out.println("2. Combos ");
			System.out.println("3. Finalizar pedido ");
			int tipo = Integer.parseInt(input("Ingrese el numero: "));
			if (tipo == 1)
			{
				int i = 0;
				for (ProductoMenu producto: Restaurante.getMenuBase())
				{
					String nombreProducto = producto.getNombre();
					int precioProducto = producto.getPrecio();
					System.out.println(i + ": " +nombreProducto +" - "+ precioProducto);
					i+=1;
				}
				int prod = Integer.parseInt(input("Ingrese el numero del producto: "));
				int a = 0;
				ProductoMenu productoelegido = productos.get(3); //no sabia q mas poner ahi
				while (a<21)
				{
					if (prod == a)
					{
						productoelegido = productos.get(a);
					}
					a +=1;
				}
				String mas = input("Desea adicionar o eliminar ingredientes? (Sí/No)").toUpperCase();
				if (mas == "SI")
				{
					boolean ae = true;
					Restaurante.agregareliminar(ae, productoelegido, nuevopedido, nombreCliente, direccionCliente);
				}
				else if (mas == "NO")
				{
					boolean ae = false;
					Restaurante.agregareliminar(ae, productoelegido, nuevopedido, nombreCliente, direccionCliente);
				}
				
				
			}
			else if (tipo == 2)
			{
				int ere = 0;
				for (Combo combo: Restaurante.getCombo())
				{
					String nombreCombo = combo.getNombre();
					int precioCombo = combo.getPrecio();
					System.out.println(ere + ": " + nombreCombo +" - "+ precioCombo);
					ere +=1;
				}	
				int prodcombo = Integer.parseInt(input("Ingrese el numero del combo: "));
				int b = 0;
				Combo comboelegido = combos.get(1);
				while (b<3)
				{
					if (prodcombo == b)
					{
						comboelegido = combos.get(b);
					}
					b+=1;
				}

				String mas = input("Desea adicionar o eliminar ingredientes? (Sí/No)").toUpperCase();
				if (mas == "SI")
				{
					boolean ae = true;
					Restaurante.agregareliminar(ae, comboelegido, nuevopedido, nombreCliente, direccionCliente);
				}
				else if (mas == "NO")
				{
					boolean ae = false;
					Restaurante.agregareliminar(ae, comboelegido, nuevopedido, nombreCliente, direccionCliente);
				}
				
			}
			else if (tipo == 3) 
			{
				Random rand = new Random(); 
				Integer id = rand.nextInt(10000);
				if (pedidos.containsKey(id) == false) // No esta funcionando, nuevo pedido no almacena??
				{
					nuevopedido.setId(id);
					nuevopedido.guardarFactura();
					pedidos.put(nuevopedido.getIdPedido(), nuevopedido);
					pedidobucle = false;
				}
			}
		}
		
			
		return;
		
	}
	public static void IDs(Pedido nuevopedido)
	{
		
	}
	
	public static void agregareliminar(boolean mas, Producto productonuevo, Pedido nuevopedido, String nombreCliente, String direccionCliente) 
	{
		//Pedido nuevopedido = new Pedido(nombreCliente, direccionCliente);
		if (mas == false)
		{
			nuevopedido.agregarProducto(productonuevo);
		}
		else if (mas == true) // no esta entrando aca??
		{
			ProductoAjustado cambio = new ProductoAjustado(productonuevo, productonuevo.getPrecio());
			boolean bucleIngre = true;
			while (bucleIngre)
			{
				System.out.println("\n1. Adicionar ingrediente");
				System.out.println("2. Eliminar ingrediente");
				int aoe = Integer.parseInt(input("Ingrese la opción que desea: "));
				int ara = 1;
				for (Ingrediente ingre: Restaurante.getIngredientes()) {
					String nomb = ingre.getNombre();
					int prec = ingre.getCostoAdicional();
					System.out.println(ara + ": " + nomb + ": " + prec);
					ara ++;
				}
				int numIngre = Integer.parseInt(input("Ingrese el ingrediente: "));
				int c = 0;
				Ingrediente ingreelegido = ingredientes.get(2);
				while (c<14)
				{
					if (numIngre == c)
					{
						ingreelegido = ingredientes.get(c);
					}
					c+=1;
				}
				int inding = 0;
				boolean bucle = true;
				while (bucle)
				{
					Ingrediente ingr = ingredientes.get(inding);
					if (ingreelegido.equals(ingr.getNombre()))
					{
						ingreelegido = ingr;
						bucle =false;
					}
					inding +=1;
				}
				if (aoe == 1)
				{
					cambio.agregarIngrediente(ingreelegido);
				}
				else if (aoe == 2)
				{
					cambio.eliminarIngrediente(ingreelegido);
				}
				System.out.println("Desea modificar mas el producto? (Si/No)");
				String otro = input("").toUpperCase();
				if (otro == "NO") {
					nuevopedido.agregarProducto(cambio);
					bucleIngre = false;
				}
				else if (otro == "SI") {
					continue;
				}
			}
		}
		
	}
	
	public void cerrarYGuardarPedido()
	{
		
	}

	public static ArrayList<ProductoMenu> getMenuBase()
	{
		return productos;	
	}
	public static ArrayList<Ingrediente> getIngredientes()
	{
		return ingredientes;
	}
	
	static ArrayList<ProductoMenu> productos = new ArrayList<ProductoMenu>();
	static ArrayList<Combo> combos = new ArrayList<Combo>();
	public static ArrayList<Combo> getCombo()
	{
		return combos;
	}
	static ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		
	public static void cargarInformacionRestaurante(String archivoIngredientes, String archivoMenu, String archivoCombos) throws IOException
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	private static void cargarIngredientes(String archivoIngredientes) throws FileNotFoundException, IOException 
	{
		//try {
			BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes)); 
			String linea = br.readLine();
			//System.out.println("HDP"+linea);
			while (linea != null)
			{
				String[] partes = linea.split(";");
				String nombreProd = partes[0];
				int precioProd = Integer.parseInt(partes[1]);
				//System.out.println(precioProd);
				Ingrediente nuevo = new Ingrediente(nombreProd, precioProd);
				ingredientes.add(nuevo);
				linea = br.readLine();
			}
		br.close();
//		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	private static void cargarMenu(String archivoMenu) throws IOException
	{
		//try {
			BufferedReader br = new BufferedReader (new FileReader(archivoMenu));
			String linea = br.readLine();
			while (linea != null)
			{
				String[] partes = linea.split(";");
				String nombre = partes[0];
				int precioBase = Integer.parseInt(partes[1]);
				ProductoMenu nuevo = new ProductoMenu(nombre, precioBase);
				productos.add(nuevo);
				linea = br.readLine();
			}
		br.close();
//		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	private static void cargarCombos(String archivoCombos) throws IOException
	{
		//try {
			BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
			String linea = br.readLine();
			while (linea != null)
			{
				String[] partes = linea.split(";");
				String nombreCombo = partes[0];
				String[] complemento = new String[]{partes[2], partes[3], partes[4]};
				//System.out.println(nombreCombo);
				
				double descuento = Double.parseDouble(partes[1].replace("%", "")) / 100;
				Combo pr = new Combo(nombreCombo, descuento);
				int precio = 0;
				for (String comp: complemento)
				{
					for (ProductoMenu prod : productos) 
					{
							
						String nom = prod.getNombre();
						if (nom.equals(comp))
						{
							int precioadd = prod.getPrecio();
							precio += ((int)(precioadd - precioadd*descuento));
							pr.agregarItemACombo(prod);
						}
					}
				}
				pr.esPrecio(precio);
				combos.add(pr);
				linea = br.readLine();
			}
		br.close();
//		}
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}	
	static Map<Integer, Pedido> pedidos = new HashMap<Integer,Pedido>();
	public static String consultarPedido(Integer id) {
		Pedido order = pedidos.get(id);
		if (order != null){
			return order.getFactura();
		}
		else {
			return ("No existe ningún pedido con el id " + id);
		}
	}
	public static String input(String mensaje)
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
}