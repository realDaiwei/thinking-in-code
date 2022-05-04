package main

import "fmt"

func main() {
	// arr := [...]int{1, 2, 3, 4, 5, 6}
	// sum := 0
	// for i := 0; i < len(arr); i++ {
	// 	sum += arr[i]
	// }
	// for _, v := range arr {
	// 	sum += v
	// }
	// fmt.Println(sum)

	arr1 := [...]int{1, 2, 3, 4, 5, 3, 5}
	for i := 0; i < len(arr1); i++ {
		for j := i + 1; j < len(arr1); j++ {
			if arr1[i]+arr1[j] == 4 {
				fmt.Printf("(%d:%d, %d:%d)\n", i, arr1[i], j, arr1[j])
			}
		}
	}

}
