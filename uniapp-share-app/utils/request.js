export function request(url, method, data) {
	let token = ''
	if (uni.getStorageSync('token') == '') {
		token = 'no-token'
	} else {
		token = uni.getStorageSync('token')
	}
	return new Promise(function(resolve, reject) {
		uni.request({
			url: url,
			method: method,
			data: data,
			dataType: 'string',
			header: {
				'Content-Type': 'application/json',
				'token': token
			},
			success: function(res) {
				var json = res.data.replace(/:s*([0-9]{15,})s*(,?)/g, ': "$1" $2')
				//2.根据后端返回的数据调用一次或者两次replace替换
				var json1 = json.replace(/:s*([0-9]{15,})s*(,?)/g, ': "$1" $2')
				//3.手动转换回json数据即可
				var trueData = JSON.parse(json1);
				resolve(trueData)
			},
			fail: function(err) {
				uni.showModal({
					title: "错误",
					content: '网络请求异常',
					showCancel: false
				});
				reject(err)
			}
		})
	})
}
