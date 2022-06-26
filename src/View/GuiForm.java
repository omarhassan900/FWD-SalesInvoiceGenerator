package View;

import Controller.Controller;
import Model.FileOperation;
import Model.InvoiceHeader;
import Model.InvoiceLine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.io.*;

import static Model.InvoiceLine.invoiceLine;

public  class GuiForm extends JFrame {

    private JMenuBar myBar;
    private JMenu file;
    private JMenuItem saveMenuItem;
    private JMenuItem openMenuItem;

    private JPanel leftSidePanel;
    private JPanel mainPanel;
    private JLabel label1;
    private JTable invoiceDetailsTable;

    private JButton createNewInvoiceButton;
    private JButton deleteInvoiceButton;
    public  JTable allInvoiceTable;
    private JScrollPane Tabel1;
    private JLabel totalInvoiceLabel;
    private JLabel invoiceNumberLabel;
    private JLabel customerNameField;
    private JLabel invoiceDateField;
    private JButton addLineButton;
    private JButton deleteItemButton;

    private TableColumnModel invoiceDetailsTableColumns;
    private TableColumnModel allInvoiceTableColumn;

    public GuiForm(){}

    public GuiForm(String title) {

        super(title);
        this.setResizable(false);
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(400, 230);

        setContentPane(mainPanel);
        addMenuBar();
        addinvoiceHeader();
        createNewInvoice();
        ifRowSelected();
        addItemLine();
        deleteInvoiceHeader();
        deleteItem();



    }





    private void deleteItem(){

        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListSelectionModel model=invoiceDetailsTable.getSelectionModel();
                int selectedIndex=model.getMinSelectionIndex();
                String invoiceNumber=invoiceNumberLabel.getText();
                String itemName= (String) invoiceDetailsTable.getModel().getValueAt(selectedIndex,1);


                for(int i=0;i< invoiceLine.size();i++){


                    if( invoiceLine.get(i)[0].equals( invoiceNumber) && invoiceLine.get(i)[1].equals(itemName)){

                        invoiceLine.remove(i);
                    }

                }
                invoiceDetailsTable.setModel(new DefaultTableModel(Controller.getInvoiceLine(invoiceNumberLabel.getText()),new String[]{"No,","Item Name","Item Price","Item Count","Total"}));
                updateItemsNumbers();
                updateInvoiceTotalAmount(Integer.parseInt(invoiceNumberLabel.getText())-1);





            }
        });
    }

    private void deleteInvoiceHeader(){
        deleteInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




                ListSelectionModel model=allInvoiceTable.getSelectionModel();
                int selectedIndex=model.getMinSelectionIndex();


                InvoiceHeader.invoiceHeader.remove(selectedIndex);

                String invoiceNumber= (String) allInvoiceTable.getModel().getValueAt(selectedIndex,0);
                for(int i=invoiceLine.size();i>0;i--){
                    if( invoiceLine.get(i-1)[0].equals(invoiceNumber)){
                        invoiceLine.remove(i-1);
                    }

                }
                addinvoiceHeader();


            }
        });

    }

    private void addMenuBar() {
        myBar = new JMenuBar();
        file = new JMenu("File");
        saveMenuItem = new JMenuItem("Save", 's');
        openMenuItem = new JMenuItem("Open", 'o');
        file.add(openMenuItem);
        file.add(saveMenuItem);
        myBar.add(file);
        setJMenuBar(myBar);

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FileOperation save=new FileOperation();

                try {

                    String[] invoiceHeader= save.convertInvoiceHeaderToString();
                    String[] invoiceLine=save.convertInvoiceLineToString();
                    save.saveStringOf(invoiceHeader);
                    save.saveStringOf(invoiceLine);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        openMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                FileOperation fileOperation=new FileOperation();
                try {
                    fileOperation.convertStingHeaderToList();
                    fileOperation.convertStingLineToList();
                    addinvoiceHeader();
                    fileOperation.printLoadedData();
                } catch (IOException ex) {
                    System.out.println("File not found");
                }
            }
        });




    }




    public void addinvoiceHeader() {

        allInvoiceTable.setModel(new DefaultTableModel(Controller.updateTable(), new String[]{"No.","Date","Customer","Total"}));


    }


    public void ifRowSelected(){

        ListSelectionModel model=allInvoiceTable.getSelectionModel();


        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int selectedIndex=model.getMinSelectionIndex();

                try {

                    invoiceNumberLabel.setText((String) allInvoiceTable.getModel().getValueAt(selectedIndex, 0));
                    invoiceDateField.setText((String) allInvoiceTable.getModel().getValueAt(selectedIndex, 1));
                    customerNameField.setText((String) allInvoiceTable.getModel().getValueAt(selectedIndex, 2));
                    invoiceDetailsTable.setModel(new DefaultTableModel(Controller.getInvoiceLine(invoiceNumberLabel.getText()),new String[]{"No,","Item Name","Item Price","Item Count","Total"}));



                }catch (ArrayIndexOutOfBoundsException c){}

                InvoiceLine.setInvoiceNumber(String.valueOf(invoiceNumberLabel.getText()));

                invoiceDetailsTable.setModel(new DefaultTableModel(Controller.getInvoiceLine(invoiceNumberLabel.getText()),new String[]{"No,","Item Name","Item Price","Item Count","Total"}));


                updateItemsNumbers();



                updateInvoiceTotalAmount(selectedIndex);


            }
        });

    }
    //TODO update column "No." on the invoice_Line table
    public void  updateItemsNumbers(){
        for(int i=0;i<Controller.getInvoiceLine(invoiceNumberLabel.getText()).length;i++){

            invoiceDetailsTable.getModel().setValueAt(i+1,i,0);
        }

    }

    //Todo  Add invoice total amount to the total amount Label and in invoice header table
    public void updateInvoiceTotalAmount(int selectedIndex){


        try {

            double totalPrice=0;
            for(int i=0;i<Controller.getInvoiceLine(invoiceNumberLabel.getText()).length;i++){
                totalPrice=totalPrice+ (double)Controller.getInvoiceLine(invoiceNumberLabel.getText())[i][4];
            }

            allInvoiceTable.getModel().setValueAt(totalPrice,selectedIndex,3);
            totalInvoiceLabel.setText(String.valueOf(totalPrice));
        }catch (ArrayIndexOutOfBoundsException r){}

    }



    public void addItemLine(){


        addLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                AddLine addLine = new AddLine();
                addLine.setVisible(true);
                addLine.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            invoiceDetailsTable.setModel(new DefaultTableModel(Controller.getInvoiceLine(invoiceNumberLabel.getText()), new String[]{"No,", "Item Name", "Item Price", "Item Count", "Total"}));
                            updateInvoiceTotalAmount(Integer.parseInt(invoiceNumberLabel.getText()) - 1);
                            updateItemsNumbers();
                        }catch (NumberFormatException t){
                            System.out.println("No invoice has been selected");
                        }

                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }

        });



    }



    /**
     * Handle Actions For Create new invoice button
     */
    private void createNewInvoice(){

        createNewInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


//                GuiForm addInvoiceWindow=new GuiForm();
//                addInvoiceWindow.setTitle("Create invoice");
//                addInvoiceWindow.setSize(350,130);
//                addInvoiceWindow.setLocation(700,400);
//                addInvoiceWindow.setContentPane(panel1);

                CreateNewInvoice createFrame=new CreateNewInvoice();
                createFrame.setVisible(true);
                createFrame.addWindowListener(new WindowListener() {
                    @Override
                    public void windowOpened(WindowEvent e) {

                    }

                    @Override
                    public void windowClosing(WindowEvent e) {

                    }

                    @Override
                    public void windowClosed(WindowEvent e) {

                        addinvoiceHeader();

                    }

                    @Override
                    public void windowIconified(WindowEvent e) {

                    }

                    @Override
                    public void windowDeiconified(WindowEvent e) {

                    }

                    @Override
                    public void windowActivated(WindowEvent e) {

                    }

                    @Override
                    public void windowDeactivated(WindowEvent e) {

                    }
                });
            }


        });

    }




}
