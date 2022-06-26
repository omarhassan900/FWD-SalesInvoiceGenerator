package Model;

import Controller.Controller;
import View.GuiForm;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileOperation extends Component {

    public String[] convertInvoiceHeaderToString(){

        String[] invoiceHeaderString=new String[InvoiceHeader.invoiceHeader.size()];


        for(int i=0;i<InvoiceHeader.invoiceHeader.size();i++){

                invoiceHeaderString[i]=InvoiceHeader.invoiceHeader.get(i)[0]+","+InvoiceHeader.invoiceHeader.get(i)[1]+","+InvoiceHeader.invoiceHeader.get(i)[2];
        }


        return invoiceHeaderString;

    }

    public String[] convertInvoiceLineToString(){

        String[] invoiceLineString=new String[InvoiceLine.invoiceLine.size()];


        for(int i=0;i<InvoiceLine.invoiceLine.size();i++){

            invoiceLineString[i]=InvoiceLine.invoiceLine.get(i)[0]+","+InvoiceLine.invoiceLine.get(i)[1]+","+InvoiceLine.invoiceLine.get(i)[2]+","+InvoiceLine.invoiceLine.get(i)[3];
        }

        return invoiceLineString;

    }

    public void saveStringOf(String[] invoiceHeaderString) throws IOException {

        String path=getPath();
        path=path+".csv";
        File fileWriter=new File(path);
        PrintWriter printWriter=new PrintWriter(fileWriter);


        for(int i=0;i<invoiceHeaderString.length;i++) {
            printWriter.println(invoiceHeaderString[i]);
        }
        printWriter.close();

    }

    public String getPath() throws FileNotFoundException {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        String filePath = fileChooser.getSelectedFile().getPath();
        if (result == JFileChooser.APPROVE_OPTION) {
            System.out.println(filePath);
        }
        return filePath;
    }


    public void convertStingHeaderToList() throws IOException {

        String path =getPath();
        String[] p=path.split(".");
        System.out.println(getFileExtension(path));

        if(getFileExtension(path).equals("csv")) {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            List lines = new ArrayList<>();
            String data;

            while ((data = bufferedReader.readLine()) != null) {
                lines.add(data);
            }

            //TODO : Delete All data on the old Invoice
            for (int i = InvoiceHeader.invoiceHeader.size() - 1; i >= 0; i--) {
                InvoiceHeader.invoiceHeader.remove(i);
            }


            for (int i = 0; i < lines.size(); i++) {
                String x;
                x = (String) lines.get(i);
                var words = x.split(",");

                InvoiceHeader.invoiceHeader.add(words);

            }
        }else{
            System.out.println("Wrong file format 'Should be csv'");
        }

    }






    public void convertStingLineToList() throws IOException {

        String path =getPath();
        BufferedReader bufferedReader=new BufferedReader(new FileReader(path));
        List lines=new ArrayList<>();
        String data;
        while((data=bufferedReader.readLine())!=null){
            lines.add(data);
        }

        //TODO : Delete All items on the old Invoice
        for(int i=InvoiceLine.invoiceLine.size()-1;i>=0;i--){
            InvoiceLine.invoiceLine.remove(i);
        }


        for(int i=0;i<lines.size();i++){
            String x;
            x= (String) lines.get(i);
            var words= x.split(",");
            InvoiceLine.invoiceLine.add(words);

        }

    }



    public void printLoadedData(){


        for(int i=0;i<InvoiceHeader.invoiceHeader.size();i++){
            for(int j=0;j<3;j++) {
                System.out.print(InvoiceHeader.invoiceHeader.get(i)[j]);
                System.out.print("  ");
            }
            System.out.println("{  ");

            Object[][] invoiceDetails=  Controller.getInvoiceLine(String.valueOf(InvoiceHeader.invoiceHeader.get(i)[0]));

            for(int k=0;k<invoiceDetails.length;k++){

                for (int l=0;l<4;l++){
                    System.out.print(invoiceDetails[k][l+1]);
                    System.out.print("  ");

                }
                System.out.println();



            }

            System.out.println("}");
            System.out.println("\n");




        }

    }


    public static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }


}
