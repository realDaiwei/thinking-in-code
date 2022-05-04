package main

import (
	"encoding/json"
	"fmt"
)

type person struct {
	Name string `json:"name"` // 因为struct person和json不在一个包中，因此通过这种方式可以在别的包中起别名，曲线救国属于是
	Age  int    `json:"age"`
}

func main() {
	// json 序列化
	p1 := &person{
		Name: "daiwei",
		Age:  26,
	}
	b, e := json.Marshal(*p1)
	if e != nil {
		fmt.Println("json格式化失败!!")
	} else {
		fmt.Println(string(b))
	}

	// json 反序列化
	var p2 person
	jsonStr := `{"name":"lml", "age":26}`
	err := json.Unmarshal([]byte(jsonStr), &p2)
	if err != nil {
		fmt.Printf("返序列化失败，err: %#v\n", err)
	} else {
		fmt.Printf("%#v\n", p2)
	}
}
