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
    public static Object[][] getInvoiceLine(String invoiceNumber) {




        //TODO Count the number of items for the input invoice
        int numberOfItemsOfInvoice = 0;
        for (int i = 0; i < invoiceLine.size(); i++) {


            if (String.valueOf( invoiceLine.get(i)[0]).equals(invoiceNumber)) {
                numberOfItemsOfInvoice++;
            }

        }

        //ToDO add invoce line details to the new object
        int row = 0;

        Object[][] invoiceLineTableBody = new Object[numberOfItemsOfInvoice][5];
        for (int i = 0; i < invoiceLine.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (String.valueOf( invoiceLine.get(i)[0]).equals(invoiceNumber)) {
                    invoiceLineTableBody[row][j + 1] = invoiceLine.get(i)[j + 1];
                }
            }
            if (String.valueOf( invoiceLine.get(i)[0]).equals(invoiceNumber)) {row++;}
        }





        //TODO Calculate the total value of each line to send it to the table

        for(int i=0;i<invoiceLineTableBody.length;i++){
            double itemPrice;
            int itemCount;
            try {
                 itemPrice = Double.parseDouble((String) invoiceLineTableBody[i][2]);
                 itemCount = Integer.parseInt((String) invoiceLineTableBody[i][3]);
            }catch (ClassCastException r){
                itemPrice=(double) invoiceLineTableBody[i][2];
                itemCount=(int)invoiceLineTableBody[i][3];
            }
            invoiceLineTableBody[i][4]=itemCount*itemPrice;

        }


        //Print to validate
//        for(int i=0;i<invoiceLineTableBody.length;i++){
//            for (int j=0;j<5;j++){
//
//                System.out.print(invoiceLineTableBody[i][j]);
//                System.out.print("  ");
//
//            }
//            System.out.println();
//
//        }







        return invoiceLineTableBody;

    }


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
