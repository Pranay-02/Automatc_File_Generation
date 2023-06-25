package com.vnit.api.entity;

import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import javax.persistence.Table;import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="customer")
public class customerMst {
@ApiNodelProperty(required = {size=10, column_name=ccode, column_type=int, primary_key=true, required=false}, value = "({size=10, column_name=ccode, column_type=int, primary_key=true, required=false})")
@Column(name = "{size=10, column_name=ccode, column_type=int, primary_key=true, required=false}")
private {size=10, column_name=ccode, column_type=int, primary_key=true, required=false} {size=10, column_name=ccode, column_type=int, primary_key=true, required=false};
public {size=10, column_name=ccode, column_type=int, primary_key=true, required=false} get{size=10, column_name=ccode, column_type=int, primary_key=true, required=false}() {
	return {size=10, column_name=ccode, column_type=int, primary_key=true, required=false}
}

public void set{size=10, column_name=ccode, column_type=int, primary_key=true, required=false}() {
	this.{size=10, column_name=ccode, column_type=int, primary_key=true, required=false} = {size=10, column_name=ccode, column_type=int, primary_key=true, required=false}
}

@ApiNodelProperty(required = {size=100, column_name=cname, column_type=String, primary_key=false, required=true}, value = "({size=100, column_name=cname, column_type=String, primary_key=false, required=true})")
@Column(name = "{size=100, column_name=cname, column_type=String, primary_key=false, required=true}")
private {size=100, column_name=cname, column_type=String, primary_key=false, required=true} {size=100, column_name=cname, column_type=String, primary_key=false, required=true};
public {size=100, column_name=cname, column_type=String, primary_key=false, required=true} get{size=100, column_name=cname, column_type=String, primary_key=false, required=true}() {
	return {size=100, column_name=cname, column_type=String, primary_key=false, required=true}
}

public void set{size=100, column_name=cname, column_type=String, primary_key=false, required=true}() {
	this.{size=100, column_name=cname, column_type=String, primary_key=false, required=true} = {size=100, column_name=cname, column_type=String, primary_key=false, required=true}
}

}