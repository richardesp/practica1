package gestorDeCriticas;
import java.io.*;
import javax.swing.JOptionPane;
public class pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File archivo;
		FileReader fr;
		BufferedReader br;

		try {
			archivo = new File("./data/datosCriticas.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				int index1 = linea.indexOf(",");
				int index2 = linea.indexOf(",", index1 + 1);
				int index3 = linea.indexOf(",", index2 + 1);
				int indexFinal = linea.lastIndexOf(",");
				String titulo = linea.substring(0, index1);
				int puntuacion = Integer.parseInt(linea.substring(index1 + 1, index2));
				String comentario = linea.substring(index2 + 1, index3);
				int indice = Integer.parseInt(linea.substring(index3 + 1, indexFinal));
				float valoracion = Float.parseFloat(linea.substring(indexFinal + 1));
				System.out.println(titulo+" "+puntuacion+" "+comentario+" "+indice+" "+valoracion);
				int b=indice+2;
				System.out.println(b);
			}
		br.close();
		fr.close();
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "error leyendo archivo" + e);
	
}
		}
}
