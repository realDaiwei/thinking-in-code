package main

import (
	"fmt"
	"strings"
)

// 统计单词出现次数
func countWord(word string) (ret map[string]int) {
	ret = make(map[string]int)
	list := strings.Split(word, " ")
	for _, str := range list {
		v, ok := ret[str]
		if ok {
			ret[str] = v + 1
		} else {
			ret[str] = 1
		}
	}
	return
}

// 判断是否回文字符串
func palindromicStr(str string) bool {
	strRune := []rune(str)
	lo := 0
	hi := len(strRune) - 1
	for {
		if lo >= hi {
			break
		} else {
			if strRune[lo] == strRune[hi] {
				lo++
				hi--
			} else {
				return false
			}
		}
	}
	return true
}

func main() {
	word := "how do you do"
	result := countWord(word)
	fmt.Println(result)
	res := palindromicStr("abcdefedcba")
	fmt.Println(res)
}
