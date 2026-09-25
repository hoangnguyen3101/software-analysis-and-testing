import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// Bai 3 - test theo quy tac cua bang quyet dinh + gia tri bien 79/80, 0.9/1, 9.9/10
public class ExamEligibilityTest {

    @Test
    void TC01_R1_diHocDuoi80PhanTram_khongDuocDuThi() {
        assertEquals(ExamEligibility.Result.KHONG_DUOC_DU_THI, ExamEligibility.check(79, 8, 8));
    }

    @Test
    void TC02_R2_diemGiuaKyDuoi1_khongDuocDuThi() {
        assertEquals(ExamEligibility.Result.KHONG_DUOC_DU_THI, ExamEligibility.check(90, 0.9, 8));
    }

    @Test
    void TC03_R3_diemBaiTapLonDuoi1_khongDuocDuThi() {
        assertEquals(ExamEligibility.Result.KHONG_DUOC_DU_THI, ExamEligibility.check(90, 8, 0.5));
    }

    @Test
    void TC04_R4_duDieuKien_duocDuThi() {
        assertEquals(ExamEligibility.Result.DUOC_DU_THI, ExamEligibility.check(85, 7, 8));
    }

    @Test
    void TC05_R4_bienDuoiCuaCaBaDieuKien_duocDuThi() {
        assertEquals(ExamEligibility.Result.DUOC_DU_THI, ExamEligibility.check(80, 1, 1));
    }

    @Test
    void TC06_R5_diemTuyetDoiCaHai_mienThi() {
        assertEquals(ExamEligibility.Result.MIEN_THI, ExamEligibility.check(80, 10, 10));
    }

    @Test
    void TC07_R4_chiGiuaKyDat10_khongMienThi() {
        assertEquals(ExamEligibility.Result.DUOC_DU_THI, ExamEligibility.check(100, 10, 9.9));
    }

    @Test
    void TC08_R4_chiBaiTapLonDat10_khongMienThi() {
        assertEquals(ExamEligibility.Result.DUOC_DU_THI, ExamEligibility.check(100, 9.9, 10));
    }

    @Test
    void TC09_R1_diemTuyetDoiNhungThieuBuoiHoc_khongDuocDuThi() {
        assertEquals(ExamEligibility.Result.KHONG_DUOC_DU_THI, ExamEligibility.check(79, 10, 10));
    }

    @Test
    void TC10_tyLeDiHocKhongHopLe_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> ExamEligibility.check(101, 5, 5));
    }

    @Test
    void TC11_diemVuotThangDiem10_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> ExamEligibility.check(90, 11, 5));
    }

    @Test
    void TC12_diemAm_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> ExamEligibility.check(90, 5, -1));
    }
}
