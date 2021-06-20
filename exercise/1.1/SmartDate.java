public class SmartDate implements Comparable<SmartDate> {
    private final int month;
    private final int day;
    private final int year;

    public SmartDate(int m, int d, int y) {
        if (m <= 12 && m > 0 && d <= 31 && d > 0 && y > 0) {
            year = y;
            month = m;
            day = d;
        }
        else
            throw new RuntimeException("Illege date!");
    }

    public SmartDate(String date) {
        //String[] mdy = date.split("/");
        this(Integer.parseInt(date.split("/")[0]), Integer.parseInt(date.split("/")[1]),
             Integer.parseInt(date.split("/")[2]));

    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    public String toString() {
        return month() + "/" + day() + "/" + year();
    }

    public boolean equals(Object that) {

        if (this == that) return true;
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        SmartDate x = (SmartDate) that;
        return this.day == x.day && this.month == x.month && this.year == x.year;
    }

    public int compareTo(SmartDate that) {
        if (this.equals(that)) return 0;
        else if (year > that.year()) return 1;
        else if (year < that.year()) return -1;
        else if (year == that.year()) {
            if (month > that.month()) return 1;
            else if (month < that.month()) return -1;
            else if (month == that.month()) {
                return day > that.day() ? 1 : -1;
            }
        }
        return 0;
    }

    public String dayofweek() {
        int c = year / 100;
        int w = (day + (int) (2.6 * month - 0.2) + year + (year / 4) + (c / 4)
                - 2 * c) % 7;
        switch (w) {

            case 0:
                return "Sunday";
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
        }
        return "NA";
    }

    public static void main(String[] args) {

        SmartDate sd = new SmartDate("5/1/1985");
        StdOut.println(sd.dayofweek());

    }
}
