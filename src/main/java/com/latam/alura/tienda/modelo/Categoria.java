/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.latam.alura.tienda.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
//Muchos productos estan relacionados con una unica categoria, asi q es Many to One

@Entity //le indicamos que esta sera una entidad que debe realizar el mapeamiento
//realiza un mapeamiento para compararlo con el modelo de la BD, si la clase se llama Producto , la tabla de la BD tambn se llama asi
@Table(name="Categoria") //si el nombre de la BD y la clase son diferentes, se pone en la anotacion el nombre de la BD , haciend referencia q esta clase le pertenece
@Inheritance(strategy=InheritanceType.JOINED)
public class Categoria {//SI QUEREMOS HEREDAR ATRIBUTOS DE UNA TABLA A OTRA, PODEMOS USAR EL EXTENDS, PONER @Inheritance(strategy=InheritanceType.JOINED)
    
      @Id //le indicamos q este sera el ID
        @GeneratedValue(strategy = GenerationType.IDENTITY)  //le decimos que la reesponsabilidad de generar ese ID identificador sera de la BD
//Y EL TIPO DE GENERACION DE LA BD SERA IDENTITY,esto depende de la BD, ya con esto tenemos nuestro ID pa representar la lalve primaria en la tabla
  private long ID;
  private String nombre;
  
  public Categoria() {}
  
  //CONSTRUCTOR //Solo nombre por q el ID lo genera automaticamente, es autroincrementa
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
  
  
  
  //GETTERS Y SETTERS

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
