import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Day12 {

    /**
     * Day 12, time to try and remember Lee!
     *
     * @return strong answer
     * @throws FileNotFoundException if file not found
     */
    public static long solve1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input12.txt"));
        char[][] mat = new char[41][162];
        int[][] no = new int[41][162];
        LinkedList<Integer> x = new LinkedList<>();
        LinkedList<Integer> y = new LinkedList<>();
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        int n = 0, m = 0, endX = 0, endY = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            m = line.length();
            mat[n++] = line.toCharArray();
            for (int i = 0; i < m; i++) {
                if (mat[n - 1][i] == 'S') {
                    x.add(n - 1);
                    y.add(i);
                }
                if (mat[n - 1][i] == 'E') {
                    endX = n - 1;
                    endY = i;
                    mat[n - 1][i] = 'z';
                }
            }
        }
        no[x.get(0)][y.get(0)] = 1;
        while (!x.isEmpty()) {
            int crtX = x.removeFirst();
            int crtY = y.removeFirst();
            for (int i = 0; i < 4; i++) {
                if (crtX + dx[i] >= 0 && crtX + dx[i] < n && crtY + dy[i] >= 0
                    && crtY + dy[i] < m && no[crtX + dx[i]][crtY + dy[i]] == 0
                    && ((int) mat[crtX + dx[i]][crtY + dy[i]] - mat[crtX][crtY] <= 1 ||
                    mat[crtX][crtY] == 'S')) {
                    x.add(crtX + dx[i]);
                    y.add(crtY + dy[i]);
                    no[crtX + dx[i]][crtY + dy[i]] = no[crtX][crtY] + 1;
                }
            }
        }
        return no[endX][endY] - 1;
    }

    /**
     * I think I could've just added all positions to the queue and ran Lee with that
     * <p>oh well soz</p>
     *
     * @return strong answer
     * @throws FileNotFoundException if file not found
     */
    public static long solve2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input12.txt"));
        char[][] mat = new char[41][162];
        int[][] no;
        LinkedList<Integer> x = new LinkedList<>();
        LinkedList<Integer> y = new LinkedList<>();
        Stack<Integer> startX = new Stack<>();
        Stack<Integer> startY = new Stack<>();
        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        int n = 0, m = 0, endX = 0, endY = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            m = line.length();
            mat[n++] = line.toCharArray();
            for (int i = 0; i < m; i++) {
                if (mat[n - 1][i] == 'S' || mat[n - 1][i] == 'a') {
                    startX.push(n - 1);
                    startY.push(i);
                    mat[n - 1][i] = 'a';
                }
                if (mat[n - 1][i] == 'E') {
                    endX = n - 1;
                    endY = i;
                    mat[n - 1][i] = 'z';
                }
            }
        }
        int min = Integer.MAX_VALUE;
        while (!startX.isEmpty()) {
            int a = startX.pop(), b = startY.pop();
            //System.out.println(a+" "+b);
            no = new int[41][162];
            no[a][b] = 1;
            x.add(a);
            y.add(b);
            while (!x.isEmpty()) {
                int crtX = x.removeFirst();
                int crtY = y.removeFirst();
                for (int i = 0; i < 4; i++) {
                    if (crtX + dx[i] >= 0 && crtX + dx[i] < n && crtY + dy[i] >= 0
                        && crtY + dy[i] < m && no[crtX + dx[i]][crtY + dy[i]] == 0
                        && ((int) mat[crtX + dx[i]][crtY + dy[i]] - mat[crtX][crtY] <= 1)) {
                        x.add(crtX + dx[i]);
                        y.add(crtY + dy[i]);
                        no[crtX + dx[i]][crtY + dy[i]] = no[crtX][crtY] + 1;
                    }
                }
            }
            if (no[endX][endY] - 1 < min && no[endX][endY] != 0) {
                min = no[endX][endY] - 1;
                //System.out.println("Shortest path changed:"+ min);
            }
        }
        return min;
    }
}
