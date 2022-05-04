package main

import "fmt"

func main() {
	s := "hello 代炜"
	len := len(s)

	fmt.Println(len)

	// for i := 0; i < len; i++ {
	// 	fmt.Printf("%v\n", s[i])
	// }

	// for _, c := range s {
	// 	fmt.Printf("%c\n", c)
	// }

	s2 := "白萝卜"
	s2Arr := []rune(s2)
	s2Arr[0] = '腌'
	fmt.Println(string(s2Arr))

	c1 := "红"
	c2 := '红' // rune类型的本质int32
	fmt.Printf("c1 :%T, c2:%T\n", c1, c2)

	c3 := "H"
	c4 := byte('H') //byte是类型本质是unint8
	fmt.Printf("c3 :%T, c4 :%T\n", c3, c4)
	fmt.Printf("%d\n", c4)

	// 类型转换
	n1 := 123
	var f1 float64 = float64(n1)
	fmt.Printf("f1 type %T and value %v\n", f1, f1)
}
