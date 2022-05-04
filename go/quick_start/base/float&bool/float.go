package main

import "fmt"

func main() {
	var b1 bool
	b2 := true

	fmt.Printf("b1 type %T, %v\n", b1, b1)
	fmt.Printf("b2 type %T, %v \n", b2, b2)

	var f1 float64 = 1.34352
	f2 := 2.3456
	f3 := float32(f2)
	fmt.Printf("f1 type %T\n", f1)
	fmt.Printf("f1 type %T, %v\n", f2, f2)
	fmt.Printf("f3 type %T, %v\n", f3, f3)
}
