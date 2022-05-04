package main

import (
	"errors"
	"fmt"
	"io/ioutil"
	"reflect"
	"strconv"
	"strings"
)

var (
	configRowMap map[string]*map[string]string
)

// mysql config
type MysqlConfig struct {
	Address  string
	Port     uint64
	Username string
	Password string
}

// redis config
type RedisConfig struct {
	Host     string
	Port     uint64
	Password string
	Database uint8
}

// 加载配置文件
func loadIni(filePath string, p interface{}) error {
	keyStr, err := structFieldStr(p)
	if err != nil {
		return err
	}
	if _, ok := configRowMap[keyStr]; !ok {
		err = importFile2RowMap(filePath)
		if err != nil {
			return err
		}
	}

	v, ok := configRowMap[keyStr]
	if ok {
		reflectSetData(*v, p)
	}
	return nil
}

// set data by reflect
func reflectSetData(config map[string]string, p interface{}) error {
	t := reflect.TypeOf(p)
	if t.Kind() != reflect.Ptr && t.Elem().Kind() != reflect.Struct {
		return errors.New("param data should be a pointer")
	}
	v := reflect.ValueOf(p).Elem()
	for k, str := range config {
		key := strings.ToUpper(k[0:1]) + k[1:]
		field := v.FieldByName(key)
		val := convertType(field.Type().Kind(), str)
		field.Set(reflect.ValueOf(val))
	}
	return nil
}

// convert value to provide type
func convertType(kind reflect.Kind, s string) interface{} {
	var (
		res interface{}
		err error
	)
	switch kind {
	case reflect.Int, reflect.Int8, reflect.Int16, reflect.Int32, reflect.Int64:
		res, err = strconv.ParseInt(s, 10, 64)
	case reflect.Uint, reflect.Uint8, reflect.Uint16, reflect.Uint32, reflect.Uint64:
		res, err = strconv.ParseUint(s, 10, 64)
	case reflect.Float32, reflect.Float64:
		res, err = strconv.ParseFloat(s, 64)
	case reflect.String:
		res, err = s, nil
	default:
		res, err = nil, errors.New("unknow type")
	}
	if err != nil {
		panic(err)
	}
	return res
}

// import config data to configRowMap
func importFile2RowMap(filePath string) error {
	configRowMap = make(map[string]*map[string]string)
	b, err := ioutil.ReadFile(filePath)
	if err != nil {
		return errors.New("open file from file path failed")
	}
	config := strings.Split(string(b), "\n")
	var (
		tag string
	)
	for _, str := range config {
		if strings.Contains(str, "[") && strings.Contains(str, "]") && !strings.Contains(str, "=") {
			// this is a ini config tag
			configItem := make(map[string]string)
			tag = str
			configRowMap[str] = &configItem
		} else if strings.Contains(str, "=") {
			param := strings.Split(str, "=")
			(*configRowMap[tag])[param[0]] = param[1]
		}
	}
	for _, v := range configRowMap {
		var keyStr string
		for k := range *v {
			keyStr += (k + "#")
		}
		configRowMap[keyStr] = v
	}
	return nil
}

// get struct field string
func structFieldStr(p interface{}) (string, error) {
	t := reflect.TypeOf(p)
	if t.Kind() != reflect.Ptr && t.Elem().Kind() != reflect.Struct {
		return "", errors.New("param data should be a pointer")
	}
	var strRes string
	for i := 0; i < t.Elem().NumField(); i++ {
		strRes += (strings.ToLower(t.Elem().Field(i).Name) + "#")
	}
	return strRes, nil
}

func main() {
	var m MysqlConfig
	loadIni("./config.ini", &m)
	fmt.Printf("loaded config: %v\n", m)
}
