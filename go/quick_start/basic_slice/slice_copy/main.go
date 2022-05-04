package main

import "fmt"

func main() {
	// a := []int{1, 2, 3, 4, 5}
	// b := make([]int, 5)
	// copy(b, a)
	// fmt.Println(a)
	// fmt.Println(b)

	// a[0] = 1000
	// fmt.Println(a)
	// fmt.Println(b)

	// a = append(a[:2], a[3:]...)
	// fmt.Println(a)

	x1 := []int{1, 2, 3, 4, 5}
	s1 := x1[:]
	fmt.Println(s1, len(s1), cap(s1))
	s1 = append(s1[:1], s1[2:]...)
	fmt.Println(s1, len(s1), cap(s1))

	fmt.Println(x1)
}
