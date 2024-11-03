import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner calk1 = new Scanner(System.in);
        while (true) { // Бесконечный цикл
            System.out.print("Введите выражение (или введите 'Выход' для завершения): ");
            String calk = calk1.nextLine();

            if (calk.equalsIgnoreCase("Выход")) {
                break; // Выход из цикла
            }

            try {
                String[] data;
                char action;

                if (calk.contains(" + ")) {
                    data = calk.split(" \\+ ");
                    action = '+';
                } else if (calk.contains(" - ")) {
                    data = calk.split(" - ");
                    action = '-';
                } else if (calk.contains(" * ")) {
                    data = calk.split(" \\* ");
                    action = '*';
                } else if (calk.contains(" / ")) {
                    data = calk.split(" / ");
                    action = '/';
                } else {
                    throw new Exception("Некорректный знак действия");
                }

                // Проверяем, соответствует ли строка правильному формату
                if (data.length != 2 || !data[0].startsWith("\"") || !data[0].endsWith("\"")) {
                    throw new Exception("Первым аргументом выражения, подаваемого на вход, должна быть строка.");
                }

                // Удаляем кавычки
                String str1 = data[0].replace("\"", "");
                String str2 = data[1].replace("\"", "");

                if (str1.length() > 10) {
                    throw new Exception("Строки должны быть не длиннее 10 символов.");
                }

                if (action == '*' || action == '/') {
                    int number = parseAndValidateNumber(str2, action);
                    processOperation(str1, str2, number, action);
                } else if (action == '+') {
                    printInQuotes(str1 + str2);
                } else { // action == '-'
                    String result = str1.replace(str2, "");
                    printInQuotes(result.isEmpty() ? str1 : result);
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage()); // Обработка исключений
            }
        }
        calk1.close(); // Закрываем сканер перед завершением программы
    }

    private static int parseAndValidateNumber(String str, char action) throws Exception {
        int num = Integer.parseInt(str);
        if (num < 1 || num > 10) {
            throw new Exception("Число должно быть от 1 до 10.");
        }
        return num;
    }

    private static void processOperation(String str1, String str2, int number, char action) throws Exception {
        if (action == '*') {
            String result = str1.repeat(number);
            printWithLimit(result);
        } else if (action == '/') {
            if (number == 0) {
                throw new Exception("Делить на 0 нельзя.");
            }
            int newLength = str1.length() / number;
            String result = str1.substring(0, Math.min(newLength, str1.length()));
            printWithLimit(result);
        }
    }

    private static void printWithLimit(String text) {
        System.out.print("Результат: "); // Добавлено
        if (text.length() > 40) {
            System.out.println(text.substring(0, 40) + "...");
        } else {
            System.out.println(text);
        }
    }

    static void printInQuotes(String text) {
        System.out.print("Результат: "); // Добавлено
        System.out.println("\"" + text + "\"");
    }
}