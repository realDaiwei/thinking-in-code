package main

import "fmt"

// 变量声明
// var name string
// var age int
// var isOk bool

// 批量声明
var (
	name string
	age  int
	isOk bool
)

func main() {
	name = "daiwei"
	age = 26
	isOk = true
	fmt.Printf("my name is %s\n", name)
	fmt.Printf("my age %d\n", age)
	fmt.Println(isOk)

	// 声明变量的同时赋值
	var s1 string = "hello"
	fmt.Println("s1 =", s1)

	// 隐式赋值
	var s2 = "s2"
	fmt.Println("s2 =", s2, "s1 =", s1)

	// 隐式赋值的简短写法
	s3 := "s3"
	fmt.Println("s3 =", s3)

}
