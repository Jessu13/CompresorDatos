package lista_char;

import excepciones.FueraDeRango;
import excepciones.ListaVacia;

public class Lista {
	private Nodo inicio;
	//getts y sets
	public Nodo getInicio() {
		return inicio;
	}
	public void setInicio(Nodo inicio) {
		this.inicio = inicio;
	}

	//constructores
	public Lista(){
		this.setInicio(null);
	}

	//metodos
	public void agregar(char valor){
		Nodo nuevo=new Nodo(valor);
		if(this.getInicio() == null)
			this.setInicio(nuevo);
		else{
			nuevo.setSiguiente(inicio);
			inicio=nuevo;
		}
	}
	
	//Métodos para recorrer la cadena e ir imprimiendo en consola la separación de los caracteres correspondientes
	//Método uno para recorrer cadena
	public void recorrer(){
		if(inicio == null)
			System.out.println("NULL.");
		else{
			Nodo aux = this.getInicio();
			System.out.print("Inicio ");
			while(aux !=null){
				System.out.print(" -> "+aux.getDato());
				aux=aux.getSiguiente();
			}
			System.out.println(" -> NULL");
		}
	}
	//Método dos para recorrer cadena.
	public void agregarAtras(char valor){
		Nodo nuevo =new Nodo(valor);
		if(inicio == null)
			inicio=nuevo;
		else{
			Nodo aux=inicio;
			while(aux.getSiguiente() != null)
				aux= aux.getSiguiente();
			aux.setSiguiente(nuevo);
		}
	}
	
	//Método para contar los caracteres del mensaje, el cual nos servirá para establecer la frecuencia de cada carácter.

	public Integer contarIguales(char d) throws FueraDeRango, ListaVacia{
		Nodo aux=inicio;
		Integer contador=0;
		Integer contador2=0;
		while(aux!=null){
			contador2++;
			if(aux.getDato()==d){
				contador ++;
				aux=aux.getSiguiente();
				this.eliminaPosicion(contador2);
				contador2--;
			}else
				aux=aux.getSiguiente();
		}
		return contador;
	}
	
	//Sirve para eliminar la posición de cada caracter igual que haya en la lista, de esta forma, sólo deja los carácteres principales.
	public void eliminaPosicion(int posicion) throws FueraDeRango, ListaVacia{
		if(inicio != null){
			if(posicion>0){
				Nodo ant=null;
				Nodo aux=inicio;
				int cont=1;
				while((aux!=null) && (cont != posicion)){
					cont ++;
					ant =aux;
					aux=aux.getSiguiente();
				}
				if(aux==null)
					throw new FueraDeRango(); 
				if(ant==null){
					inicio=inicio.getSiguiente();
			
					aux=null;
				}else{
					ant.setSiguiente(aux.getSiguiente());
					
					aux=null;
				}

			}
		}else
			throw new ListaVacia();
	}

	public void toLista(String mensaje){
		for(int i=0;i<mensaje.length();i++)
			this.agregarAtras(mensaje.charAt(i));
	}

}