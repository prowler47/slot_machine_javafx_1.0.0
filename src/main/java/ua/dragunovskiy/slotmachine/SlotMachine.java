package ua.dragunovskiy.slotmachine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ua.dragunovskiy.slotmachine.repository.SymbolStorage;

import java.util.Random;

public class SlotMachine extends Application {
    private final SymbolStorage storage = new SymbolStorage();
    int currentBalance = 100;
    int winAmount = 0;

    @Override
    public void start(Stage stage) throws Exception {
        ImageView slot1 = new ImageView();
        ImageView slot2 = new ImageView();
        ImageView slot3 = new ImageView();

        Button spinButton = new Button("Spin");

        Label balanceLabel = new Label("Balance: " + currentBalance);
        Label winLabel = new Label("");

        HBox slotBox = new HBox(10, slot1, slot2, slot3);
        VBox infoBox = new VBox(10, balanceLabel, winLabel, spinButton);
        BorderPane root = new BorderPane();
        root.setCenter(slotBox);
        root.setBottom(infoBox);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Slot machine");
        stage.show();
        spinButton.setOnAction(e -> spin(slot1, slot2, slot3, balanceLabel, winLabel));
    }

    private void spin(ImageView slot1, ImageView slot2, ImageView slot3, Label balanceLabel, Label winLabel) {
        Random random = new Random();
        int indexSlot1 = random.nextInt(storage.SYMBOLS.length);
        int indexSlot2 = random.nextInt(storage.SYMBOLS.length);
        int indexSlot3 = random.nextInt(storage.SYMBOLS.length);


        slot1.setImage(new Image(storage.SYMBOLS[indexSlot1]));
        slot2.setImage(new Image(storage.SYMBOLS[indexSlot2]));
        slot3.setImage(new Image(storage.SYMBOLS[indexSlot3]));

        if (indexSlot1 == indexSlot2 && indexSlot2 == indexSlot3) {
            winAmount = winAmount + 10;
            winLabel.setText("You win $: " + winAmount);
        } else {
            winLabel.setText("Try again!");
        }
        balanceLabel.setText("Your balance: $" + (currentBalance + winAmount));
    }
}
