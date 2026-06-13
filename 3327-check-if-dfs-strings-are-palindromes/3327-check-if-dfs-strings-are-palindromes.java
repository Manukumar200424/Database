import java.util.*;

class Solution {

    List<Integer>[] tree;
    String s;
    StringBuilder dfsStr = new StringBuilder();

    int[] start;
    int[] end;

    long[] hash;
    long[] revHash;
    long[] pow;

    long MOD = 1000000007L;
    long BASE = 911382323L;

    public boolean[] findAnswer(int[] parent, String s) {
        int n = parent.length;
        this.s = s;

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        for (int i = 0; i < n; i++) {
            Collections.sort(tree[i]);
        }

        start = new int[n];
        end = new int[n];

        dfs(0);

        String str = dfsStr.toString();
        int m = str.length();

        hash = new long[m + 1];
        revHash = new long[m + 1];
        pow = new long[m + 1];

        pow[0] = 1;

        for (int i = 0; i < m; i++) {
            pow[i + 1] = (pow[i] * BASE) % MOD;
            hash[i + 1] =
                (hash[i] * BASE + str.charAt(i)) % MOD;
        }

        String rev = new StringBuilder(str).reverse().toString();

        for (int i = 0; i < m; i++) {
            revHash[i + 1] =
                (revHash[i] * BASE + rev.charAt(i)) % MOD;
        }

        boolean[] ans = new boolean[n];

        for (int i = 0; i < n; i++) {
            ans[i] = isPalindrome(start[i], end[i], m);
        }

        return ans;
    }

    private void dfs(int node) {

        start[node] = dfsStr.length();

        for (int child : tree[node]) {
            dfs(child);
        }

        dfsStr.append(s.charAt(node));

        end[node] = dfsStr.length() - 1;
    }

    private boolean isPalindrome(int l, int r, int n) {

        long forward = getHash(hash, l, r);

        int rl = n - 1 - r;
        int rr = n - 1 - l;

        long backward = getHash(revHash, rl, rr);

        return forward == backward;
    }

    private long getHash(long[] pref, int l, int r) {

        long res =
            (pref[r + 1]
             - (pref[l] * pow[r - l + 1]) % MOD
             + MOD) % MOD;

        return res;
    }
}
