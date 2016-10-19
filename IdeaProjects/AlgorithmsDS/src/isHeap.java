import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by reddy on 17.10.16.
 */
public class isHeap {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("isheap.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("isheap.out")));

        int n = in.nextInt();
        long[] a = new long[n + 1];   //dirty silly hack
        for (int i = 0; i < n; i++) {
            a[i + 1] = in.nextLong();
        }

        boolean fact= true;
        for (int i = 1; i <= n; i++) {
            if (2 * i <= n) {
                fact = fact && a[i] <= a[2 * i];
            }
            if (2 * i + 1 <= n) {
                fact = fact && a[i] <= a[2 * i + 1];
            }
        }

        out.println(fact ? "YES" : "NO");

        out.close();
    }
}
