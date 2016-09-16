import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

/**
 * Created by reddy on 15.09.16.
 */
public class FirstIssue {

    private enum OPTIONS{
        ENCRYPT,
        DECRYPT
    }

    private static class Output<T> {
        private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        public OPTIONS getOption() throws IOException {
            System.out.print("Do you want to encrypt (y\\n): ");
            Character ans = (char) input.read();

            switch (ans) {
                case 'y' : return OPTIONS.ENCRYPT;
                default : return OPTIONS.DECRYPT;
            }
        }

        public String getPassPhrase() throws IOException {
            boolean ok = false;
            String pass = new String();
            while (!ok) {
                System.out.print("Enter pass phrase: ");
                input.readLine(); //to flush input
                pass = input.readLine().toLowerCase();
                if (pass.length() < 25) { ok = true; }
            }
            return pass;
        }

        public String getMessage() throws IOException {
            System.out.println("Enter Message: ");
            return input.readLine().toLowerCase();
        }
    }

    private static class Table {
        private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        List<List<Character>> matrix = new ArrayList<>();

        public Table(String word) {
            word = trimDouble(word);
            String localAlphabet = trimDouble(ALPHABET, word);

            {
                List<Character> topLine = new ArrayList<>(localAlphabet .length());
                for (char c : localAlphabet .toCharArray()) {
                    topLine.add(c);
                }
                matrix.add(topLine);
            }

            for (int i = 1; i < localAlphabet.length(); i++) {
                List<Character> line = new ArrayList<>();
                for (int j = i; j < localAlphabet.length() + i; j++) {
                    if (j == i || (j - 2) >= word.length()) {
                        line.add(j < localAlphabet.length() ?
                                localAlphabet.charAt(j) :
                                localAlphabet.charAt(j - localAlphabet.length()));
                    } else {
                        line.add(word.charAt(j - 2));
                    }
                }
                matrix.add(line);
            }

            showTable("table is done!\n--------------");
        }

        private String trimDouble(String word) {
            boolean[] letters = new boolean[ALPHABET.length()]; //they are default false
            String ret  = new String();
            for (char c : word.toCharArray()) {
                if (!letters[word.indexOf(c)]) {
                    letters[word.indexOf(c)] = true;
                    ret += c;
                }
            }
            return ret;
        }

        private String trimDouble(String firstWord, String secondWord) {
            String ret  = new String();
            for (char c : firstWord.toCharArray()) {
                if (secondWord.indexOf(c) < 0) {
                    ret += c;
                }
            }
            return ret;
        }

        public char get(char line, char column) {
            return matrix.get(ALPHABET.indexOf(column)).get(ALPHABET.indexOf(line));
        }

//        public List<List<Character>> transpose (List<List<Character>> matrix) {
//            for (int i  = 0; i < matrix.size(); i++) {
//                for (int j = i + 1; j < matrix.get(i).size(); j++) {
//                    //it's a strange swap
//                    Character first = matrix.get(i).get(j);
//                    Character second = matrix.get(j).get(i);
//                    matrix.get(i).set(j, second);
//                    matrix.get(j).set(i, first);
//                }
//            }
//            return matrix;
//        }

        public void showTable(final String foreword) {
            System.out.println(foreword);

            for (List<Character> characters : matrix) {
                for (Character symbol : characters) {
                    // toString must be implemented for T
                    System.out.print(symbol + "\t");
                }
                System.out.println();
            }
        }
    }

    public static void encrypt(Table table, String message) {
        String encryptedMessage = new String();
        if (message.length() == 0) {
            System.out.println("");
            return;
        }
        encryptedMessage += message.charAt(0);      //first as is
        for (int i = 1; i < message.length(); i++) {
            encryptedMessage += table.get(message.charAt(i - 1), message.charAt(i));
        }
        System.out.println("Encrypted message is: " + encryptedMessage);
    }

    public static void decrypt(Table table, String message) {
        if (message.length() == 0) {
            System.out.println("");
            return;
        }

        String encryptedMessage = new String();
        encryptedMessage += message.charAt(0);      //first as is
        for (int i = 1; i < message.length(); i++) {
            encryptedMessage += table.get(message.charAt(i - 1), message.charAt(i));
        }

        System.out.println("Encrypted message is: " + encryptedMessage);
    }

    public static void main(String[] args) throws Exception {
        Output<Character> output = new Output<>();
        OPTIONS option = output.getOption();
        Table table = new Table(output.getPassPhrase());
        switch (option) {
            case ENCRYPT: encrypt(table, output.getMessage()); break;
            case DECRYPT: decrypt(table, output.getMessage()); break;
        }
    }
}
