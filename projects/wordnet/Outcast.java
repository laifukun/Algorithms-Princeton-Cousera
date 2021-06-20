/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wdnet;

    public Outcast(WordNet wordnet)         // constructor takes a WordNet object
    {
        wdnet = wordnet;

    }

    public String outcast(String[] nouns)   // given an array of WordNet nouns, return an outcast
    {
        if (nouns == null) throw new IllegalArgumentException();
        int n = nouns.length;
        if (n < 2) throw new IllegalArgumentException();
        int minDist = Integer.MIN_VALUE;
        int minIndex = -1;
        for (int i = 0; i < n; i++) {
            int dist = 0;
            for (int j = 0; j < n; j++)
                if (j != i) dist += wdnet.distance(nouns[i], nouns[j]);
            if (dist > minDist) {
                minDist = dist;
                minIndex = i;
            }
        }
        return nouns[minIndex];
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }

    }
}
