package main

import "fmt"

func main() {
	ch := make(chan int, 20)
	for i := 0; i < 20; i++ {
		// select 可以从多个通道拿值，从不处于阻塞状态的通道拿值
		select {
		case x := <-ch:
			fmt.Println(x)
		case ch <- i:
		}
	}
}
