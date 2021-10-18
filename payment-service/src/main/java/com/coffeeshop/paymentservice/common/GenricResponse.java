package com.coffeeshop.paymentservice.common;

import lombok.Data;

@Data
public class GenricResponse {

    private String genricHeader;
    public   String toString(){
        return " GenricResponse (genricHeader="+ this.genricHeader()+")";
    }

    private String genricHeader() {
        return  this.genricHeader;
    }
}
