import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by reddy on 10.09.16.
 */
public class easySort {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("sortland.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("sortland.out")));
        int length = in.nextInt();
        List<Pair<Double, Integer>> a = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            a.add(new Pair(in.nextDouble(), i+1));
        }

        //sorting
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (a.get(j).getKey() > a.get(i).getKey()) {
                    Collections.swap(a, j, i);
                }
            }
        }

        out.print(a.get(0).getValue() + " ");
        out.print(a.get(a.size() / 2).getValue() + " ");
        out.println(a.get(a.size() - 1).getValue());

        out.close();
    }
}
