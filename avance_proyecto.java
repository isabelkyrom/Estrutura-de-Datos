package Actividades;

import java.util.InputMismatchException;
import java.util.Scanner;

// Clases de Nodos
class Node {      // Nodo normal
    Object dato;
    Node next;
    public Node(Object dato) {
        this.dato = dato;
        next = null;
    }
}
class PriorityNode {    // Nodo colas de prioridad
    Object dato;
    final int priority;

    public PriorityNode(Object dato, int priority) {
        this.dato = dato;
        this.priority = priority;
    }

    // Getters
    public int getPriority() {
        return priority;
    }
    public Object getDato() {
        return dato;
    }
}

// Clases lista, pila y colas

// Lista simple
class Lista {
    Node cabeza;
    Node pie;
    public Lista() {
        cabeza = null;
        pie = null;
    }

    // Getters
    public Object getDato(int index) {
        Node actual = cabeza; 
        int contador = 0;

        while (actual != null) {
            if (contador == index) {
                return actual.dato;
            }else{
                
            }
            actual = actual.next; 
            contador++;
        }
        return null;
    }

    // Indice de la lista
    public void index() {
        Node actual = cabeza;
        int index = 0;
        while (actual != null) {
            System.out.println(index + ": " + actual.dato);
            actual = actual.next;
            index++;
        }
    }
    // Push
    public void push(Object dato) {
        Node nuevo = new Node(dato);

        if (cabeza == null) {
            cabeza = nuevo;
            pie = nuevo;
        } else {
            pie.next = nuevo;
            pie = nuevo;
        }
    }
    // Pop
    public Object pop(int index) {
        if (cabeza == null) {
            return null; // Lista vacía
        }

        Node temp = cabeza;

        // Caso especial: eliminar la cabeza
        if (index == 0) {
            Object dato = cabeza.dato;
            cabeza = cabeza.next;
            if (cabeza == null) pie = null; // Si borramos el único nodo
            return dato;
        }

        // Buscar el nodo anterior al que queremos borrar
        Node prev = null;
        int contador = 0;
        while (temp != null && contador < index) {
            prev = temp;
            temp = temp.next;
            contador++;
        }

        if (temp == null) {
            System.out.println("No se encuentra el producto");
            return null;
        }

        // Enlazar para saltarse el nodo eliminado
        Object dato = temp.dato;
        prev.next = temp.next;

        // Si eliminamos el último, actualizar pie
        if (temp == pie) {
            pie = prev;
        }

        return dato;
    }
    // Mostrar
    public void mostrar() {
        Node actual = cabeza;
        while (actual != null) {
            System.out.println("//   - " + actual.dato);
            actual = actual.next;
        }
    }
}

// Pilas
class Stack {
    Node top;
    private int capacidad;
    private int capacidadTotal;
    
    public Stack() {
        top = null;
        capacidadTotal = 100;
        capacidad = capacidadTotal;
    }

    // Agregar a la pila
    public void push(Object dato) {
        if (capacidad == 0) {
            System.out.println("Espacio lleno, libere espacio para hacer más operaciones");
            return;
        } else {
            Node nuevo = new Node(dato);
            if (top == null) {
                top = nuevo;
            }else {
                nuevo.next = top;     // enlazar el nuevo nodo a la cima de la pila
                top = nuevo;          // mover top al nuevo nodo
                
            }
            capacidad --;
        }
    }
    // Devolver de la pila
    public Object pop() {
        if (top == null) {
            return null;
        }
        Object o = top.dato;      // Obtener el valor de la cima
        top = top.next;           // mover top al siguiente dato
        capacidad ++;
        return o;
    }
    // Ver el dato de la cima
    public Object peek() {
        if (top == null) 
            return null;
        ;
        return top.dato;       // retorna el dato de la cima
    }
    // Ver pila completa
    public void display() {
        if(top == null) {     // Si la pila esta vacía
            System.out.println("Historial vacío...");
            return; 
        }
        Node temp = top; // Empezar desde el frente
        while (temp != null) {   // Recorrer hasta el final
            System.out.print(temp.dato + " -> ");  // Imprimir el valor del nodo
            temp = temp.next;    // mover al siguiente nodo
        }
    }
    // Ver capacidad de la pila
    public void capacity() {
        System.out.println("Capacidad total de la pila: " + capacidadTotal);
        System.out.println("Capacidad restante de la pila: " + capacidad);
    }
    // Metodo getter
    public int getCapacidad() {
        return capacidad;
    }
}

// Cola de Prioridad
class PriorityQueue {
    PriorityNode[] data;
    int tamaño;
    int limite;

    public PriorityQueue() {
        limite = 50;
        data = new PriorityNode[limite + 1];
        tamaño = 0;
    }

    // Enqueue
    public void enqueue(Object dato, int priority) {
        if (tamaño == limite) {
            System.out.println("\nLimite alcanzado, ya no puede agregar más datos");
            return;
        }

        PriorityNode nuevo = new PriorityNode(dato, priority);
        tamaño++;
        data[tamaño] = nuevo;

        // Hacer "bubble up"
        int index = tamaño;
        int indexPadre = index / 2;

        while (index > 1 && data[index].getPriority() < data[indexPadre].getPriority()) {
            PriorityNode temp = data[index];
            data[index] = data[indexPadre];
            data[indexPadre] = temp;

            index = indexPadre;
            indexPadre = index / 2;
        }
    }
    // Dequeue
    public Object dequeue() {
        if (tamaño == 0) {
            System.out.println("\nCola vacía, agrege un dato");
            return null;
        }

        PriorityNode raiz = data[1];   // Guardamos el nodo raíz
        data[1] = data[tamaño];        // Último nodo pasa a la raíz
        tamaño--;

        int i = 1;

        // Hacer "heapify down"
        while (true) {
            int izquierda = 2 * i;
            int derecha = 2 * i + 1;
            int menor = i;

            if (izquierda <= tamaño && data[izquierda].getPriority() < data[menor].getPriority()) {
                menor = izquierda;
            }
            if (derecha <= tamaño && data[derecha].getPriority() < data[menor].getPriority()) {
                menor = derecha;
            }

            if (menor == i) break;

            PriorityNode temp = data[i];
            data[i] = data[menor];
            data[menor] = temp;

            i = menor;
        }

        return raiz.getDato();
    }
    // Peek
    public Object peek() {
        if (tamaño == 0) return null;
        return data[1].getDato();
    }
    // Mostrar
    public void mostrar() {
        for (int i = 1; i <= tamaño; i++) {
            System.out.println(data[i].getDato() + " (prioridad " + data[i].getPriority() + ")");
        }
    }
}

// Main
public class avance_proyecto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lista productos = new Lista();
        Lista carrito = new Lista();
        PriorityQueue porEnviar = new PriorityQueue();
        Stack pedidosAnteriores = new Stack();

        // Datos para casos
        productos.push("Camisa");
        productos.push("Pantalon");
        productos.push("Zapatos");
        productos.push("Collar");
        productos.push("Lentes de sol");

        porEnviar.enqueue("Pedido 1", 2);
        porEnviar.enqueue("Pedido 2", 1);
        porEnviar.enqueue("Camisa rosa", 3);

        carrito.push("Reloj");
        carrito.push("Pantalon");
        carrito.push("Bolsa roja");

        
        try{
            
            System.out.println("1-. Usuario \n2-. Administrador \n3-. Salir");
            System.out.print(">>> ");
            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion < 1 || opcion > 4) {
                throw new IllegalArgumentException("Opción fuera de rango (1-4)");
            }

            switch(opcion) {
                
                case 1: // Menú Usuario
                    System.out.println("\nBienvenido a la Tienda Online JDGATE");

                    boolean repetirUs = true;
                    while(repetirUs) {
                        System.out.println("\nQue quiere hacer? \n   1-. Ver productos \n   2-. Ver carrito \n   3-. Ver historial de compras \n   4-. Salir");
                        System.out.print(">>> ");
                        opcion = sc.nextInt();
                        sc.nextLine();

                        switch(opcion) {
                            
                            case 1: // Productos
                                System.err.println("\n PRODUCTOS: ");
                                productos.mostrar();

                                System.out.println("\nDesea comprar un producto? \n1-. SI 2-. NO");
                                System.out.print(">>> ");
                                opcion = sc.nextInt();
                                sc.nextLine();

                                switch(opcion) {
                                    case 1: // Comprar producto
                                        System.out.println("\nIngrese el index del producto deseado");
                                    
                                        productos.index();

                                        System.out.print(">>> ");
                                        int num = sc.nextInt();
                                        Object compra = productos.getDato(num);
                                        pedidosAnteriores.push(compra);

                                        // Aqui se usa la cola priorizada
                                        System.out.println("\nQue tipo de entrega desea?: \n1-. Premiun \n2-. Regular \n(Si elige premium se le dara más prioridad a su pedido al enviar)");
                                        System.out.print(">>> ");
                                        int entrega = sc.nextInt();
                                        
                                        if(entrega == 1) {
                                            System.out.println("Su pedido tomara prioridad sobre otros.");
                                            porEnviar.enqueue(compra, 2);
                                        } else {
                                            System.out.println("Entendido");
                                            porEnviar.enqueue(compra, 3);
                                        }
                                        System.out.println("\n   Gracias por su compra!");
                                        break;
                                    case 2: // Regresar
                                        break;
                                }
                                break;
                            case 2: // Carrito
                                System.out.println("\n CARRITO");
                                carrito.mostrar();

                                boolean repetirCar = true;
                                while(repetirCar) {

                                    System.out.println("\nDesea comprar o borrar algo de su carrito? \n1-. Comprar 2-. Borrar 3-. Salir");
                                    System.out.print(">>> ");
                                    opcion = sc.nextInt();
                                    sc.nextLine();

                                    switch(opcion) {
                                        
                                        case 1: // Comprar del carrito
                                            System.out.println("\nIngrese el index del producto a comprar");
                                    
                                            carrito.index();

                                            System.out.print(">>> ");
                                            int num = sc.nextInt();
                                            Object compra = carrito.getDato(num);
                                            pedidosAnteriores.push(compra);

                                            // Aqui se usa la cola priorizada
                                            System.out.println("\nQue tipo de entrega desea?: \n1-. Premiun \n2-. Regular \n(Si elige premium se le dara más prioridad a su pedido al enviar)");
                                            int entrega = sc.nextInt();
                                            
                                            if(entrega == 1) {
                                                System.out.println("Su pedido tomara prioridad sobre otros.");
                                                porEnviar.enqueue(compra, 2);
                                            } else {
                                                System.out.println("Entendido");
                                                porEnviar.enqueue(compra, 3);
                                            }
                                            System.out.println("\n   Gracias por su compra!");
                                            break;
                                        
                                        
                                        case 2: // Borrar del carrito
                                            carrito.index();
                                            System.out.println("\nIngrese el index del producto a borrar");
                                            System.out.print(">>> ");

                                            int borrar = sc.nextInt();
                                            
                                            System.out.println("Producto '" + carrito.pop(borrar) + "' Borrado del Carrito");
                                            break;
                                        
                                        case 3: // Regresar
                                            repetirCar = false;
                                            break;
                                    }
                                }
                                break;
                            case 3: // Historial de compras
                                System.err.println("\n HISTORIAL DE COMPRAS: ");
                                System.err.println("\n Ultimo producto comprado: ");
                                pedidosAnteriores.peek();

                                System.err.println("\n Historial completo: ");
                                pedidosAnteriores.display();
                                break;
                            case 4: // Regresar
                                repetirUs = false;
                                break;

                        }
                    }
                    break;
                
                case 2: // Menú Administrador
                    System.out.println("\nBienvenido Administrador");    

                    boolean repetirAdmin = true;
                    while(repetirAdmin) {
                        System.out.println("\nQue quiere hacer? \n   1-. Ver Productos \n   2-. Enviar Pedidos \n   3-. Salir");
                        System.out.print(">>> ");
                        opcion = sc.nextInt();
                        sc.nextLine();
                        
                        switch(opcion) {
                            
                            case 1: // Ver Productos
                                System.out.println("\n PRODUCTOS: ");
                                productos.mostrar();

                                System.out.println("\n1-. Agregar producto   2-. Borrar producto   3-. Regresar");
                                System.out.print(">>> ");
                                opcion = sc.nextInt();
                                sc.nextLine();

                                switch(opcion) {
                                    
                                    case 1: // Agregar Producto
                                        System.out.print("\nIngrese un producto: ");
                                        Object producto = sc.next();
                                        sc.nextLine();

                                        productos.push(producto);
                                        System.out.println("Producto '" + producto + "' Agregado");
                                        break;
                                    
                                    case 2: // Borrar producto
                                        System.out.println("\nIngrese el index del producto a borrar");
                                        productos.index();

                                        int index = sc.nextInt();
                                        Object borrado = productos.pop(index);
                                        System.out.println("//   Producto '" + borrado + "' Borrado");
                                        break;
                                    
                                    case 3: // Regresar
                                        break;
                                }
                                break;
                            
                            case 2: // Enviar Pedidos
                                System.out.println("Pedidos por enviar: ");
                                porEnviar.mostrar();

                                System.out.println("\nPedido '" + porEnviar.dequeue() + "' Enviado");

                                break;
                            
                            
                            case 3: // Regresar
                                repetirAdmin = false;
                                break;
                        }
                    }
                    break;

                
                case 3: // Salir
                    System.out.println(" >>>");
                    break;
            }

            
        } catch(InputMismatchException e) {
            System.out.println("Error: Ingrese un VALOR correcto");
            sc.nextLine();
        }catch (IllegalArgumentException e) {
            System.out.println("Error: Ingrese un VALOR dentro del rango (1-4)");
            sc.nextLine();
        }

        System.out.println(">>>>");

    }
}