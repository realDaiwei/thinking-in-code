package main

import (
	"fmt"
	"strings"
)

// 闭包：闭包是指有权访问另外一个函数作用域中的变量的函数
// 简单理解就是一个函数A保存了一个或多个变量，并且返回一个函数B，调用返回函数B，
// 可以访问到函数A中保存的变量
func addSuffixFunc(suffix string) func(string) string {
	return func(name string) string {
		if !strings.Contains(name, suffix) {
			return name + suffix
		}
		return name
	}
}

func calc(base int) (add func(int) int, sub func(int) int) {
	add = func(i int) int {
		base += i
		return base
	}

	sub = func(i int) int {
		base -= i
		return base
	}
	return
}

func main() {
	addFunc := addSuffixFunc(".jpg")
	name1 := addFunc("hello")
	name2 := addFunc("666.jpg")
	fmt.Println(name1)
	fmt.Println(name2)

	add, sub := calc(100)
	fmt.Println(add(100), sub(20))
}
