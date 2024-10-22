import java.util.*;
import java.util.stream.*;

public class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> s = new Stack<>();
        s.add(arr[0]); // 첫번째건 무저건 너음

        for (int i = 1 ; i < arr.length ; i++) {
            if (s.peek() != arr[i]) {
                s.add(arr[i]);
            }
        }
        
        // List<Integer>를 int[]로 변환
        int[] result = s.stream().mapToInt(i -> i).toArray();

        return result;
    }
}
