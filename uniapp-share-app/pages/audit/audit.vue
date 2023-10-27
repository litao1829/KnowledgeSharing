<template>
	<view class="container container25498">
		<view class="flex flex-wrap diygw-col-24 flex-direction-column">
			<view class="flex flex-wrap diygw-col-24 flex1-clz" v-for="(item,index) in shares" :key="index">
				<view class="flex flex-wrap diygw-col-4 flex-direction-column justify-center items-stretch">
					<image :src="item.cover" style="height: 60px;width: 60px; border-radius: 50px;"></image>
				</view>
				<view class="flex flex-wrap diygw-col-15 flex-direction-column flex3-clz">
					<view class="diygw-col-24 text-clz"> {{item.title}} </view>
					<view class="diygw-text-line2 diygw-col-24 text1-clz">{{item.summary}}</view>
				</view>
				<view class="flex flex-wrap diygw-col-5 flex-direction-column justify-center items-end flex4-clz">
					<view class="diygw-text-line2 diygw-col-22 text3-clz" @tap="auditDetail(item.id)"> 去审核 > </view>
				</view>
			</view>
		</view>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	import {NOT_YET_SHARE_URL } from '../../utils/api.js';
	import { request } from '../../utils/request.js'
	export default {
		data() {
			return {
				shares:[]
			};
		},
		onShow() {
			this.setCurrentPage(this);
		},
		onLoad(option) {
			uni.showLoading({
				title:'加载中'
			});
			this.getShares();
		},
		onPullDownRefresh(){
			this.getShares();
		},
		methods: {
			async getShares(){
				let res=await request(NOT_YET_SHARE_URL,'GET',{});
				console.log(res);
				setTimeout(()=>{
					uni.hideLoading();
				},2000);
				if(res.success==true){
					this.shares=res.data;
					uni.stopPullDownRefresh();
				}else{
					uni.showToast({
						title:'没有权限访问'
					})
				}
			},
			auditDetail(id){
				uni.navigateTo({
					url: `/pages/resourcereview/resourcereview?id=${id}`
				})
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
		padding-bottom: 16rpx;
		margin-top: 16rpx;
		margin-bottom: 0rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 0rpx;
		padding-right: 10rpx;
	}
	.flex3-clz {
		margin-left: 10rpx;
		width: calc(62.5% - 10rpx - 10rpx) !important;
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
		width: calc(20.8333333333% - 8rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.text3-clz {
		color: #969696;
	}
	.text2-clz {
		margin-left: 0rpx;
		color: #636363;
		font-weight: bold;
		width: calc(100% - 0rpx - 0rpx) !important;
		font-size: 26rpx !important;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
		margin-right: 0rpx;
	}
	.text4-clz {
		color: #969696;
	}
	.text5-clz {
		color: #969696;
	}
	.container25498 {
		padding-left: 0px;
		padding-right: 0px;
	}
	.container25498 {
	}
</style>
