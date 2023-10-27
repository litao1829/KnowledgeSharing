<template>
	<view class="container container25498">
		<!-- 未登录显示 -->
		<view v-if="!userInfo">
			<view class="flex flex-wrap diygw-col-24 flex-direction-column justify-center items-center flex-clz">
				<image src="/static//unlogin.png" class="response diygw-col-13 image-clz" mode="aspectFill"></image>
			</view>
			<view class="flex flex-wrap diygw-col-24 flex-direction-column items-center">
				<view class=" text1-clz"> 登录，享受技术之旅 </view>
				<view class="flex flex-wrap diygw-col-24 justify-center items-center">
					<view class="flex diygw-col-6">
						<button @tap="navigateTo" data-type="page" data-url="/pages/login/login"
							class="diygw-btn red radius-xs flex-sub margin-xs button1-button-clz">登录</button>
					</view>
				</view>
			</view>
		</view>
		<!-- 未登录显示 -->
		<!-- 已经登录显示 -->
		<view v-else>
			<view>
				<view class="flex flex-wrap diygw-col-24 flex-direction-column justify-center items-center flex-clz">
					<image :src="userInfo.avatarUrl" class="  diygw-col-13 image-clz" mode="aspectFill"></image>
				</view>
				<view class="flex flex-wrap diygw-col-24 flex-direction-column items-center">
					<view class=" text-clz">{{userInfo.nickname}}</view>
					<view class=" text1-clz1"> 积分：{{userInfo.bonus}}</view>
					<view class="flex flex-wrap diygw-col-24 justify-center items-center">
						<view class="flex diygw-col-6">
							<button class="diygw-btn blue radius-xs flex-sub margin-xs button-button-clz">签到</button>
						</view>
						<view class="flex diygw-col-6">
							<button @tap="logout"
								class="diygw-btn blue radius-xs flex-sub margin-xs button1-button-clz">退出</button>
						</view>
					</view>
				</view>
			</view>
			<!-- 已经登录显示 -->
			<view class="flex flex-wrap diygw-col-24 flex-direction-column flex3-clz">
				<view class="cus flex4-clz" @tap="navigateTo" data-type="page" data-url="/pages/myexchange/myexchange">
					<view class="cus2">
						<image src="../../static/兑换.png" style="width: 25px; height: 25px;"></image>
						<view style=" margin-left: 6px;font-size: 16px;"> 我的兑换 </view>
					</view>
					<view class="diygw-col-1 text3-clz"> > </view>
				</view>

				<view class="cus flex4-clz" @tap="navigateTo" data-type="page"
					data-url="/pages/pdointsdetails/pdointsdetails">
					<view class="cus2">
						<image src="../../static/jifen.png" style="width: 25px; height: 25px;"></image>
						<view style=" margin-left: 6px;font-size: 16px;"> 积分明细 </view>
					</view>
					<view class="diygw-col-1 text3-clz"> > </view>
				</view>
				<view class="cus flex4-clz" @tap="navigateTo" data-type="page"
					data-url="/pages/mycontribute/mycontribute">
					<view class="cus2">
						<image src="../../static/tougao.png" style="width: 25px; height: 25px;"></image>
						<view style=" margin-left: 6px;font-size: 16px;"> 我的投稿 </view>
					</view>
					<view class="diygw-col-1 text3-clz"> > </view>
				</view>
				<view class="cus flex4-clz" v-if="userInfo.roles=='admin'" @tap="navigateTo" data-type="page" data-url="/pages/audit/audit">
					<view class="cus2">
						<image src="../../static/shenhe.png" style="width: 25px; height: 25px;"></image>
						<view style=" margin-left: 6px;font-size: 16px;"> 审核投稿 </view>
					</view>
					<view class="diygw-col-1 text3-clz"> > </view>
				</view>
			</view>
		</view>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//用户全局信息
				userInfo: null,
				//页面传参
				globalOption: {},
				//自定义全局变量
				globalData: {}
			};
		},
		onShow() {
			this.setCurrentPage(this);

			//从uni-app中获取用户
			if (uni.getStorageSync('user')) {
				this.userInfo = uni.getStorageSync('user');
			}
			console.log(this.userInfo);
		},
		onLoad(option) {
			this.setCurrentPage(this);
			if (option) {
				this.setData({
					globalOption: this.getOption(option)
				});
			}

			this.init();
		},
		methods: {
			async init() {},
			logout() {
				this.userInfo = null;
				uni.clearStorageSync();
			}
		}
	};
</script>

<style lang="scss" scoped>
	.cus {
		height: 40px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
	}

	.cus2 {
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.flex-clz {
		margin-left: 0rpx;
		width: calc(100% - 0rpx - 0rpx) !important;
		margin-top: 60rpx;
		margin-bottom: 10rpx;
		margin-right: 0rpx;
	}

	.image-clz {
		overflow: hidden;
		width: 220rpx !important;
		border-radius: 50%;
		height: 220rpx !important;
	}

	.text-clz {
		margin-top: 10px;
		font-weight: bold;
		font-size: 29rpx !important;
	}

	.text1-clz {
		margin-left: 0rpx;
		font-weight: 600;
		font-size: 16px;
		width: calc(16.6666666667% - 0rpx - 0rpx) !important;
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		margin-right: 0rpx;
	}

	.text1-clz1 {
		margin-left: 0rpx;
		font-size: 14px;
		width: calc(20.6666666667% - 0rpx - 0rpx) !important;
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		margin-right: 0rpx;
	}

	.button-button-clz {
		margin: 3px !important;
	}

	.button1-button-clz {
		margin: 3px !important;
	}

	.flex3-clz {
		margin-left: 0rpx;
		width: calc(100% - 0rpx - 0rpx) !important;
		margin-top: 40rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}

	.flex4-clz {
		padding-top: 15rpx;
		padding-left: 15rpx;
		padding-bottom: 15rpx;
		border-bottom: 2rpx solid #e3e1e1;
		padding-right: 15rpx;
	}

	.text2-clz {
		font-size: 15px;
	}

	.text3-clz {
		font-size: 18px;
	}

	.flex5-clz {
		padding-top: 15rpx;
		padding-left: 15rpx;
		padding-bottom: 15rpx;
		border-bottom: 2rpx solid #e3e1e1;
		padding-right: 15rpx;
	}

	.text4-clz {
		font-size: 15px;
	}

	.text5-clz {
		font-size: 18px;
	}

	.flex6-clz {
		padding-top: 15rpx;
		padding-left: 15rpx;
		padding-bottom: 15rpx;
		border-bottom: 2rpx solid #e3e1e1;
		padding-right: 15rpx;
	}

	.text6-clz {
		font-size: 15px;
	}

	.text7-clz {
		font-size: 18px;
	}

	.flex7-clz {
		padding-top: 15rpx;
		padding-left: 15rpx;
		padding-bottom: 15rpx;
		border-bottom: 2rpx solid #e3e1e1;
		padding-right: 15rpx;
	}

	.text8-clz {
		font-size: 15px;
	}

	.text9-clz {
		font-size: 18px;
	}

	.container25498 {
		padding-left: 0px;
		padding-right: 0px;
	}

	.container25498 {}
</style>
