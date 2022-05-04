package main

import (
	"fmt"
	"sync"
)

var (
	c         chan int
	waitGroup sync.WaitGroup
)

func getFromChannel() {
	defer waitGroup.Done()
	x := <-c
	fmt.Printf("get data[%d] from channel\n", x)
}

func main() {
	c = make(chan int, 16)
	defer close(c)
	waitGroup.Add(1)
	go getFromChannel()
	c <- 100
	fmt.Println("已经把100发送到channel中")
	waitGroup.Wait()
	fmt.Println("finished～")
}
