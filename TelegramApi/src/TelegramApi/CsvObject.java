/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramApi;

/**
 *
 * @author Miky
 */
public class CsvObject {
    String firstName, idChat, lat, lon;

    public CsvObject(String idChat, String firstName, String lat, String lon) {
        this.idChat = idChat;
        this.firstName = firstName;
        this.lat = lat;
        this.lon = lon;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
    
    public String ToString()
    {
        return idChat + ";" + firstName + ";" + lat + ";" + lon;
    }
}
