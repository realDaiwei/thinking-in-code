package main

import "fmt"

type person struct {
	name   string
	age    int
	gender string
	hobby  []string
}

func main() {

	var p person
	p.name = "daiwei"
	p.age = 25
	p.gender = "男"
	p.hobby = []string{"学习", "健身", "女"}
	fmt.Println(p)

	var s struct {
		x int
		y string
	}
	s.x = 123
	s.y = "hello"
	fmt.Println(s)

	s2 := struct {
		a int
		b int
	}{2, 3}
	fmt.Println(s2)
}
