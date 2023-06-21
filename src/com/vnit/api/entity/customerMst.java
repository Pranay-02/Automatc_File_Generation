package com.vnit.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "customer")
public class customerMst {

@ApiModelProperty(required = false, value = "(size = 10)")
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name = "ccode")
private int ccode;

public int getCcode() {
	return ccode;
}

public void setCcode() {
	this.ccode = ccode;
}

@ApiModelProperty(required = true, value = "(size = 100) (required)")
@Column(name = "cname")
private String cname;

public String getCname() {
	return cname;
}

public void setCname() {
	this.cname = cname;
}

}