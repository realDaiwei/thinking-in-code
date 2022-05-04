package mylogger

// define interface
type Logger interface {
	enable(level LoggerLevel) bool
	Trace(msg string, args ...interface{})
	Debug(msg string, args ...interface{})
	Info(msg string, args ...interface{})
	Warn(msg string, args ...interface{})
	Error(msg string, args ...interface{})
	Fatal(msg string, args ...interface{})
	formatPrint(logMsg *LogMsg)
}

//define message struct in channel
type LogMsg struct {
	l        Logger
	level    LoggerLevel
	format   string
	funcName string
	fileName string
	lineNo   int
	args     []interface{}
}

var logChann chan *LogMsg

func NewLogMsg(l Logger, level LoggerLevel, msg string, args ...interface{}) *LogMsg {
	funcName, fileName, lineNo := getInfo(0)
	return &LogMsg{
		l:        l,
		level:    level,
		format:   msg,
		funcName: funcName,
		fileName: fileName,
		lineNo:   lineNo,
		args:     args,
	}
}

// 输出打印日志信息
func print() {
	for msg := range logChann {
		msg.l.formatPrint(msg)
	}
}

// create a logMessage and send to channel
func sendToChannel(logMsg *LogMsg) {
	if !logMsg.l.enable(logMsg.level) {
		return
	}
	if logChann == nil {
		logChann = make(chan *LogMsg)
		go print()
	} else {
		logChann <- logMsg
	}
}
