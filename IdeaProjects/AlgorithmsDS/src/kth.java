import java.io.*;
import java.util.Scanner;

public class kth {
    public class Tree {
        public class Item {
            public int value;
            public Item left;
            public Item right;
            int leftAmount;
            int rightAmount;

            public Item(int value) {
                this.value = (value);
                this.left = null;
                this.right = null;
                this.leftAmount = 0;
                this.rightAmount = 0;
            }

            public void incLeftAmount() {
                leftAmount++;
            }

            public void incRightAmount() {
                rightAmount++;
            }

            public int getLeftAmount() {
                return leftAmount;
            }

            public int getRightAmount() {
                return rightAmount;
            }
        }

        public Item head;

        public Tree(int value) {
            this.head = new Item(value);
        }

        public int get(Item now, int k, int cnt) {
            if (cnt == k) return now.value;
            if (now.getLeftAmount() >= k) {
                return get(now.left, k, cnt + 1);
            } else {
                return get(now.right, k, cnt + 1);
            }
        }

        public void put(Item now, int a) {
            if (a < now.value) {
                now.incLeftAmount();
                if (now.left == null) {
                    now.left = new Item(a);
                } else {
                    put(now.left, a);
                }
            } else {
                now.incRightAmount();
                if (now.right == null) {
                    now.right = new Item(a);
                } else {
                    put(now.right, a);
                }
            }

        }
    }

    public void solve() throws Exception {
        Scanner in = new Scanner(new File("kth.in"));
        PrintWriter out = new PrintWriter(new FileWriter(new File("kth.out")));
        int length = in.nextInt();
        int k = in.nextInt();
        int firstVal = in.nextInt();
        Tree tree = new Tree(firstVal);

        for (int i = 1; i < length; i++) {
            tree.put(tree.head, in.nextInt());
        }

        out.println(tree.get(tree.head, k, 0));
        out.close();
    }

    public static void main(String[] args) throws Exception {
        kth k = new kth();
        k.solve();
    }
}
