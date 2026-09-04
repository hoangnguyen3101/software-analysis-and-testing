import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TrainTicketTest {

    // ----- Gia tri bien (Boundary Value Analysis) -----

    @Test
    void TC01_bienDuoiCuaNgay_00h00_veThuong() {
        assertEquals(TrainTicket.TicketType.VE_THUONG, TrainTicket.getTicketType("00:00"));
    }

    @Test
    void TC02_ngayTruocMoc9h30_veThuong() {
        assertEquals(TrainTicket.TicketType.VE_THUONG, TrainTicket.getTicketType("09:29"));
    }

    @Test
    void TC03_dungMoc9h30_veTietKiem() {
        assertEquals(TrainTicket.TicketType.VE_TIET_KIEM, TrainTicket.getTicketType("09:30"));
    }

    @Test
    void TC04_ngaySauMoc9h30_veTietKiem() {
        assertEquals(TrainTicket.TicketType.VE_TIET_KIEM, TrainTicket.getTicketType("09:31"));
    }

    @Test
    void TC05_ngayTruocMoc16h00_veTietKiem() {
        assertEquals(TrainTicket.TicketType.VE_TIET_KIEM, TrainTicket.getTicketType("15:59"));
    }

    @Test
    void TC06_dungMoc16h00_veTietKiem() {
        assertEquals(TrainTicket.TicketType.VE_TIET_KIEM, TrainTicket.getTicketType("16:00"));
    }

    @Test
    void TC07_ngaySauMoc16h00_veThuong() {
        assertEquals(TrainTicket.TicketType.VE_THUONG, TrainTicket.getTicketType("16:01"));
    }

    @Test
    void TC08_ngayTruocMoc19h30_veThuong() {
        assertEquals(TrainTicket.TicketType.VE_THUONG, TrainTicket.getTicketType("19:29"));
    }

    @Test
    void TC09_dungMoc19h30_veThuong() {
        assertEquals(TrainTicket.TicketType.VE_THUONG, TrainTicket.getTicketType("19:30"));
    }

    @Test
    void TC10_ngaySauMoc19h30_veTietKiem() {
        assertEquals(TrainTicket.TicketType.VE_TIET_KIEM, TrainTicket.getTicketType("19:31"));
    }

    @Test
    void TC11_bienTrenCuaNgay_23h59_veTietKiem() {
        assertEquals(TrainTicket.TicketType.VE_TIET_KIEM, TrainTicket.getTicketType("23:59"));
    }

    // ----- Vung tuong duong hop le (Equivalence Partitioning) -----

    @Test
    void TC12_vungV1_sangSom_veThuong() {
        assertEquals(TrainTicket.TicketType.VE_THUONG, TrainTicket.getTicketType("07:00"));
    }

    @Test
    void TC13_vungV2_giuaTrua_veTietKiem() {
        assertEquals(TrainTicket.TicketType.VE_TIET_KIEM, TrainTicket.getTicketType("12:00"));
    }

    @Test
    void TC14_vungV3_chieuToiCaoDiem_veThuong() {
        assertEquals(TrainTicket.TicketType.VE_THUONG, TrainTicket.getTicketType("17:45"));
    }

    @Test
    void TC15_vungV4_toiMuon_veTietKiem() {
        assertEquals(TrainTicket.TicketType.VE_TIET_KIEM, TrainTicket.getTicketType("21:00"));
    }

    // ----- Vung tuong duong khong hop le -----

    @Test
    void TC16_gioVuotBienTren_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> TrainTicket.getTicketType("24:00"));
    }

    @Test
    void TC17_gioAm_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> TrainTicket.getTicketType(-1, 0));
    }

    @Test
    void TC18_phutVuotBienTren_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> TrainTicket.getTicketType(10, 60));
    }

    @Test
    void TC19_phutAm_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> TrainTicket.getTicketType(10, -1));
    }

    @Test
    void TC20_chuoiSaiDinhDang_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> TrainTicket.getTicketType("9h30"));
    }

    @Test
    void TC21_chuoiRong_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> TrainTicket.getTicketType(""));
    }

    @Test
    void TC22_giaTriNull_baoLoi() {
        assertThrows(IllegalArgumentException.class, () -> TrainTicket.getTicketType((String) null));
    }
}
