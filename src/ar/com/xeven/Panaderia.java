package ar.com.xeven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Panaderia {

    public static void main(String[] args) {
        System.out.println("Welcome to the panaderia");
        // muestro lo que tengo en stock
        List<Producto> productos = Producto.getProductos();
        for(Producto producto:productos)
            System.out.println(producto);


        int op=0;
        System.out.println("Nueva orden. Indique 0 para salir.");
        HashMap<Integer, Integer> productosPorPedido = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Indique el ID de producto: ");
            int idProd = Integer.parseInt(sc.nextLine());
            System.out.print("Indique la cantidad deseada: ");
            int cantidad = Integer.parseInt(sc.nextLine());
            productosPorPedido.put(idProd, cantidad);
            System.out.print("¿Desea finalizar? (1=No, 0=Si) ");
            op = Integer.parseInt(sc.nextLine());
        }while(op!=0);
        Pedido pedido = new Pedido(productosPorPedido); //Crea el pedido en la DB

      /*
      pagar un pedido existente:
        Pedido pedido1 = new Pedido(25);
        int vuelto = pedido1.pagar(40);
       */

    /*
        pagar el pedido actual:
        System.out.println(pedido.verDetalles());
        System.out.println("¿Está ok?");
        if(usuarioconfirma){
            pedido.pagar();
        }
    */

    }
}
