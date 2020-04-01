package com.example.a3130_group17_project;

import java.util.HashMap;

/** Represents an entry in the product log.
 * @author      Tongtong Zhang
 * @author      Allan Jones
 * @author      Matt Wuard
 * @author      Ziheng Shi
 * @author      Omar Shams
 * @author      Yunzhong Xiao
 */
public class LogResource implements Comparable<LogResource>{

    private String productName, productPercentage;
    private int year, month, day;
    private long time;

    public LogResource () {
    }

    /**
     * Creates an entry for a given product on a given day
     * @param productName name of the product
     * @param productPercentage percentage of the product
     * @param year year of log date
     * @param month month of log date
     * @param day day of log date
     * @param time time of log date in milliseconds
     */
    public LogResource(String productName, String productPercentage, int year, int month, int day,long time){
      this.productName = productName;
      this.productPercentage = productPercentage;
      this.year = year;
      this.month = month;
      this.day = day;
      this.time= time;
    }

    /**
     * Get the log day
     * @return the log day
     */
    public int getDay() {
        return day;
    }
    /**
     * Get the log month
     * @return the log month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Get the log year
     * @return the log year
     */
    public int getYear() {
        return year;
    }

    /**
     * Get the log time
     * @return the log time
     */
    public long getTime() {
        return time;
    }

    /**
     * Set the log time
     * @param time time to set
     */
    public void setTime(long time){
        this.time=time;
    }

    /**
     * Set the log day
     * @param day day to set
     */
    public void setDay(int day){
        this.day=day;
    }

    /**
     * Set the log month
     * @param month month to set
     */
    public void setMonth(int month){
        this.month=month;
    }

    /**
     * Set the log year
     * @param year year to set
     */
    public void setYear(int year){
        this.year=year;
    }

    /**
     * Set the product name
     * @param productName productname to set
     */
    public void setProductName(String productName){
        this.productName = productName;
    }

    /**
     * Get the product name
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Set the product percentage
     * @param productPercentage the product percentage
     */
    public void setProductPercentage(String productPercentage){
        this.productPercentage = productPercentage;
    }

    /**
     * Get the product percentage
     * @return the product percentage
     */
    public String getProductPercentage() {
        return productPercentage;
    }

    /**
     * Compare to log resources based on time
     * @param that the resource to compare
     * @return the comparison result
     */
    public int compareTo(LogResource that) {
         if (this.time > that.time) {
             return 1;
         }
         else if (this.time < that.time) {
             return -1;
         }
         else {
             return 0;
         }
    }
}
