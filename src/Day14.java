import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day14 {
    /**
     * Easy day today, not too much to think
     *
     * @return res
     * @throws FileNotFoundException fnf
     */
    public static long solve1() throws FileNotFoundException {
        return solve_h(false);
    }

    /**
     * Was too focused so I made both in one method
     *
     * @param makeLine if you need to make a line to catch the sand
     * @return #of grains of sand
     * @throws FileNotFoundException fnf
     */
    public static long solve_h(boolean makeLine) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input14.txt"));
        boolean[][] mat = new boolean[800][800];
        int maxk = -1;
        while (sc.hasNextLine()) {
            String[] coords = sc.nextLine().split(",| -> ");
            for (int i = 2; i < coords.length; i += 2) {
                int fj = min(Integer.parseInt(coords[i]), Integer.parseInt(coords[i - 2])),
                    tj = max(Integer.parseInt(coords[i]), Integer.parseInt(coords[i - 2])),
                    fk = min(Integer.parseInt(coords[i + 1]), Integer.parseInt(coords[i - 1])),
                    tk = max(Integer.parseInt(coords[i + 1]), Integer.parseInt(coords[i - 1]));
                maxk = max(maxk, tk);
                for (int j = fj; j <= tj; j++)
                    for (int k = fk; k <= tk; k++)
                        mat[k][j] = true;
            }
        }
        //System.out.println(maxk);
        if (makeLine)
            for (int i = 0; i < 800; i++)
                mat[maxk + 2][i] = true;
        /*for(int i=0;i<40;i++)
        {
            for(int j=94;j<106;j++)
                if(mat[i][j])
                    System.out.print('#');
                else
                    System.out.print(' ');
            System.out.println();
        }*/

        int ct = 0;
        while (fall(mat, 0, 500) != 0)
            ct++;

        return ct;
    }

    public static int fall(boolean[][] mat, int x, int y) {
        if (x > 798 || y > 798 || y < 1 || mat[0][500])
            return 0;
        if (!mat[x + 1][y])
            return fall(mat, x + 1, y);
        if (!mat[x + 1][y - 1])
            return fall(mat, x + 1, y - 1);
        if (!mat[x + 1][y + 1])
            return fall(mat, x + 1, y + 1);
        //System.out.println(x+" "+y);
        mat[x][y] = true;
        return 1;
    }

    /**
     * just make a line lol
     *
     * @return ans
     * @throws FileNotFoundException fnf
     */
    public static long solve2() throws FileNotFoundException {
        return solve_h(true);
    }
}
