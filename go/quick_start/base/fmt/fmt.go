package main

import "fmt"

func main() {
	var n int = 10

	fmt.Printf("%T\n", n)
	fmt.Printf("%v\n", n)
	fmt.Printf("%d\n", n)
	fmt.Printf("%x\n", n)
	fmt.Printf("%o\n", n)

	var s string = "hello"
	fmt.Printf("str is %s\n", s)
	fmt.Printf("str(use v) is %#v\n", s)
}
