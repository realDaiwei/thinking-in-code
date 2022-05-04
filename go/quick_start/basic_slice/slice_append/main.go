package main

import "fmt"

func main() {
	arr := [...]int{1, 2, 3, 4, 5, 6}
	s1 := arr[:]
	s1 = append(s1[0:3], s1[2:3]...) // ...表示数据拆开来
	fmt.Printf("arr %T, %v, %d, %d\n", arr, arr, len(arr), cap(arr))
	fmt.Printf("s1 %T, %v, %p\n", s1, s1, s1)
	s2 := append(s1, 7)
	s1[0] = 200
	arr[4] = 200
	fmt.Printf("s1 %T, %v, %p\n", s1, s1, s1)
	fmt.Printf("s2 %T, %v, %d, %d, %p\n", s2, s2, len(s2), cap(s2), s2)
	fmt.Printf("arr %T, %v, %d, %d\n", arr, arr, len(arr), cap(arr))
}
