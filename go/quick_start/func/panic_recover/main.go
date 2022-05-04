package main

import "fmt"

func funcA() {
	fmt.Println("A")
}

func funcB() {
	// defer func(){
	// 	err := recover()
	// 	fmt.Println(err)
	// 	fmt.Println("....")
	// }()
	panic("出现了异常！！")
	// fmt.Println("B")
}

func funcC() {
	fmt.Println("C")
}

func errRecover() {
	err := recover()
	fmt.Println(err)
	fmt.Println("....")
}

func main() {
	defer errRecover()
	funcA()
	funcB()
	funcC()
}
