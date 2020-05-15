function handleLabelShow(labelArray) {
    //关于label显示的处理
    //当一个图表的data中数据过多时，则只显示最大值和最小值的label
    let maxLabel = Math.max(...labelArray)
    let minLabel = Math.min(...labelArray)
    let formatter = function (num) {
        if (num.value == maxLabel || num.value == minLabel && num.value != 0) {
            return num.value
        } else {
            return ''
        }
    }
    return formatter
}

function handleDecimal(number) {
    /*//小数点位置
    let pointPosition = String(number).indexOf('.')
    //如果num小数点后超过三位，则只保留三位
    if (String(number).length-pointPosition-1 > 3) {
        return number.toFixed(3)
    }
    return number*/
    return number.toFixed(3)
}

