package main

import "fmt"

func main() {
	ret := climbStairs(4)
	fmt.Println(ret)
}

// 爬楼梯问题
func climbStairs(n int) int {
	if n < 3 {
		return n
	}
	return climbStairs(n-1) + climbStairs(n-2)
}
