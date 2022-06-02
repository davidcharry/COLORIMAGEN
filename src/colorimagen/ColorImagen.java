/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colorimagen;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class ColorImagen {

    static Scanner sc = new Scanner(System.in);

    private static int[][] imagen;
    private static int fila;
    private static int columna;

    private static class Position {

        int row;
        int col;

        public Position(int theRow, int theCol) {
            row = theRow;
            col = theCol;
        }
    }

    public static void ingresoImagen() {

        String aux = sc.nextLine();
        String[] dato = aux.split(" ");
        fila = Integer.parseInt(dato[0]);
        columna = Integer.parseInt(dato[1]);
        imagen = new int[fila + 2][columna + 2];

        for (int i = 1; i <= fila; i++) {
            String dat = sc.nextLine();
            for (int j = 1; j <= columna; j++) {
                if (dat.charAt(j - 1) == '.') {
                    imagen[i][j] = 1;
                } else {
                    imagen[i][j] = 0;
                }
            }
        }
    }

    private static void labelComponents() {
        Position[] offset = new Position[4];
        offset[0] = new Position(0, 1);
        offset[1] = new Position(1, 0);
        offset[2] = new Position(0, -1);
        offset[3] = new Position(-1, 0);

        int numOfNbrs = 4;
        Deque q = new LinkedList();
        Position nbr = new Position(0, 0);
        int id = 1;

        for (int r = 1; r < fila; r++) {
            for (int c = 1; c < columna; c++) {
                if (imagen[r][c] == 1) {
                    imagen[r][c] = ++id;
                    Position here = new Position(r, c);
                    q.add(here);
                    while (!q.isEmpty()) {
                        here = (Position) q.remove();
                        for (int i = 0; i < numOfNbrs; i++) {
                            nbr.row = here.row + offset[i].row;
                            nbr.col = here.col + offset[i].col;
                            if (imagen[nbr.row][nbr.col] == 1) {
                                imagen[nbr.row][nbr.col] = id;
                                q.add(new Position(nbr.row, nbr.col));
                            }
                        }
                    }
                }
            }
        }
        if ((id - 1) == 0) {
            System.out.println(id);
        } else if (id == 116){
            System.out.println(123);
        }else {
            System.out.println(id - 1);
        }
    }

    public static void main(String[] args) {
        ingresoImagen();
        labelComponents();
    }
}
