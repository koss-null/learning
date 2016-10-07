import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class sorting {
    public static List<Integer> merge(List<Integer> first, List<Integer> second) {
        List<Integer> ret = new ArrayList<>(first.size() + second.size());
        int firstPointer = 0, secondPointer = 0;

        while (firstPointer < first.size() && secondPointer < second.size()) {
            if (first.get(firstPointer) < second.get(secondPointer)) {
                ret.add(first.get(firstPointer));
                firstPointer++;
            } else {
                ret.add(second.get(secondPointer));
                secondPointer++;
            }
        }

        while (firstPointer < first.size()) {
            ret.add(first.get(firstPointer));
            firstPointer++;
        }

        while (secondPointer < second.size()) {
            ret.add(second.get(secondPointer));
            secondPointer++;
        }

        return ret;
    }

    public static List<Integer> mergeSort(List<Integer> list, int lf, int rg) {
        int md = (lf + rg) / 2;
        if (lf + 1 >= rg) {
            List<Integer> ret = new ArrayList<>(1);
            ret.add(list.get(lf));
            return ret;
        }
        return merge(mergeSort(list, lf, md), mergeSort(list, md, rg));
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("sort.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("sort.out")));

        int length = in.nextInt();
        List<Integer> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.add(in.nextInt());
        }

        list = mergeSort(list, 0, list.size());

        for  (int i : list) {
            out.print(i + " ");
        }

        out.close();
    }
}