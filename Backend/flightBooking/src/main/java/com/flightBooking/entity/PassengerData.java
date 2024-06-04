package com.flightBooking.entity;

public class PassengerData {
    private String name;
    private int age;
    private String mobileNo;
    private boolean checkInStatus;
     
	public PassengerData() {
		super();
	}


	public PassengerData(String name, int age, String mobileNo, boolean checkInStatus) {
		super();
		this.name = name;
		this.age = age;
		this.mobileNo = mobileNo;
		this.checkInStatus = checkInStatus;
	}





	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public boolean isCheckInStatus() {
		return checkInStatus;
	}


	public void setCheckInStatus(boolean checkInStatus) {
		this.checkInStatus = checkInStatus;
	}
     
	
	
	
}
