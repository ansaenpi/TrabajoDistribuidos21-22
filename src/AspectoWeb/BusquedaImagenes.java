/* ÁNGELA SÁENZ PINILLOS
Explicación: Esta clase se encarga de listar en un archivo las URL de las imágenes (los recursos) que tenemos que descargar
partiendo del código fuente. Y también lista en otro archivo las direcciones del producto en la propia tienda.
 */

package AspectoWeb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class BusquedaImagenes {
	private String tam;
	private int numDescarga;

	public BusquedaImagenes(String tam, int nd) {
		this.tam = tam;
		this.numDescarga = nd;
	}

	public int listarDireccionesImagenes() {
		// La función devuelve el número de lineas de la lista de direcciones creadas.
		// Es decir, el número de imágenes a descargar.
		// este valor es importante porque luego lo empleo para la creacción del
		// cyclicbarrier en el main.
		int numImagenes = 0;
		File fcodigo = new File("codigo_fuente" + this.numDescarga + ".txt");
		File f = new File("url_imagenes" + this.numDescarga + ".txt");
		File f2 = new File("href_imagenes" + this.numDescarga + ".txt");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fcodigo)));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
				BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f2)));) {
			String linea;
			int contador = 0;
			String[] trozos;
			String imagen;
			String href = "";
			while ((linea = reader.readLine()) != null) {
				if (linea.contains("h2 class")) {
					contador++;
				}
				if (contador == 1) {
					// Si el contador vale 1 significa que estamos entre los dos h2 class, que son
					// el cuerpo en donde se encuentran
					// las imágenes que nos interesan.
					if (linea.contains("href=")) {
//					nombre=linea.substring(linea.indexOf("href=")+5);
//					nombre=nombre.split("\"")[1];
////					nombre=nombre.split("/")[2];
//					nombre=nombre.split("\\?")[0];
//					nombre=nombre.replace("-", " ");
						href = linea.substring(linea.indexOf("href=") + 5);
						href = href.split("\"")[1];
					}

					if (linea.contains("data-src")) {
						trozos = linea.split("\"");
						imagen = trozos[1].replace("{width}", tam) + "\r\n";
						imagen = "https:" + imagen;
						writer.write(imagen);
						writer2.write(href + "\r\n");
						numImagenes++;
					}
				}
			}
			writer.flush();
			writer2.flush();
			System.out.println("Se ha acabado de buscar todas las url de las imágenes. Se han guardado en"
					+ "url_imagenes" + this.numDescarga + ".txt");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return numImagenes;
		}
	}
}
