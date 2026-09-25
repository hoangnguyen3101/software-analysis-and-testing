/**
 * Bai 4 - Chinh sach mien phi van chuyen.
 *
 * Luat theo thu tu uu tien (docs/Bai04-Bang-quyet-dinh-mien-phi-van-chuyen.md):
 *   R1: ngoai pham vi phuc vu                       -> tu choi don (uu tien cao nhat)
 *   R2: trong vung, don >= 500.000                  -> mien phi ship
 *   R3: trong vung, khach VIP, don >= 200.000       -> mien phi ship
 *   R4: trong vung, cac truong hop con lai          -> thu 30.000
 */
public class ShippingPolicy {

    public enum Result {
        TU_CHOI_DON("Tu choi don hang", -1),
        MIEN_PHI("Mien phi van chuyen", 0),
        PHI_TIEU_CHUAN("Thu phi van chuyen 30.000d", 30_000);

        private final String label;
        private final long fee;

        Result(String label, long fee) {
            this.label = label;
            this.fee = fee;
        }

        public String getLabel() {
            return label;
        }

        /** Phi van chuyen phai tra; -1 nghia la don khong duoc chap nhan. */
        public long getFee() {
            return fee;
        }
    }

    public static final long NGUONG_THUONG = 500_000L;
    public static final long NGUONG_VIP = 200_000L;
    public static final long PHI_VAN_CHUYEN = 30_000L;

    public static Result evaluate(long orderValue, boolean isVip, boolean outsideServiceArea) {
        if (orderValue < 0) {
            throw new IllegalArgumentException("Gia tri don hang khong duoc am: " + orderValue);
        }

        if (outsideServiceArea) {
            return Result.TU_CHOI_DON;                  // R1
        }

        if (orderValue >= NGUONG_THUONG) {
            return Result.MIEN_PHI;                     // R2
        }

        if (isVip && orderValue >= NGUONG_VIP) {
            return Result.MIEN_PHI;                     // R3
        }

        return Result.PHI_TIEU_CHUAN;                   // R4
    }

    public static void main(String[] args) {
        long[] values = {199_999L, 200_000L, 499_999L, 500_000L};

        for (long v : values) {
            System.out.println(v + " - VIP     : " + evaluate(v, true, false).getLabel());
            System.out.println(v + " - Thuong  : " + evaluate(v, false, false).getLabel());
        }

        System.out.println("1.000.000 - ngoai vung: " + evaluate(1_000_000L, true, true).getLabel());
    }
}
