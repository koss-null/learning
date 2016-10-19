import javafx.util.Pair;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class inversions {
    static long cnt = 0;

    public static List<Integer> merge(List<Integer> first, List<Integer> second) {
        List<Integer> ret = new ArrayList<>(first.size() + second.size());
        int firstPointer = 0, secondPointer = 0;

        while (firstPointer < first.size() && secondPointer < second.size()) {
            if (first.get(firstPointer) <= second.get(secondPointer)) {
                cnt += secondPointer;
                ret.add(first.get(firstPointer));
                firstPointer++;
            } else {
                ret.add(second.get(secondPointer));
                secondPointer++;
            }
        }

        while (firstPointer < first.size()) {
            if (second.get(second.size() - 1) !=  first.get(firstPointer)) {
                cnt += secondPointer;
            }
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
        int md = lf + (rg - lf) / 2;
        if (lf + 1 >= rg) {
            List<Integer> ret = new ArrayList<>(1);
            ret.add(list.get(lf));
            return ret;
        }
        return merge(mergeSort(list, lf, md), mergeSort(list, md, rg));
    }


    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("inversions.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("inversions.out")));

        int length = in.nextInt();

        List<Integer> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.add(in.nextInt());
        }

        if (list.size() == 0) {
            out.println(0);
            return;
        }
        //not clear function changes cnt
        mergeSort(list, 0, list.size());

        out.println(cnt);
        out.close();
    }
}