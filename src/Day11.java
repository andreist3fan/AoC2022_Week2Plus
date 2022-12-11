import java.io.*;
import java.util.*;

public class Day11 {

    /**
     * First problem: everything fits into int;
     * <p>Lots of parsing though</p>
     * @return answer
     * @throws FileNotFoundException if no file :(
     */
    public static int solve1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input11.txt"));
        ArrayList<Monkey> monkeys= new ArrayList<>();
        List<Integer> business= new ArrayList<>();
        while(sc.hasNextLine()){
            business.add(0);
            monkeys.add(Monkey.readMonkey(sc));
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < monkeys.size(); j++) {
                Monkey current = monkeys.get(j);

                for(int k=0; k<current.items().size();){
                    business.set(j, business.get(j)+1);
                    long toI = current.removeItem(0);
                    long second;
                    if(current.val().startsWith("old"))
                        second=toI;
                    else
                        second = Long.parseLong(current.val());
                    switch (current.op()){
                        case '*'-> toI = toI*second;
                        case '+'-> toI = toI+second;
                    }
                    toI/=3;
                    if(toI% current.divBy()==0)
                        monkeys.get(current.passIfTrue()).addItem(toI);
                    else
                        monkeys.get(current.passIfFalse()).addItem(toI);
                }
            }
        }
        business = business.stream().sorted().toList();
        return business.get(business.size()-1)*business.get(business.size()-2);
    }
    /**
     * Second problem: everything no longer fits into int (or long);
     * <p>Find common denominator and take the modulo of that to keep values low</p>
     * @return answer
     * @throws FileNotFoundException if no file :(
     */
    public static long solve2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input11.txt"));
        ArrayList<Monkey> monkeys= new ArrayList<>();
        List<Integer> business= new ArrayList<>();
        int x=0, mxDiv =1;
        while(sc.hasNextLine()){
            business.add(0);
            monkeys.add(Monkey.readMonkey(sc));
            mxDiv *=monkeys.get(x).divBy();
            x++;
        }
        for (int i = 1; i <= 10000; i++) {
            for (int j = 0; j < monkeys.size(); j++) {
                Monkey current = monkeys.get(j);
                business.set(j, business.get(j)+current.items().size());
                for(int k=0; k<current.items().size();){

                    long toI = current.removeItem(0);
                    long second;
                    if(current.val().startsWith("old"))
                        second=toI;
                    else
                        second = Integer.parseInt(current.val());
                    switch (current.op()){
                        case '*'-> toI = toI*second;
                        case '+'-> toI = toI+second;
                    }
                    toI%= mxDiv;
                    if(toI% current.divBy()==0)
                        monkeys.get(current.passIfTrue()).addItem(toI);
                    else
                        monkeys.get(current.passIfFalse()).addItem(toI);
                }
            }
        }
        business = business.stream().sorted().toList();
        return (long) business.get(business.size()-1)*business.get(business.size()-2);
    }
}
