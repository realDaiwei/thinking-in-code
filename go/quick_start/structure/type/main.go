package main

import "fmt"

type myInt int     // 自定义类型
type yourInt = int // 类型取别名

// 自定义类型，编译之后会保留，类型别名，编译之后会被抹掉
func main() {
	var n myInt   // 自定义类型
	var m yourInt // 只是取了一个别名
	var c rune    // rune也就是一个别名 本质是上int32
	n = 100
	fmt.Printf("n type is %T, value is %v\n", n, n)
	m = 200
	fmt.Printf("m type is %T, value is %v\n", m, m)
	c = '中'
	fmt.Printf("c type is %T, value is %v\n", c, c)

}
