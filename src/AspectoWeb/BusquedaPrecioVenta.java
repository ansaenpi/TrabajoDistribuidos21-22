/* ÁNGELA SÁENZ PINILLOS
Explicación: Esta clase inspecciona el código fuente de la url seleccionada y nos dice el precio de venta al público que tiene
en ese momento en la web.
 */


package AspectoWeb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class BusquedaPrecioVenta {

	private int numDescarga;
	
	public BusquedaPrecioVenta(int n) {
		this.numDescarga=n;
	}
	

	
	public void obtenerPrecioVenta() {
		//La función obtiene el precio de venta del producto que se ha seleccionado.
		int contador=0;
		File fcodigo=new File("codigo_fuente" + this.numDescarga + ".txt");
		
		try(
				BufferedReader reader= new BufferedReader(new InputStreamReader(new FileInputStream(fcodigo)));
		){
		String linea;
		String precio="";
		boolean encontrado=false;
		while((linea=reader.readLine())!=null && encontrado==false) {
			if(linea.contains("Precio de venta")) {
				contador++;
			}
			if(contador==2) {
				if(linea.contains("â‚¬")) {
					precio=linea;
					//NOTA IMPORTANTE; No sabia como poner el simbolo del euro, poniendo € no me lo cogía bien.
					//Visualizando la linea he visto que lo representaba â‚¬ y lo he puesto así. Pero no sé si funcionará
					//en otros sistemas operativos.
					precio=precio.replace("â‚¬", "EUR");
					encontrado=true;
				}
				
			}
		}
		System.out.println("El precio de venta del producto es:");
		System.out.println(precio);
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
}
	}}