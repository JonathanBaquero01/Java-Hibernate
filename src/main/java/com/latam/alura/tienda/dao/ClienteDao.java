/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Cliente;
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
public class ClienteDao {
    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }
    
    //GUARDA
    public void guardar (Cliente cliente){
    this.em.persist(cliente);
    }
    
     //ACTUALIZA
    public void actualizar (Cliente cliente){
    this.em.merge(cliente);
    }
    
     //ELIMINA
    public void eliminar (Cliente cliente){
    cliente=this.em.merge(cliente);//la entidad debe estar en estado manage para q se pueda eliminar
    this.em.remove(cliente);
    }
    
    //CONSULTA POR ID
    public Cliente ConsultaPorID(Long ID){     
        return em.find(Cliente.class, ID);//aqui el ID es la posicion o codigo en la tabla de la BD del elemento q queremos buscar
    }
    
    //CONSULTAR TODOS LOS ELEMENTOS
    public List<Cliente> ConsultarTodos(){
    String jpql = "SELECT P FROM Cliente AS P";
    
    return em.createQuery(jpql, Cliente.class).getResultList();
    }
    
     //CONSULTAR POR FILTROS-NOMBRE
    public List<Cliente> ConsultaPorNombres(String nombre){
    String jpql = "SELECT P FROM Cliente AS P WHERE P.nombre=:nombre";//puedo poner mas filtros, puedo poner despues: AND P.descripcion=:descripcion
    
    return em.createQuery(jpql).setParameter("nombre", nombre).getResultList();
    }
    
      //CONSULTAR POR FILTROS-NOMBRE DE CATEGORIA
    public List<Cliente> consultaPorNombreDeCategoria (String nombre) {

String jpql="SELECT p FROM Cliente AS p WHERE p.categoria.nombre=: nombre";

return em.createQuery(jpql).setParameter("nombre",nombre).getResultList();
}

     //CONSULTAR POR FILTROS-PRECIO
    
public List<BigDecimal> consultarPreciosPorNombreDeCliente(String nombre) {
    String jpql = "SELECT P.precio FROM Cliente AS P WHERE P.nombre=:nombre";
    return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getResultList();
}

  

}
