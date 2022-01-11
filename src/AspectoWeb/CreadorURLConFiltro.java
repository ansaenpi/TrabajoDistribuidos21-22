/* ÁNGELA SÁENZ PINILLOS
Explicación: Esta clase crea una URL con el filtro que hayamos elegido, pej: pantalon marron, falda marron...
 */

package AspectoWeb;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class CreadorURLConFiltro {
	
	private String URLinicial="https://algo-bonito.com";
	private String filtro;
	
	
	public CreadorURLConFiltro(String f) {
		this.filtro=f;
	}
	
	public String getUrlConFiltro() {
//		String[] trozos=filtro.split(" ");
//		String query="";
//		int contador=0;
//		for(String t: trozos) {
//			if(contador==0) {
//				query=t;
//			}else {
//				query=query + "+" + t;
//			}		
//			contador++;
//		}
//		
//		String resultado=URLinicial+"/search?q="+query+"&options%5Bprefix%5D=last";
//		return resultado;
		String query="";
		try {
			query = URLEncoder.encode(this.filtro, "UTF-8");
			//es mejor usar la clase URLEncoder que lo que había hecho anteriormente para crear la query codificada con UTF-8.
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resultado=URLinicial+"/search?q="+query+"&options%5Bprefix%5D=last";
		return resultado;
	}
	
}
