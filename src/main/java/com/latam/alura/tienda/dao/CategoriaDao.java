/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Admin
 */
//La clase Dao SIEMPRE tiene la responsabilidad de dar acceso a la BD, como eliminar, guardar, select...
//EntityManagerFactory es el encargado de cerrar, abrir la conexion. Tienda es el nombre de la BD
        //Siempre q querramos guardar , borrar o hacer algo a la BD lo haremos a traves de EntityManagerFactory
public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }
    
    //GUARDA
    public void guardar (Categoria categoria){
    this.em.persist(categoria);
    }
    
    //ACTUALIZA
    public void actualizar (Categoria categoria){
    this.em.merge(categoria);
    }
    
    //ELIMINA
    public void eliminar (Categoria categoria){
   categoria=this.em.merge(categoria);//la entidad debe estar en estado manage para q se pueda eliminar
        this.em.remove(categoria);
    }
    
     //CONSULTA POR ID
    public Categoria ConsultaPorID(Long ID){     
        return em.find(Categoria.class, ID);//aqui el ID es la posicion o codigo en la tabla de la BD del elemento q queremos buscar
    }
    
    //CONSULTAR TODOS LOS ELEMENTOS
    public List<Categoria> ConsultarTodos(){
    String jpql = "SELECT P FROM Categoria AS P";
    
    return em.createQuery(jpql, Categoria.class).getResultList();
    }
    
    //CONSULTAR TODOS FILTROS
    public List<Categoria> ConsultaPorNombres(String nombre){
    String jpql = "SELECT P FROM Categoria AS P WHERE P.nombre=:nombre";//puedo poner mas filtros, puedo poner despues: AND P.descripcion=:descripcion
    
    return em.createQuery(jpql).setParameter("nombre", nombre).getResultList();
    }
    
    
    
    
}
