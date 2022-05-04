package main

import (
	"fmt"
)

func main() {

	// switch n := 2; n {
	// case 1:
	// 	fmt.Println("一")
	// case 2:
	// 	fmt.Println("二")
	// case 3:
	// 	fmt.Println("三")
	// case 4:
	// 	fmt.Println("四")
	// default:
	// 	fmt.Println("布吉岛")
	// }

	// switch n := 9; n {
	// case 1, 2, 3, 4, 5:
	// 	fmt.Printf("n小于5，n=%d\n", n)
	// case 6, 7, 8, 9, 10:
	// 	fmt.Printf("n大于5, n=%d\n", n)
	// default:
	// 	fmt.Println("母鸡啊！")
	// }

	// age := 20;
	// switch {
	// case age >= 18:
	// 	fmt.Println("已经成年啦！")
	// case age < 18:
	// 	fmt.Println("还是一个小屁孩～")
	// }

outter:
	for i := 0; i < 100; i++ {
		for j := 'A'; j <= 'Z'; j++ {
			if j == 'B' {
				break outter
			}
			fmt.Printf("cur A for-loop idx is %d, is %c\n", i, j)
		}
	}

	for i := 0; i < 100; i++ {
		for j := 'A'; j <= 'Z'; j++ {
			if j == 'C' {
				continue
			}
			fmt.Printf("cur B for-loop idx is %d, is %c\n", i, j)
		}
	}
}
