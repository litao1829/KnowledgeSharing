<template>
	<view class="container container25498">
		<view class="flex flex-wrap diygw-col-24 flex-direction-column">
			<view class="flex flex-wrap diygw-col-24 flex1-clz" v-for="(item,index) in shares" :key="index">
				<view class="flex flex-wrap diygw-col-4 flex-direction-column justify-center items-stretch"
					style="position: relative;">
					<text style="position: absolute; left: -9px;top: -5px; background-color: red;
					font-size: 13px; padding: 0 5px; z-index: 99;color: white;" v-if="item.isOriginal">原创</text>
					<text style="position: absolute; left: -9px;top: -5px; background-color: darkgreen; font-size: 13px; padding: 0 5px; 
					z-index: 99;color: white;" v-else>转载</text>
					<image :src="item.cover" style="height: 60px;width: 60px; border-radius: 5px;"></image>
				</view>
				<view class="flex flex-wrap diygw-col-10 flex-direction-column flex3-clz">
					<view class="diygw-col-24 text-clz"> {{item.title}}</view>
					<view class="diygw-text-line2 diygw-col-24 text1-clz">{{item.summary}}</view>
				</view>
				<view class="flex flex-wrap diygw-col-24 flex-direction-column justify-center items-end flex4-clz">
					<view class="diygw-text-line2  text3-clz" v-if="item.auditStatus=='NOT_YET'"> 待审核 > </view>
					<view class="diygw-text-line2  text3-clz" v-else-if="item.auditStatus=='PASS'"> 审核通过 > </view>
					<view class="diygw-text-line2  text3-clz" v-else> 审核不通过 > </view>
				</view>
			</view>
			<view class="bottom"></view>
		</view>

	</view>
</template>

<script>
	import {
		MY_CONTRIBUTE_URL
	} from '../../utils/api.js'
	import {
		request
	} from '../../utils/request.js'

	export default {
		data() {
			return {
				shares: [],
				pageNo: 1,
				pageSize: 8,
				more: true
			};
		},
		onShow() {
			this.setCurrentPage(this);
			this.getShare(true);
		},
		onLoad(option) {},
		//下拉刷新
		onPullDownRefresh() {
			this.getShare(true);
		},
		//触底加载更多
		onReachBottom() {
			//没有更多数据了
			if (!this.more) {
				uni.showToast({
					title: '已经加载完毕了',
					duration: 1000
				});
				return false;
			}
			//正常加载下一页
			this.pageNo = this.pageNo + 1;
			uni.showLoading({
				title: '加载中'
			});
			//不带参数的请求，就是分页加载
			this.getShare();
			setTimeout(() => {
				uni.hideLoading();
			}, 1000);
		},
		methods: {
			async getShare(init) {
				console.log("开始请求");
				//入参为true，从第一页重新开始加载数据，场景为：下来刷新、每次进入
				if (init) {
					this.pageNo = 1;
					this.more = true;
				}
				let res = await request(MY_CONTRIBUTE_URL, 'GET', {
					pageNo: this.pageNo,
					pageSize: this.pageSize
				});
				console.log(res.data)
				//请求结束延时隐藏加载动画
				setTimeout(() => {
					uni.hideLoading();
				}, 2000);

				//处理进入首页加载数据和下拉刷新场景
				if (init) {
					//获得新的数据集合，将数据初始化为本次请求放回结果
					this.shares = res.data;
					//停止下拉刷新
					uni.stopPullDownRefresh();
					console.log("AAAAAAAAAAAAAAAAAA");
				} else {
					//不是下拉刷新，是加载下一页，则把新数据追加到后面，不能覆盖原来的值
					this.shares = this.shares.concat(res.data);
				}
				//加载到了最后一页
				if (res.data.length < this.pageSize && this.pageNo > 0) {
					this.more = false;
				}
			},
		}
	};
</script>

<style lang="scss" scoped>
	.flex1-clz {
		margin-left: 10rpx;
		padding-top: 0rpx;
		padding-left: 10rpx;
		width: calc(100% - 10rpx - 0rpx) !important;
		padding-bottom: 30rpx;
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 0rpx;
		padding-right: 20rpx;
	}

	.flex3-clz {
		margin-left: 10rpx;
		width: calc(60.5% - 10rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}

	.text-clz {
		margin-left: 0rpx;
		color: #636363;
		font-weight: bold;
		width: calc(100% - 0rpx - 0rpx) !important;
		font-size: 26rpx !important;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
		margin-right: 0rpx;
	}

	.text1-clz {
		color: #969696;
	}

	.flex4-clz {
		margin-left: 8rpx;
		width: calc(22.8333333333% - 8rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}

	.text3-clz {
		color: #969696;
	}

	.container25498 {
		padding-left: 0px;
		padding-right: 0px;
	}

	.container25498 {}

	.bottom {
		width: 100%;
		height: 200px;
	}
</style>
