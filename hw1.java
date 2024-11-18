// Class: CSE360 Intro to Software Engineering
// Description: HW1: Joe’s Deli Breakfast
// Author: Melissa Mandyck
// Date: 09/14/2024
package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class hw1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Joe's Deli");

        // CheckBox for Food Items
        CheckBox eggSandwich = new CheckBox("Egg Sandwich");
        CheckBox chickenSandwich = new CheckBox("Chicken Sandwich");
        CheckBox bagel = new CheckBox("Bagel");
        CheckBox potatoSalad = new CheckBox("Potato Salad");

        // VBox Group Alignment of Food Items
        VBox foodVBox = new VBox(10); // vertical spacing between CheckBoxes
        foodVBox.getChildren().addAll(eggSandwich, chickenSandwich, bagel, potatoSalad);

        // RadioButtons for DrinkGroup
        ToggleGroup drinkGroup = new ToggleGroup();
        RadioButton blackTea = new RadioButton("Black Tea");
        RadioButton greenTea = new RadioButton("Green Tea");
        RadioButton coffee = new RadioButton("Coffee");
        RadioButton orangeJuice = new RadioButton("Orange Juice");

        blackTea.setToggleGroup(drinkGroup);
        greenTea.setToggleGroup(drinkGroup);
        coffee.setToggleGroup(drinkGroup);
        orangeJuice.setToggleGroup(drinkGroup);

        // VBox Group Alignment of Drink Items
        VBox drinkVBox = new VBox(10); // Vertical Spacing between RadioButtons
        drinkVBox.getChildren().addAll(blackTea, greenTea, coffee, orangeJuice);

        // Bill Text Area
        TextArea billTextArea = new TextArea();
        billTextArea.setPrefHeight(200);
        billTextArea.setEditable(false); // Makes bill not editable by user

        // Buttons Order, Cancel and Confirm
        Button orderButton = new Button("Order");
        Button cancelButton = new Button("Cancel");
        Button confirmButton = new Button("Confirm");

        // GridPane Layout Configurations 
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(10);
        layout.setHgap(20); // Horizontal Gap Between Columns

        // Layout Components Eat, Drink, and Bill
        layout.add(new Label("Eat:"), 0, 0);
        layout.add(foodVBox, 0, 1);  // Add VBox for Food Checkboxes

        layout.add(new Label("Drink:"), 1, 0);
        layout.add(drinkVBox, 1, 1);  // Add VBox forDrink RadioButtons

        layout.add(new Label("Bill:"), 2, 0);
        layout.add(billTextArea, 2, 1, 1, 4);  // Height Spanning rows

        layout.add(orderButton, 0, 5);
        layout.add(cancelButton, 1, 5);
        layout.add(confirmButton, 2, 5);

        // Create & Set Scene
        Scene scene = new Scene(layout, 800, 300); // Full Window Size Display
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event Handlers
        orderButton.setOnAction(e -> {
            StringBuilder bill = new StringBuilder("Order Summary:\n");
            double total = 0;

            if (eggSandwich.isSelected()) {
                bill.append("Egg Sandwich: $7.99\n");
                total += 7.99;
            }
            if (chickenSandwich.isSelected()) {
                bill.append("Chicken Sandwich: $9.99\n");
                total += 9.99;
            }
            if (bagel.isSelected()) {
                bill.append("Bagel: $2.50\n");
                total += 2.50;
            }
            if (potatoSalad.isSelected()) {
                bill.append("Potato Salad: $4.49\n");
                total += 4.49;
            }

            RadioButton selectedDrink = (RadioButton) drinkGroup.getSelectedToggle();
            if (selectedDrink != null) {
                bill.append(selectedDrink.getText() + ": ");
                switch (selectedDrink.getText()) {
                    case "Black Tea":
                        bill.append("$1.25\n");
                        total += 1.25;
                        break;
                    case "Green Tea":
                        bill.append("$0.99\n");
                        total += 0.99;
                        break;
                    case "Coffee":
                        bill.append("$1.99\n");
                        total += 1.99;
                        break;
                    case "Orange Juice":
                        bill.append("$2.25\n");
                        total += 2.25;
                        break;
                }
            }

            // Print Total(before tax)
            bill.append("\nTotal (before tax): $" + String.format("%.2f", total) + "\n");

            // Print Sales Tax
            double salesTax = total * 0.07;
            bill.append("Sales Tax (7%): $" + String.format("%.2f", salesTax) + "\n");

            // Print Total (with Sales Tax)
            double totalWithTax = total + salesTax;
            bill.append("\n ");
            bill.append("Total (with Sales Tax): $" + String.format("%.2f", totalWithTax) + "\n");

            // Update Bill in TextArea
            billTextArea.setText(bill.toString());
        });
     // Cancel Button Clears Everything
        cancelButton.setOnAction(e -> {
            eggSandwich.setSelected(false);
            chickenSandwich.setSelected(false);
            bagel.setSelected(false);
            potatoSalad.setSelected(false);
            drinkGroup.selectToggle(null);
            billTextArea.clear();
        });
        // Order Confirmation in TextArea
        confirmButton.setOnAction(e -> {
            billTextArea.appendText("\nOrder Confirmed. Thank you for your purchase.");
            billTextArea.appendText("\nYour order will be ready soon!");

            
         // Clear Food Selection CheckBoxes
            eggSandwich.setSelected(false);
            chickenSandwich.setSelected(false);
            bagel.setSelected(false);
            potatoSalad.setSelected(false);

            // Clear Drink Selections RadioButtons
            drinkGroup.selectToggle(null);  
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
