package main

import (
	"fmt"

	// "go.daiwei.io/daiwei/day05/calc"
	hello "go.daiwei.io/daiwei/interface_file/calc" // 起别名
)

func main() {
	res := hello.Add(123, 456)
	fmt.Println(res)
}
