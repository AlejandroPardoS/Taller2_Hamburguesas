package modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto
{
	private Producto Base;
	private int precioNuevo = 0;
	private ArrayList<Ingrediente> Agregados = new ArrayList<Ingrediente>();
	private ArrayList<Ingrediente> Eliminados = new ArrayList<Ingrediente>();
	
	public ProductoAjustado(Producto productonuevo, int precioNuevo)
	{
		this.Base = productonuevo;
		this.precioNuevo += precioNuevo;
	}
	public void addPrecio(int adicion) 
	{
		this.precioNuevo += adicion;
	}
	
	public void agregarIngrediente(Ingrediente agregar) {
		Agregados.add(agregar);
		this.addPrecio(agregar.getCostoAdicional());
	}
	
	public void eliminarIngrediente(Ingrediente restar)
	{
		Eliminados.add(restar);
	}
	
	public String getNombre()
	{
		return Base.getNombre();
	}
	public int getPrecio()
	{
		return precioNuevo;
	}
	public String generarTextoFactura()
	{
		String text = "1 " + this.getNombre();
		if (this.Agregados.size() > 0)
		{
			text += " con ";
			for (Ingrediente agregar: this.Agregados){
				text += agregar.getNombre() + ","; 
			}
			text += ",";
		}
		if (this.Eliminados.size() > 0)
		{
			text += " sin ";
			for (Ingrediente restar: this.Eliminados)
			{
				text += restar.getNombre() + ",";
			}
			text += "+";
			text = text.replace(",,", " y");
			text = text.replace(",+", "");
		}
		else
		{
			text = text.replace(",,", "");
		}
		
		text += " ... " + this.getPrecio();
		
		return text;
	}

}
