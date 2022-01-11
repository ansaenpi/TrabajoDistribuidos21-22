/* ÁNGELA SÁENZ PINILLOS
Explicación: Este es el archivo principal del proyecto desde el cual ejecutamos el resto de clases.
En primer lugar pedimos la búsqueda determinada que queremos hacer (o filtro), y obtenemos las url de las imágenes que queremos
descargar. Las descargamos de manera concurrente con un pool de hilos, haciendo además que todos ellos acaben a la vez con un
cyclic barrier. A continuación, pedimos que se elija un artículo en concreto de los que se han descargado, y proporcionamos
la url tanto de shopify como de la tienda, por si se desea comprar. También se proporciona el precio del producto.
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Principal {
	public static void main(String[] args) {
		CreadorURLConFiltro creadorurl = new CreadorURLConFiltro("vestido marron");
		String url = creadorurl.getUrlConFiltro();
		int numDescarga = 1;
		CodigoFuente cf = new CodigoFuente(url, numDescarga);
		cf.obtenerCodigoFuente();
		String tam = "180";
		BusquedaImagenes bi = new BusquedaImagenes(tam, numDescarga);
		int N = bi.listarDireccionesImagenes();
		File f = new File("url_imagenes" + numDescarga + ".txt");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
				Scanner teclado = new Scanner(System.in);) {
			ExecutorService pool = Executors.newCachedThreadPool();
			final CyclicBarrier barrera = new CyclicBarrier(N + 1);
			String imagen;
			int contador = 1;
			while ((imagen = reader.readLine()) != null) {
				// el contador hay que pasarselo desde aquí porque sino nadie te asegura que se
				// vayan a ejecutar en ese orden
				// pero si ya le pasas el identificador del número de la imagen solventamos el
				// problema.
				DescargaImagenes dim = new DescargaImagenes(new URL(imagen), contador, barrera);
				contador++;
				pool.execute(dim);
			}
			barrera.await();
			pool.shutdown();
			// NOTA: Una vez llegados a este punto todas las imagenes han acabado de
			// descargarse a la vez.
			// Ahora podemos proceder a preguntar si de alguna de las imágenes desea obtener
			// más imágenes
			// puesto que ya se hna descargado todas gracias al cyclic barrier.

			System.out.println("Introduce el valor de la imagen que quieres inspeccionar");
			int numero = teclado.nextInt();

			BusquedaURLProducto bup = new BusquedaURLProducto(numero);
			String url2 = bup.buscarURLProducto();
			numDescarga++;
			CodigoFuente cf2 = new CodigoFuente(url2, numDescarga);
			cf2.obtenerCodigoFuente();

			BusquedaPrecioVenta bpv = new BusquedaPrecioVenta(numDescarga);
			bpv.obtenerPrecioVenta();
			System.out.println("Accede a la página web que te he ofrecido para conseguirlo!!");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
