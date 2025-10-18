
/* question:- 
Restaurant Order Management System
The goal is to create a basic console-based Restaurant Order Management System in Java that manages orders, allows a customer to place an order and check their status/bill, and allows a waiter to view orders and update their status.

The system requires:

Classes: An oder class to hold order details and a Restorent class to manage the menu, order list, and system logic.

Menu: A fixed menu with item names (burger, pizza, coffe, tea) and corresponding prices.

Customer Role:

Place a new order (record name, item number, and table number).

Get a bill (by entering an order ID).

Check order status (by entering an order ID).

Waiter Role:

View all pending orders.

Update the status of an order to "preparing," "ready," or "served."

Main Loop: A menu-driven interface to select the user's role (Customer, Waiter, etc.) until the user chooses to exit.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Define the 'oder' class to hold details for a single order.
class oder {
    private String user;
    private int itemNo;
    private int tableNo;
    private String status; // Initial status is set in the constructor

    // Constructor to initialize an order
    public oder(String user, int itemNo, int tableNo) {
        this.user = user;
        this.itemNo = itemNo;
        this.tableNo = tableNo;
        this.status = "new order"; // Default initial status
    }

    // Getters to retrieve order information
    public String getUser() {
        return user;
    }

    public int getItemNo() {
        return itemNo;
    }

    public int getTableNo() {
        return tableNo;
    }

    public String getStatus() {
        return status;
    }

    // Setter to allow the waiter to update the order status
    public void setStatus(String status) {
        this.status = status;
    }
}

// The 'Restorent' class manages the menu, prices, and the list of all orders.
class Restorent {
    // 'count' is used as the global Order ID counter.
    public static int count = 0; 
    Scanner sc = new Scanner(System.in);
    
    // Menu items and prices
    String[] itemList = {"burger", "pizza", "coffe", "tea"};
    int[] price = {150, 400, 80, 30};

    // List to store all 'oder' objects
    List<oder> lisOders = new ArrayList<>();

    // Redundant method from original code, preserved but Customer() is used in main.
    void setoder() {
        // This is a duplicate of the Customer method's logic
        Customer(); 
    }

    // Method for the Customer to place a new order.
    public void Customer() {
        System.out.println("\n--- PLACE NEW ORDER ---");
        System.out.println("Order ID:" + count); // Display ID before incrementing
        
        System.out.print("Enter your name: ");
        // Consume any leftover newline before reading the name
        if(sc.hasNextLine()){sc.nextLine();}
        String name = sc.nextLine();

        System.out.println("\nMenu Items:");
        for (int i = 0; i < itemList.length; i++) {
            System.out.println(i + ". " + itemList[i] + " (Price: " + price[i] + ")");
        }

        System.out.print("Enter your item number: ");
        int no = sc.nextInt();
        
        if (no < 0 || no >= itemList.length) {
            System.out.println("Invalid item number. Order not placed.");
            sc.nextLine(); // Consume the newline
            return;
        }

        System.out.print("Enter your table number: ");
        int tableNo = sc.nextInt();

        lisOders.add(new oder(name, no, tableNo));
        System.out.println("\n✅ Order placed successfully! Your Order ID is: " + count);
        count++; // Increment the order ID after successful placement
        sc.nextLine(); // Consume the newline character
    }

    // Method to calculate and display the bill for an order.
    public void getBill() {
        if (lisOders.isEmpty()) {
            System.out.println("❌ No orders have been placed yet.");
            return;
        }
        
        System.out.print("\nEnter your order id: ");
        if (!sc.hasNextInt()) {
            System.out.println("❌ Invalid input for Order ID.");
            sc.nextLine(); 
            return;
        }
        int in = sc.nextInt();
        sc.nextLine(); // Consume the newline

        if (in < 0 || in >= lisOders.size()) {
            System.out.println("❌ Invalid Order ID. Please check your ID.");
            return;
        }

        oder oc = lisOders.get(in);
        String itemName = itemList[oc.getItemNo()];
        int priceOfItem = price[oc.getItemNo()];

        System.out.println("\n--- BILL (Order ID: " + in + ") ---");
        System.out.println("Customer Name: " + oc.getUser());
        System.out.println("Table No: " + oc.getTableNo());
        System.out.println("Item Ordered: " + itemName + " (Status: " + oc.getStatus() + ")");
        System.out.println("\n💰 Your total bill is: " + priceOfItem + " INR");
        System.out.println("--------------------------------\n");
    }

    // Method for the Customer to check the status of their order.
    public void showStatus() {
        if (lisOders.isEmpty()) {
            System.out.println("❌ No orders have been placed yet.");
            return;
        }
        
        System.out.print("\nEnter your order id: ");
        if (!sc.hasNextInt()) {
            System.out.println("❌ Invalid input for Order ID.");
            sc.nextLine(); 
            return;
        }
        int in = sc.nextInt();
        sc.nextLine(); // Consume the newline

        if (in < 0 || in >= lisOders.size()) {
            System.out.println("❌ Invalid Order ID. Please check your ID.");
            return;
        }
        
        oder oc = lisOders.get(in);
        String itemName = itemList[oc.getItemNo()];

        System.out.println("\n--- ORDER STATUS (ID: " + in + ") ---");
        System.out.println("Customer Name: " + oc.getUser());
        System.out.println("Table: " + oc.getTableNo());
        System.out.println("Item: " + itemName);
        System.out.println("Current Status: " + oc.getStatus().toUpperCase());
        System.out.println("-----------------------------------\n");
    }

    // Method for the Waiter to view and update the status of orders.
    public void waiter() {
        if (lisOders.isEmpty()) {
            System.out.println("\n✨ No new orders waiting for action. The kitchen is quiet!");
            return;
        }
        
        System.out.println("\n--- WAITER: ORDER MANAGEMENT ---");
        
        // Iterate through all orders
        for (int i = 0; i < lisOders.size(); i++) {
            oder oc = lisOders.get(i);
            String itemName = itemList[oc.getItemNo()];
            
            System.out.println("\n[Order ID: " + i + "]");
            System.out.println("  Name: " + oc.getUser() + " | Table: " + oc.getTableNo() + " | Item: " + itemName + " | Current Status: " + oc.getStatus());

            System.out.print("Do you want to update the status for this order? (1 for YES, 0 for NO/Next): ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Moving to next order.");
                sc.nextLine(); // Consume invalid input
                continue;
            }
            int num = sc.nextInt();
            
            if (num == 1) {
                System.out.println("  Update Status Options:");
                System.out.println("  0. preparing ");
                System.out.println("  1. ready ");
                System.out.println("  2. served");
                System.out.print("  Enter new status (0, 1, or 2): ");
                
                if (!sc.hasNextInt()) {
                    System.out.println("  Invalid input. Status not updated.");
                    sc.nextLine();
                    continue;
                }
                int st = sc.nextInt();
                String stau = null;

                switch (st) {
                    case 0: stau = "preparing"; break;
                    case 1: stau = "ready"; break;
                    case 2: stau = "served"; break;
                    default:
                        System.out.println("  Invalid status choice (" + st + "). Status not updated.");
                        sc.nextLine();
                        continue; 
                }

                oc.setStatus(stau);
                System.out.println("  ✅ Status successfully updated to: " + stau.toUpperCase());
            }
            // Consume the newline character after reading the integer
            sc.nextLine(); 
        }
        System.out.println("\n--- WAITER: END OF ORDERS LIST ---\n");
    }
}

public class hyperlink {
    public static void main(String[] args) {
        Restorent rs = new Restorent();
        int choois = 0;

        // Scanner for the main menu interactions
        Scanner sc = new Scanner(System.in); 
        System.out.println("--- Welcome to the Simple Restaurant System! ---");

        String[] roles = {"Customer (Place Order)", "Waiter (Update Status)", "Show Order Status", "Get Bill", "Exit"};

        do {
            System.out.println("\n--- MAIN MENU: Select Your Role/Action ---");
            for (int i = 0; i < roles.length; i++) {
                System.out.println(i + ". " + roles[i]);
            }
            System.out.print("Select action (0-" + (roles.length - 1) + "): ");

            if (sc.hasNextInt()) {
                choois = sc.nextInt();
                // Consume the newline
                sc.nextLine(); 
                
                if (choois >= 0 && choois < roles.length) {
                    System.out.println("\n-> You Selected: " + roles[choois]);
                } else if (choois != 4) {
                    System.out.println("⚠️ Invalid choice. Please select a number between 0 and " + (roles.length - 1) + ".");
                    continue; 
                }
            } else {
                System.out.println("⚠️ Invalid input. Please enter a number.");
                sc.nextLine(); // Consume the invalid input
                continue;
            }

            switch (choois) {
                case 0:
                    rs.Customer();
                    break;
                case 1:
                    rs.waiter();
                    break;
                case 2:
                    rs.showStatus();
                    break;
                case 3:
                    rs.getBill();
                    break;
                case 4:
                    System.out.println("\n✅ Thank you! System shutting down. Goodbye! 👋");
                    break;
                default:
                    // Handled by the check above, but here for completeness
                    break;
            }

        } while (choois != 4);
        
        sc.close(); // Close the main menu scanner
    }
}
