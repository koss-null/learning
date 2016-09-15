import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.max;

//is505  iqacafomu

public class turtile {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("turtle.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("turtle.out")));
        int h = in.nextInt();
        int w = in.nextInt();
        List<List<Integer>> field = new ArrayList<>();

        for (int i = 0; i < h; i++) {
            List<Integer> cash = new ArrayList<>();
            for (int j = 0; j < w; j++) {
                cash.add(in.nextInt());
            }
            field.add(cash);
        }

        for (int i = h-1; i > -1; i--) {
            for (int j = 0; j < w; j++) {
                int a, b;
                if (j - 1 >= 0) {
                    a = field.get(i).get(j-1);
                } else a = 0;
                if (i + 1 < h) {
                    b = field.get(i + 1).get(j);
                } else b = 0;

                field.get(i).set(j, field.get(i).get(j) + max(a, b));
//
//                for(List<Integer> aa : field){
//                    System.out.println();
//                    for(Integer bb : aa) {
//                        System.out.print(bb + " ");
//                    }}
            }
        }
        out.print(field.get(0).get(w-1));
        out.close();
    }
}