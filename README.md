## 组织结构

``` lua
mall
├── mall-common -- 工具类及通用代码模块
├── mall-mbg -- MyBatisGenerator生成的数据库操作代码模块
├── mall-security -- 封装SpringSecurity+JWT的安全认证的模块
├── mall-admin -- 后台管理系统服务
├── mall-search -- 基于Elasticsearch的商品搜索系统服务
├── mall-portal -- 移动端商城系统服务
```
## 表业务说明
前缀：
* cms_ 网站内容管理
* oms_ 订单管理
* pms_ 产品管理
* sms_ 营销管理(秒杀活动,优惠券,热门推荐，首页焦点推荐)
* ums_ 系统用户管理(会员用户，管理员用户)
