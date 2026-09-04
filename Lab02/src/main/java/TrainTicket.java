public class TrainTicket {

    public enum TicketType {
        VE_THUONG("Ve thuong"),
        VE_TIET_KIEM("Ve tiet kiem");

        private final String label;

        TicketType(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    // 09:30 - moc bat dau vung ve tiet kiem buoi sang
    public static final int SAVER_MORNING_START = 9 * 60 + 30;

    // 16:00 - moc cuoi cung con duoc mua ve tiet kiem buoi chieu
    public static final int SAVER_AFTERNOON_END = 16 * 60;

    // 19:30 - moc cuoi cung cua gio cao diem buoi toi
    public static final int PEAK_EVENING_END = 19 * 60 + 30;

    public static TicketType getTicketType(int hour, int minute) {
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Thoi gian khong hop le: " + hour + ":" + minute);
        }

        int time = hour * 60 + minute;

        if (time < SAVER_MORNING_START) {
            return TicketType.VE_THUONG;        // 00:00 - 09:29
        }

        if (time <= SAVER_AFTERNOON_END) {
            return TicketType.VE_TIET_KIEM;     // 09:30 - 16:00
        }

        if (time <= PEAK_EVENING_END) {
            return TicketType.VE_THUONG;        // 16:01 - 19:30
        }

        return TicketType.VE_TIET_KIEM;         // 19:31 - 23:59
    }

    public static TicketType getTicketType(String time) {
        if (time == null) {
            throw new IllegalArgumentException("Thoi gian khong duoc rong");
        }

        String[] parts = time.trim().split(":");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Sai dinh dang thoi gian HH:mm: " + time);
        }

        int hour;
        int minute;

        try {
            hour = Integer.parseInt(parts[0]);
            minute = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Sai dinh dang thoi gian HH:mm: " + time);
        }

        return getTicketType(hour, minute);
    }

    public static void main(String[] args) {
        String[] departures = {"00:00", "09:29", "09:30", "16:00", "16:01", "19:30", "19:31", "23:59"};

        for (String departure : departures) {
            System.out.println(departure + " -> " + getTicketType(departure).getLabel());
        }
    }
}
