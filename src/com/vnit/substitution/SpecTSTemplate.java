package com.vnit.substitution;

public class SpecTSTemplate {
    public String getSpecTSString() {
        String temp = "";
        temp+="import { async, ComponentFixture, TestBed } from '@angular/core/testing';\n";

        temp+="import { ^$00$01$m:constantsMap:cap_table_name$^MasterComponent } from './^$00$01$m:constantsMap:table_name$^-master.component';\n";

        temp+="describe('^$00$01$m:constantsMap:cap_table_name$^MasterComponent', () => {\n"+
        "\tlet component: ^$00$01$m:constantsMap:cap_table_name$^MasterComponent;\n"+
        "\tlet fixture: ^$00$01$m:constantsMap:cap_table_name$^Fixture<^$00$01$m:constantsMap:cap_table_name$^MasterComponent>;\n\n"+

        "\tbeforeEach(async(() => {\n"+
            "\t\tTestBed.configureTestingModule({\n"+
            "\t\t\tdeclarations: [ ^$00$01$m:constantsMap:cap_table_name$^MasterComponent ]\n"+
            "\t\t})\n"+
            "\t\t.compileComponents();\n"+
        "\t}));\n\n"+

        "\tbeforeEach(() => {\n"+
            "\t\tfixture = TestBed.createComponent(^$00$01$m:constantsMap:cap_table_name$^MasterComponent);\n"+
            "\t\tcomponent = fixture.componentInstance;\n"+
            "\t\tfixture.detectChanges();\n"+
        "\t});\n\n"+

        "\tit('should create', () => {\n"+
            "\t\texpect(component).toBeTruthy();\n"+
        "\t});\n\n"+
       "});\n\n";
        return temp;
    }

}
