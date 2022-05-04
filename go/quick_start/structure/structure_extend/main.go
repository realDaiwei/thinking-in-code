package main

import "fmt"

type animal struct {
	name    string // 名字
	subject string // 科属
}

func (a animal) move() {
	fmt.Printf("%s is an animal!!\n", a.name)
}

type dog struct {
	animal
	feet int
}

func newDog(name, subject string, feet int) *dog {
	return &dog{
		animal: animal{
			subject: subject,
			name:    name,
		},
		feet: feet,
	}
}

func (d dog) wang() {
	fmt.Printf("%s barking: wangwangwang!!\n", d.name)
}

func main() {
	dog := newDog("阿财", "狗子", 4)
	dog.wang()
	dog.move()
	fmt.Println(dog.feet)
	fmt.Println(dog.subject)
}
