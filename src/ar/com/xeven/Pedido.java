package ar.com.xeven;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class Pedido {
    private int idPedido;
    private HashMap<Integer, Integer> productosPorPedido;
    private static final String dbName = "panaderia";
    private static final String dbUser = "root";
    private static final String dbPwd = "unafacil";

    public Pedido(HashMap<Integer, Integer> productosPorPedido) {
        this.productosPorPedido = productosPorPedido;
        crearPedido();
        productosPorPedido.forEach(this::crearProductosPorPedido);
    }

    private void crearProductosPorPedido(int idProducto, int cantidad){
        String sql = "INSERT INTO productosxpedido (`idPedido`,`idProducto`,`cantidad`) VALUES (?,?,?);";
        ConexionDB conexionDB = new ConexionDB(dbName, dbUser, dbPwd, sql);
        PreparedStatement pstmt = conexionDB.getPstmt();
        try {
            pstmt.setInt(1, idPedido);
            pstmt.setInt(2, idProducto);
            pstmt.setInt(3, cantidad);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conexionDB.cerrar();
        }
    }

    private void crearPedido(){
        int total = calcularTotal();
        String sql = "INSERT INTO pedidos (`total`) VALUES (?);";
        ConexionDB conexionDB = new ConexionDB(dbName, dbUser, dbPwd, sql);
        PreparedStatement pstmt = conexionDB.getPstmt();
        try {
            pstmt.setInt(1, total);
            this.idPedido = conexionDB.ejecutarRetornarKey();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conexionDB.cerrar();
        }
    }

    private int calcularTotal() {
        int total = 0;
        for(Integer idProducto : productosPorPedido.keySet())
            total += Producto.getPrecio(idProducto) * productosPorPedido.get(idProducto);
        return total;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public HashMap<Integer, Integer> getProductosPorPedido() {
        return productosPorPedido;
    }

    public void setProductosPorPedido(HashMap<Integer, Integer> productosPorPedido) {
        this.productosPorPedido = productosPorPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", productosPorPedido=" + productosPorPedido +
                '}';
    }

    public String verDetalles() {
        String detalles = "";

        return detalles;
    }
}
