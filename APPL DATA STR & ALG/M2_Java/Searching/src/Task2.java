import java.util.Arrays;

public class Task2 {
    public boolean binarySearch(int[][] matrix, int target){

        int outerLeft = 0;
        int outerRight = matrix.length - 1;
        int innerLength = matrix[0].length;
        int midArray[] = new int[innerLength];
        int innerLeft = 0;
        int innerRight = innerLength - 1;

        OuterLoop:
        while(outerLeft <= outerRight){
            int outerMid = outerLeft + (outerRight - outerLeft)/2;
            //midArray is an array storing values possibly including target
            midArray = matrix[outerMid];

            if(midArray[innerLength - 1] < target){
                //inner array nums all are smaller than target, try to find target in right side
                outerLeft = outerMid + 1;
            }else if(midArray[0] > target){
                //inner array nums all are bigger than target, try to find target in left side
                outerRight = outerMid - 1;
            }else{
                //try to find target the inner array
                while(innerLeft <= innerRight){
                    int innerMid = innerLeft + (innerRight - innerLeft)/2;
                    if(midArray[innerMid] == target){
                        //target has been found
                        return true;
                    }else if(midArray[innerMid] < target){
                        //try to find in right
                        innerLeft = innerMid + 1;
                    }else{
                        //try to find in left
                        innerRight = innerMid - 1;
                    }
                }
                return false;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        Task2 t2 = new Task2();

        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 3;
        System.out.println(t2.binarySearch(matrix, target));

        int[][] matrix2 = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        int target2 = 13;
        System.out.println(t2.binarySearch(matrix2, target2));

        int[][] matrix3 = {
                {2, 4, 6},
                {8, 10, 12},
                {14, 16, 18},
                {20, 22, 24}
        };
        int target3 = 11;
        System.out.println(t2.binarySearch(matrix3, target3));

        int[][] matrix4 = {
                {1, 3, 5},
                {7, 9, 11},
                {13, 15, 17},
                {19, 21, 23}
        };
        int target4 = 17;
        System.out.println(t2.binarySearch(matrix4, target4));

    }
}

