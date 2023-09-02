package com.latam.alura.tienda.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Si hay una clase llamada Producto en la BD ira una tabla con el nombre Producto, y los atributos de la clase seran las columnas de la tabla


@Entity //le indicamos que esta sera una entidad que debe realizar el mapeamiento
//realiza un mapeamiento para compararlo con el modelo de la BD, si la clase se llama Producto , la tabla tambn se llama asi
@Table(name="Pedido") //si el nombre de la BD y la clase son diferentes, se pone en la anotacion el nombre de la BD , haciend referencia q esta clase le pertenece
public class Pedido { //SI QUEREMOS HEREDAR ATRIBUTOS DE UNA TABLA A OTRA, PODEMOS USAR EL EXTENDS, PONER @Inheritance(strategy=InheritanceType.JOINED)
	   @Id //le indicamos q este sera el ID
        @GeneratedValue(strategy = GenerationType.IDENTITY)  //le decimos que rla reesponsabilidad de generar ese ID identificador sera de la BD
//Y EL TIPO DE GENERACION DE LA BD SERA IDENTITY,esto depende de la BD, ya con esto tenemos nuestro ID pa representar la lalve primaria en la tabla
	private Long id;
           // @Column(name="nombres")//de igual manera si el atributo de la BD es diferente a la de la clase, se deja claro aqui
	private LocalDate fecha=LocalDate.now();
	private BigDecimal valorTotal=new BigDecimal(0);
	// @Enumerated(EnumType.STRING)//Esto es si hay una clase enum -le digo que me guarde las categorias como stirng , que guarde las palabras y no el valor donde estan ubicaddas
    @ManyToOne (fetch=FetchType.LAZY)//un cliente tiene multiples pedidos, pero como lo hacemos desde el pedido pues pasa de OneToMany a ManyToOne, pero desde la clase cliente seria OneToMany
	//LO de fetch siempre ponerlo si tiene One(si termina en toOne),, esto es para q traiga el recurso unicamente cuando sea necesario
    private Cliente cliente;
	
	//@ManyToMany //Un pedido puede tener multiples productos  y esos multiples productos y esos  multiples productos  pueden encontrarse en muchos pedidos, osea 1 persona realizo un pedido de 1 libro, y una 2da persona tambn realizo un pedido de un libro, adicional esa 1ra persona puede realizar un pedido de 1 libro de ciencias y 1 libro de matematicas, tonces tendra multiples productos
     // @JoinTable(name="items_pedido") //Permite relacionar los productos con los pedidos, creando asi la tabla items_pedido, pa q se relacionen, o tambn puedo crear otra clase llamada item_pedido
       
  
        @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)//si se llega a crear otra tabla o no conectan las relaciones, le decimos que ItemsPedido esta mapeada por la clase pedido, osea lo obligamos a conectar
        //CON cascade le digo q cada ves que alla una alteracion en PEDIDO, entonces haga una alteracion en items pedido, que haga las modificaciones automaticamente,  PERO ES MEJOR CREAR OTRO ITEMSPEDIDODAO
          //Como vamos a tener multiples productos tenemos que hacer una lista que almacene elementos del tipo producto
        //generalmente cuando es por lista es OneToMany
	private List<ItemsPedido> items = new ArrayList<>();
	
        
             //CONSTRUCTOR, ID no por q la da la BD y fecha ya nosotros le asignamos un valor
	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido() {}
	
        //AGREGAR LOS ITEMS A LA LISTA ItemsPedido
	public void agregarItems(ItemsPedido item) {
		item.setPedido(this);
		this.items.add(item);
		this.valorTotal= this.valorTotal.add(item.getValor());
	}
        
        
        //GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

    public List<ItemsPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemsPedido> items) {
        this.items = items;
    }
        
        

}
