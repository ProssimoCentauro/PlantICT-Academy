public class MaxElem {
    static int max(int[] arr, boolean flag) {
        int res;
        if (flag) {
            res = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > res) {
                    res = arr[i];
                }
            }
        }
        else {
            res = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < res) {
                    res = arr[i];
                }
            }
        }
        return (res);
    }
    public static void main(String[] args) {
        System.out.println(max(new int[]{-98, 20, 123, 44, 15}, true));
        System.out.println(max(new int[]{-98, 20, 123, 44, 15}, false));
    }
}
