/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrampubblicita;

import TelegramApi.*;   
import TelegramApi.User;
import TelegramApi.Update;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.Vector;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Miky
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException, IOException, ParserConfigurationException, SAXException {
        Functions fx = new Functions();
        File f = new File("dati.csv");
        String scelta = "0";
        do
        {
            System.out.println("\nScelta:");
            System.out.println("[1]GetMe\n[2]GetUpdates\n[3]SendMessage\n[4]Invia Pubblicità");

            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            scelta = myObj.nextLine();

            switch(scelta)
            {
                case "1":
                    User user = fx.getMe();
                    System.out.println(user.ToString());
                    break;
                case "2":
                    Vector<Update> ArrayUpdates = new Vector<>();
                    ArrayUpdates = fx.getUpdates();
                    for(Update element:ArrayUpdates)
                    {
                        System.out.println(element.ToString());
                        String testo = element.getMessage().getText();
                        
                        if(testo.contains("/citta") && testo.length() > 6) //guardo se è stato digitato citta e dopo c'è qualcosa
                        {
                            String citta = testo.substring(testo.indexOf(" ") + 1); //prendo tutto quello dopo /citta
                            Place place = fx.getCoordinate(citta); //prendo le coordinate
                            String lat = Float.toString(place.getLat());
                            String lon = Float.toString(place.getLon());
                            
                            //utente
                            String idChat = element.getMessage().getChat().getId();
                            String firstName = element.getMessage().getFrom().getFirst_name();
                            fx.SaveCsv(f, idChat, firstName, lat, lon); //salvo Csv
                        }
                    }
                    break;
                case "3":
                    System.out.println("\nchat id:");
                    String chat_id = myObj.nextLine();
                    
                    System.out.println("\ntesto:");
                    String text = myObj.nextLine();
                    System.out.println("");
                    
                    Message mess = fx.sendMessage(chat_id, text);
                    System.out.println(mess.ToString()); 
                    break;
                case "4":
                    Interface inter = new Interface();
                    inter.setVisible(true);
                    break;
            }
        }
        while(!scelta.equals("-1"));
    }
}
