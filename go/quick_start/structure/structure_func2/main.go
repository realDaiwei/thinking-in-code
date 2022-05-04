package main

import "fmt"

type person struct {
	name   string
	age    int
	gender string
}

// person的构造函数
func newPerson(name, gender string, age int) *person {
	return &person{
		name:   name,
		gender: gender,
		age:    age,
	}
}

// 值接收者 不会改变原来的值
func (p person) growUp() {
	// 因为是值接收者所以这里修改是无效的
	p.age++
	fmt.Printf("person value is %#v\n", p)
}

// 指针接受者，会修改原来的值（一般情况下用指针就对了）
func (p *person) growUpWithPoint() {
	p.age++
	p.name = "lml"
	p.gender = "female"
}

func main() {
	p := newPerson("daiwei", "male", 26)
	p.growUp()
	fmt.Printf("person value just value is %#v\n", *p)
	p.growUpWithPoint()
	fmt.Printf("person value by pointer is %#v\n", *p)
}
