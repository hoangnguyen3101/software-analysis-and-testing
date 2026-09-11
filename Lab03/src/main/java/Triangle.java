public class Triangle {

    // So trong ngoac (n) la so hieu nut tuong ung tren Control Flow Graph (xem docs)
    public static String classify(int a, int b, int c) {

        if (a <= 0 || b <= 0 || c <= 0) {                   // (1)
            return "Invalid";                               // (2)
        }

        if (a + b <= c || a + c <= b || b + c <= a) {       // (3)
            return "Not a triangle";                        // (4)
        }

        if (a == b && b == c) {                             // (5)
            return "Equilateral";                           // (6)
        } else if (a == b || b == c || a == c) {            // (7)
            return "Isosceles";                             // (8)
        } else {
            return "Scalene";                               // (9)
        }
    }                                                       // (10) exit

    public static void main(String[] args) {
        int[][] inputs = {{0, 4, 5}, {1, 2, 3}, {3, 3, 3}, {3, 3, 5}, {3, 4, 5}};

        for (int[] in : inputs) {
            System.out.println("(" + in[0] + ", " + in[1] + ", " + in[2] + ") -> " + classify(in[0], in[1], in[2]));
        }
    }
}
