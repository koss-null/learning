import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by reddy on 23.09.16.
 */


public class kth {
    public static int[] a;
    public static int k;

    public static void qsort(int left, int right) {
        int lf = left, rg = right;
        int md = lf + (rg - lf) / 2;
        while (lf < rg) {
            while (a[lf] < a[md])
                lf++;
            while (a[rg] > a[md])
                rg--;
            if (lf < rg) {
                int cash = a[lf];
                a[lf] = a[rg];
                a[rg] = cash;
            }
        }

        if (lf < right && k-1 <= lf) qsort(lf, right);
        if (rg > left && k-1 >= left) qsort(left, rg);
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("kth.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("kth.out")));

        int n = in.nextInt();
        k = in.nextInt();
        int A = in.nextInt(), B = in.nextInt(), C = in.nextInt();
        a = new int[n];
        a[0] = in.nextInt();
        a[1] = in.nextInt();

        for (int i = 2; i < n; i++) {
            a[i] = A * a[i - 2] + B * a[i - 1] + C;
        }

        qsort(0, n);

        out.println(a[k - 1]);

        out.close();
    }
}
