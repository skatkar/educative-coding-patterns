package org.grokking.dp.string;

public class LongestCommonSubstring {

    public static void main(String[] args) {
        LongestCommonSubstring q = new LongestCommonSubstring();
        q.findLPSLength("cddpd");
    }

    public int findLPSLength(String st) {
        StringBuilder sb = new StringBuilder(st);
        int n = st.length();
        return commonSubStr(st, sb.reverse().toString(), n, n);
    }

    /**
     * This is based on Leetcode 1143.
     * @param X - First string
     * @param Y - Second string
     * @param m - Length of the first string
     * @param n - Length of the second string
     * @return - Longest common substring length
     */
    public int commonSubStr(String X, String Y, int m, int n) {
        int maxLength = 0;
        int[][] dp = new int[m + 1][n + 1];

        for(int i=0; i <= m; i++){
            for(int j=0; j <= n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                }else if(X.charAt(i - 1) == Y.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        display(dp);
        return maxLength;
    }

    private void display(int[][] dp) {
        for (int[] ints : dp) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
