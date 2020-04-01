package com.example.a3130_group17_project;

import java.io.Serializable;

/** Abstract class for representing different product.
 * @author      Tongtong Zhang
 * @author      Allan Jones
 * @author      Matt Wuard
 * @author      Ziheng Shi
 * @author      Omar Shams
 * @author      Yunzhong Xiao
 */
public abstract class Product implements Serializable {
    private String name;

    public Product () {
    }

    /**
     * Create a product with a given name
     * @param name product name
     */
    public Product(String name){
        this.name = name;
    }

    /**
     * Get product name
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Set product name
     * @param n name to set
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Return a string with the product's percentages.
     * @return percentages
     */
    public abstract String getPercentage();

}
