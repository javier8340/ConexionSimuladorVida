package com.Javiergg.SimuladorVida.core;

import com.Javiergg.SimuladorVida.Principal;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class DatosArchivo {
    private String driver;
    private String url;
    private String username;
    private String pass;

//    private String pass;

    public DatosArchivo( FileConfiguration file){
        ConfigurationSection conexion = file.getConfigurationSection("datosConexion");
        this.url = conexion.getString("conexion") + conexion.getString("hostname") + ":" + conexion.getString("port")
                + "/" + conexion.getString("database")+ conexion.getString("options");

        this.username = conexion.getString("username");
        this.pass = conexion.getString("pass");
        this.driver = conexion.getString("driver");


        ConfigurationSection items = file.getConfigurationSection("items");

        createItems(items,"madera");
        createItems(items,"piedra");
        createItems(items,"tela");
        createItems(items,"agua");
        createItems(items,"comida");
        createItems(items,"petroleo");

    }
    private void createItems(ConfigurationSection file,String tipo){
        ArrayList<Object> items = (ArrayList<Object>) file.getList(tipo);
        for (int i=0;i<items.size();i++){
            String nombre = (String) items.get(i);
            i++;
            int valor = (int) items.get(i);
            Item iy = new Item(tipo,nombre,valor);
            System.out.println(iy);

            Principal.get().items.add(iy);
        }

    }


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
