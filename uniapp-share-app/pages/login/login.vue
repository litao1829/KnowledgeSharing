<template>
	<view class="container container25498">
		<view class="flex flex-wrap diygw-col-24 flex-direction-column justify-center items-center flex-clz">
			<image src="/static/icon5_dy.png" class="response diygw-col-15 image-clz" mode="widthFix"></image>
		</view>
		<u-form :model="form" :rules="formRules" :errorType="['message', 'toast']" ref="formRef" class="flex diygw-form diygw-col-24 form-clz">
			<u-form-item class="diygw-col-24 input-clz" label="手机号" prop="input">
				<u-input :focus="formData.inputFocus" class="" placeholder="请输入手机号" v-model="form.phone" type="text"></u-input>
			</u-form-item>
			<u-form-item class="diygw-col-24 input1-clz" label="密码" prop="input1">
				<u-input :focus="formData.input1Focus" class="" placeholder="请输入密码" v-model="form.password" type="text"></u-input>
			</u-form-item>
			<view class="flex diygw-col-24">
				<button @tap="login" class="diygw-btn red radius-xs flex-sub margin-xs button-button-clz" :disabled="disabled">登录</button>
			</view>
		</u-form>
		<view class="clearfix"></view>
	</view>
</template>

<script>
	import {LOGIN_URL} from '../../utils/api.js'
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
				form: {
					phone: '13951905171',
					password: '123123'
				},
				formRules: {},
				formData: {
					inputFocus: false,
					input1Focus: false
				}
			};
		},
		computed:{
			disabled(){
				if((this.form.phone===''||this.form.password==='')){
					return true;
				}
				return false;
			}
		},
		onShow() {
			this.setCurrentPage(this);
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
		onReady() {
			this.$refs.formRef?.setRules(this.formRules);
		},
		methods: {
			async init() {
				await this.initResetform();
			},
			initResetform() {
				this.initform = JSON.stringify(this.form);
			},
			resetForm() {
				this.form = JSON.parse(this.initform);
			},
			async login(){
				const res=await request(LOGIN_URL,'POST',this.form);
				console.log(res)
				if(res.success===true){
					uni.showToast({
						title:'登录成功'
					});
					console.log(JSON.stringify(res.data.user)+"&&&&&&&&&&&")
					console.log(res.data.token)
					uni.setStorageSync('user',res.data.user);
					uni.setStorageSync('token',res.data.token);
					uni.switchTab({
						url:'/pages/profile/profile'
					});
				}
			}
		}
	};
</script>

<style lang="scss" scoped>
	.flex-clz {
		margin-left: 0rpx;
		width: calc(100% - 0rpx - 0rpx) !important;
		margin-top: 200rpx;
		margin-bottom: 0rpx;
		margin-right: 0rpx;
	}
	.image-clz {
		flex-shrink: 0;
		width: 280rpx !important;
	}
	.form-clz {
		padding-top: 10rpx;
		padding-left: 16rpx;
		padding-bottom: 10rpx;
		padding-right: 16rpx;
	}
	.input-clz {
		border-bottom: 2rpx solid #e3e1e1;
	}
	.input1-clz {
		border-bottom: 2rpx solid #e3e1e1;
	}
	.button-button-clz {
		margin: 15px 3px 3px 3px !important;
		
	}
	.container25498 {
		padding-left: 0px;
		padding-right: 0px;
	}
	.container25498 {
	}
</style>
