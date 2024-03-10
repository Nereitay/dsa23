package es.kiwi.algorithm.recursion_multi;

public class E03PascualTriangle {

    private static int element(int i, int j) {
        if (j == 0 || i == j) return 1;

        return element(i  - 1, j - 1) + element(i - 1, j);
    }

    /**
     * 打印空格
     * @param n
     * @param i
     */
    private static void printSpace(int n, int i) {
        int num = (n - 1 - i) * 2;
        for (int i1 = 0; i1 < num; i1++) {
            System.out.print(" ");
        }
    }

    /**
     * 打印三角形
     * @param n 三角形的高度
     */
    public static void print(int n) {
        for (int i = 0; i < n ; i++) {// 行
            printSpace(n, i);
            for (int j = 0; j <= i; j++) { // 列
                System.out.printf("%-4d",element(i, j));
            }
            System.out.println();
        }
    }

    private static int element1(int[][] triangle, int i, int j) {

        if (triangle[i][j] > 0) return triangle[i][j];

        if (j == 0 || i == j) {
            triangle[i][j] = 1;
            return 1;
        }

        triangle[i][j] = element1(triangle, i  - 1, j - 1) + element1(triangle, i - 1, j);
        return triangle[i][j];
    }

    public static void print1(int n) {
        int[][] triangle = new int[n][]; // 列不确定
        for (int i = 0; i < n ; i++) {
            triangle[i] = new int[i + 1];
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d",element1(triangle, i, j));
            }
            System.out.println();
        }
    }

    private static void createRow(int[] row, int i) {// 动态规划优化
        if (i == 0) {
            row[0] = 1;
            return;
        }

        for (int j = i; j > 0; j--) {
            row[j] = row[j] + row[j - 1];
        }
    }

    public static void print2(int n) {
        int[] row = new int[n];
        for (int i = 0; i < n ; i++) {
            createRow(row, i);
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d",row[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        System.out.println(element(4, 2));

//        print(10);

//        print1(10);

        print2(10);
    }
}
