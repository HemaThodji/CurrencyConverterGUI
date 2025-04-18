import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter extends JFrame {

    private JLabel fromLabel, toLabel, resultLabel;
    private JTextField amountTextField, resultTextField;
    private JComboBox<String> fromCurrencyComboBox, toCurrencyComboBox;
    private JButton convertButton;

    // Exchange rates (For simplicity, we use fixed rates)
    private static final double USD_TO_EUR = 0.85; // Example rate
    private static final double USD_TO_INR = 74.54;
    private static final double EUR_TO_USD = 1.18;
    private static final double EUR_TO_INR = 87.62;
    private static final double INR_TO_USD = 0.013;
    private static final double INR_TO_EUR = 0.011;

    public CurrencyConverter() {
        // Set the title and layout for the window
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Use null layout for this example (can be replaced with a layout manager)

        // From Label
        fromLabel = new JLabel("From Currency:");
        fromLabel.setBounds(30, 30, 120, 30);
        add(fromLabel);

        // To Label
        toLabel = new JLabel("To Currency:");
        toLabel.setBounds(30, 80, 120, 30);
        add(toLabel);

        // Result Label
        resultLabel = new JLabel("Converted Amount:");
        resultLabel.setBounds(30, 130, 120, 30);
        add(resultLabel);

        // Amount TextField
        amountTextField = new JTextField();
        amountTextField.setBounds(160, 30, 120, 30);
        add(amountTextField);

        // Result TextField (non-editable)
        resultTextField = new JTextField();
        resultTextField.setBounds(160, 130, 120, 30);
        resultTextField.setEditable(false);
        add(resultTextField);

        // From Currency ComboBox
        String[] currencies = {"USD", "EUR", "INR"};
        fromCurrencyComboBox = new JComboBox<>(currencies);
        fromCurrencyComboBox.setBounds(160, 80, 120, 30);
        add(fromCurrencyComboBox);

        // To Currency ComboBox
        toCurrencyComboBox = new JComboBox<>(currencies);
        toCurrencyComboBox.setBounds(160, 110, 120, 30);
        add(toCurrencyComboBox);

        // Convert Button
        convertButton = new JButton("Convert");
        convertButton.setBounds(160, 170, 120, 30);
        add(convertButton);

        // Add ActionListener to the Convert Button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get input amount
                    double amount = Double.parseDouble(amountTextField.getText());

                    // Get selected currencies
                    String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
                    String toCurrency = (String) toCurrencyComboBox.getSelectedItem();

                    // Perform conversion
                    double result = convertCurrency(amount, fromCurrency, toCurrency);

                    // Display the result
                    resultTextField.setText(String.format("%.2f", result));
                } catch (NumberFormatException ex) {
                    // If the input is not a valid number
                    JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
                }
            }
        });

        // Make the JFrame visible
        setVisible(true);
    }

    // Method to perform the currency conversion
    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double rate = 1.0;

        // Currency conversion logic based on selected currencies
        if (fromCurrency.equals("USD")) {
            if (toCurrency.equals("EUR")) {
                rate = USD_TO_EUR;
            } else if (toCurrency.equals("INR")) {
                rate = USD_TO_INR;
            } else {
                rate = 1.0; // USD to USD
            }
        } else if (fromCurrency.equals("EUR")) {
            if (toCurrency.equals("USD")) {
                rate = EUR_TO_USD;
            } else if (toCurrency.equals("INR")) {
                rate = EUR_TO_INR;
            } else {
                rate = 1.0; // EUR to EUR
            }
        } else if (fromCurrency.equals("INR")) {
            if (toCurrency.equals("USD")) {
                rate = INR_TO_USD;
            } else if (toCurrency.equals("EUR")) {
                rate = INR_TO_EUR;
            } else {
                rate = 1.0; // INR to INR
            }
        }

        // Perform conversion and return the result
        return amount * rate;
    }

    public static void main(String[] args) {
        new CurrencyConverter(); // Create and display the GUI
    }
}


