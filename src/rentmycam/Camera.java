package rentmycam;

public class Camera {

	private int id;
	private String brand;
	private String model;
	private int perDayRent;
	private CameraStatus status;

	// I use enum for the camera status as we have only two statuses AVAILABLE and RENTED
	public enum CameraStatus {
		AVAILABLE, RENTED
	}

	// ------------------------- Constructor for the Camera ------------------------------------
	public Camera(int id, String brand, String model, int perDayRent) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.perDayRent = perDayRent;
		// as we know when user add camera then that is available for sure
		this.status = CameraStatus.AVAILABLE;
	}

	// -------------------------------- Getters --------------------------------------------------

	public int getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public int getPerDayRent() {
		return perDayRent;
	}

	public CameraStatus getStatus() {
		return status;
	}

	// -------------------------------- Getters --------------------------------------------------

	public void setStatus(CameraStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("%-10s%-15s%-15s%-20d%-15s", id, brand, model, perDayRent, status);
	}
}
