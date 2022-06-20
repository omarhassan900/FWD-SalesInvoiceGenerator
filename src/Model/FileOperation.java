package Model;

import View.GuiForm;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;
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
        BufferedReader bufferedReader=new BufferedReader(new FileReader(path));
        List lines=new ArrayList<>();
        String data;

        while((data=bufferedReader.readLine())!=null){
            lines.add(data);
        }

        //TODO : Delete All data on the old Invoice
        for(int i=InvoiceHeader.invoiceHeader.size()-1;i>=0;i--){
            InvoiceHeader.invoiceHeader.remove(i);
        }



        for(int i=0;i<lines.size();i++){
            String x;
            x= (String) lines.get(i);
            var words= x.split(",");

            InvoiceHeader.invoiceHeader.add(words);

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
            System.out.println(i);
            InvoiceLine.invoiceLine.remove(i);
        }


        for(int i=0;i<lines.size();i++){
            String x;
            x= (String) lines.get(i);
            var words= x.split(",");
            InvoiceLine.invoiceLine.add(words);

        }

    }


}
