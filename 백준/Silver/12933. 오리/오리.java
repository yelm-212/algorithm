import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // 풀이 참고해서 작성함
    static final char[] quack = {'q','u','a','c','k'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sound = br.readLine();

        if (sound.length() % 5 != 0) {
            System.out.println(-1);
            return;
        }

        int remain = sound.length(), cnt = 0;
        char[] soundArr = sound.toCharArray();

        while (remain != 0){ // 길이가 0이 될때까지
            int point = 0, idx = 0;
            boolean flag = false;
            int[] tmp = new int[5];

            while (idx < soundArr.length){
                if (soundArr[idx] == quack[point]){
                    tmp[point++] = idx; // 후보 인덱스 값 저장함
                    if (point == 5){ // 마지막 글자 도달
                        flag = true; // 결과값 올려줌
                        remain -= 5;
                        point = 0;
                        for(int i = 0; i < 5 ; i++){
                            soundArr[tmp[i]] = '\0';
                        }
                    }
                }
                idx++;
            }

            if (flag) cnt++;
            else break;
        }

        System.out.println(remain == 0 ? cnt : -1);
    }
}
