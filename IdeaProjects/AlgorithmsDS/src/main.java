import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("aplusbb.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("aplusbb.out")));
        long a = in.nextInt();
        long b = in.nextInt();
        a = a + b*b;
        out.println(a);
        out.close();
    }
}
