import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Vyberte akci:");
            System.out.println("1. Zjištění přestupného roku podle vašeho roku");
            System.out.println("2. Zjištění přestupného roku podle aktuálního data");
            System.out.println("3. Váš formát data a času");
            System.out.println("4. Zbývající čas do určitého data");
            System.out.println("5. Vypnout");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkInput(scanner);
                    break;
                case 2:
                    checkDate();
                    break;
                case 3:
                    formatTime();
                    break;
                case 4:
                    calculateTime(scanner);
                    break;
                case 5:
                    System.out.println("Program byl ukončen.");
                    System.exit(0);
                default:
                    System.out.println("Neplatná volba. Zvolte 1-5.");
            }
        }
    }

    private static void checkInput(Scanner scanner) {
        System.out.print("Zadejte rok: ");
        int year = scanner.nextInt();
        boolean isLeapYear = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        System.out.println("Rok " + year + " je " + (isLeapYear ? "přestupný" : "nepřestupný"));
    }

    private static void checkDate() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        boolean isLeapYear = (currentYear % 4 == 0 && (currentYear % 100 != 0 || currentYear % 400 == 0));
        System.out.println("Aktuální rok " + currentYear + " je " + (isLeapYear ? "přestupný" : "nepřestupný"));
    }

    private static void formatTime() {
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String formattedTime = dateFormat.format(new Date(currentTimeMillis));
        System.out.println("Aktuální čas: " + formattedTime);
    }

    private static void calculateTime(Scanner scanner) {
        System.out.print("Zadejte den: ");
        int day = scanner.nextInt();
        System.out.print("Zadejte měsíc: ");
        int month = scanner.nextInt();
        System.out.print("Zadejte rok: ");
        int year = scanner.nextInt();

        Calendar targetDate = Calendar.getInstance();
        targetDate.set(year, month - 1, day);
        Calendar currentDate = Calendar.getInstance();

        long remainingMillis = targetDate.getTimeInMillis() - currentDate.getTimeInMillis();
        if (remainingMillis < 0) {
            System.out.println("Zadané datum již uplynulo.");
            return;
        }

        long remainingSeconds = remainingMillis / 1000;
        long remainingMinutes = remainingSeconds / 60;
        long remainingHours = remainingMinutes / 60;
        long remainingDays = remainingHours / 24;
        long remainingMonths = targetDate.get(Calendar.MONTH) - currentDate.get(Calendar.MONTH) +
                (year - currentDate.get(Calendar.YEAR)) * 12;

        System.out.println("Zbývá " + remainingMonths + " měsíců, " + remainingDays + " dní, " +
                remainingHours % 24 + " hodin, " + remainingMinutes % 60 + " minut, " +
                remainingSeconds % 60 + " sekund do zadaného data.");
    }
}
