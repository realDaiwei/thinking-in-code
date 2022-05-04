package split

import (
	"strings"
)

func Split(str, sep string) (ret []string) {
	if len(str) == 0 && len(sep) == 0 {
		return []string{}
	}
	// ret := make([]string, strings.Count(str, sep)+1)
	if len(sep) == 0 {
		for {
			if len(str) <= 0 {
				break
			}
			ret = append(ret, str[0:1])
			str = str[1:]
		}
	} else {
		idx := strings.Index(str, sep)
		for idx >= 0 {
			ret = append(ret, str[:idx])
			str = str[idx+1:]
			idx = strings.Index(str, sep)
		}
		ret = append(ret, str)
	}
	return ret
}
