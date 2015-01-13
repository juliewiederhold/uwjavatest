package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  protected static int numberOfObjectsCreated = 0;
  
  public static class AgeComparator implements Comparator<Person>{
	  public int compare(Person p1, Person p2){
		  return p1.age - p2.age;
	  }
  }
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    numberOfObjectsCreated++;
	name = n;
    age = a;
    salary = s;
    ssn = "";
  }
  
  public int count(){
	  return numberOfObjectsCreated;
  }

  public int getAge() {
    return age;
  }
  
  public String getName() {
    return name;
  }
  
  public double getSalary() {
    return salary;
  }
  
  public String getSSN() {
    return ssn;
  }
  
  public void setAge(int age){
	  if(age < 0){
		  throw new IllegalArgumentException("A person's age must be greater than 0");
	  } else {
		  this.age = age;
	  }
  }
  
  public void setName(String name){
	  if(name == null){
		  throw new IllegalArgumentException("A person's name cannot be null");
	  } else {
		  this.name = name;
	  }
  }
  
  public void setSalary(double salary){
	  this.salary = salary;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  
  public int compareTo(Person p){
	  if(this.salary < p.salary){
		  return 1;
	  } else if (this.salary > p.salary){
		  return -1;
	  } else {
		  return 0;
	  }
  }
  
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public boolean equals(Object obj) {
    if(obj instanceof Person){
    	Person p = (Person)obj;
    	return (this.name.equals(p.name) && this.age == p.age);
    }
	return false; 
  }
  

  public String toString() {
    return "[Person name:" + getName() + " age:" + getAge() + " salary:" + getSalary() + "]";
  }
  
  public static ArrayList<Person> getNewardFamily(){
	  Person ted = new Person("Ted", 41, 250000);
	  Person charlotte = new Person("Charlotte", 43, 150000);
	  Person michael = new Person("Michael", 22, 100000);
	  Person matthew = new Person("Matthew", 15, 0);
	  ArrayList<Person> neward = new ArrayList<Person>();
	  neward.add(ted);
	  neward.add(charlotte);
	  neward.add(michael);
	  neward.add(matthew);
	  return neward;
	  
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
