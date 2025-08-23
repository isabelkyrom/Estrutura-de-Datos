package Actividades.Actividad_2;

import java.util.InputMismatchException;
import java.util.Scanner;

class Node {
    Object dato;
    Node next;       // Siguiente
    
    public Node(Object dato) {
        this.dato = dato;
        this.next = null;
    }
}
// Clase de Pilas
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
            System.out.println("La pila esta vacía");
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
// Clase de Colas
class Queue {
    Node front;
    Node rear;
    private int capacidad;
    private int capacidadTotal;

    public Queue() {
        front = null;
        rear = null;
        capacidadTotal = 100;
        capacidad = capacidadTotal;
    }

    // Agregar a la cola
    public void enqueue(Object dato) {
        if (capacidad == 0) {
            return;
        } else {
            Node nuevo = new Node(dato);
            if ( front == null) {     // Si la cola esta vacía
                front = nuevo;
                rear = nuevo;
            } else {
                rear.next = nuevo;      // Enlazar el nuevo nodo final
                rear = nuevo;          // Mover rear al nuevo nodo
            }
            capacidad --;
        }
    }
    // Devolver el primero de la cola
    public Object dequeue() {
        if (front == null) {
            return null;
        }
        Object o = front.dato;      // Obtener el valor del nodo al frente
        front = front.next;         // Mover el frente al siguiente nodo
        if (front == null) {        // Si la cola queda vacía, rear debe ser null
            rear = null;
        }
        capacidad ++;
        return o;
    }
    // Ver el primer valor de la cola
    public Object peek() {
        if(front == null) {     // Si la cola esta vacía
            System.out.println("La cola esta vacía");
            return null;        // valor por defecto
        }
        return front.dato;
    }
    // Mostrar todos los elementos de la cola
    public void display() {
        if(front == null) {     // Si la cola esta vacía
            System.out.println("La cola esta vacía");
            return; 
        }
        Node temp = front; // Empezar desde el frente
        while (temp != null) {   // Recorrer hasta el final
            System.out.print(temp.dato + " -> ");  // Imprimir el valor del nodo
            temp = temp.next;    // mover al siguiente nodo
        }
    }
    // Mostrar capacidad de la cola
    public void capacity() {
        System.out.println("Capacidad total de la cola: " + capacidadTotal);
        System.out.println("Capacidad restante de la cola: " + capacidad);
    }
    // Método getter
    public int getCapacidad() {
        return capacidad;
    }
}

// Menú

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack pila = new Stack();
        Queue cola = new Queue();
        
        boolean repetir = true;
        while (repetir) {
            int opcion = 0;
            try {
                System.out.println("Que desea manejar? \n1-. Comandos del Sistema Operativo \n2-. Procesos o Aplicaciones \n3-. Salir");
                System.out.print(">>> ");
                opcion = sc.nextInt();
                
                if (opcion < 1 || opcion > 3) {
                        throw new IllegalArgumentException("Opción fuera de rango (1-2)");
                    }

            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un VALOR correcto");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Ingrese un VALOR dentro del rango (1-2)");
                sc.nextLine();
            }

            switch (opcion) {

                case 1:
                    boolean repetir2 = true;
                    while (repetir2) {
                        try {
                            System.out.println("\nQue desea hacer? \n1-. Hacer un comando \n2-. Deshacer comando(CTRL + Z) \n3-. Ver comando mas reciente \n4-. Mostrar historial completo \n5-. Ver Capacidad de la pila \n6-. Regresar");
                            System.out.print(">>> ");
                            opcion = sc.nextInt();
                        
                            switch(opcion) {
                                // Push
                                case 1:
                                    int cap = pila.getCapacidad();
                                    try {
                                    System.out.println("\nCual comando desea realizar? \n1-. CD \n2-. Copy \n3-. Ping \n4-. ipcongif \n5-. mv \n6-. Exit");
                                    System.out.print(">>> ");
                                    opcion = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.println("Error: Ingrese un VALOR correcto");
                                    } 
                                    switch(opcion) {
                                        case 1: 
                                            pila.push("CD");
                                            if(cap != 0) {
                                                System.out.println("Comando 'CD' realizado");
                                            }
                                            break;
                                        case 2: 
                                            pila.push("Copy");
                                            if(cap != 0) {
                                                System.out.println("Comando 'Copy' realizado");
                                            }
                                            break;
                                        case 3: 
                                            pila.push("Ping");
                                            if(cap != 0) {
                                                System.out.println("Comando 'Ping' realizado");
                                            }
                                            break;
                                        case 4: 
                                            pila.push("ipconfig");
                                            if(cap != 0) {
                                                System.out.println("Comando 'ipconfig' realizado");
                                            }
                                            break;
                                        case 5: 
                                            pila.push("mv");
                                            if(cap != 0) {
                                                System.out.println("Comando 'mv' realizado");
                                            }
                                            break;
                                        case 6: 
                                            pila.push("exit");
                                            if(cap != 0) {
                                                System.out.println("Comando 'Exit' realizado");
                                            }
                                            break;
                                    }
                                    break;
                                // Pop
                                case 2:
                                    System.out.println("Comando '" + pila.pop() + "' borrado");
                                    break;
                                // Peek
                                case 3:
                                    System.out.println("Comando más reciente: " + pila.peek());
                                    break;
                                // Mostrar toda la pila
                                case 4:
                                    pila.display();
                                    break;
                                // Mostrar capacidad de la pila
                                case 5: 
                                    pila.capacity();
                                    break;
                                // Regresar
                                case 6:
                                    repetir2 = false;
                                    break;
                                default:
                                    System.out.println("Opción inválida, intente de nuevo");
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Ingrese un VALOR correcto");
                        }
                    }
                    break;
                case 2:
                    boolean repetir3 = true;
                    while (repetir3) {
                        int opcion3 = 0;
                        try {
                            System.out.println("\n¿Qué desea hacer? \n1-. Insertar proceso \n2-. Realizar proceso \n3-. Ver proceso por realizar \n4-. Ver cola de procesos \n5-. Ver capacidad de la cola \n6-. Regresar");
                            System.out.print(">>> ");
                            opcion3 = sc.nextInt();
                            sc.nextLine();
                            
                            switch (opcion3) {
                                // Enqueue
                                case 1:
                                    System.out.print("Ingrese el proceso: ");
                                    String proceso = sc.nextLine();
                                    cola.enqueue(proceso);
                                    break;

                                // Dequeue
                                case 2:
                                    Object realizado = cola.dequeue();
                                    if (realizado != null) {
                                        System.out.println("Proceso realizado: " + realizado);
                                    } else {
                                        System.out.println("La cola está vacía.");
                                    }
                                    break;

                                // Peek
                                case 3:
                                    Object porRealizar = cola.peek();
                                    if (porRealizar != null) {
                                        System.out.println("Próximo proceso a realizar: " + porRealizar);
                                    }
                                    break;

                                // Mostrar cola completa
                                case 4:
                                    System.out.print("Procesos en cola: ");
                                    cola.display();
                                    System.out.println();
                                    break;
                                
                                // Capacidad de la cola
                                case 5:
                                    cola.capacity();
                                    break;

                                // Regresar
                                case 6:
                                    repetir3 = false;
                                    break;

                                default:
                                    System.out.println("Opción inválida. Intente de nuevo.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Ingrese un valor numérico.");
                            sc.nextLine();
                        }
                    }
                
                case 3:
                    repetir = false;
                    break;
            }
        }
        System.out.println(">>> Programa cerrado");
    }
}
