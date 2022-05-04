package main

import (
	"fmt"
	"sync"
)

var (
	waitGroup sync.WaitGroup
	lock      sync.Mutex
)

var x int

func add() {
	defer waitGroup.Done()
	for i := 0; i < 10000; i++ {
		lock.Lock()
		x += 1
		lock.Unlock()
	}
}

func main() {
	waitGroup.Add(2)
	go add()
	go add()
	waitGroup.Wait()
	fmt.Println(x)
}
