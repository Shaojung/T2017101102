package com.example.teacher.t2017101102;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by teacher on 2017/10/11.
 */

public class MyDataHandler extends DefaultHandler {
    boolean isTitle = false;
    boolean isItem = false;
    boolean isLink = false;
    public ArrayList<RSSNewsItem> data = new ArrayList<>();
    RSSNewsItem item;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("title"))
        {
            isTitle = true;
        }
        if (qName.equals("link"))
        {
            isLink = true;
        }
        if (qName.equals("item"))
        {
            isItem = true;
            item = new RSSNewsItem();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("title"))
        {
            isTitle = false;
        }
        if (qName.equals("link"))
        {
            isLink = false;
        }
        if (qName.equals("item"))
        {
            isItem = false;
            data.add(item);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (isItem && isTitle)
        {
            item.title = new String(ch, start, length);
            // Log.d("NET", new String(ch, start, length));
        }
        if (isItem && isLink)
        {
            item.link = new String(ch, start, length);
            // Log.d("NET", new String(ch, start, length));
        }
    }
}
