package com.wang;

import java.io.*;

public class AparseArrayDemo {

    public static void main(String[] args) {
        //创建一个二维数组
        int[][] chessArray1 = new int[11][11];

        // 1表示是黑子  2表示蓝子
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        chessArray1[4][5] = 2;

        // 遍历二维数组
        for (int [] row: chessArray1) {
            for (int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        // 获取原来数组中的非0的值，用来初始化稀疏数组
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //创建稀疏数组
        int[][] sparsearray = new int[sum + 1][3];
        // 初始化稀疏数组，第一行的第一列(保存原数组的行数)  第一行的第二列(保存原数组的列数)  第一行的第三列(保存原数组非0数的值)
        sparsearray[0][0] = 11;
        sparsearray[0][1] = 11;
        sparsearray[0][2] = sum;

        // 遍历原数组，将里面的非0数的值的 行 列 以及数值 保存到稀疏数组
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray1[i][j] != 0) {
                    count++;
                    sparsearray[count][0] = i;
                    sparsearray[count][1] = j;
                    sparsearray[count][2] = chessArray1[i][j];
                }
            }
        }

        System.out.println();
        System.out.println("得到的稀疏数组：");
        for (int i = 0; i < sparsearray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparsearray[i][0],sparsearray[i][1],sparsearray[i][2]);
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("aaa.txt");
            for (int i = 0; i < sparsearray.length; i++) {
                for (int j = 0; j < 3; j++) {
                    fileWriter.write(sparsearray[i][j] + "\t");
                }
                fileWriter.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
        System.out.println("恢复之后的二维数组");

        // 将稀疏数组转为二维数组
        // 初始化恢复的二维数组
        int[][] chessArr2 = new int[sparsearray[0][0]][sparsearray[0][1]];

        // 给二维数组进行赋值，取出稀疏数组里面的值
        for (int i = 1; i < sparsearray.length; i++) {
            chessArr2[sparsearray[i][0]][sparsearray[i][1]] = sparsearray[i][2];
        }

        // 恢复之后的二维数组
        for (int [] row: chessArr2) {
            for (int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        System.out.println("ending");
    }
}


