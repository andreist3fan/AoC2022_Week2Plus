import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day10 {

    /**
     * 10th day, first part;
     * very easy and fun
     *
     * @return answer
     * @throws FileNotFoundException if no file :(
     */
    public static long solve1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input10.txt"));
        long regX = 1, ins = 1, res = 0;
        while (sc.hasNextLine()) {
            if (ins % 40 == 20 && ins < 240) {
                res += (ins * regX);
            }
            String[] inst = sc.nextLine().split(" ");
            switch (inst[0]) {
                case "noop" -> ins++;

                case "addx" -> {
                    long val = Long.parseLong(inst[1]);
                    ins++;
                    if (ins % 40 == 20 && ins < 240) {
                        res += (ins * regX);
                    }
                    regX += val;
                    ins++;
                }
            }
        }
        return res;
    }

    /**
     * 10th day, second part
     * <p>More fun, a bit less easy, but still easy-ish</p>
     *
     * @throws FileNotFoundException if no file :/
     */
    public static void solve2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input10.txt"));
        char[][] pixel = new char[6][40];
        long regX = 1, ins = 1;
        while (sc.hasNextLine()) {
            if ((ins - 1) % 40 >= regX - 1 && (ins - 1) % 40 <= regX + 1) {
                pixel[(int) ((ins - 1) / 40)][(int) ((ins - 1) % 40)] = '▓';
            } else pixel[(int) ((ins - 1) / 40)][(int) ((ins - 1) % 40)] = ' ';
            String[] inst = sc.nextLine().split(" ");
            switch (inst[0]) {
                case "noop" -> ins++;

                case "addx" -> {
                    long val = Long.parseLong(inst[1]);
                    ins++;
                    if ((ins - 1) % 40 >= regX - 1 && (ins - 1) % 40 <= regX + 1) {
                        pixel[(int) ((ins - 1) / 40)][(int) ((ins - 1) % 40)] = '▓';
                    } else pixel[(int) ((ins - 1) / 40)][(int) ((ins - 1) % 40)] = ' ';
                    regX += val;
                    ins++;
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 40; j++) {
                System.out.print(pixel[i][j]);
            }
            System.out.println();
        }
    }
}
