import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner calk1 = new Scanner(System.in);
        while (true) { // ����������� ����
            System.out.print("������� ��������� (��� ������� '�����' ��� ����������): ");
            String calk = calk1.nextLine();

            if (calk.equalsIgnoreCase("�����")) {
                break; // ����� �� �����
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
                    throw new Exception("������������ ���� ��������");
                }

                // ���������, ������������� �� ������ ����������� �������
                if (data.length != 2 || !data[0].startsWith("\"") || !data[0].endsWith("\"")) {
                    throw new Exception("������ ���������� ���������, ����������� �� ����, ������ ���� ������.");
                }

                // ������� �������
                String str1 = data[0].replace("\"", "");
                String str2 = data[1].replace("\"", "");

                if (str1.length() > 10) {
                    throw new Exception("������ ������ ���� �� ������� 10 ��������.");
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
                System.out.println("������: " + e.getMessage()); // ��������� ����������
            }
        }
        calk1.close(); // ��������� ������ ����� ����������� ���������
    }

    private static int parseAndValidateNumber(String str, char action) throws Exception {
        int num = Integer.parseInt(str);
        if (num < 1 || num > 10) {
            throw new Exception("����� ������ ���� �� 1 �� 10.");
        }
        return num;
    }

    private static void processOperation(String str1, String str2, int number, char action) throws Exception {
        if (action == '*') {
            String result = str1.repeat(number);
            printWithLimit(result);
        } else if (action == '/') {
            if (number == 0) {
                throw new Exception("������ �� 0 ������.");
            }
            int newLength = str1.length() / number;
            String result = str1.substring(0, Math.min(newLength, str1.length()));
            printWithLimit(result);
        }
    }

    private static void printWithLimit(String text) {
        System.out.print("���������: "); // ���������
        if (text.length() > 40) {
            System.out.println(text.substring(0, 40) + "...");
        } else {
            System.out.println(text);
        }
    }

    static void printInQuotes(String text) {
        System.out.print("���������: "); // ���������
        System.out.println("\"" + text + "\"");
    }
}