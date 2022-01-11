/* ÁNGELA SÁENZ PINILLOS
Explicación: Esta clase se encarga de descargar el recurso de una url. Es decir, se encarga de descargar imágenes en este caso y las guarda
en nuestra carpeta del proyecto.
 */

package AspectoWeb;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DescargaImagenes implements Runnable{
	private URL url;
	private int contador;
	private CyclicBarrier barrera;
	

	
	public DescargaImagenes(URL u, int contador, CyclicBarrier b) {
		this.url=u;
		this.contador=contador;
		this.barrera=b;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try (
			DataInputStream in=new DataInputStream(url.openStream());
			DataOutputStream out=new DataOutputStream(new FileOutputStream("imagen_" + this.contador+".jpg"));
				//url.toString().substring(url.toString().lastIndexOf("?")+1)+ ".jpg"
		){
				byte[] buf=new byte[1024*16];
				int leidos=in.read(buf);
				while(leidos!=-1) {
					out.write(buf, 0, leidos);
					leidos=in.read(buf);
				}
				out.flush();
				barrera.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}
}
