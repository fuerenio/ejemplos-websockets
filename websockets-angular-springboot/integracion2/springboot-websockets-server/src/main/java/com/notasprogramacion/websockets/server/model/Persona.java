package com.notasprogramacion.websockets.server.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="app_persons")
public class Persona implements Serializable {

	@ApiModelProperty(notes = "Versión serial",name="serialVersionUID",required=true,value="8450477777998912341L")
	private static final long serialVersionUID = -8450461991418922031L;
	
	@ApiModelProperty(notes = "Id unico de la persona",name="personId",required=true,value="Ejemplo: 5c11f62337e8612f68925cdb")
	@Id
	private String personId;
	
	@ApiModelProperty(notes = "Nombre de la persona",name="nombre",required=true,value="Prueba nombre")
	private String nombre;
	
	@ApiModelProperty(notes = "Apellidos de la personae",name="apellidos",required=true,value="Prueba nombre")
	private String apellidos;
	
	@ApiModelProperty(notes = "Email de la persona",name="email",required=false,value="Prueba email test@gmail.com")
	private String email;

	@ApiModelProperty(notes = "Edad de la persona",name="edad",required=false,value="Prueba edad")
	private Integer edad;
	
	@ApiModelProperty(notes = "Direccion de la persona",name="direccion",required=false,value="Prueba direccion")
	private String direccion;
	
	@ApiModelProperty(notes = "Genero de la persona",name="sexo",required=false,value="Genero Masculino o Femenino")
	private Integer sexo;
	
	@ApiModelProperty(notes = "Fecha de registro",name="registerDate",required=false,value="Ejemplo: 2018-12-23 05:54:25.520Z")
	private Date registerDate;
}
