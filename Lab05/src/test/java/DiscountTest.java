import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


// Bai 1 - moi test case ung voi mot quy tac cua bang quyet dinh
public class DiscountTest {

    @Test
    void TC01_R4_khachMoi_khongCoThe_giam10() {
        assertEquals(10, Discount.discountPercent(Discount.CustomerType.KHACH_MOI, false));
    }

    @Test
    void TC02_R3_khachCu_khongCoThe_giam15() {
        assertEquals(15, Discount.discountPercent(Discount.CustomerType.KHACH_CU, false));
    }

    @Test
    void TC03_R1_khachMoi_coThe_giam25() {
        assertEquals(25, Discount.discountPercent(Discount.CustomerType.KHACH_MOI, true));
    }

    @Test
    void TC04_R2_khachCu_coThe_giam25() {
        assertEquals(25, Discount.discountPercent(Discount.CustomerType.KHACH_CU, true));
    }

    @Test
    void TC05_tinhTienPhaiTra_khachCuCoThe() {
        assertEquals(750_000L, Discount.finalPrice(1_000_000L, Discount.CustomerType.KHACH_CU, true));
    }

    @Test
    void TC06_loaiKhachNull_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> Discount.discountPercent((Discount.CustomerType) null, false));
    }

    @Test
    void TC07_giaTriDonAm_baoLoi() {
        assertThrows(IllegalArgumentException.class,
                () -> Discount.finalPrice(-1L, Discount.CustomerType.KHACH_MOI, false));
    }
}
