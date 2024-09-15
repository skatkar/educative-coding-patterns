package org.educative.dp.subsets.coinchange;

public class Leetcode322 {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        // Recursion
        //int answer = recursion(n - 1, amount, coins);

        // Memoization
        /*int[][] memo = new int[n][amount + 1];
        for(int[] subArray : memo) {
            Arrays.fill(subArray, -1);
        }
        int answer = memoization(memo, n - 1, amount, coins);*/
        int answer = dynamic(n, coins, amount);
        return answer >= Math.pow(10,9) ? -1 : answer;
    }

    private int dynamic(int n, int[] coins, int amount) {
        int[][] dp = new int[n][amount + 1];
        for(int currAmount=0; currAmount <= amount; currAmount++) {
            if(currAmount % coins[0] == 0)
                dp[0][currAmount] = currAmount / coins[0];
            else
                dp[0][currAmount] = (int)Math.pow(10, 9);
        }

        for(int index=1; index < n; index++) {
            for(int currAmount=0; currAmount <= amount; currAmount++) {
                int notPick = dp[index - 1][currAmount];
                int pick = Integer.MAX_VALUE;
                if(coins[index] <= currAmount) {
                    pick = 1 + dp[index][currAmount - coins[index]];
                }

                dp[index][currAmount] = Math.min(pick, notPick);
            }
        }


        return dp[n - 1][amount];
    }

    private int memoization(int[][] memo, int index, int amount, int[] coins) {
        // Base condition
        if(index == 0) {
            if(amount % coins[index] == 0) return amount / coins[index];
            return (int)Math.pow(10, 9);
        }

        if(memo[index][amount] != -1) return memo[index][amount];
        int notPick = recursion(index - 1, amount, coins);
        int pick = Integer.MAX_VALUE;
        if(coins[index] <= amount) {
            pick = 1 + recursion(index, amount - coins[index], coins);
        }

        memo[index][amount] = Math.min(pick, notPick);
        return memo[index][amount];
    }

    private int recursion(int index, int amount, int[] coins) {
        // Base condition
        if(index == 0) {
            if(amount % coins[index] == 0) return amount / coins[index];
            return (int)Math.pow(10, 9);
        }

        int notPick = recursion(index - 1, amount, coins);
        int pick = Integer.MAX_VALUE;
        if(coins[index] <= amount) {
            pick = 1 + recursion(index, amount - coins[index], coins);
        }

        return Math.min(pick, notPick);
    }
}
