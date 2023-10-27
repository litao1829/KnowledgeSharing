
  ;(function(){
  let u=void 0,isReady=false,onReadyCallbacks=[],isServiceReady=false,onServiceReadyCallbacks=[];
  const __uniConfig = {"pages":[],"globalStyle":{"navigationBar":{"backgroundColor":"#DB5E54","titleText":"share-app","type":"default","titleColor":"#ffffff"},"isNVue":false},"nvue":{"compiler":"uni-app","styleCompiler":"uni-app","flex-direction":"column"},"renderer":"auto","appname":"share-app","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":true},"compilerVersion":"3.7.3","entryPagePath":"pages/index/index","entryPageQuery":"","realEntryPagePath":"","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000},"tabBar":{"position":"bottom","color":"#999999","selectedColor":"#08c261","borderStyle":"black","blurEffect":"none","fontSize":"10px","iconWidth":"24px","spacing":"3px","height":"50px","backgroundColor":"#ffffff","list":[{"pagePath":"pages/index/index","iconPath":"/static/global/shouye_unselect.png","selectedIconPath":"/static/global/shouye_selected.png","text":"首页"},{"pagePath":"pages/tocontribute/tocontribute","iconPath":"/static/global/tougao_unselect.png","selectedIconPath":"/static/global/tougao_selected.png","text":"投稿"},{"pagePath":"pages/profile/profile","iconPath":"/static/global/wode_unselect.png","selectedIconPath":"/static/global/wode_selected.png","text":"我的"}],"selectedIndex":0,"shown":true},"locales":{},"darkmode":false,"themeConfig":{}};
  const __uniRoutes = [{"path":"pages/index/index","meta":{"isQuit":true,"isEntry":true,"isTabBar":true,"tabBarIndex":0,"enablePullDownRefresh":true,"navigationBar":{"backgroundColor":"#db5e54","titleText":"首页","type":"default"},"isNVue":false}},{"path":"pages/profile/profile","meta":{"isQuit":true,"isTabBar":true,"tabBarIndex":2,"navigationBar":{"backgroundColor":"#DB5E54","titleText":"我的","type":"default"},"isNVue":false}},{"path":"pages/contribute/contribute","meta":{"navigationBar":{"backgroundColor":"#DB5E54","titleText":"投稿","type":"default"},"isNVue":false}},{"path":"pages/login/login","meta":{"navigationBar":{"backgroundColor":"#DB5E54","titleText":"登录","type":"default"},"isNVue":false}},{"path":"pages/mycontribute/mycontribute","meta":{"enablePullDownRefresh":true,"navigationBar":{"backgroundColor":"#DB5E54","titleText":"我的投稿","type":"default"},"isNVue":false}},{"path":"pages/tocontribute/tocontribute","meta":{"isQuit":true,"isTabBar":true,"tabBarIndex":1,"navigationBar":{"backgroundColor":"#DB5E54","titleText":"投稿","type":"default"},"isNVue":false}},{"path":"pages/audit/audit","meta":{"enablePullDownRefresh":true,"navigationBar":{"backgroundColor":"#DB5E54","titleText":"待审核列表","type":"default"},"isNVue":false}},{"path":"pages/exchange/exchange","meta":{"navigationBar":{"backgroundColor":"#DB5E54","titleText":"兑换","type":"default"},"isNVue":false}},{"path":"pages/pdointsdetails/pdointsdetails","meta":{"navigationBar":{"backgroundColor":"#DB5E54","titleText":"积分明细","type":"default"},"isNVue":false}},{"path":"pages/myexchange/myexchange","meta":{"navigationBar":{"backgroundColor":"#DB5E54","titleText":"我的兑换","type":"default"},"isNVue":false}},{"path":"pages/detail/detail","meta":{"navigationBar":{"backgroundColor":"#DB5E54","titleText":"详情","type":"default"},"isNVue":false}},{"path":"pages/resourcereview/resourcereview","meta":{"navigationBar":{"backgroundColor":"#DB5E54","titleText":"资源审核","type":"default"},"isNVue":false}},{"path":"pages/webview","meta":{"navigationBar":{"titleText":"","type":"default"},"isNVue":false}}].map(uniRoute=>(uniRoute.meta.route=uniRoute.path,__uniConfig.pages.push(uniRoute.path),uniRoute.path='/'+uniRoute.path,uniRoute));
  __uniConfig.styles=[];//styles
  __uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
  __uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
  service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:16})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:u,window:u,document:u,frames:u,self:u,location:u,navigator:u,localStorage:u,history:u,Caches:u,screen:u,alert:u,confirm:u,prompt:u,fetch:u,XMLHttpRequest:u,WebSocket:u,webkit:u,print:u}}}}); 
  })();
  