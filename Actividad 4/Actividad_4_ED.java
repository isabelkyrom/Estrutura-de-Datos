import java.util.InputMismatchException;
import java.util.Scanner;

class Nodo {
    int valor;
    String nombre;
    Nodo hijoizquierdo, hijoderecho;
    public Nodo(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
        this.hijoizquierdo = null;
        this.hijoderecho = null;
    }
}
class ArbolBinario {
    Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    // Insertar nodo
    public void agregarNodo(int valor, String nom) {
        Nodo nuevo = new Nodo(valor, nom);
        if (raiz == null) {
            raiz = nuevo;
            return;
        }
        Nodo auxiliar = raiz;
        Nodo padre;
        while (true) {
            padre = auxiliar;
            if (valor < auxiliar.valor) {
                auxiliar = auxiliar.hijoizquierdo;
                if (auxiliar == null) {
                    padre.hijoizquierdo = nuevo;
                    return;
                }
            } else {
                auxiliar = auxiliar.hijoderecho;
                if (auxiliar == null) {
                    padre.hijoderecho = nuevo;
                    return;
                }
            }
        }
    }

    // Buscar nodo
    public Nodo buscar(Nodo nodo, int id) {
        if (nodo == null) 
            return null;

        if (nodo.valor == id) {
            return nodo;
        }

        Nodo izq = buscar(nodo.hijoizquierdo, id);
        if (izq != null) return izq;

        return buscar(nodo.hijoderecho, id);
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // Recorridos
    // Inorden
    public void inorden() {
        inorden(raiz);
    }
    private void inorden(Nodo nodo) {
        if (nodo != null) {
            inorden(nodo.hijoizquierdo);
            System.out.println(nodo.valor + " -> " + nodo.nombre);
            inorden(nodo.hijoderecho);
        }
    }
    
    // Preorden
    public void preorden() {
        preorden(raiz);
    }
    private void preorden(Nodo nodo) {
        if (nodo != null) {
            System.out.println(nodo.valor + " -> " + nodo.nombre);
            preorden(nodo.hijoizquierdo);
            preorden(nodo.hijoderecho);
        }
    }

    // Postorden
    public void postorden() {
        postorden(raiz);
    }
    private void postorden(Nodo nodo) {
        if (nodo != null) {
            postorden(nodo.hijoizquierdo);
            postorden(nodo.hijoderecho);
            System.out.println(nodo.valor + " -> " + nodo.nombre);
        }
    }

    // Eliminación
    public Nodo eliminar(Nodo nodo, int valor) {
        if (nodo == null) return null;

        if (valor < nodo.valor) {
            nodo.hijoizquierdo = eliminar(nodo.hijoizquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.hijoderecho = eliminar(nodo.hijoderecho, valor);
        } else {
            // Caso 1: sin hijos
            if (nodo.hijoizquierdo == null && nodo.hijoderecho == null) {
                return null;
            }
            // Caso 2: un solo hijo
            else if (nodo.hijoizquierdo == null) {
                return nodo.hijoderecho;
            } else if (nodo.hijoderecho == null) {
                return nodo.hijoizquierdo;
            }
            // Caso 3: dos hijos
            else {
                Nodo sucesor = encontrarMinimo(nodo.hijoderecho);
                nodo.valor = sucesor.valor;
                nodo.nombre = sucesor.nombre;
                nodo.hijoderecho = eliminar(nodo.hijoderecho, sucesor.valor);
            }
        }
        return nodo;
    }

    // Encontrar el nodo con valor mínimo en un subárbol
    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.hijoizquierdo != null) {
            nodo = nodo.hijoizquierdo;
        }
        return nodo;
    }

    // Mostrar arbol
    public void mostrar(Nodo nodo, String espacio) {
        if(nodo == null) return;
        System.out.println(espacio + "ID: " + nodo.valor + " -> Nombre: " + nodo.nombre);
        mostrar(nodo.hijoizquierdo, espacio);
        mostrar(nodo.hijoderecho, espacio);
    }
}
public class Actividad_4_ED {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolBinario arbol = new ArbolBinario();
        
        /* 
        // Agregar nodo
        arbol.agregarNodo(40, "Raíz");
        arbol.agregarNodo(20, "Izq");
        arbol.agregarNodo(10, "Der");
        arbol.agregarNodo(50, "Izq-Izq");
        arbol.agregarNodo(30, "Izq-Der");
        arbol.agregarNodo(60, "Der-Izq");
        arbol.agregarNodo(70, "Der-Der");

        // Recorridos
        System.out.println("\nPreOrden");
        arbol.preorden();

        System.out.println("\nInOrden");
        arbol.inorden();

        System.out.println("\nPostOrden");
        arbol.postorden();

        // Mostrar
        System.out.println("\n");
        arbol.mostrar(arbol.raiz, "");

        // Eliminacion
        arbol.eliminar(arbol.raiz, 70);
        arbol.eliminar(arbol.raiz, 10);
        arbol.eliminar(arbol.raiz, 40);
        
        System.out.println("\n");
        arbol.mostrar(arbol.raiz, "");

        // Busqueda
        System.out.println("\n");
        Nodo buscar = arbol.buscar(arbol.raiz, 30);
        Nodo buscar2 = arbol.buscar(arbol.raiz, 60);
        System.out.println("Buscando Nodo con valor de 30:  \nNodo encontrado: " + buscar.nombre);
        System.out.println("Buscando Nodo con valor de 60:  \nNodo encontrado: " + buscar2.nombre);
        */
        
        // ID: Contiene 6 números, esta compuesto por el año,el mes y el numero de empleado registrado
        int numEmpleado = 3;
        arbol.agregarNodo(101001, "Isabel");
        arbol.agregarNodo(100802, "Dafne");
        arbol.agregarNodo(100903, "Jocelyn");

        try{
            boolean repetir = true;
            while(repetir) {
                
                System.out.println("\n 1-. Ver empleados \n 2-. Insertar empleado \n 3-. Buscar Empleado por ID \n4-. Salir");
                System.out.print(">>> ");
                int opcion = sc.nextInt();
                
                if (opcion < 1 || opcion > 4) {
                    throw new IllegalArgumentException("Opción fuera de rango (1-4)");
                }

                switch(opcion) {
                    // Mostrar empleados
                    case 1:
                        System.out.println("\n EMPLEADOS ");
                        arbol.mostrar(arbol.raiz, "");
                        break;
                    
                    // Agregar Empleados
                    case 2:
                        numEmpleado ++;
                        System.out.println("\nIngrese el ID(año, mes, número actual de empleado: " + numEmpleado + ") y el nombre del Empleado: ");
                        System.out.print(">>> ID: ");
                        int ID = sc.nextInt();
                        sc.nextLine();
                        System.out.print(">>> Nombre: ");
                        String nombre = sc.nextLine();

                        arbol.agregarNodo(ID, nombre);
                        break;
                    
                    // Buscar
                    case 3:
                        System.out.println("\nIngrese el ID(año, mes, número actual de empleado: " + numEmpleado + ") del empleado que quiere buscar: ");
                        System.out.print(">>> ID: ");
                        ID = sc.nextInt();
                        sc.nextLine();

                        Nodo encontrado = arbol.buscar(arbol.raiz, ID);
                        if(encontrado != null){
                            System.out.println("\nEmpleado encontrado: \n ID: " + encontrado.valor + "\n Nombre: " + encontrado.nombre);
                        }else{
                            System.out.println("\nNo hay un empleado con ese ID");
                        }

                        break;
                    
                    // Salir
                    case 4:
                        System.out.println("\nSesión terminada \n>>>>");
                        repetir = false;
                        break;
                }

            }

            
        } catch(InputMismatchException e) {
            System.out.println("Error: Ingrese un VALOR correcto");
            sc.nextLine();
        }catch (IllegalArgumentException e) {
            System.out.println("Error: Ingrese un VALOR dentro del rango (1-2)");
            sc.nextLine();
        }
        
    }
    
}
