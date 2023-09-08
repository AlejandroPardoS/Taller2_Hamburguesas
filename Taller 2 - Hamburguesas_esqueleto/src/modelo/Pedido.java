package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Pedido 
{
	private int numeroPedidosp = 0;
	
	private int idPedido;
	
	private String nombreCliente;
	
	private String direccionCliente;

	public Pedido(String nombreCliente, String direccionCliente)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		numeroPedidosp +=1;
	}
	public int getIdPedido()
	{
		return idPedido;
	}
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private int precioNeto= 0;
	public void agregarProducto(Producto item)
	{
		this.productos.add(item);
		precioNeto += item.getPrecio();
	}
	private int getPrecioNetoPedido()
	{
		return precioNeto;
	}
	private int getPrecioTotalPedido()
	{
		return this.getPrecioIVAPedido()+this.getPrecioNetoPedido();
	}
	private int getPrecioIVAPedido()
	{
		int IVA = (int)(this.getPrecioNetoPedido()*0.19);
		return IVA;
	}
	private String factura;
	public void setFactura(String factura) 
	{
		this.factura = factura;
	}
	
	public String getFactura() 
	{
		return this.factura;
	}
	
	public void setId(int id) 
	{
		this.idPedido = id;
	}
	private String generarTextoFactura()
	{
		String factura = "ID pedido:" +this.idPedido;
		factura += "\nNombre: " + this.nombreCliente;
		factura += "\nDirección: " + this.direccionCliente;
		factura += "\n";
		for (Producto producto: this.productos) {
			factura += producto.generarTextoFactura() + "\n";
		}
		factura += "\nNúmero: " + numeroPedidosp;
		factura += "\nBase: " + this.getPrecioNetoPedido() + "   IVA: " + this.getPrecioIVAPedido();
		factura += "\nPrecio total: " + this.getPrecioTotalPedido();
		this.setFactura(factura);
		System.out.println("\n" + factura );
		return factura;
	}
	public void guardarFactura() throws IOException // basado en otro amigo
	{
		BufferedWriter facturas = new BufferedWriter(new FileWriter("data\\Facturas", true));
		String textoFactura = this.generarTextoFactura();
		facturas.write(textoFactura);
		facturas.newLine();
		facturas.close();
	}

}