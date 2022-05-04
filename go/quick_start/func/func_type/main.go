package main

import (
	"fmt"
)

// func f1() {
// 	fmt.Println("hello~")
// }

// func f2(x, y int) (ret int) {
// 	ret = x + y
// 	return
// }

func f3(x int, y ...int) (ret string) {
	str := fmt.Sprintf("%d", x)
	for _, v := range y {
		str += fmt.Sprintf("%d", v)
	}
	return str
}

func f4(ff func(int, ...int) string, s []int) string {
	num1 := s[0]
	s2 := s[1:]
	return ff(num1, s2...)
}

func main() {
	// fmt.Printf("%T\n", f2)
	// fmt.Printf("%T\n", f3)
	// fmt.Printf("%s\n", f3(1, 3, 4, 5, 6, 3, 4))
	fmt.Println(f4(f3, []int{1}))
}
