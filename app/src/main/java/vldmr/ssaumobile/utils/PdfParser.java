package vldmr.ssaumobile.utils;

import android.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PdfParser {
    Map<String,Map<String,List<String>>> workers;
    public PdfParser() {
        Document doc=new Document();
        PdfReader reader=null;
        String s=null;
        List<String> listPages=new ArrayList<>();
        List<String> listWorker=new ArrayList<>();
        List<String> listWorker2=new ArrayList<>();


        try {
           reader =new PdfReader("assets/phones/phonebook.pdf");
            int i=reader.getNumberOfPages();
            while (i>0){
            s=PdfTextExtractor.getTextFromPage(reader,i);
                String s1=s.substring(86);
                listPages.add(s1);
                i--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ///разделяем на строки чере \n
        for (String item:listPages){
           String[] array= item.split("\n");
            List<String> list= Arrays.asList(array);
            for(String worker:list){listWorker.add(worker);}
        }

        /////////////удаляем лищнее,совмещаем телефоны
        int f = 0;
        for (int i=0;i<listWorker.size();i++){
            String s2=listWorker.get(i);
            Pattern pattern=Pattern.compile("[А-Я]+.");
            Matcher m=pattern.matcher(s2);

            if (m.find()){
                listWorker2.add(s2);
                f=listWorker2.indexOf(s2);
            }

            Pattern pattern2=Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}.*$");
            Matcher m2=pattern2.matcher(s2);
            if (m2.matches()){
                String newS=listWorker2.get(f).concat(s2);
                listWorker2.remove(f);
                listWorker2.add(newS);
            }
        }
        ////////////организуем Map
        String name = null;
        workers=new HashMap<>(listWorker2.size());
        for (String str:listWorker2){

            Pattern pattern=Pattern.compile("[А-ЯЁ]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+");
            Matcher m=pattern.matcher(str);
            if (m.find()){name=m.group();}
            else Log.d("GG", "GG");

            Pattern pattern2=Pattern.compile("((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}");
            Matcher m2=pattern2.matcher(str);
            List<String> telArray=new ArrayList<>();
            while (m2.find()){
                String tel=m2.group();
                telArray.add(tel);}

            Pattern pattern3=Pattern.compile("([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}");
            Matcher m3=pattern3.matcher(str);
            List<String> emailList=new ArrayList<>();
            while (m3.find()){
                String e=m3.group();
                emailList.add(e);}

            Map<String,List<String>> email_tel=new HashMap<>();

            email_tel.put("Телефоны",telArray);
            email_tel.put("Почты",emailList);
            workers.put(name,email_tel);




        }



        Log.d("GG", s);
    }

    public Map<String, Map<String, List<String>>> getWorkers() {
        return workers;
    }

    public void setWorkers(Map<String, Map<String, List<String>>> workers) {
        this.workers = workers;
    }
}
