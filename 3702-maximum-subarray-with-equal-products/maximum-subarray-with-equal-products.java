class Solution {
    public int maxLength(int[] nums) {
        int maxLen = 2; 
        int left = 0;
        long prod = 1;

        for (int right = 0; right < nums.length; right++) {
            while (gcd(prod, nums[right]) > 1) {
                prod /= nums[left];
                left++;
            }
            
            prod *= nums[right];
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}