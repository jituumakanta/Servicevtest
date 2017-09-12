package com.ipvworld.servicevtest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by jitu on 9/11/2017.
 */
public class news2 implements Runnable {
    @Override
    public void run() {
        Document document = null;
        try {
            document = Jsoup.connect("http://aajtak.intoday.in/world-news.html").get();
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
                    //getting the title
                    String title = page.title();

                    if (title.contains("AajTak")) {
                        //method2.....getting the image by providing class name............................................
                        try {

                            Element hindititle = page.getElementsByClass("secArticleTitle").first();
                            String hindititletext = hindititle.text();

                            Element src = page.getElementsByClass("tabContener").first();//method1
                            //Element src = page.getElementById("highlight");//method2
                            Element src1 = src.select("img").last();
                            String imgsrc = src1.attr("abs:src");
                           /* System.out.println(singlepagelink);
                            System.out.println(title);
                            System.out.println(hindititletext);
                            System.out.println(imgsrc);*/
                            System.out.println("singlepagelink " + singlepagelink + " title " + hindititletext + " imgsrc" + imgsrc);
                        } catch (Exception e) {
                            System.out.println("imgsrc=null");
                        }

                    }
                } catch (Exception e) {
                    page = null;
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
