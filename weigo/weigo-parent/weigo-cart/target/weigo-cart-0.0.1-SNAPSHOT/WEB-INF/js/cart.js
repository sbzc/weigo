var TTCart = {
	load : function(){ // 加载购物车数据
		
	},
	itemNumChange : function(){
		$(".increment").click(function(){//＋
			var _thisInput = $(this).siblings("input");
			_thisInput.val(eval(_thisInput.val()) + 1);
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".action",function(data){
				TTCart.refreshTotalPrice();
			});
			TTCart.refreshTotalNum();
		});
		$(".decrement").click(function(){//-
			var _thisInput = $(this).siblings("input");
			if(eval(_thisInput.val()) == 1){
				return ;
			}
			_thisInput.val(eval(_thisInput.val()) - 1);
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".action",function(data){
				TTCart.refreshTotalPrice();
			});
			TTCart.refreshTotalNum();
		});
		$(".quantity-form .quantity-text").rnumber(1);//限制只能输入数字
		$(".quantity-form .quantity-text").change(function(){
			var _thisInput = $(this);
			$.post("/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".action",function(data){
				TTCart.refreshTotalPrice();
			});
			
			TTCart.refreshTotalNum();
		});
	},
	refreshTotalPrice : function(){ //重新计算总价
		var total = 0;
		$(".quantity-form .quantity-text").each(function(i,e){
			var _this = $(e);
			if(_this.parent().parent().siblings(".p-checkbox").children().eq(0)[0].checked){
				total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
			}
		});
		$(".totalSkuPrice").html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
			 prefix: '￥',
			 thousandsSeparator: ',',
			 centsLimit: 2
		});
		
		TTCart.refreshTotalNum();
	},
	refreshTotalNum: function(){//计算总数量
		var totalNum = 0;
		$(".quantity-form .quantity-text").each(function(i,e){
			var _this = $(e);
			if(_this.parent().parent().siblings(".p-checkbox").children().eq(0)[0].checked){
				totalNum += parseInt(_this.val());
			}
		});
		$("#selectedCount").html(totalNum);
	}
};

$(function(){
	TTCart.load();
	TTCart.itemNumChange();
	TTCart.refreshTotalNum();
	
	//对删除超链接添加点击事情
	$(".mycart_remove").click(function(){
		var $a =$(this);
		var href=$(this).attr("href");
		$.post(href,function(data){
			if(data.code==1){
				//parent()当前标签的父标签
				$a.parent().parent().parent().remove();
				TTCart.refreshTotalPrice();
				TTCart.refreshTotalNum();
			}
		});
		return false;
	});
	//对复选框添加点击事件
	$(".checkbox").click(function(){

		var total = 0;
		$(".quantity-form .quantity-text").each(function(i,e){
			var _this = $(e);
			if(_this.parent().parent().siblings(".p-checkbox").children().eq(0)[0].checked){
				total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
			}
		});
		$(".totalSkuPrice").html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
			 prefix: '￥',
			 thousandsSeparator: ',',
			 centsLimit: 2
		});
		
		TTCart.refreshTotalNum();
		if($(".checkbox:checked").length==$(".checkbox").length){
			$('input[name="toggle-checkboxes"]').prop("checked",true);
		}else{
			$('input[name="toggle-checkboxes"]').prop("checked",false);
		}
	});
	//复选框全选
	
	$('input[name="toggle-checkboxes"]').click(function(){
		
		if($(this).is(':checked')){
			$('.checkbox').each(function(){
				$(this).prop("checked",true);
			});
			$('input[name="toggle-checkboxes"]').prop("checked",true);
		}else{
			$('.checkbox').each(function(){
				$(this).prop("checked",false);
			});
			$('input[name="toggle-checkboxes"]').prop("checked",false);
		}
		TTCart.refreshTotalNum();
		TTCart.refreshTotalPrice();
	   
	});
    //删除所选商品
	$("#remove-batch").click(function(){
		var param="";
		$.each($(".checkbox:checked"),function(i,n){
			param += $(n).val();
			if(i<$(".checkbox:checked").length-1){
				param+=",";
			}
		});
		console.log("param="+param);
		$.post("/cart/delete/"+param+".action",function(data){
			if(data.code==1){
				 location.reload();
			}
		});
		
	});
	
	//"去结算"按钮点击事件
	$("#toSettlement").click(function(){
		//alert($(".checkbox:checked").length);
		//i脚标   n当前循环时对象,对象是一个dom对象
		var param = "";
		console.log($(".checkbox:checked").length);
		
		
		if($(".checkbox:checked").length==0){
			alert("还没选择商品！！！")
			return false;
		}else{
			
		
			$.each($(".checkbox:checked"),function(i,n){
				//alert($(n).val());
				param+="id="+$(n).val();
				if(i<$(".checkbox:checked").length-1){
					param+="&";
				}
			});
			//alert(param);
			location.href=$(this).attr("href")+"?"+param;
			return false;
		}
	});
	
	
	
});