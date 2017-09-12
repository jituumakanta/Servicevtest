package com.ipvworld.servicevtest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by jitu on 9/11/2017.
 */
public class news1 implements Runnable {
    @Override
    public void run() {
        String mainPageUrl = "http://indianexpress.com/section/world/";
        String wordcontain = "The Indian Express";
        String srcClassName = "custom-caption";
        Document document = null;
        try {
            document = Jsoup.connect(mainPageUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element link = document.select("body").first();
        Elements links1 = link.select("a[href]");
        for (Element link1 : links1) {

            if (wordcount(link1.text()) > 3) {
                Document page;
                try {
                    String singlepagelink = link1.attr("abs:href");
                    page = Jsoup.connect(singlepagelink).timeout(100000).get();
                    String title = page.title();//GETTING THE TITLE
                    if (title.contains(wordcontain)) {
                        //method2.....getting the image by providing class name............................................
                        try {
                            Element src = page.getElementsByClass(srcClassName).first();
                            Element src1 = src.select("img").last();
                            String imgsrc = src1.attr("abs:src");
                            /*System.out.println(singlepagelink);
                            System.out.println(title);
                            System.out.println(imgsrc);*/
                            System.out.println("singlepagelink "+singlepagelink+" title "+title+" imgsrc "+imgsrc);
                            //sendDataToDatabase(singlepagelink, wordcontain, title, imgsrc, newsCatagory);//volleyadded
                        } catch (Exception e) {
                            System.out.println("imgsrc=null");
                        }

                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        try {  Thread.sleep(2000);  } catch (InterruptedException e) { e.printStackTrace(); }
    }

    static int wordcount(String s) {

        int i, c = 0, res;
        char ch[] = new char[s.length()];      //in string especially we have to mention the () after length
        for (i = 0; i < s.length(); i++) {
            ch[i] = s.charAt(i);
            if (((i > 0) && (ch[i] != ' ') && (ch[i - 1] == ' ')) || ((ch[0] != ' ') && (i == 0)))
                c++;
        }
        return c;
    }
}
