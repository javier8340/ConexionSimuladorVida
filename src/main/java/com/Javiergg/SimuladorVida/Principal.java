package com.Javiergg.SimuladorVida;

import com.Javiergg.SimuladorVida.comands.TransacctionCommand;
import com.Javiergg.SimuladorVida.core.Item;
import com.Javiergg.SimuladorVida.core.DatosArchivo;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;

public class Principal extends JavaPlugin {

    private static Principal plugin  = null;

    public static Principal get(){
        return plugin;
    }
    public String rutaConfig;
    public ArrayList<Item> items = new ArrayList<>();
    public DatosArchivo conexion;


    @Override
    public void onEnable(){
        Principal.plugin = this;
        registerConfig();
        registerCommands();
        registerConfig();
        registerEvents();


        FileConfiguration file = getConfig();
        conexion = new DatosArchivo(file);
        System.out.println("asdd");


    }

    public void registerCommands(){
        this.getCommand("transaccion").setExecutor(new TransacctionCommand());
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
    }

    public void registerConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        rutaConfig = config.getPath();
        if(!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }







}
