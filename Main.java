public class Main {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 7 == 0) {
                sum += i;
            }
        }
        System.out.println("Tổng các số từ 1 đến 100 là bội số của 7 là: " + sum);
    }
}
