package main

import "fmt"

// 基础函数 返回值已声明，所以不用在return一个具体的值
func sum(x int, y int) (ret int) {
	ret = x + y
	return
}

// 基础函数，返回值不初始化
func sum1(x int, y int) int {
	return x + y
}

// 没有返回值的函数
func f1(x int, y int) {
	fmt.Printf("x = %d, y = %d\n", x, y)
}

// 没有返回值也没有入参的函数
func f2() {
	fmt.Println("hello world and hello function!!")
}

// 没有入参但是有返回i
func f3() int {
	return 3
}

// 函数多返回值
func f4() (x int, y int) {
	x = 2
	y = 3
	return
}

// 参数简写
func f5(x, y int, a, b, c string, i, j bool) int {
	return x + y
}

// 可变长参数， 只有最后一个入参可以用...
func f6(x int, y ...string) {
	fmt.Println(x)
	fmt.Println(y)
}


func main() {
	// ret := sum(23, 45)
	// ret := sum1(23, 45)
	// f1(23, 34)
	// f2()
	// ret := f3()
	// ret := f5(3, 4)
	// fmt.Println(ret)
	// x, y := f4()
	// fmt.Printf("x = %d, y = %d\n", x, y)
	f6(1, "shanghai", "hangzhou", "beijing")
}
