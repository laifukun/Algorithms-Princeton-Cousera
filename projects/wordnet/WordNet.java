/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

public class WordNet {
    private final HashMap<String, Bag<Integer>> synset;
    private final HashMap<Integer, String> synsetID;
    private final Digraph hypernymG;
    // private final int nID;
    private final SAP hypernymSAP;


    // constructor takes the name of the two input files

    /**
     * @param synsets
     * @param hypernyms
     */
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException();
        synset = new HashMap<String, Bag<Integer>>();
        synsetID = new HashMap<Integer, String>();
        In synIn = new In(synsets);
        int nID = 0;
        while (!synIn.isEmpty()) {
            String line = synIn.readLine();
            String[] tokens = line.split(",");
            int v = Integer.parseInt(tokens[0]);
            String[] syns = tokens[1].split(" ");
            synsetID.put(v, tokens[1]);
            for (String s : syns) {
                if (synset.containsKey(s)) {
                    Bag<Integer> sID = synset.get(s);
                    sID.add(v);
                    synset.put(s, sID);
                }
                else {
                    Bag<Integer> sID = new Bag<Integer>();
                    sID.add(v);
                    synset.put(s, sID);
                }

            }
            ++nID;
        }

        hypernymG = new Digraph(nID);
        In hyperIn = new In(hypernyms);
        while (!hyperIn.isEmpty()) {
            String line = hyperIn.readLine();
            String[] tokens = line.split(",");
            int v = Integer.parseInt(tokens[0]);
            for (String s : tokens) {
                int w = Integer.parseInt(s);
                if (v != w) hypernymG.addEdge(v, w);
            }
        }

        if (!isRootedDAG()) throw new IllegalArgumentException();

        hypernymSAP = new SAP(hypernymG);
    }

    // returns all WordNet nouns

    /**
     * returns all WordNet nouns;
     *
     * @return
     */
    public Iterable<String> nouns() {
        return synset.keySet();
    }


    /**
     * is the word a WordNet noun?
     *
     * @param word
     * @return
     */
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return synset.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();
        if (!isNoun(nounA)) throw new IllegalArgumentException();
        if (!isNoun(nounB)) throw new IllegalArgumentException();
        Bag<Integer> idA = synset.get(nounA);
        Bag<Integer> idB = synset.get(nounB);

        return hypernymSAP.length(idA, idB);
    }

    /**
     * check if hypernym is rooted Dyrected acyclic graph;
     *
     * @return
     */
    private boolean isRootedDAG() {

        DirectedCycle dc = new DirectedCycle(hypernymG);
        if (dc.hasCycle()) return false;
        int count = 0;
        for (int v = 0; v < hypernymG.V() && count < 2; v++)
            if (hypernymG.outdegree(v) == 0) {
                //     StdOut.println("v: " + v + " outdegree: " + hypernymG.outdegree(v));
                count++;
            }
        if (count > 1) return false;
        return true;

    }


    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();
        if (!isNoun(nounA)) throw new IllegalArgumentException();
        if (!isNoun(nounB)) throw new IllegalArgumentException();
        Bag<Integer> idA = synset.get(nounA);
        Bag<Integer> idB = synset.get(nounB);
        int ancestorID = hypernymSAP.ancestor(idA, idB);
        if (ancestorID == -1) return null;

        return synsetID.get(ancestorID);
    }
/*
    private void resizeBag(int n) {
        Bag<String>[] tempbag = (Bag<String>[]) new Bag[n];
        for (int i = 0; i < synsetBag.length; i++)
            tempbag[i] = synsetBag[i];
        synsetBag = tempbag;
    }

 */


    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);

        StdOut.println(wordnet.isRootedDAG());

        String nounA = "thing";
        String nounB = "whopper";
        StdOut.println(wordnet.distance(nounA, nounB));
        StdOut.println(wordnet.sap(nounA, nounB));

    }
}
