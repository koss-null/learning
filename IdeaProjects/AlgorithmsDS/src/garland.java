import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by reddy on 17.10.16.
 */
public class garland {
    public static final double eps = 0.000_001;
    public static int check;

    public static double test(int num, double h1, double h2) {
        double next = 0;
        check = 0;
        for (int i = 2; i < num; i++) {
            next = 2 * h2 + 2 - h1;
            h1 = h2;
            h2 = next;
            check += ((next > 0 - eps) && (next < 0 + eps)) ? 1 : 0;
            if (next < 0) check += 2;
        }
        return next;
    }

    public static double binarySearch(int num, double h1) {
        double lf = 0.000_001, rg = h1;
        double minResult = 100_000_000;

        for (int i = 0; i < 100_000; i++) {
            double md = lf + (rg - lf) / 2;
            double result = test(num, h1, md);
            if (check < 2) {
                rg = md;
                if (result < minResult) minResult = result;
            } else {
                lf = md;
            }
        }
        return minResult;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("garland.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("garland.out")));

        int n = in.nextInt();
        double h1 = in.nextDouble();
        out.println(binarySearch(n, h1));

        out.close();
    }
}
