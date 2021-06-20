public class eggThrow {

    public static int lgNThrow(int N, int F) {

        //int F = StdRandom.uniform(N);
        assert F > 0;

        StdOut.println("The first floor with egg broken: " + F);
        int i = 1;
        int j = N;
        int cnt = 0;
        int firstBrokenFloor = 1;

        //int lastBrokenFloor = N + 1;
        while (i < j) {

            int mid = i + (j - i) / 2;
            //if (mid == F) return cnt;
            if (mid >= F) {
                j = mid;
                cnt++;
                firstBrokenFloor = mid;
            }
            else if (mid != N && mid < F) {
                i = mid + 1;

            }

        }
        StdOut.println("The first floor with egg broken: " + firstBrokenFloor);


        return cnt;

    }

    public static int lgFThrow(int N, int F) {
        //exersice 1.4.24
        //int F = StdRandom.uniform(N);
        assert F > 0;
        StdOut.println("The first floor with egg broken: " + F);
        int firstBrokenFloor = 1;
        int cnt = 0;
        // try trow egg every 2^i floor until first egg broken
        while (firstBrokenFloor <= F) firstBrokenFloor *= 2;

        cnt++;
        int i = 1;
        int j = firstBrokenFloor;
        //binary search down from the tested floor of egg broken
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (mid >= F) {
                j = mid;
                cnt++;
                firstBrokenFloor = mid;
            }
            else if (mid != N && mid < F) i = mid + 1;

        }
        StdOut.println("The first floor with egg broken: " + firstBrokenFloor);
        return cnt;

    }

    public static int EggThrow(int e, int N) {

        // minimum number of throws for e eggs from N floors
        if (e == 1) return N;
        if (N == 0) return 0;
        if (N == 1) return 1;

        int MINThrow = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++)
            MINThrow = Math.min(MINThrow, 1 + Math.max(EggThrow(e - 1, i - 1), EggThrow(e, N - i)));

        return MINThrow;


    }

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        int F = StdRandom.uniform(N);

        StdOut.println("Number of broken egg: " + lgNThrow(N, F));
        StdOut.println("Number of broken egg: " + lgFThrow(N, F));
        StdOut.println("Max number of eggs: " + (int) (Math.log(N) / Math.log(2)) + ", "
                               + (int) (Math.log(F) / Math.log(2)));

        StdOut.println("Minmum Throws for two eggs and " + N + " floors: " + EggThrow(2, N));


    }
}
