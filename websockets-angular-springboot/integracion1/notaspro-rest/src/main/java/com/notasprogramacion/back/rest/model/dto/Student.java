package com.notasprogramacion.back.rest.model.dto;

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
@Document(collection="school")
public class Student implements Serializable {

	@ApiModelProperty(notes = "Versión serial",name="serialVersionUID",required=true,value="8450477777998912341L")
	private static final long serialVersionUID = -8450461991418922031L;
	
	//@ApiModelProperty(notes = "Id del estudiante",name="id",required=true,value="100")
	//private Integer id;
	
	@ApiModelProperty(notes = "Id del estudiante en la BD Mongo",name="mongoId",required=true,value="5c11f62337e8612f68925cdb")
	@Id
	private String mongoId;
	
	@ApiModelProperty(notes = "Nombre del estudiante",name="name",required=true,value="prueba nombre")
	private String name;
	
	@ApiModelProperty(notes = "Clase del estudiante",name="cls",required=true,value="prueba clase")
	private String cls;
	
	@ApiModelProperty(notes = "Pais del estudiante",name="country",required=false,value="prueba país")
	private String country;

	@ApiModelProperty(notes = "Fecha de creacion",name="registerDate",required=false,value="prueba fecha")
	private Date registerDate;
}
