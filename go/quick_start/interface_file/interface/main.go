package main

import "fmt"

// 这是一个接口
type runnable interface {
	run()
	walk()
	move()
}

func gogogo(r runnable) {
	r.move()
	r.walk()
	r.run()
}

type person struct {
	name   string
	age    int
	addr   string
	gender string
}

func (p *person) run() {
	fmt.Println("people is running!")
}

func (p *person) walk() {
	fmt.Println("people is walking!")
}

func (p *person) move() {
	fmt.Println("people is moving slowly~")
}

type dog struct {
	name    string
	subject string
	a       int
}

func (d *dog) run() {
	fmt.Println("dog is running!")
}

func (d *dog) walk() {
	fmt.Println("dog is walking!")
}

func (d *dog) move() {
	fmt.Println("dog is moving slowly!")
}

type cat struct {
	name    string
	subject string
	b       int
}

func (c *cat) run() {
	fmt.Println("cat is running!")
}

func (c *cat) walk() {
	fmt.Println("cat walking!")
}

func (c *cat) move() {
	fmt.Println("cat move move move~")
}

func main() {
	p := &person{
		name:   "daiwei",
		age:    25,
		addr:   "shanghai",
		gender: "male",
	}
	gogogo(p)
	d := &dog{
		name:    "阿财",
		subject: "大黄狗",
		a:       666,
	}
	gogogo(d)

	c := &cat{
		name:    "不言",
		subject: "猫猫～",
		b:       123,
	}
	gogogo(c)

}
