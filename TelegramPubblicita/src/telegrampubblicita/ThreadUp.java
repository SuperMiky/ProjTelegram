/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrampubblicita;

import TelegramApi.CsvObject;
import TelegramApi.Functions;
import TelegramApi.Place;
import TelegramApi.Update;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author pepe_michele
 */
public class ThreadUp extends Thread {
    HashMap<Long,Long> map;
    public ThreadUp(){
        
        map = new HashMap<Long,Long>();
    }
    @Override
    public void run() {
        Functions fx = new Functions();
        File f = new File("dati.csv");
        List<CsvObject> list = fx.ReadCsv(f);
        int size = -2;
        while(true)
        {
            Vector<Update> ArrayUpdates = new Vector<>();
            ArrayUpdates = fx.getUpdates();
            for(Update element:ArrayUpdates)
            {
                long idUser = Long.parseLong(element.getMessage().getFrom().getId()); //id user
                long idMess = Long.parseLong(element.getMessage().getMessage_id()); //id messaggio
                
                if(!map.containsValue(idUser) || (map.containsValue(idUser) && idMess > map.get(idMess))) //se lo user non c'è o c'è e il messaggio è nuovo
                {
                    map.put(idUser, idMess);
                    System.out.println(element.ToString());
                    String testo = element.getMessage().getText();

                    if(testo.contains("/citta") && testo.length() > 6) //guardo se è stato digitato citta e dopo c'è qualcosa
                    {
                        String citta = testo.substring(testo.indexOf(" ") + 1); //prendo tutto quello dopo /citta
                        Place place;
                        try {
                            place = fx.getCoordinate(citta); //prendo le coordinate
                            String lat = Float.toString(place.getLat());
                            String lon = Float.toString(place.getLon());
                            //utente
                            String idChat = element.getMessage().getChat().getId();
                            String firstName = element.getMessage().getFrom().getFirst_name();
                            fx.SaveCsv(f, idChat, firstName, lat, lon); //salvo Csv
                        } catch (UnsupportedEncodingException ex) {
                            Logger.getLogger(ThreadUp.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadUp.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParserConfigurationException ex) {
                            Logger.getLogger(ThreadUp.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SAXException ex) {
                            Logger.getLogger(ThreadUp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        }
    }
    
    private void ottieni()
    {
        
    }
}
