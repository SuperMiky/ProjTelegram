/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author pepe_michele
 */
public class Documento {
    
    Document document;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Element root, element;
    NodeList nodelist;

    public Documento()
    {
    };
    
    public Place GetCoordinate(String filename) throws ParserConfigurationException, SAXException, IOException
    {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        
        document = builder.parse(filename);
        root = document.getDocumentElement();
        Element element = (Element) root.getElementsByTagName("place").item(0); 
        Place place = new Place(Float.parseFloat(element.getAttribute("lat")), Float.parseFloat(element.getAttribute("lon")));
        return place;
    }
}

 