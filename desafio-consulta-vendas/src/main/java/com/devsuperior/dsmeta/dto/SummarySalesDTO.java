package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SummarySales;

public class SummarySalesDTO {

  private String sellerName;
  private Double total;

  public SummarySalesDTO() {
  }

  public SummarySalesDTO(String sellerName, Double total) {
    this.sellerName = sellerName;
    this.total = total;
  }

  public SummarySalesDTO(SummarySales summarySales) {
    sellerName = summarySales.getSellerName();
    total = summarySales.getTotal();
  }

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }
}
