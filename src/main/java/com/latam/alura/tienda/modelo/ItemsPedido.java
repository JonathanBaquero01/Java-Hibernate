/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.latam.alura.tienda.modelo;

//Si hay una clase llamada Producto en la BD ira una tabla con el nombre Producto, y los atributos de la clase seran las columnas de la tabla

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity //le indicamos que esta sera una entidad que debe realizar el mapeamiento
//realiza un mapeamiento para compararlo con el modelo de la BD, si la clase se llama Producto , la tabla tambn se llama asi
@Table(name="ItemsPedido") //si el nombre de la BD y la clase son diferentes, se pone en la anotacion el nombre de la BD , haciend referencia q esta clase le pertenece
public class ItemsPedido {//SI QUEREMOS HEREDAR ATRIBUTOS DE UNA TABLA A OTRA, PODEMOS USAR EL EXTENDS, PONER @Inheritance(strategy=InheritanceType.JOINED)
       @Id //le indicamos q este sera el ID
        @GeneratedValue(strategy = GenerationType.IDENTITY)  //le decimos que rla reesponsabilidad de generar ese ID identificador sera de la BD
//Y EL TIPO DE GENERACION DE LA BD SERA IDENTITY,esto depende de la BD, ya con esto tenemos nuestro ID pa representar la lalve primaria en la tabla
	private Long id;
           // @Column(name="nombres")//de igual manera si el atributo de la BD es diferente a la de la clase, se deja claro aqui
	private int cantidad;
	private BigDecimal precioUnitario;
	
	@ManyToOne (fetch=FetchType.LAZY)//Un producto tiene multiples items pedidos
        //LO de fetch siempre ponerlo si tiene One (si termina en toOne),, esto es para q traiga el recurso unicamente cuando sea necesario
	private Producto producto;
	
	@ManyToOne (fetch=FetchType.LAZY)//tenemos un pedido con diferentes productos
          //LO de fetch siempre ponerlo si tiene One(si termina en toOne),, esto es para q traiga el recurso unicamente cuando sea necesario
	private Pedido pedido;

	public ItemsPedido() {	}

        //CONSTRUCTOR
	public ItemsPedido(int cantidad, Producto producto, Pedido pedido) {
		this.cantidad = cantidad;
		this.producto = producto;
		this.pedido = pedido;
		this.precioUnitario=producto.getPrecio();
	}

         //GETTERS Y SETTERS
	public Long getId() {
		return id;
	}


	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public BigDecimal getValor() {
		return this.precioUnitario.multiply(new BigDecimal(this.cantidad));
	}
}
