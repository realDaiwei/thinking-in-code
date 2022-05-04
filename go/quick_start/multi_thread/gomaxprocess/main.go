package main

import (
	"fmt"
	"runtime"
	"sync"
)

var waitGroup sync.WaitGroup

func f1() {
	defer waitGroup.Done()
	for i := 0; i < 100; i++ {
		fmt.Println("A:", i)
	}
}

func f2() {
	defer waitGroup.Done()
	for i := 0; i < 100; i++ {
		fmt.Println("B:", i)
	}
}

func main() {
	runtime.GOMAXPROCS(2)
	waitGroup.Add(2)
	go f1()
	go f2()
	waitGroup.Wait()
	fmt.Println("finished~")
}
