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
    private Estado estado;

    public Pedido(HashMap<Integer, Integer> productosPorPedido) {
        this.productosPorPedido = productosPorPedido;
        crearPedido();
        productosPorPedido.forEach(this::crearProductosPorPedido);
    }

    public Pedido(int idPedido) {
        // cargar el pedido de la base de datos
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
        String sql = "INSERT INTO pedidos (`total`,`estado`) VALUES (?,?);";
        ConexionDB conexionDB = new ConexionDB(dbName, dbUser, dbPwd, sql);
        PreparedStatement pstmt = conexionDB.getPstmt();
        try {
            pstmt.setInt(1, total);
            pstmt.setInt(2, Estado.CONFIRMADO.ordinal());
            this.idPedido = conexionDB.ejecutarRetornarKey();
            this.estado = Estado.CONFIRMADO;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conexionDB.cerrar();
        }
    }
    public static void cancelarPedido(int idPedido){
        String sql = "UPDATE pedidos SET estado = ? WHERE idPedido = ?";
        ConexionDB conexionDB = new ConexionDB(dbName, dbUser, dbPwd, sql);
        PreparedStatement pstmt = conexionDB.getPstmt();
        try {
            pstmt.setInt(1, Estado.CANCELADO.ordinal());
            pstmt.setInt(2,idPedido);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conexionDB.cerrar();
        }
    }

    public int calcularTotal() {
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
        String detalles = "Pedido: "+idPedido+"\n";
        int total = 0;
        for(Integer idProducto: productosPorPedido.keySet()){
            Producto p = new Producto(idProducto);
            int cantidad = productosPorPedido.get(idProducto);
            int subtotal = cantidad*p.getPrecio();
            total += subtotal;
            detalles += p.getNombre() + ": " + cantidad+" x $"+p.getPrecio()+" = $"+subtotal+"\n";
        }
        detalles += "Total: $"+total;
        return detalles;
    }
}
