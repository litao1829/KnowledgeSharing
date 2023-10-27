<template>
	<view class="container container25498">
		<view class="flex flex-wrap diygw-col-24 flex-direction-column">
			<view class="flex flex-wrap diygw-col-24 items-center flex-clz" v-for="(item,index) in logs" :key="index">
				<image src="/static/download.png" class="response diygw-col-1" mode="widthFix"></image>
				<view class="diygw-col-12 text-clz">{{item.createTime}}</view>
				<view class="diygw-col-4" v-if="item.event==='BUY'">兑换</view>
				<view class="diygw-col-4" v-else>投稿</view>
				<view class="diygw-col-2" v-if="item.event==='BUY'" style="color: red;">{{item.value}}</view>
				<view class="diygw-col-2" v-else style="color: lawngreen;">{{+item.value}}</view>
				<view class="flex flex-wrap diygw-col-3 justify-end">
					<view class="diygw-col-0 text1-clz"> > </view>
				</view>
			</view>
		</view>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	import {MY_BONUS_LOG_URL} from '../../utils/api.js';
	import { request } from '../../utils/request.js'
	
	export default {
		data() {
			return {
				logs:[],
				id:''
			};
		},
		onShow() {
			this.setCurrentPage(this);
		},
		onLoad(option) {
			this.setCurrentPage(this);
			this.id=uni.getStorageSync('user').id
			console.log(this.id);
			uni.showLoading({
				title:'加载中'
			});
			this.getLogs();
		},
		methods: {
			async getLogs(){
				let res = await request(MY_BONUS_LOG_URL+`/${this.id}`,'GET',{});
				setTimeout(()=>{
					uni.hideLoading();
				},500);
				
				if(res.success==true){
					this.logs=res.data;
				}else{
					uni.showToast({
						title:'没有权限访问'
					})
				}
			}
		}
	};
</script>

<style lang="scss" scoped>
	.flex-clz {
		margin-left: 16rpx;
		padding-top: 10rpx;
		padding-left: 0rpx;
		width: calc(100% - 16rpx - 0rpx) !important;
		padding-bottom: 20rpx;
		margin-top: 20rpx;
		margin-bottom: 0rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 0rpx;
		padding-right: 40rpx;
	}
	.text-clz {
		margin-left: 30rpx;
		letter-spacing: 2rpx !important;
		width: calc(83.3333333333% - 30rpx - 0rpx) !important;
		font-size: 26rpx !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.text1-clz {
		margin-left: 30rpx;
		flex-shrink: 0;
		width: 10rpx !important;
		font-size: 28rpx !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.flex5-clz {
		margin-left: 16rpx;
		padding-top: 10rpx;
		padding-left: 0rpx;
		width: calc(100% - 16rpx - 0rpx) !important;
		padding-bottom: 20rpx;
		margin-top: 20rpx;
		margin-bottom: 0rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 0rpx;
		padding-right: 40rpx;
	}
	.text4-clz {
		margin-left: 30rpx;
		letter-spacing: 2rpx !important;
		width: calc(83.3333333333% - 30rpx - 0rpx) !important;
		font-size: 26rpx !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.text5-clz {
		margin-left: 30rpx;
		flex-shrink: 0;
		width: 10rpx !important;
		font-size: 28rpx !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.flex3-clz {
		margin-left: 16rpx;
		padding-top: 10rpx;
		padding-left: 0rpx;
		width: calc(100% - 16rpx - 0rpx) !important;
		padding-bottom: 20rpx;
		margin-top: 20rpx;
		margin-bottom: 0rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 0rpx;
		padding-right: 40rpx;
	}
	.text2-clz {
		margin-left: 30rpx;
		letter-spacing: 2rpx !important;
		width: calc(83.3333333333% - 30rpx - 0rpx) !important;
		font-size: 26rpx !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.text3-clz {
		margin-left: 30rpx;
		flex-shrink: 0;
		width: 10rpx !important;
		font-size: 28rpx !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.container25498 {
		padding-left: 0px;
		padding-right: 0px;
	}
	.container25498 {
	}
</style>
