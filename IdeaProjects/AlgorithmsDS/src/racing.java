import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;


public class racing {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("race.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("race.out")));

        int participantsAmount = in.nextInt();
        Map<String, List<String>> results = new HashMap<>();

        in.nextLine();

        for (int i = 0; i < participantsAmount; i++) {
            String s = in.nextLine();
            String country = s.split(" ")[0];
            String sportsmen = s.split(" ")[1];
            if (results.containsKey(country)) {
                results.get(country).add(sportsmen);
            } else {
                List<String> list = new LinkedList<>();
                list.add(sportsmen);
                results.put(country, list);
            }
        }

        //dirty hack
        SortedSet<String> keys = new TreeSet<>(results.keySet());
        for (String entry : keys) {
            out.println("=== " + entry + " ===");
            for (String s : results.get(entry)) {
                out.println(s);
            }
        }

        out.close();
    }
}
