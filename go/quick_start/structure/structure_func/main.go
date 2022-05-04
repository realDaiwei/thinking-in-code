package main

import "fmt"

// 定义结构体
type person struct {
	name   string
	age    int
	addr   string
	gender string
}

type cat struct {
	name    string
	age     float64
	catType string
}

// 构造函数：约定俗成用new作为开头
// 返回一般情况都是结构体指针（具体情况具体定）
// 当结构体比较大的时候尽量使用结构体指针，减少程序的内存开销
func newPerson(name, addr, gender string, age int) *person {
	return &person{
		name:   name,
		addr:   addr,
		age:    age,
		gender: gender,
	}
}

func newCat(name, catType string, age float64) *cat {
	return &cat{
		name:    name,
		age:     age,
		catType: catType,
	}
}

func main() {
	p := newPerson("daiwei", "songjiang shanghai", "male", 26)
	fmt.Printf("p's value is %#v\n", *p)
	myCat := newCat("阿福", "布偶猫", 1.5)
	fmt.Printf("cat's value is %#v\n", *myCat)
}
