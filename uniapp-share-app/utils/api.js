// 后端基础接口
export const BASE_API_URL = 'http://10.22.242.105:8000';


// 各个服务地址
export const USER_URL = BASE_API_URL+'/user-service/user';
export const SHARE_URL = BASE_API_URL+'/content-service/share';

// 首页相关接口
// 分享列表
export const SHARE_LIST_URL = SHARE_URL+'/list';
// 最新公告
export const LATEST_NOTICE_URL = SHARE_URL+'/notice';
//兑换资源
export const SHARE_EXCHANGE = SHARE_URL+'/exchange';


// 投稿相关接口
export const CONTRIBUTE_URL=SHARE_URL+"/contribute"
//我的投稿
export const MY_CONTRIBUTE_URL=SHARE_URL+"/my-contribute"

// 我的相关接口
// 登录
export const LOGIN_URL = USER_URL+'/login';

// 我的兑换
export const MY_EXCHANGE_URL = SHARE_URL + '/my-exchange';
// 积分明细
export const MY_BONUS_LOG_URL = USER_URL + '/logs';
// 签到
export const SIGN_URL = USER_URL + '/sign';


export const MY_EXCHANGE=SHARE_URL+'/myexchange';


/******  管理员相关接口  ******/
// 管理员查看待审核分享列表
export const NOT_YET_SHARE_URL = SHARE_URL + '/admin/list';
// 管理员审核某个分享
export const AUDIT_URL = SHARE_URL + '/admin/audit';

