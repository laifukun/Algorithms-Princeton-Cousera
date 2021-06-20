/* *****************************************************************************
 *  Name: Fukun Lai
 *  Date: 09/02/2020
 *  Description: Baseball elimination
 **************************************************************************** */

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {

    private final String[] teams;

    private final int[] win;
    private final int[] loss;
    private final int[] left;
    private final int[][] game;
    private final int nTeam;
    private final int tNetGame;  // total games in flow network
    private final int s;
    private final int t;


    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);
        nTeam = Integer.parseInt(in.readLine());
        tNetGame = (nTeam - 1) * (nTeam - 2) / 2;
        teams = new String[nTeam];
        win = new int[nTeam];
        loss = new int[nTeam];
        left = new int[nTeam];
        game = new int[nTeam][nTeam];
        s = 0;
        t = (nTeam - 1) * (nTeam - 2) / 2 + (nTeam - 1) + 2 - 1;
        int i = 0;
        while (!in.isEmpty()) {
            teams[i] = in.readString();
            win[i] = in.readInt();
            loss[i] = in.readInt();
            left[i] = in.readInt();
            for (int j = 0; j < nTeam; j++)
                game[i][j] = in.readInt();
            i++;
        }
    }

    // number of teams

    public int numberOfTeams() {
        return nTeam;
    }

    // all teams
    public Iterable<String> teams() {
        Queue<String> q = new Queue<String>();
        for (int i = 0; i < nTeam; i++) q.enqueue(teams[i]);
        return q;
    }

    // number of wins for given team
    public int wins(String team) {
        return win[teamIndex(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        return loss[teamIndex(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        return left[teamIndex(team)];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (nTeam == 1) return 0;
        int t1num = teamIndex(team1);
        int t2num = teamIndex(team2);

        return game[t1num][t2num];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        int teamInd = teamIndex(team);
        if (trivialEliminate(teamInd) != -1) return true;
        double fullflow = 0;
        for (int i = 0; i < nTeam; i++) {
            if (i == teamInd) continue;
            for (int j = i + 1; j < nTeam; j++) {
                if (j == teamInd) continue;
                fullflow += game[i][j];
            }
        }
        FlowNetwork gNet = gameNetwork(team);
        FordFulkerson ff = new FordFulkerson(gNet, s, t);
        double max_flow = ff.value();
        if (fullflow > max_flow) return true;
        else return false;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        int teamID = teamIndex(team);
        Queue<String> q = new Queue<String>();
        int trivial = trivialEliminate(teamID);
        if (trivial != -1) {
            q.enqueue(this.teams[trivial]);
            return q;
        }

        FlowNetwork gNet = gameNetwork(team);
        FordFulkerson ff = new FordFulkerson(gNet, s, t);
        for (int i = 0; i < nTeam; i++) {
            if (i == teamID) continue;
            int tVertex = teamVertex(i, teamID);
            if (ff.inCut(tVertex)) q.enqueue(this.teams[i]);
        }
        if (q.isEmpty()) return null;
        return q;
    }

    private int trivialEliminate(int x) {
        for (int i = 0; i < nTeam; i++) {
            if (win[x] + left[x] < win[i]) return i;
        }
        return -1;
    }

    private FlowNetwork gameNetwork(String team) {
        int teamID = teamIndex(team);

        FlowNetwork gNet = new FlowNetwork(t + 1);

        for (int i = 0; i < nTeam; i++) {
            if (i == teamID) continue;
            int t1Vertex = teamVertex(i, teamID);
            for (int j = i + 1; j < nTeam; j++) {
                if (j == teamID) continue;
                int t2Vertex = teamVertex(j, teamID);
                int gVertex = gameVertex(i, j, teamID);
                FlowEdge e1 = new FlowEdge(s, gVertex, game[i][j]);
                gNet.addEdge(e1);
                FlowEdge e2 = new FlowEdge(gVertex, t1Vertex, Integer.MAX_VALUE);
                gNet.addEdge(e2);
                FlowEdge e3 = new FlowEdge(gVertex, t2Vertex, Integer.MAX_VALUE);
                gNet.addEdge(e3);
            }
            FlowEdge e4 = new FlowEdge(t1Vertex, t, win[teamID] + left[teamID] - win[i]);
            gNet.addEdge(e4);
        }

        return gNet;
    }

    private int teamIndex(String team) {
        for (int i = 0; i < nTeam; i++)
            if (this.teams[i].equals(team)) return i;
        throw new IllegalArgumentException();
    }

    private int teamVertex(int i, int teamTestID) {
        if (i == teamTestID) return -1;
        if (i > teamTestID) i--;
        return tNetGame + i + 1;
    }

    private int gameVertex(int i, int j, int teamTestID) {
        if (i == teamTestID || j == teamTestID) return -1;
        if (i > teamTestID) i--;
        if (j > teamTestID) j--;

        return i * (nTeam - 1) + j - (i + 2) * (i + 1) / 2 + 1;
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
