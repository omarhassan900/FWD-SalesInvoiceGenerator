package Controller;

import Model.InvoiceHeader;
import Model.InvoiceLine;

public class Controller {



    public static   Object[][]  updateTable(){
        Object[][] invoiceHeaderTableBody = new Object[InvoiceHeader.invoiceHeader.size()][4];


        for(int i=0 ;i< InvoiceHeader.invoiceHeader.size();i++){
            for(int j=0;j<3;j++){

                invoiceHeaderTableBody[i][j]=InvoiceHeader.invoiceHeader.get(i)[j];

            }
        }
        return invoiceHeaderTableBody;

    }


    public static Object[][] getInvoiceLine(String invoiceNumber) {




        //TODO Count the number of items for the input invoice
        int numberOfItemsOfInvoice = 0;
        for (int i = 0; i < InvoiceLine.invoiceLine.size(); i++) {


            if (String.valueOf( InvoiceLine.invoiceLine.get(i)[0]).equals(invoiceNumber)) {
                numberOfItemsOfInvoice++;
            }

        }

        //ToDO add invoce line details to the new object
        int row = 0;

        Object[][] invoiceLineTableBody = new Object[numberOfItemsOfInvoice][5];
        for (int i = 0; i < InvoiceLine.invoiceLine.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (String.valueOf( InvoiceLine.invoiceLine.get(i)[0]).equals(invoiceNumber)) {
                    invoiceLineTableBody[row][j + 1] = InvoiceLine.invoiceLine.get(i)[j + 1];
                }
            }
            if (String.valueOf( InvoiceLine.invoiceLine.get(i)[0]).equals(invoiceNumber)) {row++;}
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


}
