package main

import (
	"flag"
	"fmt"
	"os"
	"runtime/pprof"
	"time"
)

func loginCode() {
	var c chan int // nil
	for {
		select {
		// 因为chan没有初始化，所有这个时候 <-c 会阻塞，由于select特性，并不会走到这个分支上来
		case v := <-c:
			fmt.Printf("revc from chan, value:%v\n", v)
		default:
			time.Sleep(50 * time.Millisecond)
			//会出cpu空转
		}
	}
}

func main() {
	var (
		isCPUPprof bool
		isMemPprof bool
	)
	flag.BoolVar(&isCPUPprof, "cpu", false, "turn cpu pprof on")
	flag.BoolVar(&isMemPprof, "mem", false, "turn mem pprof on")
	flag.Parse()
	if isCPUPprof {
		f1, err := os.Create("./cpu.pprof") //当前路径下创建一个cpu.pprof文件
		if err != nil {
			fmt.Printf("create cpu pprof failed, err:%v\n", err)
			return
		}
		pprof.StartCPUProfile(f1)
		defer func() {
			pprof.StopCPUProfile()
			f1.Close()
		}()
	}
	for i := 0; i < 6; i++ {
		go loginCode()
	}
	time.Sleep(20 * time.Second)
	if isMemPprof {
		file, err := os.Create("./mem.pprof")
		if err != nil {
			fmt.Printf("create mem pprof failed, err:%v\n", err)
		}
		pprof.WriteHeapProfile(file)
		file.Close()
	}

}
