package club.eugeneliu.information.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


//输入一个数字位数不超过一位,只使用加减运算以及括号运算的中缀算术表达式的求值
//1.遇到操作数时直接入列表list
//2.栈为空时，运算符直接入栈
//3.遇到左括号时将其入栈
//4.遇到右括号时，执行出栈操作，并将出栈元素添加到列表list中，直到遇到左括号为止,左括号不输出
//5.在拿到运算符时,在栈中还有其他运算符的时候，将栈中所有运算优先级大于或等于该运算符的栈顶元素出栈然后将该出栈的运算符添加到列表list中,之后将新的运算符入栈
//6.最后将栈中剩余的元素依次出栈并将其添加到列表list中
public class CaclExpression {
    public static int calc(String inOrderExpression) {
        List<Character> list = new ArrayList<>();
        Stack<Character> tempStack = new Stack<>();
        for (int i = 0; i < inOrderExpression.length(); i++) {
            if (inOrderExpression.charAt(i) >= '0' && inOrderExpression.charAt(i) <= '9') {
                list.add(inOrderExpression.charAt(i));
            } else if (inOrderExpression.charAt(i) == '+' || inOrderExpression.charAt(i) == '-') {
                if (!tempStack.empty()) {
                    if (tempStack.peek() == '+' || tempStack.peek() == '-') {
                        list.add(tempStack.pop());
                    }
                }
                tempStack.push(inOrderExpression.charAt(i));
            } else if (inOrderExpression.charAt(i) == '(') {
                tempStack.push('(');
            } else if (inOrderExpression.charAt(i) == ')') {
                while (tempStack.peek() != '(') {
                    list.add(tempStack.pop());
                }
                tempStack.pop();//移除右括号
            }
        }
        while (!tempStack.empty()) {
            list.add(tempStack.pop());
        }

        //Calc Expression

        Stack<Integer> integerStack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) >= '0' && list.get(i) <= '9') {
                integerStack.push(Integer.parseInt(String.valueOf(list.get(i))));
            } else if (list.get(i) == '+') {
                int number1 = integerStack.pop();
                int number2 = integerStack.pop();
                integerStack.push(number2 + number1);
            } else if (list.get(i) == '-') {
                int number1 = integerStack.pop();
                int number2 = integerStack.pop();
                integerStack.push(number2 - number1);
            }
        }
        int result = integerStack.pop();
        if (!integerStack.empty()) {
            return -1;//Error
        } else {
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(calc("4-(5+6)")); //-7
        System.out.println(calc("6+6-(5-8+3)+9"));//21
        System.out.println(calc("8+(7-2+9-7-(9+2-3))"));//7
        System.out.println(calc("3-1+(4+3-(2-1+7)+9)-2+7"));//15
    }
}
