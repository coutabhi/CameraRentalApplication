package rentmycam;

import java.util.*;
import java.util.Scanner;

public class UserOptions {
    private static Scanner sc = new Scanner(System.in);
    public static List<User> users = RentalCameraMain.users;
     
     
    // This method shows the current wallet balance and also helps in adding money
 	// to wallet
    public static void myWallet(String loggedInUser) {
    	
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.getUsername().equalsIgnoreCase(loggedInUser)) {
				System.out.println("YOUR CURRENT WALLET BALANCE IS - INR." + user.getWallet());
				System.out.print("DO YOU WANT TO DEPOSIT MORE AMOUNT TO YOUR WALLET?(1.YES 2.NO) - ");
				String input = sc.nextLine();
				if (input.equals("1")) {
					try {
						System.out.print("ENTER THE AMOUNT (INR) - ");
						String input2 = sc.nextLine();
						int amountUpdate = Integer.parseInt(input2);

						// Find the user with the specified username
						User userToUpdateWallet = null;
						for (int j = 0; j < users.size(); j++) {
							User user1 = users.get(j);
							if (user.getUsername().equalsIgnoreCase(loggedInUser)) {
								userToUpdateWallet = user1;
								break;
							}
						}

						// Update the user wallet with money
						if (userToUpdateWallet != null) {
							userToUpdateWallet.addMoneyToWallet(amountUpdate);
							System.out.println("YOUR WALLET BALANCE UPDATED SUCCESSFULLY. CURRENT WALLET BALANCE - INR."
									+ user.getWallet());
						} else {
							System.out.println("User with username " + loggedInUser + " not found.");
						}
					} catch (NumberFormatException | InputMismatchException e) {
						System.out.println("\nPlease enter valid amount in numbers");
					}
				} else {
					break;
				}
			}
		}
	}
}
