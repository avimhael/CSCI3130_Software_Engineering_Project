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
public class CannabisProduct extends Product implements Serializable {
    private String percTHC, percCBD;

    public CannabisProduct () {
        super();
    }

    /**
     *  Creates an cannabis product with the name and thcP, cbdP.
     * @param name the cannabis's name
     * @param thcP the cannabis thc percentage
     * @param cbdP the cannabis cbd percentage
     */
    public CannabisProduct(String name, String thcP, String cbdP){
        super(name);
        percTHC = thcP;
        percCBD = cbdP;

    }

    /**
     * Get the thc percentage
     * @return the thc percentage
     */
    public String getPercTHC() {
        return percTHC;
    }

    /**
     * Get the cbd percentage
     * @return the cbd percentage
     */
    public String getPercCBD() {
        return percCBD;
    }

    /**
     * Set the thc percentage
     * @param thc the thc value
     */
    public void setPercTHC(String thc) {
        percTHC = thc;
    }

    /**
     * Set the cbd percentage
     * @param cbd the cbd value
     */
    public void setPercCBD(String cbd) {
        percCBD = cbd;
    }

    /**
     * Get the thc and cbd percentage
     * @return a string with thc and cbd percentage
     */
    public String getPercentage(){
        return "T: "+percTHC + " C: " + percCBD;
    }


}
