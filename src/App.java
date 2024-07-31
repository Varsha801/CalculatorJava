// import java.util.Scanner;
// public class App {
//     public static void main(String[] args) throws Exception {
//         Scanner scanner= new Scanner(System.in);
//         String done = "no";
//         int num1 , num2 ,result;
//         String operator ;
//         System.out.println("Enter num1");
//         num1 = scanner.nextInt();
//         do{
//             System.out.println("Enter operator");
//             operator = scanner.next();
//             System.out.println("Enter num1");
//             num2 = scanner.nextInt();
//             switch (operator) {
//                 case "+":
//                     result = num1+num1;
//                     break;
//                 case "-":
//                     result = num1-num1;
//                     break;
//                 case "*":
//                     result = num1*num1;
//                     break;
//                 case "/":
//                     result = num1/num1;
//                     break;
//                 default:
//                     System.out.println("invalid operator");
//                     break;
//             }
//             System.out.println("Result:");
//             num1 = result;
//         }while(done == "no");
//         scanner.close();
    
//     }
// }

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String done = "no";
        int num1, num2, result = 0;
        String operator;
        System.out.println("Enter num1");
        num1 = scanner.nextInt();

        do {
            System.out.println("Enter operator");
            operator = scanner.next();
            System.out.println("Enter num2");
            num2 = scanner.nextInt();

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        System.out.println("Cannot divide by zero");
                        continue;
                    }
                    break;
                default:
                    System.out.println("Invalid operator");
                    continue;
            }
            System.out.printf("Result: %d\n", result);
            System.out.println("Do you want to continue? (yes/no)");
            done = scanner.next();
            num1 = result;
        } while (!done.equals("no"));

        scanner.close();
    }
}

