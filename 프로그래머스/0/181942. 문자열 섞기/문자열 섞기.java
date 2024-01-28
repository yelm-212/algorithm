class Solution {
    public String solution(String str1, String str2) {
        StringBuilder tmp = new StringBuilder();
        for(int i = 0 ; i < str1.length(); i++){
            tmp.append(str1.charAt(i));
            tmp.append(str2.charAt(i));
        }
        return tmp.toString();
    }
}