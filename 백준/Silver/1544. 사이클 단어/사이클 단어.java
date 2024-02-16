import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Set<String> words = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            boolean isNewWord = true;

            // 현재 단어를 회전해 이미 등록된 단어와 비교
            for (int j = 0; j < word.length(); j++) {

                String rotatedWord = rotate(word, j);
                if (words.contains(rotatedWord)) {
                    isNewWord = false;
                    break;
                }
            }

            // 새로운 단어 Set에 추가
            if (isNewWord) {
                words.add(word);
            }
        }

        System.out.println(words.size());
    }

    // 문자열을 주어진 위치만큼 회전시키는 함수
    private static String rotate(String word, int rotation) {
        int N = word.length();
        word = word + word;
        return word.substring(rotation, rotation + N);
    }
}
