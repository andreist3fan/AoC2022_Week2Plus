import java.io.*;
import java.util.*;

public class Day9 {

    /**
     * The first part
     *
     * @return nice answer
     * @throws FileNotFoundException if file not found
     */
    public static long solve1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input9.txt"));
        int xT = 20, yT = 20, xH = 20, yH = 20;
        ArrayList<Position> tailPos = new ArrayList<>();
        tailPos.add(new Position(xT, yT));
        while (sc.hasNextLine()) {
            String[] temp = sc.nextLine().split(" ");
            int no_steps = Integer.parseInt(temp[1]);
            int dx = 0, dy = 0;
            switch (temp[0]) {
                case "L" -> dy = -1;
                case "R" -> dy = 1;
                case "U" -> dx = -1;
                case "D" -> dx = 1;
            }
            for (int i = 0; i < no_steps; i++) {
                if (Math.abs(xH + dx - xT) > 1 || Math.abs(yH + dy - yT) > 1) {
                    xT = xH;
                    yT = yH;
                    if (!tailPos.contains(new Position(xT, yT)))
                        tailPos.add(new Position(xT, yT));
                }
                yH += dy;
                xH += dx;
            }
        }
        char[][] mm = new char[30][250];
        for(int i=0;i<tailPos.size();i++)
        {
             mm[tailPos.get(i).getX()][tailPos.get(i).getY()]='█';
        }
        for(int i=0;i<mm.length;i++){
            for(int j=0;j<mm[i].length;j++)
                if(mm[i][j]!='█')
                    System.out.print(' ');
                else
                    System.out.print(mm[i][j]);
            System.out.println();
        }
        return tailPos.size();
    }

    /**
     * The second part;
     * <p> Don't ask me about no reasoning here, just did what i felt was right</p>
     *
     * @return ans
     * @throws FileNotFoundException if file not found
     */
    public static long solve2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input9.txt"));
        int[] xs = new int[10];
        int[] ys = new int[10];
        for (int i = 0; i < 10; i++)
            xs[i] = ys[i] = 100;
        ArrayList<Position> tailPos = new ArrayList<>();
        tailPos.add(new Position(xs[9], ys[9]));
        while (sc.hasNextLine()) {
            String[] temp = sc.nextLine().split(" ");
            int no_steps = Integer.parseInt(temp[1]);
            int dx = 0, dy = 0;
            switch (temp[0]) {
                case "L" -> dy = -1;
                case "R" -> dy = 1;
                case "U" -> dx = -1;
                case "D" -> dx = 1;
            }
            for (int i = 0; i < no_steps; i++) {
                if (Math.abs(xs[0] + dx - xs[1]) > 1 || Math.abs(ys[0] + dy - ys[1]) > 1) {
                    xs[1] = xs[0];
                    ys[1] = ys[0];
                }
                ys[0] += dy;
                xs[0] += dx;
                for (int j = 2; j < 10; j++) {
                    if (Math.abs(xs[j - 1] - xs[j]) > 1 || Math.abs(ys[j - 1] - ys[j]) > 1) {
                        xs[j] += sgn(xs[j - 1] - xs[j]);
                        ys[j] += sgn(ys[j - 1] - ys[j]);
                        if (j == 9)
                            if (!tailPos.contains(new Position(xs[j], ys[j])))
                                tailPos.add(new Position(xs[j], ys[j]));
                    }
                }
            }
        }
        return tailPos.size();
    }

    /**
     * diy sign method 'cause I was lazy to look up one, and maybe it wasn't what I needed
     *
     * @param i number to find the sign on
     * @return +/-1 or 0, depending on value
     */
    public static int sgn(int i) {
        if (i == 0)
            return 0;
        return i / Math.abs(i);
    }
}
