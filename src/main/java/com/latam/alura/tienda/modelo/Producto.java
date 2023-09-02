/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.latam.alura.tienda.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//Si hay una clase llamada Producto en la BD ira una tabla con el nombre Producto, y los atributos de la clase seran las columnas de la tabla


@Entity //le indicamos que esta sera una entidad que debe realizar el mapeamiento
//realiza un mapeamiento para compararlo con el modelo de la BD, si la clase se llama Producto , la tabla tambn se llama asi
@Table(name="Productos") //si el nombre de la BD y la clase son diferentes, se pone en la anotacion el nombre de la BD , haciend referencia q esta clase le pertenece
public class Producto {//SI QUEREMOS HEREDAR ATRIBUTOS DE UNA TABLA A OTRA, PODEMOS USAR EL EXTENDS, PONER @Inheritance(strategy=InheritanceType.JOINED)
    
        @Id //le indicamos q este sera el ID
        @GeneratedValue(strategy = GenerationType.IDENTITY)  //le decimos que rla reesponsabilidad de generar ese ID identificador sera de la BD
//Y EL TIPO DE GENERACION DE LA BD SERA IDENTITY,esto depende de la BD, ya con esto tenemos nuestro ID pa representar la lalve primaria en la tabla
        
	private Long id;
       // @Column(name="nombres")//de igual manera si el atributo de la BD es diferente a la de la clase, se deja claro aqui
	private String nombre; 
	private String descripcion; 
	private BigDecimal precio;
        private LocalDate fechaDeRegistro = LocalDate.now();
       // @Enumerated(EnumType.STRING)//Esto es si hay una clase enum -le digo que me guarde las categorias como stirng , que guarde las palabras y no el valor donde estan ubicaddas
    @ManyToOne (fetch=FetchType.LAZY)//Muchos productos estan relacionados con una unica categoria, asi q es Many to One
     //LO de fetch siempre ponerlo si tiene One (si termina en toOne), esto es para q traiga el recurso unicamente cuando sea necesario
    private  Categoria categoria;

    
    public Producto() {}
    
        //CONSTRUCTOR, ID no por q la da la BD y fecha ya nosotros le asignamos un valor
    public Producto(String nombre, String descripcion, BigDecimal precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }
        
        
        
        
        
        //GETTERS Y SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
        
        
        
    
}
