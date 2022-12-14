import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day13 {

    /**
     * day 13 part 1
     * ughhhhhhhhh
     * whyyyyyyyyyy
     * still doable but whyyyy
     *
     * @return answer
     * @throws FileNotFoundException no file >:(
     */
    public static long solve1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input13.txt"));
        long ans = 0;
        int ct = 0;
        while (sc.hasNextLine()) {
            ct++;
            String line1 = sc.nextLine();
            String line2 = sc.nextLine();
            ArrayList<Object> arr1 = parse(line1, 1, line1.length() - 1);
            ArrayList<Object> arr2 = parse(line2, 1, line2.length() - 1);
            //long preAns = ans;
            if (compare(arr1, arr2) == 1)
                ans += ct;
            //System.out.println(ct+" "+(ans-preAns));
            if (sc.hasNextLine())
                sc.nextLine();
        }
        return ans;
    }

    /**
     * Compares 2 packets
     *
     * @param a1 packet 1
     * @param a2 packet 2
     * @return 1 if a1<a2 , -1 if a1>a2, 0 otherwise
     */
    public static int compare(ArrayList<Object> a1, ArrayList<Object> a2) {
        int i;
        for (i = 0; i < a1.size() && i < a2.size(); i++) {
            if (a1.get(i) instanceof Integer && a2.get(i) instanceof Integer) {
                if ((Integer) a1.get(i) > (Integer) a2.get(i))
                    return -1;
                else if ((Integer) a1.get(i) < (Integer) a2.get(i))
                    return 1;
            } else if (a1.get(i) instanceof ArrayList<?> && a2.get(i) instanceof ArrayList<?>) {
                int bb = compare((ArrayList<Object>) a1.get(i), (ArrayList<Object>) a2.get(i));
                if (bb != 0)
                    return bb;
            } else if (a1.get(i) instanceof Integer) {
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(a1.get(i));
                int bb = compare(temp, (ArrayList<Object>) a2.get(i));
                if (bb != 0)
                    return bb;
            } else {
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(a2.get(i));
                int bb = compare((ArrayList<Object>) a1.get(i), temp);
                if (bb != 0)
                    return bb;

            }

        }
        if (i < a2.size())
            return 1;
        if (i < a1.size())
            return -1;
        return 0;
    }

    /**
     * Parses the horrible input strings, turning them into array lists of objects
     *
     * @param input input string
     * @param from  index to start from(excludes outer square brackets)
     * @param to    index to end at (excludes outer square brackets)
     * @return an ArrayList of objects
     */
    public static ArrayList<Object> parse(String input, int from, int to) {
        ArrayList<Object> res = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            switch (input.charAt(i)) {
                case ' ', ',' -> {
                }
                case '[' -> {
                    res.add(parse(input, i + 1, input.indexOf(']', i + 1) - 1));
                    i = input.indexOf(']', i + 1);
                }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                    int no = 0;
                    while (Character.isDigit(input.charAt(i))) {
                        no = no * 10 + (input.charAt(i) - '0');
                        i++;
                    }
                    res.add(no);
                }
            }
        }
        return res;
    }

    /**
     * day 13 part 2
     * thank god I implemented the compare method above
     *
     * @return answer
     * @throws FileNotFoundException no file >:(
     */
    public static long solve2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input13.txt"));
        ArrayList<Object> all = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line1 = sc.nextLine();
            String line2 = sc.nextLine();
            all.add(parse(line1, 1, line1.length() - 1));
            all.add(parse(line2, 1, line2.length() - 1));
            if (sc.hasNextLine())
                sc.nextLine();
        }
        int pack1 = all.size() - 2, pack2 = all.size() - 1;
        for (int i = 0; i < all.size() - 1; i++)
            for (int j = i + 1; j < all.size(); j++) {
                if (compare((ArrayList<Object>) all.get(i), (ArrayList<Object>) all.get(j)) < 0) {
                    Object temp = all.get(i);
                    all.set(i, all.get(j));
                    all.set(j, temp);
                    if (i == pack1)
                        pack1 = j;
                    else if (j == pack1)
                        pack1 = i;

                    if (i == pack2)
                        pack2 = j;
                    else if (j == pack2)
                        pack2 = i;
                }
            }
        pack1++;
        pack2++;
        return (long) pack1 * pack2;
    }
}
