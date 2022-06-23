package Model;


import java.util.ArrayList;

public class InvoiceLine {

    public static ArrayList<Object[]> invoiceLine=new ArrayList<Object[]>();


    public static   String invoiceNumber;



    String itemName;
    double itemPrice;
    int itemCount;

    double itemTotalPrice;


    public InvoiceLine(String itemName,double itemPrice,int itemCount){

//        this.invoiceNumber=invoiceNumber;
        this.itemName=itemName;
        this.itemPrice=itemPrice;
        this.itemCount=itemCount;

        itemTotalPrice=itemPrice*itemCount;

//        System.out.println(itemTotalPrice);


        Object[] invoiceRecord=new Object[4];
        invoiceRecord[0]= invoiceNumber;
        invoiceRecord[1]=itemName;
        invoiceRecord[2]=itemPrice;
        invoiceRecord[3]=itemCount;
        invoiceLine.add(invoiceRecord);




//        for(int i=0;i<invoiceLine.size();i++){
//
//            System.out.print(invoiceLine.get(i)[0]);
//            System.out.print("   ");
//            System.out.print(invoiceLine.get(i)[1]);
//            System.out.print("   ");
//            System.out.print(invoiceLine.get(i)[2]);
//            System.out.print("   ");
//            System.out.println(invoiceLine.get(i)[3]);
//        }



    }

    /**
     * This Method search for a certain items on all invoiceLines
     * @param invoiceNumber number of invoice to serach for
     * @return return 2D Object with the invoice details
     */


    public double calculateTotal(){

        return itemCount*itemPrice;
    }



    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public static void setInvoiceNumber(String invoiceNo) {
        invoiceNumber = invoiceNo;
    }








}
