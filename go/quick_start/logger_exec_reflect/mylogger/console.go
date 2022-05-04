package mylogger

import (
	"fmt"
	"time"
)

// 往终端写
type ConsoleLogger struct {
	level LoggerLevel
}

func NewConsoleLogger(level string) *ConsoleLogger {
	loggerLevel, err := parseLogLevel(level)
	if err != nil || loggerLevel == UNKONW {
		panic(err)
	}
	return &ConsoleLogger{
		level: loggerLevel,
	}
}

// trace 方法
func (c *ConsoleLogger) Trace(msg string, args ...interface{}) {
	sendToChannel(NewLogMsg(c, TRACE, msg, args...))
}

// debug 方法
func (c *ConsoleLogger) Debug(msg string, args ...interface{}) {
	sendToChannel(NewLogMsg(c, DEBUG, msg, args...))
	// c.formatPrint(DEBUG, msg, args...)
}

// info 方法
func (c *ConsoleLogger) Info(msg string, args ...interface{}) {
	sendToChannel(NewLogMsg(c, INFO, msg, args...))
	// c.formatPrint(INFO, msg, args...)
}

// warm 方法
func (c *ConsoleLogger) Warn(msg string, args ...interface{}) {
	sendToChannel(NewLogMsg(c, WARN, msg, args...))
	// c.formatPrint(WARN, msg, args...)
}

// error 方法
func (c *ConsoleLogger) Error(msg string, args ...interface{}) {
	sendToChannel(NewLogMsg(c, ERROR, msg, args...))
	// c.formatPrint(ERROR, msg, args...)
}

// fatac 方法
func (c *ConsoleLogger) Fatal(msg string, args ...interface{}) {
	sendToChannel(NewLogMsg(c, FATAL, msg, args...))
	// c.formatPrint(FATAL, msg, args...)
}

func (c *ConsoleLogger) formatPrint(logMsg *LogMsg) {
	msg := fmt.Sprintf(logMsg.format, logMsg.args...)
	nowStr := time.Now().Format("2006-01-02 15:04:05")
	fmt.Printf("[%s][%s][%s:%s:%d] %s\n", nowStr, loggerLevelToStr(logMsg.level), logMsg.fileName, logMsg.funcName, logMsg.lineNo, msg)
}

func (c *ConsoleLogger) enable(level LoggerLevel) bool {
	return c.level <= level
}
