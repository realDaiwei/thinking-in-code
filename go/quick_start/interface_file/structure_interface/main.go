package main

import "fmt"

type mover interface {
	move()
}

type eater interface {
	eat(string) string
}

type animal struct {
	mover
	eater
}

type cat struct {
	name string
	feet uint8
}

func (c *cat) eat(food string) {
	fmt.Println("cat love eat finishes!")
}

func (c *cat) move() {
	fmt.Println("cat move always~~")
}

func main() {

}
