package View;

import Model.InvoiceLine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLine extends GuiForm{
    private JPanel panel1;
    private JTextField itemNameTextField;
    private JTextField itemPriceTextField;
    private JTextField itemCountTextField;
    private JButton cancelButton;
    private JButton okButton;


    public AddLine(){

        this.setTitle("Add Item");
        this.setSize(350,170);
        this.setLocation(700,400);
        setContentPane(panel1);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    InvoiceLine invoiceLine = new InvoiceLine(itemNameTextField.getText(),
                            Double.parseDouble(itemPriceTextField.getText()),
                            Integer.parseInt(itemCountTextField.getText()));
                    dispose();
                }catch (NumberFormatException k){
                    System.out.println("Wrong input format");
                }
            }
        });
    }



}
