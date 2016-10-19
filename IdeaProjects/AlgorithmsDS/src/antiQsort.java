import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by reddy on 23.09.16.
 */


public class antiQsort {
    public static int[] a;
    public static int k;

    public static void qSort(int l, int r) {
        int i = l;
        int j = r;
        int x = a[(l + r) / 2];
        while (i <= j) {
            while (a[i] < x) {
                i++;
            }
            while (a[j] > x) {
                j--;
            }
            if (i <= j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }
        if (l < j && k-1 <= j){
            qSort(l, j);
        }
        if(i < r && k-1 >= i){
            qSort(i, r);
        }
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

        qSort(0, n - 1);

        out.println(a[k - 1]);

        out.close();
    }
}
