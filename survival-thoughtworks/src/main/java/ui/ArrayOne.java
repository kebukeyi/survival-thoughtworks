package ui;

public class ArrayOne {

    //初始化数组
   public static int[][] nextMatrix = new int[20][72];

   // 中间待变
   public   static int[][] middleMatrix = new int[20][72];


    //变换后的数组
    public   static int[][] afterMatrix = new int[20][72];

    /*
       初始化为 0
     */
    public int[][] init() {
//        int row=one.length;//行数
//        int col=one[0].length;//列数
        int[][] one = new int[100][100];
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 51; j++) {
                one[i][j] = 0;
            }
        }

        one[0][1] = 1;
        one[0][2] = 1;
//        one[1][1] = 1;
//        one[1][2] = 1;
//        one[2][1]=1;

        nextMatrix = one;

        for (int g = 0; g <= 2; g++) {
            afterMatrix = ONE(nextMatrix);
            nextMatrix = afterMatrix;
            System.out.println("---------------------------------------------------------------------------------------");

            for (int x = 0; x < 19; x++) {
                for (int y = 0; y < 51; y++) {
                    System.out.print(afterMatrix[x][y]);
                }
                System.out.println();
            }
        }
        return one;
    }

    /*
      处理数组  返回新数组
     */
    public  int[][] ONE(int[][] one) {
        System.out.println("调用监听nextMatrix[][]数组");
        int sum = 0;
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 51; j++) {
                afterMatrix[i][j] = 0;
            }
        }
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 51; j++) {
                sum = comput(one, i, j);
                if (one[i][j] == 1) {//存活状态
                    if (sum == 2 || sum == 3) {
                        afterMatrix[i][j] = one[i][j];//不变
                    } else if (sum < 2 || sum > 3) {
                        afterMatrix[i][j] = 0;
                    }
                } else if (one[i][j] == 0) {//死亡状态
                    if (sum == 3) {
                        afterMatrix[i][j] = 1;
                    }
                }
            }
        }
        return afterMatrix;
    }


//    public  int comput(int[][] one, int i, int j) {
//        int sum = 0;
//        if (i == 0 && j == 0) {//[0,0]
//            sum = one[i][j + 1] + one[i + 1][j] + one[i + 1][j + 1];
//        } else if (i == 0 && j == 99) {//[0,99]
//            sum = one[i][j - 1] + one[i + 1][j - 1] + one[i + 1][j];
//
//        } else if (i == 99 && j == 0) {//[99,0]
//            sum = one[i - 1][j] + one[i - 1][j + 1] + one[i][j + 1];
//
//        } else if (i == 99 && j == 99) {//[99,99]
//            sum = one[i][j - 1] + one[i - 1][j - 1] + one[i - 1][j];
//        } else if (i == 0 && j != 0) {//第一行
//            sum = one[i][j - 1] + one[i][j + 1] + one[i + 1][j - 1] + one[i + 1][j] + one[i + 1][j + 1];
//
//        } else if (j == 0 && i != 0) {//第一列
//            sum = one[i - 1][j] + one[i - 1][j + 1] + one[i][j + 1] + one[i + 1][j] + one[i + 1][j + 1];
//
//        } else if (i == 99 && j != 0) {//最后一行
//            sum = one[i - 1][j - 1] + one[i - 1][j] + one[i - 1][j + 1] + one[i][j - 1] + one[i][j + 1];
//        } else if (j == 99 && i != 0) {//最后一列
//            sum = one[i - 1][j - 1] + one[i - 1][j] + one[i][j - 1] + one[i + 1][j - 1] + one[i + 1][j];
//        } else if (i != 0 && j != 0) {//中间部分
//            sum = one[i - 1][j - 1] + one[i - 1][j] + one[i - 1][j + 1] + one[i][j - 1] + one[i][j + 1] + one[i + 1][j - 1] + one[i + 1][j] + one[i + 1][j + 1];
//        }
//        return sum;
//    }

    public int comput(int[][] nextMatrix, int i, int j) {
        int num = 0;
        int row = nextMatrix.length;
        int col = nextMatrix[0].length;

        if(i!=0){
            num+=nextMatrix[i][j-1];
        }

        //左上角
        if(i!=0&&j!=0){
            num+=nextMatrix[j-1][i-1];
        }
        //上边
        if(j!=0){
            num+=nextMatrix[j-1][i];
        }
        //右上
        if(i!=col-1&&j!=0){
            num+=nextMatrix[j-1][i+1];
        }
        //右边
        if(i!=col-1){
            num+=nextMatrix[j][i+1];
        }
        //右下
        if(i!=col-1&&j!=row-1){
            num+=nextMatrix[j+1][i+1];
        }
        //下边
        if(j!=row-1){
            num+=nextMatrix[j+1][i];
        }
        //左下
        if(i!=0&&j!=row-1){
            num+=nextMatrix[j+1][i-1];
        }

        return num;
    }


    public static  void main(String [] agrs){
        ui.ArrayOne one=new ui.ArrayOne();
        one.init();

    }


}
