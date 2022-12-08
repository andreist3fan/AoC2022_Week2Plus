import java.io.*;
import java.util.*;

public class Day8 {
    /**
     *  O(n^3) (kind of)
     * @return .
     * @throws FileNotFoundException .
     */
    public static long solve1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input8.txt"));
        int[][] hgt = new int[100][100];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        //0 up, 1 right, 2 down, 3 left

        // scrapped (bad) idea: boolean matrix to show whether
        // you can move up/down/.. in that direction
        int n = 0;
        int m = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            m = line.length();
            for (int i = 0; i < m; i++) {
                hgt[n][i] = (int) line.charAt(i) - '0';
            }
            n++;
        }
        int res = 2 * (n + m - 2);
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                boolean ok = false;
                for (int k = 0; k < 4; k++) {
                    if (can_you(hgt, n, m, dx[k], dy[k], i, j)) {
                        ok = true;
                        break;
                    }
                }
                if (ok)
                    res++;
            }
        }
        return res;
    }

    /**
     * can_you get out of the "forest" in that direction?
     * @param mat the matrix
     * @param n height
     * @param m length
     * @param dx direction on x-axis
     * @param dy direction on y-axis
     * @param i current row index
     * @param j current column index
     * @return true/false
     */
    public static boolean can_you(int[][] mat, int n, int m, int dx, int dy, int i, int j) {
        int initial = mat[i][j];
        while (i + dx >= 0 && i + dx < n && j + dy >= 0 && j + dy < m &&
            initial > mat[i + dx][j + dy]) {
            i += dx;
            j += dy;
        }
        return !(i + dx >= 0 && i + dx < n && j + dy >= 0 && j + dy < m);
    }

    /**
     * Still O(n^3), but reused most code
     * @return .
     * @throws FileNotFoundException .
     */
    public static long solve2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input8.txt"));
        int[][] hgt = new int[100][100];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        //0 up, 1 right, 2 down, 3 left
        int n = 0;
        int m = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            m = line.length();
            for (int i = 0; i < m; i++) {
                hgt[n][i] = (int) line.charAt(i) - '0';
            }
            n++;
        }
        int res = -1;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                int ss = 1;
                for (int k = 0; k < 4; k++) {
                    ss *= scenic(hgt, n, m, dx[k], dy[k], i, j);
                }
                if (res < ss)
                    res = ss;
            }
        }
        return res;
    }
    /**
     * Gets a position's scenic number in a direction (U/D/L/R)
     * @param mat the matrix
     * @param n height
     * @param m length
     * @param dx direction on x-axis
     * @param dy direction on y-axis
     * @param i current row index
     * @param j current column index
     * @return the scenic number in that direction
     */
    public static int scenic(int[][] mat, int n, int m, int dx, int dy, int i, int j) {
        int initial = mat[i][j];
        int score = 1;
        while (i + dx > 0 && i + dx < n - 1 && j + dy > 0 && j + dy < m - 1 &&
            initial > mat[i + dx][j + dy]) {
            i += dx;
            j += dy;
            score++;
        }
        return score;
    }
}
