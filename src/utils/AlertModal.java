package utils;

import javafx.scene.control.Alert;

public class AlertModal {
	// Error alert
	public static void showErrorAlert(String alertHeader, String alertMessage) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(alertHeader);
		alert.setContentText(alertMessage);
		alert.show();
	}

	// Info alert
	public static void showInfoAlert(String alertHeader, String alertMessage) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(alertHeader);
		alert.setContentText(alertMessage);
		alert.show();
	}

}
