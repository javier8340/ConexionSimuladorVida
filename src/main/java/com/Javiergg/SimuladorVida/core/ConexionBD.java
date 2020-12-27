package com.Javiergg.SimuladorVida.core;

import com.Javiergg.SimuladorVida.Principal;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.logging.Level;

public class ConexionBD {


    private Connection conexion;

    public ConexionBD() throws ClassNotFoundException, SQLException{
        DatosArchivo datosConexion = Principal.get().conexion;
        this.conexion = null;
        Class.forName(datosConexion.getDriver());
        this.conexion = DriverManager.getConnection(datosConexion.getUrl(), datosConexion.getUsername(), datosConexion.getPass());
        if (this.conexion == null){
            throw new SQLException("Error en bd");
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
    public void createDB() throws SQLException {
        Statement stm = this.conexion.createStatement();
        stm.execute("create table transacciones\n" +
                "(\n" +
                "    codigo_transaccion int auto_increment\n" +
                "        primary key,\n" +
                "    usuario_Minecraft  varchar(100) not null,\n" +
                "    material           varchar(50)  not null,\n" +
                "    tipo               varchar(50)  not null,\n" +
                "    cantidad           int(9)       not null\n" +
                ");");
    }

}
