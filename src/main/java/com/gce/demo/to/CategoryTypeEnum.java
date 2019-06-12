package com.gce.demo.to;

public enum CategoryTypeEnum {
	GENERAL_STORE("GENERAL STORE", "STORE"),
	MEDICAL_STORE("MEDICAL STORE", "STORE"),
	SUPER_MARKET("SUPER MARKET", "SHOP"),
	MALL("MALL", "MALLS")
	;

    private final String value;
    private final String name; 

    private CategoryTypeEnum(String name, String value) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
}
