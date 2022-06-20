package View;

import Model.InvoiceHeader;
import Model.InvoiceLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewInvoice extends GuiForm {
    private JPanel panel1;
    private JTextField dateTextField;
    private JTextField customerNameTextField;
    private JButton createInvoiceButton;
    private JButton cancelButton;

    public CreateNewInvoice(){

        this.setTitle("Create invoice");
        this.setSize(350,130);
        this.setLocation(700,400);
        setContentPane(panel1);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        createInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InvoiceHeader newInvoiceHeader=new InvoiceHeader(dateTextField.getText(),customerNameTextField.getText());
                dispose();

            }
        });

    }

}
