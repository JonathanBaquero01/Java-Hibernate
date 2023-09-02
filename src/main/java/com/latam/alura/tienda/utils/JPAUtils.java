/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.latam.alura.tienda.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

 //EntityManagerFactory es el encargado de cerrar, abrir la conexion. Tienda es el nombre de la BD
        //Siempre q querramos guardar , borrar o hacer algo a la BD lo haremos a traves de EntityManagerFactory
//EN ESA CLASE DAMOS LA RESPONSABILIDAD DEL ENTITY MANAGER
public class JPAUtils {
    //nombre de la BD (ESTA EN EL XML persistence.xml en Othe Sources)
    private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("tienda");
    
    public static EntityManager getEntityManager(){

        return FACTORY.createEntityManager();

}
}
