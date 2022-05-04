package mylogger

import (
	"errors"
	"fmt"
	"os"
	"path"
	"time"
)

type FileLogger struct {
	level       LoggerLevel
	filePath    string
	fileName    string
	maxFileSize int64
	fileObj     *os.File
	errFileObj  *os.File
}

func NewFileLogger(levelStr, fileName, filePath string, maxSize int64) *FileLogger {
	logLevel, err := parseLogLevel(levelStr)
	if err != nil {
		panic(err)
	}
	f := &FileLogger{
		level:       logLevel,
		filePath:    filePath,
		fileName:    fileName,
		maxFileSize: maxSize,
	}
	if err := f.initFile(); err != nil {
		panic(err)
	}
	return f
}

// 打开日志文件
func (f *FileLogger) initFile() error {
	fullFileName := path.Join(f.filePath, f.fileName)
	file, err := os.OpenFile(fullFileName, os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	if err != nil {
		return errors.New("open file failed")
	}
	f.fileObj = file
	errFileObj, err := os.OpenFile(fullFileName+".err", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	if err != nil {
		return errors.New("open error file failed")
	}
	f.errFileObj = errFileObj
	return nil
}

// trace 方法
func (f *FileLogger) Trace(msg string, args ...interface{}) {
	// f.formatPrint(TRACE, msg, args...)
	sendToChannel(NewLogMsg(f, TRACE, msg, args...))
}

// debug 方法
func (f *FileLogger) Debug(msg string, args ...interface{}) {
	// f.formatPrint(DEBUG, msg, args...)
	sendToChannel(NewLogMsg(f, DEBUG, msg, args...))
}

// info 方法
func (f *FileLogger) Info(msg string, args ...interface{}) {
	// f.formatPrint(INFO, msg, args...)
	sendToChannel(NewLogMsg(f, INFO, msg, args...))
}

// warm 方法
func (f *FileLogger) Warn(msg string, args ...interface{}) {
	// f.formatPrint(WARN, msg, args...)
	sendToChannel(NewLogMsg(f, WARN, msg, args...))
}

// error 方法
func (f *FileLogger) Error(msg string, args ...interface{}) {
	sendToChannel(NewLogMsg(f, ERROR, msg, args...))

}

// fatac 方法
func (f *FileLogger) Fatal(msg string, args ...interface{}) {
	sendToChannel(NewLogMsg(f, FATAL, msg, args...))
}

// 日志输出打印
func (f *FileLogger) formatPrint(logMsg *LogMsg) {
	msg := fmt.Sprintf(logMsg.format, logMsg.args...)
	nowStr := time.Now().Format("2006-01-02 15:04:05")
	if logMsg.level >= ERROR {
		f.errFileObj = f.checkAndSplit(f.errFileObj)
		fmt.Fprintf(f.errFileObj, "[%s][%s][%s:%s:%d] %s\n", nowStr, loggerLevelToStr(logMsg.level), logMsg.fileName, logMsg.funcName, logMsg.lineNo, msg)
	}
	f.fileObj = f.checkAndSplit(f.fileObj)
	fmt.Fprintf(f.fileObj, "[%s][%s][%s:%s:%d] %s\n", nowStr, loggerLevelToStr(logMsg.level), logMsg.fileName, logMsg.funcName, logMsg.lineNo, msg)
}

//当前level是否开启
func (f *FileLogger) enable(level LoggerLevel) bool {
	return f.level <= level
}

//文件校验和拆分
func (f *FileLogger) checkAndSplit(curFile *os.File) *os.File {
	var (
		newFile = curFile
		err     error
	)
	if f.checkSize(curFile) {
		newFile, err = f.fileSplit(curFile)
		if err != nil {
			fmt.Printf("文件拆分失败, err:%v\n", err)
		}
	}
	return newFile
}

// 校验文件大小 true: 需要切割 false: 不需要切割
func (f *FileLogger) checkSize(file *os.File) bool {
	info, err := file.Stat()
	if err != nil {
		fmt.Printf("get file info failed!\n")
		return false
	}
	return f.maxFileSize <= info.Size()
}

// 文件拆分
func (f *FileLogger) fileSplit(file *os.File) (*os.File, error) {
	// 1. 关闭当前日志文件
	file.Close()
	// 2. rename当前文件 xxx.log -> xxx.log.bak20060102150405000
	fullFileName := path.Join(f.filePath, file.Name())
	nowStr := time.Now().Format("20060102150405000")
	os.Rename(fullFileName, fullFileName+".bak"+nowStr)
	// 3. 打开一个新的日志文件
	curFile, err := os.OpenFile(fullFileName, os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	if err != nil {
		fmt.Printf("new rolling file open failed!\n")
		return nil, err
	}
	// 4. 将打开新的日志文件对象赋给f.fileObj
	return curFile, nil
}
