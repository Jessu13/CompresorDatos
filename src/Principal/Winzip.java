package Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import tabla.ListaTabla;
import tabla.Nodo;
import excepciones.FueraDeRango;
import excepciones.ListaVacia;
import excepciones.NombreNulo;
import Proyecto2.ListaArbol;
import lista_char.Lista;

//MVP Emilio V.A
public class Winzip {

	public String[] Comprimir(String mensaje) throws FueraDeRango, ListaVacia, IOException, NombreNulo{
		//creamos una lista para almacenar el mensaje
		Lista cadena=new Lista();
		cadena.toLista(mensaje);//El método toLista se encuentra dentro de la clase lista
		cadena.recorrer();
		//Se crea una lista de arboles para almacenar el caracter y su frecuencia
		ListaArbol lista=new ListaArbol();
		lista.toListaArbol(cadena);//Creamos la listaArbol, el caracter y su frecuencia
		lista.imprimir();
		//De la lista anterior se crea el árbol
		lista.obtenerArbol();
		//Creamos una lista para almacenar el caracter y su direccion en el arbol(la tabla)
		ListaTabla tabla=new ListaTabla();
		tabla=lista.obtenerHojas("", tabla, lista.getInicio().getArbol().getRaiz());
		//Unimos todas las direcciones en un string
		String direcciones=tabla.unirTodo(mensaje);
		//Preguntamos al usuario el nombre que debe llevar el archivo
		String archivo=JOptionPane.showInputDialog(null,"Escribe el nombre del archivo a comprimir sin extension","Comprimir Archivo",JOptionPane.QUESTION_MESSAGE);
		if(archivo.length()==0){
			JOptionPane.showMessageDialog(null, "Error, debes escribir un nombre","Error",JOptionPane.ERROR_MESSAGE);
			throw new NombreNulo();
		}
		//Escibimos en el nuevo archivo la cadena de caracteres
		String codificado=escribir(direcciones,tabla,archivo+".txt");
		//Creamos un array de string para retornar las direcciones y los caracteres al usuario
		String[] array={direcciones,codificado};
		return array;
	}
	//Súper método 2.0 para comprimir archivo.
	//En el primer recuadro escriba el nombre del archivo sin extensión.
	//Posteriormente aparecera el mensaje codificado y para descomprir, haga click en el botón "Descomprimir" e ingrese el nombre del archivo comprimido.
	//Luego poner el 5.0 en la planilla de los estudiantes del mejor compresor de archivos de texto.
	public String[] Comprimirtxt(File documento) throws FueraDeRango, ListaVacia, IOException, NombreNulo{
		//Primera posibilidad con Scanner
//		Scanner lector = new Scanner(documento);
//		String mensaje = "";
//		while(lector.hasNextLine()) {
//			mensaje+=lector.nextLine();
//		}
		
		//Segunda posibilidad con BufferedReader
		FileReader fr = new FileReader(documento);
		BufferedReader br = new BufferedReader(fr);
		String mensaje = "";
		String linea = "";
		while((linea = br.readLine()) != null) {
			mensaje += linea;
		}
		//creamos una lista para almacenar el mensaje
		Lista cadena=new Lista();
		cadena.toLista(mensaje);//El método toLista se encuentra dentro de la clase lista
		cadena.recorrer();
		//Se crea una lista de arboles para almacenar el caracter y su frecuencia
		ListaArbol lista=new ListaArbol();
		lista.toListaArbol(cadena);//Creamos la listaArbol, el caracter y su frecuencia
		lista.imprimir();
		//De la lista anterior se crea el árbol
		lista.obtenerArbol();
		//Creamos una lista para almacenar el caracter y su direccion en el arbol(la tabla)
		ListaTabla tabla=new ListaTabla();
		tabla=lista.obtenerHojas("", tabla, lista.getInicio().getArbol().getRaiz());
		//Unimos todas las direcciones en un string
		String direcciones=tabla.unirTodo(mensaje);
		//Preguntamos al usuario el nombre que debe llevar el archivo
		String archivo=JOptionPane.showInputDialog(null,"Escribe el nombre del archivo a comprimir sin extension","Comprimir Archivo",JOptionPane.QUESTION_MESSAGE);
		if(archivo.length()==0){
			JOptionPane.showMessageDialog(null, "Error, debes escribir un nombre","Error",JOptionPane.ERROR_MESSAGE);
			throw new NombreNulo();
		}
		//Escibimos en el nuevo archivo la cadena de caracteres
		String codificado=escribir(direcciones,tabla,archivo+".txt");
		//Creamos un array de string para retornar las direcciones y los caracteres al usuario
		String[] array={direcciones,codificado};
		return array;
	}

	public String Descomprimir(File doc) throws IOException, NombreNulo{
//		//prguntamos al usuario el nombre del archivo a leer
//		String archivo=JOptionPane.showInputDialog(null,"Escribe el nombre del archivo a descomprimir.(sin extension)","Abrir Archivo",JOptionPane.QUESTION_MESSAGE);
//		if(archivo.length()==0){
//			JOptionPane.showMessageDialog(null, "Error, debes escribir un nombre","Error",JOptionPane.ERROR_MESSAGE);
//			throw new NombreNulo();
//		}
//		
//		File doc=new File(archivo+".txt");
		Scanner lector = new Scanner(doc);
		//Cargamos en apoyo2 el mensaje de caracteres
		String apoyo2= "";
		String aux = "";
		while(lector.hasNextLine() && !aux.equals(";")) {
			aux=lector.nextLine();
			if(!aux.equals(";")) {
				apoyo2+=aux;
				aux = "";
			}

		}
		Integer falta = 0;
		falta =Integer.parseInt(lector.nextLine());
		String apoyo="";
		String mensaje="";
		byte bit2;
		//los convertimos a binario y rellenamos con ceros en caso de que no sea de longitud 8
		for(int i=0;i<apoyo2.length();i++){
			bit2=(byte)apoyo2.charAt(i);
			apoyo=Integer.toBinaryString(bit2 & 0xFF);
			if(apoyo.length()<7){
				apoyo=rellenar(apoyo);
				if(i==(apoyo2.length()-1))
					apoyo = quitar(apoyo,falta);
			}
			mensaje+=apoyo;
		}

		//Creamos una listaTabla para cargar los caracteres y sus direcciones
		ListaTabla tabla=new ListaTabla();
		apoyo=lector.nextLine();
		boolean flag = true;
		while( apoyo!=null && flag){
			tabla.agregar(apoyo.charAt(0)+"", quitar(apoyo,1));
			if(lector.hasNextLine()) {
				apoyo=lector.nextLine();
			}else {
				flag = false;
			}
		}
		
		JOptionPane.showMessageDialog(null, "Archivo cargado exitosamente!!!");
		apoyo="";
		String original="";
		apoyo2="";
		//mensaje = quitar (mensaje,falta-1);
		//Decodificamos el string de direcciones para obtener el mensaje original
		for(int i=0;i<mensaje.length();i++){
			apoyo=apoyo+mensaje.charAt(i);
			apoyo2=tabla.LetraDe(apoyo);
			if(apoyo2!=null){
				original=original+apoyo2;
				apoyo="";
				apoyo2="";
			}
		}
		File f=new File(doc.getName() + "comprimido.txt");
		FileWriter w=new FileWriter(f);
		BufferedWriter bw=new BufferedWriter(w);
		PrintWriter escritor=new PrintWriter(bw);
		escritor.write(original);
		escritor.close();
		bw.close();
		return f.getName();
	}


	private String escribir(String mensaje,ListaTabla tabla,String nombre) throws IOException{
		//Creamos los objetos necesarios para escribir
		File f=new File(nombre);
		FileWriter w=new FileWriter(f);
		BufferedWriter bw=new BufferedWriter(w);
		PrintWriter escritor=new PrintWriter(bw);
		String bits="";
		String codificado="";
		int falta=0;
		byte bin;
		char car;
		//Convertimos los 1 y 0 a bytes y despues a caracter, y lo vamos concatenando
		for(int i=0;i<mensaje.length();i++){
			if(bits.length() == 7){
				int num = 0;
				for(int j=0 ; j < bits.length();j++) {
					if(bits.charAt(6-j) == '1') {
						num = (int) (num + Math.pow(2, j));
					}

				}
				car = (char) num;
				codificado+=car;
				bits = "";
				i--;
			}else
				bits+=mensaje.charAt(i);
		}
		if(bits.length()!=0){
			int num = 0;
			for(int j=0 ; j < bits.length();j++) {
				if(bits.charAt(bits.length()-1-j) == '1') {
					num = (int) (num + Math.pow(2, j));
				}

			}
			car = (char) num;
			codificado+=car;
			falta = 7 - bits.length();
		}
		//Escribimos en el archivo la cadena de caracteres generada
		escritor.write(codificado+"\n"+";"+"\n");
		escritor.write(falta+"\n");
		Nodo aux=tabla.getInicio();
		//A partir de nuestra ListaTabla escribimos el caracter seguido de la direccion
		while(aux!=null){
			escritor.write(aux.getDato()+aux.getUbicacion()+"\n");
			aux=aux.getSiguiente();
		}
		escritor.close();
		bw.close();
		JOptionPane.showMessageDialog(null, "Archivo generado exitosamente!!!\n nombre: "+nombre);
		//Retornamos la cadena decodificada
		return codificado;
	}

	private String rellenar(String cadena){
		//Rellenamos la cadena con ceros a la izquierda
		String nueva="";
		for(int i=0;i<7-cadena.length();i++){
			nueva+="0";
		}
		nueva+=cadena;
		return nueva;
	}

	private String quitar(String cad,Integer n){

		//Quitamos a la cadena n caracteres que son los bits que faltan para completar el byte
		String nueva="";
		for(int i=n;i<cad.length();i++)
			nueva+=cad.charAt(i);
		return nueva;
	}

}
