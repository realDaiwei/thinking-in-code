package main

import (
	"fmt"
)

func assign(a interface{}) {
	fmt.Printf("%T\n", a)
	v, ok := a.(string)
	if !ok {
		fmt.Println("类型不是一个字符串")
	} else {
		fmt.Println("传递进来的是一个字符串", v)
	}
}

func assign2(a interface{}) {
	fmt.Printf("%T\n", a)
	switch t := a.(type) {
	case string:
		fmt.Printf("是一个字符串类型 %T, val is %#v\n", t, t)
	case int:
		fmt.Printf("是一个int类型 %T, val is %#v\n", t, t)
	case int64:
		fmt.Printf("是一个int64类型 %T, val is %#v\n", t, t)
	default:
		fmt.Printf("不知道是什么类型。")
	}

}

func main() {
	assign2(int64(12345))
}
