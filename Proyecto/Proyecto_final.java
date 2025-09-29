
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

// Clases de Nodos
class PriorityNode {    // Nodo colas de prioridad
    Tarea dato;
    final int priority;

    public PriorityNode(Tarea dato, int priority) {
        this.dato = dato;
        this.priority = priority;
    }

    // Getters
    public int getPriority() {
        return priority;
    }
    public Tarea getDato() {
        return dato;
    }
}
class NodoArbol {
    int valor;
    String nombre;
    NodoArbol hijoizquierdo, hijoderecho;
    public NodoArbol(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
        this.hijoizquierdo = null;
        this.hijoderecho = null;
    }
}

// Producto
class Producto{
    String nombre;
    boolean entregado;
    int precio;

    public Producto(String nombre, int precio) {
        this.nombre = nombre;
        this.entregado = false;
        this.precio = precio;
    }

    // Getters

    public String getNombre() {
        return this.nombre;
    }
    public int getPrecio() {
        return this.precio;
    }


}
// Tarea
class Tarea{
    String nombre;
    int prioridad;
    String descripcion;
    int horasEstimadas;
    public Tarea(String nombre, int prioridad, String descripcion, int horasEstimadas){
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.horasEstimadas = horasEstimadas;
    }

    // Getters
    public String getNombre() {
        return this.nombre;
    }
    public int getPrioridad() {
        return this.prioridad;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public int getHoras() {
        return this.horasEstimadas;
    }
}

// ---- Clases y cola

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
    public void enqueue(Tarea dato, int priority) {
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
    public Tarea dequeue() {
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
    public Tarea peek() {
        if (tamaño == 0) return null;
        return data[1].getDato();
    }
    // Mostrar
    public void mostrar() {
        for (int i = 1; i <= tamaño; i++) {
            System.out.println(data[i].getDato().nombre + " (prioridad " + data[i].getPriority() + ")");
        }
    }
}

// Arbol Binario
class ArbolBinario {
    NodoArbol raiz;

    public ArbolBinario() {
        raiz = null;
    }

    // Insertar nodo
    public void agregarNodo(int valor, String nom) {
        NodoArbol nuevo = new NodoArbol(valor, nom);
        if (raiz == null) {
            raiz = nuevo;
            return;
        }

        NodoArbol auxiliar = raiz;
        NodoArbol padre;

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
    public NodoArbol buscar(NodoArbol nodo, int id) {
        if (nodo == null) 
            return null;

        if (nodo.valor == id) {
            return nodo;
        }

        NodoArbol izq = buscar(nodo.hijoizquierdo, id);
        if (izq != null) return izq;

        return buscar(nodo.hijoderecho, id);
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // Eliminación
    public NodoArbol eliminar(NodoArbol nodo, int valor) {
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
                NodoArbol sucesor = encontrarMinimo(nodo.hijoderecho);
                nodo.valor = sucesor.valor;
                nodo.nombre = sucesor.nombre;
                nodo.hijoderecho = eliminar(nodo.hijoderecho, sucesor.valor);
            }
        }
        return nodo;
    }

    // Encontrar el nodo con valor mínimo en un subárbol
    private NodoArbol encontrarMinimo(NodoArbol nodo) {
        while (nodo.hijoizquierdo != null) {
            nodo = nodo.hijoizquierdo;
        }
        return nodo;
    }

    // Mostrar arbol
    public void mostrar(NodoArbol nodo, String espacio) {
        if (nodo == null) return;
        System.out.println(espacio + "ID: " + nodo.valor + " -> Nombre: " + nodo.nombre);
        mostrar(nodo.hijoizquierdo, espacio + "   ");
        mostrar(nodo.hijoderecho, espacio + "   ");
    }

}



// Main
public class Proyecto_final {

    // BUSQUEDA: 
    public static Producto buscarPorNombre(String nombre, ArrayList<Producto> productos) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null; // no encontrado
    }

    // Sumar horas de tareas recursivamente
    public static int sumarHorasRecursivo(List<Tarea> lista, int inicio, int fin) {
        if (inicio > fin) return 0;
        if (inicio == fin) return lista.get(inicio).getHoras();
        int medio = (inicio + fin) / 2;
        return sumarHorasRecursivo(lista, inicio, medio) + sumarHorasRecursivo(lista, medio + 1, fin);
    }

    // Distribuir tareas entre empleados
    public static void distribuirTareasRec(List<Tarea> tareas, List<Integer> empleadosIds,
                                        HashMap<Integer, List<Tarea>> asignaciones) {
        if (tareas == null || tareas.isEmpty() || empleadosIds.isEmpty()) return;
        distribuirRecHelper(tareas, 0, tareas.size() - 1, empleadosIds, asignaciones);
    }

    private static void distribuirRecHelper(List<Tarea> tareas, int inicio, int fin,
                                            List<Integer> empleadosIds, HashMap<Integer, List<Tarea>> asignaciones) {
        if (inicio > fin) return;
        if (inicio == fin) {
            int emp = empleadoConMenorCarga(empleadosIds, asignaciones);
            asignaciones.putIfAbsent(emp, new ArrayList<>());
            asignaciones.get(emp).add(tareas.get(inicio));
            return;
        }
        int medio = (inicio + fin) / 2;
        distribuirRecHelper(tareas, inicio, medio, empleadosIds, asignaciones);
        distribuirRecHelper(tareas, medio + 1, fin, empleadosIds, asignaciones);
    }

    // Obtener empleado con menor carga de horas
    private static int empleadoConMenorCarga(List<Integer> empleadosIds, HashMap<Integer, List<Tarea>> asignaciones) {
        int mejor = empleadosIds.get(0);
        int minHoras = horasAsignadasAEmpleado(mejor, asignaciones);
        for (int id : empleadosIds) {
            int horas = horasAsignadasAEmpleado(id, asignaciones);
            if (horas < minHoras) {
                minHoras = horas;
                mejor = id;
            }
        }
        return mejor;
}

    // Sumar horas asignadas a un empleado
    private static int horasAsignadasAEmpleado(int id, HashMap<Integer, List<Tarea>> asignaciones) {
    List<Tarea> l = asignaciones.get(id);
    if (l == null || l.isEmpty()) return 0;
    int sum = 0;
    for (Tarea t : l) sum += t.getHoras();
    return sum;
}

    private static void obtenerIdsEmpleados(NodoArbol nodo, List<Integer> ids) {
    if (nodo == null) return;
    ids.add(nodo.valor);
    obtenerIdsEmpleados(nodo.hijoizquierdo, ids);
    obtenerIdsEmpleados(nodo.hijoderecho, ids);
}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Listas
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Producto> carrito = new ArrayList<>();
        Stack<Producto> historial = new Stack<>();
        Stack<Producto> entregados = new Stack<>();
        Stack<Producto> noEntregados = new Stack<>();
        PriorityQueue tareas = new PriorityQueue();
        ArbolBinario empleados = new ArbolBinario();
        HashMap<Tarea, String> tareasEmpleados = new HashMap<>();

        // Datos
        Producto camisa = new Producto("Camisa", 45);
        Producto pantalon = new Producto("Pantalon", 60);
        Producto zapatos = new Producto("Zapatos", 100);
        Producto collar = new Producto("Collar", 45);
        Producto lentes = new Producto("Lentes de sol", 55);
        Producto reloj = new Producto("Reloj", 100);
        Producto bolsa = new Producto("Bolsa Roja", 70);

        // Agregar productos a lista productos
        productos.add(camisa);
        productos.add(pantalon);
        productos.add(zapatos);
        productos.add(collar);
        productos.add(lentes);

        // Agregar productos a carrito
        carrito.add(reloj);
        carrito.add(bolsa);
        carrito.add(lentes);

        // Empleados
        int numEmpleado = 3;
        empleados.agregarNodo(1, "Isabel"); // Jefe
        empleados.agregarNodo(201, "Dafne"); // Jefe departamento
        empleados.agregarNodo(202, "Valentin");
        empleados.agregarNodo(301, "Jocelyn"); // Jefe departamento
        empleados.agregarNodo(302, "David");

        Tarea t1 = new Tarea("Empaquetar pedido 1", 2, "Prioridad alta", 1);
        Tarea t2 = new Tarea("Empaquetar pedido 2", 3, "Prioridad media", 2);
        Tarea t3 = new Tarea("Empaquetar pedido 3", 4, "Prioridad baja", 4);
        Tarea t4 = new Tarea("Empaquetar pedido 4", 2, "Prioridad alta", 4);
        tareas.enqueue(t1, t1.getPrioridad());
        tareas.enqueue(t2, t2.getPrioridad());
        tareas.enqueue(t3, t3.getPrioridad());
        tareas.enqueue(t4, t4.getPrioridad());


        // Agregar tareas asignadas
        tareasEmpleados.put(t1, "David");
        
        try{
            
            System.out.println("1-. Usuario \n2-. Administrador \n3-. Salir");
            System.out.print(">>> ");
            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion < 1 || opcion > 5) {
                throw new IllegalArgumentException("Opción fuera de rango (1-5)");
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
                                
                                for(Producto producto: productos) {
                                    System.out.println("\n - " + producto.nombre + " $" + producto.precio);
                                }

                                System.out.println("\nQue desea hacer ? \n1-. Comprar producto 2-. Buscar producto 3-. Agregar a carrito 4-. Regresar");
                                System.out.print(">>> ");
                                opcion = sc.nextInt();
                                sc.nextLine();

                                switch(opcion) {
                                    case 1: // Comprar producto
                                        System.out.println("\nIngrese el nombre del producto deseado");
                                        System.out.print(">>> ");
                                        String nombre = sc.next();
                                        Producto compra = buscarPorNombre(nombre, productos);
                                        
                                        // Algoritmo Busqueda en uso
                                        if (compra != null) {

                                            System.out.println("\nProducto pedido: " + compra.nombre);
                                            historial.add(compra);
                                            noEntregados.add(compra);

                                            System.out.println("\nQue tipo de entrega desea?: \n1-. Premiun \n2-. Regular \n(Si elige premium se le dara más prioridad a su pedido al enviar)");
                                            System.out.print(">>> ");
                                            int entrega = sc.nextInt();
                                            
                                            if(entrega == 1) {
                                                System.out.println("Su pedido tomara prioridad sobre otros.");
                                                Tarea nuevo = new Tarea(nombre, 2, compra.nombre, 2);
                                                tareas.enqueue(nuevo, nuevo.prioridad);

                                            } else {
                                                System.out.println("Entendido");
                                                Tarea nuevo = new Tarea(nombre, 3, compra.nombre, 4);
                                                tareas.enqueue(nuevo, nuevo.prioridad);
                                            }
                                            System.out.println("\n   Gracias por su compra!");

                                        }else{
                                            System.out.println("\nProducto no encontrado, intente de nuevo");
                                        }                                        
                                        break;
                                    
                                    case 2: // Buscar producto
                                        System.out.println("\nIngrese el nombre del producto que desea buscar");
                                        System.out.print(">>> ");
                                        String producto = sc.next();

                                        Producto buscar = buscarPorNombre(producto, productos);

                                        if (buscar != null) {
                                            System.out.println("Si esta este producto: " + buscar.nombre);
                                        }else{
                                            System.out.println("El producto no se encontro: " + buscar.nombre);
                                        }
                                        break;

                                    case 3: // Agregar al carrito
                                        System.out.println("\nIngrese el nombre del producto deseado");
                                        System.out.print(">>> ");
                                        String nombreCar = sc.next();
                                        Producto compraCar = buscarPorNombre(nombreCar, productos);
                                        
                                        // Algoritmo Busqueda en uso
                                        if (compraCar != null) {

                                            System.out.println("\n   Producto agregado al carrito!: " + compraCar.nombre);
                                            carrito.add(compraCar);

                                        }else{
                                            System.out.println("\nProducto no encontrado, intente de nuevo");
                                        }                         

                                        break;
                                    case 4: // Regresar
                                        break;
                                }

                                break;

                            case 2: // Carrito
                                System.out.println("\n CARRITO");
                                for(Producto producto : carrito){
                                    System.out.println("\n - " + producto.nombre + " $" + producto.precio);
                                }

                                boolean repetirCar = true;
                                while(repetirCar) {

                                    System.out.println("\nDesea comprar o borrar algo de su carrito? \n1-. Comprar 2-. Borrar 3-. Salir");
                                    System.out.print(">>> ");
                                    opcion = sc.nextInt();
                                    sc.nextLine();

                                    switch(opcion) {
                                        
                                        case 1: // Comprar del carrito
                                            System.out.println("\nIngrese el nombre del producto");
                                            System.out.print(">>> ");
                                            String nombre = sc.next();
                                            Producto compra = buscarPorNombre(nombre, carrito);
                                            
                                            // Algoritmo Busqueda en uso
                                            if (compra != null) {

                                                System.out.println("\nProducto pedido: " + compra.nombre);
                                                historial.add(compra);
                                                noEntregados.add(compra);

                                                System.out.println("\nQue tipo de entrega desea?: \n1-. Premiun \n2-. Regular \n(Si elige premium se le dara más prioridad a su pedido al enviar)");
                                                System.out.print(">>> ");
                                                int entrega = sc.nextInt();
                                                
                                                if(entrega == 1) {
                                                    System.out.println("Su pedido tomara prioridad sobre otros.");
                                                    Tarea nuevo = new Tarea(nombre, 2, compra.nombre, 2);
                                                    tareas.enqueue(nuevo, nuevo.prioridad);

                                                } else {
                                                    System.out.println("Entendido");
                                                    Tarea nuevo = new Tarea(nombre, 3, compra.nombre, 4);
                                                    tareas.enqueue(nuevo, nuevo.prioridad);
                                                }
                                                System.out.println("\n   Gracias por su compra!");

                                            }else{
                                                System.out.println("\nProducto no encontrado, intente de nuevo");
                                            }                                        
                                            break;
                                        
                                        
                                        case 2: // Borrar del carrito
                                            System.out.println("\nIngrese el nombre del producto");
                                            System.out.print(">>> ");
                                            String nombreBor = sc.next();
                                            Producto compraBor = buscarPorNombre(nombreBor, carrito);

                                            if(compraBor != null) {
                                                System.out.println("\n Producto '" + compraBor.nombre + "' borrado...");
                                                carrito.remove(compraBor);
                                            }else{
                                                System.out.println("Producto no encontrado, intente de nuevo...");
                                            }
                                            break;
                                                     
                                        case 3: // Regresar
                                            repetirCar = false;
                                            break;
                                    }
                                }
                                break;
                            case 3: // Historial de compras
                                System.out.println("\n HISTORIAL DE COMPRAS: ");
                                System.out.println("\n Ultimo producto comprado: ");
                                System.out.println(historial.firstElement().nombre);

                                System.out.println("\n Que desea ver? \n1-. Entregados 2-. No entregados 3-. Todos 4-. Regresar ");
                                System.out.print(">>> ");
                                int opcion2 = sc.nextInt();
                                sc.nextLine();

                                switch(opcion2) {
                                    case 1: // Productos Entregados
                                        System.err.println("\n Productos Entregados: ");
                                        for(Producto p : historial) {
                                            System.out.println(" - " + p.nombre + " $" + p.precio);
                                        }
                                        break;

                                    case 2: // Productos No Entregados
                                        System.err.println("\n Productos No Entregados: ");
                                        for(Producto p : historial) {
                                            System.out.println(" - " + p.nombre + " $" + p.precio);
                                        }
                                        break;

                                    case 3: // Historial Completo
                                        System.err.println("\n Historial completo: ");
                                        for(Producto p : historial) {
                                            System.out.println(" - " + p.nombre + " $" + p.precio);
                                        }
                                        break;

                                    case 4: // Regresar
                                        break;

                                }

                                

                                break;

                            case 4: // Salir
                                repetirUs = false;
                                break;

                        }
                    }
                    break;
                
                case 2: // Menú Administrador
                    System.out.println("\nBienvenido Administrador");    

                    boolean repetirAdmin = true;
                    while(repetirAdmin) {
                        System.out.println("\nQue quiere hacer? \n   1-. Ver Productos \n   2-. Ver Tareas \n   3-. Ver Empleados  \n   4-. Salir");
                        System.out.print(">>> ");
                        opcion = sc.nextInt();
                        sc.nextLine();
                        
                        switch(opcion) {
                         
                            case 1: // Ver Productos
                                System.out.println("\n PRODUCTOS: ");

                                for(Producto producto: productos) {
                                    System.out.println("\n - " + producto.nombre + " $" + producto.precio);
                                }

                                System.out.println("\n1-. Agregar producto   2-. Borrar producto   3-. Buscar producto   4-. Regresar");
                                System.out.print(">>> ");
                                opcion = sc.nextInt();
                                sc.nextLine();

                                switch(opcion) {
                                    
                                    case 1: // Agregar Producto
                                        System.out.print("\nIngrese un producto: ");
                                        String producto = sc.next();
                                        System.out.print("\nIngrese el precio: ");
                                        int precio = sc.nextInt();
                                        sc.nextLine();

                                        Producto nuevo = new Producto(producto, precio);
                                        productos.add(nuevo);
                                        System.out.println("Producto '" + nuevo.nombre + "' Agregado");
                                        break;
                                    
                                    case 2: // Borrar producto
                                        System.out.println("\nIngrese el nombre del producto");
                                        System.out.print(">>> ");
                                        String nombreBor = sc.next();
                                        Producto compraBor = buscarPorNombre(nombreBor, productos);

                                        if(compraBor != null) {
                                            System.out.println("\n Producto '" + compraBor.nombre + "' borrado...");
                                            productos.remove(compraBor);
                                        }else{
                                            System.out.println("Producto no encontrado, intente de nuevo...");
                                        }
                                        break;
                                
                                    case 3: // Buscar Producto
                                        System.out.println("\nIngrese el nombre del producto que desea buscar");
                                        System.out.print(">>> ");
                                        String producto2 = sc.next();

                                        Producto buscar = buscarPorNombre(producto2, productos);

                                        if (buscar != null) {
                                            System.out.println("Si esta este producto: " + buscar.nombre);
                                        }else{
                                            System.out.println("El producto no se encontro: " + buscar.nombre);
                                        }
                                        break;

                                    case 4: // Regresar
                                    break;
                                }
                                break;
                            
                            case 2: // Ver Tareas ---
                                System.out.println("\n TAREAS: ");

                                tareas.mostrar();

                                System.out.println("\n1-. Agregar tarea   2-. Completar proxima tarea   3-. Distribuir tareas   4-. Ver horas de trabajo faltantes   5-. Regresar");
                                System.out.print(">>> ");
                                opcion = sc.nextInt();
                                sc.nextLine();

                                switch(opcion) {
                                    
                                    case 1: // Agregar tarea
                                        System.out.print("\nIngrese el nombre de la tarea: ");
                                        String nombre = sc.next();
                                        System.out.print("\nIngrese la prioridad de la tarea: ");
                                        int prioridad = sc.nextInt();
                                        System.out.print("\nIngrese una descripcion: ");
                                        String desc = sc.next();
                                        sc.nextLine();
                                        System.out.print("\nIngrese las horas estimadas: ");
                                        int horas = sc.nextInt();
                                        sc.nextLine();

                                        Tarea nuevo = new Tarea(nombre, prioridad, desc, horas);
                                        tareas.enqueue(nuevo, prioridad);
                                        System.out.println("Tarea '" + nuevo.getNombre() + "' Agregada");
                                        break;
                                    
                                    case 2: // Completar tarea
                                        Tarea completada = tareas.dequeue();
                                        if (completada != null) 
                                            System.out.println("Tarea '" + completada.getNombre() + "' completada");
                                        break;

                                    case 3: // Distribuir tareas
                                        // Crear lista de IDs de empleados
                                        List<Integer> empleadosIds = new ArrayList<>();
                                        obtenerIdsEmpleados(empleados.raiz, empleadosIds); // Método helper recursivo
                                        // Crear HashMap de asignaciones
                                        HashMap<Integer, List<Tarea>> asignaciones = new HashMap<>();
                                        // Convertir PriorityQueue a lista temporal
                                        List<Tarea> listaTareas = new ArrayList<>();
                                        while (tareas.peek() != null) listaTareas.add(tareas.dequeue());
                                        // Distribuir
                                        distribuirTareasRec(listaTareas, empleadosIds, asignaciones);

                                        // Mostrar resultados
                                        System.out.println("\nTareas distribuidas:");
                                        for (int id : asignaciones.keySet()) {
                                            System.out.println("Empleado ID " + id + ":");
                                            for (Tarea t : asignaciones.get(id)) {
                                                System.out.println("  - " + t.getNombre() + " (" + t.getHoras() + " hrs)");
                                            }
                                        }
                                        break;

                                    case 4: // Horas faltantes de trabajo
                                        List<Tarea> lista = new ArrayList<>();
                                        while (tareas.peek() != null) lista.add(tareas.dequeue());
                                        int totalHoras = sumarHorasRecursivo(lista, 0, lista.size() - 1);
                                        System.out.println("\nTotal de horas pendientes de trabajo: " + totalHoras);
                                        break;
                                                                
                                    case 5: // Regresar
                                        break;
                                }
                                break;
                            
                            case 3: // Ver empleados
                                System.out.println("\n EMPLEADOS: ");

                                empleados.mostrar(empleados.raiz, " ");
                                

                                System.out.println("\n1-. Agregar empleado  2-. Buscar empleado por ID   3-. Regresar");
                                System.out.print(">>> ");
                                opcion = sc.nextInt();
                                sc.nextLine();

                                switch (opcion) {
                                    case 1: // Agregar
                                        numEmpleado ++;
                                        System.out.println("Ingrese el Nombre y el ID del empleado: ");
                                        
                                        System.out.print(">>> ID: ");
                                        int ID = sc.nextInt();
                                        sc.nextLine();
                                        System.out.print(">>> Nombre: ");
                                        String nombre = sc.nextLine();

                                        empleados.agregarNodo(ID, nombre);

                                        break;
                                        
                                    case 2: // Buscar
                                        System.out.println("\nIngrese el ID(año, mes, número actual de empleado: " + numEmpleado + ") del empleado que quiere buscar: ");
                                        System.out.print(">>> ID: ");
                                        int id = sc.nextInt();
                                        sc.nextLine();

                                        NodoArbol encontrado = empleados.buscar(empleados.raiz, id);
                                        if(encontrado != null){
                                            System.out.println("\nEmpleado encontrado: \n ID: " + encontrado.valor + "\n Nombre: " + encontrado.nombre);
                                        }else{
                                            System.out.println("\nNo hay un empleado con ese ID");
                                        }

                                        break;

                                    case 3: // Regresar
                                        break;
                                }
                                
                            case 4: // Salir
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