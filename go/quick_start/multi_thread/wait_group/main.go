package main

import (
	"fmt"
	"runtime"
	"sync"
)

// 这个和java里面的countdownLatch很像，这个可以重复利用
var wg sync.WaitGroup

func main() {
	runtime.GOMAXPROCS(2)
	for i := 0; i < 100; i++ {
		wg.Add(1)
		go func(i int) {
			defer wg.Done()
			fmt.Println(i)
		}(i)
	}
	wg.Wait()
	fmt.Println("finished~")
}
