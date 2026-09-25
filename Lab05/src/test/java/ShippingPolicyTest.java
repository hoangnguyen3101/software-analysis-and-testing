import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// Bai 4 - test theo quy tac cua bang quyet dinh + cac moc 199.999 / 200.000 / 499.999 / 500.000
public class ShippingPolicyTest {

    // ----- R1: ngoai pham vi phuc vu, uu tien cao nhat -----

    @Test
    void TC01_R1_ngoaiVung_donLon_khachVIP_vanTuChoi() {
        assertEquals(ShippingPolicy.Result.TU_CHOI_DON,
                ShippingPolicy.evaluate(1_000_000L, true, true));
    }

    @Test
    void TC02_R1_ngoaiVung_donNho_khachThuong_tuChoi() {
        assertEquals(ShippingPolicy.Result.TU_CHOI_DON,
                ShippingPolicy.evaluate(50_000L, false, true));
    }

    // ----- R2: don tu 500.000 tro len -----

    @Test
    void TC03_R2_khachThuong_dung500k_mienPhi() {
        assertEquals(ShippingPolicy.Result.MIEN_PHI,
                ShippingPolicy.evaluate(500_000L, false, false));
    }

    @Test
    void TC04_R4_khachThuong_499999_thuPhi() {
        assertEquals(ShippingPolicy.Result.PHI_TIEU_CHUAN,
                ShippingPolicy.evaluate(499_999L, false, false));
    }

    @Test
    void TC05_R2_khachVIP_dung500k_mienPhi() {
        assertEquals(ShippingPolicy.Result.MIEN_PHI,
                ShippingPolicy.evaluate(500_000L, true, false));
    }

    // ----- R3: khach VIP tu 200.000 tro len -----

    @Test
    void TC06_R3_khachVIP_dung200k_mienPhi() {
        assertEquals(ShippingPolicy.Result.MIEN_PHI,
                ShippingPolicy.evaluate(200_000L, true, false));
    }

    @Test
    void TC07_R4_khachVIP_199999_thuPhi() {
        assertEquals(ShippingPolicy.Result.PHI_TIEU_CHUAN,
                ShippingPolicy.evaluate(199_999L, true, false));
    }

    @Test
    void TC08_R3_khachVIP_499999_mienPhi() {
        assertEquals(ShippingPolicy.Result.MIEN_PHI,
                ShippingPolicy.evaluate(499_999L, true, false));
    }

    // ----- R4: cac truong hop con lai -----

    @Test
    void TC09_R4_khachThuong_dung200k_thuPhi() {
        assertEquals(ShippingPolicy.Result.PHI_TIEU_CHUAN,
                ShippingPolicy.evaluate(200_000L, false, false));
    }

    @Test
    void TC10_R4_khachThuong_199999_thuPhi() {
        assertEquals(ShippingPolicy.Result.PHI_TIEU_CHUAN,
                ShippingPolicy.evaluate(199_999L, false, false));
    }

    @Test
    void TC11_R4_donBang0_thuPhi() {
        assertEquals(ShippingPolicy.Result.PHI_TIEU_CHUAN,
                ShippingPolicy.evaluate(0L, false, false));
    }

    // ----- Kiem tra so tien phi va du lieu khong hop le -----

    @Test
    void TC12_soTienPhiTuongUngVoiKetQua() {
        assertEquals(0L, ShippingPolicy.evaluate(500_000L, false, false).getFee());
        assertEquals(30_000L, ShippingPolicy.evaluate(100_000L, false, false).getFee());
        assertEquals(-1L, ShippingPolicy.evaluate(100_000L, false, true).getFee());
    }

    @Test
    void TC13_giaTriDonAm_baoLoi() {
        assertThrows(IllegalArgumentException.class,
                () -> ShippingPolicy.evaluate(-1L, false, false));
    }
}
