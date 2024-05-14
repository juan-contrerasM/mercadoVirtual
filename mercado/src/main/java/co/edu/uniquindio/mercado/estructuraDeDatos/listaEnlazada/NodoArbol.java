package co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada;

public class NodoArbol <T>{
    private T valor;
    private NodoArbol <T> izquierda;
    private NodoArbol<T> derecha;

    public NodoArbol() {
    }

    public NodoArbol(T valor) {
        this.valor = valor;
    }

    public NodoArbol(T valor, NodoArbol<T> izquierda, NodoArbol<T> derecha) {
        this.valor = valor;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NodoArbol<T> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoArbol<T> izquierda) {
        this.izquierda = izquierda;
    }

    public NodoArbol<T> getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoArbol<T> derecha) {
        this.derecha = derecha;
    }
}
