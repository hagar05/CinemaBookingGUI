package Cinema;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import javafx.stage.Stage;

public class App extends Application {

    List<UserCredentials> credentialsList = new ArrayList<>();

    static class UserCredentials {
        private final String userName;
        private final String password;

        UserCredentials(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public String getUserName() {
            return userName;
        }

    }

    public void readFromFile() {
        FileReader fileReader = null;
        BufferedReader reader = null;

        try {
            fileReader = new FileReader("login.txt");
            reader = new BufferedReader(fileReader);
            String content;
            while ((content = reader.readLine()) != null) {
                String splittedContent[] = content.split("-");
                String userContent = splittedContent[0].trim();
                String passContent = splittedContent[1].trim();
                credentialsList.add(new UserCredentials(userContent, passContent));
            }
        } catch (Exception e) {
            System.out.println("Cannot open file!");
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox group = new VBox(20);
        Label welcomeLabel = new Label("Welcome");
        Label userLabel = new Label("Username: ");
        TextField userField = new TextField();
        userField.setPromptText("Enter username");
        Label passLabel = new Label("Password: ");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter password");
        Button loginButton = new Button("Login");
        Button signupButton = new Button("Sign Up");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        welcomeLabel.setStyle("-fx-text-fill: white;");

        userLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        passLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        stage.setFullScreenExitKeyCombination(null);
        Image backgroundImage = new Image("Cinema/Cenima_Background.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO,
                        BackgroundSize.AUTO, false, false, true, true));
        group.setBackground(new Background(background));
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName = userField.getText();
                CharSequence passwordChar = passField.getCharacters();
                String password = passwordChar.toString();
                readFromFile();
                boolean validCredentials = false;
                for (UserCredentials credentials : credentialsList) {
                    if (password.equals(credentials.getPassword()) && userName.equals(credentials.getUserName())) {
                        validCredentials = true;
                        break;
                    }
                }

                if (validCredentials == true) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Successful Login!");
                    alert.setHeaderText("Welcome " + userName);
                    alert.showAndWait();

                    try {
                        openNewStage(DisplayApp.class);
                    } catch (Exception ex) {
                        System.err.println("Error opening new stage: " + ex.getMessage());
                    }

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Login Error!");
                    alert.setHeaderText("Incorrect credentials!\nPlease enter correct credentials!");
                    alert.showAndWait();
                }
            }
        });

        signupButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                VBox group = new VBox(20);
                Label welcomeLabel = new Label(" Signup");
                Label userLabel = new Label("Username: ");
                TextField userField2 = new TextField();
                userField.setPromptText("Enter username");
                Label passLabel = new Label("Password: ");
                PasswordField passField2 = new PasswordField();
                passField.setPromptText("Enter password");
                Button signupButton2 = new Button("Sign Up");
                welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
                welcomeLabel.setStyle("-fx-text-fill: white;");
                userLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                passLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

                Image backgroundImage = new Image("Cinema/Cenima_Background.jpg");
                BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO,
                                BackgroundSize.AUTO, false, false, true, true));

                group.setBackground(new Background(background));

                signupButton2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        FileWriter fileWriter = null;
                        BufferedWriter bufferedWriter = null;
                        try {
                            fileWriter = new FileWriter("login.txt", true);
                            bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.newLine();
                            readFromFile();
                            if (userField2 != null && passField2 != null) {
                                boolean userExists = false;
                                for (UserCredentials credentials : credentialsList) {
                                    if (userField2.getText().equals(credentials.getUserName()))
                                        userExists = true;
                                }
                                if (userExists) {
                                    Alert alert = new Alert(AlertType.ERROR);
                                    alert.setTitle("Sign Up Error!");
                                    alert.setHeaderText(
                                            "Username already exists!\nPlease choose a different username!");
                                    alert.showAndWait();
                                } else {
                                    bufferedWriter.append(userField2.getText() + " - " + passField2.getText());
                                    bufferedWriter.flush();
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Sign Up Success");
                                    alert.setHeaderText("Successfully Signed Up!");
                                    alert.showAndWait();
                                    stage.close();
                                }

                            }

                        } catch (Exception ex) {
                            System.out.println("Cannot open file!");
                        } finally {
                            try {
                                if (fileWriter != null) {
                                    fileWriter.close();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                    }
                });

                group.setAlignment(Pos.CENTER);
                group.getChildren().addAll(welcomeLabel, userLabel, userField2, passLabel, passField2, signupButton2);
                Scene scene = new Scene(group, 1000, 600);
                stage.setScene(scene);
                stage.setTitle("SignUp");
                stage.show();

            }
        });

        group.setAlignment(Pos.CENTER);
        group.getChildren().addAll(welcomeLabel, userLabel, userField, passLabel, passField, loginButton, signupButton);
        Scene scene = new Scene(group, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("SignUp/LogIn");
        stage.show();

    }

    private void openNewStage(Class<? extends Application> appClass) {
        try {
            Application appInstance = appClass.newInstance();
            Stage newStage = new Stage();
            appInstance.start(newStage);
        } catch (Exception ex) {
            System.err.println("Error opening new stage: " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
