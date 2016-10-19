import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by reddy on 17.10.16.
 */
public class RadixSort {
    static List<String> elems = new ArrayList();

    public static void countSort(int place) {
        List<String>[] counter = new List[26];

        for (int i = 0; i < elems.size(); i++) {
            String s = elems.get(i);
            List<String> cash = counter[s.charAt(place) - ((int)'a')];
            if (cash == null) {
                cash = new ArrayList<>();
            }
            cash.add(s);
            counter[(s.charAt(place) - ((int)'a'))] = cash;
        }

        List<String> newElems = new ArrayList<>(elems.size());
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] != null)
                for (int j = 0; j < counter[i].size(); j++) {
                    newElems.add(counter[i].get(j));
                }
        }
        elems = newElems;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("radixsort.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("radixsort.out")));

        int elementsAmount = in.nextInt();
        int stringLength = in.nextInt();
        int iterations = in.nextInt();

        in.nextLine();
        for (int i = 0; i < elementsAmount; i++) {
            elems.add(in.nextLine());
        }

        for (int i = 0; i < iterations; i++) {
            countSort(stringLength - i - 1);
        }

        for (int i = 0; i < elems.size(); i++) {
            out.println(elems.get(i));
        }

        out.close();
    }
}
