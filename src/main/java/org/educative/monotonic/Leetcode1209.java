package org.educative.monotonic;

import java.util.Stack;

public class Leetcode1209 {
    public String removeDuplicates(String s, int k) {
        if(s == null || s.length() == 0) return s;

        Stack<Object[]> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for(char ch : s.toCharArray()) {
            if(stack.isEmpty()) {
                stack.push(new Object[]{ch, 1});
            }else {
                char lastChar = (char)stack.peek()[0];
                int lastCount = (int) stack.peek()[1];
                if(lastChar == ch && lastCount < k - 1) {
                    stack.push(new Object[]{ch, lastCount + 1});
                }else if(lastChar == ch && lastCount == k - 1) {
                    int count = 0;
                    while(count != k - 1) {
                        stack.pop();
                        count++;
                    }
                }else if(lastChar != ch) {
                    stack.push(new Object[]{ch, 1});
                }
            }
        }

        while(!stack.isEmpty()) {
            Object[] item = stack.pop();
            result.append((char)item[0]);
        }

        return result.reverse().toString();
    }
}
