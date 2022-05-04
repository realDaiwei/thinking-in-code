package main

import "fmt"

func main() {
	i1 := 123
	fmt.Printf("i1 = %d\n", i1)

	i2 := 073
	fmt.Printf("i2 = %d\n", i2)

	i3 := 0x123fa
	fmt.Printf("i3 = %d\n", i3)

	i4 := 12345678
	fmt.Printf("i4(0x)= %X\n", i4)

	i5 := 12345
	fmt.Printf("i5(o)=%o\n", i5)

	fmt.Printf("i5 type = %T\n", i5)
}