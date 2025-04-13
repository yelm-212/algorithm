import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        } 

        int[] res = new int[N + 1];  // 초기값 0을 포함하기 위해 크기를 N+1로 설정
        res[0] = 0;  // 초기값 0 설정
        int len = 1;  // 초기값 0이 들어있으므로 길이를 1부터 시작

        for (int i = 0; i < N; i++) {
            if (res[len - 1] < arr[i]) {
                // 현재 원소가 res 배열의 마지막 값보다 크면 추가
                res[len] = arr[i];
                len++;
            } else {
                // 이진 탐색으로 적절한 위치 찾기
                int left = 0;
                int right = len;
                
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (res[mid] < arr[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                
                // 찾은 위치에 현재 값 삽입
                res[right] = arr[i];
            }
        }

        System.out.println(len - 1);  // 초기값 0을 제외한 길이 출력
    }
}