package mylogger

import (
	"errors"
	"fmt"
	"path"
	"runtime"
	"strings"
)

// 自定义日志库
type LoggerLevel uint32

const (
	UNKONW LoggerLevel = iota
	TRACE
	DEBUG
	INFO
	WARN
	ERROR
	FATAL
)


func parseLogLevel(s string) (level LoggerLevel, err error) {
	level = UNKONW
	switch str := strings.ToLower(s); str {
	case "trace":
		level = TRACE
	case "debug":
		level = DEBUG
	case "info":
		level = INFO
	case "warn":
		level = WARN
	case "error":
		level = ERROR
	case "fatal":
		level = FATAL
	default:
		level = UNKONW
		err = errors.New("invalidate logger type: " + str)
	}
	return
}

func loggerLevelToStr(l LoggerLevel) (levelStr string) {
	switch l {
	case TRACE:
		levelStr = "TRACE"
	case DEBUG:
		levelStr = "DEBUG"
	case INFO:
		levelStr = "INFO"
	case WARN:
		levelStr = "WARN"
	case ERROR:
		levelStr = "ERROR"
	case FATAL:
		levelStr = "FATAL"
	default:
		levelStr = "UNKONW"
	}
	return
}

func getInfo(n int) (funcName, fileName string, lineNo int) {
	pc, file, lineNo, ok := runtime.Caller(n)
	if !ok {
		fmt.Printf("runtime.Caller failed\n")
		return
	}
	funcName = strings.Split(runtime.FuncForPC(pc).Name(), ".")[1]
	fileName = path.Base(file)
	return
}

