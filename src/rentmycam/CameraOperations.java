package rentmycam;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import rentmycam.Camera.CameraStatus;

public class CameraOperations {

	private static Scanner sc = new Scanner(System.in);
	public static List<User> users = RentalCameraMain.users;
	public static List<Camera> cameraList = RentalCameraMain.cameraList;

	// This is the utility function to get camera by id
	public static Camera getCameraById(List<Camera> cameraList, int id) {
		for (Camera camera : cameraList) {
			if (camera.getId() == id) {
				return camera;
			}
		}
		return null;
	}

	// This method is to rent camera by user and update data respect to that
	public static void rentCamera(String loggedInUser) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("ENTER THE CAMERA ID YOU WANT TO RENT - ");
			int cameraCode = sc.nextInt();

			Camera getcamera = CameraOperations.getCameraById(cameraList, cameraCode);
			if (getcamera == null) {
				System.out.println("Camera with ID " + cameraCode + " not found.");
				return;
			}
			if (getcamera.getStatus() == CameraStatus.RENTED) {
				System.out.println("This camera already rented and not available for rent");
				rentCamera(loggedInUser);
			}
			System.out.print("ENTER RENTAL PERIOD (in days) - ");
			int rentalPeriod = sc.nextInt();
			sc.nextLine();

			getcamera.setStatus(CameraStatus.RENTED);
			double rentAmount = getcamera.getPerDayRent() * rentalPeriod;

			// Find the user with the specified username
			User userToUpdate = null;
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				if (user.getUsername().equalsIgnoreCase(loggedInUser)) {
					userToUpdate = user;
					break;
				}
			}
			if (rentAmount > userToUpdate.getWallet()) {
				System.out.println(
						"ERROR : TRANSACTION FAILED DUE TO INSUFFIECIENT WALLET BALANCE. PLEASE DEPOSIT THE AMOUNT TO YOUR WALLET.");
				return;
			}

			// Update the userCameras ArrayList of the found user object
			if (userToUpdate != null) {
				userToUpdate.deductMoneyFromWallet(rentAmount);
			}

			System.out.println(
					"YOUR TRANSACTION FOR CAMERA - " + "with rent INR." + rentAmount + " HAS SUCCESSFULLY COMPLETED.");
		} catch (NumberFormatException | InputMismatchException e) {
			System.out.println("Please enter valid number\n");
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		} finally {}
	}

	// This method is to view all the cameras
	public static void viewAllCameras() {
		System.out.println("\nFOLLOWING IS THE LIST OF AVAILABLE CAMERA(S)-");
		System.out.println("================================================================================");
		if (cameraList.size() == 0) {
			System.out.println("No cameras available for rent.");
		} else {
			System.out.println("CAMERA ID\tBRAND\t\tMODEL\t\tPRICE (PER DAY)\tSTATUS");
			System.out.println("================================================================================");
			for (Camera camera : cameraList) {
				System.out.printf("%-10s%-20s%-20s%-15s%s%n", camera.getId(), camera.getBrand(), camera.getModel(),
						camera.getPerDayRent(), camera.getStatus());
			}

		}
		System.out.println("================================================================================");
	}

	// This method is to add, delete, view user's own camera
	public static void myCamera(String loggedInUser) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean backToMenu = false;
		while (!backToMenu) {
			try {
				System.out.println("1. ADD");
				System.out.println("2. REMOVE");
				System.out.println("3. VIEW MY CAMERAS");
				System.out.println("4. GO TO PREVIOUS MENU");
				System.out.print("Enter your choice: ");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					addCamera(loggedInUser);
					break;
				case 2:
					removeCamera();
					break;
				case 3:
					viewMyCameras(loggedInUser);
					break;
				case 4:
					backToMenu = true;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter valid number from 1 to 4\n");
				break;
			}
		}
	}

	// To add the camera by user
	public static void addCamera(String loggedInUser) {
		try {
			// System.out.println("\nADD CAMERA");
			System.out.print("ENTER THE CAMERA BRAND - ");
			String brand = sc.nextLine();
			System.out.print("ENTER THE MODEL - ");
			String model = sc.nextLine();
			System.out.print("ENTER THE PER DAY PRICE (INR) - ");
			int price = sc.nextInt();
			int id = generateCameraId();
			Camera camera = new Camera(id, brand, model, price);
			ArrayList<Camera> cameraToAdd = new ArrayList<Camera>();
			cameraToAdd.add(camera);
			User userToUpdate = null;
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				if (user.getUsername().equalsIgnoreCase(loggedInUser)) {
					userToUpdate = user;
					break;
				}
			}
			userToUpdate.setUserCameras(cameraToAdd);
			cameraList.add(camera);
			System.out.println("YOUR CAMERA HAS BEEN SUCCESSFULLY ADDED TO THE LIST.\n");
		} catch (NumberFormatException | InputMismatchException e) {
			System.out.println("Please enter valid the valid data\n");
		}
	}

	// Utility method to generate the camera id serial wise.
	public static int generateCameraId() {
		int lastCameraId = 0;
		if (!cameraList.isEmpty()) {
			Camera lastCamera = cameraList.get(cameraList.size() - 1);
			lastCameraId = lastCamera.getId();
		}
		int newCameraId = lastCameraId + 1;
		return newCameraId;
	}

	// Method to remove the user added camera
	public static void removeCamera() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("================================================================");
			System.out.println("CAMERA ID  BRAND     MODEL    PRICE (PER DAY)  STATUS");
			System.out.println("=============================================================== ");
			for (int i = 0; i < cameraList.size(); i++) {
				Camera camera = cameraList.get(i);
				System.out.format("%-10d%-10s%-10s%-18s%s%n", camera.getId(), camera.getBrand(), camera.getModel(),
						camera.getPerDayRent(),
						camera.getStatus().equals(CameraStatus.AVAILABLE) ? "Available" : "Not Available");
			}
			System.out.println("------------------------------------------------------");
			System.out.print("ENTER THE CAMERA ID TO REMOVE - ");
			if (scanner.hasNextInt()) {
				int cameraId = scanner.nextInt();
				// process the input
				boolean found = false;
				for (int i = 0; i < cameraList.size(); i++) {
					Camera camera = cameraList.get(i);
					if (camera.getId() == cameraId) {
						cameraList.remove(camera);
						found = true;
						System.out.println("CAMERA SUCCESSFULLY REMOVED FROM THE LIST - ");
						break;
					}
				}
				if (!found) {
					System.out.println("Camera with ID " + cameraId + " not found.");
				}
			} else {
				System.out.println("Invalid input. Please enter an integer.");
			}
		} catch (NumberFormatException | InputMismatchException e) {
			System.out.println("Please enter valid data\n");
		} finally {
		}
	}

	// To view user's ownn listed cameras
	public static void viewMyCameras(String loggedInUser) {
		for (User user : users) {
			System.out.println(user.getUsername());
			if (user.getUsername().equalsIgnoreCase(loggedInUser)) {
				user.viewMyCameras();
				return;
			}
		}
		System.out.println("User with username " + loggedInUser + " not found.");
	}
}
