<template>
	<view class="container container25498">
		<view class="flex flex-wrap diygw-col-24 flex-direction-column">
			<view class="flex flex-wrap diygw-col-24 flex-direction-column flex1-clz">
				<view class="diygw-col-24 text-clz"> 说明：投稿审核通过后有积分奖励；资源被下载会有积分奖励；提交的资源的包含广告、侵权信息，百度网盘地址建议有密码。 </view>
			</view>
			<u-form-item class="diygw-col-24 radio-clz" label="原创" prop="radio">
				<u-radio-group class="flex flex-wrap diygw-col-24 justify-end" wrapClass=" justify-end" activeColor="#0081ff" v-model="isOriginal" @change="changeRadio">
					<u-radio shape="circle" name="1"></u-radio>
				</u-radio-group>
			</u-form-item>
			<u-form-item class="diygw-col-24" label="转载" prop="radio1">
				<u-radio-group class="flex flex-wrap diygw-col-24 justify-end" wrapClass=" justify-end" activeColor="#0081ff" v-model="isOriginal" @change="changeRadio1">
					<u-radio shape="circle" name="0"></u-radio>
				</u-radio-group>
			</u-form-item>
			
			<u-form-item class="diygw-col-24" label="标题" prop="input">
				<u-input :focus="inputFocus" class="" placeholder="请输入标题" v-model="title" type="text"></u-input>
			</u-form-item>
			<u-form-item class="diygw-col-24" label="作者" prop="input2">
				<u-input :focus="input2Focus" class="" placeholder="请输入作者" v-model="author" type="text"></u-input>
			</u-form-item>
			<u-form-item class="diygw-col-24" label="价格" prop="input3">
				<u-input :focus="input3Focus" class="" placeholder="请输入价格" v-model="price" type="text"></u-input>
			</u-form-item>
			<u-form-item class="diygw-col-24" label="简介" prop="input1">
				<u-input :focus="input1Focus" class="" placeholder="介绍一下技术干货吧" v-model="summary" type="text"></u-input>
			</u-form-item>
			<u-form-item class="diygw-col-24" label="封面图" prop="input5">
				<u-input :focus="input5Focus" class="" placeholder="封面地址" v-model="cover" type="text"></u-input>
			</u-form-item>
			<u-form-item class="diygw-col-24" label="下载地址" prop="input4">
				<u-input :focus="input4Focus" class="" placeholder="下载地址" v-model="downloadUrl" type="text"></u-input>
			</u-form-item>
			<view class="flex diygw-col-24">
				<button class="diygw-btn red radius-xs flex-sub margin-xs button-button-clz" @tap="submit">提交</button>
			</view>
		</view>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	import {CONTRIBUTE_URL} from '../../utils/api.js';
	import {request} from '../../utils/request.js'
	export default {
		data() {
			return {
				isOriginal: 1,
				title: '',
				author: '',
				price: '',
				summary: '',
				cover: 'http://imge2.jog',
				downloadUrl: 'http://pan.baid',
				inputFocus: false,
				input2Focus: false,
				input3Focus: false,
				input1Focus: false,
				input4Focus: false,
				input5Focus: false,
			};
		},
		onShow() {
			this.setCurrentPage(this);
			this.isOriginal=1;
			this.title='';
			this.author='';
			this.price='';
			this.summary='';
			this.downloadUrl='';
		},
		onLoad(option) {},
		methods: {
			changeRadio(evt) {
				this.isOriginal=1;
				console.log(this.isOriginal)
			},
			changeRadio1(evt) {
				this.isOriginal=0;
				console.log(this.isOriginal)
			},
			async submit(){
				uni.showLoading({
					title:'投稿中'
				});
				let res=await request(CONTRIBUTE_URL,'POST',{
					isOriginal: this.isOriginal==1 ? true :false,
					title: this.title,
					author: this.author,
					price: this.price,
					cover: this.cover,
					summary: this.summary,
					downloadUrl: this.downloadUrl
				});
				setTimeout(()=>{
					uni.hideLoading();
				},1000);
				console.log(res.data);
				if(res.data===1){
					uni.showModal({
						title:'提示',
						content: '投稿成功，将在最短时间内审核资源！',
						cancelText:'返回首页',
						confirmText:'继续投稿',
						success(res){
							if(res.confirm){
								console.log("CCCCCCCCCCCCCCCCC")
								this.clear();
							}
							else{
								uni.switchTab({
									url:'/pages/index/index'
								});
							}
						}
					});
				}
			},
			clear(){
				this.isOriginal=1;
				this.title='';
				this.author='';
				this.price='';
				this.summary='';
				this.downloadUrl='';
			}
		}
	};
</script>

<style lang="scss" scoped>
	.flex1-clz {
		padding-top: 16rpx;
		padding-left: 16rpx;
		padding-bottom: 16rpx;
		padding-right: 16rpx;
	}
	.text-clz {
		letter-spacing: 4rpx !important;
		font-size: 26rpx !important;
	}
	.radio-clz {
		margin-left: 0rpx;
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
