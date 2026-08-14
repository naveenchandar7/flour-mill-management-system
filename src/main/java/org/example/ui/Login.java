package org.example.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("🌾 Flour Mill Management");

        title.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;"
        );

        Label usernameLabel = new Label("Username");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        Label passwordLabel = new Label("Password");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Button loginButton = new Button("Login");

        Label message = new Label();

        loginButton.setOnAction(event -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {

                message.setText("Please enter username and password.");

            } else if (
                    username.equals("admin") &&
                            password.equals("admin123")
            ) {

                Dashboard dashboard = new Dashboard();

                dashboard.show();

                stage.close();

            } else {

                message.setText("Invalid username or password.");
            }
        });

        VBox layout = new VBox(12);

        layout.setPadding(new Insets(30));

        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(
                title,
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                loginButton,
                message
        );

        Scene scene = new Scene(
                layout,
                400,
                400
        );

        stage.setTitle("Login");

        stage.setScene(scene);

        stage.show();
    }
}