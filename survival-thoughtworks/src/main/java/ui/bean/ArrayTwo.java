package ui.bean;

import ui.ArrayOne;

public class ArrayTwo {


    public  int[][] ONE(int[][] one) {
        System.out.println("调用监听nextMatrix[][]数组");
        int sum = 0;
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 51; j++) {
                System.out.print(one[i][j]);
                sum = comput(one, i, j);
                if (one[i][j] == 1) {//存活状态
                    if (sum == 2 || sum == 3) {
                        ArrayOne.afterMatrix[i][j] = one[i][j];//不变
                    } else if (sum < 2 || sum > 3) {
                        ArrayOne.afterMatrix[i][j] = 0;
                    }
                } else if (one[i][j] == 0) {//死亡状态
                    if (sum == 3) {
                        ArrayOne. afterMatrix[i][j] = 1;
                    }
                }
            }
            System.out.println();
        }
        return ArrayOne.afterMatrix;
    }


    public  int comput(int[][] one, int i, int j) {
        int sum = 0;
        if (i == 0 && j == 0) {//[0,0]
            sum = one[i][j + 1] + one[i + 1][j] + one[i + 1][j + 1];
        } else if (i == 0 && j == 99) {//[0,99]
            sum = one[i][j - 1] + one[i + 1][j - 1] + one[i + 1][j];

        } else if (i == 99 && j == 0) {//[99,0]
            sum = one[i - 1][j] + one[i - 1][j + 1] + one[i][j + 1];

        } else if (i == 99 && j == 99) {//[99,99]
            sum = one[i][j - 1] + one[i - 1][j - 1] + one[i - 1][j];
        } else if (i == 0 && j != 0) {//第一行
            sum = one[i][j - 1] + one[i][j + 1] + one[i + 1][j - 1] + one[i + 1][j] + one[i + 1][j + 1];

        } else if (j == 0 && i != 0) {//第一列
            sum = one[i - 1][j] + one[i - 1][j + 1] + one[i][j + 1] + one[i + 1][j] + one[i + 1][j + 1];

        } else if (i == 99 && j != 0) {//最后一行
            sum = one[i - 1][j - 1] + one[i - 1][j] + one[i - 1][j + 1] + one[i][j - 1] + one[i][j + 1];
        } else if (j == 99 && i != 0) {//最后一列
            sum = one[i - 1][j - 1] + one[i - 1][j] + one[i][j - 1] + one[i + 1][j - 1] + one[i + 1][j];
        } else if (i != 0 && j != 0) {//中间部分
            sum = one[i - 1][j - 1] + one[i - 1][j] + one[i - 1][j + 1] + one[i][j - 1] + one[i][j + 1] + one[i + 1][j - 1] + one[i + 1][j] + one[i + 1][j + 1];
        }
        return sum;
    }
}
