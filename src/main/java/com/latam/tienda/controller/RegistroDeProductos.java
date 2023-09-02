/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.latam.tienda.controller;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ClienteDao;
import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.ItemsPedido;
import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Admin
 */
public class RegistroDeProductos {
     public static void main(String[] args) {
       
        
        //EntityManagerFactory es el encargado de cerrar, abrir la conexion. Tienda es el nombre de la BD
        //Siempre q querramos guardar , borrar o hacer algo a la BD lo haremos a traves de EntityManagerFactory, ademas este creara de una todas las tablas que hallamos hecho en el modelo
          EntityManager em = JPAUtils.getEntityManager();
            em.getTransaction().begin();
            
             Registar_Producto(em);  
             Registar_Pedido(em);
   
 em.flush();//sincroniza los valores en la BD, es como un borrador para poder hacer rollback, pero ya COMMIT es definitivo
          em.getTransaction().commit();

          em.clear();//liberara todos los objetos q hibernate rastrea cuando se hacen operaciones, close ya desconecta de la BD
          em.close();
     
    }
     
     public static void Registar_Producto(EntityManager em){
           ProductoDao productoDao = new ProductoDao(em);
           CategoriaDao categoriaDao = new CategoriaDao(em);
          
         
           
       
         Categoria celulares = new Categoria("CELULARES");
         //le asigno valores al producto mediante su constructor
        Producto celular = new Producto("Samsumg", "Usado", new BigDecimal("5000"), celulares);
        
       /*//Tambn se podria asi: Simulamos que seteamos los atributos 
        celular.setNombre("Samsumg");
        celular.setDescripcion("Celular usado");
        celular.setPrecio(new BigDecimal("1000"));*/
       
          //le indicamos q las operaciones comienzan,
            //me permite GUARDAR el valor en  la BD, llamo al metodo
          productoDao.guardar(celular);
          categoriaDao.guardar(celulares);
          
          //metodo ACTUALIZAR
         productoDao.actualizar(celular);
          
          //metodo CONSULTA POR ID
        Producto producto =  productoDao.ConsultaPorID(1l);//la l al final es indicando que es tipo long, pero si se cambia long por int ya pasa
         System.out.println("CONSULTA POR ID Y DA EL NOMBRE: " +producto.getNombre());
         
         //metodo CONSULTAR TODOS
        List<Producto> productos = productoDao.ConsultarTodos();
        productos.forEach(prod-> System.out.println("CONSULTA TODOS LOS ELEMENTOS Y DA SU DESCRIPCION: "+prod.getDescripcion()));
        
        //metodo CONSULTA POR NOMBRE, Y CONSULTANDO POR NOMBRE PUEDO OBTENER LA DESCRIPCION
          List<Producto> productos2 = productoDao.ConsultaPorNombres("Xiaomi");
        productos2.forEach(prod-> System.out.println("CONSULTA ELEMENTOS POR NOMBRE Y DA LA DESCRIPCION: "+ prod.getDescripcion()));
         
        //metodo CONSULTA POR NOMBRE DE CATEGORIA , Y OBTENGO EL NOMBRE
          List<Producto> productos3 = productoDao.consultaPorNombreDeCategoria("CELULARES");
        productos3.forEach(prod-> System.out.println("CONSULTA ELEMENTOS POR NOMBRE DE CATEGORIA Y DA LA DESCRIPCION: "+ prod.getDescripcion()));
        
         //metodo consultarPrecioPorNombreDeProducto
         List<BigDecimal> precios = productoDao.consultarPreciosPorNombreDeProducto("Xiaomi");
for (BigDecimal precio : precios) {
    System.out.println("Precio para el producto 'Xiaomi': " + precio);
}
     }   
     
         public static void Registar_Pedido(EntityManager em){
                ProductoDao productoDao = new ProductoDao(em);
                  PedidoDao pedidoDao = new PedidoDao(em);
                    ClienteDao clienteDao = new ClienteDao(em);
                //me trae el producto
              Producto producto  = productoDao.ConsultaPorID(1l);//L diciendo q es long, recuerda que el id cambia por el producto q vaya a comprar
         Cliente cliente = new Cliente("Juan","1098528745");
             Pedido pedido = new Pedido(cliente);
             //voy al metodo de pedido(agregarItems), y este metodo pide el objeto ItemsPedido, el producto y el pedido
             pedido.agregarItems(new ItemsPedido(5, producto, pedido));
             
              clienteDao.guardar(cliente);//como le envio al pedido el cliente, el cliente lo debo guardar primero en la BD
             pedidoDao.guardar(pedido);
             
           BigDecimal ValorTotalVendido=  pedidoDao.Sumatoria_Valor_Total();
             System.out.println("VALOR TOTAL VENDIDO EN PEDIDOS: "+ValorTotalVendido);
             
          List<Object[]> relatorio = pedidoDao.obtenerResumenProductos();
System.out.println("Nombre producto, su cantidad vendida y la fecha de ultima venta");
for (Object[] obj : relatorio) {
    System.out.println(obj[0]);
    System.out.println(obj[1]);
    System.out.println(obj[2]);                 
}

            
         }
       
}
