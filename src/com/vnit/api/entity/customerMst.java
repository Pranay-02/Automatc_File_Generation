#00$01$m:constantsMap:package_name$#

#00$01$m:constantsMap:imports$#

@Entity
@Table(name="#$00$01$m:constantsMap:table_name$#")

public class#$00$01$m:constantsMap:table_name$#Mst {
@ApiNodelProperty(required = #00$02$m:fld:ccode$m:fld_map:required$#, value = "(#00$02$m:fld:ccode$m:fld_map:size$#)")
#@Id#
#@GeneratedValue(strategy=GenerationType.IDENTITY)#
@Column(name = "#00$02$m:fld:ccode$m:fld_map:column_name$#")
private #00$02$m:fld:ccode$m:fld_map:column_type$# #00$02$m:fld:ccode$m:fld_map:column_name$#;
public #00$02$m:fld:ccode$m:fld_map:column_name$# get#00$02$m:fld:ccode$m:fld_map:column_name$#() {
	return #00$02$m:fld:ccode$m:fld_map:column_name$#
}

public void set#00$02$m:fld:ccode$m:fld_map:column_name$#() {
	this.#00$02$m:fld:ccode$m:fld_map:column_name$# = #00$02$m:fld:ccode$m:fld_map:column_name$#
}

@ApiNodelProperty(required = #00$02$m:fld:cname$m:fld_map:required$#, value = "(#00$02$m:fld:cname$m:fld_map:size$#)")
#@Id#
#@GeneratedValue(strategy=GenerationType.IDENTITY)#
@Column(name = "#00$02$m:fld:cname$m:fld_map:column_name$#")
private #00$02$m:fld:cname$m:fld_map:column_type$# #00$02$m:fld:cname$m:fld_map:column_name$#;
public #00$02$m:fld:cname$m:fld_map:column_name$# get#00$02$m:fld:cname$m:fld_map:column_name$#() {
	return #00$02$m:fld:cname$m:fld_map:column_name$#
}

public void set#00$02$m:fld:cname$m:fld_map:column_name$#() {
	this.#00$02$m:fld:cname$m:fld_map:column_name$# = #00$02$m:fld:cname$m:fld_map:column_name$#
}

}