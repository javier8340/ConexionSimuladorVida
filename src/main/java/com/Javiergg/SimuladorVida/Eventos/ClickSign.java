package com.Javiergg.SimuladorVida.Eventos;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.EventListener;

public class ClickSign implements Listener {

    @EventHandler
    public void clickSign(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Material mat;
        try {
             mat = event.getClickedBlock().getType();
        }catch(Exception e){
             mat = Material.AIR;
        }
        if (mat.equals(Material.ACACIA_SIGN) || mat.equals(Material.SPRUCE_SIGN) || mat.equals(Material.BIRCH_SIGN) ||
                mat.equals(Material.CRIMSON_SIGN) || mat.equals(Material.DARK_OAK_SIGN) || mat.equals(Material.JUNGLE_SIGN)
                || mat.equals(Material.OAK_SIGN) || mat.equals(Material.WARPED_SIGN) || mat.equals(Material.ACACIA_WALL_SIGN)
                || mat.equals(Material.SPRUCE_WALL_SIGN) || mat.equals(Material.BIRCH_WALL_SIGN) || mat.equals(Material.CRIMSON_WALL_SIGN)
                || mat.equals(Material.DARK_OAK_WALL_SIGN) || mat.equals(Material.JUNGLE_WALL_SIGN) || mat.equals(Material.OAK_WALL_SIGN)
                || mat.equals(Material.WARPED_WALL_SIGN)){
            Sign s = (Sign) event.getClickedBlock().getState();
            if (s.getLine(0).toLowerCase().equals("[canjearapcu]")){
                player.performCommand("transaccion");
            }
        }
    }
}
