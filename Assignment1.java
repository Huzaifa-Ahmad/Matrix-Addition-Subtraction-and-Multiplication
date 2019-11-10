// Name: Huzaifa Ahmad
// Student Number: 251025254
// Date: October 20th, 2019

import java.io.*;

public class Assignment1 {

  public int[][] denseMatrixMult(int[][] A, int[][] B, int size) {
    int[][] C = new int[size][size];

        int[][] D = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                D[i][j] = 0;
            }
        }

        if (size == 1) {
            C[0][0] = A[0][0] * B[0][0];
        }
        else {
            int[][] A00 = sum(A, D, 0, 0, 0, 0, size / 2);
            int[][] A11 = sum(A, D, size / 2, size / 2, size / 2, size / 2, size / 2);
            int[][] B00 = sum(B, D, 0, 0, 0, 0, size / 2);
            int[][] B11 = sum(B, D, size / 2, size / 2, size / 2, size / 2, size / 2);



            int[][] M0 = denseMatrixMult(sum(A, A, 0, 0, size / 2, size / 2, size / 2), sum(B, B, 0, 0, size / 2, size / 2, size / 2), size / 2);
            int[][] M1 = denseMatrixMult(sum(A, A, size / 2, 0, size / 2, size / 2, size / 2), B00, size / 2);
            int[][] M2 = denseMatrixMult(A00, sub(B, B, 0, size / 2, size / 2, size / 2, size / 2), size / 2);
            int[][] M3 = denseMatrixMult(A11, sub(B, B, size / 2, 0, 0, 0, size / 2), size / 2);
            int[][] M4 = denseMatrixMult(sum(A, A, 0, 0, 0, size / 2, size / 2), B11, size / 2);
            int[][] M5 = denseMatrixMult(sub(A, A, size / 2, 0, 0, 0, size / 2), sum(B, B, 0, 0, 0, size / 2, size / 2), size / 2);
            int[][] M6 = denseMatrixMult(sub(A, A, 0, size / 2, size / 2, size / 2, size / 2), sum(B, B, size / 2, 0, size / 2, size / 2, size / 2), size / 2);

            int[][] C00 = new int[size / 2][size / 2];
            int[][] C01 = new int[size / 2][size / 2];
            int[][] C10 = new int[size / 2][size / 2];
            int[][] C11 = new int[size / 2][size / 2];


            for (int i = 0; i < size / 2; i++) {
                for (int j = 0; j < size / 2; j++) {
                    C00[i][j] = M0[i][j] + M3[i][j] - M4[i][j] + M6[i][j];
                    C01[i][j] = M2[i][j] + M4[i][j];
                    C10[i][j] = M1[i][j] + M3[i][j];
                    C11[i][j] = M0[i][j] - M1[i][j] + M2[i][j] + M5[i][j];
                }
            }
            for (int i = 0; i < size / 2; i++) {
                for (int j = 0; j < size / 2; j++) {
                    C[i][j] = C00[i][j];
                    C[i][j + size / 2] = C01[i][j];
                    C[i + size / 2][j] = C10[i][j];
                    C[i + size / 2][j + size / 2] = C11[i][j];
                }
            }
        }

    return C;
  }

  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n) {
    int [][] C = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        C[i][j] = A[x1+i][y1+j] + B[x2+i][y2+j];
      }
    }
    return C;
  }

  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n) {
    int [][] C = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        C[i][j] = A[x1+i][y1+j] - B[x2+i][y2+j];
      }
    }
    return C;
  }

  public int[][] initMatrix(int n) {
    int[][] A = new int[n][n];

    return A;
  }

  public void printMatrix(int n, int[][] A) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(A[i][j] + " ");
      }
      System.out.println();
    }
  }

  public int[][] readMatrix(String filename, int n) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(filename));

    int[][] A = new int[n][n];

    for (int i = 0; i < n; i++) {
      String[] line = br.readLine().trim().split(" ");

      for (int j = 0; j < n; j++) {
        A[i][j] = Integer.parseInt(line[j]);
      }
    }
    br.close();
    return A;
  }
}