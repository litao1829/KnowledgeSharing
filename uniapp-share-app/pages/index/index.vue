<template>
	<view class="container container25498">
		<view class="flex diygw-col-24 flex-direction-column">
			<view class="diygw-tabs text-center solid-bottom justify-center tabs-title">
				<view class="diygw-tab-item tabs-item-title flex-sub" :class="index == tabsIndex ? ' cur text-green ' : ''" v-for="(item, index) in tabsDatas" :key="index" @click="changeTabs" :data-index="index"> <text v-if="item.icon" :class="item.icon"></text> {{ item.text }} </view>
			</view>
			<view class="">
				<view v-if="tabsIndex == 0" class="flex-sub">
					<view class="diygw-col-24 search-clz">
						<view class="diygw-search">
							<view class="flex1 align-center flex padding-xs solid radius search-search">
								<text style="color: #555 !important" class="diy-icon-search"></text>
								<input class="flex1" name="search" type="" v-model="search" placeholder="请输入搜索内容" />
							</view>
						</view>
					</view>
					<view class="flex diygw-col-24 noticebar-clz">
						<diy-noticebar class="flex1 diy-notice-bar" color="#db5e54" bgColor="#fff" leftIcon="diy-icon-notification">
							<block v-slot:content>
								<text class="diy-notice-item">{{notice}}</text>
							</block>
						</diy-noticebar>
					</view>
					<!-- 请求数据渲染开始 -->
					<view v-for="(item,index) in shares" :key="index">
						<view class="flex flex-wrap diygw-col-24 flex-clz" >
							<view class="customer-images diygw-col-3" style="position: relative;">
								<text style="position: absolute; left: -9px;top: -5px; background-color: red; 
								font-size: 13px; padding: 0 5px; z-index: 99;color: white;" v-if="item.isOriginal">原创</text>
								<text style="position: absolute; left: -9px;top: -5px; background-color: darkgreen; font-size: 13px; padding: 0 5px; 
								z-index: 99;color: white;" v-else>转载</text>
								<image :src="item.cover" style="height: 50px;width: 55px; border-radius: 5px;"></image>
								</view>
							<view class="flex flex-wrap diygw-col-14 flex-direction-column flex2-clz">
								<view class="diygw-col-24 text-clz"> {{item.title}} </view>
								<view class="diygw-text-line2 diygw-col-24" >{{item.summary}}</view>
							</view>
							<view class="flex flex-wrap diygw-col-4 flex-direction-column justify-center items-end flex3-clz">
								<view class="diygw-col-19 text2-clz"> {{item.price}}积分</view>
								<view class="diygw-text-line2 diygw-col-13" v-if="item.downloadUrl" @tap="gotoDetail(item.id)"> 下载 </view>
								<view class="diygw-text-line2 diygw-col-13" v-else @tap="gotoExchange(item.id)"> 兑换 </view>
							</view>
						</view>
					</view>
					<!-- 请求数据渲染结束 -->
					
				</view>
				<view v-if="tabsIndex == 1" class="flex-sub">
					<view class="flex flex-wrap diygw-col-24 flex-direction-column flex12-clz">
						<view class="flex flex-wrap diygw-col-24 flex13-clz">
							<view class="flex flex-wrap diygw-col-24 flex-direction-column justify-center items-center red flex15-clz">
								<view class="diygw-col-10 text12-clz"> 01 </view>
							</view>
							<view class="flex flex-wrap diygw-col-10 flex-direction-column flex16-clz">
								<view class="diygw-col-23 text13-clz diygw-ellipsis"> 积分获得方式 </view>
							</view>
						</view>
						<view class="flex flex-wrap diygw-col-24 flex-direction-column flex14-clz">
							<view class="diygw-col-24 text15-clz"> 每日签到 </view>
							<view class="diygw-col-24 text14-clz"> 投稿 </view>
						</view>
					</view>
					<view class="flex flex-wrap diygw-col-24 flex-direction-column flex17-clz">
						<view class="flex flex-wrap diygw-col-24 flex18-clz">
							<view class="flex flex-wrap diygw-col-24 flex-direction-column justify-center items-center red flex19-clz">
								<view class="diygw-col-10 text16-clz"> 02 </view>
							</view>
							<view class="flex flex-wrap diygw-col-7 flex-direction-column flex20-clz">
								<view class="diygw-col-23 text17-clz diygw-ellipsis"> 深入交流 </view>
							</view>
						</view>
						<view class="flex flex-wrap diygw-col-24 flex-direction-column flex21-clz">
							<view class="diygw-col-24 text18-clz"> 技术交流群：88888888 </view>
							<view class="diygw-col-24 text19-clz"> 私人微信：Infinnity </view>
						</view>
					</view>
					<view class="flex flex-wrap diygw-col-24 flex-direction-column flex22-clz">
						<view class="flex flex-wrap diygw-col-24 flex23-clz">
							<view class="flex flex-wrap diygw-col-24 flex-direction-column justify-center items-center red flex24-clz">
								<view class="diygw-col-10 text20-clz"> 03 </view>
							</view>
							<view class="flex flex-wrap diygw-col-15 flex-direction-column flex25-clz">
								<view class="diygw-col-23 text21-clz diygw-ellipsis"> 公众号(技术干货分享) </view>
							</view>
						</view>
						<view class="flex flex-wrap diygw-col-24 flex-direction-column flex26-clz">
							<view class="diygw-col-24 text22-clz"> 点击右上角-> </view>
							<view class="diygw-col-24 text23-clz"> 公众微信号 </view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	import {LATEST_NOTICE_URL,SHARE_LIST_URL} from '../../utils/api.js'
	import {request} from '../../utils/request.js'
	export default {
		data() {
			return {
				//用户全局信息
				userInfo: {},
				//页面传参
				globalOption: {},
				//自定义全局变量
				globalData: {},
				notice:"自定义",
				tabsDatas: [
					{ text: `发现`, icon: `diy-icon-discoverfill` },
					{ text: `使用说明`, icon: `diy-icon-wendang` }
				],
				tabsLeft: 0,
				tabsWidth: 0,
				tabsItemWidth: 0,
				tabsIndex: 0,
				search: '',
				shares:[],
				pageNo:1,
				pageSize:8,
				more:true
			};
		},
		onShow() {
			this.setCurrentPage(this);
			this.getNotice();
			this.getShare(true);
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
		//下拉刷新
		onPullDownRefresh(){
			this.getShare(true);
		},
		//触底加载更多
		onReachBottom(){
			//没有更多数据了
			if(!this.more){
				uni.showToast({
					title:'已经加载完毕了',
					duration:1000
				});
				return false;
			}
			
			//正常加载下一页
			this.pageNo=this.pageNo+1;
			uni.showLoading({
				title:'加载中'
			});
			//不带参数的请求，就是分页加载
			this.getShare();
			setTimeout(()=>{
				uni.hideLoading();
			},1000);
		},
		methods: {
			async init() {},
			changeTabs(evt) {
				let { index } = evt.currentTarget.dataset;
				if (index == this.tabsIndex) return;
				this.setData({
					tabsIndex: index
				});
				
			},
			async getNotice(){
				let res=await request(LATEST_NOTICE_URL,"GET");
				console.log(res.data);
				this.notice=res.data.content;
			},
			async getShare(init){
				console.log("首页开始请求");
				//入参为true，从第一页重新开始加载数据，场景为：下来刷新、每次进入
				if(init){
					this.pageNo=1;
					this.more=true;
				}
				
				
				let res=await request(SHARE_LIST_URL,'GET',{
					pageNo:this.pageNo,
					pageSize:this.pageSize
				});
				
				//请求结束延时隐藏加载动画
				setTimeout(()=>{
					uni.hideLoading();
				},2000);
				
				//处理进入首页加载数据和下拉刷新场景
				if(init){
					//获得新的数据集合，将数据初始化为本次请求放回结果
					this.shares=res.data;
					//停止下拉刷新
					uni.stopPullDownRefresh();
					console.log("AAAAAAAAAAAAAAAAAA");
				}else{
					//不是下拉刷新，是加载下一页，则把新数据追加到后面，不能覆盖原来的值
					this.shares=this.shares.concat(res.data);
				}
				
				//加载到了最后一页
				if(res.data.length<this.pageSize&&this.pageNo>0){
					this.more=false;
				}
			
			},
			gotoDetail(id){
				uni.navigateTo({
					url:"/pages/detail/detail?id="+id
				})
			},
			gotoExchange(id){
				uni.navigateTo({
					url: "/pages/exchange/exchange?id="+id
				})
			}
		}
	};
</script>

<style lang="scss" scoped>
	.customer-images{
		display: flex;
		
	}
	.tabs-item-title {
		color: #040404 !important;
	}
	.tabs-title {
	}
	.tabs-item-title.cur {
		color: #db5e54 !important;
	}
	.search-clz {
		padding-top: 10rpx;
		padding-left: 20rpx;
		padding-bottom: 10rpx;
		padding-right: 20rpx;
	}
	.search-search {
		background-color: #f9f6f6 !important;
	}
	.noticebar-clz {
		font-size: 20rpx !important;
	}
	.flex-clz {
		margin-left: 20rpx;
		padding-top: 5rpx;
		padding-left: 0rpx;
		width: calc(100% - 14rpx - 14rpx) !important;
		padding-bottom: 25rpx;
		margin-top: 20rpx;
		margin-bottom: 5rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 20rpx;
		padding-right: 0rpx;
	}
	.flex2-clz {
		margin-left: 10rpx;
		width: calc(66.6666666667% - 10rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		
	}
	.text-clz {
		font-weight: bold;
		font-size: 26rpx !important;
	}
	.flex3-clz {
		margin-left: 10rpx;
		width: 50px;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.text2-clz {
		font-weight: bold;
		font-size: 26rpx !important;
	}
	.flex4-clz {
		margin-left: 10rpx;
		padding-top: 0rpx;
		padding-left: 0rpx;
		width: calc(100% - 10rpx - 10rpx) !important;
		padding-bottom: 4rpx;
		margin-top: 10rpx;
		margin-bottom: 0rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 10rpx;
		padding-right: 0rpx;
	}
	.flex6-clz {
		margin-left: 10rpx;
		width: calc(66.6666666667% - 10rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.text4-clz {
		font-weight: bold;
		font-size: 26rpx !important;
	}
	.flex7-clz {
		margin-left: 20rpx;
		width: calc(20.8333333333% - 20rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.text6-clz {
		font-weight: bold;
		font-size: 26rpx !important;
	}
	.flex8-clz {
		margin-left: 10rpx;
		padding-top: 0rpx;
		padding-left: 0rpx;
		width: calc(100% - 10rpx - 10rpx) !important;
		padding-bottom: 4rpx;
		margin-top: 10rpx;
		margin-bottom: 0rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 10rpx;
		padding-right: 0rpx;
	}
	.flex10-clz {
		margin-left: 10rpx;
		width: calc(66.6666666667% - 10rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.text8-clz {
		font-weight: bold;
		font-size: 26rpx !important;
	}
	.flex11-clz {
		margin-left: 20rpx;
		width: calc(20.8333333333% - 20rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.text10-clz {
		font-weight: bold;
		font-size: 26rpx !important;
	}
	.flex27-clz {
		margin-left: 10rpx;
		padding-top: 0rpx;
		padding-left: 0rpx;
		width: calc(100% - 10rpx - 10rpx) !important;
		padding-bottom: 4rpx;
		margin-top: 10rpx;
		margin-bottom: 0rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 10rpx;
		padding-right: 0rpx;
	}
	.flex29-clz {
		margin-left: 10rpx;
		width: calc(66.6666666667% - 10rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.text24-clz {
		font-weight: bold;
		font-size: 26rpx !important;
	}
	.flex30-clz {
		margin-left: 20rpx;
		width: calc(20.8333333333% - 20rpx - 10rpx) !important;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		margin-right: 10rpx;
	}
	.text26-clz {
		font-weight: bold;
		font-size: 26rpx !important;
	}
	.flex12-clz {
		margin-left: 16rpx;
		padding-top: 0rpx;
		padding-left: 16rpx;
		width: calc(100% - 16rpx - 16rpx) !important;
		padding-bottom: 0rpx;
		margin-top: 20rpx;
		margin-bottom: 10rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 16rpx;
		padding-right: 16rpx;
	}
	.flex13-clz {
		flex-shrink: 0;
		width: 600rpx !important;
	}
	.flex15-clz {
		flex-shrink: 0;
		width: 76rpx !important;
	}
	.text12-clz {
		color: #f8f8f8;
		font-weight: bold;
	}
	.flex16-clz {
		margin-left: 10rpx;
		padding-top: 16rpx;
		padding-left: 16rpx;
		width: calc(41.6666666667% - 10rpx - 0rpx) !important;
		padding-bottom: 10rpx;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		border-bottom: 6rpx solid #f29837;
		margin-right: 0rpx;
		padding-right: 0rpx;
	}
	.text13-clz {
		color: #db5e54;
		font-weight: bold;
		font-size: 32rpx !important;
	}
	.flex14-clz {
		margin-left: 0rpx;
		width: calc(100% - 0rpx - 0rpx) !important;
		margin-top: 10rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.text15-clz {
		margin-left: 0rpx;
		letter-spacing: 4rpx !important;
		width: calc(100% - 0rpx - 0rpx) !important;
		font-size: 30rpx !important;
		margin-top: 16rpx;
		margin-bottom: 4rpx;
		margin-right: 0rpx;
	}
	.text14-clz {
		margin-left: 0rpx;
		width: calc(100% - 0rpx - 0rpx) !important;
		font-size: 28rpx !important;
		margin-top: 10rpx;
		margin-bottom: 6rpx;
		margin-right: 0rpx;
	}
	.flex17-clz {
		margin-left: 16rpx;
		padding-top: 0rpx;
		padding-left: 16rpx;
		width: calc(100% - 16rpx - 16rpx) !important;
		padding-bottom: 0rpx;
		margin-top: 20rpx;
		margin-bottom: 10rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 16rpx;
		padding-right: 16rpx;
	}
	.flex18-clz {
		flex-shrink: 0;
		width: 600rpx !important;
	}
	.flex19-clz {
		flex-shrink: 0;
		width: 76rpx !important;
	}
	.text16-clz {
		color: #f8f8f8;
		font-weight: bold;
	}
	.flex20-clz {
		margin-left: 10rpx;
		padding-top: 16rpx;
		padding-left: 16rpx;
		width: calc(29.1666666667% - 10rpx - 0rpx) !important;
		padding-bottom: 10rpx;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		border-bottom: 6rpx solid #f29837;
		margin-right: 0rpx;
		padding-right: 0rpx;
	}
	.text17-clz {
		color: #db5e54;
		font-weight: bold;
		font-size: 32rpx !important;
	}
	.flex21-clz {
		margin-left: 0rpx;
		width: calc(100% - 0rpx - 0rpx) !important;
		margin-top: 10rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.text18-clz {
		margin-left: 0rpx;
		letter-spacing: 4rpx !important;
		width: calc(100% - 0rpx - 0rpx) !important;
		font-size: 30rpx !important;
		margin-top: 16rpx;
		margin-bottom: 4rpx;
		margin-right: 0rpx;
	}
	.text19-clz {
		margin-left: 0rpx;
		width: calc(100% - 0rpx - 0rpx) !important;
		font-size: 28rpx !important;
		margin-top: 10rpx;
		margin-bottom: 6rpx;
		margin-right: 0rpx;
	}
	.flex22-clz {
		margin-left: 16rpx;
		padding-top: 0rpx;
		padding-left: 16rpx;
		width: calc(100% - 16rpx - 16rpx) !important;
		padding-bottom: 0rpx;
		margin-top: 20rpx;
		margin-bottom: 10rpx;
		border-bottom: 2rpx solid #e3e1e1;
		margin-right: 16rpx;
		padding-right: 16rpx;
	}
	.flex23-clz {
		flex-shrink: 0;
		width: 600rpx !important;
	}
	.flex24-clz {
		flex-shrink: 0;
		width: 76rpx !important;
	}
	.text20-clz {
		color: #f8f8f8;
		font-weight: bold;
	}
	.flex25-clz {
		margin-left: 10rpx;
		padding-top: 16rpx;
		padding-left: 16rpx;
		width: calc(62.5% - 10rpx - 0rpx) !important;
		padding-bottom: 10rpx;
		margin-top: 0rpx;
		margin-bottom: 0rpx;
		border-bottom: 6rpx solid #f29837;
		margin-right: 0rpx;
		padding-right: 0rpx;
	}
	.text21-clz {
		color: #db5e54;
		font-weight: bold;
		font-size: 32rpx !important;
	}
	.flex26-clz {
		margin-left: 0rpx;
		width: calc(100% - 0rpx - 0rpx) !important;
		margin-top: 10rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.text22-clz {
		margin-left: 0rpx;
		letter-spacing: 4rpx !important;
		width: calc(100% - 0rpx - 0rpx) !important;
		font-size: 30rpx !important;
		margin-top: 16rpx;
		margin-bottom: 4rpx;
		margin-right: 0rpx;
	}
	.text23-clz {
		margin-left: 0rpx;
		width: calc(100% - 0rpx - 0rpx) !important;
		font-size: 28rpx !important;
		margin-top: 10rpx;
		margin-bottom: 6rpx;
		margin-right: 0rpx;
	}
	.container25498 {
		padding-left: 0px;
		padding-right: 0px;
	}
	.container25498 {
	}
</style>
