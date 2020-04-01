package com.example.a3130_group17_project;

import java.io.Serializable;

/** Represents an alcohol product.
 * @author      Tongtong Zhang
 * @author      Allan Jones
 * @author      Matt Wuard
 * @author      Ziheng Shi
 * @author      Omar Shams
 * @author      Yunzhong Xiao
 */
public class AlcoholProduct extends Product implements Serializable {
    private String APV;


    public AlcoholProduct() {
        super();
    }

    /**
     *  Creates an alcohol product with the name and apv.
     * @param name the alcohol's name
     * @param apv the alcohol percentage
     */
    public AlcoholProduct(String name, String apv){
        super(name);
        APV = apv;
    }

    /**
     * Get the alcohol percentage
     * @return the percentage
     */
    public String getAPV() {
        return APV;
    }

    /**
     * Set the alcohol percentage
     * @param APV the alcohol percentage
     */
    public void setAPV(String APV) {
        this.APV = APV;
    }

    /**
     * Get the alcohol percentage
     * @return the alcohol percentage preceded by "APV"
     */
    public String getPercentage(){
        return "APV: "+APV;
    }
}

