package main

import "fmt"

func calc(index string, a, b int) int {
	ret := a + b
	fmt.Println(index, a, b, ret)
	return ret
}

// defer 压栈的时候会把变量全部变成对应的值进行压栈
func main() {
	a := 1
	b := 2
	defer calc("1", a, calc("10", a, b))
	a = 0
	defer calc("2", a, calc("20", a, b))
	b = 1
}

// 10, 1, 2, 3
// 20, 0, 2, 2
// 2, 0, 2, 2
// 1, 1, 3, 4
