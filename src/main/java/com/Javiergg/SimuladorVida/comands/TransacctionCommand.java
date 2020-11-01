package com.Javiergg.SimuladorVida.comands;

import com.Javiergg.SimuladorVida.core.Item;
import com.Javiergg.SimuladorVida.Principal;
import com.Javiergg.SimuladorVida.core.ConexionBD;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.List;

public class TransacctionCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            ItemStack item = player.getInventory().getItemInMainHand();
            boolean salida = true;
            int i = 0;

            Item resultado = null;
            while (salida)
            {
                Item itm = Principal.get().items.get(i);
                i++;
                if (item.getType().name().equals(itm.getNombre())){
                    salida = false;
                    resultado = itm;
                }
                if (i>= Principal.get().items.size()){
                    salida = false;
                }

            }

            if (resultado != null){
                ConexionBD conn = new ConexionBD();
                try {

                    conn.addMaterial(player,resultado,item.getAmount());
                    player.sendMessage(ChatColor.DARK_GRAY+"Has intercambiado "+item.getAmount()+" ("+(item.getAmount()*resultado.getCoste())+") de " + resultado.getTipo() + " a Simulador de Vida.");
                    item.setAmount(0);
                }catch(SQLException e){
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
