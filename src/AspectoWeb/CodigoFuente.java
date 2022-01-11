/* ÁNGELA SÁENZ PINILLOS
Explicación: Esta clase obtiene el código fuente de una determinada URL y la guarda en un archivo de texto.
 */

package AspectoWeb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class CodigoFuente {
	
	private String URL;
	int contador;
	
	public CodigoFuente(String u, int contador) {
		this.URL=u;
		this.contador=contador;
	}
	
	public void obtenerCodigoFuente() {
		
		File f=new File("codigo_fuente" + this.contador + ".txt");
		
		try(
			BufferedReader reader= new BufferedReader(new InputStreamReader(new URL(this.URL).openStream()));
			BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));	
		){
			String linea;
			while((linea=reader.readLine())!=null) {
				writer.write(linea + "\r\n");
			}
			writer.flush();
			
			
			System.out.println("El codigo fuente de "+ this.URL + "ha sido descargado en el fichero" + "codigo_fuente" + this.contador + ".txt");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
