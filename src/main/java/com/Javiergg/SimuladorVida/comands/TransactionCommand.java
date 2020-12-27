package com.Javiergg.SimuladorVida.comands;

import com.Javiergg.SimuladorVida.core.Item;
import com.Javiergg.SimuladorVida.Principal;
import com.Javiergg.SimuladorVida.core.ConexionBD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class TransactionCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            ItemStack item = player.getInventory().getItemInMainHand();
            boolean salida = true;
            int i = 0;

            Item resultant = null;
            while (salida)
            {
                Item itm = Principal.get().items.get(i);
                i++;
                if (item.getType().name().equals(itm.getNombre())){
                    salida = false;
                    resultant = itm;
                }
                if (i>= Principal.get().items.size()){
                    salida = false;
                }

            }

            if (resultant != null){

                ConexionBD conn = null;
                try {
                    conn = new ConexionBD();
                    if (conn == null){
                        throw new SQLException("error bd");
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "No se ha podido Conectar con la base de datos, desactivando plugin");
                    Principal.get().disable();

                }
                try {

                    conn.addMaterial(player,resultant,item.getAmount());
                    player.sendMessage(ChatColor.DARK_GRAY+"Has intercambiado "+item.getAmount()+" ("+(item.getAmount()*resultant.getCoste())+") de " + resultant.getTipo() + " a Simulador de Vida.");
                    item.setAmount(0);
                }catch(SQLException|NullPointerException e){
                    Principal.get().disable();
                    player.sendMessage(ChatColor.DARK_GRAY+"Ha ocurrido un error al ejecutar el comando");
                }

            }else{
                player.sendMessage(ChatColor.DARK_GRAY+"El item seleccionado no se puede transferir");
            }



        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
