class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        TreeMap<Integer, Integer> window = new TreeMap<>();
        int ans = Integer.MAX_VALUE;

        for (int j = l; j <= n; j++) {
            window.merge(prefix[j - l], 1, Integer::sum);
            int old = j - r - 1;
            if (old >= 0) {
                window.merge(prefix[old], -1, Integer::sum);
                if (window.get(prefix[old]) == 0) window.remove(prefix[old]);
            }
            Integer lower = window.lowerKey(prefix[j]);
            if (lower != null) {
                ans = Math.min(ans, prefix[j] - lower);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}