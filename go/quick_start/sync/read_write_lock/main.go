package main

import (
	"fmt"
	"sync"
	"time"
)

var (
	x      int
	wg     sync.WaitGroup
	lock   sync.Mutex
	rwLock sync.RWMutex // 性能稍微好那么一丢丢但是不明显
)

func write() {
	defer wg.Done()
	rwLock.Lock()
	// lock.Lock()
	x += 1
	// time.Sleep(time.Millisecond * 5)
	rwLock.Unlock()
	// lock.Unlock()
}

func read() {
	defer wg.Done()
	rwLock.RLock()
	// lock.Lock()
	fmt.Println(x)
	rwLock.RUnlock()
	// lock.Unlock()
}

func main() {
	start := time.Now()
	for i := 0; i < 100; i++ {
		wg.Add(1)
		go write()
	}
	// time.Sleep(time.Second)
	for i := 0; i < 100000; i++ {
		go read()
		wg.Add(1)
	}
	wg.Wait()
	fmt.Println(time.Since(start))
}
