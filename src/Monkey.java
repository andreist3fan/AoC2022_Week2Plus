import java.util.ArrayList;
import java.util.Scanner;

public record Monkey(ArrayList<Long> items, char op, String val, int divBy, int passIfTrue,
                     int passIfFalse) {

    public void addItem(long item) {
        items.add(item);
    }

    public long removeItem(int index) {
        return items.remove(index);
    }

    public static Monkey readMonkey(Scanner sc) {
        int lineNo = 0;
        ArrayList<Long> itms = new ArrayList<>();
        String vl = "";
        char o = ' ';
        int div = 0, ifD = 0, ifND = 0;
        String line = sc.nextLine();
        while (!line.trim().equals("")) {
            String[] spaces = line.split("[:, ]");
            switch (lineNo) {
                case 1 -> {
                    for (int i = 5; i < spaces.length; i += 2) {
                        itms.add(Long.parseLong(spaces[i]));
                    }
                }
                case 2 -> {
                    o = spaces[7].charAt(0);
                    vl = spaces[8];
                }
                case 3 -> div = Integer.parseInt(spaces[6]);
                case 4 -> ifD = Integer.parseInt(spaces[10]);
                case 5 -> ifND = Integer.parseInt(spaces[10]);
            }
            lineNo++;
            if (sc.hasNextLine())
                line = sc.nextLine();
            else
                break;
        }
        return new Monkey(itms, o, vl, div, ifD, ifND);
    }
}
