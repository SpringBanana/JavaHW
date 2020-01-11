/*
 * Prints sum of all numbers in a row and column from cmd arguments
 */

import java.util.Arrays;
import classes.Scanner;

public class ReverseSum {
    public static void main(String[] args) {
        
        int matrixSize = 10;
        int[][] matrix = new int[matrixSize][matrixSize];
        int[] rows_Sum = new int[1];
        int[] columns_Sum = new int[1];
        int iterator = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            Scanner in = new Scanner(sc.nextLine());
            if (rows_Sum.length <= iterator) {
                rows_Sum = Arrays.copyOf(rows_Sum, rows_Sum.length * 2);
                matrix = Arrays.copyOf(matrix, matrix.length * 2);
            }
            matrix[iterator] = Arrays.copyOf(rows_Sum, 1);
            int j = 1;
            while (in.hasNextInt()) {
                int numb = in.nextInt();
                if (columns_Sum.length <= j) {
                    columns_Sum = Arrays.copyOf(columns_Sum, columns_Sum.length * 2);
                }
                if (matrix[iterator].length <= j) {
                    matrix[iterator] = Arrays.copyOf(matrix[iterator], matrix[iterator].length * 2);
                }
                columns_Sum[j] += numb;
                rows_Sum[iterator] += numb;
                matrix[iterator][j] = numb;
                j++;
            }
            matrix[iterator][0] = j;
            iterator++;
            in.close();
        }
        sc.close();
        for (int k = 0; k < iterator; k++) {
            for (int j = 1; j < matrix[k][0]; j++) {
                int ans = columns_Sum[j] + rows_Sum[k] - matrix[k][j];
                System.out.print(ans + " ");
            }
            System.out.println();
        }
    }
}
