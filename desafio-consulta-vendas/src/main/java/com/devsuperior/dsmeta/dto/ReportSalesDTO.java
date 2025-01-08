package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.ReportSales;

import java.time.LocalDate;

public class ReportSalesDTO {
  private Long id;
  private LocalDate date;
  private Double amount;
  private String sellerName;

  public ReportSalesDTO() {
  }

  public ReportSalesDTO(Long id, LocalDate date, Double amount, String sellerName) {
    this.id = id;
    this.date = date;
    this.amount = amount;
    this.sellerName = sellerName;
  }

  public ReportSalesDTO(ReportSales reportSales) {
    id = reportSales.getId();
    date = reportSales.getDate();
    amount = reportSales.getAmount();
    sellerName = reportSales.getSellerName();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }
}
