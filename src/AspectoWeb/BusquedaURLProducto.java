/* ÁNGELA SÁENZ PINILLOS
Explicación: Esta clase nos proporciona la URL de shopify y de la tienda de la imagen seleccionada.
 */


package AspectoWeb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class BusquedaURLProducto {
	private int contador;

	
	public BusquedaURLProducto(int contador) {
		this.contador=contador;
	}
	
	
	public String buscarURLProducto() {
		File f = new File("url_imagenes1.txt");
		File f2 = new File("href_imagenes1.txt");
		String href = "";

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(f2)));) {
			String linea = "";
			String linea2 = "";
			int i = 1;
			Boolean encontrado = false;
			while (encontrado == false && (linea = reader.readLine()) != null && (linea2 = reader2.readLine()) != null) {
				if (i == this.contador) {
					encontrado = true;
				}
				i++;
			}
			href = "https://algo-bonito.com" + linea2;
			System.out.println("Procedemos a analizar la imagen_" + this.contador);
			System.out.println("Su url en shopify es " + linea);
			System.out.println("Su url en algo_bonito es " + href);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			return href;
		}
	}
}