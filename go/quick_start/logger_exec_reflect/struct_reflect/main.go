package main

import (
	"fmt"
	"reflect"
)

type stu struct {
	Name string `tag:"myName" hello:"hello name"` // 注意tag这里是空格连接
	Age  int    `tag:"myAge" hello:"hello age"`   // 注意tag这里是空格连接
}

func main() {
	stu := stu{
		Name: "daiwei",
		Age:  18,
	}

	t := reflect.TypeOf(stu)

	fmt.Println(t.Name(), t.Kind())

	fmt.Println(t.NumField())

	for i := 0; i < t.NumField(); i++ {
		field := t.Field(i)
		fmt.Printf("name:%s, index:%d, type:%v, json_tag:%v\n", field.Name, field.Index, field.Type, field.Tag.Get("hello"))
	}
}
