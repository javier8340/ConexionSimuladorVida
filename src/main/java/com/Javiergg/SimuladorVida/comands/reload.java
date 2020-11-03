package com.Javiergg.SimuladorVida.comands;

import com.Javiergg.SimuladorVida.Principal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            if (player.isOp()){
                recargar();
            }
        }else{
            recargar();
        }
        return false;


    }
private void recargar(){
    Principal.get().onEnable();
}

}

