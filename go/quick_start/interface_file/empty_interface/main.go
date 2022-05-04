package main

import "fmt"

func show(v interface{}) {
	fmt.Printf("type: %T, value: %#v\n", v, v)
}

func main() {
	var m1 map[string]interface{} = make(map[string]interface{}, 64)
	m1["name"] = "daiwei"
	m1["age"] = 26
	m1["married"] = false
	m1["hobby"] = []string{"reading", "coding"}

	show(m1)
	show(nil)
	show(false)
}
