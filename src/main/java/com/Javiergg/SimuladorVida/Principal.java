package com.Javiergg.SimuladorVida;

import com.Javiergg.SimuladorVida.Eventos.ClickSign;
import com.Javiergg.SimuladorVida.comands.TransactionCommand;
import com.Javiergg.SimuladorVida.comands.reload;
import com.Javiergg.SimuladorVida.core.Item;
import com.Javiergg.SimuladorVida.core.DatosArchivo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;

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


    }

    public void recarga(){
        if (!Bukkit.getPluginManager().isPluginEnabled(this)){
            Bukkit.getPluginManager().enablePlugin(this);
            Bukkit.getLogger().log(Level.INFO, "Reiniciando el Plugin");
        }

        items = new ArrayList<>();
        File config = new File(this.getDataFolder(), "config.yml");
        rutaConfig = config.getPath();

//        registerConfig();
        registerCommands();
        registerConfig();

        FileConfiguration file = YamlConfiguration.loadConfiguration(config);
        conexion = null;
        conexion = new DatosArchivo(file);
    }
    public void registerCommands(){

        this.getCommand("transaccion").setExecutor(new TransactionCommand());
        this.getCommand("recarga").setExecutor(new reload());
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new ClickSign(),this);
    }

    public void registerConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        rutaConfig = config.getPath();
        if(!config.exists()) {
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public void disable(){
        this.getCommand("transaccion").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
                commandSender.sendMessage(ChatColor.GRAY +"El plugin esta inactivo");
                return true;
            }
        });
    }







}
