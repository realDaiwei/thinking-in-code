package main

import (
	"fmt"
	"reflect"
)

type Cat struct {
}

func reflectType(x interface{}) {
	v := reflect.TypeOf(x)
	fmt.Printf("type: %v\n", v)
	fmt.Printf("type name :%v type kind:%v\n", v.Name(), v.Kind())
}

func reflectValue(x interface{}) {
	v := reflect.ValueOf(x) // 值的种类类型
	switch v.Kind() {
	case reflect.Int64:
		fmt.Printf("type is int64, value is %d\n", int64(v.Int()))
	case reflect.Float32:
		fmt.Printf("type is float32, value is %f\n", float32(v.Float()))
	case reflect.Float64:
		fmt.Printf("type is float64, value is %f\n", float64(v.Float()))
	case reflect.String:
		fmt.Printf("type is string, value is %s\n", string(v.String()))
	default:
		fmt.Printf("unknow type~~")
	}
}

// 指针类型获取到具体的值，然后再更新
func reflectSetValue(x interface{}) {
	v := reflect.ValueOf(x)
	if v.Elem().Kind() == reflect.String {
		v.Elem().SetString("hello from reflect " + v.String()) // 通过指针找到具体的值，然后再更新
	}
}

func main() {
	// var a float32 = 3.14
	// reflectType(a)
	// var b int64 = 1231
	// reflectType(b)
	// reflectValue("hello")
	var x string = "daiwei"
	reflectSetValue(&x)
	fmt.Printf("after reflect update value %v\n", x)

}
