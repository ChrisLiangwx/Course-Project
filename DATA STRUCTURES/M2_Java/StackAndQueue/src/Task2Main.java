import java.util.Scanner;


public class Task2Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please type in String: ");
        String input = scanner.nextLine();
        String words[] = input.split("\\s+");
        System.out.println("The input is:");

        Stack numbers = new Stack();
        Stack operators = new Stack();


        for(String word : words){
            //check for letters
            if(word.matches("-?\\d+")){
                //word is a number
                Node n = new Node(Double.parseDouble(word));
                numbers.push(n);
            }
            else if(word.matches("[+\\-*/]")){
                //word is an operator
                //check for priority
                while (!operators.isEmpty() && hasHigherPriority((char)operators.peek().data, word.charAt(0))) {
                    calculate(operators, numbers);
                }
                Node n = new Node(word.charAt(0));
                operators.push(n);
            }else{
                System.out.println("NaN");
                break;
            }
        }
        //in the loop above, only results that involves latter operator with higher priority than the former are calculated

        //calculate the rest
        while (!operators.isEmpty()) {
            calculate(operators, numbers);
        }


        numbers.displayStack();


    }


    private static boolean hasHigherPriority(char op1, char op2) {
        int precedence1 = getOperatorPriority(op1);
        int precedence2 = getOperatorPriority(op2);
        return precedence1 >= precedence2;
    }

    private static int getOperatorPriority(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }


    public static void calculate(Stack operators, Stack numbers){
        Node rightNum = numbers.pop();
        Node leftNum =  numbers.pop();
        Node operator = operators.pop();
        char operatorType = (char)operator.data;
        double result = 0;
        Node newNode = null;
        switch (operatorType) {
            case '+':
                result = (double) leftNum.data + (double) rightNum.data;
                newNode = new Node(result);
                break;
            case '-':
                result = (double) leftNum.data - (double) rightNum.data;
                newNode = new Node(result);
                break;
            case '*':
                result = (double) leftNum.data * (double) rightNum.data;
                newNode = new Node(result);
                break;
            case '/':
                if ((double)rightNum.data == 0) {
                    System.out.println("NaN");
                }
                result = (double) leftNum.data / (double) rightNum.data;
                newNode = new Node(result);
                break;
        }
        numbers.push(newNode);

    }

}
