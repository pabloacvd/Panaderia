package ar.com.xeven;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Producto {

    private int idProducto;
    private String nombre;
    private int precio;
    private int stock;

    private static final String dbName = "panaderia";
    private static final String dbUser = "root";
    private static final String dbPwd = "unafacil";

    public Producto(int idProducto, String nombre, int precio, int stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public static List<Producto> getProductos(){
        List<Producto> productos = new ArrayList<>();

        ConexionDB conexionDB = new ConexionDB(dbName,dbUser,dbPwd);
        ResultSet rs = conexionDB.consultar("SELECT * FROM productos WHERE stock > 0;");
        if(rs!=null)
            try {
                while(rs.next()) {
                    productos.add(new Producto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4)
                    ));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                conexionDB.cerrar();
            }
        return productos;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
