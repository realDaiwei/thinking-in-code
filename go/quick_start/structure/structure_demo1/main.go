package main

import "fmt"

type person struct {
	name, gender string
}

// go语言中函数参数永远时候拷贝
func f(p person) {
	// 修改的传入p的副本，所以对原值不会有影响
	p.gender = "女"
	p.name = "lml"
}

func f1(p *person) {
	// 修改的传入p的指针，这里语法糖修改的直接就是指针所指向的对象
	p.gender = "女"
	p.name = "lml"
}

func main() {
	var p person
	p.gender = "男"
	p.name = "daiwei"
	f1(&p)
	fmt.Printf("%#v\n", p)

	// p1是一个指针，指向person的内存地址。
	// 第一种方式创建结构体
	p1 := new(person)
	p1.name = "hello"
	p1.gender = "world"
	fmt.Printf("%#v\n", p1)
	fmt.Printf("type is %T\n", p1)
	(*p1).gender = "男士"
	fmt.Printf("%#v\n", p1)

	//第二种方式创建结构体, p2是结构体对象不是指针
	// 如果想要修改为指针在person之前加上&
	p2 := &person{
		name:   "daiwei",
		gender: "man",
	}
	fmt.Printf("p2 type is %T\n", p2)
	fmt.Printf("before p2 val is %#v\n", *p2)
	f1(p2)
	fmt.Printf("after p2 val is %#v\n", *p2)

	// 第三种结构体构建方式，直接p3对象依旧是值对象
	// 如果想要修改为指针类型在person前面加上&
	p3 := &person{
		// 这里面值的顺序要和结构体中定义的顺序一致
		"daiwei",
		"男",
	}
	fmt.Printf("p3 type is %T\n", p3)
	fmt.Printf("before p3 val is %#v\n", *p3)
	f1(p3)
	fmt.Printf("after p3 val is %#v\n", *p3)

}
