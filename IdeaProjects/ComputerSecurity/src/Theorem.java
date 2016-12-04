import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 */
public class Theorem {
    static final int[] primes = {11, 12, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 57, 59, 61, 67, 71};

    private static int getPrimesMultiplication(int startPoint, int stopPoint) {
        int result = 1;
        for (int i = startPoint; i < stopPoint; i++) {
            if (i == primes.length) {
                System.out.println("System cant provide this amount of users");
                throw new IllegalArgumentException();
            }
            result *= primes[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Type 1 if you want to get shadows, any other num, if you want to get a Secret");
        int ans = in.nextInt();

        if (ans == 1) {
            System.out.println("Please enter the amount of people ");
            final int people = in.nextInt();

            System.out.println("Please enter a necessary and sufficient amount to decode");
            final int minAmount = in.nextInt();

            int pk = getPrimesMultiplication(0, minAmount);
            int pn = getPrimesMultiplication(people - minAmount + 1, people);

            if (pk > pn) {
                int cash = pn;
                pn = pk;
                pk = cash;
            }

            System.out.println("Enter random S from " + pk + " to " + pn);
            int S = in.nextInt();
           /* if (S <= pk || S >= pn) {
                System.out.println("Wrong S!!!");
                throw new IllegalArgumentException();
            }*/

            for (int i = 0; i < people; i++) {
                System.out.println("Shadow " + (i + 1) + " is " + S % primes[i]);
            }
        } else {
            System.out.println("Please, enter shadows amount");
            int shadowsAmount = in.nextInt();

            System.out.println("One by one enter all shadows");
            List<Integer> shadows = new ArrayList<>(shadowsAmount);
            for (int i = 0; i < shadowsAmount; i++) {
                shadows.add(in.nextInt());
            }

            System.out.println("Enter numbers: people in chain");
            List<Integer> chain = new ArrayList<>(shadowsAmount);
            for (int i = 0; i < shadowsAmount; i++) {
                chain.add(in.nextInt() - 1);
            }

            System.out.println("Searching for solution...");
            int commonModule = 1;
            for (int i : chain) {
                commonModule *= primes[i];
            }

            int secret = -1;

            for (int i = 1; i < 1000_000_000; i++) {
                boolean ok = true;
                for (int j = 0; j < shadowsAmount; j++) {
                    if (i % primes[chain.get(j)] != shadows.get(j)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    secret = i;
                    break;
                }
            }

            System.out.println("Secret Is " + (secret == -1 ? "WRONG DATA" : secret));
        }
    }
}
