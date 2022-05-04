package main

import "fmt"

type person struct {
	name    string
	age     int
	company company
	addr    address
	work    workspace
}

type company struct {
	name string
	addr address
}

type address struct {
	province string
	city     string
}

type workspace struct {
	province string
	city     string
}

func main() {
	p1 := person{
		name: "daiwei",
		age:  26,
		addr: address{
			province: "jiangxi",
			city:     "jiujiang",
		},
		work: workspace{
			province: "shanghai",
			city:     "yangpu",
		},
		company: company{
			name: "bytedance",
			addr: address{
				province: "shanghai",
				city:     "yangpu",
			},
		},
	}
	fmt.Printf("%#v\n", p1)
	fmt.Println("daiwei's city is", p1.addr.city)
	fmt.Println("daiwei's work city is", p1.work.city)
}
