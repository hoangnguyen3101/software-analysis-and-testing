// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.Test;

// public class ElectricityBillTest {

//     @Test
//     void testUsageBelowOrEqual50() {
//         double[] usages = {30};

//         double result = ElectricityBill.calculateBill(usages);

//         assertEquals(54.0, result, 0.001);
//     }

//     @Test
//     void testUsageAbove50() {
//         double[] usages = {60};

//         double result = ElectricityBill.calculateBill(usages);

//         assertEquals(115.0, result, 0.001);
//     }

//     @Test
//     void testMultipleUsages() {
//         double[] usages = {30, 60, 40};

//         double result = ElectricityBill.calculateBill(usages);

//         assertEquals(241.0, result, 0.001);
//     }
// }


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ElectricityBillTest {

    @Test
    void testEmptyArray() {
        double[] usages = {};

        double result = ElectricityBill.calculateBill(usages);

        assertEquals(0.0, result, 0.001);
    }

    @Test
    void testUsageBelowOrEqual50() {
        double[] usages = {30};

        double result = ElectricityBill.calculateBill(usages);

        assertEquals(54.0, result, 0.001);
    }

    @Test
    void testUsageAbove50() {
        double[] usages = {60};

        double result = ElectricityBill.calculateBill(usages);

        assertEquals(115.0, result, 0.001);
    }

    @Test
    void testMultipleUsagesWithDifferentBranches() {
        double[] usages = {30, 60, 40};

        double result = ElectricityBill.calculateBill(usages);

        assertEquals(241.0, result, 0.001);
    }
}