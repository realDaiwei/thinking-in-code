package main

import (
	"time"

	"go.daiwei.io/daiwei/logger_exec_reflect/mylogger"
)

func main() {
	// log := mylogger.NewConsoleLogger("trace")
	log := mylogger.NewFileLogger("trace", "daiwei.log", ".", 2*1024)
	for i := 0; i < 100; i++ {
		log.Trace("这是一条Trace日志 and value is %s", "daiwei")
		log.Debug("这是一条debug日志")
		log.Info("这是一条Info日志")
		log.Warn("这是一条Warm日志")
		log.Error("这是一条Error日志")
		log.Fatal("这是一条Fatal日志")
		time.Sleep(5 * time.Millisecond)
	}

}
