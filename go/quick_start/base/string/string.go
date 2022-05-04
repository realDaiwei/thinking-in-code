package main

import (
	"fmt"
	"strings"
)

func main() {
	s := "hello"

	// 长度
	fmt.Printf("%v\n", s)
	fmt.Println(len(s))

	// 包含
	fmt.Println(strings.Contains(s, "lox"))

	// 字符串拼接
	s1 := "daiwei " + "hello"
	fmt.Println(s1)
	s2 := fmt.Sprintf("%s 666 %s", s, s1)
	fmt.Println(s2)

	//
	s3 := strings.Split(s2, " ")
	fmt.Println(s3)

	hasPrefix := strings.HasPrefix(s2, "hello")
	fmt.Println("has prefix: ", hasPrefix)

	hasSuffix := strings.HasSuffix(s2, "hel")
	fmt.Println("has suffix: ", hasSuffix)

	index := strings.Index(s2, "xxx")
	fmt.Println("index is ", index)

	lastIndex := strings.LastIndex(s2, "llo")
	fmt.Println("last index is ", lastIndex)

	// 拼接
	s5 := strings.Join(s3, "++")
	fmt.Println("s5 is ", s5)
}
