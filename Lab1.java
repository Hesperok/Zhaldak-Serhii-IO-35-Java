public class MatrixTask {

    public static void main(String[] args) {
        try {
            byte[][] matrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };

            byte[][] matrixB = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
            };

            if (matrixA.length != matrixB.length ||
                matrixA[0].length != matrixB[0].length) {
                throw new IllegalArgumentException(
                        "Матриці повинні мати однаковий розмір");
            }

            int rows = matrixA.length;
            int cols = matrixA[0].length;

            byte[][] matrixC = new byte[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrixC[i][j] = (byte) (matrixA[i][j] ^ matrixB[i][j]);
                }
            }

            System.out.println("Матриця C (A ⊕ B):");
            for (byte[] row : matrixC) {
                for (byte value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }

            int sum = 0;
            int count = 0;

            for (byte[] row : matrixC) {
                for (byte value : row) {
                    sum += value;
                    count++;
                }
            }

            double average = (double) sum / count;
            System.out.println("\nСереднє значення елементів матриці C: "
                    + average);

        } catch (ArithmeticException e) {
            System.out.println("Помилка обчислень: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Некоректні дані: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        }
    }
}
