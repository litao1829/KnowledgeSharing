<template>
	<view class="container container25498">
		<view class="flex flex-wrap diygw-col-24 flex-direction-column">
			<view class="flex flex-wrap diygw-col-24 flex5-clz" v-for="(item,index) in shares" :key="index">
				<view class="flex flex-wrap diygw-col-4 flex-direction-column justify-center items-stretch">
					<image :src="item.cover" style="height: 60px;width: 60px; border-radius: 50px;"></image>
				</view>
				<view class="flex flex-wrap diygw-col-13 flex-direction-column flex7-clz">
					<view class="diygw-col-24 text4-clz"> {{item.title}} </view>
					<view class="diygw-text-line2 diygw-col-24 text5-clz"> {{item.summary}} </view>
				</view>
				<view class="flex flex-wrap diygw-col-4 flex-direction-column justify-center items-center flex4-clz">
					<view class="diygw-col-19 text2-clz"> 积分{{item.price}}</view>
					<view class="diygw-text-line2 diygw-col-13" @tap="gotoDetail(item.id)"> 下载 </view>
				</view>
			</view>
		</view>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	
	import {MY_EXCHANGE} from '../../utils/api.js';
	import {request} from '../../utils/request.js'
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
			this.setCurrentPage(this);
			this.getShares();
		},
		methods: {
			async getShares(){
				let res=await request(MY_EXCHANGE,'GET',{});
				console.log(res);
				setTimeout(()=>{
					uni.hideLoading();
				},2000);
				if(res.success==true){
					this.shares=res.data;
				}else{
					uni.showToast({
						title:'没有权限访问'
					})
				}
			},
			gotoDetail(id){
				uni.navigateTo({
					url:"/pages/detail/detail?id="+id
				})
			},
		}
	};
</script>

<style lang="scss" scoped>
	.flex5-clz {
		margin-left: 10rpx;
		padding-top: 0rpx;
		padding-left: 10rpx;
		width: calc(100% - 10rpx - 0rpx) !important;
		padding-bottom: 16rpx;
		margin-top: 16rpx;
		margin-bottom: 0rpx;
		border-bottom: 4rpx dotted #e3e1e1;
		margin-right: 0rpx;
		padding-right: 10rpx;
	}
	.image1-clz {
		border-bottom-left-radius: 100rpx;
		overflow: hidden;
		border-top-left-radius: 100rpx;
		border-top-right-radius: 100rpx;
		border-bottom-right-radius: 100rpx;
	}
	.flex7-clz {
		margin-left: 10rpx;
		width: calc(60.6666666667% - 10rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.text4-clz {
		color: #636363;
		font-weight: bold;
		width: calc(100% - 0rpx - 0rpx) !important;
		font-size: 26rpx !important;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
		margin-right: 0rpx;
	}
	.text5-clz {
		color: #969696;
	}
	.flex4-clz {
		
		margin-left: 20rpx;
		width: calc(16.6666666667% - 0rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.text2-clz {
		font-weight: bold;
		font-size: 26rpx !important;
	}
	.container25498 {
		padding-left: 0px;
		padding-right: 0px;
	}
	.container25498 {
	}
</style>
