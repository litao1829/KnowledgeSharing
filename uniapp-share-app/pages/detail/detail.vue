<template>
	<view class="container container25498">
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex-clz">
			<view class="diygw-col-24 text-clz">{{share.title}}</view>
		</view>
		<view class="cusvim diygw-col-24">
			<view class="">作者:{{share.author}}</view>
			<view class="mf">发布人：{{nickname}}</view>
			<view class="mf">积分：{{share.price}}</view>
			<view class="mf" v-if="share.isOriginal===true">来源：原创</view>
			<view class="mf" v-if="share.isOriginal===false">来源：转载</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex1-clz">
			<view class="flex flex-wrap diygw-col-24 flex-direction-column flex3-clz">
				<image :src="share.cover" class="response diygw-col-16 image-clz" mode="heightFix"></image>
				<view class="diygw-col-24 text4-clz">{{share.summary}}</view>
				<view class="diygw-col-24 text7-clz">{{share.downloadUrl}}</view>
				<view class="flex diygw-col-24 button-clz">
					<button style="color: #0d0d0d !important; background-color: #ff0000 !important ;color: #ffffff;" class="diygw-btn grey radius-xs flex-sub margin-xs button-button-clz"
					@tap="paste">复制下载地址</button>
				</view>
			</view>
		</view>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	import {SHARE_URL} from '../../utils/api.js';
	import {request} from '../../utils/request.js';
	export default {
		data() {
			return {
				//用户全局信息
				userInfo: {},
				//页面传参
				globalOption: {},
				//自定义全局变量
				globalData: {},
				share: null,
				nickname:'',
				avatarUrl:'',
				id:''
			};
		},
		onShow() {
			this.setCurrentPage(this);
			
		},
		onLoad(option){
			this.id=option.id;
			console.log(this.id);
			this.getShare();
		},
		methods: {
			async init() {},
			async getShare(){
				let id=this.id;
				uni.showLoading({
					title:'加载中'
				});
				let res=await request(SHARE_URL+`/${id}`,'GET');
				setTimeout(()=>{
					uni.hideLoading();
				},500);
				console.log(res.data);
				this.share=res.data.share;
				console.log(this.share);
				this.nickname=res.data.nickname
				this.avatarUrl=res.data.avatarUrl;
			},
			paste(){
				uni.setClipboardData({
					data:this.share.downloadUrl,
					success:function(){
						uni.showToast({
							title:'下载地址已复制'
						})
					}
				});
			},
		}
	};
</script>

<style lang="scss" scoped>
	.flex-clz {
		padding-top: 20rpx;
		padding-left: 20rpx;
		padding-bottom: 20rpx;
		padding-right: 20rpx;
	}
	.cusvim{
		display: flex;
		flex-direction: row;
		padding-left: 20rpx;
		font-weight: bold;
	}
	.mf{
		margin-left: 10px;
	}
	.text-clz {
		font-weight: bold;
		letter-spacing: 2rpx !important;
		font-size: 32rpx !important;
	}
	.flex1-clz {
		padding-top: 10rpx;
		padding-left: 20rpx;
		padding-bottom: 20rpx;
		padding-right: 20rpx;
	}
	.flex3-clz {
		text-indent: 16rpx !important;
	}
	.image-clz {
		margin-left: 0rpx;
		width: calc(66.6666666667% - 0rpx - 0rpx) !important;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
		margin-right: 0rpx;
	}
	.text4-clz {
		text-indent: 8rpx !important;
		font-size: 14px;
	}
	.text7-clz {
		margin-left: 0rpx;
		text-indent: 8rpx !important;
		width: calc(100% - 0rpx - 0rpx) !important;
		margin-top: 10rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.button-clz {
		margin-left: 0rpx;
		width: calc(100% - 0rpx - 0rpx) !important;
		margin-top: 16rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.button-button-clz {
		font-size: 15px !important;
		margin: 3px !important;
	}
	.container25498 {
		padding-left: 0px;
		padding-right: 0px;
	}
	.container25498 {
	}
</style>
