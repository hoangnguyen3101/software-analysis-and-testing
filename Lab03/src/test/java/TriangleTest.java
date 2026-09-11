import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

// 5 test case ung voi 5 duong di doc lap P1 - P5,
// dat 100% Statement Coverage va 100% Branch Coverage
public class TriangleTest {

    @Test
    void TC01_P1_canhKhongDuong_invalid() {
        assertEquals("Invalid", Triangle.classify(0, 4, 5));
    }

    @Test
    void TC02_P2_viPhamBatDangThucTamGiac_notATriangle() {
        assertEquals("Not a triangle", Triangle.classify(1, 2, 3));
    }

    @Test
    void TC03_P3_baCanhBangNhau_equilateral() {
        assertEquals("Equilateral", Triangle.classify(3, 3, 3));
    }

    @Test
    void TC04_P4_haiCanhBangNhau_isosceles() {
        assertEquals("Isosceles", Triangle.classify(3, 3, 5));
    }

    @Test
    void TC05_P5_baCanhKhacNhau_scalene() {
        assertEquals("Scalene", Triangle.classify(3, 4, 5));
    }
}
