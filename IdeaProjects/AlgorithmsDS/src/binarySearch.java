import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by reddy on 17.10.16.
 */
public class binarySearch {
    static Pair<Integer, Integer>[] a;
    static int n = 0;
    static int[] prefix;

    public static int binSearch(int toFind) {
        int lf = 0, rg = n - 1;
        while (lf <= rg) {
            int md = lf + (rg - lf) / 2;
            if      (toFind < a[md].getKey()) rg = md - 1;
            else if (toFind > a[md].getKey()) lf = md + 1;
            else return md;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("binsearch.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("binsearch.out")));

        n = in.nextInt();
        a = new Pair[n];

        for (int i = 0; i < n; i++) {
            int cash = in.nextInt();
            if (i == 0) {
                a[i] = new Pair<>(cash, 0);
            } else if (a[i - 1].getKey() == cash) {
                a[i - 1] = new Pair(cash, a[i-1].getValue() + 1);
                i--;
                n--;
            } else {
                a[i] = new Pair(cash, 0);
            }
        }

        prefix = new int[n];
        int prefSum = 0;
        for (int i = 0; i < n; i++) {
            prefix[i] = prefSum;
            prefSum += a[i].getValue();
        }

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int toFind = in.nextInt();
            int cash = binSearch(toFind);
            int pref = cash == -1 ? 0 : prefix[cash];
            out.println(cash != -1 ? ((cash + 1 + pref) + " " + (cash + 1 + a[cash].getValue() + pref)) : "-1 -1");
        }

        out.close();
    }
}
