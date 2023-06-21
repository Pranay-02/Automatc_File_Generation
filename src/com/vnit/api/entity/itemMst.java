package com.vnit.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "item")
public class itemMst {

@ApiModelProperty(required = false, value = "(size = 10)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "itcode")
private int itcode;

public int getItcode() {
	return itcode;
}

public void setItcode() {
	this.itcode = itcode;
}

@ApiModelProperty(required = true, value = "(size = 100) (required)")
@Column(name = "itname")
private String itname;

public String getItname() {
	return itname;
}

public void setItname() {
	this.itname = itname;
}

@ApiModelProperty(required = true, value = "(size = 5) (required)")
@Column(name = "itrate")
private java.math.BigDecimal itrate;

public java.math.BigDecimal getItrate() {
	return itrate;
}

public void setItrate() {
	this.itrate = itrate;
}

}