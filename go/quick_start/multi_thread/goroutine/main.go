package main

import (
	"fmt"
	"time"
)

func hello(i int) {
	fmt.Println("hello ", i)
}

func main() {
	for i := 0; i < 100; i++ {
		go hello(i)
	}
	fmt.Println("main method")
	time.Sleep(time.Second)
}
