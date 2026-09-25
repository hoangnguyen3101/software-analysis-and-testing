/**
 * Bai 3 - Kiem tra dieu kien du thi cua sinh vien.
 *
 * Luat (theo bang quyet dinh trong docs/Bai03-Bang-quyet-dinh-dieu-kien-du-thi.md):
 *   Du dieu kien du thi  : di hoc >= 80% so buoi VA diem giua ky >= 1 VA diem BTL >= 1
 *   Duoc mien thi        : du dieu kien du thi VA diem giua ky = 10 VA diem BTL = 10
 */
public class ExamEligibility {

    public enum Result {
        MIEN_THI("Duoc mien thi"),
        DUOC_DU_THI("Duoc du thi"),
        KHONG_DUOC_DU_THI("Khong duoc du thi");

        private final String label;

        Result(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public static final double MIN_ATTENDANCE = 80.0;   // % so buoi toi thieu
    public static final double MIN_SCORE = 1.0;         // diem toi thieu de du thi
    public static final double MAX_SCORE = 10.0;        // diem toi da (moc mien thi)

    public static Result check(double attendancePercent, double midterm, double project) {
        if (attendancePercent < 0 || attendancePercent > 100) {
            throw new IllegalArgumentException("Ty le di hoc khong hop le: " + attendancePercent);
        }

        if (midterm < 0 || midterm > MAX_SCORE) {
            throw new IllegalArgumentException("Diem giua ky khong hop le: " + midterm);
        }

        if (project < 0 || project > MAX_SCORE) {
            throw new IllegalArgumentException("Diem bai tap lon khong hop le: " + project);
        }

        boolean duDieuKien = attendancePercent >= MIN_ATTENDANCE
                && midterm >= MIN_SCORE
                && project >= MIN_SCORE;

        if (!duDieuKien) {
            return Result.KHONG_DUOC_DU_THI;            // R1, R2, R3
        }

        if (midterm == MAX_SCORE && project == MAX_SCORE) {
            return Result.MIEN_THI;                     // R5
        }

        return Result.DUOC_DU_THI;                      // R4
    }

    public static void main(String[] args) {
        System.out.println("(75%, 5, 5)    -> " + check(75, 5, 5).getLabel());
        System.out.println("(80%, 5, 5)    -> " + check(80, 5, 5).getLabel());
        System.out.println("(80%, 10, 10)  -> " + check(80, 10, 10).getLabel());
    }
}
