import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.*;

public class Day15 {
    /**
     * tough tough tough Day 15.......
     * <p>First problem</p>
     *
     * @param xQuestion where the signal should be, btw I reversed the coords
     * @return answer
     * @throws FileNotFoundException fnf
     */
    public static long solve1(int xQuestion) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input15.txt"));
        ArrayList<Position> sensors = new ArrayList<>();
        ArrayList<Position> beacons = new ArrayList<>();
        ArrayList<Integer> distances = new ArrayList<>();
        List<Integer> confirmedPos = new ArrayList<>();
        List<Position> seqs = new ArrayList<>();

        while (sc.hasNextLine()) {
            String[] coords = sc.nextLine().split("Sensor at x=|, y=|: closest beacon is at x=");
            int xs = Integer.parseInt(coords[1]), ys = Integer.parseInt(coords[2]);
            int xb = Integer.parseInt(coords[3]), yb = Integer.parseInt(coords[4]);
            if (yb == xQuestion && !confirmedPos.contains(xb)) {
                confirmedPos.add(xb);
            }
            distances.add(abs(xs - xb) + abs(ys - yb));
            sensors.add(new Position(ys, xs));
            if (!beacons.contains(new Position(yb, xb)))
                beacons.add(new Position(yb, xb));
        }
        // x=2_000_000

        for (int i = 0; i < sensors.size(); i++) {
            //System.out.println(i);
            int x = sensors.get(i).getX(),
                y = sensors.get(i).getY(),
                dist = distances.get(i);
            // System.out.println(x + " " + y + " " + dist);
            if (x < xQuestion && x + dist >= xQuestion) {
                dist -= (xQuestion - x);
                x = xQuestion;
            } else if (x > xQuestion && x - dist <= xQuestion) {
                dist -= (x - xQuestion);
                x = xQuestion;
            } else continue;
            //System.out.println("continued");

            seqs.add(new Position(y - dist, y + dist));
        }
        for (Position p : seqs) {
            System.out.println(p.getX() + " " + p.getY());
        }
        for (int i = 0; i < seqs.size() - 1; i++) {
            for (int j = i + 1; j < seqs.size(); j++) {
                Position p1 = seqs.get(i),
                    p2 = seqs.get(j);
                int maxX = max(p1.getX(), p2.getX());
                int minY = min(p1.getY(), p2.getY());
                if ((isIn(maxX, p1) && isIn(maxX, p2)) || (isIn(minY, p1) && isIn(minY, p2))) {
                    seqs.add(new Position(min(p1.getX(), p2.getX()), max(p1.getY(), p2.getY())));
                    seqs.remove(i);
                    seqs.remove(j - 1);
                    j = i;
                }
            }
        }
        //seqs = seqs.stream().sorted().toList();
        //for(int i=0; i<confirmedPos.size();i++)
        //System.out.println(confirmedPos.get(i));
        System.out.println();
        for (Position p : seqs) {
            System.out.println(p.getX() + " " + p.getY());
        }
        return seqs.get(0).getY() - seqs.get(0).getX();//there is a beacon there
    }

    public static boolean isIn(int x, Position p) {
        return x >= p.getX() && x <= p.getY();
    }

    /**
     * Lots of finicking around and debugging, as solution 1 was kinda wrong.
     * <p>sigh.. all's well that ends well, right?</p>
     *
     * @return answer found out by hand
     * @throws FileNotFoundException fnf
     */
    public static long solve2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input15.txt"));
        ArrayList<Position> sensors = new ArrayList<>();
        ArrayList<Position> beacons = new ArrayList<>();
        ArrayList<Integer> distances = new ArrayList<>();
        List<Integer> confirmedPos = new ArrayList<>();
        List<Position> seqs = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] coords = sc.nextLine().split("Sensor at x=|, y=|: closest beacon is at x=");
            int xs = Integer.parseInt(coords[1]), ys = Integer.parseInt(coords[2]);
            int xb = Integer.parseInt(coords[3]), yb = Integer.parseInt(coords[4]);
            distances.add(abs(xs - xb) + abs(ys - yb));
            sensors.add(new Position(ys, xs));
            if (!beacons.contains(new Position(yb, xb)))
                beacons.add(new Position(yb, xb));
        }
        // x=2_000_000
        for (int xq = 1; xq < 4000001; xq++) {
            seqs = new ArrayList<>();
            for (Position q : sensors) {
                if (q.getX() == xq)
                    seqs.add(new Position(q.getY(), q.getY()));
            }
            for (int i = 0; i < sensors.size(); i++) {
                //System.out.println(i);
                int x = sensors.get(i).getX(),
                    y = sensors.get(i).getY(),
                    dist = distances.get(i);
                // System.out.println(x + " " + y + " " + dist);
                if (x < xq && x + dist >= xq) {
                    dist -= (xq - x);
                    x = xq;
                } else if (x > xq && x - dist <= xq) {
                    dist -= (x - xq);
                    x = xq;
                } else if (x != xq) continue;
                //System.out.println("continued");

                seqs.add(new Position(y - dist, y + dist));
            }

            for (int i = 0; i < seqs.size() - 1; i++) {
                for (int j = i + 1; j < seqs.size(); j++) {
                    Position p1 = seqs.get(i),
                        p2 = seqs.get(j);
                    int maxX = max(p1.getX(), p2.getX());
                    int minY = min(p1.getY(), p2.getY());
                    if ((isIn(maxX, p1) && isIn(maxX, p2)) || (isIn(minY, p1) && isIn(minY, p2)) ||
                        continues(p1, p2)) {
                        seqs.add(
                            new Position(min(p1.getX(), p2.getX()), max(p1.getY(), p2.getY())));
                        seqs.remove(i);
                        seqs.remove(j - 1);
                        j = i;
                    }
                }
            }
            //System.out.println(xq);
            //for (Position p : seqs) {
            //    System.out.println(p.getX() + " " + p.getY());
            //}
            //System.out.println();
            if (seqs.size() == 2)
                System.out.println(xq);
        }
        //2949122 3041245
        return 2949122L * 4000000 + 3041245;
    }

    public static boolean continues(Position p1, Position p2) {
        return p1.getX() == p2.getY() + 1 || p1.getY() + 1 == p2.getX();
    }
}
