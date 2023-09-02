/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Pedido;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Admin
 */
//La clase Dao SIEMPRE tiene la responsabilidad de dar acceso a la BD, como eliminar, guardar, select...
//EntityManagerFactory es el encargado de cerrar, abrir la conexion. Tienda es el nombre de la BD
        //Siempre q querramos guardar , borrar o hacer algo a la BD lo haremos a traves de EntityManagerFactory
public class PedidoDao {
    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }
    
    //GUARDA
    public void guardar (Pedido pedido){
    this.em.persist(pedido);
    }
    
     //ACTUALIZA
    public void actualizar (Pedido pedido){
    this.em.merge(pedido);
    }
    
     //ELIMINA
    public void eliminar (Pedido pedido){
    pedido=this.em.merge(pedido);//la entidad debe estar en estado manage para q se pueda eliminar
    this.em.remove(pedido);
    }
    
    //CONSULTA POR ID
    public Pedido ConsultaPorID(Long ID){     
        return em.find(Pedido.class, ID);//aqui el ID es la posicion o codigo en la tabla de la BD del elemento q queremos buscar
    }
    
    //CONSULTAR TODOS LOS ELEMENTOS
    public List<Pedido> ConsultarTodos(){
    String jpql = "SELECT P FROM Pedido AS P";
    
    return em.createQuery(jpql, Pedido.class).getResultList();
    }
 
   //Suma todo el valorTotal de todos los elementos y da la sumatoria 
  //  EN HIBERNATE SE SUELE USAR JPQL, PERO TAMBIEN SE PUEDE USAR SQL
    public BigDecimal Sumatoria_Valor_Total(){
   String sql = "SELECT SUM(valorTotal) FROM pedido"; 
    
   /// return em.createQuery(sql, BigDecimal.class).getSingleResult();
     return (BigDecimal) em.createNativeQuery(sql).getSingleResult();
    }
  
    
    //Me da todos los productos, su cantidad vendida y la fecha de ultima venta
    //ESTE ESTA CON LENGUAJE SQL PERO TAMBN SE PUEDE CON JPQL
  public List<Object[]> obtenerResumenProductos() {
    // SQL query
    String sql = 
        "SELECT " + 
        "    p.nombre AS nombre_producto, " + 
        "    COALESCE(SUM(ip.cantidad), 0) AS cantidad_vendida, " + 
        "    COALESCE(MAX(pd.fecha), 'N/A') AS fecha_ultima_venta " + 
        "FROM productos p " + 
        "LEFT JOIN itemspedido ip ON p.id = ip.producto_id " + 
        "LEFT JOIN pedido pd ON ip.pedido_id = pd.id " + 
        "GROUP BY p.id, p.nombre " + 
        "ORDER BY p.nombre";
    
    // Ejecutar la consulta SQL nativa y obtener los resultados en la misma línea del return
    return em.createNativeQuery(sql).getResultList();
}
  
  //Asi se veria en JPQL
 public List<Object[]> relatorioDeVentas() {
    String jpql = "SELECT producto.nombre, "
            + "SUM(item.cantidad), "
            + "MAX(pedido.fecha) "
            + "FROM Pedido pedido "
            + "JOIN pedido.items item "
            + "JOIN item.producto producto "
            + "GROUP BY producto.nombre "
            + "ORDER BY SUM(item.cantidad) DESC, MAX(pedido.fecha) DESC"; // Cambio aquí
    return em.createQuery(jpql, Object[].class).getResultList();
}




}
