package main

import "fmt"

type myInt int32

func (m myInt) hello() {
	fmt.Printf("这是daiwei int类型\n")
}

type person struct {
	name   string
	age    int
	gender string
}

func newPerson(name string, age int, gender string) *person {
	return &person{
		name:   name,
		age:    age,
		gender: gender,
	}
}

func main() {
	// var i int32 = 8
	// myint := myInt(i)
	// myint.hello()

	// // 直接构造
	// y := myInt(23) //强制类型转换
	// y.hello()

	// 结构体的初始化的三种方式
	// 第一种 直接创建对象
	var p1 person
	// new 创建的是指针对象
	// var p1 *person = new(person)
	p1.name = "daiwei"
	p1.age = 18
	p1.gender = "male"
	fmt.Printf("p1 value is %#v\n", p1)

	//第二种 kv方式创建对象
	p2 := person{
		name:   "daiwei1",
		age:    25,
		gender: "male",
	}
	fmt.Printf("p2 value is %#v\n", p2)

	//第三种 值列表方式
	p3 := person{
		"daiwei3",
		27,
		"male",
	}
	fmt.Printf("p3's value is %#v\n", p3)

	// 构造器实例化
	laoji := newPerson("laoji", 25, "male")
	fmt.Printf("laoji's value is %#v\n", *laoji)

	m := map[string]int{ 
		"daiwei": 20,
		"renchu": 18,
		"lml":    20,
		"laoji":  20,
	}
	fmt.Printf("value of map %#v\n", m)
}
