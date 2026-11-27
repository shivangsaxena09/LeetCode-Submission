class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }
        int[] multiplesCount = new int[maxFactor + 1];
        for (int d = 1; d <= maxFactor; d++) {
            for (int mult = d; mult <= maxFactor; mult += d) {
                multiplesCount[d] += freq[mult];
            }
        }
        int[] dp = new int[budget + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            if (price > budget) {
                continue;
            }

            int firstBuyGain = 1 + (multiplesCount[factor] - 1);
            for (int b = budget; b >= price; b--) {
                if (dp[b - price] + firstBuyGain > dp[b]) {
                    dp[b] = dp[b - price] + firstBuyGain;
                }
            }
            for (int b = price; b <= budget; b++) {
                if (dp[b - price] + 1 > dp[b]) {
                    dp[b] = dp[b - price] + 1;
                }
            }
        }

        int ans = 0;
        for (int copies : dp) {
            if (copies > ans) {
                ans = copies;
            }
        }
        return ans;
    }
}