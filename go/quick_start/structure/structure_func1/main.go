package main

import "fmt"

//Go语言中如果有标识符首字母是大写的，就表示对外部包可见
type Dog struct {
	name string
}

func newDog(name string) *Dog {
	return &Dog{
		name: name,
	}
}

//方法是作用于特定类型的函数
// 接受者表示调用该方法的具体类型变量，多用类型的首字母小写表示。
// 也就是标识一个方法的归属，相当于java中的类方法
func (d Dog) wang() {
	fmt.Printf("狗子(%s): 汪汪汪～\n", d.name)
}

func main() {
	d := newDog("阿财")
	d.wang()
}
