<template>
	<view class="container container25498">
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex-clz">
			<view class="diygw-col-24 text-clz"> {{share.title}} </view>
		</view>
		<view class="flex flex-wrap diygw-col-24 flex-direction-column flex1-clz">
			<view class="flex flex-wrap diygw-col-24">
				<view class="diygw-col-6 text1-clz"> 作者：{{share.author}} </view>
				<view class="diygw-col-8 text2-clz"> 发布人：{{nickname}} </view>
				<view class="diygw-col-6 text3-clz"> 下载次数：{{share.buyCount}} </view>
			</view>
			<view class="flex flex-wrap diygw-col-24 flex-direction-column flex3-clz">
				<image :src="share.cover" class="response diygw-col-16 image-clz" mode="heightFix"></image>
				<view class="diygw-col-24 text4-clz">{{share.summary}}</view>
			</view>
		</view>
		<view class="flex flex-wrap diygw-col-24 justify-end items-center white flex4-clz">
			<view class="diygw-col-3 text5-clz"> ￥积分 </view>
			<view class="diygw-col-2 text6-clz"> {{share.price}} </view>
			<view class="flex diygw-col-5 justify-center button-clz">
				<button class="diygw-btn red md radius-xs flex-sub margin-xs button-button-clz" @tap="exchange()">兑换</button>
			</view>
		</view>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	import {SHARE_URL,SHARE_EXCHANGE} from '../../utils/api.js';
	import {request} from '../../utils/request.js';
	export default {
		data() {
			return {
				share:null,
				nickname:'',
				avatarUrl:'',
				id:''
			};
		},
		onShow() {
			this.setCurrentPage(this);
		},
		onLoad(option) {
			this.id=option.id;
			console.log(this.id);
			this.getshare();
			this.init();
		},
		methods: {
			async init() {},
			async getshare(){
				let id=this.id;
				console.log("id:"+id)
				uni.showLoading({
					title:'加载中'
				});
				let res=await request(SHARE_URL+`/${id}`,'GET');
				setTimeout(()=>{
					uni.hideLoading();
				},1000);
				console.log(res.data);
				this.share=res.data.share;
				this.nickname=res.data.nickname;
				this.avatarUrl=res.data.avatarUrl;
			},
			async exchange(){
				 await request(SHARE_EXCHANGE,'POST',{
					userId: uni.getStorageSync('user').id,
					shareId: this.share.id
				}).then(res=>{
					uni.showModal({
							title:'兑换成功',
							content:'确定去查看，取消继续兑换',
							success:res=>{
								if(res.confirm){
									uni.redirectTo({
										url: `/pages/detail/detail?id=${this.id}`
									});
									
								}else if(res.cancel){
									uni.switchTab({
										url:'/pages/index/index'
									})
								}
							}
						});
					}
				);
				}
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
	.text-clz {
		font-weight: bold;
		letter-spacing: 2rpx !important;
		font-size: 28rpx !important;
	}
	.flex1-clz {
		padding-top: 10rpx;
		padding-left: 20rpx;
		padding-bottom: 20rpx;
		padding-right: 20rpx;
	}
	.text1-clz {
		font-weight: bold;
	}
	.text2-clz {
		font-weight: bold;
	}
	.text3-clz {
		font-weight: bold;
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
	}
	.flex4-clz {
		padding-top: 10rpx;
		bottom: 0rpx;
		padding-left: 20rpx;
		padding-bottom: 10rpx;
		position: absolute;
		padding-right: 20rpx;
	}
	.text5-clz {
		flex-shrink: 0;
		font-weight: bold;
		width: 80rpx !important;
	}
	.text6-clz {
		margin-left: 0rpx;
		flex-shrink: 0;
		width: calc(8.3333333333% - 0rpx - 10rpx) !important;
		font-size: 20rpx !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.button-clz {
		flex-shrink: 0;
		height: 80rpx !important;
	}
	.button-button-clz {
		font-size: 10px !important;
		margin: 3px !important;
		padding: 0px !important;
	}
	.container25498 {
		padding-left: 0px;
		padding-right: 0px;
	}
	.container25498 {
	}
</style>
