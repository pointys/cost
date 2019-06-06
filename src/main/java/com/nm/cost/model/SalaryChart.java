package com.nm.cost.model;

import java.util.Date;

public class SalaryChart {
private Date salaryMonth;
private float salaryBasicTotal;
private float salaryCommTotal;
public Date getSalaryMonth() {
	return salaryMonth;
}
public void setSalaryMonth(Date salaryMonth) {
	this.salaryMonth = salaryMonth;
}
public float getSalaryBasicTotal() {
	return salaryBasicTotal;
}
public void setSalaryBasicTotal(float salaryBasicTotal) {
	this.salaryBasicTotal = salaryBasicTotal;
}
public float getSalaryCommTotal() {
	return salaryCommTotal;
}
public void setSalaryCommTotal(float salaryCommTotal) {
	this.salaryCommTotal = salaryCommTotal;
}

}
