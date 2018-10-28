package com.maze;

import java.util.Scanner;

public class MazePath {

    static int[][] solution;
    static int maxRows;
    static int maxColumns;
    static char[][] inputArray;
    static int[][] memory;

    public static void main(String[] args) {
        System.out.println("Enter rows,columns and maze");
        Scanner sc = new Scanner(System.in);
        maxRows = sc.nextInt();
        maxColumns = sc.nextInt();
        inputArray = new char[maxRows][maxColumns];
        int i=0;
        sc.nextLine();
        while(i<maxRows){

            String s = sc.nextLine();
            inputArray[i] = s.toCharArray();
            i++;
        }
        solution = new int[maxRows][maxColumns];
        memory = new int[maxRows][maxColumns];
        int start = getStart();
        boolean result = getResult(start, 0);

        if(result){
            printSolutionArray();
        }
    }

    private static int getStart() {
        int i=0;
        while(i<inputArray.length){
            if(inputArray[i][0]=='S'){
                return i;
            }
            i++;
        }
        return -1;
    }

    private static boolean getResult(int i, int j) {
        if (i < 0 || j < 0 || i >= maxRows || j >= maxColumns){
            return false;
        }
        if(memory[i][j]==1){
            return false;
        }
        memory[i][j]=1;
        if(inputArray[i][j] == '#') {
            return false;
        }
        if (inputArray[i][j] == 'F') {
            solution[i][j]=1;
            return true;
        }

        solution[i][j] = 1;

        boolean r1 = getResult(i + 1, j);
        boolean r2 = getResult(i, j + 1);
        boolean r3 = getResult(i - 1, j);
        boolean r4 = getResult( i, j - 1);
        if (r1 || r2 || r3 || r4) {
            return true;
        } else {
            solution[i][j] = 0;
            return false;
        }

    }

    private static void printSolutionArray() {
        for(int i=0;i<solution.length;i++){
            for(int j=0;j<solution[0].length;j++){
                System.out.print(solution[i][j]+" ");
            }
            System.out.println();
        }
    }
}