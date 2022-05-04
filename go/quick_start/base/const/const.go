package main

import "fmt"

const PI_VALUE float64 = 3.1415927

const (
	S1 string = "hello"
	S2 string = "hello1"
	S3 string = "hello2"
	S4
)

const (
	num1 = iota
	num2 = 1
	_    = iota
	num4 = iota
)

// const (
// 	num1, num2 = iota + 1, iota + 2
// 	num3, num4 = iota + 1, iota + 2
// )

func main() {
	// fmt.Println("p1 :", pi)
	// fmt.Println("s1 :", s1)
	// fmt.Println("s2 :", s2)
	// fmt.Println("s3 :", s3)
	// fmt.Println("s4 :", s4)

	fmt.Println("a1 :", num1)
	fmt.Println("a2 :", num2)
	// fmt.Println("a3 :", num3)
	fmt.Println("a4 :", num4)

}
