package com.arioatlas.hub_lbs;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SheypoorScraper extends AsyncTask<Void,Void,Document> {
    private static final String TAG = "SheypoorScraper";
    private String baseUrl;

    public SheypoorScraper(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    protected Document doInBackground(Void... voids) {
        Document tagertPage = null;
        try {
            tagertPage = Jsoup.connect(baseUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tagertPage;

    }

    @Override
    protected void onPostExecute(Document page) {
        Elements rows = page.getElementsByAttributeValue("type","application/ld+json");
        for(Element e : rows){
            if(!e.data().contains("itemListElement"))
                Log.d(TAG, "onPostExecute: "+e.data());
        }
    }
}
