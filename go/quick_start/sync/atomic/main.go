package main

import (
	"fmt"
	"sync"
	"sync/atomic"
)

var (
	wg sync.WaitGroup
	x  int64
)

func add() {
	defer wg.Done()
	// 原子类操作
	atomic.AddInt64(&x, 1)
	// x += 1
}

func main() {
	wg.Add(1000)
	for i := 0; i < 1000; i++ {
		go add()
	}
	wg.Wait()
	// cas操作
	atomic.CompareAndSwapInt64(&x, 1000, 200000)
	fmt.Println(x)
	fmt.Println("finished~")
}
