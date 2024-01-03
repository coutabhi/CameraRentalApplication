package rentmycam;

import java.util.*;


public class RentalCameraMain {

	static Scanner sc = new Scanner(System.in);

	// This camera listy is for the all user's combined camera's list
	static List<Camera> cameraList = new ArrayList<>();

	// This array list is for storing the list of users details
	static List<User> users = new ArrayList<>();

	// in this we can store the logged user details
	static String loggedInUser = null;
	
	private static final String WELCOME_MESSAGE = 
			"                                                                     \n"+
					"                                                                     \n"+
					"                                                                     \n"+
		    "               	       |        WELCOME TO RENTMYCAM               |\n" +
		    "               	       +-------------------------------------------+\n" +
		    "                       |      Camera rental application            |\n" +
		    "                       +-------------------------------------------+\n" +
		    "                       |    Developed by  Abhishek Yadav           |\n" +
		    "                       +-------------------------------------------+";


	public static void main(String[] args) {
		initializeData();
		welcomeAndLogin();
		runMainMenu();
	}

	// to initialize the data (camera and users)
	private static void initializeData() {
		cameraList.addAll(Arrays.asList(new Camera(1, "Samsung", "DS123", 500), new Camera(2, "Sony", "HD214", 500),
				new Camera(3, "Nikon", "XC", 25000), new Camera(4, "Canon", "XLR", 500),
				new Camera(5, "Fujitsu", "J5", 500), new Camera(7, "Sony", "HD226", 500),
				new Camera(8, "Samsung", "DS246", 500), new Camera(9, "LG", "L123", 500),
				new Camera(10, "Canon", "XPL", 500), new Camera(11, "Chroma", "CT", 500),
				new Camera(12, "Something", "some", 200), new Camera(13, "Some", "Another", 100),
				new Camera(14, "Canon", "Digital", 123), new Camera(15, "Nikon", "DSLR-D7500", 500),
				new Camera(16, "Sony", "DSLR12", 200), new Camera(17, "Samsung", "SM123", 200),
				new Camera(18, "Sony", "SONY1234", 123)));

		users.addAll(Arrays.asList(new User("abhishek", "abhi123"), new User("harshit", "harsh123"),
				new User("test", "test123")));
	}

	// This is for the welcome Screen that asks for the login.
	private static void welcomeAndLogin() {

		System.out.println(WELCOME_MESSAGE + "\n");
		System.out.println("PLEASE LOGIN TO CONTINUE");
		while (loggedInUser == null) {
			System.out.print("USERNAME - ");
			String username = sc.nextLine();
			System.out.print("PASSWORD - ");
			String password = sc.nextLine();

			loggedInUser = login(username, password);
			if (loggedInUser == null) {
				System.out.println("Invalid credentials. Please try again.");
			} else {
				System.out.println("Hi! " + loggedInUser + " Welcome to the RentMyCam");
			}
		}
	}

	// This method verify the user's username and password if verified then logged in the user
	private static String login(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(username) && user.verifyPassword(password)) {
				return username;
			}
		}
		return null;
	}

	// This method is for then main menu and run till we press 5(i.e exit case)
	private static void runMainMenu() {
		while (true) {
			System.out.println("1. MY CAMERA");
			System.out.println("2. RENT A CAMERA");
			System.out.println("3. VIEW ALL CAMERAS");
			System.out.println("4. MY WALLET");
			System.out.println("5. EXIT");
			try {
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					CameraOperations.myCamera(loggedInUser);
					break;
				case 2:
					CameraOperations.viewAllCameras();
					CameraOperations.rentCamera(loggedInUser);
					break;
				case 3:
					CameraOperations.viewAllCameras();
					break;
				case 4:
					UserOptions.myWallet(loggedInUser);
					break;
				case 5:
					System.out.println("Thank you for using the app!");
					System.exit(0);
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			} catch (NumberFormatException | InputMismatchException e) {
				System.out.println("Please enter valid number from 1 to 5\n");
			}
		}
	}
}
