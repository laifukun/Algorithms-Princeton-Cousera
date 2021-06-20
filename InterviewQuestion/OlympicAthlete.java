/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class OlympicAthlete {
    private final String name;
    private final int num;
    private final String type;

    OlympicAthlete(String nm, int num, String type) {
        this.name = nm;
        this.num = num;
        this.type = type;
    }

    public int hashCode() {
        return name.hashCode() + num;
    }

    public static void main(String[] args) {
        OlympicAthlete oa1 = new OlympicAthlete("He", 1, "Jump");
        OlympicAthlete oa2 = new OlympicAthlete("He", 1, "Jump");

        StdOut.println("Athlete 1 hash code: " + oa1.hashCode());
        StdOut.println("Athlete 2 hash code: " + oa2.hashCode());
        StdOut.println(oa1.equals(oa2));
    }
}
