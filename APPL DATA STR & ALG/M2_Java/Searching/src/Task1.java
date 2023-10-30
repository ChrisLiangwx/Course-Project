public class Task1 {

    public int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                //if target has been found, try to find another one on its left
                result = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                //mid is smaller than target, find in the right half
                left = mid + 1;
            } else {
                //mid is bigger than target, find in the left half
                right = mid - 1;
            }
        }

        return result;
    }

    public int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                //if target has been found, try to find another one on its right
                result = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                //mid is smaller than target, find in the right half
                left = mid + 1;
            } else {
                //mid is bigger than target, find in the left half
                right = mid - 1;
            }
        }

        return result;
    }


    public static void main(String[] args) {

        int nums [] = {4,9,10,13,17,17,19,21};
        int target = 17;
        Task1 t1 = new Task1();
        int result []= {t1.findFirstPosition(nums, target), t1.findLastPosition(nums, target)};
        System.out.println(result[0]+"  "+result[1]);


        int nums2 []= {2, 4, 6, 8, 10, 14, 16};
        int target2 = 12;
        int result2 []= {t1.findFirstPosition(nums2, target2), t1.findLastPosition(nums2, target2)};
        System.out.println(result2[0]+"  "+result2[1]);

        int nums3 []= {};
        int target3 = 0;
        int result3 []= {t1.findFirstPosition(nums3, target3), t1.findLastPosition(nums3, target3)};
        System.out.println(result3[0]+"  "+result3[1]);

        int[] nums4 = {1, 2, 2, 2, 3, 4};
        int target4 = 2;
        int result4 [] = {t1.findFirstPosition(nums4, target4), t1.findLastPosition(nums4, target4)};
        System.out.println(result4[0]+"  "+result4[1]);

        int[] nums5 = {1, 3, 5, 7, 9};
        int target5 = 5;
        int result5 [] = {t1.findFirstPosition(nums5, target5), t1.findLastPosition(nums5, target5)};
        System.out.println(result5[0]+"  "+result5[1]);
    }



}
