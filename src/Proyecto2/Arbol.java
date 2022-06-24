package Proyecto2;

public class Arbol {
	private NodoArbol raiz;

	public NodoArbol getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoArbol raiz) {
		this.raiz = raiz;
	}
	
	public Arbol(){
		raiz=null;
	}
	//Será la suma
	public Arbol(String s,Integer suma){
		raiz=new NodoArbol(s,suma);
	}
	
	public void AgregarHojas(NodoArbol izq,NodoArbol der){
		raiz.setIzq(izq);
		raiz.setDer(der);
	}
}
