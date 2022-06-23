package Model;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import static Model.InvoiceLine.invoiceLine;

public class InvoiceHeader {

    private static int invoiceNumber=0;
    private String invoiceDate;
    private String customerName;
    private double invoiceTotalValue;

    public static ArrayList<String[]> invoiceHeader=new ArrayList<String[]>();

    public InvoiceHeader(String date, String name){


        invoiceNumber=invoiceHeader.size()+1;
        this.invoiceDate=date;
        this.customerName=name;

        String[] newInvoiceHeader=new String[3];
        newInvoiceHeader[0]=String.valueOf(invoiceNumber);
        newInvoiceHeader[1]=invoiceDate;
        newInvoiceHeader[2]=customerName;

        invoiceHeader.add(newInvoiceHeader);

    }



    public static String getInvoiceNumber() {
        return String.valueOf(invoiceNumber);
    }

    public static void setInvoiceNumber(int invoiceNumber) {
        InvoiceHeader.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getInvoiceTotalValue() {
        return invoiceTotalValue;
    }

    public void setInvoiceTotalValue(double invoiceTotalValue) {
        this.invoiceTotalValue = invoiceTotalValue;
    }


}
