import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by reddy on 23.09.16.
 */


public class antiQsort {
    public static int[] a = new int[71 * 1000];
    public static Deque<Integer> values = new ArrayDeque<>();
    public static int n;

    public static void putValue(int lf, int rg) {
        int md = (lf + rg) / 2;
        a[md] = values.pop();
        if (a[(lf + md) / 2] == 0) putValue(lf, md);
        if (a[(rg + md) / 2] == 0) putValue(md, rg);
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("antiqs.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("antiqs.out")));

        n = in.nextInt();

        for (int i = 1; i <= n; i++) {
            values.push(i);
        }

        putValue(0, n);

        for (int i = 0; i < n; i++) {
            out.print(a[i] + " ");
        }

        out.close();
    }
}
