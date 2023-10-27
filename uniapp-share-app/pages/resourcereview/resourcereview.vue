<template>
	<view class="container container25498">
		<view class="flex flex-wrap diygw-col-24 flex-direction-column">
			<view class="flex flex-wrap diygw-col-24 flex-direction-column flex1-clz">
				<view class="diygw-col-24 text-clz">{{share.title}}</view>
			</view>
			<view class="flex flex-wrap diygw-col-24 flex-direction-column flex2-clz">
				<view class="flex flex-wrap diygw-col-24">
					<view class="diygw-col-6 text1-clz"> 作者：{{share.author}}</view>
					<view class="diygw-col-8 text2-clz"> 发布人：{{nickname}} </view>
					<view class="diygw-col-4 text3-clz"> 积分：{{share.price}} </view>
					<view class="mf text3-clz" v-if="share.isOriginal===true">来源：原创</view>
					<view class="mf text3-clz" v-if="share.isOriginal===false">来源：转载</view>
				</view>
				<view class="flex flex-wrap diygw-col-24 flex-direction-column flex4-clz">
					<image :src="share.cover" class="response diygw-col-16 image-clz" mode="heightFix"></image>
					<view class="diygw-col-24 text4-clz"> {{share.summary}}</view>
					<view class="diygw-col-24 text7-clz">{{share.downloadUrl}}</view>
				</view>
				<u-form-item :borderBottom="false" class="diygw-col-24" label="立刻发布" prop="switch">
					<view class="flex diygw-col-24 justify-end">
						<u-switch :activeValue="1" :inactiveValue="0" activeColor="#0081ff" @tap="changeSwitched" v-model="contribute" slot="right"></u-switch>
					</view>
				</u-form-item>
				<u-form-item :borderBottom="false" class="diygw-col-24" label="通过审核" prop="pass">
					<view class="flex diygw-col-24 justify-end">
						<u-switch :activeValue="1" :inactiveValue="0" activeColor="#0081ff" @tap="changeSwitch1" v-model="pass" slot="right"></u-switch>
					</view>
				</u-form-item>
				<u-form-item class="diygw-col-24" label="原因" prop="input4" v-if="!pass">
					<u-input :focus="input4Focus" class="" placeholder="不通过审核的原因" v-model="downloadUrl" type="text"></u-input>
				</u-form-item>
				<view class="flex diygw-col-24">
					<button class="diygw-btn red radius-xs flex-sub margin-xs button-button-clz" @tap="sumbmit">提交</button>
				</view>
			</view>
		</view>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	import{AUDIT_URL,SHARE_URL} from '../../utils/api.js';
	import{request} from '../../utils/request.js'
	export default {
		data() {
			return {
				shareId:'',
				contribute: 1,
				pass: 1,
				share: null,
				nickname:'',
				avatarUrl:'',
				reason:''
			};
		},
		onShow() {
			this.setCurrentPage(this);
		},
		onLoad(option) {
			this.shareId=option.id;
			console.log(this.shareId);
			this.getShare();
		},
		methods: {
			async getShare(){
				let id=this.shareId;
				uni.showLoading({
					title:'加载中'
				});
				let res=await request(SHARE_URL+`/${id}`,'GET');
				setTimeout(()=>{
					uni.hideLoading();
				},1000);
				console.log(res.data);
				this.share=res.data.share;
				console.log(this.share);
				this.nickname=res.data.nickname
				this.avatarUrl=res.data.avatarUrl;
			},
			changeSwitched(evt) {
				console.log(this.contribute)
			},
			changeSwitch1(evt) {
				console.log(this.pass)
			},
			async sumbmit(){
				uni.showLoading({
					title:'审核中'
				});
				let res=await request(AUDIT_URL+`/${this.shareId}`,'POST',{
					auditStatusEnum: this.pass ? 'PASS':'REJECT',
					reason: this.pass ? '通过审核' : this.reason,
					showFlag: this.contribute
				});
				setTimeout(()=>{
					uni.hideLoading();
				},500);
				console.log(res.data);
				if(res.success){
					
					uni.showToast({
						title:'审核通过',
						duration:1000
					})
					uni.switchTab({
						url:'/pages/index/index'
					});
				}
			}
		}
	};
</script>

<style lang="scss" scoped>
	.flex1-clz {
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
	.flex2-clz {
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
	.text5-clz {
		font-weight: bold;
	}
	.flex4-clz {
		padding-top: 0rpx;
		text-indent: 16rpx !important;
		padding-left: 0rpx;
		padding-bottom: 16rpx;
		border-bottom: 2rpx solid #969696;
		padding-right: 0rpx;
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
	.text7-clz {
		margin-left: 0rpx;
		text-indent: 8rpx !important;
		width: calc(100% - 0rpx - 0rpx) !important;
		margin-top: 10rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.button-button-clz {
		margin: 3px !important;
	}
	.container25498 {
		padding-left: 0px;
		padding-right: 0px;
	}
	.container25498 {
	}
</style>
