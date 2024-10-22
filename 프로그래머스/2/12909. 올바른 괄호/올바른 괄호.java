import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(Character ch : s.toCharArray()){
            if (stack.isEmpty() && ch == ')') return false;
            
            if (ch == ')') {
                if (stack.peek() == '(') stack.pop();
                else return false;
            }
            if (ch == '(') stack.add('(');
        }

        return stack.isEmpty();
    }
}