public class HotorCold {
    public static int findSecret(int N) {
        int secret = 1;//StdRandom.uniform(1, N);
        int i = 1;
        int j = N;
        int measure = Integer.MAX_VALUE;
        int cnt = 0;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (mid == secret) return mid;
            else if (mid < secret) i = mid + 1;
            else if (mid > secret) j = mid - 1;
            cnt++;
            StdOut.println("Number of searchs: " + cnt);
        }
        return -1;

    }

    public static void main(String[] args) {

        int N = StdIn.readInt();

        StdOut.println("Secret number is: " + findSecret(N));

    }
}
