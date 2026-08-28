public class ElectricityBill {

    public static double calculateBill(double[] usages) {
        double total = 0;

        for (double usage : usages) {
            if (usage <= 50) {
                total += usage * 1.8;
            } else {
                total += 50 * 1.8 + (usage - 50) * 2.5;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        double[] usages = {30, 60, 40};

        double bill = calculateBill(usages);

        System.out.println("Total electricity bill: " + bill);
    }
}