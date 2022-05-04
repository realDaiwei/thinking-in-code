package main

import "fmt"

func main() {
	n := 18
	p := &n
	fmt.Println(p)
	fmt.Printf("%T\n", p)
	m := *p
	fmt.Println(m)

	a := 20
	b := &a

	fmt.Printf("%p\n", &a)
	fmt.Printf("%v\n", b)

}
