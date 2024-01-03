# RentMyCam - Camera Rental Application

## Overview
RentMyCam is a Java-based camera rental application that facilitates camera rentals, viewing available cameras, adding cameras to user lists, and managing wallet balances for transactions.

## Components

1. **Camera.java:** Defines a camera's attributes such as ID, brand, model, rental price per day, and status.

2. **CameraOperations.java:** Provides functionalities to rent a camera, view all cameras, manage user-specific cameras (add, remove, view), and perform related operations.

3. **User.java:** Represents a user with properties like username, password, wallet balance, and a list of cameras added by the user. Includes methods to manage user-related tasks like adding money to the wallet, renting a camera, viewing user's cameras, etc.

4. **UserOptions.java:** Handles user-specific operations like managing the wallet balance, depositing money into the wallet, and providing related functionalities.

5. **RentalCameraMain.java:** Acts as the main entry point of the application. It initializes camera and user data, manages user login, and offers a menu-driven interface for users to interact with the system. It contains functionalities for the main menu, login validation, and handling user choices.

## Usage
Upon running the application, users are prompted to log in using their credentials. Once logged in, they can perform various actions from the main menu:
1. **My Camera:** Add, remove, or view user-specific cameras.
2. **Rent a Camera:** View available cameras and rent a selected camera for a specified rental period.
3. **View All Cameras:** Display all available cameras for rent.
4. **My Wallet:** View current wallet balance and deposit additional amounts to the wallet.

Users can navigate through the menu using numeric input and perform the desired actions.

## Running the Application
- Compile all Java files in the `rentmycam` package.
- Run the `RentalCameraMain.java` file.

**Note:**
- Ensure Java Development Kit (JDK) is installed to compile and run the Java files.
- This readme provides an overview; for detailed function-level documentation, refer to individual Java files.
