package main

import "fmt"

func main() {
	var a1 [3]int
	var a2 [4]int

	fmt.Printf("a1: %T and value %v\n", a1, a1)
	fmt.Printf("a2: %T and value %v\n", a2, a2)

	a3 := [3]int{1, 2, 3}
	fmt.Printf("a3: %T and value %v\n", a3, a3)

	a3[2] = 999
	fmt.Printf("a3: %T and value %v\n", a3, a3)

	// 值赋予
	a4 := a3
	fmt.Printf("a4: %T and value %v\n", a4, a4)

	//
	// a4[2] = 888
	// fmt.Printf("a4: %T and value %v\n", a4, a4)

	// a5 := [...]int{6, 5, 4, 3, 2, 1, 0}
	// fmt.Printf("a5: %T and value %v\n", a5, a5)

	// for i := 0; i < len(a5); i++ {
	// 	fmt.Printf("cur idx %d and value %v\n", i, a5[i])
	// }

	// for i, v := range a5 {
	// 	fmt.Printf("cur idx %d and value %v\n", i, v)
	// }

	//多维数组
	// var a6 = [3][3]int{
	// 	{1, 2, 3},
	// 	{4, 5, 6},
	// 	{7, 8, 9},
	// }

	// fmt.Printf("print arr %v\n", a6)

	// // 双重for循环遍历数组
	// for i := 0; i < 3; i++ {
	// 	for j := 0; j < 3; j++ {
	// 		fmt.Printf("int[%d][%d] value is %d\n", i, j, a6[i][j])
	// 	}
	// }

}
