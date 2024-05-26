package co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada;

import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.NodoArbol;


public class Arbol<T extends Comparable<T>> {

    private NodoArbol<T> raiz;
    private int peso;

    public Arbol() {
        this.raiz = null;
        this.peso = 0;
    }

    public Arbol(T valor) {
        this.raiz = new NodoArbol<>(valor);
        this.peso++;
    }

    public void insertar(T valor) {
        raiz = insertarRec(raiz, valor);
    }

    private NodoArbol<T> insertarRec(NodoArbol<T> nodo, T valor) {
        if (nodo == null) {
            peso++;
            return new NodoArbol<T>(valor);
        }

        if ((Integer) valor < (Integer) nodo.getValor()) {
            nodo.setIzquierda(insertarRec(nodo.getIzquierda(), valor));
        } else if ((Integer) valor > (Integer) nodo.getValor()) {
            nodo.setDerecha(insertarRec(nodo.getDerecha(), valor));
        }

        return nodo;
    }

    public boolean buscar(T valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(NodoArbol<T> nodo, T valor) {
        if (nodo == null) {
            return false;
        }

        if (valor.equals(nodo.getValor())) {
            return true;
        }

        if ((Integer) valor < (Integer) nodo.getValor()) {
            return buscarRec(nodo.getIzquierda(), valor);
        } else {

            return buscarRec(nodo.getDerecha(), valor);
        }
    }

    public void eliminar(T valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private NodoArbol<T> eliminarRec(NodoArbol<T> nodo, T valor) {
        if (nodo == null) {
            return null;
        }

        if ((Integer) valor < (Integer) nodo.getValor()) {
            nodo.setIzquierda(eliminarRec(nodo.getIzquierda(), valor));
        } else if ((Integer) valor > (Integer) nodo.getValor()) {
            nodo.setDerecha(eliminarRec(nodo.getDerecha(), valor));
        } else {
            if (nodo.getIzquierda() == null) {
                return nodo.getDerecha();
            } else if (nodo.getDerecha() == null) {
                return nodo.getIzquierda();
            }

            nodo.setValor(encontrarMinimo(nodo.getDerecha()).getValor());
            nodo.setDerecha(eliminarRec(nodo.getDerecha(), nodo.getValor()));
        }

        return nodo;
    }

    private NodoArbol<T> encontrarMinimo(NodoArbol<T> nodo) {
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo;
    }

    public void imprimirInOrder() {
        imprimirInOrderRec(raiz);
        System.out.println();
    }

    private void imprimirInOrderRec(NodoArbol<T> nodo) {
        if (nodo != null) {
            imprimirInOrderRec(nodo.getIzquierda());
            System.out.print(nodo.getValor() + " ");
            imprimirInOrderRec(nodo.getDerecha());
        }
    }

    public boolean contains(T valor) {
        return containsRec(raiz, valor);
    }

    private boolean containsRec(NodoArbol<T> nodo, T valor) {
        if (nodo == null) {
            return false;
        }

        if (valor.equals(nodo.getValor())) {
            return true;
        }

        if ((Integer) valor < (Integer) nodo.getValor()) {
            return containsRec(nodo.getIzquierda(), valor);
        } else {
            return containsRec(nodo.getDerecha(), valor);
        }
    }

}


