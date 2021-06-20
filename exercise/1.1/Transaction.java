public class Transaction implements Comparable<Transaction> {
    private final String who;
    private final SmartDate when;
    private final double amount;

    Transaction(String iwho, SmartDate iwhen, double iamount) {
        who = iwho;
        when = iwhen;
        amount = iamount;
    }

    Transaction(String transaction) {
        String[] trans = transaction.split(",");
        who = trans[0];
        when = new SmartDate(trans[1]);
        amount = Double.parseDouble(trans[2]);
    }

    public String who() {
        return who;
    }

    public SmartDate when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    public String toString() {
        return who + ", " + when + ", " + amount;
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        Transaction x = (Transaction) that;

        return this.who == x.who && this.when == x.when && this.amount == x.amount;
    }

    public int compareTo(Transaction that) {
        if (this.equals(that)) return 0;
        return this.amount > that.amount() ? 1 : -1;
    }

    public static void main(String[] args) {

    }
}
