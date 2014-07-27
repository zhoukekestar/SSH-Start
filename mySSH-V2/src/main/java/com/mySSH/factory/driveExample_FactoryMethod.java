package com.mySSH.factory;

public class driveExample_FactoryMethod {
	public static void main(String[] args){
		FM_Driver driver = new FM_BenzDriver();//定义抽象工厂  =  将其指定到具体工厂
		FM_Car car = driver.driverCar();		//通过抽象工厂指定的具体工厂 生成具体产品 并用抽象产品包装
		car.drive();							
	}
}


interface FM_Driver {//抽象工厂角色
	FM_Car driverCar();
}

class FM_BenzDriver implements FM_Driver{//具体工厂角色

	@Override
	public FM_Car driverCar() {
		return new FM_Benz();
	}
}

class FM_BmwDriver implements FM_Driver{//具体工厂角色

	@Override
	public FM_Car driverCar() {
		return new FM_Bmw();
	}
	
}

interface FM_Car{//抽象产品角色
	public void drive();
}

class FM_Benz implements FM_Car {//具体产品角色

	@Override
	public void drive() {
		System.out.println("Driving Benz");
	}

}

class FM_Bmw implements FM_Car {//具体产品角色

	@Override
	public void drive() {
		System.out.println("Driving Bmw");
		
	}
}