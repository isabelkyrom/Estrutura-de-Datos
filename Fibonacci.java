import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
    public static int fibonacci(int n) {
        
        // Casos base
        if(n == 0 ) {
            return 0;
        
        }else if(n == 1) {
            return 1;

        }else {
            
            int num = fibonacci(n-1) + fibonacci(n-2);

            return num;
        }
    }
    
    public static List<Integer> SubsetSum(int[] conjunto, int objetivo, int i) {
        
        if (objetivo == 0) {
            
            return new ArrayList<>(); 
            
        }else if(i == conjunto.length){
            return null;
        }else {

            // Intenta incluir el número actual
            List<Integer> incluido = SubsetSum(conjunto, objetivo - conjunto[i], i + 1);
            if (incluido != null) {
                incluido.add(conjunto[i]);
                return incluido;
            }

            // Intenta excluir el número
            return SubsetSum(conjunto, objetivo, i + 1);
        }
        
    }
    
    
    public static int[] vacio (int[][] array) {
        for(int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1,-1};
    }
    public static boolean seguro(int[][] array, int fila, int col, int num) {
        // Checa la fila
        for(int i = 0; i< 9; i++) {
            if(array[fila][i] == num) {
                return false;
            }
        }
        // Checa columna
        for(int i = 0; i< 9; i++) {
            if(array[i][col] == num) {
                return false;
            }
        }

        // Checa si esta en el recuadro de 3x3
        int startRow = fila - (fila % 3), startCol = col - (col % 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
            
        return true;
    }    
    public static boolean sudoku(int[][] sudoku) {
        // Buscar lugar vacio
        int[] coor = vacio(sudoku);  // coordenadas del lugar vacio
        if(coor[0] == -1) 
            return true;
        ;
        int fila = coor[0];
        int col = coor[1];

        for(int num = 1; num<=9; num++) {
            // Cambia el numero si se cumplen las condiciones
            if (seguro(sudoku, fila, col, num)) {
                sudoku[fila][col] = num;

                if (sudoku(sudoku)) {
                    return true; // solucionado
                }

                // retroceso
                sudoku[fila][col] = 0;
            }
        }

        return false;
    }
    
    public static void main(String[] args) {
        
        // Fibonnaci
        System.out.println("Fibonacci de 5: " + fibonacci(5));

        // Subconjunto de conjunto
        int[] o = {2,6,7,3,4};
        System.out.println("Subconjunto de 5: " + SubsetSum( o, 5, 0));

        // Sudoku
        int[][] cuadro = {
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        sudoku(cuadro);

        for (int i = 0; i < cuadro.length; i++) {
            for (int j = 0; j < cuadro[i].length; j++) {
                System.out.print(cuadro[i][j] + " ");
            }
            System.out.println();
        }

    }

}