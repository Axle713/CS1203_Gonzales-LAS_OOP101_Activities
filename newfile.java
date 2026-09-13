import java.util.Scanner;

public class Canteen_menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Canteen menu: 
        String[] menuItems = {"Burger", "Pizza", "Pasta", "Sandwich", "Milk Tea"};
        double[] menuPrices = {80.00, 120.00, 100.00, 70.00, 90.00};
        int menuSize = menuItems.length;

        // Running the totals for the whole transaction
        int totalQuantity = 0;
        double totalBeforeDeduction = 0.0;
        double totalDeduction = 0.0;

        // Display the menu once at the start
        System.out.println("=====  MENU  =====");
        for (int i = 0; i < menuSize; i++) {
            System.out.printf("%d. %-10s - $%.2f%n", i + 1, menuItems[i], menuPrices[i]);
        }

        char orderAgain = 'Y';

        while (orderAgain == 'Y' || orderAgain == 'y') {

            System.out.println();
            System.out.print("Enter item number: ");
            int itemNumber = scanner.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            String studentInput = scanner.next();
            boolean isStudent = studentInput.equalsIgnoreCase("Y");

            boolean validItem = (itemNumber >= 1 && itemNumber <= menuSize);
            boolean validQuantity = (quantity >= 1 && quantity <= 10);

            if (!validItem || !validQuantity) {
                // Invalid order: skip computation entirely, do not add to totals
                System.out.println();
                System.out.println("Invalid order! Please enter a valid item and quantity.");
            } else {
                double subtotal = menuPrices[itemNumber - 1] * quantity;
                double discountRate;

                // Determine discount rate based on student status and order amount
                if (isStudent && subtotal >= 500) {
                    discountRate = 0.15;
                } else if (isStudent) {
                    discountRate = 0.10;
                } else if (subtotal >= 500) {
                    discountRate = 0.05;
                } else {
                    discountRate = 0.00;
                }

                double discount = subtotal * discountRate;
                double orderTotal = subtotal - discount;

                System.out.println();
                System.out.printf("Subtotal: $%.2f%n", subtotal);
                System.out.printf("Discount: $%.2f%n", discount);
                System.out.printf("Order total: $%.2f%n", orderTotal);

                // Update running totals only for valid, processed orders
                totalQuantity += quantity;
                totalBeforeDeduction += subtotal;
                totalDeduction += discount;
            }

            System.out.println();
            System.out.print("Do you want to order again? (Y/N): ");
            orderAgain = scanner.next().charAt(0);

            if (orderAgain == 'N' || orderAgain == 'n') {
                break;
            }
        }

        double finalAmount = totalBeforeDeduction - totalDeduction;

        System.out.println();
        System.out.println("=====  ORDER SUMMARY  =====");
        System.out.println("Total quantity of items purchased: " + totalQuantity);
        System.out.printf("Total amount before deductions: $%.2f%n", totalBeforeDeduction);
        System.out.printf("Total deduction: $%.2f%n", totalDeduction);
        System.out.printf("Final amount to pay: $%.2f%n", finalAmount);
        System.out.println();
        System.out.println("Thank you for ordering!");

        scanner.close();
    }
}