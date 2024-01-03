package rentmycam;

import java.util.ArrayList;
import rentmycam.Camera.CameraStatus;

public class User {
	private String username;
	private String password;
	private double walletBalance;

	// this list contains cameras that added by user
	private ArrayList<Camera> userCameras;
	
	
	// ------------------ Parametrized constructor used to create user ---------------------------
	
	public User(String username, String password) {
		this.username = username;
		setPassword(password);
		this.walletBalance = 0.0; // First user have to add balance in wallet for transaction
		// after creation of account
		
		// a array list created for the individual user
		this.userCameras = new ArrayList<Camera>();
	}

	// ----------------------------------- setters ---------------------------------------------------
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserCameras(ArrayList<Camera> userCameras) {
		this.userCameras = userCameras;
	}
	
	// ---------------------------------- getters ---------------------------------------------------
	
	public String getUsername() {
		return username;
	}

	public ArrayList<Camera> getMyCameras() {
		return userCameras;
	}

	public String getPassword() {
		return password;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public double getWallet() {
		return walletBalance;
	}

	public ArrayList<Camera> getUserCameras() {
		return userCameras;
	}

	//---------------------------- Methods related to user ----------------------------------------
		
	// Method to remove camera that added by user and user not able to remove other's camera
	public void removeUserCamera(int id) {
		for (Camera camera : userCameras) {
			if (camera.getId() == id) {
				userCameras.remove(camera);
				System.out.println("Camera with ID " + id + " removed from your list.");
				return;
			}
		}
		System.out.println("Camera with ID " + id + " not found in your list or not belongs to you.");
	}

	
	// Method to view cameras added by user
	public void viewMyCameras() {
		if (userCameras.isEmpty()) {
			System.out.println("You have no cameras in your list.");
			return;
		}
		System.out.println(
				String.format("%-10s%-15s%-15s%-20s%-15s", "CAMERA ID", "BRAND", "MODEL", "PRICE (PER DAY)", "STATUS"));
		for (Camera camera : userCameras) {
			System.out.println(camera);
		}
	}

	// Method to add money to wallet.
	public void addMoneyToWallet(double amount) {
		walletBalance += amount;
	}

	// Method to deduct money (this method used mainly when we do transaction).
	public void deductMoneyFromWallet(double amount) {
		if (walletBalance < amount) {
			System.out.println("Insufficient balance in your wallet.");
			return;
		}
		walletBalance -= amount;
		System.out.println("Amount of INR " + amount + " deducted from your wallet.");
	}
	
	// Method to rent a camera by user
	public void rentCamera(Camera camera, int days) {
		if (walletBalance < camera.getPerDayRent() * days) {
			System.out.println("Insufficient balance in your wallet.");
			return;
		}
		camera.setStatus(CameraStatus.RENTED);
		double rentAmount = camera.getPerDayRent() * days;
		walletBalance -= rentAmount;
		System.out.println("Amount of INR " + rentAmount + " deducted from your wallet for renting camera with ID "
				+ camera.getId() + ".");
	}

	// This method is used to verify password
	public boolean verifyPassword(String inputPassword) {
		return this.password.equals(inputPassword);
	}

}
