package co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada;


import lombok.ToString;

@ToString
    public class Pila<T> {

        //--------------------------atributos----------------------
        private Nodo<T> nodoCabeza;
        private Nodo<T> nodoUltimo;
        private int tamanio;


        //------------------constructor--------------------------------------
        public Pila() {
            this.nodoCabeza = null;
            this.nodoUltimo = null;
            this.tamanio = 0;

        }

        //--------------------metodos-------------------------

        public  void clear(){
            if(!(nodoCabeza==null)){
                nodoCabeza=null;
                nodoUltimo=null;
                tamanio=0;
            }
        }

        //se agrega al inicio
        public void apilar(T valorNodo) {
            Nodo<T> nuevoNodo = new Nodo<>(valorNodo);

                if (estaVacia()) {
                    nodoCabeza = nuevoNodo;
                    nodoUltimo = nuevoNodo;
                } else {
                    nuevoNodo.setSiguienteNodo(nodoCabeza);
                    nodoCabeza = nuevoNodo;
                }
                tamanio++;


        }


        //Verificar si la cola esta vacia
        public boolean estaVacia() {
            return (nodoCabeza == null) ? true : false;
        }


        //Elimina el primer nodo de la cola
        public T desapilar() {

            if (!estaVacia()) {
                Nodo<T> n = nodoCabeza;
                T valor = n.getValorNodo();
                nodoCabeza = n.getSiguienteNodo();

                if (nodoCabeza == null) {
//				nodoUltimo = null;
                }

                tamanio--;
                return valor;
            }

            throw new RuntimeException("Lista vac�a");
        }

        //obtener nodo primero
        public Nodo<T> obtenetNodoFrente() {
            return nodoCabeza;
        }


        public void imprimirLista() {

            Nodo<T> aux = nodoCabeza;

            while (aux != null) {
                System.out.println(aux.getValorNodo() + "\t");
                aux = aux.getSiguienteNodo();

            }


        }

        public Nodo<T> obtenerCima() {
            return nodoCabeza;
        }

        public Nodo<T> getNodoCabeza() {
            return nodoCabeza;
        }

        public void setNodoCabeza(Nodo<T> nodoCabeza) {
            this.nodoCabeza = nodoCabeza;
        }

        public Nodo<T> getNodoUltimo() {
            return nodoUltimo;
        }

        public void setNodoUltimo(Nodo<T> nodoUltimo) {
            this.nodoUltimo = nodoUltimo;
        }

        public int getTamanio() {
            return tamanio;
        }

        public void setTamanio(int tamanio) {
            this.tamanio = tamanio;
        }
    }

