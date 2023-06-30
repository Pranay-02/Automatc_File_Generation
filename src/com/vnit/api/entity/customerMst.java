package com.vnit.api.entity;

import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.Table;import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="customer")
public class customerMst {

@ApiModelProperty(required = false, value = "(10)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "ccode")
private int ccode;

public int getccode() {
	return ccode;
}

public void setccode() {
	this.ccode = ccode;
}


@ApiModelProperty(required = true, value = "(100)")
@Column(name = "cname")
private String cname;

public String getcname() {
	return cname;
}

public void setcname() {
	this.cname = cname;
}

}