package com.Javiergg.SimuladorVida.core;

import com.Javiergg.SimuladorVida.Principal;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.logging.Level;

public class ConexionBD {


    private Connection conexion;

    public ConexionBD(){
        DatosArchivo datosConexion = Principal.get().conexion;
        this.conexion = null;
        try {
            Class.forName(datosConexion.getDriver());
            this.conexion = DriverManager.getConnection(datosConexion.getUrl(), datosConexion.getUsername(), datosConexion.getPass());
        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
        }

    }

    /**
     * @deprecated
     * @return
     * @throws SQLException
     */
    public Connection conectarMySQL() throws SQLException {


        Statement stmt = this.conexion.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM transacciones;");
        while (rs.next()){

            rs.getString("servicio");
            Principal.get().getLogger().log(Level.INFO,rs.getString("servicio") + " " + rs.getString("orden") + " " + rs.getString("usuario"));
        }
        return this.conexion;
    }

    public boolean addMaterial(Player user, Item item, int cantidad) throws SQLException {
        String sql = "INSERT INTO transacciones(usuario_Minecraft, material,tipo, cantidad) values('"+user.getName()+"','"+item.getNombre()+"','"+item.getTipo()+"','"+(cantidad*item.getCoste())+"')";
        Statement stm = this.conexion.createStatement();
        return stm.execute(sql);
    }

}
