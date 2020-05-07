package com.weigo.commons.pojo;

public class ConstantObject {
   
    //商品分类根目录
    public static final Integer itemCarRoot = 2;
    //大广告id
    public static final Long bigPics = 89l;
    //大广告下的广告    
    public static final Long bigPicBlows = 90l;
    //热门商品的广告
    public static final Long hostItemPicBlows = 96l;
    //待发货
    public static final Integer resetOrderItem = 0;
    //用户退款
    public static final Integer refundOrderItem = 1;
    //商家同意退款
    public static final Integer  refundOrderItemSuccess= 2;
    //等待快递员接单
    public static final Integer prepareSendOrderItem = 3;
    //送货中
    public static final Integer SendOrderItem = 4;
    //已签收待确认收货
    public static final Integer signOnOrderItem=5;
    //确认收货
    public  static final Integer finishOrderItem = 6;
    //订单状态集合
    public  static final Integer [] orderItemStartArry = {resetOrderItem,refundOrderItem,refundOrderItemSuccess,prepareSendOrderItem,
    		                                              SendOrderItem,signOnOrderItem,finishOrderItem};
    //订单状态集合（中文表示）
    public  static final String [] orderItemStartStrArry = {"待发货","申请退款","已退款","待接单",
    		                                              "配送中","已签收","已完成"};
    //后台主页
    public  static final Integer managePage=1;
    //前主页
    public  static final Integer PortalPage=2;
    //搜索主页
    public  static final Integer searchPage=3;
    //购物车
    public  static final Integer cartPage=4;
    //商品详情
    public  static final Integer itemdescPage=5;
    //快递接单页面
    public  static final Integer dispatcherPage=6;
    public  static final String[] visitorPageArray= {"","后台主页","前台主页","搜索主页","购物车主页","商品详情页","快递页面"};
    
   
    
    
}
