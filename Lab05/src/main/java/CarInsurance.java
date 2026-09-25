/**
 * Bai 2 - Phi bao hiem o to theo gioi tinh va tuoi.
 *
 * Luat (theo bang quyet dinh trong docs/Bai02-Bang-quyet-dinh-bao-hiem-o-to.md):
 *   R1: nu,  18 - 24   -> 500
 *   R2: nam, 18 - 24   -> 3000
 *   R3: nu,  25 - 64   -> 500
 *   R4: nam, 25 - 64   -> 1000
 *   R5: nu,  >= 65     -> 1500
 *   R6: nam, >= 65     -> 1500
 */
public class CarInsurance {

    public enum Gender {
        NAM("Nam"),
        NU("Nu");

        private final String label;

        Gender(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    // Tuoi hop le: tu tuoi duoc cap bang lai den 120
    public static final int MIN_AGE = 18;
    public static final int MAX_AGE = 120;

    public static final int NGUOI_CAO_TUOI = 65;    // moc "tu 65 tuoi tro len"
    public static final int NGUOI_TRE = 25;         // moc "duoi 25 tuoi"

    public static final int PHI_NU = 500;
    public static final int PHI_NAM_TRE = 3000;
    public static final int PHI_NAM_TRUNG_NIEN = 1000;
    public static final int PHI_CAO_TUOI = 1500;

    public static int premium(Gender gender, int age) {
        if (gender == null) {
            throw new IllegalArgumentException("Gioi tinh khong duoc null");
        }

        if (age < MIN_AGE || age > MAX_AGE) {
            throw new IllegalArgumentException("Tuoi khong hop le: " + age);
        }

        if (age >= NGUOI_CAO_TUOI) {
            return PHI_CAO_TUOI;                    // R5, R6
        }

        if (gender == Gender.NU) {
            return PHI_NU;                          // R1, R3
        }

        if (age < NGUOI_TRE) {
            return PHI_NAM_TRE;                     // R2
        }

        return PHI_NAM_TRUNG_NIEN;                  // R4
    }

    public static void main(String[] args) {
        int[] ages = {24, 25, 64, 65};

        for (Gender g : Gender.values()) {
            for (int age : ages) {
                System.out.println(g.getLabel() + ", " + age + " tuoi -> $" + premium(g, age));
            }
        }
    }
}
