给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 
返回你可以获得的最大乘积。
方法一：
class Solution {
    public int integerBreak(int n) {
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=1;j<i;j++){
                dp[i]=Math.max(dp[i],dp[j]*(i-j));
                dp[i]=Math.max(dp[i],j*(i-j));
            }
        }
        return dp[n];
    }
}

方法二：
class Solution {
    public int integerBreak(int n) {
        if(n<3){
            return 1;
        }if(n==3){
            return 2;
        }
        int a=n/3;
        int b=n%3;
        if(b==0) return (int)Math.pow(3,a);
        if(b==1) return (int)Math.pow(3,a-1)*4;
        return (int)Math.pow(3,a)*2;
    }
}

如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。

例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。

给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/wiggle-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
方法一：
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] up=new int[nums.length];
        int[] down=new int[nums.length];
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    up[i]=Math.max(up[i],down[j]+1);
                }else if(nums[i]<nums[j]){
                    down[i]=Math.max(down[i],up[j]+1);
                }
            }
        }
        return 1+Math.max(up[nums.length-1],down[nums.length-1]);
    }
}
方法二：
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] up=new int[nums.length];
        int[] down=new int[nums.length];
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                up[i]=down[i-1]+1;
                down[i]=down[i-1];
            }else if(nums[i]<nums[i-1]){
                down[i]=up[i-1]+1;
                up[i]=up[i-1];
            }else{
                down[i]=down[i-1];
                up[i]=up[i-1];
            }
        }
        return 1+Math.max(up[nums.length-1],down[nums.length-1]);
    }
}
方法三：
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int up=1;
        int down=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                up=down+1;
            }if(nums[i]<nums[i-1]){
                down=up+1;
            }
        }
        return Math.max(up,down);
    }
}

给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
class Solution {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int i:nums){
            sum+=i;
        }
        if(sum%2!=0){
            return false;
        }
        sum/=2;
        boolean[] dp=new boolean[sum+1];
        dp[0]=true;
        for(int i=0;i<nums.length;i++){
            for(int j=sum;j>=nums[i];j--){
                dp[j]=dp[j]||dp[j-nums[i]];
            }
        }
        return dp[sum];
    }
}

