package com.example.ahmed.newsapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by master on 31/10/2016.
 */
public class ReedRss extends AsyncTask<Void,Void,Void> {

    Context context;
    ProgressDialog progressDialog;
    String urlAdress ;
    URL url;
    ArrayList<NewsItem>newsItems;
    RecyclerView recyclerView;

    public ReedRss(Context context, RecyclerView recyclerView,String link){

        urlAdress = link;
        this.recyclerView=recyclerView;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }
    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params){

        try {
            processXml(getData());
        }catch (Exception e){
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("No Enternet!!!");
            progressDialog.setMessage("Check Your Internet");
            progressDialog.setCancelable(false);
        }
        return null;
    }

    public void processXml(Document data)throws Exception{

        if (data != null){
            newsItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for (int i=0;i<items.getLength();i++){
                Node currentChiled = items.item(i);
                if (currentChiled.getNodeName().equalsIgnoreCase("item")){
                    NewsItem newsItem = new NewsItem();
                    NodeList itemChiled = currentChiled.getChildNodes();
                    for (int j=0;j<itemChiled.getLength();j++){
                        Node current = itemChiled.item(j);
                        if (current.getNodeName().equalsIgnoreCase("title")){
                            newsItem.setTitle(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("link")){
                            newsItem.setLink(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("author")){
                            newsItem.setAuthor(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("pubDate")){
                            newsItem.setPubDate(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("description")){
                            newsItem.setDescription(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("attachmentLoop")){
                            NodeList seconditems = current.getChildNodes();
                            for (int k=0;k<seconditems.getLength();k++){
                                Node secondChiled = seconditems.item(k);
                                if (secondChiled.getNodeName().equalsIgnoreCase("attachment.url")){
                                    newsItem.setBigPhoto(secondChiled.getTextContent());
                                }else if (secondChiled.getNodeName().equalsIgnoreCase("attachment_thumbnail")){
                                    newsItem.setSmallPhoto(secondChiled.getTextContent());
                                }
                            }
                        }
                    }
                    newsItems.add(newsItem);
}
            }
        }

    }

    public Document getData()throws Exception{

        try {
            url = new URL(urlAdress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();

        MyAdapter adapter = new MyAdapter(context,newsItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }
}
