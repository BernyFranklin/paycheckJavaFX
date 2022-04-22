/*
 * PaycheckFX Version#1.0
 * Frank Bernal
 * CIS 084 Java Programming
 * Input:       Hours, Pay Rate
 * Processing:  Compute paycheck with overtime
 * Output:      Display gross pay, taxes and net pay
 * 21 April 2022
 */

package paycheckfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/*
 * This program takes user input for hours worked and pay rate and 
 * computes a paycheck accounting for overtime and taxes.
 * 
 */

public class paycheckFX extends Application {
    
    // Define constants
    public static final double OVERTIME_RATE = 1.5;  // Time and a half
    public static final double TAX_RATE = 0.17;      // 0.17 is 17%
    
    // List of controls on the scene
    private static Label     lblTitle;
    private static Label     lblHours;
    private static Label     lblPayRate;
    private static Button    btnCompute;
    private static TextField txtHours;
    private static TextField txtPayRate;
    private static TextArea  txtPaycheck;
    
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        createControls (root);
        
        Scene scene = new Scene(root, 450, 320);
        stage.setTitle("JavaFX Paycheck Project");
        stage.setResizable(false);
        stage.show();
        
    }   // End of start stage

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }   // End of PSV main
    
    /*
     * Process the Compute button click event
     */
    private static void btnComputeClick() {
        double hours;
        double payRate;
        double regHours;
        double overtimeHours;
        double regPay;
        double overtimePay;
        double grossPay;
        double taxes;
        double netPay;
        
        // Input the hours and payRate from the text fields
        try {
            hours = Double.valueOf(txtHours.getText());
            payRate = Double.valueOf(txtPayRate.getText());
            if (hours < 0 || payRate < 0)
                throw new IllegalArgumentException ("Inputs must be positive");
        }   // End of try
        catch (NumberFormatException e){
            txtPaycheck.setText("Values for hours and pay rate must be numeric");
            return;   // No more processing
        }   // End of Number catch
        catch (IllegalArgumentException e) {
            txtPaycheck.setText(e.getMessage());
            return;   // No more processing
        }   // End of illegal argument catch
        
        // Compute the regHOurs and overtimeHours
        if (hours <= 40) {                  // Less or = to 40 hours, then
            regHours = hours;               // all hours are regular
            overtimeHours = 0.0;            // with no overtime
        }   // End of regular week
        else {                              // Over 40 hours, then
            regHours = 40.0;                // first 40 at regular pay
            overtimeHours = hours - 40.0;   // over 40 is OT
        }   // End of heavy week
        
        // Compute the paycheck
        regPay = regHours * payRate;
        overtimePay = overtimeHours * payRate * OVERTIME_RATE;
        grossPay = regPay + overtimePay;
        taxes = grossPay * TAX_RATE;
        netPay = grossPay - taxes;
        
        // Display the paycheck
        txtPaycheck.setText(
        String.format ("Your gross pay is $%.2f\n", grossPay) +
        String.format ("Your taxes are    $%.2f\n", taxes)    +
        String.format ("Your net pay is   $%.2f"  , netPay));
        
    }   // End of btnComputeClick
    
    /*
     *   Create the controls and place them on the pane
     */
    private static void createControls(Pane root) {
        Font font36B = Font.font("Ariel", FontWeight.BOLD, 36);   // Title
        Font font18  = Font.font("Ariel", FontWeight.NORMAL, 18);
        
        lblTitle = new Label ("Frank's Paycheck Calculator");
            lblTitle.setFont(font36B);   // Big fat font
            lblTitle.setLayoutX(12);
            lblTitle.setLayoutY(4);
            root.getChildren().add(lblTitle);
            
        lblHours = new Label ("Hours");
            lblHours.setFont(font18);
            lblHours.setLayoutX(24);
            lblHours.setLayoutY(57);
            root.getChildren().add(lblHours);
            
        txtHours = new TextField();
            txtHours.setFont(font18);
            txtHours.setLayoutX(123);
            txtHours.setLayoutY(57);
            txtHours.setMaxSize(119, 30);   // Make textfields same size
            txtHours.setMinSize(119, 30);
            root.getChildren().add(txtHours);
            
        lblPayRate = new Label ("Pay Rate");
            lblPayRate.setFont(font18);
            lblPayRate.setLayoutX(24);
            lblPayRate.setLayoutY(101);
            root.getChildren().add(lblPayRate);
            
        txtPayRate = new TextField();
            txtPayRate.setFont(font18);
            txtPayRate.setLayoutX(123);
            txtPayRate.setLayoutY(95);
            txtPayRate.setMaxSize(119, 30);
            txtPayRate.setMinSize(119, 30);
            root.getChildren().add(txtPayRate);
            
        btnCompute = new Button ("Compute");
            btnCompute.setFont(font18);
            btnCompute.setLayoutX(40);
            btnCompute.setLayoutY(148);
            btnCompute.setMaxSize(110, 40);   // Make buttons same size
            btnCompute.setMinSize(110, 40); 
            // Provide link to the event handler for the compute button
            btnCompute.setOnAction( e -> btnComputeClick());
            btnCompute.setOnKeyPressed( e -> btnComputeClick());
            root.getChildren().add(btnCompute);
            
        txtPaycheck = new TextArea();
            txtPaycheck.setFont(font18);
            txtPaycheck.setLayoutX(40);
            txtPaycheck.setLayoutY(205);
            txtPaycheck.setMaxSize(400, 100);
            txtPaycheck.setMinSize(400, 100);
            txtPaycheck.setEditable(false);        // Don;t let user edit check
            root.getChildren().add(txtPaycheck);
        
    }   // End of createControls
    
}   // End of paycheckFX
