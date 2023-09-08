package modelo;

import java.util.ArrayList;

public class Combo implements Producto
{	
	public Combo(String nombres, double descuentos)
	{
		this.nombreCombo = nombres;
		this.esDescuento(descuento);
	}
	private String nombreCombo;
	private double descuento;
	
	private ArrayList<ProductoMenu> item = new ArrayList<ProductoMenu>();
	public void agregarItemACombo(ProductoMenu itemCombo)
	{
		item.add(itemCombo);
	}
	private int precio; // OJO
	public int getPrecio()
	{
		return precio;
	}
	public void esPrecio(int prec) 
	{
		this.precio = prec;
	}
	public String generarTextoFactura() // basado en otro
	{
		String text = "1 " + (this.getNombre()) + "(";
		for (ProductoMenu item: item) {
			
			text += item.getNombre() + ",";
		}
		text += "+";
		text = text.replace(",+", "");
		text += ") ... " + Integer.toString(this.getPrecio());
		
		return text;
	}
	//private String nombre; //OJO
	public String getNombre()
	{
		return nombreCombo;
	}
	public double getDescuento()
	{
		return descuento;
	}
	public void esDescuento(double descuento) 
	{
		this.descuento = descuento;
	}
	
}
