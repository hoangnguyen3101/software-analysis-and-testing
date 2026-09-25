/**
 * Bai 1 - Chuong trinh khuyen mai cua website ban hang.
 *
 * Luat (theo bang quyet dinh trong docs/Bai01-Bang-quyet-dinh-khuyen-mai.md):
 *   R1, R2: khach co the giam gia          -> 25%  (uu tien cao nhat)
 *   R3    : khach cu, khong co the         -> 15%
 *   R4    : khach moi, khong co the        -> 10%
 */
public class Discount {

    public enum CustomerType {
        KHACH_MOI("Khach moi"),
        KHACH_CU("Khach cu");

        private final String label;

        CustomerType(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public static final int MUC_GIAM_CO_THE = 25;
    public static final int MUC_GIAM_KHACH_CU = 15;
    public static final int MUC_GIAM_KHACH_MOI = 10;

    public static int discountPercent(CustomerType type, boolean hasCard) {
        if (type == null) {
            throw new IllegalArgumentException("Loai khach hang khong duoc null");
        }

        if (hasCard) {
            return MUC_GIAM_CO_THE;                 // R1, R2
        }

        if (type == CustomerType.KHACH_CU) {
            return MUC_GIAM_KHACH_CU;               // R3
        }

        return MUC_GIAM_KHACH_MOI;                  // R4
    }

    /** Tien phai tra sau khi ap dung khuyen mai. */
    public static long finalPrice(long originalPrice, CustomerType type, boolean hasCard) {
        if (originalPrice < 0) {
            throw new IllegalArgumentException("Gia tri don hang khong duoc am: " + originalPrice);
        }

        int percent = discountPercent(type, hasCard);

        return originalPrice - originalPrice * percent / 100;
    }

    public static void main(String[] args) {
        System.out.println("Khach moi, khong the : " + discountPercent(CustomerType.KHACH_MOI, false) + "%");
        System.out.println("Khach cu,  khong the : " + discountPercent(CustomerType.KHACH_CU, false) + "%");
        System.out.println("Khach moi, co the    : " + discountPercent(CustomerType.KHACH_MOI, true) + "%");
        System.out.println("Khach cu,  co the    : " + discountPercent(CustomerType.KHACH_CU, true) + "%");
    }
}
