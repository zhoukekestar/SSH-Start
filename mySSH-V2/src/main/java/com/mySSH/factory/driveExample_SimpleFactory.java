package com.mySSH.factory;

public class driveExample_SimpleFactory {
	public static void main(String[] args){
		SF_Car car = SF_Driver.driverCar("Benz");
		car.drive();
	}
}


class SF_Driver {//工厂类角色
	
	public static SF_Car driverCar(String model) {
		if (model.equalsIgnoreCase("Benz"))
			return new SF_Benz();
		else 
			return new SF_Bmw();
	}
}

interface SF_Car{//抽象产品角色
	public void drive();
}

class SF_Benz implements SF_Car {//具体产品角色

	@Override
	public void drive() {
		System.out.println("Driving Benz");
	}

}

class SF_Bmw implements SF_Car {//具体产品角色

	@Override
	public void drive() {
		System.out.println("Driving Bmw");
		
	}
}