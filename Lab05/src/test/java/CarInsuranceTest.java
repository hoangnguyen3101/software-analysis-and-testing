import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


// Bai 2 - test theo quy tac cua bang quyet dinh + gia tri bien 24/25/64/65
public class CarInsuranceTest {

    @Test
    void TC01_R1_nu_24tuoi_500() {
        assertEquals(500, CarInsurance.premium(CarInsurance.Gender.NU, 24));
    }

    @Test
    void TC02_R3_nu_25tuoi_500() {
        assertEquals(500, CarInsurance.premium(CarInsurance.Gender.NU, 25));
    }

    @Test
    void TC03_R3_nu_64tuoi_500() {
        assertEquals(500, CarInsurance.premium(CarInsurance.Gender.NU, 64));
    }

    @Test
    void TC04_R5_nu_65tuoi_1500() {
        assertEquals(1500, CarInsurance.premium(CarInsurance.Gender.NU, 65));
    }

    @Test
    void TC05_R2_nam_24tuoi_3000() {
        assertEquals(3000, CarInsurance.premium(CarInsurance.Gender.NAM, 24));
    }

    @Test
    void TC06_R4_nam_25tuoi_1000() {
        assertEquals(1000, CarInsurance.premium(CarInsurance.Gender.NAM, 25));
    }

    @Test
    void TC07_R4_nam_64tuoi_1000() {
        assertEquals(1000, CarInsurance.premium(CarInsurance.Gender.NAM, 64));
    }

    @Test
    void TC08_R6_nam_65tuoi_1500() {
        assertEquals(1500, CarInsurance.premium(CarInsurance.Gender.NAM, 65));
    }

    @Test
    void TC09_bienDuoiHopLe_18tuoi() {
        assertEquals(3000, CarInsurance.premium(CarInsurance.Gender.NAM, 18));
        assertEquals(500, CarInsurance.premium(CarInsurance.Gender.NU, 18));
    }

    @Test
    void TC10_tuoiNhoHonBienDuoi_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> CarInsurance.premium(CarInsurance.Gender.NAM, 17));
    }

    @Test
    void TC11_tuoiVuotBienTren_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> CarInsurance.premium(CarInsurance.Gender.NU, 121));
    }

    @Test
    void TC12_gioiTinhNull_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> CarInsurance.premium(null, 30));
    }
}
